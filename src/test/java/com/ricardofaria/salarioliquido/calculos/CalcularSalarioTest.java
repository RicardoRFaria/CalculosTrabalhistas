package com.ricardofaria.salarioliquido.calculos;

import static com.ricardofaria.salarioliquido.util.PrecisionUtil.createMonetaryBigDecimal;
import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import com.ricardofaria.salarioliquido.model.input.ParametrosSalario;
import com.ricardofaria.salarioliquido.model.resultado.Salario;

public class CalcularSalarioTest {
	
	private static final float VALOR_SALARIO_MINIMO = 788.00f;
	private static final BigDecimal BIG_DECIMAL_0 = createMonetaryBigDecimal("0");

	private Calcular calcular;
	
	@Before
	public void init() {
		this.calcular = new Calcular();
	}
	
	@Test
	public void testCalcularSalarioMinimo() {
		ParametrosSalario parametro = new ParametrosSalario(VALOR_SALARIO_MINIMO);
		
		Salario salario = calcular.calcularSalario(parametro); 
		
		assertEquals(createMonetaryBigDecimal(63.04f), salario.getDescontoInss());
		assertEquals(BIG_DECIMAL_0, salario.getDescontoIrpf());
		assertEquals(createMonetaryBigDecimal(724.96f), salario.getValorLiquido());
	}
	
	@Test
	public void testCalcularSalario1500() {
		ParametrosSalario parametro = new ParametrosSalario(1500f);
		
		Salario salario = calcular.calcularSalario(parametro); 
		
		assertEquals(createMonetaryBigDecimal(135.00f), salario.getDescontoInss());
		assertEquals(BIG_DECIMAL_0, salario.getDescontoIrpf());
		assertEquals(createMonetaryBigDecimal(1365.00f), salario.getValorLiquido());
	}
	
	@Test
	public void testCalcularSalario2000() {
		ParametrosSalario parametro = new ParametrosSalario(2000f);
		
		Salario salario = calcular.calcularSalario(parametro); 
		
		assertEquals(createMonetaryBigDecimal(180.00f), salario.getDescontoInss());
		assertEquals(BIG_DECIMAL_0, salario.getDescontoIrpf());
		assertEquals(createMonetaryBigDecimal(1820.0f), salario.getValorLiquido());
	}
	
	@Test
	public void testCalcularSalario2500() {
		ParametrosSalario parametro = new ParametrosSalario(2500f);
		
		Salario salario = calcular.calcularSalario(parametro); 
		
		assertEquals(createMonetaryBigDecimal(275.00f), salario.getDescontoInss());
		assertEquals(createMonetaryBigDecimal(24.08f), salario.getDescontoIrpf());
		assertEquals(createMonetaryBigDecimal(2200.92f), salario.getValorLiquido());
	}
	
	@Test
	public void testCalcularSalario3000() {
		ParametrosSalario parametro = new ParametrosSalario(3000f);
		
		Salario salario = calcular.calcularSalario(parametro); 
		
		assertEquals(createMonetaryBigDecimal(330.00f), salario.getDescontoInss());
		assertEquals(createMonetaryBigDecimal(57.45f), salario.getDescontoIrpf());
		assertEquals(createMonetaryBigDecimal(2612.55f), salario.getValorLiquido());
	}
	
	@Test
	public void testCalcularSalario3500() {
		ParametrosSalario parametro = new ParametrosSalario(3500f);
		
		Salario salario = calcular.calcularSalario(parametro); 
		
		assertEquals(createMonetaryBigDecimal(385.00f), salario.getDescontoInss());
		assertEquals(createMonetaryBigDecimal(112.45f), salario.getDescontoIrpf());
		assertEquals(createMonetaryBigDecimal(3002.55f), salario.getValorLiquido());
	}
	
	@Test
	public void testCalcularSalario4000() {
		ParametrosSalario parametro = new ParametrosSalario(4000);
		
		Salario salario = calcular.calcularSalario(parametro); 
		
		assertEquals(createMonetaryBigDecimal(440.00f), salario.getDescontoInss());
		assertEquals(createMonetaryBigDecimal(179.20f), salario.getDescontoIrpf());
		assertEquals(createMonetaryBigDecimal(3380.80f), salario.getValorLiquido());
	}
	
	@Test
	public void testCalcularSalario4500() {
		ParametrosSalario parametro = new ParametrosSalario(4500);
		
		Salario salario = calcular.calcularSalario(parametro); 
		
		assertEquals(createMonetaryBigDecimal(495.00f), salario.getDescontoInss());
		assertEquals(createMonetaryBigDecimal(265.00f), salario.getDescontoIrpf());
		assertEquals(createMonetaryBigDecimal(3740.00f), salario.getValorLiquido());
	}
	
