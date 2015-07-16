package com.ricardofaria.salarioliquido.calculos;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.ricardofaria.salarioliquido.model.input.ParametrosDecimoTerceiro;
import com.ricardofaria.salarioliquido.model.input.ParametrosFerias;
import com.ricardofaria.salarioliquido.model.input.ParametrosSalario;
import com.ricardofaria.salarioliquido.model.resultado.DecimoTerceiro;
import com.ricardofaria.salarioliquido.model.resultado.Ferias;
import com.ricardofaria.salarioliquido.model.resultado.Salario;
import com.ricardofaria.salarioliquido.model.resultado.DecimoTerceiro.TIPO_DECIMO_TERCEIRO;
import com.ricardofaria.salarioliquido.model.resultado.Ferias.TIPO_FERIAS;
import com.ricardofaria.salarioliquido.util.ReduzSalarioPorData;

import static com.ricardofaria.salarioliquido.util.PrecisionUtil.*;

/**
 * 
 * Classe respons�vel por chamar os c�lculos menores em sua ordem correta e
 * gerar o resultado para o c�lculo total
 * 
 * @author Ricardo Faria
 *
 */
public class Calcular {

	public Salario calcularSalario(ParametrosSalario parametro) {
		float salarioCalculo = parametro.getSalarioBruto();
		if (parametro.getDataInicioColaborador() != null) {
			salarioCalculo = ReduzSalarioPorData.reduzirSalarioPorDataDeInicio(parametro.getSalarioBruto(), parametro.getDataInicioColaborador());
		}
		
		float inss = CalculaINSS.calcular(salarioCalculo);
		float salarioParaIrpf = salarioCalculo - inss;
		float irpf = CalculaImpostoDeRenda.calcular(salarioParaIrpf, parametro.getNumeroDependentes());
		float salarioLivre = salarioCalculo - inss - irpf;

		Salario salario = new Salario(parametro.getSalarioBruto());
		salario.setDescontoInss(inss);
		salario.setDescontoIrpf(irpf);
		salario.setSalarioLiquido(salarioLivre);

		return salario;
	}

	public Ferias calcularFerias(ParametrosFerias parametro) {
		Ferias feriasObject = new Ferias();
		BigDecimal salarioBrutoObj = createMonetaryBigDecimal(parametro.getSalarioBruto());

		salarioBrutoObj = aplicarModificarDeFeriasParcial(salarioBrutoObj, parametro.getTipo());

		BigDecimal ferias = salarioBrutoObj.multiply(new BigDecimal("0.333333"));
		feriasObject.setValorFerias(ferias.floatValue());

		BigDecimal salarioCalculo = ferias.add(salarioBrutoObj);

		BigDecimal descontoInss = CalculaINSS.calcular(salarioCalculo);
		BigDecimal salarioDescontado = salarioCalculo.subtract(descontoInss);
		BigDecimal descontoImpostoDeRenda = CalculaImpostoDeRenda.calcular(salarioDescontado, parametro.getNumeroDependentes());
		salarioDescontado = salarioDescontado.subtract(descontoImpostoDeRenda);

		if (parametro.getTipo() == TIPO_FERIAS.DIAS_20) {
			BigDecimal abonoPecuniario = calcularAbonoPecuniario(parametro.getSalarioBruto());
			feriasObject.setAbonoPecuniario(abonoPecuniario.floatValue());
			salarioDescontado = salarioDescontado.add(abonoPecuniario);
		}

		feriasObject.setFeriasLiquidas(salarioDescontado.floatValue());
		feriasObject.setDescontoInss(descontoInss.floatValue());
		feriasObject.setDescontoIrpf(descontoImpostoDeRenda.floatValue());

		return feriasObject;
	}

