package com.ricardofaria.salarioliquido.model.input;

public class ParametrosDecimoTerceiro extends ParametrosBase {

	private int diaDeInicioFuncionar;
	private int mesDeInicioFuncionario;
	
	public ParametrosDecimoTerceiro() {
		super();
	}
	
	public ParametrosDecimoTerceiro(float salarioBruto) {
		super(salarioBruto);
	}

	public int getDiaDeInicioFuncionar() {
		return diaDeInicioFuncionar;
	}

	public void setDiaDeInicioFuncionar(int diaDeInicioFuncionar) {
		this.diaDeInicioFuncionar = diaDeInicioFuncionar;
	}

	public int getMesDeInicioFuncionario() {
		return mesDeInicioFuncionario;
	}

	public void setMesDeInicioFuncionario(int mesDeInicioFuncionario) {
		this.mesDeInicioFuncionario = mesDeInicioFuncionario;
	}
	
	public boolean isSalarioReduzido() {
		return (diaDeInicioFuncionar > 0 && mesDeInicioFuncionario > 0);
	}

}
