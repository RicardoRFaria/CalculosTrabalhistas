package com.ricardofaria.salarioliquido.calculos;

import java.math.BigDecimal;

import static com.ricardofaria.salarioliquido.util.PrecisionUtil.*;

/**
 * Classe responsável por efetuar os cálculos de imposto de renda do funcionário
 * </br> Referência para os dados @see <a href=
 * "http://www.receita.fazenda.gov.br/AutomaticoSRFSinot/2014/05/02/2014_05_02_10_37_34_298605328.html"
 * >Receita Federal - Tabela 2015</a>
 * 
 * @author Ricardo Faria
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

	/**
	 * Calcula o IRPF com base na base de cálculo informada e quantidade
	 * dependentes
	 * 
	 * @param baseCalculo
	 *            formada pelo salário bruto - desconto do INSS
	 * @param qtdDependentes
	 *            registrados no nome do funcionário
	 * @return valor a ser pago em IRPF
	 */
	public static float calcular(Float baseCalculo, int qtdDependentes) {
		return calcular(new BigDecimal(baseCalculo.toString()), qtdDependentes)
				.floatValue();
	}

	public static BigDecimal calcular(BigDecimal baseCalculo, int qtdDependentes) {
		BigDecimal valorDesconto;
		BigDecimal valorImposto;
		BigDecimal valorPorDependentes = new BigDecimal(VALOR_POR_DEPENDENTE
				* qtdDependentes);

		baseCalculo = baseCalculo.subtract(valorPorDependentes);

		if (baseCalculo.floatValue() <= VALOR_LIMITE_FAIXA1) {
			return BigDecimal.ZERO;
		} else if (baseCalculo.floatValue() <= VALOR_LIMITE_FAIXA2) {
			valorDesconto = createMonetaryBigDecimal(VALOR_DEDUCAO_FAIXA2);
			valorImposto = baseCalculo.multiply(createMonetaryBigDecimal("0.075"));
		} else if (baseCalculo.floatValue() <= VALOR_LIMITE_FAIXA3) {
			valorDesconto = createMonetaryBigDecimal(VALOR_DEDUCAO_FAIXA3);
			valorImposto = baseCalculo.multiply(createMonetaryBigDecimal("0.15"));
		} else if (baseCalculo.floatValue() <= VALOR_LIMITE_FAIXA4) {
			valorDesconto = createMonetaryBigDecimal(VALOR_DEDUCAO_FAIXA4);
			valorImposto = baseCalculo.multiply(createMonetaryBigDecimal("0.225"));
		} else {
			valorDesconto = createMonetaryBigDecimal(VALOR_DEDUCAO_MAXIMO);
			valorImposto = baseCalculo.multiply(createMonetaryBigDecimal("0.275"));
		}
		valorImposto = valorImposto.subtract(valorDesconto);
		if (valorImposto.floatValue() < 0) {
			return BigDecimal.ZERO;
		}

		changeToMonetaryBidecimal(valorImposto);
		return valorImposto;

	}

}
