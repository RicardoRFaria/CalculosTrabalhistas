package com.ricardofaria.salarioliquido.util;

/**
 * 
 * Classe com m�todos auxiliares para os c�lculos
 * 
 * @author Ricardo Faria
 *
 */
public class ReduzSalarioPorData {
	
	private ReduzSalarioPorData() {
		super();
	}

	/**
	 * Reduz o sal�rio do funcion�rio com base na data de entrada, �til para
	 * c�lculos parciais de 13�
	 *
	 * @param salarioBruto
	 *            atual do funcion�rio
	 * @param diaDeInicio
	 *            do funcion�rio na empresa
	 * @param mesDeInicio
	 *            do funcion�rio na empresa
	 * @return sal�rio reduzido com base no tempo de trabalho
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
