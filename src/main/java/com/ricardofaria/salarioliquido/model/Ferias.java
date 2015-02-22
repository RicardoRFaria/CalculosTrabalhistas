package com.ricardofaria.salarioliquido.model;

public class Ferias extends Remuneracao {
	
	public enum TIPO_FERIAS {
		COMPLETA, DIAS_20;
	}
	
	private float abonoPecuniario;
	private float valorFerias;
	private float feriasLiquidas;

	public float getAbonoPecuniario() {
		return abonoPecuniario;
	}

	public void setAbonoPecuniario(float abonoPecuniario) {
		this.abonoPecuniario = abonoPecuniario;
	}

	public float getFeriasLiquidas() {
		return feriasLiquidas;
	}

	public void setFeriasLiquidas(float feriasLiquidas) {
		this.feriasLiquidas = feriasLiquidas;
	}

	public float getValorFerias() {
		return valorFerias;
	}

	public void setValorFerias(float valorFerias) {
		this.valorFerias = valorFerias;
	}

}
