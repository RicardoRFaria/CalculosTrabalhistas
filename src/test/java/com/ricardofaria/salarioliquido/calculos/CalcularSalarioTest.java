package com.ricardofaria.salarioliquido.calculos;

import static com.ricardofaria.salarioliquido.util.PrecisionUtil.createMonetaryBigDecimal;
import static com.ricardofaria.salarioliquido.calculos.ConstantesDeValores.*;
import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import com.ricardofaria.salarioliquido.model.input.ParametrosSalario;
import com.ricardofaria.salarioliquido.model.resultado.Salario;

public class CalcularSalarioTest {

	private static final BigDecimal BIG_DECIMAL_0 = createMonetaryBigDecimal("0");

	private CalculaSalario calcular;

	@Before
	public void init() {
		this.calcular = new CalculaSalario();
	}

	@Test
	public void testCalcularSalarioMinimo() {
		ParametrosSalario parametro = new ParametrosSalario(VALOR_SALARIO_MINIMO);

		Salario salario = calcular.calcular(parametro);

		assertEquals(createMonetaryBigDecimal(70.40f), salario.getDescontoInss());
		assertEquals(BIG_DECIMAL_0, salario.getDescontoIrpf());
		assertEquals(createMonetaryBigDecimal(809.60f), salario.getValorLiquido());
	}

	@Test
	public void testCalcularSalarioMinimoComAdicionalDePericulosidade() {
		ParametrosSalario parametro = new ParametrosSalario(VALOR_SALARIO_MINIMO);
		parametro.setAdicionalDePericulosidade(true);

		Salario salario = calcular.calcular(parametro);

		assertEquals(createMonetaryBigDecimal(91.52f), salario.getDescontoInss());
		assertEquals(BIG_DECIMAL_0, salario.getDescontoIrpf());
		assertEquals(createMonetaryBigDecimal(1052.48f), salario.getValorLiquido());
		assertEquals(createMonetaryBigDecimal(264.00f), salario.getAdicionalPericulosidade());
	}

	@Test
	public void testCalcularSalario1500() {
		ParametrosSalario parametro = new ParametrosSalario(1500f);

		Salario salario = calcular.calcular(parametro);

		assertEquals(createMonetaryBigDecimal(120.00f), salario.getDescontoInss());
		assertEquals(BIG_DECIMAL_0, salario.getDescontoIrpf());
		assertEquals(createMonetaryBigDecimal(1380.00f), salario.getValorLiquido());
	}

	@Test
	public void testCalcularSalario2000() {
		ParametrosSalario parametro = new ParametrosSalario(2000f);

		Salario salario = calcular.calcular(parametro);

		assertEquals(createMonetaryBigDecimal(180.00f), salario.getDescontoInss());
		assertEquals(BIG_DECIMAL_0, salario.getDescontoIrpf());
		assertEquals(createMonetaryBigDecimal(1820.0f), salario.getValorLiquido());
	}

	@Test
	public void testCalcularSalario2500() {
		ParametrosSalario parametro = new ParametrosSalario(2500f);

		Salario salario = calcular.calcular(parametro);

		assertEquals(createMonetaryBigDecimal(225.00f), salario.getDescontoInss());
		assertEquals(createMonetaryBigDecimal(27.82f), salario.getDescontoIrpf());
		assertEquals(createMonetaryBigDecimal(2247.18f), salario.getValorLiquido());
	}

	@Test
	public void testCalcularSalario3000() {
		ParametrosSalario parametro = new ParametrosSalario(3000f);

		Salario salario = calcular.calcular(parametro);

		assertEquals(createMonetaryBigDecimal(330.00f), salario.getDescontoInss());
		assertEquals(createMonetaryBigDecimal(57.45f), salario.getDescontoIrpf());
		assertEquals(createMonetaryBigDecimal(2612.55f), salario.getValorLiquido());
	}

	@Test
	public void testCalcularSalario3500() {
		ParametrosSalario parametro = new ParametrosSalario(3500f);

		Salario salario = calcular.calcular(parametro);

		assertEquals(createMonetaryBigDecimal(385.00f), salario.getDescontoInss());
		assertEquals(createMonetaryBigDecimal(112.45f), salario.getDescontoIrpf());
		assertEquals(createMonetaryBigDecimal(3002.55f), salario.getValorLiquido());
	}

	@Test
	public void testCalcularSalario4000() {
		ParametrosSalario parametro = new ParametrosSalario(4000);

		Salario salario = calcular.calcular(parametro);

		assertEquals(createMonetaryBigDecimal(440.00f), salario.getDescontoInss());
		assertEquals(createMonetaryBigDecimal(179.20f), salario.getDescontoIrpf());
		assertEquals(createMonetaryBigDecimal(3380.80f), salario.getValorLiquido());
	}

	@Test
	public void testCalcularSalario4500() {
		ParametrosSalario parametro = new ParametrosSalario(4500);

		Salario salario = calcular.calcular(parametro);

		assertEquals(createMonetaryBigDecimal(495.00f), salario.getDescontoInss());
		assertEquals(createMonetaryBigDecimal(265.00f), salario.getDescontoIrpf());
		assertEquals(createMonetaryBigDecimal(3740.00f), salario.getValorLiquido());
	}

	@Test
	public void testCalcularSalario5000() {
		ParametrosSalario parametro = new ParametrosSalario(5000f);

		Salario salario = calcular.calcular(parametro);

		assertEquals(createMonetaryBigDecimal(550.00f), salario.getDescontoInss());
		assertEquals(createMonetaryBigDecimal(365.12f), salario.getDescontoIrpf());
		assertEquals(createMonetaryBigDecimal(4084.88f), salario.getValorLiquido());
	}

