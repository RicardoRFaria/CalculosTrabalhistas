package com.ricardofaria.salarioliquido.calculos;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.ricardofaria.salarioliquido.model.DecimoTerceiro;
import com.ricardofaria.salarioliquido.model.Ferias;
import com.ricardofaria.salarioliquido.model.Ferias.TIPO_FERIAS;
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
		float salarioBruto = VALOR_SALARIO_MINIMO;
		
		Salario salario = calcular.calcularSalario(salarioBruto); 
		
		assertEquals(63.04f, salario.getDescontoInss(), 0.01);
		assertEquals(0.0f, salario.getDescontoIrpf(), 0.01);
		assertEquals(724.96f, salario.getSalarioLiquido(), 0.01);
	}
	
	@Test
	public void testCalcularSalario1500() {
		float salarioBruto = 1500.00f;
		int quantidadeDependentes = 0;
		
		Salario salario = calcular.calcularSalario(salarioBruto, quantidadeDependentes); 
		
		assertEquals(135.00f, salario.getDescontoInss(), 0.01);
		assertEquals(0.0f, salario.getDescontoIrpf(), 0.01);
		assertEquals(1365.00f, salario.getSalarioLiquido(), 0.01);
	}
	
	@Test
	public void testCalcularSalario2000() {
		float salarioBruto = 2000.00f;
		int quantidadeDependentes = 0;
		
		Salario salario = calcular.calcularSalario(salarioBruto, quantidadeDependentes); 
		
		assertEquals(180.00f, salario.getDescontoInss(), 0.01);
		assertEquals(0.0f, salario.getDescontoIrpf(), 0.01);
		assertEquals(1820.0f, salario.getSalarioLiquido(), 0.01);
	}
	
	@Test
	public void testCalcularSalario2500() {
		float salarioBruto = 2500.00f;
		int quantidadeDependentes = 0;
		
		Salario salario = calcular.calcularSalario(salarioBruto, quantidadeDependentes); 
		
		assertEquals(275.00f, salario.getDescontoInss(), 0.01);
		assertEquals(24.08f, salario.getDescontoIrpf(), 0.01);
		assertEquals(2200.92f, salario.getSalarioLiquido(), 0.01);
	}
	
	@Test
	public void testCalcularSalario3000() {
		float salarioBruto = 3000.00f;
		int quantidadeDependentes = 0;
		
		Salario salario = calcular.calcularSalario(salarioBruto, quantidadeDependentes); 
		
		assertEquals(330.00f, salario.getDescontoInss(), 0.01);
		assertEquals(57.45f, salario.getDescontoIrpf(), 0.01);
		assertEquals(2612.55f, salario.getSalarioLiquido(), 0.01);
	}
	
	@Test
	public void testCalcularSalario3500() {
		float salarioBruto = 3500.00f;
		int quantidadeDependentes = 0;
		
		Salario salario = calcular.calcularSalario(salarioBruto, quantidadeDependentes); 
		
		assertEquals(385.00f, salario.getDescontoInss(), 0.01);
		assertEquals(112.45f, salario.getDescontoIrpf(), 0.01);
		assertEquals(3002.55f, salario.getSalarioLiquido(), 0.01);
	}
	
	@Test
	public void testCalcularSalario4000() {
		float salarioBruto = 4000.00f;
		int quantidadeDependentes = 0;
		
		Salario salario = calcular.calcularSalario(salarioBruto, quantidadeDependentes); 
		
		assertEquals(440.00f, salario.getDescontoInss(), 0.01);
		assertEquals(179.20f, salario.getDescontoIrpf(), 0.01);
		assertEquals(3380.80f, salario.getSalarioLiquido(), 0.01);
	}
	
	@Test
	public void testCalcularSalario4500() {
		float salarioBruto = 4500.00f;
		int quantidadeDependentes = 0;
		
		Salario salario = calcular.calcularSalario(salarioBruto, quantidadeDependentes); 
		
		assertEquals(495.00f, salario.getDescontoInss(), 0.01);
		assertEquals(265.00f, salario.getDescontoIrpf(), 0.01);
		assertEquals(3740.00f, salario.getSalarioLiquido(), 0.01);
	}
	
	@Test
	public void testCalcularSalario5000() {
		float salarioBruto = 5000.00f;
		int quantidadeDependentes = 0;
		
		Salario salario = calcular.calcularSalario(salarioBruto, quantidadeDependentes); 
		
		assertEquals(513.01f, salario.getDescontoInss(), 0.01);
		assertEquals(373.44f, salario.getDescontoIrpf(), 0.01);
		assertEquals(4113.55f, salario.getSalarioLiquido(), 0.01);
	}
	
	@Test
	public void testCalcularFeriasSalarioMinimo() {
		float salarioBruto = VALOR_SALARIO_MINIMO;
		Ferias ferias = calcular.calcularFerias(salarioBruto);
		
		
		assertEquals(262.67, ferias.getValorFerias(), 0.01);
		assertEquals(84.05, ferias.getDescontoInss(), 0.01);
		assertEquals(0.0, ferias.getDescontoIrpf(), 0.01);
		assertEquals(0.0, ferias.getAbonoPecuniario(), 0.01);
		assertEquals(966.62, ferias.getFeriasLiquidas(), 0.01);
	}
	
	@Test
	public void testCalcularFerias1500() {
		float salarioBruto = (float) 1500.00;
		int dependentes = 0;
		Ferias ferias = calcular.calcularFerias(salarioBruto, dependentes);
		
		
		assertEquals(500.0, ferias.getValorFerias(), 0.01);
		assertEquals(180.0, ferias.getDescontoInss(), 0.01);
		assertEquals(0.0, ferias.getDescontoIrpf(), 0.01);
		assertEquals(0.0, ferias.getAbonoPecuniario(), 0.01);
		assertEquals(1819.99, ferias.getFeriasLiquidas(), 0.01);
	}
	
	@Test
	public void testCalcularFerias2000() {
		float salarioBruto = 2000.00f;
		int dependentes = 0;
		
		Ferias ferias = calcular.calcularFerias(salarioBruto, dependentes);
		
		assertEquals(666.67, ferias.getValorFerias(), 0.01);
		assertEquals(293.33, ferias.getDescontoInss(), 0.01);
		assertEquals(35.20, ferias.getDescontoIrpf(), 0.01);
		assertEquals(0.0, ferias.getAbonoPecuniario(), 0.01);
		assertEquals(2338.14, ferias.getFeriasLiquidas(), 0.01);	
	}
	
	@Test
	public void testCalcularFerias2500() {
		float salarioBruto = 2500.00f;
		
		Ferias ferias = calcular.calcularFerias(salarioBruto);
		
		assertEquals(833.33, ferias.getValorFerias(), 0.01);
		assertEquals(366.67, ferias.getDescontoInss(), 0.01);
		assertEquals(90.20, ferias.getDescontoIrpf(), 0.01);
		assertEquals(0.0, ferias.getAbonoPecuniario(), 0.01);
		assertEquals(2876.46, ferias.getFeriasLiquidas(), 0.01);	
	}
	
	@Test
	public void testCalcularFerias3000() {
		float salarioBruto = 3000.00f;
		Ferias ferias = calcular.calcularFerias(salarioBruto);
		
		assertEquals(1000.00, ferias.getValorFerias(), 0.01);
		assertEquals(440.00, ferias.getDescontoInss(), 0.01);
		assertEquals(179.20, ferias.getDescontoIrpf(), 0.01);
		assertEquals(0.0, ferias.getAbonoPecuniario(), 0.01);
		assertEquals(3380.80, ferias.getFeriasLiquidas(), 0.01);	
	}
	
	@Test
	public void testCalcularFerias3500() {
		float salarioBruto = 3500.00f;
		Ferias ferias = calcular.calcularFerias(salarioBruto);
		
		assertEquals(1166.67, ferias.getValorFerias(), 0.01);
		assertEquals(513.01, ferias.getDescontoInss(), 0.01);
		assertEquals(298.44, ferias.getDescontoIrpf(), 0.01);
		assertEquals(0.0, ferias.getAbonoPecuniario(), 0.01);
		assertEquals(3855.22, ferias.getFeriasLiquidas(), 0.01);	
	}
	
	@Test
	public void testCalcularFerias4000() {
		float salarioBruto = 4000.00f;
		Ferias ferias = calcular.calcularFerias(salarioBruto);
		
		assertEquals(1333.33, ferias.getValorFerias(), 0.01);
		assertEquals(513.01, ferias.getDescontoInss(), 0.01);
		assertEquals(456.23, ferias.getDescontoIrpf(), 0.01);
		assertEquals(0.0, ferias.getAbonoPecuniario(), 0.01);
		assertEquals(4364.09, ferias.getFeriasLiquidas(), 0.01);	
	}
	
	@Test
	public void testCalcularFerias4500() {
		float salarioBruto = 4500.00f;
		Ferias ferias = calcular.calcularFerias(salarioBruto);
		
		assertEquals(1500.00, ferias.getValorFerias(), 0.01);
		assertEquals(513.01, ferias.getDescontoInss(), 0.01);
		assertEquals(639.56, ferias.getDescontoIrpf(), 0.01);
		assertEquals(0.0, ferias.getAbonoPecuniario(), 0.01);
		assertEquals(4847.43, ferias.getFeriasLiquidas(), 0.01);	
	}
	
	@Test
	public void testCalcularFerias5000() {
		float salarioBruto = 5000.00f;
		Ferias ferias = calcular.calcularFerias(salarioBruto);
		
		assertEquals(1666.67, ferias.getValorFerias(), 0.01);
		assertEquals(513.01, ferias.getDescontoInss(), 0.01);
		assertEquals(822.90, ferias.getDescontoIrpf(), 0.01);
		assertEquals(0.0, ferias.getAbonoPecuniario(), 0.01);
		assertEquals(5330.76, ferias.getFeriasLiquidas(), 0.01);	
	}

	@Test
	public void testCalcular20DiasDeFeriasSalarioMinimo() {
		float salarioBruto = VALOR_SALARIO_MINIMO;
		Ferias ferias = calcular.calcularFerias(salarioBruto, TIPO_FERIAS.DIAS_20);
		
		
		assertEquals(175.11, ferias.getValorFerias(), 0.01);
		assertEquals(56.03, ferias.getDescontoInss(), 0.01);
		assertEquals(0.0, ferias.getDescontoIrpf(), 0.01);
		assertEquals(350.23, ferias.getAbonoPecuniario(), 0.01);
		assertEquals(994.63, ferias.getFeriasLiquidas(), 0.01);
	}
	
	@Test
	public void testCalcular20DiasDeFerias1500() {
		float salarioBruto = (float) 1500.00;
		int dependentes = 0;
		Ferias ferias = calcular.calcularFerias(salarioBruto, dependentes, TIPO_FERIAS.DIAS_20);
		
		assertEquals(333.33, ferias.getValorFerias(), 0.01);
		assertEquals(106.67, ferias.getDescontoInss(), 0.01);
		assertEquals(0.0, ferias.getDescontoIrpf(), 0.01);
		assertEquals(666.66, ferias.getAbonoPecuniario(), 0.01);
		assertEquals(1893.33, ferias.getFeriasLiquidas(), 0.01);
	}
	
	@Test
	public void testCalcular20DiasDeFerias2000() {
		float salarioBruto = 2000.00f;
		
		Ferias ferias = calcular.calcularFerias(salarioBruto, TIPO_FERIAS.DIAS_20);
		
		assertEquals(444.44, ferias.getValorFerias(), 0.01);
		assertEquals(160.00, ferias.getDescontoInss(), 0.01);
		assertEquals(0.00, ferias.getDescontoIrpf(), 0.01);
		assertEquals(888.89, ferias.getAbonoPecuniario(), 0.01);
		assertEquals(2506.67, ferias.getFeriasLiquidas(), 0.01);	
	}
	
	@Test
	public void testCalcular20DiasDeFerias2500() {
		float salarioBruto = 2500.00f;
		
		Ferias ferias = calcular.calcularFerias(salarioBruto, TIPO_FERIAS.DIAS_20);
		
		assertEquals(555.56, ferias.getValorFerias(), 0.01);
		assertEquals(200.00, ferias.getDescontoInss(), 0.01);
		assertEquals(8.87, ferias.getDescontoIrpf(), 0.01);
		assertEquals(1111.11, ferias.getAbonoPecuniario(), 0.01);
		assertEquals(3124.46, ferias.getFeriasLiquidas(), 0.01);	
	}
	
	@Test
	public void testCalcular20DiasDeFerias3000() {
		float salarioBruto = 3000.00f;
		Ferias ferias = calcular.calcularFerias(salarioBruto, TIPO_FERIAS.DIAS_20);
		
		assertEquals(666.67, ferias.getValorFerias(), 0.01);
		assertEquals(293.33, ferias.getDescontoInss(), 0.01);
		assertEquals(35.20, ferias.getDescontoIrpf(), 0.01);
		assertEquals(1333.33, ferias.getAbonoPecuniario(), 0.01);
		assertEquals(3671.47, ferias.getFeriasLiquidas(), 0.01);	
	}
	
	@Test
	public void testCalcular20DiasDeFerias3500() {
		float salarioBruto = 3500.00f;
		Ferias ferias = calcular.calcularFerias(salarioBruto, TIPO_FERIAS.DIAS_20);
		
		assertEquals(777.78, ferias.getValorFerias(), 0.01);
		assertEquals(342.22, ferias.getDescontoInss(), 0.01);
		assertEquals(64.87, ferias.getDescontoIrpf(), 0.01);
		assertEquals(1555.56, ferias.getAbonoPecuniario(), 0.01);
		assertEquals(4259.58, ferias.getFeriasLiquidas(), 0.01);	
	}
	
	@Test
	public void testCalcular20DiasDeFerias4000() {
		float salarioBruto = 4000.00f;
		Ferias ferias = calcular.calcularFerias(salarioBruto, TIPO_FERIAS.DIAS_20);
		
		assertEquals(888.89, ferias.getValorFerias(), 0.01);
		assertEquals(391.11, ferias.getDescontoInss(), 0.01);
		assertEquals(119.87, ferias.getDescontoIrpf(), 0.01);
		assertEquals(1777.78, ferias.getAbonoPecuniario(), 0.01);
		assertEquals(4822.36, ferias.getFeriasLiquidas(), 0.01);	
	}
	
	@Test
	public void testCalcular20DiasDeFerias4500() {
		float salarioBruto = 4500.00f;
		Ferias ferias = calcular.calcularFerias(salarioBruto, TIPO_FERIAS.DIAS_20);
		
		assertEquals(1000.00, ferias.getValorFerias(), 0.01);
		assertEquals(440.00, ferias.getDescontoInss(), 0.01);
		assertEquals(179.20, ferias.getDescontoIrpf(), 0.01);
		assertEquals(2000.0, ferias.getAbonoPecuniario(), 0.01);
		assertEquals(5380.80, ferias.getFeriasLiquidas(), 0.01);	
	}
	
	@Test
	public void testCalcular20DiasDeFerias5000() {
		float salarioBruto = 5000.00f;
		Ferias ferias = calcular.calcularFerias(salarioBruto, TIPO_FERIAS.DIAS_20);
		
		assertEquals(1111.11, ferias.getValorFerias(), 0.01);
		assertEquals(488.89, ferias.getDescontoInss(), 0.01);
		assertEquals(253.87, ferias.getDescontoIrpf(), 0.01);
		assertEquals(2222.22, ferias.getAbonoPecuniario(), 0.01);
		assertEquals(5923.91, ferias.getFeriasLiquidas(), 0.01);	
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
