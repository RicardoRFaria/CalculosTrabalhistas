package com.ricardofaria.salarioliquido.util;

/**
 * Created by Ricardo on 14/12/2014.
 */
public class ReduzSalarioPorData {

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
		mesDeInicio = 12 - mesDeInicio;
		if (diaDeInicio <= 15) {
			mesDeInicio++;
		}
		salarioBruto = (salarioBruto / 12) * mesDeInicio;
		return salarioBruto;
	}
}
