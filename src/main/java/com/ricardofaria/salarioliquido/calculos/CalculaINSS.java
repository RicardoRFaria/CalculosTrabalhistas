package com.ricardofaria.salarioliquido.calculos;

import java.math.BigDecimal;

import com.ricardofaria.salarioliquido.util.PrecisionUtil;

/**
 * Classe responsavel por efetuar os calculos de INSS do funcionario </br>
 * Referencia para os dados @see <a href=
 * "http://www.previdencia.gov.br/servicos-ao-cidadao/todos-os-servicos/gps/tabela-contribuicao-mensal/" >Faixas
 * de desconto INSS - Tabela 2016</a>
 *
 * @author Ricardo Faria
 *
 */
public class CalculaINSS {

	public static final float VALOR_LIMITE_FAIXA1 = 1659.38f;
    public static final float VALOR_LIMITE_FAIXA2 = 2765.66f;
    public static final float VALOR_LIMITE_FAIXA3 = 5531.31f;

    public static final BigDecimal MULTIPLICADOR_FAIXA1 = new BigDecimal("0.08");
    public static final BigDecimal MULTIPLICADOR_FAIXA2 = new BigDecimal("0.09");
    public static final BigDecimal MULTIPLICADOR_FAIXA3 = new BigDecimal("0.11");
    public static final BigDecimal TETO_INSS = new BigDecimal("608.44");

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
