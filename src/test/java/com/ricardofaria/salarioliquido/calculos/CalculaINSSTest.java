package com.ricardofaria.salarioliquido.calculos;

import static com.ricardofaria.salarioliquido.calculos.CalculaINSS.calcular;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CalculaINSSTest {
	
	private static final float VALOR_SALARIO_MINIMO = 788.00f;

	@Test
	public void testDescontoInssSalarioMinimo() {
		float salarioBruto = VALOR_SALARIO_MINIMO;
		float resultadoEsperado = 63.04f;
		
		float resultado = calcular(salarioBruto);
		
		assertEquals(resultadoEsperado, resultado, 0.01f);
	}
	
	@Test
	public void testDescontoInssSalarioLimiteFaixaUm() {
		float salarioBruto = 1399.12f;
		float resultadoEsperado = 111.929596f;
		
		float resultado = calcular(salarioBruto);
		
		assertEquals(resultadoEsperado, resultado, 0.01f);
	}
	
	@Test
	public void testDescontoInssSalarioInicioFaixaDois() {
		float salarioBruto = 1399.13f;
		float resultadoEsperado = 125.92f;
		
		float resultado = calcular(salarioBruto);
		
		assertEquals(resultadoEsperado, resultado, 0.01f);
	}
	
	@Test
	public void testDescontoInssSalarioLimiteFaixaDois() {
		float salarioBruto = 2331.88f;
		float resultadoEsperado = 209.86f;
		
		float resultado = calcular(salarioBruto);
		
		assertEquals(resultadoEsperado, resultado, 0.01f);
	}
	
	@Test
	public void testDescontoInssSalarioInicioFaixaTres() {
		float salarioBruto = 2331.89f;
		float resultadoEsperado = 256.51f;
		
		float resultado = calcular(salarioBruto);
		
		assertEquals(resultadoEsperado, resultado, 0.01f);
	}
	
	@Test
	public void testDescontoInssSalarioLimiteFaixaTres() {
		float salarioBruto = 4663.75f;
		float resultadoEsperado = 513.01f;
		
		float resultado = calcular(salarioBruto);
		
		assertEquals(resultadoEsperado, resultado, 0.01f);
	}
	
	@Test
	public void testDescontoInssSalarioTeto() {
		float salarioBruto = 10000f;
		float resultadoEsperado = 513.01f;
		
		float resultado = calcular(salarioBruto);
		
		assertEquals(resultadoEsperado, resultado, 0.01f);
	}

}
