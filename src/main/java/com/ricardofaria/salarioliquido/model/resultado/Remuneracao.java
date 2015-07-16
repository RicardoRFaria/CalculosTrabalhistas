package com.ricardofaria.salarioliquido.model.resultado;

import java.math.BigDecimal;

public abstract class Remuneracao {

	private float salarioBruto;
	private float descontoInss;
	private float descontoIrpf;
	
	public Remuneracao() {
		super();
	}
	
	public Remuneracao(float salarioBruto) {
		super();
		setSalarioBruto(salarioBruto);
	}
	
	public Remuneracao(BigDecimal salarioBruto) {
		super();
		setSalarioBruto(salarioBruto);
	}
	
	public float getSalarioBruto() {
		return salarioBruto;
	}

	public void setSalarioBruto(float salarioBruto) {
		this.salarioBruto = salarioBruto;
	}
	
	public void setSalarioBruto(BigDecimal salarioBruto) {
		this.salarioBruto = salarioBruto.floatValue();
	}

	public float getDescontoInss() {
		return descontoInss;
	}

	public void setDescontoInss(float descontoInss) {
		this.descontoInss = descontoInss;
	}
	
	public void setDescontoInss(BigDecimal descontoInss) {
		this.descontoInss = descontoInss.floatValue();	
	}

	public float getDescontoIrpf() {
		return descontoIrpf;
	}

	public void setDescontoIrpf(float descontoIrpf) {
		this.descontoIrpf = descontoIrpf;
	}
	
	public void setDescontoIrpf(BigDecimal descontoIrpf) {
		this.descontoIrpf = descontoIrpf.floatValue();
	}

}
