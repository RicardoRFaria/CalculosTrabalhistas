package com.ricardofaria.salarioliquido.calculos;

/**
 * Classe responsável por efetuar os cálculos de INSS do funcionário
 * 
 * @author Ricardo
 *
 */
public class CalculaINSS {

	public static final double VALOR_LIMITE_FAIXA1 = 1399.12;
	public static final double VALOR_LIMITE_FAIXA2 = 2331.88;
	public static final double VALOR_LIMITE_FAIXA3 = 4663.75;

	public static float calcular(Float salario) {
		if (salario <= VALOR_LIMITE_FAIXA1) {
			return (float) (salario * 0.08);
		} else if (salario <= VALOR_LIMITE_FAIXA2) {
			return (float) (salario * 0.09);
		} else if (salario <= VALOR_LIMITE_FAIXA3) {
			return (float) (salario * 0.11);
		} else {
			return 513.0125f;
		}
	}
}
