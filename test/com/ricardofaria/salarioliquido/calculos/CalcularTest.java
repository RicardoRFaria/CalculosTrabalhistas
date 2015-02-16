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
		
		Salario salario = calcular.calcularSalario(salarioBruto); 
		
		assertEquals(63.04f, salario.getDescontoInss(), 0.01);
		assertEquals(0.0f, salario.getDescontoIrpf(), 0.01);
		assertEquals(724.96f, salario.getSalarioLiquido(), 0.01);
	}
	
	@Test
	public void testCalcularSalario1500() {
		Float salarioBruto = 1500.00f;
		int quantidadeDependentes = 0;
		
		Salario salario = calcular.calcularSalario(salarioBruto, quantidadeDependentes); 
		
		assertEquals(135.00f, salario.getDescontoInss(), 0.01);
		assertEquals(0.0f, salario.getDescontoIrpf(), 0.01);
		assertEquals(1365.00f, salario.getSalarioLiquido(), 0.01);
	}
	
	@Test
	public void testCalcularSalario2000() {
		Float salarioBruto = 2000.00f;
		int quantidadeDependentes = 0;
		
		Salario salario = calcular.calcularSalario(salarioBruto, quantidadeDependentes); 
		
		assertEquals(180.00f, salario.getDescontoInss(), 0.01);
		assertEquals(0.0f, salario.getDescontoIrpf(), 0.01);
		assertEquals(1820.0f, salario.getSalarioLiquido(), 0.01);
	}
	
	@Test
	public void testCalcularSalario2500() {
		Float salarioBruto = 2500.00f;
		int quantidadeDependentes = 0;
		
		Salario salario = calcular.calcularSalario(salarioBruto, quantidadeDependentes); 
		
		assertEquals(275.00f, salario.getDescontoInss(), 0.01);
		assertEquals(26.75f, salario.getDescontoIrpf(), 0.01);
		assertEquals(2198.24f, salario.getSalarioLiquido(), 0.01);
	}
	
	@Test
	public void testCalcularSalario3000() {
		Float salarioBruto = 3000.00f;
		int quantidadeDependentes = 0;
		
		Salario salario = calcular.calcularSalario(salarioBruto, quantidadeDependentes); 
		
		assertEquals(330.00f, salario.getDescontoInss(), 0.01);
		assertEquals(60.13f, salario.getDescontoIrpf(), 0.01);
		assertEquals(2609.87f, salario.getSalarioLiquido(), 0.01);
	}
	
	@Test
	public void testCalcularSalario3500() {
		Float salarioBruto = 3500.00f;
		int quantidadeDependentes = 0;
		
		Salario salario = calcular.calcularSalario(salarioBruto, quantidadeDependentes); 
		
		assertEquals(385.00f, salario.getDescontoInss(), 0.01);
		assertEquals(117.14f, salario.getDescontoIrpf(), 0.01);
		assertEquals(2997.85f, salario.getSalarioLiquido(), 0.01);
	}
	
	@Test
	public void testCalcularSalario4000() {
		Float salarioBruto = 4000.00f;
		int quantidadeDependentes = 0;
		
		Salario salario = calcular.calcularSalario(salarioBruto, quantidadeDependentes); 
		
		assertEquals(440.00f, salario.getDescontoInss(), 0.01);
		assertEquals(183.89f, salario.getDescontoIrpf(), 0.01);
		assertEquals(3376.10f, salario.getSalarioLiquido(), 0.01);
	}
	
	@Test
	public void testCalcularSalario4500() {
		Float salarioBruto = 4500.00f;
		int quantidadeDependentes = 0;
		
		Salario salario = calcular.calcularSalario(salarioBruto, quantidadeDependentes); 
		
		assertEquals(495.00f, salario.getDescontoInss(), 0.01);
		assertEquals(271.02f, salario.getDescontoIrpf(), 0.01);
		assertEquals(3733.97f, salario.getSalarioLiquido(), 0.01);
	}
	
	@Test
	public void testCalcularSalario5000() {
		Float salarioBruto = 5000.00f;
		int quantidadeDependentes = 0;
		
		Salario salario = calcular.calcularSalario(salarioBruto, quantidadeDependentes); 
		
		assertEquals(513.01f, salario.getDescontoInss(), 0.01);
		assertEquals(379.47f, salario.getDescontoIrpf(), 0.01);
		assertEquals(4107.51f, salario.getSalarioLiquido(), 0.01);
	}


}
