package br.com.bancointer.desafio.interfaces.resource;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.bancointer.desafio.interfaces.dto.CalculoDto;
import br.com.bancointer.desafio.interfaces.dto.ChaveDto;
import br.com.bancointer.desafio.interfaces.dto.UsuarioDto;
import br.com.bancointer.desafio.interfaces.facade.UsuarioFacade;
import br.com.bancointer.desafio.interfaces.http.HttpHelper;

@RestController
@RequestMapping("/usuario")
public class UsuarioResource {
	
	@Autowired
	private UsuarioFacade usuarioFacade;
	
	@Autowired
	private HttpHelper helper;
	
	@GetMapping("/{id}")
	public ResponseEntity<UsuarioDto> buscarPorId(@PathVariable Long id) {
		UsuarioDto usuario = usuarioFacade.buscarPorId(id);
		return ResponseEntity.ok(usuario);
	}
	
	@GetMapping("/{id}/calculos")
	public ResponseEntity<List<CalculoDto>> calculos(@PathVariable Long id) {
		List<CalculoDto> calculos = usuarioFacade.calculos(id);
		return ResponseEntity.ok(calculos);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long id) {
		usuarioFacade.remover(id);
	}

	@PostMapping
	public ResponseEntity<UsuarioDto> salvar(@Validated @RequestBody UsuarioDto usuarioDTO) {
		UsuarioDto usuarioSalvo = usuarioFacade.salvar(usuarioDTO);
		URI uri = helper.getUriOf(usuarioSalvo.getId());
		return ResponseEntity.created(uri).body(usuarioSalvo);
	}

	@PutMapping("/{id}")
	public ResponseEntity<UsuarioDto> atualizar(@PathVariable Long id, @Validated @RequestBody UsuarioDto usuarioDTO) {
		UsuarioDto usuarioSalvo = usuarioFacade.atuarizar(id, usuarioDTO);
		return ResponseEntity.ok(usuarioSalvo);
	}
	
	@PutMapping("/{id}/chave-publica")
	@ResponseStatus(HttpStatus.OK)
	public void chavePublica(@PathVariable Long id, @Validated @RequestBody ChaveDto chaveDto) {
		usuarioFacade.atualizarChavePublica(id, chaveDto);
	}
	
}
