package com.ricardofaria.salarioliquido.model.resultado;

public class Salario extends Remuneracao {

	private float salarioLiquido;
	private HoraExtra horaExtra;

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

	public void setHoraExtra(HoraExtra horaExtra) {
		this.horaExtra = horaExtra;
	}
	
	public HoraExtra getHoraExtra() {
		return horaExtra;
	}
	
}
