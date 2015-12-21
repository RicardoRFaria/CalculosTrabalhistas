package com.ricardofaria.salarioliquido.calculos;

import static com.ricardofaria.salarioliquido.calculos.CalculaDecimoTerceiro.calcularParcelaDois;
import static com.ricardofaria.salarioliquido.calculos.CalculaDecimoTerceiro.calcularParcelaUm;
import static com.ricardofaria.salarioliquido.util.PrecisionUtil.createMonetaryBigDecimal;
import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import com.ricardofaria.salarioliquido.model.input.ParametrosDecimoTerceiro;
import com.ricardofaria.salarioliquido.model.resultado.DecimoTerceiro;

public class CalculaDecimoTerceiroTest {
	
	private static final float VALOR_SALARIO_MINIMO = 788.00f;

	private CalculaDecimoTerceiro calcular;
	
	@Before
	public void init() {
		this.calcular = new CalculaDecimoTerceiro();
	}

	@Test
	public void testParcelaUmValorSimples() {
		BigDecimal salarioBruto = new BigDecimal("2000");
		BigDecimal parcelaUm = calcularParcelaUm(salarioBruto);
		
		assertEquals(createMonetaryBigDecimal(1000f), parcelaUm);
	}
	
	@Test
	public void testParcelaUmValorQueNecessitaArredondamento() {
		BigDecimal salarioBruto = new BigDecimal("1375.15");
		BigDecimal parcelaUm = calcularParcelaUm(salarioBruto);
		
		assertEquals(createMonetaryBigDecimal(687.58f), parcelaUm);
	}
	
	@Test
	public void testParcelaDoisValorSimples() {
		BigDecimal salarioBruto = new BigDecimal("2000");
		BigDecimal descontoINSS = new BigDecimal("100");
		BigDecimal descontoIRPF = new BigDecimal("100");
		BigDecimal parcelaUm = calcularParcelaDois(salarioBruto, descontoINSS, descontoIRPF);
		
		assertEquals(createMonetaryBigDecimal(800.0f), parcelaUm);
	}
	
	@Test
	public void testParcelaDoisValorSemDesconto() {
		BigDecimal salarioBruto = new BigDecimal("2000");
		BigDecimal descontoINSS = new BigDecimal("0");
		BigDecimal descontoIRPF = new BigDecimal("0");
		BigDecimal parcelaUm = calcularParcelaDois(salarioBruto, descontoINSS, descontoIRPF);
		
		assertEquals(createMonetaryBigDecimal(1000.0f), parcelaUm);
	}

	@Test
	public void testCalcularDecimoTerceiroCompletoSalarioMinimo() {
		float salarioBruto = VALOR_SALARIO_MINIMO;
		
		ParametrosDecimoTerceiro parametros = new ParametrosDecimoTerceiro(salarioBruto);
		DecimoTerceiro decimoTerceiro = calcular.calcular(parametros); 
		
		assertEquals(createMonetaryBigDecimal(63.04f), decimoTerceiro.getDescontoInss());
		assertEquals(createMonetaryBigDecimal(0.0f), decimoTerceiro.getDescontoIrpf());
		assertEquals(createMonetaryBigDecimal(394.00f), decimoTerceiro.getSalarioParcelaUm());
		assertEquals(createMonetaryBigDecimal(330.96f), decimoTerceiro.getSalarioParcelaDois());
	}
	
	@Test
	public void testCalcularDecimoTerceiroCompletoSalarioMinimoComAdicionalDePericulosidade() {
		float salarioBruto = VALOR_SALARIO_MINIMO;
		
		ParametrosDecimoTerceiro parametros = new ParametrosDecimoTerceiro(salarioBruto);
		parametros.setAdicionalDePericulosidade(true);
		DecimoTerceiro decimoTerceiro = calcular.calcular(parametros); 
		
		assertEquals(createMonetaryBigDecimal(81.95f), decimoTerceiro.getDescontoInss());
		assertEquals(createMonetaryBigDecimal(0.0f), decimoTerceiro.getDescontoIrpf());
		assertEquals(createMonetaryBigDecimal(512.20f), decimoTerceiro.getSalarioParcelaUm());
		assertEquals(createMonetaryBigDecimal(430.25f), decimoTerceiro.getSalarioParcelaDois());
		assertEquals(createMonetaryBigDecimal(236.40f), decimoTerceiro.getAdicionalPericulosidade());
	}
	
	@Test
	public void testCalcularDecimoTerceiroCompletoSalario1500() {
		ParametrosDecimoTerceiro parametros = new ParametrosDecimoTerceiro(1500);
		DecimoTerceiro decimoTerceiro = calcular.calcular(parametros); 
		
		assertEquals(createMonetaryBigDecimal(135.00f), decimoTerceiro.getDescontoInss());
		assertEquals(createMonetaryBigDecimal(0.0f), decimoTerceiro.getDescontoIrpf());
		assertEquals(createMonetaryBigDecimal(750.00f), decimoTerceiro.getSalarioParcelaUm());
		assertEquals(createMonetaryBigDecimal(615.00f), decimoTerceiro.getSalarioParcelaDois());
	}
	
