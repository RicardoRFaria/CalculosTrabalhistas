package com.ricardofaria.salarioliquido.calculos;

import static com.ricardofaria.salarioliquido.calculos.CalculaDecimoTerceiro.calcularParcelaDois;
import static com.ricardofaria.salarioliquido.calculos.CalculaDecimoTerceiro.calcularParcelaUm;
import static com.ricardofaria.salarioliquido.calculos.ConstantesDeValores.*;
import static com.ricardofaria.salarioliquido.util.PrecisionUtil.createMonetaryBigDecimal;
import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import com.ricardofaria.salarioliquido.model.input.ParametrosDecimoTerceiro;
import com.ricardofaria.salarioliquido.model.resultado.DecimoTerceiro;

public class CalculaDecimoTerceiroTest {

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

		assertEquals(createMonetaryBigDecimal(70.40f), decimoTerceiro.getDescontoInss());
		assertEquals(createMonetaryBigDecimal(0.0f), decimoTerceiro.getDescontoIrpf());
		assertEquals(createMonetaryBigDecimal(440.00f), decimoTerceiro.getSalarioParcelaUm());
		assertEquals(createMonetaryBigDecimal(369.60f), decimoTerceiro.getSalarioParcelaDois());
	}

	@Test
	public void testCalcularDecimoTerceiroCompletoSalarioMinimoComAdicionalDePericulosidade() {
		float salarioBruto = VALOR_SALARIO_MINIMO;

		ParametrosDecimoTerceiro parametros = new ParametrosDecimoTerceiro(salarioBruto);
		parametros.setAdicionalDePericulosidade(true);
		DecimoTerceiro decimoTerceiro = calcular.calcular(parametros);

		assertEquals(createMonetaryBigDecimal(91.52f), decimoTerceiro.getDescontoInss());
		assertEquals(createMonetaryBigDecimal(0.0f), decimoTerceiro.getDescontoIrpf());
		assertEquals(createMonetaryBigDecimal(572.00f), decimoTerceiro.getSalarioParcelaUm());
		assertEquals(createMonetaryBigDecimal(480.48f), decimoTerceiro.getSalarioParcelaDois());
		assertEquals(createMonetaryBigDecimal(264.00f), decimoTerceiro.getAdicionalPericulosidade());
	}

	@Test
	public void testCalcularDecimoTerceiroCompletoSalario1500() {
		ParametrosDecimoTerceiro parametros = new ParametrosDecimoTerceiro(1500);
		DecimoTerceiro decimoTerceiro = calcular.calcular(parametros);

		assertEquals(createMonetaryBigDecimal(120.00f), decimoTerceiro.getDescontoInss());
		assertEquals(createMonetaryBigDecimal(0.0f), decimoTerceiro.getDescontoIrpf());
		assertEquals(createMonetaryBigDecimal(750.00f), decimoTerceiro.getSalarioParcelaUm());
		assertEquals(createMonetaryBigDecimal(630.00f), decimoTerceiro.getSalarioParcelaDois());
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

		assertEquals(createMonetaryBigDecimal(225.0f), decimoTerceiro.getDescontoInss());
		assertEquals(createMonetaryBigDecimal(27.82f), decimoTerceiro.getDescontoIrpf());
		assertEquals(createMonetaryBigDecimal(1250.00f), decimoTerceiro.getSalarioParcelaUm());
		assertEquals(createMonetaryBigDecimal(997.18f), decimoTerceiro.getSalarioParcelaDois());
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

		assertEquals(createMonetaryBigDecimal(550.00f), decimoTerceiro.getDescontoInss());
		assertEquals(createMonetaryBigDecimal(365.12f), decimoTerceiro.getDescontoIrpf());
		assertEquals(createMonetaryBigDecimal(2500.00f), decimoTerceiro.getSalarioParcelaUm());
		assertEquals(createMonetaryBigDecimal(1584.88f), decimoTerceiro.getSalarioParcelaDois());
	}

}
