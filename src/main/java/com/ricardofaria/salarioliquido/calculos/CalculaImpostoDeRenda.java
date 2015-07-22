package com.ricardofaria.salarioliquido.calculos;

import static com.ricardofaria.salarioliquido.util.PrecisionUtil.changeToMonetaryBidecimal;

import java.math.BigDecimal;

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

	public static final BigDecimal VALOR_POR_DEPENDENTE = new BigDecimal("189.59");

	public static final float VALOR_LIMITE_FAIXA1 = 1903.98f;
	public static final float VALOR_LIMITE_FAIXA2 = 2826.65f;
	public static final float VALOR_LIMITE_FAIXA3 = 3751.05f;
	public static final float VALOR_LIMITE_FAIXA4 = 4664.68f;

	public static final BigDecimal VALOR_DEDUCAO_FAIXA2 = new BigDecimal("142.80");
	public static final BigDecimal VALOR_DEDUCAO_FAIXA3 = new BigDecimal("354.80");
	public static final BigDecimal VALOR_DEDUCAO_FAIXA4 = new BigDecimal("636.13");
	public static final BigDecimal VALOR_DEDUCAO_MAXIMO = new BigDecimal("869.36");
	
	private static final BigDecimal MULTIPLICADOR_FAIXA2 = new BigDecimal("0.075");
	private static final BigDecimal MULTIPLICADOR_FAIXA3 = new BigDecimal("0.15");
	private static final BigDecimal MULTIPLICADOR_FAIXA4 = new BigDecimal("0.225");
	private static final BigDecimal MULTIPLICADOR_FAIXA_TETO = new BigDecimal("0.275");

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
		BigDecimal valorDependentes = VALOR_POR_DEPENDENTE.multiply(new BigDecimal(qtdDependentes));

		BigDecimal baseCalculoReduzida = baseCalculo.subtract(valorDependentes);

		float baseCalculoComparacao = baseCalculoReduzida.floatValue();
		if (baseCalculoComparacao <= VALOR_LIMITE_FAIXA1) {
			return BigDecimal.ZERO;
		} else if (baseCalculoComparacao <= VALOR_LIMITE_FAIXA2) {
			valorDesconto = VALOR_DEDUCAO_FAIXA2;
			valorImposto = baseCalculoReduzida.multiply(MULTIPLICADOR_FAIXA2);
		} else if (baseCalculoComparacao <= VALOR_LIMITE_FAIXA3) {
			valorDesconto = VALOR_DEDUCAO_FAIXA3;
			valorImposto = baseCalculoReduzida.multiply(MULTIPLICADOR_FAIXA3);
		} else if (baseCalculoComparacao <= VALOR_LIMITE_FAIXA4) {
			valorDesconto = VALOR_DEDUCAO_FAIXA4;
			valorImposto = baseCalculoReduzida.multiply(MULTIPLICADOR_FAIXA4);
		} else {
			valorDesconto = VALOR_DEDUCAO_MAXIMO;
			valorImposto = baseCalculoReduzida.multiply(MULTIPLICADOR_FAIXA_TETO);
		}
		valorImposto = valorImposto.subtract(valorDesconto);
		if (valorImposto.floatValue() < 0) {
			return BigDecimal.ZERO;
		}

		changeToMonetaryBidecimal(valorImposto);
		return valorImposto;

	}

}
