package com.ricardofaria.salarioliquido.util;

import java.util.Calendar;
import java.util.Date;

public class TempoUtil {

	private static final int DIAS_MES_FINANCEIRO = 30;
	private static final int DIAS_TRABALHO_SEMANA = 6;

	/**
	 * Calcula a quantidade de folgas presentes no mês do funcionário (sem levar
	 * em consideração feriados)
	 * 
	 * @param mes
	 *            date com mês corrente
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

	public static float calcularHorasMensais(float quantidadeHorasSemanais) {
		float quantidadeHorasDiarias = quantidadeHorasSemanais / DIAS_TRABALHO_SEMANA;
		float quantidadeHorasMensais = quantidadeHorasDiarias * DIAS_MES_FINANCEIRO;
		return quantidadeHorasMensais;
	}

	/**
	 * Convert hours in normal pattern HH:mm to equivalent float number
	 * 
	 * @param hoursInString
	 *            using pattern HH:mm
	 * @return You know what it returns
	 * @throws NumberFormatException
	 */
	public static float hoursToFloat(String hoursInString) throws NumberFormatException {
		float result = 0;
		hoursInString = hoursInString.trim();

		try {
			result = new Float(hoursInString);
		} catch (NumberFormatException nfe) {
			if (hoursInString.contains(":")) {
				int hours = 0;
				int minutes = 0;
				int locationOfColon = hoursInString.indexOf(":");
				try {
					hours = new Integer(hoursInString.substring(0, locationOfColon));
					minutes = new Integer(hoursInString.substring(locationOfColon + 1));
				} catch (NumberFormatException nfe2) {
					throw nfe2;
				}

				if (minutes > 0) {
					result = minutes / 60f;
				}

				result += hours;
			}
		}

		return result;
	}

}
