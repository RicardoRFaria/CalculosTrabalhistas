package com.ricardofaria.salarioliquido.calculos;

import static com.ricardofaria.salarioliquido.util.PrecisionUtil.changeToMonetaryBidecimal;

import java.math.BigDecimal;

public class CalculaDecimoTerceiro {

	private static final BigDecimal METADE = new BigDecimal("2");

	private CalculaDecimoTerceiro() {
		super();
	}

	public static BigDecimal calcularParcelaUm(BigDecimal salarioBruto) {
		return changeToMonetaryBidecimal(salarioBruto.divide(METADE));
	}

	public static BigDecimal calcularParcelaDois(BigDecimal salarioBruto,
			BigDecimal descontoInss, BigDecimal descontoImpostoDeRenda) {
		BigDecimal parcelaDois = salarioBruto.divide(METADE)
				.subtract(descontoInss).subtract(descontoImpostoDeRenda);
		return changeToMonetaryBidecimal(parcelaDois);
	}

}
