package com.ricardofaria.salarioliquido.calculos;

import static com.ricardofaria.salarioliquido.util.PrecisionUtil.createMonetaryBigDecimal;
import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

import com.ricardofaria.salarioliquido.model.input.ParametrosHoraExtra;
import com.ricardofaria.salarioliquido.model.resultado.HoraExtra;

public class CalcularHoraExtraTest {
	
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
		ParametrosHoraExtra parametros = new ParametrosHoraExtra(createMonetaryBigDecimal("5000"));
		parametros.setQuantidadeHorasSemanais(44);
		parametros.setPorcentagemAdicionalHoraExtra(30);
		CalculaHorasExtras.calcularValorHoraExtra(parametros);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testCalcularValorHoraExtraSemHorasSemanais() {
		ParametrosHoraExtra parametros = new ParametrosHoraExtra(createMonetaryBigDecimal("5000"));
		parametros.setQuantidadeHorasSemanais(0);
		CalculaHorasExtras.calcularValorHoraExtra(parametros);
	}
	
	@Test
	public void testCalcularValorHoraExtraSalario5000EAdicionalDe50Porcento() {
		ParametrosHoraExtra parametros = new ParametrosHoraExtra(createMonetaryBigDecimal("5000"));
		BigDecimal valorHoras = CalculaHorasExtras.calcularValorHoraExtra(parametros);
		
		assertEquals(createMonetaryBigDecimal("34.09"), valorHoras);
	}
	
	@Test
	public void testCalcularHoraExtraDeUmaHora() {
		ParametrosHoraExtra parametros = new ParametrosHoraExtra(createMonetaryBigDecimal("5000"));
		parametros.setTempoDeHoraExtra("01:00");	
		parametros.setMesReferencia(getDateFevereiro2015());
		
		HoraExtra horaExtra = CalculaHorasExtras.calcularTotalHorasExtras(parametros);
		
		assertEquals(createMonetaryBigDecimal("39.77"), horaExtra.getValorTotal());
	}
	
	@Test
	public void testCalcularHoraExtraDeUmaHoraEMeia() {
		ParametrosHoraExtra parametros = new ParametrosHoraExtra(createMonetaryBigDecimal("5000"));
		parametros.setTempoDeHoraExtra("01:30");	
		parametros.setMesReferencia(getDateFevereiro2015());
		
		HoraExtra horaExtra = CalculaHorasExtras.calcularTotalHorasExtras(parametros);
		
		assertEquals(createMonetaryBigDecimal("59.66"), horaExtra.getValorTotal());
	}
	
	@Test
	public void testCalcularHoraExtraDe100Horas() {
		ParametrosHoraExtra parametros = new ParametrosHoraExtra(createMonetaryBigDecimal("5000"));
		parametros.setTempoDeHoraExtra("100:00");
		parametros.setMesReferencia(getDateFevereiro2015());
		
		HoraExtra horaExtra = CalculaHorasExtras.calcularTotalHorasExtras(parametros);
		
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
