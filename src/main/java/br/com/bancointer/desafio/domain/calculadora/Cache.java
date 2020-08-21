package br.com.bancointer.desafio.domain.calculadora;

public class Cache extends CalculadoraDigitoUnico {
	
	public Cache(CalculadoraDigitoUnico proximo) {
		super(proximo);
	}

	@Override
	protected int digitoUnico(String n, int k) {
		return -1;
	}

}
