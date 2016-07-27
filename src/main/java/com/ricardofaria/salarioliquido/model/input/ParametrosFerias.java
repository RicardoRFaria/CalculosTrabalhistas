package com.ricardofaria.salarioliquido.model.input;

import com.ricardofaria.salarioliquido.model.constantes.TipoFerias;
import com.ricardofaria.salarioliquido.model.resultado.Ferias;
import com.ricardofaria.salarioliquido.model.resultado.Ferias.TIPO_FERIAS;

/**
 * Parametros que extendem os {@link ParametrosBase} para fornecer informacoes
 * relevantes ao calculo de {@link Ferias} do funcionario
 * 
 * @author Ricardo Faria
 *
 */
public class ParametrosFerias extends ParametrosBase {

	private TipoFerias tipo;
	private boolean adiantarDecimoTerceiro;
	private boolean adicionarAbonoPecuniario;

	{
		tipo = TipoFerias.COMPLETA;
		setAdicionarAbonoPecuniario(true);
	}

	public ParametrosFerias() {
		super();
	}

	public ParametrosFerias(float salarioBruto) {
		super(salarioBruto);
	}

	/**
	 * Marcado como deprecated em favor do metodo
	 * {@link ParametrosFerias#getTipoFerias()}
	 * 
	 * @return
	 */
	@Deprecated
	public TIPO_FERIAS getTipo() {
		if (tipo == null) {
			return null;
		}
		return TIPO_FERIAS.valueOf(tipo.name());
	}

	/**
	 * Marcado como deprecated em favor do metodo
	 * {@link ParametrosFerias#setTipoFerias(TipoFerias)}
	 */
	@Deprecated
	public void setTipo(TIPO_FERIAS tipo) {
		if (tipo == null) {
			this.tipo = null;
		}
		this.tipo = TipoFerias.valueOf(tipo.name());
	}

	public TipoFerias getTipoFerias() {
		return tipo;
	}

	public void setTipoFerias(TipoFerias tipo) {
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
