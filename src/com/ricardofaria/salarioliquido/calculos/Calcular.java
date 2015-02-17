package com.ricardofaria.salarioliquido.calculos;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.ricardofaria.salarioliquido.model.Ferias;
import com.ricardofaria.salarioliquido.model.Salario;
import com.ricardofaria.salarioliquido.model.Ferias.TIPO_FERIAS;

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
		BigDecimal salarioBrutoObj = new BigDecimal(
				Float.toString(salarioBruto));

		salarioBrutoObj = aplicarModificarDeFeriasParcial(salarioBrutoObj, tipo);

		BigDecimal ferias = new BigDecimal(salarioBrutoObj.toString())
				.multiply(new BigDecimal(0.333333));
		feriasObject.setValorFerias(ferias.floatValue());

		Float salarioCalculo = ferias.floatValue()
				+ salarioBrutoObj.floatValue();

		float descontoInss = CalculaINSS.calcular(salarioCalculo);
		float salarioDescontado = salarioCalculo - descontoInss;
		float descontoImpostoDeRenda = CalculaImpostoDeRenda.calcular(
				salarioDescontado, numeroDependentes);
		salarioDescontado -= descontoImpostoDeRenda;

		if (tipo == TIPO_FERIAS.DIAS_20) {
			salarioDescontado += calcularAbonoPecuniario(salarioBruto);
		}

		feriasObject.setFeriasLiquidas(salarioDescontado);
		feriasObject.setDescontoInss(descontoInss);
		feriasObject.setDescontoIrpf(descontoImpostoDeRenda);

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
			salarioBruto = salarioBruto.divide(new BigDecimal(30), 2,
					RoundingMode.HALF_EVEN);
			salarioBruto = salarioBruto.multiply(new BigDecimal(20));
		}

		return salarioBruto;
	}

	/**
	 * Efetua o c�lculo do abono pecuni�rio em rela��o ao sal�rio do funcion�rio
	 * (Abono pecuni�rio s� existe na modalidade de F�rias 20 dias)
	 * 
	 * @param salarioBruto
	 * @return
	 */
	public double calcularAbonoPecuniario(float salarioBruto) {
		Double abonoPecuniario = Double.valueOf(salarioBruto / 30) * 10;
		abonoPecuniario *= 1.333;

		return abonoPecuniario;
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

}
