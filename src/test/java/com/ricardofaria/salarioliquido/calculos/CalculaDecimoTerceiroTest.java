package com.ricardofaria.salarioliquido.calculos;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Test;

import static com.ricardofaria.salarioliquido.calculos.CalculaDecimoTerceiro.*;

public class CalculaDecimoTerceiroTest {

	@Test
	public void testParcelaUmValorSimples() {
		BigDecimal salarioBruto = new BigDecimal("2000");
		float parcelaUm = calcularParcelaUm(salarioBruto);
		
		assertEquals(1000f, parcelaUm, 0.01);
	}
	
	@Test
	public void testParcelaUmValorQueNecessitaArredondamento() {
		BigDecimal salarioBruto = new BigDecimal("1375.15");
		float parcelaUm = calcularParcelaUm(salarioBruto);
		
		assertEquals(687.58f, parcelaUm, 0.01);
	}
	
	@Test
	public void testParcelaDoisValorSimples() {
		BigDecimal salarioBruto = new BigDecimal("2000");
		BigDecimal descontoINSS = new BigDecimal("100");
		BigDecimal descontoIRPF = new BigDecimal("100");
		float parcelaUm = calcularParcelaDois(salarioBruto, descontoINSS, descontoIRPF);
		
		assertEquals(800f, parcelaUm, 0.01);
	}
	
	@Test
	public void testParcelaDoisValorSemDesconto() {
		BigDecimal salarioBruto = new BigDecimal("2000");
		BigDecimal descontoINSS = new BigDecimal("0");
		BigDecimal descontoIRPF = new BigDecimal("0");
		float parcelaUm = calcularParcelaDois(salarioBruto, descontoINSS, descontoIRPF);
		
		assertEquals(1000f, parcelaUm, 0.01);
	}

}
