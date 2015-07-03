package com.ricardofaria.salarioliquido.util;

import static org.junit.Assert.*;

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

}