	@Test
	public void testCalcularSalario5000() {
		ParametrosSalario parametro = new ParametrosSalario(5000f);
		
		Salario salario = calcular.calcularSalario(parametro); 
		
		assertEquals(createMonetaryBigDecimal(513.01f), salario.getDescontoInss());
		assertEquals(createMonetaryBigDecimal(373.44f), salario.getDescontoIrpf());
		assertEquals(createMonetaryBigDecimal(4113.55f), salario.getValorLiquido());
	}
	
	
	@Test
	public void testcalcularSalarioMinimo() {
		ParametrosSalario parametro = new ParametrosSalario(VALOR_SALARIO_MINIMO);
		parametro.setDataInicioColaborador(getDataInicioFuncionario());
		
		Salario salario = calcular.calcularSalario(parametro); 
		
		assertEquals(createMonetaryBigDecimal(42.78f), salario.getDescontoInss());
		assertEquals(BIG_DECIMAL_0, salario.getDescontoIrpf());
		assertEquals(createMonetaryBigDecimal(491.94f), salario.getValorLiquido());
	}
	
	@Test
	public void testcalcularSalario1500() {
		ParametrosSalario parametro = new ParametrosSalario(1500f);
		parametro.setDataInicioColaborador(getDataInicioFuncionario());
		
		Salario salario = calcular.calcularSalario(parametro); 
		
		assertEquals(createMonetaryBigDecimal(81.43f), salario.getDescontoInss());
		assertEquals(BIG_DECIMAL_0, salario.getDescontoIrpf());
		assertEquals(createMonetaryBigDecimal(936.43f), salario.getValorLiquido());
	}
	
	@Test
	public void testcalcularSalario2000() {
		ParametrosSalario parametro = new ParametrosSalario(2000f);
		parametro.setDataInicioColaborador(getDataInicioFuncionario());
		
		Salario salario = calcular.calcularSalario(parametro);
		
		assertEquals(createMonetaryBigDecimal(108.57f), salario.getDescontoInss());
		assertEquals(BIG_DECIMAL_0, salario.getDescontoIrpf());
		assertEquals(createMonetaryBigDecimal(1248.57f), salario.getValorLiquido());
	}
	
	@Test
	public void testcalcularSalario2500() {
		ParametrosSalario parametro = new ParametrosSalario(2500f);
		parametro.setDataInicioColaborador(getDataInicioFuncionario());
		
		Salario salario = calcular.calcularSalario(parametro);
		
		assertEquals(createMonetaryBigDecimal(152.68f), salario.getDescontoInss());
		assertEquals(BIG_DECIMAL_0, salario.getDescontoIrpf());
		assertEquals(createMonetaryBigDecimal(1543.75f), salario.getValorLiquido());
	}
	
	@Test
	public void testcalcularSalario3000() {
		ParametrosSalario parametro = new ParametrosSalario(3000f);
		parametro.setDataInicioColaborador(getDataInicioFuncionario());
		
		Salario salario = calcular.calcularSalario(parametro);
		
		assertEquals(createMonetaryBigDecimal(183.21f), salario.getDescontoInss());
		assertEquals(BIG_DECIMAL_0, salario.getDescontoIrpf());
		assertEquals(createMonetaryBigDecimal(1852.50f), salario.getValorLiquido());
	}
	
	@Test
	public void testcalcularSalario3500() {
		ParametrosSalario parametro = new ParametrosSalario(3500f);
		parametro.setDataInicioColaborador(getDataInicioFuncionario());
		
		Salario salario = calcular.calcularSalario(parametro);
		
		assertEquals(createMonetaryBigDecimal(261.25f), salario.getDescontoInss());
		assertEquals(createMonetaryBigDecimal(15.73f), salario.getDescontoIrpf());
		assertEquals(createMonetaryBigDecimal(2098.02f), salario.getValorLiquido());
	}
	
	@Test
	public void testcalcularSalario4000() {
		ParametrosSalario parametro = new ParametrosSalario(4000f);
		parametro.setDataInicioColaborador(getDataInicioFuncionario());
		
		Salario salario = calcular.calcularSalario(parametro);
		
		assertEquals(createMonetaryBigDecimal(298.57f), salario.getDescontoInss());
		assertEquals(createMonetaryBigDecimal(38.38f), salario.getDescontoIrpf());
		assertEquals(createMonetaryBigDecimal(2377.34f), salario.getValorLiquido());
	}
	
	@Test
	public void testcalcularSalario4500() {
		ParametrosSalario parametro = new ParametrosSalario(4500f);
		parametro.setDataInicioColaborador(getDataInicioFuncionario());
		
		Salario salario = calcular.calcularSalario(parametro);
		
		assertEquals(createMonetaryBigDecimal(335.89f), salario.getDescontoInss());
		assertEquals(createMonetaryBigDecimal(61.03f), salario.getDescontoIrpf());
		assertEquals(createMonetaryBigDecimal(2656.65f), salario.getValorLiquido());
	}
	
	@Test
	public void testcalcularSalario5000() {
		ParametrosSalario parametro = new ParametrosSalario(5000f);
		parametro.setDataInicioColaborador(getDataInicioFuncionario());
		
		Salario salario = calcular.calcularSalario(parametro);
		
		assertEquals(createMonetaryBigDecimal(373.21f), salario.getDescontoInss());
		assertEquals(createMonetaryBigDecimal(98.15f), salario.getDescontoIrpf());
		assertEquals(createMonetaryBigDecimal(2921.50f), salario.getValorLiquido());
	}
	
	public Date getDataInicioFuncionario() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(2015, Calendar.FEBRUARY, 10, 0, 0, 1);
		return calendar.getTime();
	}

}
