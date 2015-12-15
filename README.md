# Cálculos Trabalhistas ![Coverage Status](https://coveralls.io/repos/RicardoRFaria/CalculosTrabalhistas/badge.svg?branch=master&service=github) ![Build Status](https://travis-ci.org/RicardoRFaria/CalculosTrabalhistas.svg?branch=master)
Api que efetua os cálculos trabalhistas existentes nas folhas dos funcionários (atualizada com valores 2015).

- Cálculo de salário líquido (Completo, parcial a partir da data de início, com horas extras)
- Cálculo de férias (Completa, 15 ou 20 dias)
- Cálculo de 13º (Completo e parcial por data de início)
- Cálculo de horas semanais
- Cálculo de horas extras
- Inclusão de adicional de periculosidade

#Uso
O fluxo de uso dos calculadores disponibilizados por esta lib é simples:
Objeto com parâmetros de entrada -> Chamada a classe de cálculo -> Objeto contendo os resultados brutos e formatados da operação

#Exemplo
```Java
CalculaSalario calcular = new CalculaSalario();
ParametrosSalario parametro = new ParametrosSalario(VALOR_SALARIO_MINIMO);

Salario salario = calcular.calcularSalario(parametro);

// Imprime o valor do salário líquido resultante do cálculo
System.out.println(salario.getValorLiquido());
// Imprime o valor de desconto do INSS que incide sobre este cálculo
System.out.println(salario.getDescontoInss());
```

#Maven
Apenas defina a dependência do projeto
```xml
<dependency>
	<groupId>com.ricardofaria</groupId>
	<artifactId>calculostrabalhistas</artifactId>
	<version>1.3.0</version>
</dependency>
```

#Gradle
Você pode obter a dependência com as seguintes configurações

- Adicionar o Jitpack como repositório maven
```xml
allprojects {
	repositories {
		...
		maven { url "https://jitpack.io" }
	}
}
```

- Adicionar a dependência
```xml
dependencies {
	compile 'com.github.RicardoRFaria:CalculosTrabalhistas:v1.3.0'
}
```

#Sobre o uso de Float ou BigDecimal
Internamente toda a api trabalha com BigDecimal, este é um detalhe de implementação mas revelo para justificar a escolha das entradas e saídas disponibilizadas por ela.
A escolha do BigDecimal da-se pela possibilidade de controlar os arredondamentos e manter precisão, algo que é necessário neste cenário de caso de uso.
Os parâmetros de entrada estão preparados para receber ambos, Float ou BigDecimal, já as saídas, são por padrão BigDecimal, mas existem métodos utilitários para obter o Float.