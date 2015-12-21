package com.ricardofaria.salarioliquido.calculos;

import static com.ricardofaria.salarioliquido.util.PrecisionUtil.changeToMonetaryBidecimal;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.ricardofaria.salarioliquido.model.input.ParametrosBase;
import com.ricardofaria.salarioliquido.model.input.ParametrosFerias;
import com.ricardofaria.salarioliquido.model.resultado.Ferias;
import com.ricardofaria.salarioliquido.model.resultado.Ferias.TIPO_FERIAS;
import com.ricardofaria.salarioliquido.model.resultado.Remuneracao;

public class CalculaFerias extends CalculaRemuneracao {

	private static final BigDecimal DIAS_10 = new BigDecimal("10");
	private static final BigDecimal DIAS_15 = new BigDecimal("15");
	private static final BigDecimal DIAS_20 = new BigDecimal("20");
	private static final BigDecimal DIAS_MES_FINANCEIRO = new BigDecimal("30");
	private static final BigDecimal UM_TERCO = new BigDecimal("0.333333");

	/**
	 * Modifica o sal�rio bruto informado com base na modalidade de f�rias do
	 * funcion�rio
	 *
	 * @param salarioBruto
	 *            definido para o funcion�rio
	 * @param tipo
	 *            de f�rias a ser aplicado
	 * @return
	 */
	public static BigDecimal aplicarModificarDeFeriasParcial(BigDecimal salarioBruto, TIPO_FERIAS tipo) {
		if (tipo == TIPO_FERIAS.COMPLETA) {
			return salarioBruto;
		}
		BigDecimal feriasParcial = obterUmDia(salarioBruto);
		if (tipo == TIPO_FERIAS.DIAS_20) {
			feriasParcial = feriasParcial.multiply(DIAS_20);
			return changeToMonetaryBidecimal(feriasParcial);
		} else if (tipo == TIPO_FERIAS.DIAS_15) {
			feriasParcial = feriasParcial.multiply(DIAS_15);
			return changeToMonetaryBidecimal(feriasParcial);
		}
		throw new IllegalArgumentException("Tipo de f�rias n�o implementado. Tipo atual: " + tipo.toString());
	}

	private static BigDecimal obterUmDia(BigDecimal salarioBruto) {
		return salarioBruto.divide(DIAS_MES_FINANCEIRO, 10, RoundingMode.HALF_EVEN);
	}

	/**
	 * Efetua o c�lculo do abono pecuni�rio em rela��o ao sal�rio do funcion�rio
	 * (Abono pecuni�rio s� existe na modalidade de F�rias 20 dias)
	 *
	 * @param salarioBrutoOriginal
	 *            o mesmo inserido no in�cio do c�lculo para o funcion�rio
	 * @return
	 */
	public static BigDecimal calcularAbonoPecuniario(BigDecimal salarioBruto) {

		BigDecimal umDia = obterUmDia(salarioBruto);
		BigDecimal dezDias = umDia.multiply(DIAS_10);
		BigDecimal abonoPecuniario = dezDias.multiply(UM_TERCO).add(dezDias);

		return changeToMonetaryBidecimal(abonoPecuniario);
	}

	public Ferias calcular(ParametrosFerias parametro) {
		Ferias feriasObject = new Ferias(parametro.getSalarioBruto());
		BigDecimal salarioBrutoObj = parametro.getSalarioBruto();

		salarioBrutoObj = aplicarModificarDeFeriasParcial(salarioBrutoObj, parametro.getTipo());

		BigDecimal adicionalPericulosidade = new BigDecimal("0");
		if (parametro.isAdicionalDePericulosidade()) {
			adicionalPericulosidade = calcularAdicionalDePericulosidade(salarioBrutoObj);
			salarioBrutoObj = calcularSalarioBrutoComAdicionalDePericulosidade(salarioBrutoObj);
		}

		BigDecimal ferias = salarioBrutoObj.multiply(UM_TERCO);
		feriasObject.setValorFerias(ferias);

		BigDecimal salarioCalculo = ferias.add(salarioBrutoObj);

		calcularRemuneracao(feriasObject, parametro, salarioCalculo.floatValue());
		feriasObject.setAdicionalPericulosidade(adicionalPericulosidade);

		if (parametro.getTipo() == TIPO_FERIAS.DIAS_20 && parametro.isAdicionarAbonoPecuniario()) {
			BigDecimal abonoPecuniario = CalculaFerias.calcularAbonoPecuniario(parametro.getSalarioBruto());
			feriasObject.setAbonoPecuniario(abonoPecuniario);
			feriasObject.setValorLiquido(feriasObject.getValorLiquido().add(abonoPecuniario));
		}

		if (parametro.isAdiantarDecimoTerceiro()) {
			BigDecimal primeiraParcelaDecimoTerceiro = parametro.getSalarioBruto().divide(new BigDecimal(2), 4, RoundingMode.HALF_EVEN);
			feriasObject.setAdiantamentoDecimoTerceiro(primeiraParcelaDecimoTerceiro);
			feriasObject.setValorLiquido(feriasObject.getValorLiquido().add(primeiraParcelaDecimoTerceiro));
		}

		return feriasObject;
	}

	@Override
	public Remuneracao calcular(ParametrosBase parametros) {
		if (parametros instanceof ParametrosFerias) {
			return calcular((ParametrosFerias) parametros);
		} else {
			throw new IllegalArgumentException("Para calcular as Férias a instância de parâmetros deve ser " + ParametrosFerias.class.getName());
		}
	}

}
