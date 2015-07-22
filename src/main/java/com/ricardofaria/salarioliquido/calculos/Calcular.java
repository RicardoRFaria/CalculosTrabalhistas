package com.ricardofaria.salarioliquido.calculos;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.ricardofaria.salarioliquido.model.input.ParametrosDecimoTerceiro;
import com.ricardofaria.salarioliquido.model.input.ParametrosFerias;
import com.ricardofaria.salarioliquido.model.input.ParametrosSalario;
import com.ricardofaria.salarioliquido.model.resultado.DecimoTerceiro;
import com.ricardofaria.salarioliquido.model.resultado.Ferias;
import com.ricardofaria.salarioliquido.model.resultado.HoraExtra;
import com.ricardofaria.salarioliquido.model.resultado.Salario;
import com.ricardofaria.salarioliquido.model.resultado.DecimoTerceiro.TIPO_DECIMO_TERCEIRO;
import com.ricardofaria.salarioliquido.model.resultado.Ferias.TIPO_FERIAS;
import com.ricardofaria.salarioliquido.util.ReduzSalarioPorData;

import static com.ricardofaria.salarioliquido.util.PrecisionUtil.*;

/**
 * 
 * Classe responsável por chamar os cálculos menores em sua ordem correta e
 * gerar o resultado para o cálculo total
 * 
 * @author Ricardo Faria
 *
 */
public class Calcular {

	private static final BigDecimal DIAS_10 = new BigDecimal("10");
	private static final BigDecimal DIAS_15 = new BigDecimal("15");
	private static final BigDecimal DIAS_20 = new BigDecimal("20");
	private static final BigDecimal DIAS_30 = new BigDecimal("30");
	private static final BigDecimal UM_TERCO = new BigDecimal("0.333333");

	public Salario calcularSalario(ParametrosSalario parametro) {
		if (parametro.getDataInicioColaborador() != null && parametro.getParametroHoraExtra() != null) {
			throw new UnsupportedOperationException("O cálculo de hora extra com mês de trabalho parcial ainda não foi implementado.");
		}
		
		BigDecimal salarioCalculo = parametro.getSalarioBruto();
		HoraExtra horaExtra = null;
		
		if (parametro.getDataInicioColaborador() != null) {
			salarioCalculo = ReduzSalarioPorData.reduzirSalarioPorDataDeInicio(parametro.getSalarioBruto(), parametro.getDataInicioColaborador());
		}
		if (parametro.isAdicionalDePericulosidade()) {
			salarioCalculo = CalculaSalario.calcularSalarioBrutoComAdicionalDePericulosidade(salarioCalculo);
		}
		if (parametro.getParametroHoraExtra() != null) {
			horaExtra = CalculaHorasExtras.calcularTotalHorasExtras(parametro.getParametroHoraExtra());
			salarioCalculo = salarioCalculo.add(horaExtra.getValorTotal());
		}
		
		Salario salario = new Salario(parametro.getSalarioBruto());
		salario = (Salario) CalculaSalario.calcularRemuneracao(salario, parametro, salarioCalculo.floatValue());
		salario.setHoraExtra(horaExtra);
		
		return salario;
	}

	public Ferias calcularFerias(ParametrosFerias parametro) {
		Ferias feriasObject = new Ferias(parametro.getSalarioBruto());
		BigDecimal salarioBrutoObj = parametro.getSalarioBruto();

		salarioBrutoObj = aplicarModificarDeFeriasParcial(salarioBrutoObj, parametro.getTipo());

		BigDecimal ferias = salarioBrutoObj.multiply(UM_TERCO);
		feriasObject.setValorFerias(ferias);

		BigDecimal salarioCalculo = ferias.add(salarioBrutoObj);

		feriasObject = (Ferias) CalculaSalario.calcularRemuneracao(feriasObject, parametro, salarioCalculo.floatValue());

		if (parametro.getTipo() == TIPO_FERIAS.DIAS_20) {
			BigDecimal abonoPecuniario = calcularAbonoPecuniario(parametro.getSalarioBruto());
			feriasObject.setAbonoPecuniario(abonoPecuniario);
			feriasObject.setValorLiquido(feriasObject.getValorLiquido() + abonoPecuniario.floatValue());
		}

		return feriasObject;
	}

	public Ferias calcularFeriasComAdiantamentoDeDecimoTerceiro(ParametrosFerias parametro) {
		Ferias ferias = calcularFerias(parametro);
		
		float primeiraParcelaDecimoTerceiro = parametro.getSalarioBruto().floatValue() / 2;
		ferias.setAdiantamentoDecimoTerceiro(primeiraParcelaDecimoTerceiro);
		ferias.setValorLiquido(ferias.getValorLiquido() + primeiraParcelaDecimoTerceiro);
		
		return ferias;
	}
	
	/**
	 * Modifica o salário bruto informado com base na modalidade de férias do
	 * funcionário
	 * 
	 * @param salarioBruto
	 *            definido para o funcionário
	 * @param tipo
	 *            de férias a ser aplicado
	 * @return
	 */
	public BigDecimal aplicarModificarDeFeriasParcial(BigDecimal salarioBruto, TIPO_FERIAS tipo) {
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
		throw new IllegalArgumentException("Tipo de férias não implementado. Tipo atual: " + tipo.toString());
	}

	private BigDecimal obterUmDia(BigDecimal salarioBruto) {
		return salarioBruto.divide(DIAS_30, 10, RoundingMode.HALF_EVEN);
	}

	/**
	 * Efetua o cálculo do abono pecuniário em relação ao salário do funcionário
	 * (Abono pecuniário só existe na modalidade de Férias 20 dias)
	 * 
	 * @param salarioBrutoOriginal
	 *            o mesmo inserido no início do cálculo para o funcionário
	 * @return
	 */
	public BigDecimal calcularAbonoPecuniario(BigDecimal salarioBruto) {
		
		BigDecimal umDia = obterUmDia(salarioBruto);
		BigDecimal dezDias = umDia.multiply(DIAS_10);
		BigDecimal abonoPecuniario = dezDias.multiply(UM_TERCO).add(dezDias);

		return changeToMonetaryBidecimal(abonoPecuniario);
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
		
		decimoTerceiro = (DecimoTerceiro) CalculaSalario.calcularRemuneracao(decimoTerceiro, parametro, salarioCalculo.floatValue());
		decimoTerceiro.setSalarioParcelaUm(CalculaDecimoTerceiro.calcularParcelaUm(salarioCalculo));
		decimoTerceiro.setSalarioParcelaDois(
				CalculaDecimoTerceiro.calcularParcelaDois(salarioCalculo, new BigDecimal(decimoTerceiro.getDescontoInss()), new BigDecimal(decimoTerceiro.getDescontoIrpf())));

		if (parametro.isSalarioReduzido()) {
			decimoTerceiro.setTipo(TIPO_DECIMO_TERCEIRO.PARCIAL);			
		} else {
			decimoTerceiro.setTipo(TIPO_DECIMO_TERCEIRO.COMPLETO);			
		}

		return decimoTerceiro;
	}

}
