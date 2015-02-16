package com.ricardofaria.salarioliquido.calculos;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.ricardofaria.salarioliquido.model.Salario;

public class CalcularTest {
	
	private static final float VALOR_SALARIO_MINIMO = 788.00f;

	private Calcular calcular;
	
	@Before
	public void init() {
		this.calcular = new Calcular();
	}
	
	@Test
	public void testCalcularSalarioMinimo() {
		Float salarioBruto = VALOR_SALARIO_MINIMO;
		int quantidadeDependentes = 0;
		
		Salario salario = calcular.calcularSalario(salarioBruto, quantidadeDependentes); 
		
		assertEquals(0.0f, salario.getDescontoInss());
	}

}
