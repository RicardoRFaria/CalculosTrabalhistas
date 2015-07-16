package com.ricardofaria.salarioliquido.calculos;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import com.ricardofaria.salarioliquido.model.HoraExtra;
import static com.ricardofaria.salarioliquido.util.PrecisionUtil.*;

public class CalcularHoraExtraTest {
	
	private Calcular calcular;
	
	@Before
	public void init() {
		this.calcular = new Calcular();
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
		
		HoraExtra horaExtra = CalculaHorasExtras.calcularTotalHorasExtras(valorPorHoraExtra, quantidadeDeHoras);
		
		assertEquals(valorPorHoraExtra, horaExtra.getValorTotal());
	}
	
	@Test
	public void testCalcularHoraExtraDeUmaHoraEMeia() {
		BigDecimal valorPorHoraExtra = createMonetaryBigDecimal("34.09");
		String quantidadeDeHoras = "01:30";
		
		HoraExtra horaExtra = CalculaHorasExtras.calcularTotalHorasExtras(valorPorHoraExtra, quantidadeDeHoras);
		
		assertEquals(createMonetaryBigDecimal("51.14"), horaExtra.getValorTotal());
	}
	
	@Test
	public void testCalcularHoraExtraDe100Horas() {
		BigDecimal valorPorHoraExtra = createMonetaryBigDecimal("34.09");
		String quantidadeDeHoras = "100:00";
		
		HoraExtra horaExtra = CalculaHorasExtras.calcularTotalHorasExtras(valorPorHoraExtra, quantidadeDeHoras);
		
		assertEquals(createMonetaryBigDecimal("3409.00"), horaExtra.getValorTotal());
	}

}
