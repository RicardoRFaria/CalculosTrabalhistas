package com.ricardofaria.salarioliquido.calculos;

import static com.ricardofaria.salarioliquido.calculos.ConstantesDeValores.VALOR_SALARIO_MINIMO;
import static com.ricardofaria.salarioliquido.util.PrecisionUtil.createMonetaryBigDecimal;
import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import com.ricardofaria.salarioliquido.model.constantes.TipoFerias;
import com.ricardofaria.salarioliquido.model.input.ParametrosFerias;
import com.ricardofaria.salarioliquido.model.resultado.Ferias;

public class CalcularFeriasTest {

	private static final BigDecimal BIG_DECIMAL_0 = createMonetaryBigDecimal(0.0f);

	private CalculaFerias calcular;

	@Before
	public void init() {
		this.calcular = new CalculaFerias();
	}

	@Test
	public void testCalcularFeriasSalarioMinimo() {
		ParametrosFerias parametro = new ParametrosFerias(VALOR_SALARIO_MINIMO);
		Ferias ferias = calcular.calcular(parametro);


		assertEquals(createMonetaryBigDecimal(293.33f), ferias.getValorFerias());
		assertEquals(createMonetaryBigDecimal(93.87f), ferias.getDescontoInss());
		assertEquals(BIG_DECIMAL_0, ferias.getDescontoIrpf());
		assertEquals(BIG_DECIMAL_0, ferias.getAbonoPecuniario());
		assertEquals(createMonetaryBigDecimal(1079.47f), ferias.getValorLiquido());
	}

	@Test
	public void testCalcularFerias1500() {
		ParametrosFerias parametro = new ParametrosFerias(1500);
		Ferias ferias = calcular.calcular(parametro);


		assertEquals(createMonetaryBigDecimal(500.0f), ferias.getValorFerias());
		assertEquals(createMonetaryBigDecimal(180.0f), ferias.getDescontoInss());
		assertEquals(BIG_DECIMAL_0, ferias.getDescontoIrpf());
		assertEquals(BIG_DECIMAL_0, ferias.getAbonoPecuniario());
		assertEquals(createMonetaryBigDecimal(1820.0f), ferias.getValorLiquido());
	}

	@Test
	public void testCalcularFerias2000() {
		ParametrosFerias parametro = new ParametrosFerias(2000);
		Ferias ferias = calcular.calcular(parametro);

		assertEquals(createMonetaryBigDecimal(666.67f), ferias.getValorFerias());
		assertEquals(createMonetaryBigDecimal(240.00f), ferias.getDescontoInss());
		assertEquals(createMonetaryBigDecimal(39.20f), ferias.getDescontoIrpf());
		assertEquals(BIG_DECIMAL_0, ferias.getAbonoPecuniario());
		assertEquals(createMonetaryBigDecimal(2387.47f), ferias.getValorLiquido());
	}

	@Test
	public void testCalcularFerias2500() {
		ParametrosFerias parametro = new ParametrosFerias(2500);
		Ferias ferias = calcular.calcular(parametro);

		assertEquals(createMonetaryBigDecimal(833.33f), ferias.getValorFerias());
		assertEquals(createMonetaryBigDecimal(366.67f), ferias.getDescontoInss());
		assertEquals(createMonetaryBigDecimal(90.20f), ferias.getDescontoIrpf());
		assertEquals(BIG_DECIMAL_0, ferias.getAbonoPecuniario());
		assertEquals(createMonetaryBigDecimal(2876.47f), ferias.getValorLiquido());
	}

	@Test
	public void testCalcularFerias3000() {
		ParametrosFerias parametro = new ParametrosFerias(3000);
		Ferias ferias = calcular.calcular(parametro);

		assertEquals(createMonetaryBigDecimal(1000.00f), ferias.getValorFerias());
		assertEquals(createMonetaryBigDecimal(440.00f), ferias.getDescontoInss());
		assertEquals(createMonetaryBigDecimal(179.20f), ferias.getDescontoIrpf());
		assertEquals(BIG_DECIMAL_0, ferias.getAbonoPecuniario());
		assertEquals(createMonetaryBigDecimal(3380.80f), ferias.getValorLiquido());
	}