	public Ferias calcularFeriasComAdiantamentoDeDecimoTerceiro(ParametrosFerias parametro) {
		Ferias ferias = calcularFerias(parametro);
		
		float primeiraParcelaDecimoTerceiro = parametro.getSalarioBruto() / 2;
		ferias.setAdiantamentoDecimoTerceiro(primeiraParcelaDecimoTerceiro);
		ferias.setFeriasLiquidas(ferias.getFeriasLiquidas() + primeiraParcelaDecimoTerceiro);
		
		return ferias;
	}
	
	/**
	 * Modifica o sal�rio bruto informado com base na modalidade de f�rias do
	 * funcion�rio
	 * 
	 * @param salarioBruto
	 *            definido para o funcion�rio
	 * @param tipo
	 *            de f�rias a ser aplicado
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
	 * Efetua o c�lculo do abono pecuni�rio em rela��o ao sal�rio do funcion�rio
	 * (Abono pecuni�rio s� existe na modalidade de F�rias 20 dias)
	 * 
	 * @param salarioBrutoOriginal
	 *            o mesmo inserido no in�cio do c�lculo para o funcion�rio
	 * @return
	 */
	public BigDecimal calcularAbonoPecuniario(float salarioBrutoOriginal) {
		BigDecimal salarioBruto = createMonetaryBigDecimal(salarioBrutoOriginal);
		// Aqui o m�ximo de casas
		BigDecimal umDia = salarioBruto.divide(createMonetaryBigDecimal("30"), 10, RoundingMode.HALF_EVEN);
		BigDecimal dezDias = umDia.multiply(createMonetaryBigDecimal("10"));
		BigDecimal abonoPecuniario = dezDias.multiply(new BigDecimal("1.333333"));

		// Aqui reduzimos para dinheiro
		return changeToMonetaryBidecimal(abonoPecuniario);
	}

	/**
	 * Efetua o c�lculo de d�cimo terceiro completo do funcion�rio (Situa��o
	 * normal, o funcion�rio in�ciou o trabalho antes do ano come�ar)
	 * 
	 * @param salarioBruto
	 * @param numeroDependentes
	 * @return
	 */
	public DecimoTerceiro calcularDecimoTerceiro(ParametrosDecimoTerceiro parametro) {
		BigDecimal salarioBruto;
		if (parametro.isSalarioReduzido()) {
			float salarioBrutoReduzido = ReduzSalarioPorData.reduzirDecimoTerceiro(parametro.getSalarioBruto(),
					parametro.getDiaDeInicioFuncionar(), parametro.getMesDeInicioFuncionario());

			salarioBruto = createMonetaryBigDecimal(salarioBrutoReduzido);
		} else {
			salarioBruto = createMonetaryBigDecimal(parametro.getSalarioBruto());
		}
		BigDecimal descontoInss = CalculaINSS.calcular(salarioBruto);
		BigDecimal salarioDescontado = salarioBruto.subtract(descontoInss);
		BigDecimal descontoImpostoDeRenda = CalculaImpostoDeRenda.calcular(salarioDescontado, parametro.getNumeroDependentes());
		salarioDescontado = salarioDescontado.subtract(descontoImpostoDeRenda);

		DecimoTerceiro decimoTerceiro = new DecimoTerceiro(salarioBruto.floatValue());
		decimoTerceiro.setDescontoInss(descontoInss.floatValue());
		decimoTerceiro.setDescontoIrpf(descontoImpostoDeRenda.floatValue());
		decimoTerceiro.setSalarioParcelaUm(CalculaDecimoTerceiro.calcularParcelaUm(salarioBruto));
		decimoTerceiro.setSalarioParcelaDois(
				CalculaDecimoTerceiro.calcularParcelaDois(salarioBruto, descontoInss, descontoImpostoDeRenda));

		if (parametro.isSalarioReduzido()) {
			decimoTerceiro.setTipo(TIPO_DECIMO_TERCEIRO.PARCIAL);			
		} else {
			decimoTerceiro.setTipo(TIPO_DECIMO_TERCEIRO.COMPLETO);			
		}

		return decimoTerceiro;
	}

}
