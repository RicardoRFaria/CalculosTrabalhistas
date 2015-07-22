package com.ricardofaria.salarioliquido.calculos;

import static org.junit.Assert.assertEquals;

import java.util.Calendar;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import com.ricardofaria.salarioliquido.model.input.ParametrosSalario;
import com.ricardofaria.salarioliquido.model.resultado.Salario;

public class CalcularSalarioTest {
	
	private static final float VALOR_SALARIO_MINIMO = 788.00f;

	private Calcular calcular;
	
	@Before
	public void init() {
		this.calcular = new Calcular();
	}
	
	@Test
	public void testCalcularSalarioMinimo() {
		ParametrosSalario parametro = new ParametrosSalario(VALOR_SALARIO_MINIMO);
		
		Salario salario = calcular.calcularSalario(parametro); 
		
		assertEquals(63.04f, salario.getDescontoInss(), 0.01);
		assertEquals(0.0f, salario.getDescontoIrpf(), 0.01);
		assertEquals(724.96f, salario.getValorLiquido(), 0.01);
	}
	
	@Test
	public void testCalcularSalario1500() {
		ParametrosSalario parametro = new ParametrosSalario(1500f);
		
		Salario salario = calcular.calcularSalario(parametro); 
		
		assertEquals(135.00f, salario.getDescontoInss(), 0.01);
		assertEquals(0.0f, salario.getDescontoIrpf(), 0.01);
		assertEquals(1365.00f, salario.getValorLiquido(), 0.01);
	}
	
	@Test
	public void testCalcularSalario2000() {
		ParametrosSalario parametro = new ParametrosSalario(2000f);
		
		Salario salario = calcular.calcularSalario(parametro); 
		
		assertEquals(180.00f, salario.getDescontoInss(), 0.01);
		assertEquals(0.0f, salario.getDescontoIrpf(), 0.01);
		assertEquals(1820.0f, salario.getValorLiquido(), 0.01);
	}
	
	@Test
	public void testCalcularSalario2500() {
		ParametrosSalario parametro = new ParametrosSalario(2500f);
		
		Salario salario = calcular.calcularSalario(parametro); 
		
		assertEquals(275.00f, salario.getDescontoInss(), 0.01);
		assertEquals(24.08f, salario.getDescontoIrpf(), 0.01);
		assertEquals(2200.92f, salario.getValorLiquido(), 0.01);
	}
	
	@Test
	public void testCalcularSalario3000() {
		ParametrosSalario parametro = new ParametrosSalario(3000f);
		
		Salario salario = calcular.calcularSalario(parametro); 
		
		assertEquals(330.00f, salario.getDescontoInss(), 0.01);
		assertEquals(57.45f, salario.getDescontoIrpf(), 0.01);
		assertEquals(2612.55f, salario.getValorLiquido(), 0.01);
	}
	
	@Test
	public void testCalcularSalario3500() {
		ParametrosSalario parametro = new ParametrosSalario(3500f);
		
		Salario salario = calcular.calcularSalario(parametro); 
		
		assertEquals(385.00f, salario.getDescontoInss(), 0.01);
		assertEquals(112.45f, salario.getDescontoIrpf(), 0.01);
		assertEquals(3002.55f, salario.getValorLiquido(), 0.01);
	}
	
	@Test
	public void testCalcularSalario4000() {
		ParametrosSalario parametro = new ParametrosSalario(4000);
		
		Salario salario = calcular.calcularSalario(parametro); 
		
		assertEquals(440.00f, salario.getDescontoInss(), 0.01);
		assertEquals(179.20f, salario.getDescontoIrpf(), 0.01);
		assertEquals(3380.80f, salario.getValorLiquido(), 0.01);
	}
	
	@Test
	public void testCalcularSalario4500() {
		ParametrosSalario parametro = new ParametrosSalario(4500);
		
		Salario salario = calcular.calcularSalario(parametro); 
		
		assertEquals(495.00f, salario.getDescontoInss(), 0.01);
		assertEquals(265.00f, salario.getDescontoIrpf(), 0.01);
		assertEquals(3740.00f, salario.getValorLiquido(), 0.01);
	}
	
	@Test
	public void testCalcularSalario5000() {
		ParametrosSalario parametro = new ParametrosSalario(5000f);
		
		Salario salario = calcular.calcularSalario(parametro); 
		
		assertEquals(513.01f, salario.getDescontoInss(), 0.01);
		assertEquals(373.44f, salario.getDescontoIrpf(), 0.01);
		assertEquals(4113.55f, salario.getValorLiquido(), 0.01);
	}
	
	
	@Test
	public void testcalcularSalarioMinimo() {
		ParametrosSalario parametro = new ParametrosSalario(VALOR_SALARIO_MINIMO);
		parametro.setDataInicioColaborador(getDataInicioFuncionario());
		
		Salario salario = calcular.calcularSalario(parametro); 
		
		assertEquals(42.77f, salario.getDescontoInss(), 0.01);
		assertEquals(0.0f, salario.getDescontoIrpf(), 0.01);
		assertEquals(491.94f, salario.getValorLiquido(), 0.01);
	}
	