	@Test
	public void testCalcularFerias3500() {
		ParametrosFerias parametro = new ParametrosFerias(3500);
		Ferias ferias = calcular.calcular(parametro);

		assertEquals(createMonetaryBigDecimal(1166.67f), ferias.getValorFerias());
		assertEquals(createMonetaryBigDecimal(513.33f), ferias.getDescontoInss());
		assertEquals(createMonetaryBigDecimal(298.37f), ferias.getDescontoIrpf());
		assertEquals(BIG_DECIMAL_0, ferias.getAbonoPecuniario());
		assertEquals(createMonetaryBigDecimal(3854.96f), ferias.getValorLiquido());
	}

	@Test
	public void testCalcularFerias4000() {
		ParametrosFerias parametro = new ParametrosFerias(4000);
		Ferias ferias = calcular.calcular(parametro);

		assertEquals(createMonetaryBigDecimal(1333.33f), ferias.getValorFerias());
		assertEquals(createMonetaryBigDecimal(586.67f), ferias.getDescontoInss());
		assertEquals(createMonetaryBigDecimal(435.97f), ferias.getDescontoIrpf());
		assertEquals(BIG_DECIMAL_0, ferias.getAbonoPecuniario());
		assertEquals(createMonetaryBigDecimal(4310.69f), ferias.getValorLiquido());
	}

	@Test
	public void testCalcularFerias4500() {
		ParametrosFerias parametro = new ParametrosFerias(4500);
		Ferias ferias = calcular.calcular(parametro);

		assertEquals(createMonetaryBigDecimal(1500.00f), ferias.getValorFerias());
		assertEquals(createMonetaryBigDecimal(608.44f), ferias.getDescontoInss());
		assertEquals(createMonetaryBigDecimal(613.32f), ferias.getDescontoIrpf());
		assertEquals(BIG_DECIMAL_0, ferias.getAbonoPecuniario());
		assertEquals(createMonetaryBigDecimal(4778.24f), ferias.getValorLiquido());
	}

	@Test
	public void testCalcularFerias5000() {
		ParametrosFerias parametro = new ParametrosFerias(5000);
		Ferias ferias = calcular.calcular(parametro);

		assertEquals(createMonetaryBigDecimal(1666.66f), ferias.getValorFerias());
		assertEquals(createMonetaryBigDecimal(608.44f), ferias.getDescontoInss());
		assertEquals(createMonetaryBigDecimal(796.65f), ferias.getDescontoIrpf());
		assertEquals(BIG_DECIMAL_0, ferias.getAbonoPecuniario());
		assertEquals(createMonetaryBigDecimal(5261.57f), ferias.getValorLiquido());
	}

	@Test
	public void testCalcular20DiasDeFeriasSalarioMinimo() {
		ParametrosFerias parametro = new ParametrosFerias(VALOR_SALARIO_MINIMO);
		parametro.setTipoFerias(TipoFerias.DIAS_20);
		Ferias ferias = calcular.calcular(parametro);


		assertEquals(createMonetaryBigDecimal(195.56f), ferias.getValorFerias());
		assertEquals(createMonetaryBigDecimal(62.58f), ferias.getDescontoInss());
		assertEquals(BIG_DECIMAL_0, ferias.getDescontoIrpf());
		assertEquals(createMonetaryBigDecimal(391.11f), ferias.getAbonoPecuniario());
		assertEquals(createMonetaryBigDecimal(1110.76f), ferias.getValorLiquido());
	}

