package com.ricardofaria.salarioliquido.model.input;

import static com.ricardofaria.salarioliquido.util.TempoUtil.hoursToFloat;

import java.math.BigDecimal;
import java.util.Date;

import com.ricardofaria.salarioliquido.model.resultado.HoraExtra;

/**
 * Parametro para calculo de {@link HoraExtra} sobre o salario do funcionario
 * 
 * @author Ricardo Faria
 *
 */
public class ParametrosHoraExtra {

	public static final int QUANTIDADE_PADRAO_HORAS_SEMANAIS = 44;
	public static final int PORCENTAGEM_PADRAO_HORA_EXTRA = 50;

	private BigDecimal salarioBruto;
	private float quantidadeHorasSemanais;
	private String tempoDeHoraExtra;
	private float porcentagemAdicionalHoraExtra;
	private Date mesReferencia;

	{
		this.mesReferencia = new Date();
		this.porcentagemAdicionalHoraExtra = PORCENTAGEM_PADRAO_HORA_EXTRA;
		this.quantidadeHorasSemanais = QUANTIDADE_PADRAO_HORAS_SEMANAIS;
	}

	/**
	 * Constroi uma instancia de {@link ParametrosHoraExtra} com a porcentagem
	 * adicional de hora extra e a quantidade de horas semanais do funcionarios
	 * nos padroes fornecidos pelas constantes desta classe
	 */
	public ParametrosHoraExtra() {
		super();
	}

	/**
	 * Constroi uma instancia de {@link ParametrosHoraExtra} com a porcentagem
	 * adicional de hora extra e a quantidade de horas semanais do funcionarios
	 * nos padroes fornecidos pelas constantes desta classe e salario bruto
	 * 
	 * @param salarioBruto
	 *            instancia de {@link BigDecimal} que inicializa o salario bruto
	 *            do funcionario
	 */
	public ParametrosHoraExtra(BigDecimal salarioBruto) {
		super();
		this.salarioBruto = salarioBruto;
	}

	public float getQuantidadeHorasSemanais() {
		return quantidadeHorasSemanais;
	}

	public void setQuantidadeHorasSemanais(float quantidadeHorasSemanais) {
		this.quantidadeHorasSemanais = quantidadeHorasSemanais;
	}

	public float getPorcentagemAdicionalHoraExtra() {
		return porcentagemAdicionalHoraExtra;
	}

	public void setPorcentagemAdicionalHoraExtra(float porcentagemAdicionalHoraExtra) {
		this.porcentagemAdicionalHoraExtra = porcentagemAdicionalHoraExtra;
	}

	public Date getMesReferencia() {
		return mesReferencia;
	}

	public void setMesReferencia(Date mesReferencia) {
		this.mesReferencia = mesReferencia;
	}

	public BigDecimal getSalarioBruto() {
		return salarioBruto;
	}

	public void setSalarioBruto(BigDecimal salarioBruto) {
		this.salarioBruto = salarioBruto;
	}

	public String getTempoDeHoraExtra() {
		return tempoDeHoraExtra;
	}

	public float getTempoDeHoraExtraParaMultiplicacao() {
		return hoursToFloat(tempoDeHoraExtra);
	}

	public void setTempoDeHoraExtra(String tempoDeHoraExtra) {
		this.tempoDeHoraExtra = tempoDeHoraExtra;
	}

}
