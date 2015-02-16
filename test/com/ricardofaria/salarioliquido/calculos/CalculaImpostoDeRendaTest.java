package com.ricardofaria.salarioliquido.calculos;

import static org.junit.Assert.*;

import org.junit.Test;

import static com.ricardofaria.salarioliquido.calculos.CalculaImpostoDeRenda.*;

public class CalculaImpostoDeRendaTest {

	private static final float VALOR_SALARIO_MINIMO = 788.00f;
	
	@Test
	public void testSalarioMinimo() {
		float resultadoEsperado = 0.0f;
		float resultado = calcular(VALOR_SALARIO_MINIMO, 0);
		assertEquals(resultadoEsperado, resultado, 0.01);
	}
	
	@Test
	public void testSalarioMinimoComDependentes() {
		float resultadoEsperado = 0.0f;
		float resultado = calcular(VALOR_SALARIO_MINIMO, 2);
		assertEquals(resultadoEsperado, resultado, 0.01);
	}
	
	@Test
	public void testCalcularSalarioSegundaFaixa() {
		float salario = 2250.0f;
		float resultadoEsperado = 28.63f;
		int quantidadeDependentes = 0;
		
		float resultado = calcular(salario, quantidadeDependentes);
		assertEquals(resultadoEsperado, resultado, 0.01);
	}
	
	@Test
	public void testCalcularSalarioSegundaFaixaComDependentes() {
		float salario = 2300.0f;
		float resultadoEsperado = 18.29f;
		int quantidadeDependentes = 1;
		
		float resultado = calcular(salario, quantidadeDependentes);
		assertEquals(resultadoEsperado, resultado, 0.01);
	}
	
	@Test
	public void testCalcularSalarioTerceiraFaixa() {
		float salario = 3200.0f;
		float resultadoEsperado = 129.89f;
		int quantidadeDependentes = 0;
		
		float resultado = calcular(salario, quantidadeDependentes);
		assertEquals(resultadoEsperado, resultado, 0.01);
	}
	
	@Test
	public void testCalcularSalarioTerceiraFaixaComDependentes() {
		float salario = 3200.0f;
		float resultadoEsperado = 101.72f;
		int quantidadeDependentes = 1;
		
		float resultado = calcular(salario, quantidadeDependentes);
		assertEquals(resultadoEsperado, resultado, 0.01);
	}
	
	@Test
	public void testCalcularSalarioTerceiraFaixaCom2Dependentes() {
		float salario = 3200.0f;
		float resultadoEsperado = 73.54f;
		int quantidadeDependentes = 2;
		
		float resultado = calcular(salario, quantidadeDependentes);
		assertEquals(resultadoEsperado, resultado, 0.01);
	}
	
	@Test
	public void testCalcularSalarioQuartaFaixa() {
		float salario = 4500.0f;
		float resultadoEsperado = 382.40f;
		int quantidadeDependentes = 0;
		
		float resultado = calcular(salario, quantidadeDependentes);
		assertEquals(resultadoEsperado, resultado, 0.01);
	}
	
	@Test
	public void testCalcularSalarioQuartaFaixaComDependente() {
		float salario = 4500.0f;
		float resultadoEsperado = 340.14f;
		int quantidadeDependentes = 1;
		
		float resultado = calcular(salario, quantidadeDependentes);
		assertEquals(resultadoEsperado, resultado, 0.01);
	}
	
	@Test
	public void testCalcularSalarioQuartaFaixaCom2Dependentes() {
		float salario = 4500.0f;
		float resultadoEsperado = 297.89f;
		int quantidadeDependentes = 2;
		
		float resultado = calcular(salario, quantidadeDependentes);
		assertEquals(resultadoEsperado, resultado, 0.01);
	}
	
	@Test
	public void testCalcularSalarioFaixaMaxima() {
		float salario = 10000.0f;
		float resultadoEsperado = 1886.66f;
		int quantidadeDependentes = 0;
		
		float resultado = calcular(salario, quantidadeDependentes);
		assertEquals(resultadoEsperado, resultado, 0.01);
	}
	
	@Test
	public void testCalcularSalarioFaixaMaximaComDependente() {
		float salario = 10000.0f;
		float resultadoEsperado = 1835.02f;
		int quantidadeDependentes = 1;
		
		float resultado = calcular(salario, quantidadeDependentes);
		assertEquals(resultadoEsperado, resultado, 0.01);
	}
	
	@Test
	public void testCalcularSalarioFaixaMaximaCom10Dependentes() {
		float salario = 10000.0f;
		float resultadoEsperado = 1370.22f;
		int quantidadeDependentes = 10;
		
		float resultado = calcular(salario, quantidadeDependentes);
		assertEquals(resultadoEsperado, resultado, 0.01);
	}
	

}
