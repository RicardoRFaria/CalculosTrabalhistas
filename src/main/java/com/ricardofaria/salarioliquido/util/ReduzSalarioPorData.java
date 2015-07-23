package com.ricardofaria.salarioliquido.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
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

	private static final int METADE_DO_MES_FINANCEIRO = 15;
	private static final int QUANTIDADE_MESES_ANO = 12;

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
	public static BigDecimal reduzirDecimoTerceiro(ParametrosDecimoTerceiro parametro) {
		int quantidadeMeses = QUANTIDADE_MESES_ANO - parametro.getMesDeInicioFuncionario();
		if (parametro.getDiaDeInicioFuncionar() <= METADE_DO_MES_FINANCEIRO) {
			quantidadeMeses++;
		}
		BigDecimal salarioParaCalculo = parametro.getSalarioBruto()
				.divide(new BigDecimal(QUANTIDADE_MESES_ANO), 4, RoundingMode.HALF_EVEN)
				.multiply(new BigDecimal(quantidadeMeses));
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
	public static BigDecimal reduzirSalarioPorDataDeInicio(BigDecimal salarioBruto, Date dataInicio) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(dataInicio);

		int quantidadeDiasMes = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		int diaAtual = calendar.get(Calendar.DAY_OF_MONTH);
		int diasTrabalhados = quantidadeDiasMes - (diaAtual - 1);

		BigDecimal salarioDiario = salarioBruto.divide(new BigDecimal(quantidadeDiasMes), 4, RoundingMode.HALF_EVEN);
		BigDecimal salarioReduzido = salarioDiario.multiply(new BigDecimal(diasTrabalhados));

		return salarioReduzido;
	}
}
