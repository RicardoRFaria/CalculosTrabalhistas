package com.ricardofaria.salarioliquido.calculos;

import static org.junit.Assert.*;

import org.junit.Test;

import static com.ricardofaria.salarioliquido.calculos.CalculaINSS.*;

public class CalculaINSSTest {
	
	private static final float VALOR_SALARIO_MINIMO = 788.00f;

	@Test
	public void testDescontoInssSalarioMinimo() {
		Float salarioBruto = VALOR_SALARIO_MINIMO;
		Float resultadoEsperado = 63.04f;
		
		Float resultado = calcular(salarioBruto);
		
		assertEquals(resultadoEsperado, resultado);
	}
	
	@Test
	public void testDescontoInssSalarioLimiteFaixaUm() {
		Float salarioBruto = 1399.12f;
		Float resultadoEsperado = 111.929596f;
		
		Float resultado = calcular(salarioBruto);
		
		assertEquals(resultadoEsperado, resultado);
	}
	
	@Test
	public void testDescontoInssSalarioInicioFaixaDois() {
		Float salarioBruto = 1399.13f;
		Float resultadoEsperado = 125.9217f;
		
		Float resultado = calcular(salarioBruto);
		
		assertEquals(resultadoEsperado, resultado);
	}
	
	@Test
	public void testDescontoInssSalarioLimiteFaixaDois() {
		Float salarioBruto = 2331.88f;
		Float resultadoEsperado = 209.86919f;
		
		Float resultado = calcular(salarioBruto);
		
		assertEquals(resultadoEsperado, resultado);
	}
	
	@Test
	public void testDescontoInssSalarioInicioFaixaTres() {
		Float salarioBruto = 2331.89f;
		Float resultadoEsperado = 256.50787f;
		
		Float resultado = calcular(salarioBruto);
		
		assertEquals(resultadoEsperado, resultado);
	}
	
	@Test
	public void testDescontoInssSalarioLimiteFaixaTres() {
		Float salarioBruto = 4663.75f;
		Float resultadoEsperado = 513.0125f;
		
		Float resultado = calcular(salarioBruto);
		
		assertEquals(resultadoEsperado, resultado);
	}
	
	@Test
	public void testDescontoInssSalarioTeto() {
		Float salarioBruto = 10000f;
		Float resultadoEsperado = 513.0125f;
		
		Float resultado = calcular(salarioBruto);
		
		assertEquals(resultadoEsperado, resultado);
	}

}
