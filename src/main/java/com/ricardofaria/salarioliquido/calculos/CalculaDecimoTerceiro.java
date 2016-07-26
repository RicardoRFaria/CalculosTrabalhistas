package com.ricardofaria.salarioliquido.calculos;

import static com.ricardofaria.salarioliquido.util.PrecisionUtil.changeToMonetaryBidecimal;

import java.math.BigDecimal;

import com.ricardofaria.salarioliquido.model.constantes.TipoDecimoTerceiro;
import com.ricardofaria.salarioliquido.model.input.ParametrosBase;
import com.ricardofaria.salarioliquido.model.input.ParametrosDecimoTerceiro;
import com.ricardofaria.salarioliquido.model.resultado.DecimoTerceiro;
import com.ricardofaria.salarioliquido.model.resultado.Remuneracao;
import com.ricardofaria.salarioliquido.util.ReduzSalarioPorData;

public class CalculaDecimoTerceiro extends CalculaRemuneracao {

	private static final BigDecimal METADE = new BigDecimal("2");

	public CalculaDecimoTerceiro() {
		super();
	}

	public static BigDecimal calcularParcelaUm(BigDecimal salarioBruto) {
		return changeToMonetaryBidecimal(salarioBruto.divide(METADE));
	}

	public static BigDecimal calcularParcelaDois(BigDecimal salarioBruto, BigDecimal descontoInss,
			BigDecimal descontoImpostoDeRenda) {
		BigDecimal parcelaDois = salarioBruto.divide(METADE).subtract(descontoInss).subtract(descontoImpostoDeRenda);
		return changeToMonetaryBidecimal(parcelaDois);
	}

	/**
	 * Efetua o c�lculo de d�cimo terceiro completo do funcion�rio (Situa��o
	 * normal, o funcion�rio in�ciou o trabalho antes do ano come�ar)
	 *
	 * @param salarioBruto
	 * @param numeroDependentes
	 * @return
	 */
	public DecimoTerceiro calcular(ParametrosDecimoTerceiro parametro) {
		BigDecimal salarioCalculo;
		if (parametro.isSalarioReduzido()) {
			salarioCalculo = ReduzSalarioPorData.reduzirDecimoTerceiro(parametro);
		} else {
			salarioCalculo = parametro.getSalarioBruto();
		}

		BigDecimal adicionalPericulosidade = new BigDecimal("0");
		if (parametro.isAdicionalDePericulosidade()) {
			adicionalPericulosidade = calcularAdicionalDePericulosidade(salarioCalculo);
			salarioCalculo = calcularSalarioBrutoComAdicionalDePericulosidade(salarioCalculo);
		}

		DecimoTerceiro decimoTerceiro = new DecimoTerceiro(parametro.getSalarioBruto());

		calcularRemuneracao(decimoTerceiro, parametro, salarioCalculo.floatValue());
		decimoTerceiro.setSalarioParcelaUm(CalculaDecimoTerceiro.calcularParcelaUm(salarioCalculo));
		decimoTerceiro.setSalarioParcelaDois(CalculaDecimoTerceiro.calcularParcelaDois(salarioCalculo,
				decimoTerceiro.getDescontoInss(), decimoTerceiro.getDescontoIrpf()));
		decimoTerceiro.setAdicionalPericulosidade(adicionalPericulosidade);

		if (parametro.isSalarioReduzido()) {
			decimoTerceiro.setTipoDecimoTerceiro(TipoDecimoTerceiro.PARCIAL);
		} else {
			decimoTerceiro.setTipoDecimoTerceiro(TipoDecimoTerceiro.COMPLETO);
		}

		return decimoTerceiro;
	}

	@Override
	public Remuneracao calcular(ParametrosBase parametros) {
		if (parametros instanceof ParametrosDecimoTerceiro) {
			return calcular((ParametrosDecimoTerceiro) parametros);
		} else {
			throw new IllegalArgumentException("Para calcular o Décimo Terceiro a instância de parâmetros deve ser "
					+ ParametrosDecimoTerceiro.class.getName());
		}
	}

}
