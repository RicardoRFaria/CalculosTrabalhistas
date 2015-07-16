package com.ricardofaria.salarioliquido.model;

import java.math.BigDecimal;

public class HoraExtra {

	private BigDecimal valorPorHora;
	private String tempoDeHoraExtra;
	private BigDecimal valorTotal;
	
	public HoraExtra(BigDecimal valorPorHora, String tempoDeHoraExtra, BigDecimal valorTotal) {
		super();
		this.valorPorHora = valorPorHora;
		this.tempoDeHoraExtra = tempoDeHoraExtra;
		this.valorTotal = valorTotal;
	}

	public BigDecimal getValorPorHora() {
		return valorPorHora;
	}

	public String getTempoDeHoraExtra() {
		return tempoDeHoraExtra;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

}
