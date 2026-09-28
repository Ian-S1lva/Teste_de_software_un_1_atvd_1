package br.com.aluguelcarros;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class CalculadoraCustoLocacaoTest
{
    private final CalculadoraCustoLocacao calculadora = new CalculadoraCustoLocacao();

    @Test
    void deveCalcularLocacaoBasicaSemCustosAdicionais()
    {
        BigDecimal resultado = calculadora.calcularCustoLocacao(CategoriaVeiculo.ECONOMICO, NivelCliente.COMUM, 1, 100, 0, TipoSeguro.SEM_SEGURO, false);

        assertEquals(new BigDecimal("120.00"), resultado);
    }

    @ParameterizedTest
    @CsvSource({
        "ECONOMICO, 120.00",
        "INTERMEDIARIO, 180.00",
        "SUV, 280.00"
    })
    void deveCalcularLocacaoDeUmaDiariaSemCustoAdicionalParametrizado(
        CategoriaVeiculo categoria,        
        String resultadoEsperado)
    {
        BigDecimal resultado = calculadora.calcularCustoLocacao(
            categoria, 
            NivelCliente.COMUM, 
            1, 
            10, 
            0, 
            TipoSeguro.SEM_SEGURO, 
            false
            );

        assertEquals(new BigDecimal(resultadoEsperado), resultado);
    }

    @ParameterizedTest
    @CsvSource({
        "ECONOMICO, 1, 120.00",
        "ECONOMICO, 2, 240.00",
        "ECONOMICO, 3, 342.00",
        "ECONOMICO, 6, 684.00",
        "ECONOMICO, 7, 756.00",
        "ECONOMICO, 14, 1512.00",
        "ECONOMICO, 15, 1530.00",
        "ECONOMICO, 16, 1632.00",
        "INTERMEDIARIO, 1, 180.00",
        "INTERMEDIARIO, 2, 360.00",
        "INTERMEDIARIO, 3, 513.00",
        "INTERMEDIARIO, 6, 1026.00",
        "INTERMEDIARIO, 7, 1134.00",
        "INTERMEDIARIO, 14, 2268.00",
        "INTERMEDIARIO, 15, 2295.00",
        "INTERMEDIARIO, 16, 2448.00",
        "SUV, 1, 280.00",
        "SUV, 2, 560.00",
        "SUV, 3, 798.00",
        "SUV, 6, 1596.00",
        "SUV, 7, 1764.00",
        "SUV, 14, 3528.00",
        "SUV, 15, 3570.00",
        "SUV, 16, 3808.00"	
    })
    void deveCalcularLocacaoComDescontoPorDuracaoSemCustoAdicionalParametrizado(
        CategoriaVeiculo categoria,         
        int numeroDiarias,         
        String resultadoEsperado)
    {
        BigDecimal resultado = calculadora.calcularCustoLocacao(
            categoria, 
            NivelCliente.COMUM, 
            numeroDiarias, 
            10, 
            0, 
            TipoSeguro.SEM_SEGURO, 
            false
            );

        assertEquals(new BigDecimal(resultadoEsperado), resultado);
    }

}
