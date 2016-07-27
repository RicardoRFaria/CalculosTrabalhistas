package com.ricardofaria.salarioliquido.calculos;

import static com.ricardofaria.salarioliquido.util.PrecisionUtil.changeToMonetaryBidecimal;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.ricardofaria.salarioliquido.model.constantes.TipoFerias;
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
	 * Modifica o salario bruto informado com base na modalidade de ferias do
	 * funcionario
	 *
	 * @param salarioBruto
	 *            definido para o funcionario
	 * @param tipo
	 *            instancia de {@link TipoFerias} para verificar qual calculo
	 *            sera efetuado
	 * @return
	 */
	public static BigDecimal aplicarModificarDeFeriasParcial(BigDecimal salarioBruto, TipoFerias tipo) {
		if (tipo == TipoFerias.COMPLETA) {
			return salarioBruto;
		}
		BigDecimal feriasParcial = obterUmDia(salarioBruto);
		if (tipo == TipoFerias.DIAS_20) {
			feriasParcial = feriasParcial.multiply(DIAS_20);
			return changeToMonetaryBidecimal(feriasParcial);
		} else if (tipo == TipoFerias.DIAS_15) {
			feriasParcial = feriasParcial.multiply(DIAS_15);
			return changeToMonetaryBidecimal(feriasParcial);
		}
		throw new IllegalArgumentException("Tipo de f�rias n�o implementado. Tipo atual: " + tipo.toString());
	}

	/**
	 * Modifica o salario bruto informado com base na modalidade de ferias do
	 * funcionario
	 *
	 * @param salarioBruto
	 *            definido para o funcionario
	 * @param tipo
	 *            de ferias a ser aplicado
	 * @return
	 */
	@Deprecated
	public static BigDecimal aplicarModificarDeFeriasParcial(BigDecimal salarioBruto, TIPO_FERIAS tipo) {
		return aplicarModificarDeFeriasParcial(salarioBruto, TipoFerias.valueOf(tipo.name()));
	}

	private static BigDecimal obterUmDia(BigDecimal salarioBruto) {
		return salarioBruto.divide(DIAS_MES_FINANCEIRO, 10, RoundingMode.HALF_EVEN);
	}

	/**
	 * Efetua o calculo do abono pecuniario em relacao ao salario bruto
	 * informado (Abono pecuniario so existe na modalidade de 20 dias -
	 * {@link TipoFerias#DIAS_20))
	 *
	 * @param salarioBrutoOriginal
	 *            o mesmo inserido no inicio do calculo para o funcionario
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

		if (parametro.getTipoFerias() == TipoFerias.DIAS_20 && parametro.isAdicionarAbonoPecuniario()) {
			BigDecimal abonoPecuniario = CalculaFerias.calcularAbonoPecuniario(parametro.getSalarioBruto());
			feriasObject.setAbonoPecuniario(abonoPecuniario);
			feriasObject.setValorLiquido(feriasObject.getValorLiquido().add(abonoPecuniario));
		}

		if (parametro.isAdiantarDecimoTerceiro()) {
			BigDecimal primeiraParcelaDecimoTerceiro = parametro.getSalarioBruto().divide(new BigDecimal(2), 4,
					RoundingMode.HALF_EVEN);
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
			throw new IllegalArgumentException(
					"Para calcular as Férias a instância de parâmetros deve ser " + ParametrosFerias.class.getName());
		}
	}

}