	@Test
	public void testCalcularDecimoTerceiroCompletoSalario2000() {
		float salarioBruto = 2000.00f;
		ParametrosDecimoTerceiro parametros = new ParametrosDecimoTerceiro(salarioBruto);
		DecimoTerceiro decimoTerceiro = calcular.calcular(parametros); 
		
		assertEquals(createMonetaryBigDecimal(180.00f), decimoTerceiro.getDescontoInss());
		assertEquals(createMonetaryBigDecimal(0.0f), decimoTerceiro.getDescontoIrpf());
		assertEquals(createMonetaryBigDecimal(1000.00f), decimoTerceiro.getSalarioParcelaUm());
		assertEquals(createMonetaryBigDecimal(820.00f), decimoTerceiro.getSalarioParcelaDois());
	}
	
	@Test
	public void testCalcularDecimoTerceiroCompletoSalario2500() {
		float salarioBruto = 2500.00f;
		ParametrosDecimoTerceiro parametros = new ParametrosDecimoTerceiro(salarioBruto);
		DecimoTerceiro decimoTerceiro = calcular.calcular(parametros); 
		
		assertEquals(createMonetaryBigDecimal(275.0f), decimoTerceiro.getDescontoInss());
		assertEquals(createMonetaryBigDecimal(24.08f), decimoTerceiro.getDescontoIrpf());
		assertEquals(createMonetaryBigDecimal(1250.00f), decimoTerceiro.getSalarioParcelaUm());
		assertEquals(createMonetaryBigDecimal(950.92f), decimoTerceiro.getSalarioParcelaDois());
	}
	
	@Test
	public void testCalcularDecimoTerceiroCompletoSalario3000() {
		float salarioBruto = 3000.00f;
		ParametrosDecimoTerceiro parametros = new ParametrosDecimoTerceiro(salarioBruto);
		DecimoTerceiro decimoTerceiro = calcular.calcular(parametros); 
		
		assertEquals(createMonetaryBigDecimal(330.0f), decimoTerceiro.getDescontoInss());
		assertEquals(createMonetaryBigDecimal(57.45f), decimoTerceiro.getDescontoIrpf());
		assertEquals(createMonetaryBigDecimal(1500.00f), decimoTerceiro.getSalarioParcelaUm());
		assertEquals(createMonetaryBigDecimal(1112.55f), decimoTerceiro.getSalarioParcelaDois());
	}
	
	@Test
	public void testCalcularDecimoTerceiroCompletoSalario3500() {
		float salarioBruto = 3500.00f;
		ParametrosDecimoTerceiro parametros = new ParametrosDecimoTerceiro(salarioBruto);
		DecimoTerceiro decimoTerceiro = calcular.calcular(parametros); 
		
		assertEquals(createMonetaryBigDecimal(385.0f), decimoTerceiro.getDescontoInss());
		assertEquals(createMonetaryBigDecimal(112.45f), decimoTerceiro.getDescontoIrpf());
		assertEquals(createMonetaryBigDecimal(1750.00f), decimoTerceiro.getSalarioParcelaUm());
		assertEquals(createMonetaryBigDecimal(1252.55f), decimoTerceiro.getSalarioParcelaDois());
	}
	
	@Test
	public void testCalcularDecimoTerceiroCompletoSalario4000() {
		float salarioBruto = 4000.00f;
		ParametrosDecimoTerceiro parametros = new ParametrosDecimoTerceiro(salarioBruto);
		DecimoTerceiro decimoTerceiro = calcular.calcular(parametros); 
		
		assertEquals(createMonetaryBigDecimal(440.0f), decimoTerceiro.getDescontoInss());
		assertEquals(createMonetaryBigDecimal(179.20f), decimoTerceiro.getDescontoIrpf());
		assertEquals(createMonetaryBigDecimal(2000.00f), decimoTerceiro.getSalarioParcelaUm());
		assertEquals(createMonetaryBigDecimal(1380.80f), decimoTerceiro.getSalarioParcelaDois());
	}
	
	@Test
	public void testCalcularDecimoTerceiroCompletoSalario4500() {
		float salarioBruto = 4500.00f;
		ParametrosDecimoTerceiro parametros = new ParametrosDecimoTerceiro(salarioBruto);
		DecimoTerceiro decimoTerceiro = calcular.calcular(parametros); 
		
		assertEquals(createMonetaryBigDecimal(495.0f), decimoTerceiro.getDescontoInss());
		assertEquals(createMonetaryBigDecimal(265.0f), decimoTerceiro.getDescontoIrpf());
		assertEquals(createMonetaryBigDecimal(2250.00f), decimoTerceiro.getSalarioParcelaUm());
		assertEquals(createMonetaryBigDecimal(1490.00f), decimoTerceiro.getSalarioParcelaDois());
	}
	
	@Test
	public void testCalcularDecimoTerceiroCompletoSalario5000() {
		float salarioBruto = 5000.00f;
		ParametrosDecimoTerceiro parametros = new ParametrosDecimoTerceiro(salarioBruto);
		DecimoTerceiro decimoTerceiro = calcular.calcular(parametros); 
		
		assertEquals(createMonetaryBigDecimal(513.01f), decimoTerceiro.getDescontoInss());
		assertEquals(createMonetaryBigDecimal(373.44f), decimoTerceiro.getDescontoIrpf());
		assertEquals(createMonetaryBigDecimal(2500.00f), decimoTerceiro.getSalarioParcelaUm());
		assertEquals(createMonetaryBigDecimal(1613.55f), decimoTerceiro.getSalarioParcelaDois());
	}
	
}
