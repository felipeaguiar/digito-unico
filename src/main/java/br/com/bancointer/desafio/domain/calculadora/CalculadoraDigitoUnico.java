package br.com.bancointer.desafio.domain.calculadora;

import org.springframework.stereotype.Component;

@Component
public abstract class CalculadoraDigitoUnico {
	
	private CalculadoraDigitoUnico proximo;
	
	public CalculadoraDigitoUnico(CalculadoraDigitoUnico proximo) {
		this.proximo = proximo;
	}
	
	public int calcular(String n, int k) {
		int digito = digitoUnico(n, k);
		
		if (digito == -1) {
			return proximo.calcular(n, k);
		}
			
		return digito;
	}

	protected abstract int digitoUnico(String n, int k);

	
}
