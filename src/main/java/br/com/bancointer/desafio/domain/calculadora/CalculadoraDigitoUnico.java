package br.com.bancointer.desafio.domain.calculadora;

import org.springframework.stereotype.Component;

@Component
public abstract class CalculadoraDigitoUnico {
	
	protected CalculadoraDigitoUnico proximo;
	
	public CalculadoraDigitoUnico(CalculadoraDigitoUnico proximo) {
		this.proximo = proximo;
	}

	public abstract int digitoUnico(String n, int k);

	
}
