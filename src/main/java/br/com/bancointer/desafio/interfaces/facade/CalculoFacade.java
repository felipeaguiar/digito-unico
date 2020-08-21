package br.com.bancointer.desafio.interfaces.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.bancointer.desafio.application.UsuarioService;
import br.com.bancointer.desafio.domain.calculadora.CalculadoraDigitoUnico;
import br.com.bancointer.desafio.interfaces.dto.CalculoDto;

@Component
public class CalculoFacade {
	
	private CalculadoraDigitoUnico calculadora;
	private UsuarioService usuarioService;
	
	@Autowired
	public CalculoFacade(CalculadoraDigitoUnico calculadora, UsuarioService usuarioService) {
		this.calculadora = calculadora;
		this.usuarioService = usuarioService;
	}

	public CalculoDto calcular(CalculoDto calculoDto) {
		String n = calculoDto.getNumero();
		int k = calculoDto.getRepeticoes();
		
		int digito = calculadora.calcular(n, k);
		
		Long id = calculoDto.getUsuario();
		if (id != null) {
			usuarioService.adicionarCalculo(id, n, k, digito);
		}
		
		calculoDto.setResultado(digito);
		
		return calculoDto;
	}

}
