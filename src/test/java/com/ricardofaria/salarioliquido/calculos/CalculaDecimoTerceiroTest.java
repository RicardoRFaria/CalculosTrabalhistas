package com.ricardofaria.salarioliquido.calculos;

import static com.ricardofaria.salarioliquido.calculos.CalculaDecimoTerceiro.calcularParcelaDois;
import static com.ricardofaria.salarioliquido.calculos.CalculaDecimoTerceiro.calcularParcelaUm;
import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import com.ricardofaria.salarioliquido.model.DecimoTerceiro;

public class CalculaDecimoTerceiroTest {
	
	private static final float VALOR_SALARIO_MINIMO = 788.00f;

	private Calcular calcular;
	
	@Before
	public void init() {
		this.calcular = new Calcular();
	}

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

	@Test
	public void testCalcularDecimoTerceiroCompletoSalarioMinimo() {
		float salarioBruto = VALOR_SALARIO_MINIMO;
		
		DecimoTerceiro decimoTerceiro = calcular.calcularDecimoTerceiro(salarioBruto, 0); 
		
		assertEquals(63.04f, decimoTerceiro.getDescontoInss(), 0.01);
		assertEquals(0.0f, decimoTerceiro.getDescontoIrpf(), 0.01);
		assertEquals(394.00, decimoTerceiro.getSalarioParcelaUm(), 0.01);
		assertEquals(330.96, decimoTerceiro.getSalarioParcelaDois(), 0.01);
	}
	
	@Test
	public void testCalcularDecimoTerceiroCompletoSalario1500() {
		float salarioBruto = 1500.00f;
		int quantidadeDependentes = 0;
		
		DecimoTerceiro decimoTerceiro = calcular.calcularDecimoTerceiro(salarioBruto, quantidadeDependentes); 
		
		assertEquals(135.00f, decimoTerceiro.getDescontoInss(), 0.01);
		assertEquals(0.0f, decimoTerceiro.getDescontoIrpf(), 0.01);
		assertEquals(750.00, decimoTerceiro.getSalarioParcelaUm(), 0.01);
		assertEquals(615.00, decimoTerceiro.getSalarioParcelaDois(), 0.01);
	}
	
	@Test
	public void testCalcularDecimoTerceiroCompletoSalario2000() {
		float salarioBruto = 2000.00f;
		int quantidadeDependentes = 0;
		
		DecimoTerceiro decimoTerceiro = calcular.calcularDecimoTerceiro(salarioBruto, quantidadeDependentes); 
		
		assertEquals(180.00f, decimoTerceiro.getDescontoInss(), 0.01);
		assertEquals(0.0f, decimoTerceiro.getDescontoIrpf(), 0.01);
		assertEquals(1000.00, decimoTerceiro.getSalarioParcelaUm(), 0.01);
		assertEquals(820.00, decimoTerceiro.getSalarioParcelaDois(), 0.01);
	}
	
	@Test
	public void testCalcularDecimoTerceiroCompletoSalario2500() {
		float salarioBruto = 2500.00f;
		int quantidadeDependentes = 0;
		
		DecimoTerceiro decimoTerceiro = calcular.calcularDecimoTerceiro(salarioBruto, quantidadeDependentes); 
		
		assertEquals(275.00f, decimoTerceiro.getDescontoInss(), 0.01);
		assertEquals(24.08f, decimoTerceiro.getDescontoIrpf(), 0.01);
		assertEquals(1250.00, decimoTerceiro.getSalarioParcelaUm(), 0.01);
		assertEquals(950.92, decimoTerceiro.getSalarioParcelaDois(), 0.01);
	}
	
	@Test
	public void testCalcularDecimoTerceiroCompletoSalario3000() {
		float salarioBruto = 3000.00f;
		int quantidadeDependentes = 0;
		
		DecimoTerceiro decimoTerceiro = calcular.calcularDecimoTerceiro(salarioBruto, quantidadeDependentes); 
		
		assertEquals(330.00f, decimoTerceiro.getDescontoInss(), 0.01);
		assertEquals(57.45f, decimoTerceiro.getDescontoIrpf(), 0.01);
		assertEquals(1500.00, decimoTerceiro.getSalarioParcelaUm(), 0.01);
		assertEquals(1112.55, decimoTerceiro.getSalarioParcelaDois(), 0.01);
	}
	
	@Test
	public void testCalcularDecimoTerceiroCompletoSalario3500() {
		float salarioBruto = 3500.00f;
		int quantidadeDependentes = 0;
		
		DecimoTerceiro decimoTerceiro = calcular.calcularDecimoTerceiro(salarioBruto, quantidadeDependentes); 
		
		assertEquals(385.00f, decimoTerceiro.getDescontoInss(), 0.01);
		assertEquals(112.45f, decimoTerceiro.getDescontoIrpf(), 0.01);
		assertEquals(1750.00, decimoTerceiro.getSalarioParcelaUm(), 0.01);
		assertEquals(1252.55, decimoTerceiro.getSalarioParcelaDois(), 0.01);
	}
	
	@Test
	public void testCalcularDecimoTerceiroCompletoSalario4000() {
		float salarioBruto = 4000.00f;
		int quantidadeDependentes = 0;
		
		DecimoTerceiro decimoTerceiro = calcular.calcularDecimoTerceiro(salarioBruto, quantidadeDependentes); 
		
		assertEquals(440.00f, decimoTerceiro.getDescontoInss(), 0.01);
		assertEquals(179.20f, decimoTerceiro.getDescontoIrpf(), 0.01);
		assertEquals(2000.00, decimoTerceiro.getSalarioParcelaUm(), 0.01);
		assertEquals(1380.80, decimoTerceiro.getSalarioParcelaDois(), 0.01);
	}
	
	@Test
	public void testCalcularDecimoTerceiroCompletoSalario4500() {
		float salarioBruto = 4500.00f;
		int quantidadeDependentes = 0;
		
		DecimoTerceiro decimoTerceiro = calcular.calcularDecimoTerceiro(salarioBruto, quantidadeDependentes); 
		
		assertEquals(495.00f, decimoTerceiro.getDescontoInss(), 0.01);
		assertEquals(265.00f, decimoTerceiro.getDescontoIrpf(), 0.01);
		assertEquals(2250.00, decimoTerceiro.getSalarioParcelaUm(), 0.01);
		assertEquals(1490.00, decimoTerceiro.getSalarioParcelaDois(), 0.01);
	}
	
	@Test
	public void testCalcularDecimoTerceiroCompletoSalario5000() {
		float salarioBruto = 5000.00f;
		int quantidadeDependentes = 0;
		
		DecimoTerceiro decimoTerceiro = calcular.calcularDecimoTerceiro(salarioBruto, quantidadeDependentes); 
		
		assertEquals(513.01f, decimoTerceiro.getDescontoInss(), 0.01);
		assertEquals(373.44f, decimoTerceiro.getDescontoIrpf(), 0.01);
		assertEquals(2500.00, decimoTerceiro.getSalarioParcelaUm(), 0.01);
		assertEquals(1613.55, decimoTerceiro.getSalarioParcelaDois(), 0.01);
	}
	
}
