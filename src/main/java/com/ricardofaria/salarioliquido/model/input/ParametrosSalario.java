package com.ricardofaria.salarioliquido.model.input;

import java.util.Date;

public class ParametrosSalario extends ParametrosBase {
	
	private Date dataInicioColaborador;
	
	public ParametrosSalario() {
		super();
	}
	
	public ParametrosSalario(float salarioBruto) {
		super(salarioBruto);
	}

	public Date getDataInicioColaborador() {
		return dataInicioColaborador;
	}

	public void setDataInicioColaborador(Date dataInicioColaborador) {
		this.dataInicioColaborador = dataInicioColaborador;
	}

}
