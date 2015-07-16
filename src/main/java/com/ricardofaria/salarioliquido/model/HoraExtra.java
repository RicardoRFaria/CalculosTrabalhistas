package com.ricardofaria.salarioliquido.model;

import java.math.BigDecimal;

public class HoraExtra {

	private BigDecimal valorPorHora;
	private String tempoDeHoraExtra;
	private BigDecimal valorTotal;
	private BigDecimal valorDSR;
	
	public HoraExtra(BigDecimal valorPorHora, String tempoDeHoraExtra, BigDecimal valorTotal, BigDecimal valorDSR) {
		super();
		this.valorPorHora = valorPorHora;
		this.tempoDeHoraExtra = tempoDeHoraExtra;
		this.valorTotal = valorTotal;
		this.valorDSR = valorDSR;
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

	public BigDecimal getValorDSR() {
		return valorDSR;
	}

}
