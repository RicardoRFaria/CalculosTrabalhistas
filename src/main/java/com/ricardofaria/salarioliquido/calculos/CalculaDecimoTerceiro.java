package com.ricardofaria.salarioliquido.calculos;

import static com.ricardofaria.salarioliquido.util.PrecisionUtil.changeToMonetaryBidecimal;

import java.math.BigDecimal;

import com.ricardofaria.salarioliquido.model.input.ParametrosDecimoTerceiro;
import com.ricardofaria.salarioliquido.model.resultado.DecimoTerceiro;
import com.ricardofaria.salarioliquido.model.resultado.DecimoTerceiro.TIPO_DECIMO_TERCEIRO;
import com.ricardofaria.salarioliquido.util.ReduzSalarioPorData;

public class CalculaDecimoTerceiro extends CalculaRemuneracao {

	private static final BigDecimal METADE = new BigDecimal("2");

	public CalculaDecimoTerceiro() {
		super();
	}

	public static BigDecimal calcularParcelaUm(BigDecimal salarioBruto) {
		return changeToMonetaryBidecimal(salarioBruto.divide(METADE));
	}

	public static BigDecimal calcularParcelaDois(BigDecimal salarioBruto,
			BigDecimal descontoInss, BigDecimal descontoImpostoDeRenda) {
		BigDecimal parcelaDois = salarioBruto.divide(METADE)
				.subtract(descontoInss).subtract(descontoImpostoDeRenda);
		return changeToMonetaryBidecimal(parcelaDois);
	}
	
	/**
	 * Efetua o cálculo de décimo terceiro completo do funcionário (Situação
	 * normal, o funcionário iníciou o trabalho antes do ano começar)
	 * 
	 * @param salarioBruto
	 * @param numeroDependentes
	 * @return
	 */
	public DecimoTerceiro calcularDecimoTerceiro(ParametrosDecimoTerceiro parametro) {
		BigDecimal salarioCalculo;
		if (parametro.isSalarioReduzido()) {
			salarioCalculo = ReduzSalarioPorData.reduzirDecimoTerceiro(parametro);
		} else {
			salarioCalculo = parametro.getSalarioBruto();
		}
		DecimoTerceiro decimoTerceiro = new DecimoTerceiro(parametro.getSalarioBruto());
		
		decimoTerceiro = (DecimoTerceiro) calcularRemuneracao(decimoTerceiro, parametro, salarioCalculo.floatValue());
		decimoTerceiro.setSalarioParcelaUm(CalculaDecimoTerceiro.calcularParcelaUm(salarioCalculo));
		decimoTerceiro.setSalarioParcelaDois(
				CalculaDecimoTerceiro.calcularParcelaDois(salarioCalculo, decimoTerceiro.getDescontoInss(), decimoTerceiro.getDescontoIrpf()));

		if (parametro.isSalarioReduzido()) {
			decimoTerceiro.setTipo(TIPO_DECIMO_TERCEIRO.PARCIAL);			
		} else {
			decimoTerceiro.setTipo(TIPO_DECIMO_TERCEIRO.COMPLETO);			
		}

		return decimoTerceiro;
	}

}
