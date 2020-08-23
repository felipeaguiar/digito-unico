package br.com.bancointer.desafio.domain.calculadora;

import br.com.bancointer.desafio.application.errors.NumeroNaoPermitidoException;
import br.com.bancointer.desafio.domain.calculadora.erros.LimiteDeRepeticaoException;

public class CalculadoraDigitoUnicoImpl extends CalculadoraDigitoUnico {

	public CalculadoraDigitoUnicoImpl() {
		super(null);
	}

	@Override
	public int digitoUnico(String n, int k) {
		if (k > 100000 || k < 1) {
			throw new LimiteDeRepeticaoException(); 
		}
		
		if (!n.matches("^[1-9]{1,1000000}?$|^1[0]{1000000}$")) {
			throw new NumeroNaoPermitidoException();
		}
		
		if (k > 1) {
			StringBuilder sb = new StringBuilder();
			
			for (int i = 0; i < k; i++) {				
				sb.append(n);
			}
			
			return reduce(sb.toString());
		}
		
		return reduce(n);
	}
	
	private int reduce(String n) {
		int digito = 0;
		
		for (int i = 0; i < n.length(); i++) {
		    char c = n.charAt(i);
		    digito += Character.getNumericValue(c);
		}
		
		String number = Integer.toString(digito);
		
		if (number.length() > 1) {
			return reduce(number);
		}
		
		return digito;
	}

}
