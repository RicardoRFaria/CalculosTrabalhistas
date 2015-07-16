package com.ricardofaria.salarioliquido.util;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

import com.ricardofaria.salarioliquido.model.input.ParametrosDecimoTerceiro;

/**
 * 
 * Classe com m�todos auxiliares para os c�lculos
 * 
 * @author Ricardo Faria
 *
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
	public static float reduzirDecimoTerceiro(ParametrosDecimoTerceiro parametro) {
		int quantidadeMeses = 12 - parametro.getMesDeInicioFuncionario();
		if (parametro.getDiaDeInicioFuncionar() <= 15) {
			quantidadeMeses++;
		}
		float salarioParaCalculo = (parametro.getSalarioBruto() / 12) * quantidadeMeses;
		return salarioParaCalculo;
	}

	/**
	 * Efetua a redu��o do sal�rio pago por m�s ao funcion�rio com base na data
	 * de in�cio de trabalho e nos dias do m�s in�cio
	 * 
	 * @param salarioBruto
	 *            pago por m�s ao funcion�rio
	 * @param dataInicio
	 *            do funcion�rio na empresa
	 * @return sal�rio parcial
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
	
	/**
	 * Efetua a redu��o do sal�rio pago por m�s ao funcion�rio com base na data
	 * de in�cio de trabalho e nos dias do m�s in�cio
	 * 
	 * @param salarioBruto
	 *            pago por m�s ao funcion�rio
	 * @param dataInicio
	 *            do funcion�rio na empresa
	 * @return sal�rio parcial
	 */
	public static BigDecimal reduzirSalarioPorDataDeInicio(BigDecimal salarioBruto, Date dataInicio) {
		return new BigDecimal(reduzirSalarioPorDataDeInicio(salarioBruto.floatValue(), dataInicio));
	}
}
