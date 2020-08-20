package br.com.bancointer.desafio.application;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.bancointer.desafio.application.errors.UsuarioDuplicadoException;
import br.com.bancointer.desafio.domain.usuario.Usuario;
import br.com.bancointer.desafio.domain.usuario.UsuarioRepository;

@Service
public class UsuarioService {
	
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	UsuarioService(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}

	public Usuario buscarPorId(Long id) {
		return usuarioRepository.findById(id).orElseThrow(() -> new EmptyResultDataAccessException(1));
	}

	public void remover(Long id) {
		usuarioRepository.deleteById(id);	
	}

	public Usuario salvar(Usuario usuario) {
		Optional<Usuario> usuarioSalvo = usuarioRepository.findByEmail(usuario.getEmail());
		if (usuarioSalvo.isPresent()) {
			throw new UsuarioDuplicadoException();
		}
		return usuarioRepository.save(usuario);
	}

	public Usuario atualizar(Long id, Usuario usuario) {
		Usuario usuarioSalvo = buscarPorId(id);
		BeanUtils.copyProperties(usuario, usuarioSalvo, "id");
		return usuarioRepository.save(usuarioSalvo);
	}


}
