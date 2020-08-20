package br.com.bancointer.desafio.interfaces.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class CalculoDto {
	
	private Long id;
	
	@NotBlank
	@Size(min = 3, max = 50)
	private String numero;
	
	@Min(1) @Max(100000)
	private Integer concatenacoes;
	
	@Min(1) @Max(1)
	private Integer resultado;

}

