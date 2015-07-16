package com.ricardofaria.salarioliquido.model.input;

import java.util.Date;

public class ParametrosSalario {
	
	private float salarioBruto;
	private int numeroDependentes;
	private Date dataInicioColaborador;
	
	public ParametrosSalario() {
		super();
	}
	
	public ParametrosSalario(float salarioBruto) {
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

	public Date getDataInicioColaborador() {
		return dataInicioColaborador;
	}

	public void setDataInicioColaborador(Date dataInicioColaborador) {
		this.dataInicioColaborador = dataInicioColaborador;
	}

}
