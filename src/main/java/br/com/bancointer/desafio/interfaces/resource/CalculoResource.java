package br.com.bancointer.desafio.interfaces.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.bancointer.desafio.interfaces.dto.CalculoDto;
import br.com.bancointer.desafio.interfaces.facade.CalculoFacade;

@RestController
@RequestMapping("/calculo")
public class CalculoResource {
	
	@Autowired
	private CalculoFacade calculoFacade;

	@PostMapping
	public ResponseEntity<CalculoDto> salvar(@Validated @RequestBody CalculoDto calcloDto) {
		CalculoDto resultado = calculoFacade.calcular(calcloDto);
		return ResponseEntity.ok(resultado);
	}
	
}
