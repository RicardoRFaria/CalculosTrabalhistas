package com.ricardofaria.salarioliquido.util;

import java.util.Calendar;
import java.util.Date;

/**
 * 
 * Classe com métodos auxiliares para os cálculos
 * 
 * @author Ricardo Faria
 *
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
	public static float reduzirDecimoTerceiro(float salarioBruto, int diaDeInicio, int mesDeInicio) {
		int quantidadeMeses = 12 - mesDeInicio;
		if (diaDeInicio <= 15) {
			quantidadeMeses++;
		}
		float salarioParaCalculo = (salarioBruto / 12) * quantidadeMeses;
		return salarioParaCalculo;
	}

	/**
	 * Efetua a redução do salário pago por mês ao funcionário com base na data
	 * de início de trabalho e nos dias do mês início
	 * 
	 * @param salarioBruto
	 *            pago por mês ao funcionário
	 * @param dataInicio
	 *            do funcionário na empresa
	 * @return salário parcial
	 */
	public static float reduzirSalarioPorDataDeInicio(float salarioBruto, Date dataInicio) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(dataInicio);

		int quantidadeDiasMes = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		int diaAtual = calendar.get(Calendar.DAY_OF_MONTH);
		int diasTrabalhados = quantidadeDiasMes - (diaAtual - 1);

		float salarioDiario = salarioBruto / quantidadeDiasMes;
		float salarioReduzido = salarioDiario * diasTrabalhados;

		return salarioReduzido;
	}
}
