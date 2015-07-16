package com.ricardofaria.salarioliquido.model.input;

import com.ricardofaria.salarioliquido.model.resultado.Ferias.TIPO_FERIAS;

public class ParametrosFerias extends ParametrosBase {
	
	private TIPO_FERIAS tipo;
	
	{
		tipo = TIPO_FERIAS.COMPLETA;
	}
	
	public ParametrosFerias() {
		super();
	}
	
	public ParametrosFerias(float salarioBruto) {
		super();
		this.salarioBruto = salarioBruto;
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
	public TIPO_FERIAS getTipo() {
		return tipo;
	}
	public void setTipo(TIPO_FERIAS tipo) {
		this.tipo = tipo;
	}

}
