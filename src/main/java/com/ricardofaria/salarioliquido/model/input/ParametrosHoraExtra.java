package com.ricardofaria.salarioliquido.model.input;

import static com.ricardofaria.salarioliquido.util.TempoUtil.hoursToFloat;

import java.math.BigDecimal;
import java.util.Date;

public class ParametrosHoraExtra {

	private static final int QUANTIDADE_PADRAO_HORAS_SEMANAIS = 44;
	private static final int PORCENTAGEM_PADRAO_HORA_EXTRA = 50;
	
	private BigDecimal salarioBruto;
	private float quantidadeHorasSemanais;
	private String tempoDeHoraExtra;
	private float porcentagemAdicionalHoraExtra;
	private Date mesReferencia;
	
	{
		this.mesReferencia = new Date();
		this.porcentagemAdicionalHoraExtra = PORCENTAGEM_PADRAO_HORA_EXTRA;
		this.quantidadeHorasSemanais = QUANTIDADE_PADRAO_HORAS_SEMANAIS;
	}
	
	public ParametrosHoraExtra() {
		super();
	}

	public ParametrosHoraExtra(BigDecimal salarioBruto) {
		super();
		this.salarioBruto = salarioBruto;
	}
	
	public float getQuantidadeHorasSemanais() {
		return quantidadeHorasSemanais;
	}

	public void setQuantidadeHorasSemanais(float quantidadeHorasSemanais) {
		this.quantidadeHorasSemanais = quantidadeHorasSemanais;
	}

	public float getPorcentagemAdicionalHoraExtra() {
		return porcentagemAdicionalHoraExtra;
	}

	public void setPorcentagemAdicionalHoraExtra(float porcentagemAdicionalHoraExtra) {
		this.porcentagemAdicionalHoraExtra = porcentagemAdicionalHoraExtra;
	}

	public Date getMesReferencia() {
		return mesReferencia;
	}

	public void setMesReferencia(Date mesReferencia) {
		this.mesReferencia = mesReferencia;
	}

	public BigDecimal getSalarioBruto() {
		return salarioBruto;
	}

	public void setSalarioBruto(BigDecimal salarioBruto) {
		this.salarioBruto = salarioBruto;
	}

	public String getTempoDeHoraExtra() {
		return tempoDeHoraExtra;
	}
	
	public float getTempoDeHoraExtraParaMultiplicacao() {
		return hoursToFloat(tempoDeHoraExtra);
	}

	public void setTempoDeHoraExtra(String tempoDeHoraExtra) {
		this.tempoDeHoraExtra = tempoDeHoraExtra;
	}

}