	@Test
	public void testCalcularSalario5000ComAdicionalDePericulosidade() {
		ParametrosSalario parametro = new ParametrosSalario(5000f);
		parametro.setAdicionalDePericulosidade(true);
		Salario salario = calcular.calcular(parametro);

		assertEquals(createMonetaryBigDecimal(570.88f), salario.getDescontoInss());
		assertEquals(createMonetaryBigDecimal(761.15f), salario.getDescontoIrpf());
		assertEquals(createMonetaryBigDecimal(5167.97f), salario.getValorLiquido());
		assertEquals(createMonetaryBigDecimal(1500.00f), salario.getAdicionalPericulosidade());
	}


	@Test
	public void testCalcularSalarioParcialSalarioMinimo() {
		ParametrosSalario parametro = new ParametrosSalario(VALOR_SALARIO_MINIMO);
		parametro.setDataInicioColaborador(getDataInicioFuncionario());

		Salario salario = calcular.calcular(parametro);

		assertEquals(createMonetaryBigDecimal(47.77f), salario.getDescontoInss());
		assertEquals(BIG_DECIMAL_0, salario.getDescontoIrpf());
		assertEquals(createMonetaryBigDecimal(549.37f), salario.getValorLiquido());
	}

	@Test
	public void testCalcularSalarioParcialSalario1500() {
		ParametrosSalario parametro = new ParametrosSalario(1500f);
		parametro.setDataInicioColaborador(getDataInicioFuncionario());

		Salario salario = calcular.calcular(parametro);

		assertEquals(createMonetaryBigDecimal(81.43f), salario.getDescontoInss());
		assertEquals(BIG_DECIMAL_0, salario.getDescontoIrpf());
		assertEquals(createMonetaryBigDecimal(936.43f), salario.getValorLiquido());
	}

	@Test
	public void testCalcularSalarioParcialSalario2000() {
		ParametrosSalario parametro = new ParametrosSalario(2000f);
		parametro.setDataInicioColaborador(getDataInicioFuncionario());

		Salario salario = calcular.calcular(parametro);

		assertEquals(createMonetaryBigDecimal(108.57f), salario.getDescontoInss());
		assertEquals(BIG_DECIMAL_0, salario.getDescontoIrpf());
		assertEquals(createMonetaryBigDecimal(1248.57f), salario.getValorLiquido());
	}

	@Test
	public void testCalcularSalarioParcialSalario2500() {
		ParametrosSalario parametro = new ParametrosSalario(2500f);
		parametro.setDataInicioColaborador(getDataInicioFuncionario());

		Salario salario = calcular.calcular(parametro);

		assertEquals(createMonetaryBigDecimal(152.68f), salario.getDescontoInss());
		assertEquals(BIG_DECIMAL_0, salario.getDescontoIrpf());
		assertEquals(createMonetaryBigDecimal(1543.75f), salario.getValorLiquido());
	}

	@Test
	public void testCalcularSalarioParcialSalario3000() {
		ParametrosSalario parametro = new ParametrosSalario(3000f);
		parametro.setDataInicioColaborador(getDataInicioFuncionario());

		Salario salario = calcular.calcular(parametro);

		assertEquals(createMonetaryBigDecimal(183.21f), salario.getDescontoInss());
		assertEquals(BIG_DECIMAL_0, salario.getDescontoIrpf());
		assertEquals(createMonetaryBigDecimal(1852.50f), salario.getValorLiquido());
	}

	@Test
	public void testCalcularSalarioParcialSalario3500() {
		ParametrosSalario parametro = new ParametrosSalario(3500f);
		parametro.setDataInicioColaborador(getDataInicioFuncionario());

		Salario salario = calcular.calcular(parametro);

		assertEquals(createMonetaryBigDecimal(213.75f), salario.getDescontoInss());
		assertEquals(createMonetaryBigDecimal(19.29f), salario.getDescontoIrpf());
		assertEquals(createMonetaryBigDecimal(2141.96f), salario.getValorLiquido());
	}

	@Test
	public void testCalcularSalarioParcialSalario4000() {
		ParametrosSalario parametro = new ParametrosSalario(4000f);
		parametro.setDataInicioColaborador(getDataInicioFuncionario());

		Salario salario = calcular.calcular(parametro);

		assertEquals(createMonetaryBigDecimal(298.57f), salario.getDescontoInss());
		assertEquals(createMonetaryBigDecimal(38.38f), salario.getDescontoIrpf());
		assertEquals(createMonetaryBigDecimal(2377.34f), salario.getValorLiquido());
	}

	@Test
	public void testCalcularSalarioParcialSalario4500() {
		ParametrosSalario parametro = new ParametrosSalario(4500f);
		parametro.setDataInicioColaborador(getDataInicioFuncionario());

		Salario salario = calcular.calcular(parametro);

		assertEquals(createMonetaryBigDecimal(335.89f), salario.getDescontoInss());
		assertEquals(createMonetaryBigDecimal(61.03f), salario.getDescontoIrpf());
		assertEquals(createMonetaryBigDecimal(2656.65f), salario.getValorLiquido());
	}

	@Test
	public void testCalcularSalarioParcialSalario5000() {
		ParametrosSalario parametro = new ParametrosSalario(5000f);
		parametro.setDataInicioColaborador(getDataInicioFuncionario());

		Salario salario = calcular.calcular(parametro);

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
