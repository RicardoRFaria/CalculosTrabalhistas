package com.ricardofaria.salarioliquido.model.input;

public class ParametrosBase {

	private float salarioBruto;
	private int numeroDependentes;

	public ParametrosBase() {
		super();
	}
	
	public ParametrosBase(float salarioBruto) {
		super();
		setSalarioBruto(salarioBruto);
	}
	
	public float getSalarioBruto() {
		return salarioBruto;
	}

	public void setSalarioBruto(float salarioBruto) {
		this.salarioBruto = salarioBruto;
	}

	public int getNumeroDependentes() {
		return numeroDependentes;
	}

	public void setNumeroDependentes(int numeroDependentes) {
		this.numeroDependentes = numeroDependentes;
	}

}