	@Test
	public void testCalcular20DiasDeFerias1500() {
		ParametrosFerias parametro = new ParametrosFerias(1500);
		parametro.setTipoFerias(TipoFerias.DIAS_20);
		Ferias ferias = calcular.calcular(parametro);

		assertEquals(createMonetaryBigDecimal(333.33f), ferias.getValorFerias());
		assertEquals(createMonetaryBigDecimal(106.67f), ferias.getDescontoInss());
		assertEquals(BIG_DECIMAL_0, ferias.getDescontoIrpf());
		assertEquals(createMonetaryBigDecimal(666.67f), ferias.getAbonoPecuniario());
		assertEquals(createMonetaryBigDecimal(1893.34f), ferias.getValorLiquido());
	}

	@Test
	public void testCalcular20DiasDeFerias2000() {
		ParametrosFerias parametro = new ParametrosFerias(2000);
		parametro.setTipoFerias(TipoFerias.DIAS_20);
		Ferias ferias = calcular.calcular(parametro);

		assertEquals(createMonetaryBigDecimal(444.44f), ferias.getValorFerias());
		assertEquals(createMonetaryBigDecimal(160.00f), ferias.getDescontoInss());
		assertEquals(createMonetaryBigDecimal(0.00f), ferias.getDescontoIrpf());
		assertEquals(createMonetaryBigDecimal(888.89f), ferias.getAbonoPecuniario());
		assertEquals(createMonetaryBigDecimal(2506.66f), ferias.getValorLiquido());
	}

	@Test
	public void testCalcular20DiasDeFerias2500() {
		ParametrosFerias parametro = new ParametrosFerias(2500);
		parametro.setTipoFerias(TipoFerias.DIAS_20);
		Ferias ferias = calcular.calcular(parametro);

		assertEquals(createMonetaryBigDecimal(555.56f), ferias.getValorFerias());
		assertEquals(createMonetaryBigDecimal(200.00f), ferias.getDescontoInss());
		assertEquals(createMonetaryBigDecimal(8.87f), ferias.getDescontoIrpf());
		assertEquals(createMonetaryBigDecimal(1111.11f), ferias.getAbonoPecuniario());
		assertEquals(createMonetaryBigDecimal(3124.47f), ferias.getValorLiquido());
	}

	@Test
	public void testCalcular20DiasDeFerias3000() {
		ParametrosFerias parametro = new ParametrosFerias(3000);
		parametro.setTipoFerias(TipoFerias.DIAS_20);
		Ferias ferias = calcular.calcular(parametro);

		assertEquals(createMonetaryBigDecimal(666.67f), ferias.getValorFerias());
		assertEquals(createMonetaryBigDecimal(240.00f), ferias.getDescontoInss());
		assertEquals(createMonetaryBigDecimal(39.20f), ferias.getDescontoIrpf());
		assertEquals(createMonetaryBigDecimal(1333.33f), ferias.getAbonoPecuniario());
		assertEquals(createMonetaryBigDecimal(3720.80f), ferias.getValorLiquido());
	}

	@Test
	public void testCalcular20DiasDeFerias3500() {
		ParametrosFerias parametro = new ParametrosFerias(3500);
		parametro.setTipoFerias(TipoFerias.DIAS_20);
		Ferias ferias = calcular.calcular(parametro);

		assertEquals(createMonetaryBigDecimal(777.78f), ferias.getValorFerias());
		assertEquals(createMonetaryBigDecimal(342.22f), ferias.getDescontoInss());
		assertEquals(createMonetaryBigDecimal(64.87f), ferias.getDescontoIrpf());
		assertEquals(createMonetaryBigDecimal(1555.56f), ferias.getAbonoPecuniario());
		assertEquals(createMonetaryBigDecimal(4259.58f), ferias.getValorLiquido());
	}

