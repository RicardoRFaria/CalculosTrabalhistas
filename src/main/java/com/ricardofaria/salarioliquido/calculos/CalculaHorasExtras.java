package com.ricardofaria.salarioliquido.calculos;

import static com.ricardofaria.salarioliquido.util.PrecisionUtil.*;
import static com.ricardofaria.salarioliquido.util.TempoUtil.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;

import com.ricardofaria.salarioliquido.model.HoraExtra;
import com.ricardofaria.salarioliquido.util.TempoUtil;

public class CalculaHorasExtras {
	
	public static BigDecimal calcularValorHoraExtra(BigDecimal salarioBruto, float quantidadeHorasSemanais, float porcentagemAdicionalHoraExtra) {
		if (quantidadeHorasSemanais <= 0) {
			throw new IllegalArgumentException("A quantidade de horas semanais não pode ser igual ou menor que zero. Valor atual: " + quantidadeHorasSemanais);
		}
		if (porcentagemAdicionalHoraExtra < 50) {
			throw new IllegalArgumentException("A porcentagem adicional de hora extra não pode ser menor que 50%. Valor atual: " + porcentagemAdicionalHoraExtra);
		}
		BigDecimal quantidadeHorasMensais = createMonetaryBigDecimal(calcularHorasMensaisCLT(quantidadeHorasSemanais));
		
		BigDecimal salarioPorHora = salarioBruto.divide(quantidadeHorasMensais, 4, RoundingMode.HALF_EVEN);
		BigDecimal porcentagemMultiplicacao = new BigDecimal(1 + (porcentagemAdicionalHoraExtra / 100));
		BigDecimal horaExtra = salarioPorHora.multiply(porcentagemMultiplicacao);
		
		return changeToMonetaryBidecimal(horaExtra);
	}
	
	public static HoraExtra calcularTotalHorasExtras(BigDecimal valorPorHora, String tempoDeHoraExtra, Date mes) {
		BigDecimal quantidadeHorasMultiplicacao = new BigDecimal(hoursToFloat(tempoDeHoraExtra));
		BigDecimal valorTotalDeHoras = valorPorHora.multiply(quantidadeHorasMultiplicacao);
		
		BigDecimal valorDSR = calcularDSRHoraExtra(valorTotalDeHoras, mes);
		BigDecimal valorTotal = changeToMonetaryBidecimal(valorTotalDeHoras.add(valorDSR));
		
		return new HoraExtra(valorPorHora, tempoDeHoraExtra, valorTotal, valorDSR);
	}
	
	public static BigDecimal calcularDSRHoraExtra(BigDecimal valorTotalDeHorasExtras, Date mes) {
		int diasUteis = TempoUtil.calcularDiasUteisNoMes(mes);
		int diasDSR = TempoUtil.calcularDiasDeFolgaNoMes(mes);
		
		BigDecimal valorDSRDiaUtil = valorTotalDeHorasExtras.divide(new BigDecimal(diasUteis), 4, RoundingMode.HALF_EVEN);
		BigDecimal valorDSR = valorDSRDiaUtil.multiply(new BigDecimal(diasDSR));
		
		return changeToMonetaryBidecimal(valorDSR);
	}

}
