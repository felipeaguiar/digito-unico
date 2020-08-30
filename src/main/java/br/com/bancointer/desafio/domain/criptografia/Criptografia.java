package br.com.bancointer.desafio.domain.criptografia;

import org.springframework.stereotype.Component;

@Component
public interface Criptografia {

	public String encrypt(String data, String publicKey);

	public String decrypt(String data, String privateKey);

}