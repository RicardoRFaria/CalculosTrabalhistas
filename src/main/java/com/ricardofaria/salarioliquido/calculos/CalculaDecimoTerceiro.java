package com.ricardofaria.salarioliquido.calculos;

import java.math.BigDecimal;

public class CalculaDecimoTerceiro {

	public static float calcularParcelaUm(BigDecimal salarioBruto) {
		return salarioBruto.divide(new BigDecimal("2")).floatValue();
	}

	public static float calcularParcelaDois(BigDecimal salarioBruto,
			BigDecimal descontoInss, BigDecimal descontoImpostoDeRenda) {
		BigDecimal parcelaDois = salarioBruto.divide(new BigDecimal("2"))
				.subtract(descontoInss).subtract(descontoImpostoDeRenda);
		return parcelaDois.floatValue();
	}

}
