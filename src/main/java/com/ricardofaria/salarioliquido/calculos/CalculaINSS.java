package com.ricardofaria.salarioliquido.calculos;

import java.math.BigDecimal;

import com.ricardofaria.salarioliquido.util.PrecisionUtil;

/**
 * Classe responsável por efetuar os cálculos de INSS do funcionário </br>
 * Referência para os dados @see <a href=
 * "http://www.tabelainss2015.com/nova-tabela-inss-2015-atualizada.html" >Faixas
 * de desconto INSS - Tabela 2015</a>
 * 
 * @author Ricardo Faria
 *
 */
public class CalculaINSS {

	public static final double VALOR_LIMITE_FAIXA1 = 1399.12;
    public static final double VALOR_LIMITE_FAIXA2 = 2331.88;
    public static final double VALOR_LIMITE_FAIXA3 = 4663.75;

	public static float calcular(Float salarioBruto) {

		return calcular(new BigDecimal(salarioBruto.toString())).floatValue();
	}

	public static BigDecimal calcular(BigDecimal salarioBruto) {
		BigDecimal retorno;
		if (salarioBruto.floatValue() <= VALOR_LIMITE_FAIXA1) {
			retorno = salarioBruto.multiply(new BigDecimal("0.08"));
		} else if (salarioBruto.floatValue() <= VALOR_LIMITE_FAIXA2) {
			retorno = salarioBruto.multiply(new BigDecimal("0.09"));
		} else if (salarioBruto.floatValue() <= VALOR_LIMITE_FAIXA3) {
			retorno = salarioBruto.multiply(new BigDecimal("0.11"));
		} else {
			retorno = new BigDecimal("513.01");
		}
		PrecisionUtil.changeToMonetaryBidecimal(retorno);
		return retorno;
	}
}
