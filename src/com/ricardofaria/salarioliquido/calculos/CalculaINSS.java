package com.ricardofaria.salarioliquido.calculos;

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
		if (salarioBruto <= VALOR_LIMITE_FAIXA1) {
			return (float) (salarioBruto * 0.08);
		} else if (salarioBruto <= VALOR_LIMITE_FAIXA2) {
			return (float) (salarioBruto * 0.09);
		} else if (salarioBruto <= VALOR_LIMITE_FAIXA3) {
			return (float) (salarioBruto * 0.11);
		} else {
			return 513.0125f;
		}
	}
}
