package com.ricardofaria.salarioliquido.model.input;

import java.math.BigDecimal;

import com.ricardofaria.salarioliquido.model.constantes.Insalubridade;
import com.ricardofaria.salarioliquido.util.PrecisionUtil;

/**
 * Classe que modela a estrutura basica de parametors compartilhado pelos
 * calculos relacionados a CLT
 * 
 * @author Ricardo Faria
 *
 */
public abstract class ParametrosBase {

	private BigDecimal salarioBruto;
	private int numeroDependentes;
	private boolean adicionalDePericulosidade;
	private Insalubridade nivelInsalubridade;

	/**
	 * Constroi um objeto instancia de {@link ParametrosBase} sem nada
	 * preenchido
	 */
	public ParametrosBase() {
		super();
	}

	/**
	 * Constroi um objeto instancia de {@link ParametrosBase} com o salario
	 * bruto informado
	 * 
	 * @param salarioBruto
	 *            float com o salario bruto do funcionario
	 */
	public ParametrosBase(float salarioBruto) {
		super();
		setSalarioBruto(salarioBruto);
	}

	/**
	 * Constroi um objeto instancia de {@link ParametrosBase} com o salario
	 * bruto informado
	 * 
	 * @param salarioBruto
	 *            {@link BigDecimal} com o salario bruto do funcionario
	 */
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