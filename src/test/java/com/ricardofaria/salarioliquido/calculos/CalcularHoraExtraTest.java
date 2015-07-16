package com.ricardofaria.salarioliquido.calculos;

import static com.ricardofaria.salarioliquido.util.PrecisionUtil.createMonetaryBigDecimal;
import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import com.ricardofaria.salarioliquido.model.resultado.HoraExtra;

public class CalcularHoraExtraTest {
	
	private Calcular calcular;
	
	@Before
	public void init() {
		this.calcular = new Calcular();
	}	
	
	public Date getDateFevereiro2015() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(2015, Calendar.FEBRUARY, 1, 0, 0, 0);
		return calendar.getTime();
	}
	
	public Date getDateMarco2015() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(2015, Calendar.MARCH, 1, 0, 0, 0);
		return calendar.getTime();
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCalcularValorHoraExtraComAdicionalAbaixoDe50Porcento() {
		BigDecimal salarioBruto = createMonetaryBigDecimal("5000");
		CalculaHorasExtras.calcularValorHoraExtra(salarioBruto, 44f, 30);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testCalcularValorHoraExtraSemHorasSemanais() {
		BigDecimal salarioBruto = createMonetaryBigDecimal("5000");
		CalculaHorasExtras.calcularValorHoraExtra(salarioBruto, 0, 50);
	}
	
	@Test
	public void testCalcularValorHoraExtraSalario5000EAdicionalDe50Porcento() {
		BigDecimal salarioBruto = createMonetaryBigDecimal("5000");
		float quantidadeHorasSemanais = 44f;
		float porcentagemAdicionalHoraExtra = 50;
		BigDecimal valorHoras = CalculaHorasExtras.calcularValorHoraExtra(salarioBruto, quantidadeHorasSemanais, porcentagemAdicionalHoraExtra);
		
		assertEquals(createMonetaryBigDecimal("34.09"), valorHoras);
	}
	
	@Test
	public void testCalcularHoraExtraDeUmaHora() {
		BigDecimal valorPorHoraExtra = createMonetaryBigDecimal("34.09");
		String quantidadeDeHoras = "01:00";
		
		
		
		HoraExtra horaExtra = CalculaHorasExtras.calcularTotalHorasExtras(valorPorHoraExtra, quantidadeDeHoras, getDateFevereiro2015());
		
		assertEquals(createMonetaryBigDecimal("39.77"), horaExtra.getValorTotal());
	}
	
	@Test
	public void testCalcularHoraExtraDeUmaHoraEMeia() {
		BigDecimal valorPorHoraExtra = createMonetaryBigDecimal("34.09");
		String quantidadeDeHoras = "01:30";
		
		HoraExtra horaExtra = CalculaHorasExtras.calcularTotalHorasExtras(valorPorHoraExtra, quantidadeDeHoras, getDateFevereiro2015());
		
		assertEquals(createMonetaryBigDecimal("59.66"), horaExtra.getValorTotal());
	}
	
	@Test
	public void testCalcularHoraExtraDe100Horas() {
		BigDecimal valorPorHoraExtra = createMonetaryBigDecimal("34.09");
		String quantidadeDeHoras = "100:00";
		
		HoraExtra horaExtra = CalculaHorasExtras.calcularTotalHorasExtras(valorPorHoraExtra, quantidadeDeHoras, getDateFevereiro2015());
		
		assertEquals(createMonetaryBigDecimal("3977.17"), horaExtra.getValorTotal());
	}
	
	@Test
	public void testCalcularDSRFevereiro2015() {
		BigDecimal valorHoraExtra = createMonetaryBigDecimal("100.00");
		BigDecimal valorDSR = CalculaHorasExtras.calcularDSRHoraExtra(valorHoraExtra, getDateFevereiro2015());
		
		assertEquals(createMonetaryBigDecimal("16.67"), valorDSR);
	}
	
	@Test
	public void testCalcularDSRMarch2015() {
		BigDecimal valorHoraExtra = createMonetaryBigDecimal("100.00");
		BigDecimal valorDSR = CalculaHorasExtras.calcularDSRHoraExtra(valorHoraExtra, getDateMarco2015());
		
		assertEquals(createMonetaryBigDecimal("19.23"), valorDSR);
	}

}
