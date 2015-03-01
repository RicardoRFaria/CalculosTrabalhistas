package com.ricardofaria.salarioliquido.util;

/**
 * 
 * Classe com métodos auxiliares para os cálculos
 * 
 * @author Ricardo Faria
 *
 */
public class ReduzSalarioPorData {
	
	private ReduzSalarioPorData() {
		super();
	}

	/**
	 * Reduz o salário do funcionário com base na data de entrada, útil para
	 * cálculos parciais de 13º
	 *
	 * @param salarioBruto
	 *            atual do funcionário
	 * @param diaDeInicio
	 *            do funcionário na empresa
	 * @param mesDeInicio
	 *            do funcionário na empresa
	 * @return salário reduzido com base no tempo de trabalho
	 */
	public static float reduzirDecimoTerceiro(float salarioBruto,
			int diaDeInicio, int mesDeInicio) {
		int quantidadeMeses = 12 - mesDeInicio;
		if (diaDeInicio <= 15) {
			quantidadeMeses++;
		}
		float salarioParaCalculo = (salarioBruto / 12) * quantidadeMeses;
		return salarioParaCalculo;
	}
}
