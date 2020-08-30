package br.com.bancointer.desafio.domain.criptografia;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

import javax.crypto.Cipher;

import org.springframework.stereotype.Component;

import br.com.bancointer.desafio.domain.criptografia.erros.CriptografiaException;

@Component
public class CriptografiaRsa implements Criptografia {
	
	private static final String ALGORITHM = "RSA";
	private static final String TRANSFORMATION = "RSA/ECB/OAEPWithSHA-1AndMGF1Padding";
	
	@Override
	public String encrypt(String data, String publicKey) {	
		try {
			Cipher cipher = Cipher.getInstance(TRANSFORMATION);
			cipher.init(Cipher.ENCRYPT_MODE, getPublicKey(publicKey));
			byte[] encrypted = cipher.doFinal(data.getBytes());
			return encode(encrypted);
		} catch (Exception e) {
			throw new CriptografiaException(e);
		}
		
	}
	
	@Override
	public String decrypt(String data, String privateKey) {
		try {
			Cipher cipher = Cipher.getInstance(TRANSFORMATION);
	        cipher.init(Cipher.DECRYPT_MODE, getPrivateKey(privateKey));
	        
	        byte[] decrypted = cipher.doFinal(decode(data));
	        return new String(decrypted);
		} catch (Exception e) {
			throw new CriptografiaException(e);
		}
    }
	
	private PublicKey getPublicKey(String base64PublicKey) throws NoSuchAlgorithmException, InvalidKeySpecException {
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(decode(base64PublicKey));
        KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
        return keyFactory.generatePublic(keySpec);
    }
	
	private PrivateKey getPrivateKey(String base64PrivateKey) throws InvalidKeySpecException, NoSuchAlgorithmException {
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(decode(base64PrivateKey));
        KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
        return keyFactory.generatePrivate(keySpec);
    }
	
	private byte[] decode(String data) {
		return Base64.getDecoder().decode(data.getBytes());
	}
	
	private String encode(byte[] data) {
		byte[] encoded = Base64.getEncoder().encode(data);
		return new String(encoded);
	}

}
