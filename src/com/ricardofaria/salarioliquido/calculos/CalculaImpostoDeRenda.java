package com.ricardofaria.salarioliquido.calculos;

/**
 * Classe responsável por efetuar os cálculos de imposto de renda do funcionário
 * 
 * @author Ricardo
 *
 */
class CalculaImpostoDeRenda {

	public static final float VALOR_POR_DEPENDENTE = 187.80F;

	public static final float VALOR_LIMITE_FAIXA1 = 1868.22f;
	public static final float VALOR_LIMITE_FAIXA2 = 2799.86f;
	public static final float VALOR_LIMITE_FAIXA3 = 3733.19f;
	public static final float VALOR_LIMITE_FAIXA4 = 4664.68f;
	
	public static final float VALOR_DEDUCAO_FAIXA2 = 140.12f;
	public static final float VALOR_DEDUCAO_FAIXA3 = 350.11f;
	public static final float VALOR_DEDUCAO_FAIXA4 = 630.10f;
	public static final float VALOR_DEDUCAO_MAXIMO = 863.33f;

	public static float calcular(Float salario, int qtdDependentes) {
		float valorDesconto = 0f;
		float valorImposto = 0f;
		float valorPorDependentes = VALOR_POR_DEPENDENTE * qtdDependentes;

		salario -= valorPorDependentes;

		if (salario <= VALOR_LIMITE_FAIXA1) {
			return 0f;
		} else if (salario <= VALOR_LIMITE_FAIXA2) {
			valorDesconto = VALOR_DEDUCAO_FAIXA2;
			valorImposto = (float) (salario * 0.075);
		} else if (salario <= VALOR_LIMITE_FAIXA3) {
			valorDesconto = VALOR_DEDUCAO_FAIXA3;
			valorImposto = (float) (salario * 0.15);
		} else if (salario <= VALOR_LIMITE_FAIXA4) {
			valorDesconto = VALOR_DEDUCAO_FAIXA4;
			valorImposto = (float) (salario * 0.225);
		} else {
			valorDesconto = VALOR_DEDUCAO_MAXIMO;
			valorImposto = (float) (salario * 0.275);
		}
		valorImposto -= valorDesconto;
		if (valorImposto < 0) {
			valorImposto = 0;
		}

		return valorImposto;
	}

}
