package com.ricardofaria.salarioliquido.model;

public class Salario {

	private float salarioBruto;
	private float salarioLiquido;
	private float descontoInss;
	private float descontoIrpf;

	public Salario() {
		super();
	}

	public Salario(float salarioBruto) {
		super();
		this.salarioBruto = salarioBruto;
	}

	public float getSalarioBruto() {
		return salarioBruto;
	}

	public void setSalarioBruto(float salarioBruto) {
		this.salarioBruto = salarioBruto;
	}

	public float getDescontoInss() {
		return descontoInss;
	}

	public void setDescontoInss(float descontoInss) {
		this.descontoInss = descontoInss;
	}

	public float getDescontoIrpf() {
		return descontoIrpf;
	}

	public void setDescontoIrpf(float descontoIrpf) {
		this.descontoIrpf = descontoIrpf;
	}

	public float getSalarioLiquido() {
		return salarioLiquido;
	}

	public void setSalarioLiquido(float salarioLiquido) {
		this.salarioLiquido = salarioLiquido;
	}

}
