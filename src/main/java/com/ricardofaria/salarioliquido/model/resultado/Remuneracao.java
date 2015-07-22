package com.ricardofaria.salarioliquido.model.resultado;

import java.math.BigDecimal;

import static com.ricardofaria.salarioliquido.util.PrecisionUtil.*;

public abstract class Remuneracao {

	private BigDecimal salarioBruto;
	private BigDecimal descontoInss;
	private BigDecimal descontoIrpf;
	private BigDecimal adicionalPericulosidade;
	private BigDecimal valorLiquido;
	
	public Remuneracao() {
		super();
	}
	
	public Remuneracao(float salarioBruto) {
		super();
		setSalarioBruto(salarioBruto);
	}
	
	public Remuneracao(BigDecimal salarioBruto) {
		super();
		setSalarioBruto(salarioBruto);
	}
	
	public BigDecimal getSalarioBruto() {
		return salarioBruto;
	}
	
	public float getSalarioBrutoFloat() {
		return salarioBruto.floatValue();
	}

	public void setSalarioBruto(float salarioBruto) {
		this.salarioBruto = createMonetaryBigDecimal(salarioBruto);
	}
	
	public void setSalarioBruto(BigDecimal salarioBruto) {
		this.salarioBruto = changeToMonetaryBidecimal(salarioBruto);
	}

	public float getDescontoInssFloat() {
		return descontoInss.floatValue();
	}
	
	public BigDecimal getDescontoInss() {
		return descontoInss;
	}

	public void setDescontoInss(float descontoInss) {
		this.descontoInss = createMonetaryBigDecimal(descontoInss);
	}
	
	public void setDescontoInss(BigDecimal descontoInss) {
		this.descontoInss = changeToMonetaryBidecimal(descontoInss);	
	}

	public float getDescontoIrpfFloat() {
		return descontoIrpf.floatValue();
	}
	
	public BigDecimal getDescontoIrpf() {
		return descontoIrpf;
	}

	public void setDescontoIrpf(float descontoIrpf) {
		this.descontoIrpf = createMonetaryBigDecimal(descontoIrpf);
	}
	
	public void setDescontoIrpf(BigDecimal descontoIrpf) {
		this.descontoIrpf = changeToMonetaryBidecimal(descontoIrpf);
	}

	public float getAdicionalPericulosidadeFloat() {
		return adicionalPericulosidade.floatValue();
	}
	
	public BigDecimal getAdicionalPericulosidade() {
		return adicionalPericulosidade;
	}

	public void setAdicionalPericulosidade(float adicionalPericulosidade) {
		this.adicionalPericulosidade = createMonetaryBigDecimal(adicionalPericulosidade);
	}
	
	public void setAdicionalPericulosidade(BigDecimal adicionalPericulosidade) {
		this.adicionalPericulosidade = changeToMonetaryBidecimal(adicionalPericulosidade);
	}

	public float getValorLiquidoFloat() {
		return valorLiquido.floatValue();
	}
	
	public BigDecimal getValorLiquido() {
		return valorLiquido;
	}

	public void setValorLiquido(float valorLiquido) {
		this.valorLiquido = createMonetaryBigDecimal(valorLiquido);
	}
	
	public void setValorLiquido(BigDecimal valorLiquido) {
		this.valorLiquido = changeToMonetaryBidecimal(valorLiquido);
	}

}
