package br.com.bancointer.desafio.interfaces.dto;

import lombok.Data;

@Data
public class UsuarioDto {
	
	private Long id;
	private String nome;
	private String email;

}
