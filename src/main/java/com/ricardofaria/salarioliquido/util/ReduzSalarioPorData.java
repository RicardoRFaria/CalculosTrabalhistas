package com.ricardofaria.salarioliquido.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Calendar;
import java.util.Date;

import com.ricardofaria.salarioliquido.model.input.ParametrosDecimoTerceiro;

/**
 * 
 * Classe com métodos auxiliares para os cálculos
 * 
 * @author Ricardo Faria
 *
 */
public class ReduzSalarioPorData {

	private static final int METADE_DO_MES_FINANCEIRO = 15;
	private static final int QUANTIDADE_MESES_ANO = 12;

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
	 * Efetua a redução do salário pago por mês ao funcionário com base na data
	 * de início de trabalho e nos dias do mês início
	 * 
	 * @param salarioBruto
	 *            pago por mês ao funcionário
	 * @param dataInicio
	 *            do funcionário na empresa
	 * @return salário parcial
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