	@Test
	public void testcalcularSalario1500() {
		ParametrosSalario parametro = new ParametrosSalario(1500f);
		parametro.setDataInicioColaborador(getDataInicioFuncionario());
		
		Salario salario = calcular.calcularSalario(parametro); 
		
		assertEquals(81.43f, salario.getDescontoInss(), 0.01);
		assertEquals(0.0f, salario.getDescontoIrpf(), 0.01);
		assertEquals(936.43f, salario.getValorLiquido(), 0.01);
	}
	
	@Test
	public void testcalcularSalario2000() {
		ParametrosSalario parametro = new ParametrosSalario(2000f);
		parametro.setDataInicioColaborador(getDataInicioFuncionario());
		
		Salario salario = calcular.calcularSalario(parametro);
		
		assertEquals(108.57f, salario.getDescontoInss(), 0.01);
		assertEquals(0.0f, salario.getDescontoIrpf(), 0.01);
		assertEquals(1248.57f, salario.getValorLiquido(), 0.01);
	}
	
	@Test
	public void testcalcularSalario2500() {
		ParametrosSalario parametro = new ParametrosSalario(2500f);
		parametro.setDataInicioColaborador(getDataInicioFuncionario());
		
		Salario salario = calcular.calcularSalario(parametro);
		
		assertEquals(152.68f, salario.getDescontoInss(), 0.01);
		assertEquals(0.00f, salario.getDescontoIrpf(), 0.01);
		assertEquals(1543.75f, salario.getValorLiquido(), 0.01);
	}
	
	@Test
	public void testcalcularSalario3000() {
		ParametrosSalario parametro = new ParametrosSalario(3000f);
		parametro.setDataInicioColaborador(getDataInicioFuncionario());
		
		Salario salario = calcular.calcularSalario(parametro);
		
		assertEquals(183.21f, salario.getDescontoInss(), 0.01);
		assertEquals(0.00f, salario.getDescontoIrpf(), 0.01);
		assertEquals(1852.50f, salario.getValorLiquido(), 0.01);
	}
	
	@Test
	public void testcalcularSalario3500() {
		ParametrosSalario parametro = new ParametrosSalario(3500f);
		parametro.setDataInicioColaborador(getDataInicioFuncionario());
		
		Salario salario = calcular.calcularSalario(parametro);
		
		assertEquals(261.25f, salario.getDescontoInss(), 0.01);
		assertEquals(15.73f, salario.getDescontoIrpf(), 0.01);
		assertEquals(2098.02f, salario.getValorLiquido(), 0.01);
	}
	
	@Test
	public void testcalcularSalario4000() {
		ParametrosSalario parametro = new ParametrosSalario(4000f);
		parametro.setDataInicioColaborador(getDataInicioFuncionario());
		
		Salario salario = calcular.calcularSalario(parametro);
		
		assertEquals(298.57f, salario.getDescontoInss(), 0.01);
		assertEquals(38.38f, salario.getDescontoIrpf(), 0.01);
		assertEquals(2377.34f, salario.getValorLiquido(), 0.01);
	}
	
	@Test
	public void testcalcularSalario4500() {
		ParametrosSalario parametro = new ParametrosSalario(4500f);
		parametro.setDataInicioColaborador(getDataInicioFuncionario());
		
		Salario salario = calcular.calcularSalario(parametro);
		
		assertEquals(335.89f, salario.getDescontoInss(), 0.01);
		assertEquals(61.03f, salario.getDescontoIrpf(), 0.01);
		assertEquals(2656.65f, salario.getValorLiquido(), 0.01);
	}
	
	@Test
	public void testcalcularSalario5000() {
		ParametrosSalario parametro = new ParametrosSalario(5000f);
		parametro.setDataInicioColaborador(getDataInicioFuncionario());
		
		Salario salario = calcular.calcularSalario(parametro);
		
		assertEquals(373.21f, salario.getDescontoInss(), 0.01);
		assertEquals(98.15f, salario.getDescontoIrpf(), 0.01);
		assertEquals(2921.50f, salario.getValorLiquido(), 0.01);
	}
	
	public Date getDataInicioFuncionario() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(2015, Calendar.FEBRUARY, 10, 0, 0, 1);
		return calendar.getTime();
	}

}
