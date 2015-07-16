package com.ricardofaria.salarioliquido.util;

import static org.junit.Assert.assertEquals;

import java.util.Calendar;

import org.junit.Test;

public class TempoUtilTest {
	
	
	@Test
	public void testContagemJulho2015() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(2015, Calendar.JULY, 10, 0, 0, 1);
		
		int quantidadeDiasDeFolga = TempoUtil.calcularDiasDeFolgaNoMes(calendar.getTime());
		
		assertEquals(4, quantidadeDiasDeFolga);
	}
	
	@Test
	public void testContagemMaio2015() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(2015, Calendar.MAY, 10, 0, 0, 1);
		
		int quantidadeDiasDeFolga = TempoUtil.calcularDiasDeFolgaNoMes(calendar.getTime());
		
		assertEquals(5, quantidadeDiasDeFolga);
	}
	
	@Test
	public void testHorasMensais44HorasSemanais() {
		float horasMensais = TempoUtil.calcularHorasMensaisCLT(44);
		assertEquals(220, horasMensais, 0.01);
	}
	
	@Test
	public void testHorasMensais40HorasSemanais() {
		float horasMensais = TempoUtil.calcularHorasMensaisCLT(40);
		assertEquals(200, horasMensais, 0.01);
	}
	
	@Test
	public void testConverterHorasParaFloat10Horas() {
		String horasEmString = "10:00";
		float horasEmFloat = TempoUtil.hoursToFloat(horasEmString);
		
		assertEquals(10f, horasEmFloat, 0.01f);
		
		horasEmString = "10";
		horasEmFloat = TempoUtil.hoursToFloat(horasEmString);
		
		assertEquals(10f, horasEmFloat, 0.01f);
	}
	
	@Test
	public void testConverterHorasParaFloat10HorasE55Minutos() {
		String horasEmString = "10:55";
		float horasEmFloat = TempoUtil.hoursToFloat(horasEmString);
		
		assertEquals(10.917f, horasEmFloat, 0.01f);
	}
	
	@Test
	public void testConverterHorasParaFloatMeiaHora() {
		String horasEmString = "0:30";
		float horasEmFloat = TempoUtil.hoursToFloat(horasEmString);
		
		assertEquals(0.5f, horasEmFloat, 0.01f);
	}
	

}
