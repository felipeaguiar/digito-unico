package br.com.bancointer.desafio.domain.calculadora;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CalculadoraDigitoUnicoFactory {
	
    @Bean
    public CalculadoraDigitoUnico processador() {
        return new Cache(new CalculadoraDigitoUnicoImpl());
    }

}
