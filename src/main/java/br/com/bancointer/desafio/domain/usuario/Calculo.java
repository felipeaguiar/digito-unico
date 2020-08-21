package br.com.bancointer.desafio.domain.usuario;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@ToString
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@RequiredArgsConstructor
public class Calculo {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Getter
	private Long id;
	
	@Column(length = 20, nullable = false)
	@NonNull
	@Getter @Setter
	private String numero;
	
	@Column(precision = 6, scale = 0)
	@NonNull
	@Getter @Setter
	private Integer repeticoes;
	
	@Column(precision = 1, scale = 0)
	@NonNull
	@Getter @Setter
	private Integer resultado;

}
