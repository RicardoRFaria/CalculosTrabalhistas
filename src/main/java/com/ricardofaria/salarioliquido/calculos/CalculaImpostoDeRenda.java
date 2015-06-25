package com.ricardofaria.salarioliquido.calculos;

import java.math.BigDecimal;

import static com.ricardofaria.salarioliquido.util.PrecisionUtil.*;

/**
 * Classe respons�vel por efetuar os c�lculos de imposto de renda do funcion�rio
 * </br> Refer�ncia para os dados @see <a href=
 * "http://www.receita.fazenda.gov.br/AutomaticoSRFSinot/2014/05/02/2014_05_02_10_37_34_298605328.html"
 * >Receita Federal - Tabela 2015</a>
 * 
 * @author Ricardo Faria
 *
 */
class CalculaImpostoDeRenda {
	
	private CalculaImpostoDeRenda() {
		super();
	}

	public static final float VALOR_POR_DEPENDENTE = 189.59F;
	
	public static final double VALOR_LIMITE_FAIXA1 = 1903.98;
	public static final double VALOR_LIMITE_FAIXA2 = 2826.65;
	public static final double VALOR_LIMITE_FAIXA3 = 3751.05;
	public static final double VALOR_LIMITE_FAIXA4 = 4664.68;

	public static final float VALOR_DEDUCAO_FAIXA2 = 142.80f;
	public static final float VALOR_DEDUCAO_FAIXA3 = 354.80f;
	public static final float VALOR_DEDUCAO_FAIXA4 = 636.13f;
	public static final float VALOR_DEDUCAO_MAXIMO = 869.36f;

	/**
	 * Calcula o IRPF com base na base de c�lculo informada e quantidade
	 * dependentes
	 * 
	 * @param baseCalculo
	 *            formada pelo sal�rio bruto - desconto do INSS
	 * @param qtdDependentes
	 *            registrados no nome do funcion�rio
	 * @return valor a ser pago em IRPF
	 */
	public static float calcular(Float baseCalculo, int qtdDependentes) {
		return calcular(new BigDecimal(baseCalculo.toString()), qtdDependentes)
				.floatValue();
	}

	public static BigDecimal calcular(BigDecimal baseCalculo, int qtdDependentes) {
		BigDecimal valorDesconto;
		BigDecimal valorImposto;
		BigDecimal valorDependentes = BigDecimal
				.valueOf(VALOR_POR_DEPENDENTE * qtdDependentes);

		BigDecimal baseCalculoReduzida = baseCalculo.subtract(valorDependentes);

		if (baseCalculoReduzida.floatValue() <= VALOR_LIMITE_FAIXA1) {
			return BigDecimal.ZERO;
		} else if (baseCalculoReduzida.floatValue() <= VALOR_LIMITE_FAIXA2) {
			valorDesconto = createMonetaryBigDecimal(VALOR_DEDUCAO_FAIXA2);
			valorImposto = baseCalculoReduzida
					.multiply(createMonetaryBigDecimal("0.075"));
		} else if (baseCalculoReduzida.floatValue() <= VALOR_LIMITE_FAIXA3) {
			valorDesconto = createMonetaryBigDecimal(VALOR_DEDUCAO_FAIXA3);
			valorImposto = baseCalculoReduzida
					.multiply(createMonetaryBigDecimal("0.15"));
		} else if (baseCalculoReduzida.floatValue() <= VALOR_LIMITE_FAIXA4) {
			valorDesconto = createMonetaryBigDecimal(VALOR_DEDUCAO_FAIXA4);
			valorImposto = baseCalculoReduzida
					.multiply(createMonetaryBigDecimal("0.225"));
		} else {
			valorDesconto = createMonetaryBigDecimal(VALOR_DEDUCAO_MAXIMO);
			valorImposto = baseCalculoReduzida
					.multiply(createMonetaryBigDecimal("0.275"));
		}
		valorImposto = valorImposto.subtract(valorDesconto);
		if (valorImposto.floatValue() < 0) {
			return BigDecimal.ZERO;
		}

		changeToMonetaryBidecimal(valorImposto);
		return valorImposto;

	}

}