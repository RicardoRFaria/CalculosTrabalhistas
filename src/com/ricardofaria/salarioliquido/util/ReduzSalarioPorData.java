package com.ricardofaria.salarioliquido.util;

/**
 * Created by Ricardo on 14/12/2014.
 */
public class ReduzSalarioPorData {

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
		mesDeInicio = 12 - mesDeInicio;
		if (diaDeInicio <= 15) {
			mesDeInicio++;
		}
		salarioBruto = (salarioBruto / 12) * mesDeInicio;
		return salarioBruto;
	}
}
