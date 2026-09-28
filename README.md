# Integrantes

Gustavo Fernando e Ian Silva.

## Instruções de execução

Abra o projeto no ambiente de desenvolvimento com JUnit5 instalado.
Abra o arquivo test/java/br/com/alguelcarros/CalculadoraCustoLocacaoTest.java
Clique no ícone ao lado de "class CalculadoraCustoLocacaoTest" para executar todos os testes.

## Testes

As informações completas de classes de equivalência, valores limites, tabela de decisão e projetos de teste utilizados podem ser encontrados na planilha.csv, a seção abaixo informa o resultado resumido de cada teste.

## Resultado dos testes

### Locacao de uma diaria

O teste "deveCalcularLocacaoDeUmaDiariaConformeCategoriaParametrizado" obteve sucesso, portanto o programa calcula corretamente o valor cobrado para uma única diaria por categoria.

### Aplicacao de desconto por duracao conforme valor de diarias

O teste "deveAplicarDescontoPorDuracaoConformeNumeroDeDiariasParametrizado" falhou para o parâmetro "ECONOMICO, 6, 684.00" (categoria, nº diarias, resultado esperado), pois o valor obtido foi 720.00 quando devia ser 684.00. Sabendo que a diária para categoria ECONOMICA é 120.00 e o valor bruto para 6 diárias é 120.00 x 6 = 720.00 pode-se observar que o desconto não foi aplicado corretamente para esse parâmetro. Mas ele foi aplicado corretamente para o parâmetro "ECONOMICO, 3, 342.00" (categoria, nº diarias, resultado esperado).

### Aplicacao de desconto por finalidade conforme nível do cliente

O teste "deveAplicarDescontoDeFidelidadeConformeNivelDoClienteEatrasoAnteriorParametrizado" falhou para o primeiro parâmetro "COMUM, 8, false, 864.00" (categoria, nº de diárias, atraso anterior, valor esperado) no entanto, não é possível detectar falha na lógica de aplicação do desconto de fidelidade, pois a aplicação do desconto de fidelidade depende do número de diárias, assim uma falha no teste anterior também provoca uma falha nesse teste. É preciso primeiro corrigir o erro de implementação no desconto por duração para testar a aplicação do desconto por fidelidade.

### Aplicacao de Atraso conforme Valor da Diaria da Categoria

O teste "deveAplicarAtrasoConformeValorDaDiariaDaCategoriaEhorasDeAtrasoParametrizado" falhou para o parâmetro "ECONOMICO, 0, 718.20" (categoria, horas de atraso, resultado esperado), no entanto não é possível identificar erro na lógica da aplicação do atraso sem correção da lógica de aplicação do desconto por duração. Conforme o enunciado o cálculo de descontos não deve ser aplicado no custo de atraso, assim o teste utiliza parâmetros em que são aplicados descontos para avaliar a lógica correta.

### Aplica Seguro Conforme Diárias

O teste "deveAplicarCustoDoSeguroConformeTipoDeSeguroEnumeroDeDiarias" falha para "7, SEM_SEGURO, 718.20" (Nº de diarias, tipo de seguro, resultado esperado) mas obtém sucesso para Nº de diarias = {1,2} e tipo de seguro = {SEM_SEGURO, BASICO, COMPLETO}. Assim, o custo do seguro é aplicado corretamente para 1 ou 2 diárias, mas é aplicado incorretamente para 7 diárias, uma vez que para 7 diárias também é aplicável o desconto por duração, não é possível distinguir se o erro é proveniente da lógica de aplicação do seguro ou somente do erro na lógica de aplicação do desconto.

### Aplicação de Taxa por Quilometragem

O teste "deveAplicarTaxaPorQuilometragemExcedenteConformeQuilometrosRodadosParametrizado" obtém sucesso, dando evidência de que a lógica de aplicação de custo por quilómetros excedentes está correta.

### Teste de exceção para parâmetros nulos

O teste "deveLancarNullPointerExceptionQuandoRecebeParametroNulo" obtém sucesso, portanto a classe lança corretamente a exceção NullPointerException ao receber um valor nulo.

### Teste de exceção para parâmetro nulo e valor numérico inválido

O teste "deveLancarNullPointerExceptionQuandoRecebeParametroNuloEvalorNumericoInvalido" obtém sucesso, portanto a classe dá prioridade para lançar a exceção NullPointerException quando recebe um parâmetro nulo e um valor numérico inválido.

### Teste de exceção para valor numérico inválido

O teste "deveLançarIllegalArgumentExceptionQuandoRecebeValorNumericoInvalido" obtém sucesso, portanto a classe lança corretamente a exceção IllegalArgumentException ao receber valores numéricos inválidos e entradas não nulas. 