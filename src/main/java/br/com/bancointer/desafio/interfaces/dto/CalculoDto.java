package br.com.bancointer.desafio.interfaces.dto;

import lombok.Data;

@Data
public class CalculoDto {
	
	private Long id;
	private String numero;
	private Integer concatenacoes;
	private Integer resultado;

}
