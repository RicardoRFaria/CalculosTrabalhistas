package com.ricardofaria.salarioliquido.model.input;

import java.math.BigDecimal;

import com.ricardofaria.salarioliquido.model.constantes.Insalubridade;
import com.ricardofaria.salarioliquido.util.PrecisionUtil;

public class ParametrosBase {

	private BigDecimal salarioBruto;
	private int numeroDependentes;
	private boolean adicionalDePericulosidade;
	private Insalubridade nivelInsalubridade;

	public ParametrosBase() {
		super();
	}

	public ParametrosBase(float salarioBruto) {
		super();
		setSalarioBruto(salarioBruto);
	}

	public ParametrosBase(BigDecimal salarioBruto) {
		super();
		setSalarioBruto(salarioBruto);
	}

	public BigDecimal getSalarioBruto() {
		return salarioBruto;
	}

	public float getSalarioBrutoFloat() {
		return salarioBruto.floatValue();
	}

	public void setSalarioBruto(BigDecimal salarioBruto) {
		this.salarioBruto = salarioBruto;
	}

	public void setSalarioBruto(float salarioBruto) {
		this.salarioBruto = PrecisionUtil.createMonetaryBigDecimal(salarioBruto);
	}

	public int getNumeroDependentes() {
		return numeroDependentes;
	}

	public void setNumeroDependentes(int numeroDependentes) {
		this.numeroDependentes = numeroDependentes;
	}

	public boolean isAdicionalDePericulosidade() {
		return adicionalDePericulosidade;
	}

	public void setAdicionalDePericulosidade(boolean adicionalDePericulosidade) {
		this.adicionalDePericulosidade = adicionalDePericulosidade;
	}

	/**
	 * Informa se deve incidir adicional de insalubridade ao calculo
	 * 
	 * @return
	 */
	public boolean incideNivelInsalubridade() {
		return nivelInsalubridade != null;
	}

	public Insalubridade getNivelInsalubridade() {
		return nivelInsalubridade;
	}

	public void setNivelInsalubridade(Insalubridade nivelInsalubridade) {
		this.nivelInsalubridade = nivelInsalubridade;
	}

}