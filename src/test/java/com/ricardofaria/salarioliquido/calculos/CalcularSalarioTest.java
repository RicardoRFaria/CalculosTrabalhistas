package com.ricardofaria.salarioliquido.calculos;

import static org.junit.Assert.assertEquals;

import java.util.Calendar;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import com.ricardofaria.salarioliquido.model.Salario;

public class CalcularSalarioTest {
	
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
	public void testCalcularSalarioParcialMinimo() {
		float salarioBruto = VALOR_SALARIO_MINIMO;
		Date dataInicioFuncionar = getDataInicioFuncionario();
		
		Salario salario = calcular.calcularSalarioParcial(salarioBruto, dataInicioFuncionar); 
		
		assertEquals(42.77f, salario.getDescontoInss(), 0.01);
		assertEquals(0.0f, salario.getDescontoIrpf(), 0.01);
		assertEquals(491.94f, salario.getSalarioLiquido(), 0.01);
	}
	
	@Test
	public void testCalcularSalarioParcial1500() {
		float salarioBruto = 1500.00f;
		int quantidadeDependentes = 0;
		Date dataInicioFuncionar = getDataInicioFuncionario();
		
		Salario salario = calcular.calcularSalarioParcial(salarioBruto, quantidadeDependentes, dataInicioFuncionar); 
		
		assertEquals(81.43f, salario.getDescontoInss(), 0.01);
		assertEquals(0.0f, salario.getDescontoIrpf(), 0.01);
		assertEquals(936.43f, salario.getSalarioLiquido(), 0.01);
	}
	
	@Test
	public void testCalcularSalarioParcial2000() {
		float salarioBruto = 2000.00f;
		int quantidadeDependentes = 0;
		Date dataInicioFuncionar = getDataInicioFuncionario();
		
		Salario salario = calcular.calcularSalarioParcial(salarioBruto, quantidadeDependentes, dataInicioFuncionar);
		
		assertEquals(108.57f, salario.getDescontoInss(), 0.01);
		assertEquals(0.0f, salario.getDescontoIrpf(), 0.01);
		assertEquals(1248.57f, salario.getSalarioLiquido(), 0.01);
	}
	
	@Test
	public void testCalcularSalarioParcial2500() {
		float salarioBruto = 2500.00f;
		int quantidadeDependentes = 0;
		Date dataInicioFuncionar = getDataInicioFuncionario();
		
		Salario salario = calcular.calcularSalarioParcial(salarioBruto, quantidadeDependentes, dataInicioFuncionar);
		
		assertEquals(152.68f, salario.getDescontoInss(), 0.01);
		assertEquals(0.00f, salario.getDescontoIrpf(), 0.01);
		assertEquals(1543.75f, salario.getSalarioLiquido(), 0.01);
	}
	
	@Test
	public void testCalcularSalarioParcial3000() {
		float salarioBruto = 3000.00f;
		int quantidadeDependentes = 0;
		Date dataInicioFuncionar = getDataInicioFuncionario();
		
		Salario salario = calcular.calcularSalarioParcial(salarioBruto, quantidadeDependentes, dataInicioFuncionar);
		
		assertEquals(183.21f, salario.getDescontoInss(), 0.01);
		assertEquals(0.00f, salario.getDescontoIrpf(), 0.01);
		assertEquals(1852.50f, salario.getSalarioLiquido(), 0.01);
	}
	
	@Test
	public void testCalcularSalarioParcial3500() {
		float salarioBruto = 3500.00f;
		int quantidadeDependentes = 0;
		Date dataInicioFuncionar = getDataInicioFuncionario();
		
		Salario salario = calcular.calcularSalarioParcial(salarioBruto, quantidadeDependentes, dataInicioFuncionar);
		
		assertEquals(261.25f, salario.getDescontoInss(), 0.01);
		assertEquals(15.73f, salario.getDescontoIrpf(), 0.01);
		assertEquals(2098.02f, salario.getSalarioLiquido(), 0.01);
	}
	
	@Test
	public void testCalcularSalarioParcial4000() {
		float salarioBruto = 4000.00f;
		int quantidadeDependentes = 0;
		Date dataInicioFuncionar = getDataInicioFuncionario();
		
		Salario salario = calcular.calcularSalarioParcial(salarioBruto, quantidadeDependentes, dataInicioFuncionar);
		
		assertEquals(298.57f, salario.getDescontoInss(), 0.01);
		assertEquals(38.38f, salario.getDescontoIrpf(), 0.01);
		assertEquals(2377.34f, salario.getSalarioLiquido(), 0.01);
	}
	
	@Test
	public void testCalcularSalarioParcial4500() {
		float salarioBruto = 4500.00f;
		int quantidadeDependentes = 0;
		Date dataInicioFuncionar = getDataInicioFuncionario();
		
		Salario salario = calcular.calcularSalarioParcial(salarioBruto, quantidadeDependentes, dataInicioFuncionar);
		
		assertEquals(335.89f, salario.getDescontoInss(), 0.01);
		assertEquals(61.03f, salario.getDescontoIrpf(), 0.01);
		assertEquals(2656.65f, salario.getSalarioLiquido(), 0.01);
	}
	
	@Test
	public void testCalcularSalarioParcial5000() {
		float salarioBruto = 5000.00f;
		int quantidadeDependentes = 0;
		Date dataInicioFuncionar = getDataInicioFuncionario();
		
		Salario salario = calcular.calcularSalarioParcial(salarioBruto, quantidadeDependentes, dataInicioFuncionar);
		
		assertEquals(373.21f, salario.getDescontoInss(), 0.01);
		assertEquals(98.15f, salario.getDescontoIrpf(), 0.01);
		assertEquals(2921.50f, salario.getSalarioLiquido(), 0.01);
	}
	
	public Date getDataInicioFuncionario() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(2015, Calendar.FEBRUARY, 10, 0, 0, 1);
		return calendar.getTime();
	}

}
