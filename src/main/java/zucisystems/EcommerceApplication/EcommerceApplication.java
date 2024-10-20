package zucisystems.EcommerceApplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;

@SpringBootApplication
public class EcommerceApplication {

	public static void main(String[] args) {
//		try {
//			// Create a KeyGenerator for HMAC
//			KeyGenerator keyGen = KeyGenerator.getInstance("HmacSHA512");
//			keyGen.init(512); // Key size: 512 bits
//			SecretKey secretKey = keyGen.generateKey();
//
//			// Optionally, print the key in Base64 format for storage or configuration
//			String base64Key = java.util.Base64.getEncoder().encodeToString(secretKey.getEncoded());
//			System.out.println("Generated Secret Key (Base64): " + base64Key);
//		} catch (NoSuchAlgorithmException e) {
//			e.printStackTrace();
//		}
		SpringApplication.run(EcommerceApplication.class, args);
	}

}
