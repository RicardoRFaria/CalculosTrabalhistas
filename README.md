# Cálculos Trabalhistas ![Coverage Status](https://coveralls.io/repos/RicardoRFaria/CalculosTrabalhistas/badge.svg?branch=master&service=github) ![Build Status](https://travis-ci.org/RicardoRFaria/CalculosTrabalhistas.svg?branch=master) [![Codacy Badge](https://api.codacy.com/project/badge/Grade/8c485aa1741546cbab83227f5502b539)](https://www.codacy.com/app/ricardo-faria/CalculosTrabalhistas?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=RicardoRFaria/CalculosTrabalhistas&amp;utm_campaign=Badge_Grade)
Api que efetua os cálculos trabalhistas existentes nas folhas dos funcionários (atualizada com valores 2016).

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
CalculaSalario calculadoraSalario = new CalculaSalario();
ParametrosSalario parametro = new ParametrosSalario(VALOR_SALARIO_MINIMO);

Salario salario = calculadoraSalario.calcular(parametro);

// Imprime o valor do salário líquido resultante do cálculo
System.out.println(salario.getValorLiquido());
// Imprime o valor de desconto do INSS que incide sobre este cálculo
System.out.println(salario.getDescontoInss());
```

#Maven
Você pode obter a dependência com as seguintes configurações

- Adicionar o Jitpack como repositório maven
```xml
<repositories>
	<repository>
	    <id>jitpack.io</id>
	    <url>https://jitpack.io</url>
	</repository>
</repositories>
```

- Adicionar a dependência
```xml
<dependency>
    <groupId>com.github.RicardoRFaria</groupId>
    <artifactId>CalculosTrabalhistas</artifactId>
    <version>v2.0.0</version>
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
	compile 'com.github.RicardoRFaria:CalculosTrabalhistas:v2.0.0'
}
```

#Sobre o uso de Float ou BigDecimal
Internamente toda a api trabalha com BigDecimal, este é um detalhe de implementação mas revelo para justificar a escolha das entradas e saídas disponibilizadas por ela.
A escolha do BigDecimal da-se pela possibilidade de controlar os arredondamentos e manter precisão, algo que é necessário neste cenário de caso de uso.
Os parâmetros de entrada estão preparados para receber ambos, Float ou BigDecimal, já as saídas, são por padrão BigDecimal, mas existem métodos utilitários para obter o Float.