	@Test
	public void testCalcular20DiasDeFerias4000() {
		ParametrosFerias parametro = new ParametrosFerias(4000);
		parametro.setTipoFerias(TipoFerias.DIAS_20);
		Ferias ferias = calcular.calcular(parametro);

		assertEquals(createMonetaryBigDecimal(888.89f), ferias.getValorFerias());
		assertEquals(createMonetaryBigDecimal(391.11f), ferias.getDescontoInss());
		assertEquals(createMonetaryBigDecimal(119.87f), ferias.getDescontoIrpf());
		assertEquals(createMonetaryBigDecimal(1777.78f), ferias.getAbonoPecuniario());
		assertEquals(createMonetaryBigDecimal(4822.36f), ferias.getValorLiquido());
	}

	@Test
	public void testCalcular20DiasDeFerias4500() {
		ParametrosFerias parametro = new ParametrosFerias(4500);
		parametro.setTipoFerias(TipoFerias.DIAS_20);
		Ferias ferias = calcular.calcular(parametro);

		assertEquals(createMonetaryBigDecimal(1000.00f), ferias.getValorFerias());
		assertEquals(createMonetaryBigDecimal(440.00f), ferias.getDescontoInss());
		assertEquals(createMonetaryBigDecimal(179.20f), ferias.getDescontoIrpf());
		assertEquals(createMonetaryBigDecimal(2000.0f), ferias.getAbonoPecuniario());
		assertEquals(createMonetaryBigDecimal(5380.80f), ferias.getValorLiquido());
	}

	@Test
	public void testCalcular20DiasDeFerias5000() {
		ParametrosFerias parametro = new ParametrosFerias(5000);
		parametro.setTipoFerias(TipoFerias.DIAS_20);
		Ferias ferias = calcular.calcular(parametro);

		assertEquals(createMonetaryBigDecimal(1111.11f), ferias.getValorFerias());
		assertEquals(createMonetaryBigDecimal(488.89f), ferias.getDescontoInss());
		assertEquals(createMonetaryBigDecimal(253.87f), ferias.getDescontoIrpf());
		assertEquals(createMonetaryBigDecimal(2222.22f), ferias.getAbonoPecuniario());
		assertEquals(createMonetaryBigDecimal(5923.90f), ferias.getValorLiquido());
	}

	@Test
	public void testCalcular15DiasDeFeriasSalarioMinimo() {
		ParametrosFerias parametro = new ParametrosFerias(VALOR_SALARIO_MINIMO);
		parametro.setTipoFerias(TipoFerias.DIAS_15);
		Ferias ferias = calcular.calcular(parametro);


		assertEquals(createMonetaryBigDecimal(146.67f), ferias.getValorFerias());
		assertEquals(createMonetaryBigDecimal(46.93f), ferias.getDescontoInss());
		assertEquals(BIG_DECIMAL_0, ferias.getDescontoIrpf());
		assertEquals(createMonetaryBigDecimal(539.73f), ferias.getValorLiquido());
	}

	@Test
	public void testCalcular15DiasDeFerias1500() {
		ParametrosFerias parametro = new ParametrosFerias(1500);
		parametro.setTipoFerias(TipoFerias.DIAS_15);
		Ferias ferias = calcular.calcular(parametro);

		assertEquals(createMonetaryBigDecimal(250.00f), ferias.getValorFerias());
		assertEquals(createMonetaryBigDecimal(80.00f), ferias.getDescontoInss());
		assertEquals(BIG_DECIMAL_0, ferias.getDescontoIrpf());
		assertEquals(createMonetaryBigDecimal(920.00f), ferias.getValorLiquido());
	}

	@Test
	public void testCalcular15DiasDeFerias2000() {
		ParametrosFerias parametro = new ParametrosFerias(2000);
		parametro.setTipoFerias(TipoFerias.DIAS_15);
		Ferias ferias = calcular.calcular(parametro);

		assertEquals(createMonetaryBigDecimal(333.33f), ferias.getValorFerias());
		assertEquals(createMonetaryBigDecimal(106.67f), ferias.getDescontoInss());
		assertEquals(createMonetaryBigDecimal(0.00f), ferias.getDescontoIrpf());
		assertEquals(createMonetaryBigDecimal(1226.67f), ferias.getValorLiquido());
	}

