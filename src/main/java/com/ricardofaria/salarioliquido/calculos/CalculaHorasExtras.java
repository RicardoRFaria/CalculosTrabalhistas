package com.ricardofaria.salarioliquido.calculos;

import static com.ricardofaria.salarioliquido.util.PrecisionUtil.*;
import static com.ricardofaria.salarioliquido.util.TempoUtil.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;

import com.ricardofaria.salarioliquido.model.input.ParametrosHoraExtra;
import com.ricardofaria.salarioliquido.model.resultado.HoraExtra;
import com.ricardofaria.salarioliquido.util.TempoUtil;

public class CalculaHorasExtras {
	
	public static BigDecimal calcularValorHoraExtra(ParametrosHoraExtra parametros) {
		if (parametros.getQuantidadeHorasSemanais() <= 0) {
			throw new IllegalArgumentException("A quantidade de horas semanais não pode ser igual ou menor que zero. Valor atual: " + parametros.getQuantidadeHorasSemanais());
		}
		if (parametros.getPorcentagemAdicionalHoraExtra() < 50) {
			throw new IllegalArgumentException("A porcentagem adicional de hora extra não pode ser menor que 50%. Valor atual: " + parametros.getPorcentagemAdicionalHoraExtra());
		}
		BigDecimal quantidadeHorasMensais = createMonetaryBigDecimal(calcularHorasMensaisCLT(parametros.getQuantidadeHorasSemanais()));
		
		BigDecimal salarioPorHora = parametros.getSalarioBruto().divide(quantidadeHorasMensais, 4, RoundingMode.HALF_EVEN);
		BigDecimal porcentagemMultiplicacao = new BigDecimal(1 + (parametros.getPorcentagemAdicionalHoraExtra() / 100));
		BigDecimal horaExtra = salarioPorHora.multiply(porcentagemMultiplicacao);
		
		return changeToMonetaryBidecimal(horaExtra);
	}
	
	public static HoraExtra calcularTotalHorasExtras(ParametrosHoraExtra parametros) {
		BigDecimal quantidadeHorasMultiplicacao = new BigDecimal(parametros.getTempoDeHoraExtraParaMultiplicacao());
		BigDecimal valorPorHora = calcularValorHoraExtra(parametros);
		BigDecimal valorTotalDeHoras = valorPorHora.multiply(quantidadeHorasMultiplicacao);
		
		BigDecimal valorDSR = calcularDSRHoraExtra(valorTotalDeHoras, parametros.getMesReferencia());
		BigDecimal valorTotal = changeToMonetaryBidecimal(valorTotalDeHoras.add(valorDSR));
		
		return new HoraExtra(valorPorHora, parametros.getTempoDeHoraExtra(), valorTotal, valorDSR);
	}
	
	static BigDecimal calcularDSRHoraExtra(BigDecimal valorTotalDeHorasExtras, Date mes) {
		int diasUteis = TempoUtil.calcularDiasUteisNoMes(mes);
		int diasDSR = TempoUtil.calcularDiasDeFolgaNoMes(mes);
		
		BigDecimal valorDSRDiaUtil = valorTotalDeHorasExtras.divide(new BigDecimal(diasUteis), 4, RoundingMode.HALF_EVEN);
		BigDecimal valorDSR = valorDSRDiaUtil.multiply(new BigDecimal(diasDSR));
		
		return changeToMonetaryBidecimal(valorDSR);
	}

}
