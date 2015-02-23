package com.ricardofaria.salarioliquido.calculos;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.ricardofaria.salarioliquido.model.DecimoTerceiro;
import com.ricardofaria.salarioliquido.model.DecimoTerceiro.TIPO_DECIMO_TERCEIRO;
import com.ricardofaria.salarioliquido.model.Ferias;
import com.ricardofaria.salarioliquido.model.Salario;
import com.ricardofaria.salarioliquido.model.Ferias.TIPO_FERIAS;
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

	public Salario calcularSalario(float salarioBruto, int numeroDependentes) {
		float inss = CalculaINSS.calcular(salarioBruto);
		float salarioParaIrpf = salarioBruto - inss;
		float irpf = CalculaImpostoDeRenda.calcular(salarioParaIrpf,
				numeroDependentes);
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

	public Ferias calcularFerias(float salarioBruto, int numeroDependentes,
			TIPO_FERIAS tipo) {
		Ferias feriasObject = new Ferias();
		BigDecimal salarioBrutoObj = createMonetaryBigDecimal(salarioBruto);

		salarioBrutoObj = aplicarModificarDeFeriasParcial(salarioBrutoObj, tipo);

		BigDecimal ferias = salarioBrutoObj
				.multiply(new BigDecimal("0.333333"));
		feriasObject.setValorFerias(ferias.floatValue());

		BigDecimal salarioCalculo = ferias.add(salarioBrutoObj);

		BigDecimal descontoInss = CalculaINSS.calcular(salarioCalculo);
		BigDecimal salarioDescontado = salarioCalculo.subtract(descontoInss);
		BigDecimal descontoImpostoDeRenda = CalculaImpostoDeRenda.calcular(
				salarioDescontado, numeroDependentes);
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
	public BigDecimal aplicarModificarDeFeriasParcial(BigDecimal salarioBruto,
			TIPO_FERIAS tipo) {
		if (tipo == TIPO_FERIAS.DIAS_20) {
			// Aqui o m�ximo de casas
			salarioBruto = salarioBruto.divide(createMonetaryBigDecimal("30"),
					10, RoundingMode.HALF_EVEN);
			salarioBruto = salarioBruto
					.multiply(createMonetaryBigDecimal("20"));
			// Aqui reduzimos para dinheiro
			return changeToMonetaryBidecimal(salarioBruto);
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
		BigDecimal umDia = salarioBruto.divide(createMonetaryBigDecimal("30"),
				10, RoundingMode.HALF_EVEN);
		BigDecimal dezDias = umDia.multiply(createMonetaryBigDecimal("10"));
		BigDecimal abonoPecuniario = dezDias
				.multiply(new BigDecimal("1.333333"));

		// Aqui reduzimos para dinheiro
		return changeToMonetaryBidecimal(abonoPecuniario);
	}

	public Ferias calcularFerias(float salarioBruto, int numeroDependentes) {
		return calcularFerias(salarioBruto, numeroDependentes,
				TIPO_FERIAS.COMPLETA);
	}

	public Ferias calcularFerias(float salarioBruto) {
		return calcularFerias(salarioBruto, 0);

	}

	public Ferias calcularFerias(float salarioBruto, TIPO_FERIAS tipo) {
		return calcularFerias(salarioBruto, 0, tipo);

	}

	/**
	 * Efetua o c�lculo de d�cimo terceiro completo do funcion�rio (Situa��o
	 * normal, o funcion�rio in�ciou o trabalho antes do ano come�ar)
	 * 
	 * @param salarioBruto
	 * @param numeroDependentes
	 * @return
	 */
	public DecimoTerceiro calcularDecimoTerceiro(BigDecimal salarioBruto,
			int numeroDependentes) {
		BigDecimal descontoInss = CalculaINSS.calcular(salarioBruto);
		BigDecimal salarioDescontado = salarioBruto.subtract(descontoInss);
		BigDecimal descontoImpostoDeRenda = CalculaImpostoDeRenda.calcular(
				salarioDescontado, numeroDependentes);
		salarioDescontado = salarioDescontado.subtract(descontoImpostoDeRenda);

		DecimoTerceiro decimoTerceiro = new DecimoTerceiro(
				salarioBruto.floatValue());
		decimoTerceiro.setDescontoInss(descontoInss.floatValue());
		decimoTerceiro.setDescontoIrpf(descontoImpostoDeRenda.floatValue());
		decimoTerceiro.setSalarioParcelaUm(salarioBruto.divide(
				new BigDecimal("2")).floatValue());

		BigDecimal parcelaDois = salarioBruto.divide(new BigDecimal("2"))
				.subtract(descontoInss).subtract(descontoImpostoDeRenda);
		decimoTerceiro.setSalarioParcelaDois(parcelaDois.floatValue());

		decimoTerceiro.setTipo(TIPO_DECIMO_TERCEIRO.COMPLETO);

		return decimoTerceiro;
	}

	public DecimoTerceiro calcularDecimoTerceiro(float salarioBrutoOriginal,
			int numeroDependentes) {
		BigDecimal salarioBruto = createMonetaryBigDecimal(salarioBrutoOriginal);
		return calcularDecimoTerceiro(salarioBruto, numeroDependentes);
	}

	/**
	 * Efetua o c�lculo parcial de d�cimo terceiro do funcion�rio. (Utilizado
	 * nos casos onde o funcion�rio come�ou a trabalhar ap�s o in�cio do ano)
	 * 
	 * @param salarioBrutoOriginal
	 * @param numeroDependentes
	 * @param diaDeInicioFuncionar
	 * @param mesDeInicioFuncionario
	 * @return
	 */
	public DecimoTerceiro calcularDecimoTerceiro(float salarioBrutoOriginal,
			int numeroDependentes, int diaDeInicioFuncionar,
			int mesDeInicioFuncionario) {
		float salarioBrutoReduzido = ReduzSalarioPorData.reduzirDecimoTerceiro(
				salarioBrutoOriginal, diaDeInicioFuncionar,
				mesDeInicioFuncionario);

		BigDecimal salarioBruto = createMonetaryBigDecimal(salarioBrutoReduzido);

		DecimoTerceiro decimoTerceiro = calcularDecimoTerceiro(salarioBruto,
				numeroDependentes);
		decimoTerceiro.setSalarioBruto(salarioBrutoOriginal);
		decimoTerceiro.setTipo(TIPO_DECIMO_TERCEIRO.PARCIAL);

		return decimoTerceiro;
	}

}
