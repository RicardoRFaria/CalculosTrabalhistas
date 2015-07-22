package com.ricardofaria.salarioliquido.model.resultado;

import static com.ricardofaria.salarioliquido.util.PrecisionUtil.changeToMonetaryBidecimal;
import static com.ricardofaria.salarioliquido.util.PrecisionUtil.createMonetaryBigDecimal;

import java.math.BigDecimal;

public class DecimoTerceiro extends Remuneracao {

	public enum TIPO_DECIMO_TERCEIRO {
		COMPLETO, PARCIAL
	}

	private TIPO_DECIMO_TERCEIRO tipo;
	private BigDecimal salarioParcelaUm;
	private BigDecimal salarioParcelaDois;
	
	public DecimoTerceiro() {
		super();
	}
	
	public DecimoTerceiro(BigDecimal salarioBruto) {
		super(salarioBruto);
	}

	public TIPO_DECIMO_TERCEIRO getTipo() {
		return tipo;
	}

	public void setTipo(TIPO_DECIMO_TERCEIRO tipo) {
		this.tipo = tipo;
	}

	public BigDecimal getSalarioParcelaUm() {
		return salarioParcelaUm;
	}
	
	public float getSalarioParcelaUmFloat() {
		return salarioParcelaUm.floatValue();
	}

	public void setSalarioParcelaUm(float salarioParcelaUm) {
		this.salarioParcelaUm = createMonetaryBigDecimal(salarioParcelaUm);
	}
	
	public void setSalarioParcelaUm(BigDecimal salarioParcelaUm) {
		this.salarioParcelaUm = changeToMonetaryBidecimal(salarioParcelaUm);
	}

	public BigDecimal getSalarioParcelaDois() {
		return salarioParcelaDois;
	}
	
	public float getSalarioParcelaDoisFloat() {
		return salarioParcelaDois.floatValue();
	}

	public void setSalarioParcelaDois(float salarioParcelaDois) {
		this.salarioParcelaDois = createMonetaryBigDecimal(salarioParcelaDois);
	}
	
	public void setSalarioParcelaDois(BigDecimal salarioParcelaDois) {
		this.salarioParcelaDois = changeToMonetaryBidecimal(salarioParcelaDois);
	}

}
