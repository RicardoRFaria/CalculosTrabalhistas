package com.ricardofaria.salarioliquido.model.input;

import java.math.BigDecimal;

import com.ricardofaria.salarioliquido.util.PrecisionUtil;

public class ParametrosBase {

	private BigDecimal salarioBruto;
	private int numeroDependentes;
	private boolean adicionalDePericulosidade;

	public ParametrosBase() {
		super();
	}
	
	public ParametrosBase(float salarioBruto) {
		super();
		setSalarioBruto(salarioBruto);
	}
	
	public ParametrosBase(BigDecimal salarioBruto) {
		super();
		setSalarioBruto(salarioBruto);
	}
	
	public BigDecimal getSalarioBruto() {
		return salarioBruto;
	}
	
	public float getSalarioBrutoFloat() {
		return salarioBruto.floatValue();
	}

	public void setSalarioBruto(BigDecimal salarioBruto) {
		this.salarioBruto = salarioBruto;
	}
	
	public void setSalarioBruto(float salarioBruto) {
		this.salarioBruto = PrecisionUtil.createMonetaryBigDecimal(salarioBruto);
	}

	public int getNumeroDependentes() {
		return numeroDependentes;
	}

	public void setNumeroDependentes(int numeroDependentes) {
		this.numeroDependentes = numeroDependentes;
	}

	public boolean isAdicionalDePericulosidade() {
		return adicionalDePericulosidade;
	}

	public void setAdicionalDePericulosidade(boolean adicionalDePericulosidade) {
		this.adicionalDePericulosidade = adicionalDePericulosidade;
	}

}