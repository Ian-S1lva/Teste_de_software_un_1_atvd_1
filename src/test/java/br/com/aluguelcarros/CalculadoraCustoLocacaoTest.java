package br.com.aluguelcarros;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class CalculadoraCustoLocacaoTest
{
    private final CalculadoraCustoLocacao calculadora = new CalculadoraCustoLocacao();

    @ParameterizedTest
    @CsvSource({
        "ECONOMICO, 120.00",
        "INTERMEDIARIO, 180.00",
        "SUV, 280.00"
    })
    void deveCalcularLocacaoDeUmaDiariaConformeCategoriaParametrizado(
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
    void deveAplicarDescontoPorDuracaoConformeNumeroDeDiariasParametrizado(
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

    @ParameterizedTest
    @CsvSource({
        "COMUM, 8, false, 864.00",

        "PRATA, 6, false, 684.00",
        "PRATA, 7, false, 718.20",
        "PRATA, 8, false, 820.80",
        "PRATA, 8, true, 864.00",

        "OURO, 4, false, 456.00",
        "OURO, 5, false, 513.00",
        "OURO, 6, false, 684.00",
        "OURO, 6, true, 615.60"
    })
    void deveAplicarDescontoDeFidelidadeConformeNivelDoClienteEatrasoAnteriorParametrizado(        
        NivelCliente nivel,
        int numeroDiarias,     
        boolean atraso, 
        String resultadoEsperado)
    {
        BigDecimal resultado = calculadora.calcularCustoLocacao(
            CategoriaVeiculo.ECONOMICO, 
            nivel, 
            numeroDiarias, 
            10, 
            0, 
            TipoSeguro.SEM_SEGURO, 
            atraso
            );

        assertEquals(new BigDecimal(resultadoEsperado), resultado);
    }

    @ParameterizedTest
    @CsvSource({
        "ECONOMICO, 0, 718.20",
        "INTERMEDIARIO, 0, 1077.30",
        "SUV, 0, 1675.80",
        "ECONOMICO, 1, 718.20",
        "INTERMEDIARIO, 1, 1077.30",
        "SUV, 1, 1675.80",

        "ECONOMICO, 2, 742.20",
        "INTERMEDIARIO, 2, 1113.30",
        "SUV, 2, 1731.80",
        "ECONOMICO, 3, 742.20",
        "INTERMEDIARIO, 3, 1113.30",
        "SUV, 3, 1731.80",

        "ECONOMICO, 4, 838.20",
        "INTERMEDIARIO, 4, 1257.30",
        "SUV, 4, 1955.80",
        "ECONOMICO, 5, 838.20",
        "INTERMEDIARIO, 5, 1257.30",
        "SUV, 5, 1955.80"
    })
    void deveAplicarAtrasoConformeValorDaDiariaDaCategoriaEhorasDeAtrasoParametrizado(
        CategoriaVeiculo categoria,  
        int horasAtraso, 
        String resultadoEsperado)
    {
        BigDecimal resultado = calculadora.calcularCustoLocacao(
            categoria, 
            NivelCliente.PRATA, 
            7, 
            10, 
            horasAtraso, 
            TipoSeguro.SEM_SEGURO, 
            false
            );

        assertEquals(new BigDecimal(resultadoEsperado), resultado);
    }


    @ParameterizedTest
    @CsvSource({
        "1, SEM_SEGURO, 120.00",
        "1, BASICO, 145.00",
        "1, COMPLETO, 165.00",
        "2, SEM_SEGURO, 240.00",
        "2, BASICO, 290.00",
        "2, COMPLETO, 330.00",
        "7, SEM_SEGURO, 718.20", 
        "7, BASICO, 893.20",
        "7, COMPLETO, 1033.20"
    })
        void deveAplicarCustoDoSeguroConformeTipoDeSeguroEnumeroDeDiarias(  
        int numeroDiarias,
        TipoSeguro seguro,
        String resultadoEsperado)
    {
        BigDecimal resultado = calculadora.calcularCustoLocacao(
            CategoriaVeiculo.ECONOMICO,
            NivelCliente.PRATA,
            numeroDiarias,
            50,
            0,
            seguro,
            false
        );

        assertEquals(new BigDecimal(resultadoEsperado), resultado);
    }

    @ParameterizedTest
    @CsvSource({
        "0, 120.00",
        "99, 120.00",
        "100, 120.00",
        "101, 120.80",
        "199, 199.20",
        "200, 200.00",
        "201, 221.00",
        "399, 419.00",
        "400, 420.00",
        "401, 571.50"
    })
    void deveAplicarTaxaPorQuilometragemExcedenteConformeQuilometrosRodadosParametrizado(
        int quilometrosRodados, 
        String resultadoEsperado)
    {
        BigDecimal resultado = calculadora.calcularCustoLocacao(
            CategoriaVeiculo.ECONOMICO, 
            NivelCliente.COMUM, 
            1, 
            quilometrosRodados, 
            0, 
            TipoSeguro.SEM_SEGURO, 
            false
            );

        assertEquals(new BigDecimal(resultadoEsperado), resultado);
    }

    @ParameterizedTest
    @CsvSource({
        ", COMUM, SEM_SEGURO",
        "ECONOMICO, , SEM_SEGURO",
        "ECONOMICO, COMUM, "
    })
    void deveLancarNullPointerExceptionQuandoRecebeParametroNulo(
        CategoriaVeiculo categoria, 
        NivelCliente nivel, 
        TipoSeguro seguro) {
	
	assertThrows(NullPointerException.class, () -> {
		BigDecimal resultado = calculadora.calcularCustoLocacao(
            categoria, 
            nivel, 
            1, 
            10, 
            0, 
            seguro, 
            false);
	});
}

    @ParameterizedTest
    @CsvSource({
        ", COMUM, SEM_SEGURO",
        "ECONOMICO, , SEM_SEGURO",
        "ECONOMICO, COMUM, "
    })
    void deveLancarNullPointerExceptionQuandoRecebeParametroNuloEvalorNumericoInvalido(
        CategoriaVeiculo categoria, 
        NivelCliente nivel, 
        TipoSeguro seguro) {
	
	assertThrows(NullPointerException.class, () -> {
		BigDecimal resultado = calculadora.calcularCustoLocacao(
            categoria, 
            nivel, 
            -1, 
            -1, 
            -1, 
            seguro, 
            false);
	});
}

    @ParameterizedTest
    @CsvSource({
        "1, 0, 0, false",
        "0, 0, 0, true",
        "-1, 0, 0, true",
        "1, -1, 0, true",
        "1, -2, 0, true",
        "1, 0, -1, true",
        "1, 0, -2, true",
    })
    void deveLançarIllegalArgumentExceptionQuandoRecebeValorNumericoInvalido(
        int numeroDiarias, 
        int quilometrosRodados, 
        int horasAtraso,
	    boolean throwsException
    ) {
	if(throwsException){	
        assertThrows(IllegalArgumentException.class, () -> {
            BigDecimal resultado = calculadora.calcularCustoLocacao(
                CategoriaVeiculo.ECONOMICO, 
                NivelCliente.COMUM, 
                numeroDiarias, 
                quilometrosRodados, 
                horasAtraso, 
                TipoSeguro.SEM_SEGURO, 
                false
            );
        });
	}
	else{
        assertDoesNotThrow(
           () -> { 
                BigDecimal resultado = calculadora.calcularCustoLocacao(
                    CategoriaVeiculo.ECONOMICO, 
                    NivelCliente.COMUM, 
                    numeroDiarias, 
                    quilometrosRodados, 
                    horasAtraso, 
                    TipoSeguro.SEM_SEGURO, 
                    false
                );
            });
	}

}

}
