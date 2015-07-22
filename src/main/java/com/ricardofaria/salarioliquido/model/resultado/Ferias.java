package com.ricardofaria.salarioliquido.model.resultado;

import static com.ricardofaria.salarioliquido.util.PrecisionUtil.*;

import java.math.BigDecimal;

public class Ferias extends Remuneracao {
	
	private static final BigDecimal VALOR_0 = createMonetaryBigDecimal("0");
	
	public enum TIPO_FERIAS {
		COMPLETA, DIAS_20, DIAS_15;
	}
	
	private BigDecimal abonoPecuniario;
	private BigDecimal valorFerias;
	private BigDecimal adiantamentoDecimoTerceiro;
	
	{
		abonoPecuniario = VALOR_0;
		valorFerias = VALOR_0;
		adiantamentoDecimoTerceiro = VALOR_0;
	}
	
	public Ferias() {
		super();
	}
	
	public Ferias(float valorFerias) {
		super(valorFerias);
	}
	
	public Ferias(BigDecimal valorFerias) {
		super(valorFerias);
	}

	public BigDecimal getAbonoPecuniario() {
		return abonoPecuniario;
	}
	
	public float getAbonoPecuniarioFloat() {
		return abonoPecuniario.floatValue();
	}

	public void setAbonoPecuniario(float abonoPecuniario) {
		this.abonoPecuniario = createMonetaryBigDecimal(abonoPecuniario);
	}
	
	public void setAbonoPecuniario(BigDecimal abonoPecuniario) {
		this.abonoPecuniario = changeToMonetaryBidecimal(abonoPecuniario);
	}

	public BigDecimal getValorFerias() {
		return valorFerias;
	}
	
	public float getValorFeriasFloat() {
		return valorFerias.floatValue();
	}

	public void setValorFerias(float valorFerias) {
		this.valorFerias = createMonetaryBigDecimal(valorFerias);
	}
	
	public void setValorFerias(BigDecimal valorFerias) {
		this.valorFerias = changeToMonetaryBidecimal(valorFerias);
	}

	public BigDecimal getAdiantamentoDecimoTerceiro() {
		return adiantamentoDecimoTerceiro;
	}
	
	public float getAdiantamentoDecimoTerceiroFloat() {
		return adiantamentoDecimoTerceiro.floatValue();
	}

	public void setAdiantamentoDecimoTerceiro(float adiantamentoDecimoTerceiro) {
		this.adiantamentoDecimoTerceiro = createMonetaryBigDecimal(adiantamentoDecimoTerceiro);
	}
	
	public void setAdiantamentoDecimoTerceiro(BigDecimal adiantamentoDecimoTerceiro) {
		this.adiantamentoDecimoTerceiro = changeToMonetaryBidecimal(adiantamentoDecimoTerceiro);
	}

}
