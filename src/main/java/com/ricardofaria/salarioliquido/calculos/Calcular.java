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

/**
 * 
 * Classe responsável por chamar os cálculos menores em sua ordem correta e
 * gerar o resultado para o cálculo total
 * 
 * @author Ricardo Faria
 *
 */
public class Calcular {

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

		salarioBrutoObj = CalculaFerias.aplicarModificarDeFeriasParcial(salarioBrutoObj, parametro.getTipo());

		BigDecimal ferias = salarioBrutoObj.multiply(UM_TERCO);
		feriasObject.setValorFerias(ferias);

		BigDecimal salarioCalculo = ferias.add(salarioBrutoObj);

		feriasObject = (Ferias) CalculaSalario.calcularRemuneracao(feriasObject, parametro, salarioCalculo.floatValue());

		if (parametro.getTipo() == TIPO_FERIAS.DIAS_20) {
			BigDecimal abonoPecuniario = CalculaFerias.calcularAbonoPecuniario(parametro.getSalarioBruto());
			feriasObject.setAbonoPecuniario(abonoPecuniario);
			feriasObject.setValorLiquido(feriasObject.getValorLiquido().add(abonoPecuniario));
		}

		return feriasObject;
	}

	public Ferias calcularFeriasComAdiantamentoDeDecimoTerceiro(ParametrosFerias parametro) {
		Ferias ferias = calcularFerias(parametro);
		
		BigDecimal primeiraParcelaDecimoTerceiro = parametro.getSalarioBruto().divide(new BigDecimal(2), 4, RoundingMode.HALF_EVEN);
		ferias.setAdiantamentoDecimoTerceiro(primeiraParcelaDecimoTerceiro);
		ferias.setValorLiquido(ferias.getValorLiquido().add(primeiraParcelaDecimoTerceiro));
		
		return ferias;
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
				CalculaDecimoTerceiro.calcularParcelaDois(salarioCalculo, decimoTerceiro.getDescontoInss(), decimoTerceiro.getDescontoIrpf()));

		if (parametro.isSalarioReduzido()) {
			decimoTerceiro.setTipo(TIPO_DECIMO_TERCEIRO.PARCIAL);			
		} else {
			decimoTerceiro.setTipo(TIPO_DECIMO_TERCEIRO.COMPLETO);			
		}

		return decimoTerceiro;
	}

}
