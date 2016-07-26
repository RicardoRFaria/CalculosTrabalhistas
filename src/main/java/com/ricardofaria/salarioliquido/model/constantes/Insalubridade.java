package com.ricardofaria.salarioliquido.model.constantes;

import java.math.BigDecimal;

/**
 * Enum utilizado para classificar o nivel de adicional de insalubridade e
 * informar a porcentagem para o calculo
 * 
 * @author Ricardo Faria
 *
 */
public enum Insalubridade {

	MINIMO("0.1"), MEDIO("0.2"), MAXIMO("0.4");

	Insalubridade(String porcentagem) {
		this.adicional = new BigDecimal(porcentagem);
	}

	private BigDecimal adicional;

	public BigDecimal getAdicional() {
		return adicional;
	}

}
