package com.ricardofaria.salarioliquido.model.input;

public class ParametrosBase {

	private float salarioBruto;
	private int numeroDependentes;
	private boolean adicionalDePericulosidade;

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

	public boolean isAdicionalDePericulosidade() {
		return adicionalDePericulosidade;
	}

	public void setAdicionalDePericulosidade(boolean adicionalDePericulosidade) {
		this.adicionalDePericulosidade = adicionalDePericulosidade;
	}

}