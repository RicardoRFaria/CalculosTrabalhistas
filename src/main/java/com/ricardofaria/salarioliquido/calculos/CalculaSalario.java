package com.ricardofaria.salarioliquido.calculos;

import static com.ricardofaria.salarioliquido.util.PrecisionUtil.*;

import java.math.BigDecimal;

import com.ricardofaria.salarioliquido.model.input.ParametrosBase;
import com.ricardofaria.salarioliquido.model.resultado.Remuneracao;

public class CalculaSalario {
	
	private static final BigDecimal PORCENTAGEM_ADICIONAL_PERICULOSIDADE = new BigDecimal("0.3");
	
	public static float calcularSalarioBrutoComAdicionalDePericulosidade(float salarioBruto) {
		return calcularSalarioBrutoComAdicionalDePericulosidade(createMonetaryBigDecimal(salarioBruto)).floatValue();
	}
	
	public static BigDecimal calcularSalarioBrutoComAdicionalDePericulosidade(BigDecimal salarioBruto) {
		BigDecimal adicionalPericulosidade = calcularAdicionalDePericulosidade(salarioBruto);
		return salarioBruto.add(adicionalPericulosidade);
	}
	
	public static float calcularAdicionalDePericulosidade(float salario) {
		return calcularAdicionalDePericulosidade(createMonetaryBigDecimal(salario)).floatValue();
	}
	
	public static BigDecimal calcularAdicionalDePericulosidade(BigDecimal salario) {
		return salario.multiply(PORCENTAGEM_ADICIONAL_PERICULOSIDADE);
	}
	
	public static Remuneracao calcularRemuneracao(Remuneracao remuneracao, ParametrosBase parametro, float salarioCalculo) {
		float inss = CalculaINSS.calcular(salarioCalculo);
		float salarioParaIrpf = salarioCalculo - inss;
		float irpf = CalculaImpostoDeRenda.calcular(salarioParaIrpf, parametro.getNumeroDependentes());
		float salarioLivre = salarioCalculo - inss - irpf;
		float adicionalPericulosidade = CalculaSalario.calcularAdicionalDePericulosidade(salarioCalculo);
		
		remuneracao.setDescontoInss(inss);
		remuneracao.setDescontoIrpf(irpf);
		remuneracao.setValorLiquido(salarioLivre);
		remuneracao.setAdicionalPericulosidade(adicionalPericulosidade);
		return remuneracao;
	}

}
