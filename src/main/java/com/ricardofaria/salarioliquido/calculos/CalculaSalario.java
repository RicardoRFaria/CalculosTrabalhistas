package com.ricardofaria.salarioliquido.calculos;

import java.math.BigDecimal;

import com.ricardofaria.salarioliquido.model.input.ParametrosBase;
import com.ricardofaria.salarioliquido.model.input.ParametrosSalario;
import com.ricardofaria.salarioliquido.model.resultado.HoraExtra;
import com.ricardofaria.salarioliquido.model.resultado.Remuneracao;
import com.ricardofaria.salarioliquido.model.resultado.Salario;
import com.ricardofaria.salarioliquido.util.ReduzSalarioPorData;

public class CalculaSalario extends CalculaRemuneracao {


	public Salario calcular(ParametrosSalario parametro) {
		if (parametro.getDataInicioColaborador() != null && parametro.getParametroHoraExtra() != null) {
			throw new UnsupportedOperationException("O calculo de hora extra com mes de trabalho parcial ainda nao foi implementado.");
		}

		BigDecimal salarioCalculo = parametro.getSalarioBruto();
		HoraExtra horaExtra = null;

		if (parametro.getDataInicioColaborador() != null) {
			salarioCalculo = ReduzSalarioPorData.reduzirSalarioPorDataDeInicio(parametro.getSalarioBruto(), parametro.getDataInicioColaborador());
		}
		BigDecimal adicionalPericulosidade = new BigDecimal("0");
		if (parametro.isAdicionalDePericulosidade()) {
			adicionalPericulosidade = calcularAdicionalDePericulosidade(salarioCalculo);
			salarioCalculo = calcularSalarioBrutoComAdicionalDePericulosidade(salarioCalculo);
		}
		if (parametro.getParametroHoraExtra() != null) {
			horaExtra = CalculaHorasExtras.calcularTotalHorasExtras(parametro.getParametroHoraExtra());
			salarioCalculo = salarioCalculo.add(horaExtra.getValorTotal());
		}

		Salario salario = new Salario(parametro.getSalarioBruto());
		calcularRemuneracao(salario, parametro, salarioCalculo.floatValue());
		salario.setAdicionalPericulosidade(adicionalPericulosidade);
		salario.setHoraExtra(horaExtra);

		return salario;
	}

	@Override
	public Remuneracao calcular(ParametrosBase parametros) {
		if (parametros instanceof ParametrosSalario) {
			return calcular((ParametrosSalario) parametros);
		} else {
			throw new IllegalArgumentException("Para calcular o salário a instância de parâmetros deve ser " + ParametrosSalario.class.getName());
		}
	}

}
