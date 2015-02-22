package com.ricardofaria.salarioliquido.model;

public class Salario extends Remuneracao {

	private float salarioLiquido;

	public Salario() {
		super();
	}

	public Salario(float salarioBruto) {
		super(salarioBruto);
	}

	public float getSalarioLiquido() {
		return salarioLiquido;
	}

	public void setSalarioLiquido(float salarioLiquido) {
		this.salarioLiquido = salarioLiquido;
	}

}
