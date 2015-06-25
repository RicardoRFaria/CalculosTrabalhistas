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

	public static final float VALOR_LIMITE_FAIXA1 = 1399.12f;
    public static final float VALOR_LIMITE_FAIXA2 = 2331.88f;
    public static final float VALOR_LIMITE_FAIXA3 = 4663.75f;
    
    public static final BigDecimal MULTIPLICADOR_FAIXA1 = new BigDecimal("0.08");
    public static final BigDecimal MULTIPLICADOR_FAIXA2 = new BigDecimal("0.09");
    public static final BigDecimal MULTIPLICADOR_FAIXA3 = new BigDecimal("0.11");
    public static final BigDecimal TETO_INSS = new BigDecimal("513.01");

	public static float calcular(Float salarioBruto) {

		return calcular(new BigDecimal(salarioBruto.toString())).floatValue();
	}

	public static BigDecimal calcular(BigDecimal salarioBruto) {
		BigDecimal retorno;
		if (salarioBruto.floatValue() <= VALOR_LIMITE_FAIXA1) {
			retorno = salarioBruto.multiply(MULTIPLICADOR_FAIXA1);
		} else if (salarioBruto.floatValue() <= VALOR_LIMITE_FAIXA2) {
			retorno = salarioBruto.multiply(MULTIPLICADOR_FAIXA2);
		} else if (salarioBruto.floatValue() <= VALOR_LIMITE_FAIXA3) {
			retorno = salarioBruto.multiply(MULTIPLICADOR_FAIXA3);
		} else {
			retorno = TETO_INSS;
		}
		PrecisionUtil.changeToMonetaryBidecimal(retorno);
		return retorno;
	}
}
