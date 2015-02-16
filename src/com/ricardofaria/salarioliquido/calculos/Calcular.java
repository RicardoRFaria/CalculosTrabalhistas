package com.ricardofaria.salarioliquido.calculos;

import com.ricardofaria.salarioliquido.model.Salario;

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

}
