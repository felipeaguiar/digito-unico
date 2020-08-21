package br.com.bancointer.desafio.interfaces.facade;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.bancointer.desafio.application.UsuarioService;
import br.com.bancointer.desafio.domain.usuario.Usuario;
import br.com.bancointer.desafio.interfaces.dto.CalculoDto;
import br.com.bancointer.desafio.interfaces.dto.UsuarioDto;

@Component
public class UsuarioFacade {
	
	private UsuarioService usuarioService;
	
	@Autowired
	public UsuarioFacade(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}
	
	public UsuarioDto buscarPorId(Long id) {
		Usuario usuario = usuarioService.buscarPorId(id);
		return toDto(usuario);
	}

	public void remover(Long id) {
		usuarioService.remover(id);
	}
	
	public UsuarioDto salvar(UsuarioDto usuarioDTO) {
		Usuario usuario = fromDto(usuarioDTO);
		
		Usuario usuarioSalvo = usuarioService.salvar(usuario);

		return toDto(usuarioSalvo);
	}
	
	public UsuarioDto atuarizar(Long id, UsuarioDto usuarioDTO) {
		Usuario usuario = fromDto(usuarioDTO);
		Usuario usuarioSalvo = usuarioService.atualizar(id, usuario);

		return toDto(usuarioSalvo);
	}
	
	
	private UsuarioDto toDto(Usuario usuario) {
		UsuarioDto usuarioDTO = new UsuarioDto();
		usuarioDTO.setId(usuario.getId());
		usuarioDTO.setNome(usuario.getNome());
		usuarioDTO.setEmail(usuario.getEmail());
		return usuarioDTO;
	}
	
	private Usuario fromDto(UsuarioDto usuarioDTO) {
		return new Usuario(usuarioDTO.getNome(), usuarioDTO.getEmail());
	}

	public List<CalculoDto> calculos(Long id) {
		Usuario usuario = usuarioService.buscarPorId(id);
		
		return usuario.getCalculos().stream()
			.map(c -> {
				CalculoDto calculoDto = new CalculoDto();
				calculoDto.setNumero(c.getNumero());
				calculoDto.setRepeticoes(c.getRepeticoes());
				calculoDto.setResultado(c.getResultado());
				return calculoDto;
			})
			.collect(Collectors.toList());
	}

}
