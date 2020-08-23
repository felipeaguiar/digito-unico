package br.com.bancointer.desafio.domain.calculadora;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.bancointer.desafio.application.errors.NumeroNaoPermitidoException;
import br.com.bancointer.desafio.domain.calculadora.erros.LimiteDeRepeticaoException;

class CalculadoraDigitoUnicoImplTest {
	
	private CalculadoraDigitoUnicoImpl calculadora;

	@BeforeEach
	void setUp() throws Exception {
		calculadora = new CalculadoraDigitoUnicoImpl();
	}

	@Test
	void deveCalcularDigitoUnico() {
		int digito = calculadora.digitoUnico("9875", 1);
		assertEquals(2, digito);
	}
	
	@Test
	void deveCalcularDigitoUnicoComRepeticao() {
		int digito = calculadora.digitoUnico("9875", 4);
		assertEquals(8, digito);
	}
	
	@Test
	void deveCalcularODigitoUnicoComONumeroMaximoPermitido() {
		String numero = simularNumero(1000000);
		int digito = calculadora.digitoUnico(numero, 1);
		assertEquals(1, digito);
	}
	
	@Test
	void deveCalcularODigitoUnicoComONumeroMaximoPermitidoComUmaRepeticao() {
		String numero = simularNumero(1000000);
		int digito = calculadora.digitoUnico(numero, 2);
		assertEquals(2, digito);
	}
	
	@Test
	void deveFalharAoUltrapassarONumeroMaximoPermitido() {
		String numero = simularNumero(1000001);
		assertThrows(NumeroNaoPermitidoException.class, () -> {
			calculadora.digitoUnico(numero, 1);
	    });
	}
	
	@Test
	void deveCalcularDigitoUnicoComValorMaximoDeRepeticoes() {
		int digito = calculadora.digitoUnico("9875", 100000);
		assertEquals(2, digito);
	}
	
	@Test
	void deveFalharAoUltrapassarOLimiteMaximoDeRepeticoes() {
		assertThrows(LimiteDeRepeticaoException.class, () -> {
	        calculadora.digitoUnico("9875", 100001);
	    });
	}
	
	@Test
	void deveFalharAoUltrapassarOLimiteMinimoDeRepeticoes() {
		assertThrows(LimiteDeRepeticaoException.class, () -> {
	        calculadora.digitoUnico("9875", 0);
	    });
	}
	
	private String simularNumero(int tamanho) {
		StringBuilder sb = new StringBuilder();
		
		sb.append('1');
		for (int i = 0; i < tamanho; i++) {
			sb.append('0');
		}
		
		String numero = sb.toString();
		return numero;
	}
	

}
