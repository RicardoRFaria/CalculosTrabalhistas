package com.ricardofaria.salarioliquido.calculos;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;

import com.ricardofaria.salarioliquido.model.DecimoTerceiro;
import com.ricardofaria.salarioliquido.model.DecimoTerceiro.TIPO_DECIMO_TERCEIRO;
import com.ricardofaria.salarioliquido.model.Ferias;
import com.ricardofaria.salarioliquido.model.Salario;
import com.ricardofaria.salarioliquido.model.Ferias.TIPO_FERIAS;
import com.ricardofaria.salarioliquido.util.ReduzSalarioPorData;

import static com.ricardofaria.salarioliquido.util.PrecisionUtil.*;

/**
 * 
 * Classe responsável por chamar os cálculos menores em sua ordem correta e
 * gerar o resultado para o cálculo total
 * 
 * @author Ricardo Faria
 *
 */
public class Calcular {

	public Salario calcularSalario(float salarioBruto, int numeroDependentes) {
		float inss = CalculaINSS.calcular(salarioBruto);
		float salarioParaIrpf = salarioBruto - inss;
		float irpf = CalculaImpostoDeRenda.calcular(salarioParaIrpf, numeroDependentes);
		float salarioLivre = salarioBruto - inss - irpf;

		Salario salario = new Salario(salarioBruto);
		salario.setDescontoInss(inss);
		salario.setDescontoIrpf(irpf);
		salario.setSalarioLiquido(salarioLivre);

		return salario;
	}

	public Salario calcularSalario(float salarioBruto) {
		return calcularSalario(salarioBruto, 0);
	}

	/**
	 * Efetua o cálculo de salário com base na data de início do funcionário
	 * 
	 * @param salarioBruto
	 *            por mês
	 * @param dataInicio
	 *            efetiva do trabalho
	 * @return salário parcial calculado
	 */
	public Salario calcularSalarioParcial(float salarioBruto, Date dataInicio) {
		return calcularSalarioParcial(salarioBruto, 0, dataInicio);
	}

	/**
	 * Efetua o cálculo de salário com base na data de início do funcionário
	 * 
	 * @param salarioBruto
	 *            por mês
	 * @param numeroDependentes
	 *            caso haja
	 * @param dataInicio
	 *            efetiva do trabalho
	 * @return salário parcial calculado
	 */
	public Salario calcularSalarioParcial(float salarioBruto, int numeroDependentes, Date dataInicio) {
		float salarioReduzido = ReduzSalarioPorData.reduzirSalarioPorDataDeInicio(salarioBruto, dataInicio);

		return calcularSalario(salarioReduzido, numeroDependentes);
	}

	public Ferias calcularFerias(float salarioBruto, int numeroDependentes, TIPO_FERIAS tipo) {
		Ferias feriasObject = new Ferias();
		BigDecimal salarioBrutoObj = createMonetaryBigDecimal(salarioBruto);

		salarioBrutoObj = aplicarModificarDeFeriasParcial(salarioBrutoObj, tipo);

		BigDecimal ferias = salarioBrutoObj.multiply(new BigDecimal("0.333333"));
		feriasObject.setValorFerias(ferias.floatValue());

		BigDecimal salarioCalculo = ferias.add(salarioBrutoObj);

		BigDecimal descontoInss = CalculaINSS.calcular(salarioCalculo);
		BigDecimal salarioDescontado = salarioCalculo.subtract(descontoInss);
		BigDecimal descontoImpostoDeRenda = CalculaImpostoDeRenda.calcular(salarioDescontado, numeroDependentes);
		salarioDescontado = salarioDescontado.subtract(descontoImpostoDeRenda);

		if (tipo == TIPO_FERIAS.DIAS_20) {
			BigDecimal abonoPecuniario = calcularAbonoPecuniario(salarioBruto);
			feriasObject.setAbonoPecuniario(abonoPecuniario.floatValue());
			salarioDescontado = salarioDescontado.add(abonoPecuniario);
		}

		feriasObject.setFeriasLiquidas(salarioDescontado.floatValue());
		feriasObject.setDescontoInss(descontoInss.floatValue());
		feriasObject.setDescontoIrpf(descontoImpostoDeRenda.floatValue());

		return feriasObject;
	}

	public Ferias calcularFerias(float salarioBruto, int numeroDependentes) {
		return calcularFerias(salarioBruto, numeroDependentes, TIPO_FERIAS.COMPLETA);
	}

	public Ferias calcularFerias(float salarioBruto) {
		return calcularFerias(salarioBruto, 0);

	}

	public Ferias calcularFerias(float salarioBruto, TIPO_FERIAS tipo) {
		return calcularFerias(salarioBruto, 0, tipo);
	}
	
	public Ferias calcularFeriasComAdiantamentoDeDecimoTerceiro(float salarioBruto, int numeroDependentes) {
		return calcularFeriasComAdiantamentoDeDecimoTerceiro(salarioBruto, numeroDependentes, TIPO_FERIAS.COMPLETA);
	}
	
	public Ferias calcularFeriasComAdiantamentoDeDecimoTerceiro(float salarioBruto, TIPO_FERIAS tipo) {
		return calcularFeriasComAdiantamentoDeDecimoTerceiro(salarioBruto, 0, tipo);
	}
	
