package com.ricardofaria.salarioliquido.model.resultado;

import java.math.BigDecimal;

public class Ferias extends Remuneracao {
	
	public enum TIPO_FERIAS {
		COMPLETA, DIAS_20, DIAS_15;
	}
	
	private float abonoPecuniario;
	private float valorFerias;
	private float adiantamentoDecimoTerceiro;
	
	public Ferias() {
		super();
	}
	
	public Ferias(float valorFerias) {
		super(valorFerias);
	}

	public float getAbonoPecuniario() {
		return abonoPecuniario;
	}

	public void setAbonoPecuniario(float abonoPecuniario) {
		this.abonoPecuniario = abonoPecuniario;
	}
	
	public void setAbonoPecuniario(BigDecimal abonoPecuniario) {
		this.abonoPecuniario = abonoPecuniario.floatValue();
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
