package com.ricardofaria.salarioliquido.calculos;

import static com.ricardofaria.salarioliquido.calculos.CalculaImpostoDeRenda.calcular;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

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
		float resultadoEsperado = 25.95f;
		int quantidadeDependentes = 0;
		
		float resultado = calcular(salario, quantidadeDependentes);
		assertEquals(resultadoEsperado, resultado, 0.01);
	}
	
	@Test
	public void testCalcularSalarioSegundaFaixaComDependentes() {
		float salario = 2300.0f;
		float resultadoEsperado = 15.48f;
		int quantidadeDependentes = 1;
		
		float resultado = calcular(salario, quantidadeDependentes);
		assertEquals(resultadoEsperado, resultado, 0.01);
	}
	
	@Test
	public void testCalcularSalarioTerceiraFaixa() {
		float salario = 3200.0f;
		float resultadoEsperado = 125.20f;
		int quantidadeDependentes = 0;
		
		float resultado = calcular(salario, quantidadeDependentes);
		assertEquals(resultadoEsperado, resultado, 0.01);
	}
	
	@Test
	public void testCalcularSalarioTerceiraFaixaComDependentes() {
		float salario = 3200.0f;
		float resultadoEsperado = 96.76f;
		int quantidadeDependentes = 1;
		
		float resultado = calcular(salario, quantidadeDependentes);
		assertEquals(resultadoEsperado, resultado, 0.01);
	}
	
	@Test
	public void testCalcularSalarioTerceiraFaixaCom2Dependentes() {
		float salario = 3200.0f;
		float resultadoEsperado = 68.76f;
		int quantidadeDependentes = 2;
		
		float resultado = calcular(salario, quantidadeDependentes);
		assertEquals(resultadoEsperado, resultado, 0.01);
	}
	
	@Test
	public void testCalcularSalarioQuartaFaixa() {
		float salario = 4500.0f;
		float resultadoEsperado = 376.37f;
		int quantidadeDependentes = 0;
		
		float resultado = calcular(salario, quantidadeDependentes);
		assertEquals(resultadoEsperado, resultado, 0.01);
	}
	
	@Test
	public void testCalcularSalarioQuartaFaixaComDependente() {
		float salario = 4500.0f;
		float resultadoEsperado = 333.71f;
		int quantidadeDependentes = 1;
		
		float resultado = calcular(salario, quantidadeDependentes);
		assertEquals(resultadoEsperado, resultado, 0.01);
	}
	
	@Test
	public void testCalcularSalarioQuartaFaixaCom2Dependentes() {
		float salario = 4500.0f;
		float resultadoEsperado = 291.05f;
		int quantidadeDependentes = 2;
		
		float resultado = calcular(salario, quantidadeDependentes);
		assertEquals(resultadoEsperado, resultado, 0.01);
	}
	
	@Test
	public void testCalcularSalarioFaixaMaxima() {
		float salario = 10000.0f;
		float resultadoEsperado = 1880.64f;
		int quantidadeDependentes = 0;
		
		float resultado = calcular(salario, quantidadeDependentes);
		assertEquals(resultadoEsperado, resultado, 0.01);
	}
	
	@Test
	public void testCalcularSalarioFaixaMaximaComDependente() {
		float salario = 10000.0f;
		float resultadoEsperado = 1828.50f;
		int quantidadeDependentes = 1;
		
		float resultado = calcular(salario, quantidadeDependentes);
		assertEquals(resultadoEsperado, resultado, 0.01);
	}
	
	@Test
	public void testCalcularSalarioFaixaMaximaCom10Dependentes() {
		float salario = 10000.0f;
		float resultadoEsperado = 1359.26f;
		int quantidadeDependentes = 10;
		
		float resultado = calcular(salario, quantidadeDependentes);
		assertEquals(resultadoEsperado, resultado, 0.01);
	}
	

}
