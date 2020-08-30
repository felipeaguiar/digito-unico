package br.com.bancointer.desafio.domain.usuario;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@ToString
@NoArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode(of = "id")
public class Usuario {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Getter
	private Long id;
	
	@Column(length = 50, nullable = false)
	@NonNull
	@Getter @Setter
	private String nome;
	
	@Column(length = 50, nullable = false, unique = true)
	@NonNull
	@Getter @Setter
	private String email;
	
	@Column(length = 512)
	@Getter @Setter
	private String chavePublica;
	
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @JoinColumn(name = "id_usuario")
    private List<Calculo> calculos = new ArrayList<>();
    
	public void adicionarCalculo(Calculo calculo) {
		calculos.add(calculo);
	}

	public void removerCalculo(Calculo calculo) {
		calculos.remove(calculo);
	}

	public List<Calculo> getCalculos() {
		return Collections.unmodifiableList(calculos);
	}
	

}
