package com.ricardofaria.salarioliquido.calculos;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.ricardofaria.salarioliquido.model.Ferias;
import com.ricardofaria.salarioliquido.model.Salario;
import com.ricardofaria.salarioliquido.model.Ferias.TIPO_FERIAS;

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
	
	public Ferias calcularFerias(float salarioBruto, int numeroDependentes, TIPO_FERIAS tipo) {
		Ferias feriasObject = new Ferias();
		float salarioBrutoOriginal = salarioBruto;
		BigDecimal salarioBrutoObj = new BigDecimal(Float.toString(salarioBruto));

		if (tipo == TIPO_FERIAS.DIAS_20) {
			salarioBrutoObj = salarioBrutoObj.divide(new BigDecimal(30), 2, RoundingMode.HALF_EVEN);
			salarioBrutoObj = salarioBrutoObj.multiply(new BigDecimal(20));
		}
		BigDecimal ferias = new BigDecimal(salarioBrutoObj.toString()).multiply(new BigDecimal(0.333333));
		feriasObject.setValorFerias(ferias.floatValue());

		Float salarioCalculo = ferias.floatValue() + salarioBrutoObj.floatValue();

		float descontoInss = CalculaINSS.calcular(salarioCalculo);
		float salarioDescontado = salarioCalculo - descontoInss;
		float descontoImpostoDeRenda = CalculaImpostoDeRenda.calcular(salarioDescontado, numeroDependentes);
		salarioDescontado -= descontoImpostoDeRenda;


		if (tipo == TIPO_FERIAS.DIAS_20) {
			Double abonoPecuniario = Double.valueOf(salarioBrutoOriginal / 30) * 10;
			abonoPecuniario *= 1.333;
			feriasObject.setAbonoPecuniario(abonoPecuniario.floatValue());

			salarioDescontado += abonoPecuniario;
		}
		feriasObject.setFeriasLiquidas(salarioDescontado);
		feriasObject.setDescontoInss(descontoInss);
		feriasObject.setDescontoIrpf(descontoImpostoDeRenda);

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

}
