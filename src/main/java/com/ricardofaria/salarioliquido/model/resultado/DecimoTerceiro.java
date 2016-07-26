package com.ricardofaria.salarioliquido.model.resultado;

import static com.ricardofaria.salarioliquido.util.PrecisionUtil.changeToMonetaryBidecimal;
import static com.ricardofaria.salarioliquido.util.PrecisionUtil.createMonetaryBigDecimal;

import java.math.BigDecimal;

import com.ricardofaria.salarioliquido.model.constantes.TipoDecimoTerceiro;

public class DecimoTerceiro extends Remuneracao {

	@Deprecated
	public enum TIPO_DECIMO_TERCEIRO {
		COMPLETO, PARCIAL
	}

	private TipoDecimoTerceiro tipo;
	private BigDecimal salarioParcelaUm;
	private BigDecimal salarioParcelaDois;

	public DecimoTerceiro() {
		super();
	}

	public DecimoTerceiro(BigDecimal salarioBruto) {
		super(salarioBruto);
	}

	/**
	 * Metodo deprecated em favor do
	 * {@link DecimoTerceiro#getTipoDecimoTerceiro()}
	 * 
	 * @return
	 */
	@Deprecated
	public TIPO_DECIMO_TERCEIRO getTipo() {
		if (tipo == null) {
			return null;
		}
		return TIPO_DECIMO_TERCEIRO.valueOf(tipo.name());
	}

	/**
	 * Metodo deprecated em favor do
	 * {@link DecimoTerceiro#setTipoDecimoTerceiro(TipoDecimoTerceiro)}
	 * 
	 * @param tipo
	 */
	@Deprecated
	public void setTipo(TIPO_DECIMO_TERCEIRO tipo) {
		if (tipo == null) {
			this.tipo = null;
		} else {
			this.tipo = TipoDecimoTerceiro.valueOf(tipo.name());
		}
	}

	public TipoDecimoTerceiro getTipoDecimoTerceiro() {
		return tipo;
	}

	public void setTipoDecimoTerceiro(TipoDecimoTerceiro tipo) {
		this.tipo = tipo;
	}

	public BigDecimal getSalarioParcelaUm() {
		return salarioParcelaUm;
	}

	public float getSalarioParcelaUmFloat() {
		return salarioParcelaUm.floatValue();
	}

	public void setSalarioParcelaUm(float salarioParcelaUm) {
		this.salarioParcelaUm = createMonetaryBigDecimal(salarioParcelaUm);
	}

	public void setSalarioParcelaUm(BigDecimal salarioParcelaUm) {
		this.salarioParcelaUm = changeToMonetaryBidecimal(salarioParcelaUm);
	}

	public BigDecimal getSalarioParcelaDois() {
		return salarioParcelaDois;
	}

	public float getSalarioParcelaDoisFloat() {
		return salarioParcelaDois.floatValue();
	}

	public void setSalarioParcelaDois(float salarioParcelaDois) {
		this.salarioParcelaDois = createMonetaryBigDecimal(salarioParcelaDois);
	}

	public void setSalarioParcelaDois(BigDecimal salarioParcelaDois) {
		this.salarioParcelaDois = changeToMonetaryBidecimal(salarioParcelaDois);
	}

}
