package br.com.bancointer.desafio.interfaces.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@JsonInclude(Include.NON_NULL)
public class CalculoDto {
	
	private Long id;
	
	@NotBlank
	@Size(min = 1, max=1000001)
	@Pattern(regexp = "^[1-9]{1,1000000}?$|^1[0]{1000000}$")
	private String numero;
	
	@Min(1) @Max(100000)
	private Integer repeticoes;
	
	@Min(1) @Max(1)
	private Integer resultado;
	
	@Min(1)
	private Long usuario;

}

