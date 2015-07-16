package com.ricardofaria.salarioliquido.model.resultado;

import java.math.BigDecimal;

public class DecimoTerceiro extends Remuneracao {

	public enum TIPO_DECIMO_TERCEIRO {
		COMPLETO, PARCIAL
	}

	private TIPO_DECIMO_TERCEIRO tipo;
	private float salarioParcelaUm;
	private float salarioParcelaDois;
	
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

	public float getSalarioParcelaUm() {
		return salarioParcelaUm;
	}

	public void setSalarioParcelaUm(float salarioParcelaUm) {
		this.salarioParcelaUm = salarioParcelaUm;
	}

	public float getSalarioParcelaDois() {
		return salarioParcelaDois;
	}

	public void setSalarioParcelaDois(float salarioParcelaDois) {
		this.salarioParcelaDois = salarioParcelaDois;
	}

}
