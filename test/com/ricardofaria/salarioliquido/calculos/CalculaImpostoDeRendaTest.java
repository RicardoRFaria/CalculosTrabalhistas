package com.ricardofaria.salarioliquido.calculos;

import static org.junit.Assert.*;

import org.junit.Test;

import static com.ricardofaria.salarioliquido.calculos.CalculaImpostoDeRenda.*;

public class CalculaImpostoDeRendaTest {

	private static final float VALOR_SALARIO_MINIMO = 788.00f;
	
	@Test
	public void testSalarioMinimo() {
		Float resultadoEsperado = 0.0f;
		Float resultado = calcular(VALOR_SALARIO_MINIMO, 0);
		assertEquals(resultadoEsperado, resultado);
	}
	
	@Test
	public void testSalarioMinimoComDependentes() {
		Float resultadoEsperado = 0.0f;
		Float resultado = calcular(VALOR_SALARIO_MINIMO, 2);
		assertEquals(resultadoEsperado, resultado);
	}
	
	@Test
	public void testCalcularSalarioSegundaFaixa() {
		Float salario = 2250.0f;
		Float resultadoEsperado = 28.630005f;
		int quantidadeDependentes = 0;
		
		Float resultado = calcular(salario, quantidadeDependentes);
		assertEquals(resultadoEsperado, resultado);
	}
	
	@Test
	public void testCalcularSalarioSegundaFaixaComDependentes() {
		Float salario = 2300.0f;
		Float resultadoEsperado = 18.294998f;
		int quantidadeDependentes = 1;
		
		Float resultado = calcular(salario, quantidadeDependentes);
		assertEquals(resultadoEsperado, resultado);
	}
	
	@Test
	public void testCalcularSalarioTerceiraFaixa() {
		Float salario = 3200.0f;
		Float resultadoEsperado = 129.89001f;
		int quantidadeDependentes = 0;
		
		Float resultado = calcular(salario, quantidadeDependentes);
		assertEquals(resultadoEsperado, resultado);
	}
	
	@Test
	public void testCalcularSalarioTerceiraFaixaComDependentes() {
		Float salario = 3200.0f;
		Float resultadoEsperado = 101.72f;
		int quantidadeDependentes = 1;
		
		Float resultado = calcular(salario, quantidadeDependentes);
		assertEquals(resultadoEsperado, resultado);
	}
	
	@Test
	public void testCalcularSalarioTerceiraFaixaCom2Dependentes() {
		Float salario = 3200.0f;
		Float resultadoEsperado = 73.54999f;
		int quantidadeDependentes = 2;
		
		Float resultado = calcular(salario, quantidadeDependentes);
		assertEquals(resultadoEsperado, resultado);
	}
	
	@Test
	public void testCalcularSalarioQuartaFaixa() {
		Float salario = 4500.0f;
		Float resultadoEsperado = 382.40002f;
		int quantidadeDependentes = 0;
		
		Float resultado = calcular(salario, quantidadeDependentes);
		assertEquals(resultadoEsperado, resultado);
	}
	
	@Test
	public void testCalcularSalarioQuartaFaixaComDependente() {
		Float salario = 4500.0f;
		Float resultadoEsperado = 340.14508f;
		int quantidadeDependentes = 1;
		
		Float resultado = calcular(salario, quantidadeDependentes);
		assertEquals(resultadoEsperado, resultado);
	}
	
	@Test
	public void testCalcularSalarioQuartaFaixaCom2Dependentes() {
		Float salario = 4500.0f;
		Float resultadoEsperado = 297.89f;
		int quantidadeDependentes = 2;
		
		Float resultado = calcular(salario, quantidadeDependentes);
		assertEquals(resultadoEsperado, resultado);
	}
	
	@Test
	public void testCalcularSalarioFaixaMaxima() {
		Float salario = 10000.0f;
		Float resultadoEsperado = 1886.6699f;
		int quantidadeDependentes = 0;
		
		Float resultado = calcular(salario, quantidadeDependentes);
		assertEquals(resultadoEsperado, resultado);
	}
	
	@Test
	public void testCalcularSalarioFaixaMaximaComDependente() {
		Float salario = 10000.0f;
		Float resultadoEsperado = 1835.0249f;
		int quantidadeDependentes = 1;
		
		Float resultado = calcular(salario, quantidadeDependentes);
		assertEquals(resultadoEsperado, resultado);
	}
	
	@Test
	public void testCalcularSalarioFaixaMaximaCom10Dependentes() {
		Float salario = 10000.0f;
		Float resultadoEsperado = 1370.22f;
		int quantidadeDependentes = 10;
		
		Float resultado = calcular(salario, quantidadeDependentes);
		assertEquals(resultadoEsperado, resultado);
	}
	

}
