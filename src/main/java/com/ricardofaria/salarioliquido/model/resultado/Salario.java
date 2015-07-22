package com.ricardofaria.salarioliquido.model.resultado;

public class Salario extends Remuneracao {

	private HoraExtra horaExtra;

	public Salario() {
		super();
	}

	public Salario(float salarioBruto) {
		super(salarioBruto);
	}

	public void setHoraExtra(HoraExtra horaExtra) {
		this.horaExtra = horaExtra;
	}
	
	public HoraExtra getHoraExtra() {
		return horaExtra;
	}
	
}