	@Test
	public void testCalcular15DiasDeFerias2500() {
		ParametrosFerias parametro = new ParametrosFerias(2500);
		parametro.setTipoFerias(TipoFerias.DIAS_15);
		Ferias ferias = calcular.calcular(parametro);

		assertEquals(createMonetaryBigDecimal(416.67f), ferias.getValorFerias());
		assertEquals(createMonetaryBigDecimal(150.00f), ferias.getDescontoInss());
		assertEquals(createMonetaryBigDecimal(0.00f), ferias.getDescontoIrpf());
		assertEquals(createMonetaryBigDecimal(1516.67f), ferias.getValorLiquido());
	}

	@Test
	public void testCalcular15DiasDeFerias3000() {
		ParametrosFerias parametro = new ParametrosFerias(3000);
		parametro.setTipoFerias(TipoFerias.DIAS_15);
		Ferias ferias = calcular.calcular(parametro);

		assertEquals(createMonetaryBigDecimal(500.00f), ferias.getValorFerias());
		assertEquals(createMonetaryBigDecimal(180.00f), ferias.getDescontoInss());
		assertEquals(createMonetaryBigDecimal(0.00f), ferias.getDescontoIrpf());
		assertEquals(createMonetaryBigDecimal(1820.00f), ferias.getValorLiquido());
	}

	@Test
	public void testCalcular15DiasDeFerias3500() {
		ParametrosFerias parametro = new ParametrosFerias(3500);
		parametro.setTipoFerias(TipoFerias.DIAS_15);
		Ferias ferias = calcular.calcular(parametro);

		assertEquals(createMonetaryBigDecimal(583.33f), ferias.getValorFerias());
		assertEquals(createMonetaryBigDecimal(210.00f), ferias.getDescontoInss());
		assertEquals(createMonetaryBigDecimal(16.45f), ferias.getDescontoIrpf());
		assertEquals(createMonetaryBigDecimal(2106.88f), ferias.getValorLiquido());
	}

	@Test
	public void testCalcular15DiasDeFerias4000() {
		ParametrosFerias parametro = new ParametrosFerias(4000);
		parametro.setTipoFerias(TipoFerias.DIAS_15);
		Ferias ferias = calcular.calcular(parametro);

		assertEquals(createMonetaryBigDecimal(666.67f), ferias.getValorFerias());
		assertEquals(createMonetaryBigDecimal(240.00f), ferias.getDescontoInss());
		assertEquals(createMonetaryBigDecimal(39.20f), ferias.getDescontoIrpf());
		assertEquals(createMonetaryBigDecimal(2387.47f), ferias.getValorLiquido());
	}

	@Test
	public void testCalcular15DiasDeFerias4500() {
		ParametrosFerias parametro = new ParametrosFerias(4500);
		parametro.setTipoFerias(TipoFerias.DIAS_15);
		Ferias ferias = calcular.calcular(parametro);

		assertEquals(createMonetaryBigDecimal(750.00f), ferias.getValorFerias());
		assertEquals(createMonetaryBigDecimal(330.00f), ferias.getDescontoInss());
		assertEquals(createMonetaryBigDecimal(57.45f), ferias.getDescontoIrpf());
		assertEquals(createMonetaryBigDecimal(2612.55f), ferias.getValorLiquido());
	}

	@Test
	public void testCalcular15DiasDeFerias5000() {
		ParametrosFerias parametro = new ParametrosFerias(5000);
		parametro.setTipoFerias(TipoFerias.DIAS_15);
		Ferias ferias = calcular.calcular(parametro);

		assertEquals(createMonetaryBigDecimal(833.33f), ferias.getValorFerias());
		assertEquals(createMonetaryBigDecimal(366.67f), ferias.getDescontoInss());
		assertEquals(createMonetaryBigDecimal(90.20f), ferias.getDescontoIrpf());
		assertEquals(createMonetaryBigDecimal(2876.47f), ferias.getValorLiquido());
	}

}
