package br.com.bancointer.desafio.domain.criptografia.erros;

public class CriptografiaException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
    public CriptografiaException(Throwable cause) {
        super(cause);
    }

}