	public Ferias calcularFeriasComAdiantamentoDeDecimoTerceiro(float salarioBruto, int numeroDependentes, TIPO_FERIAS tipo) {
		Ferias ferias = calcularFerias(salarioBruto, numeroDependentes, tipo);
		
		float primeiraParcelaDecimoTerceiro = salarioBruto / 2;
		ferias.setAdiantamentoDecimoTerceiro(primeiraParcelaDecimoTerceiro);
		ferias.setFeriasLiquidas(ferias.getFeriasLiquidas() + primeiraParcelaDecimoTerceiro);
		
		return ferias;
	}
	
	/**
	 * Modifica o salário bruto informado com base na modalidade de férias do
	 * funcionário
	 * 
	 * @param salarioBruto
	 *            definido para o funcionário
	 * @param tipo
	 *            de férias a ser aplicado
	 * @return
	 */
	public BigDecimal aplicarModificarDeFeriasParcial(BigDecimal salarioBruto, TIPO_FERIAS tipo) {
		if (tipo == TIPO_FERIAS.DIAS_20) {
			BigDecimal feriasParcial = salarioBruto.divide(createMonetaryBigDecimal("30"), 10, RoundingMode.HALF_EVEN);
			feriasParcial = feriasParcial.multiply(createMonetaryBigDecimal("20"));
			return changeToMonetaryBidecimal(feriasParcial);
		} else if (tipo == TIPO_FERIAS.DIAS_15) {
			BigDecimal feriasParcial = salarioBruto.divide(createMonetaryBigDecimal("30"), 10, RoundingMode.HALF_EVEN);
			feriasParcial = feriasParcial.multiply(createMonetaryBigDecimal("15"));
			return changeToMonetaryBidecimal(feriasParcial);
		}

		return salarioBruto;
	}

	/**
	 * Efetua o cálculo do abono pecuniário em relação ao salário do funcionário
	 * (Abono pecuniário só existe na modalidade de Férias 20 dias)
	 * 
	 * @param salarioBrutoOriginal
	 *            o mesmo inserido no início do cálculo para o funcionário
	 * @return
	 */
	public BigDecimal calcularAbonoPecuniario(float salarioBrutoOriginal) {
		BigDecimal salarioBruto = createMonetaryBigDecimal(salarioBrutoOriginal);
		// Aqui o máximo de casas
		BigDecimal umDia = salarioBruto.divide(createMonetaryBigDecimal("30"), 10, RoundingMode.HALF_EVEN);
		BigDecimal dezDias = umDia.multiply(createMonetaryBigDecimal("10"));
		BigDecimal abonoPecuniario = dezDias.multiply(new BigDecimal("1.333333"));

		// Aqui reduzimos para dinheiro
		return changeToMonetaryBidecimal(abonoPecuniario);
	}

	/**
	 * Efetua o cálculo de décimo terceiro completo do funcionário (Situação
	 * normal, o funcionário iníciou o trabalho antes do ano começar)
	 * 
	 * @param salarioBruto
	 * @param numeroDependentes
	 * @return
	 */
	public DecimoTerceiro calcularDecimoTerceiro(BigDecimal salarioBruto, int numeroDependentes) {
		BigDecimal descontoInss = CalculaINSS.calcular(salarioBruto);
		BigDecimal salarioDescontado = salarioBruto.subtract(descontoInss);
		BigDecimal descontoImpostoDeRenda = CalculaImpostoDeRenda.calcular(salarioDescontado, numeroDependentes);
		salarioDescontado = salarioDescontado.subtract(descontoImpostoDeRenda);

		DecimoTerceiro decimoTerceiro = new DecimoTerceiro(salarioBruto.floatValue());
		decimoTerceiro.setDescontoInss(descontoInss.floatValue());
		decimoTerceiro.setDescontoIrpf(descontoImpostoDeRenda.floatValue());
		decimoTerceiro.setSalarioParcelaUm(CalculaDecimoTerceiro.calcularParcelaUm(salarioBruto));
		decimoTerceiro.setSalarioParcelaDois(
				CalculaDecimoTerceiro.calcularParcelaDois(salarioBruto, descontoInss, descontoImpostoDeRenda));

		decimoTerceiro.setTipo(TIPO_DECIMO_TERCEIRO.COMPLETO);

		return decimoTerceiro;
	}

	public DecimoTerceiro calcularDecimoTerceiro(float salarioBrutoOriginal, int numeroDependentes) {
		BigDecimal salarioBruto = createMonetaryBigDecimal(salarioBrutoOriginal);
		return calcularDecimoTerceiro(salarioBruto, numeroDependentes);
	}

	/**
	 * Efetua o cálculo parcial de décimo terceiro do funcionário. (Utilizado
	 * nos casos onde o funcionário começou a trabalhar após o início do ano)
	 * 
	 * @param salarioBrutoOriginal
	 * @param numeroDependentes
	 * @param diaDeInicioFuncionar
	 * @param mesDeInicioFuncionario
	 * @return
	 */
	public DecimoTerceiro calcularDecimoTerceiro(float salarioBrutoOriginal, int numeroDependentes,
			int diaDeInicioFuncionar, int mesDeInicioFuncionario) {
		float salarioBrutoReduzido = ReduzSalarioPorData.reduzirDecimoTerceiro(salarioBrutoOriginal,
				diaDeInicioFuncionar, mesDeInicioFuncionario);

		BigDecimal salarioBruto = createMonetaryBigDecimal(salarioBrutoReduzido);

		DecimoTerceiro decimoTerceiro = calcularDecimoTerceiro(salarioBruto, numeroDependentes);
		decimoTerceiro.setSalarioBruto(salarioBrutoOriginal);
		decimoTerceiro.setTipo(TIPO_DECIMO_TERCEIRO.PARCIAL);

		return decimoTerceiro;
	}

}
