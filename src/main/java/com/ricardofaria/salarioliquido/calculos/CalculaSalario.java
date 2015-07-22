package com.ricardofaria.salarioliquido.calculos;

import java.math.BigDecimal;

import com.ricardofaria.salarioliquido.model.input.ParametrosSalario;
import com.ricardofaria.salarioliquido.model.resultado.HoraExtra;
import com.ricardofaria.salarioliquido.model.resultado.Salario;
import com.ricardofaria.salarioliquido.util.ReduzSalarioPorData;

public class CalculaSalario extends CalculaRemuneracao {
	
	
	public Salario calcularSalario(ParametrosSalario parametro) {
		if (parametro.getDataInicioColaborador() != null && parametro.getParametroHoraExtra() != null) {
			throw new UnsupportedOperationException("O c�lculo de hora extra com m�s de trabalho parcial ainda n�o foi implementado.");
		}
		
		BigDecimal salarioCalculo = parametro.getSalarioBruto();
		HoraExtra horaExtra = null;
		
		if (parametro.getDataInicioColaborador() != null) {
			salarioCalculo = ReduzSalarioPorData.reduzirSalarioPorDataDeInicio(parametro.getSalarioBruto(), parametro.getDataInicioColaborador());
		}
		if (parametro.isAdicionalDePericulosidade()) {
			salarioCalculo = calcularSalarioBrutoComAdicionalDePericulosidade(salarioCalculo);
		}
		if (parametro.getParametroHoraExtra() != null) {
			horaExtra = CalculaHorasExtras.calcularTotalHorasExtras(parametro.getParametroHoraExtra());
			salarioCalculo = salarioCalculo.add(horaExtra.getValorTotal());
		}
		
		Salario salario = new Salario(parametro.getSalarioBruto());
		salario = (Salario) calcularRemuneracao(salario, parametro, salarioCalculo.floatValue());
		salario.setHoraExtra(horaExtra);
		
		return salario;
	}

}
