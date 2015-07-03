package com.ricardofaria.salarioliquido.util;

import java.util.Calendar;
import java.util.Date;

public class TempoUtil {
	
	/**
	 * Calcula a quantidade de folgas presentes no m�s do funcion�rio (sem levar
	 * em considera��o feriados)
	 * 
	 * @param mes
	 *            date com m�s corrente
	 * @return quantidade de domingos
	 */
	public static int calcularDiasDeFolgaNoMes(Date mes) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(mes);
		calendar.set(Calendar.DAY_OF_MONTH, 1);

		int quantidadeDomingos = 0;
		do {
			if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
				quantidadeDomingos++;
			}
			calendar.roll(Calendar.DAY_OF_MONTH, true);
		} while (calendar.get(Calendar.DAY_OF_MONTH) != 1);

		return quantidadeDomingos;
	}

}
