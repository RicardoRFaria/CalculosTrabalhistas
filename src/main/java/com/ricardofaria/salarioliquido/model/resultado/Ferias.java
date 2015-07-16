package com.ricardofaria.salarioliquido.model.resultado;

import java.math.BigDecimal;

public class Ferias extends Remuneracao {
	
	public enum TIPO_FERIAS {
		COMPLETA, DIAS_20, DIAS_15;
	}
	
	private float abonoPecuniario;
	private float valorFerias;
	private float feriasLiquidas;
	private float adiantamentoDecimoTerceiro;

	public float getAbonoPecuniario() {
		return abonoPecuniario;
	}

	public void setAbonoPecuniario(float abonoPecuniario) {
		this.abonoPecuniario = abonoPecuniario;
	}
	
	public void setAbonoPecuniario(BigDecimal abonoPecuniario) {
		this.abonoPecuniario = abonoPecuniario.floatValue();
	}

	public float getFeriasLiquidas() {
		return feriasLiquidas;
	}

	public void setFeriasLiquidas(float feriasLiquidas) {
		this.feriasLiquidas = feriasLiquidas;
	}
	
	public void setFeriasLiquidas(BigDecimal feriasLiquidas) {
		this.feriasLiquidas = feriasLiquidas.floatValue();
	}

	public float getValorFerias() {
		return valorFerias;
	}

	public void setValorFerias(float valorFerias) {
		this.valorFerias = valorFerias;
	}
	
	public void setValorFerias(BigDecimal valorFerias) {
		this.valorFerias = valorFerias.floatValue();
	}

	public float getAdiantamentoDecimoTerceiro() {
		return adiantamentoDecimoTerceiro;
	}

	public void setAdiantamentoDecimoTerceiro(float adiantamentoDecimoTerceiro) {
		this.adiantamentoDecimoTerceiro = adiantamentoDecimoTerceiro;
	}

}
