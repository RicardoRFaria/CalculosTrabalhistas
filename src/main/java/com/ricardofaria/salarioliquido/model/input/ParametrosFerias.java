package com.ricardofaria.salarioliquido.model.input;

import com.ricardofaria.salarioliquido.model.resultado.Ferias.TIPO_FERIAS;

public class ParametrosFerias extends ParametrosBase {

	private TIPO_FERIAS tipo;
	private boolean adiantarDecimoTerceiro;
	private boolean adicionarAbonoPecuniario;

	{
		tipo = TIPO_FERIAS.COMPLETA;
		setAdicionarAbonoPecuniario(true);
	}

	public ParametrosFerias() {
		super();
	}

	public ParametrosFerias(float salarioBruto) {
		super(salarioBruto);
	}

	public TIPO_FERIAS getTipo() {
		return tipo;
	}
	public void setTipo(TIPO_FERIAS tipo) {
		this.tipo = tipo;
	}

	public boolean isAdiantarDecimoTerceiro() {
		return adiantarDecimoTerceiro;
	}

	public void setAdiantarDecimoTerceiro(boolean adiantarDecimoTerceiro) {
		this.adiantarDecimoTerceiro = adiantarDecimoTerceiro;
	}

	public boolean isAdicionarAbonoPecuniario() {
		return adicionarAbonoPecuniario;
	}

	public void setAdicionarAbonoPecuniario(boolean adicionarAbonoPecuniario) {
		this.adicionarAbonoPecuniario = adicionarAbonoPecuniario;
	}

}
