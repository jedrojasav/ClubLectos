package Utils;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.spec.AlgorithmParameterSpec;
import java.util.Base64;

/**
 *
 * @author jeff
 */
public class Crypt {
    
    private  final String ALGORITHM = "AES";
    private  final String MODE = "CBC";
    private  final String PADDING = "PKCS5Padding";
    private  final String TRANSFORMATION = ALGORITHM + "/" + MODE + "/" + PADDING;
    private  final byte[] IV = { 0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08, 0x09, 0x0a, 0x0b, 0x0c, 0x0d, 0x0e, 0x0f };
    private String key = "X8n5pDf3RtQw2YeS";

     public byte[] encrypt(String input, String key) throws Exception {
        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(), ALGORITHM);
        AlgorithmParameterSpec ivParameterSpec = new IvParameterSpec(IV);
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);
        return cipher.doFinal(input.getBytes());
    }

    public String decrypt(byte[] encryptedBytes, String key) throws Exception {
        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(), ALGORITHM);
        AlgorithmParameterSpec ivParameterSpec = new IvParameterSpec(IV);
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);
        byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
        return new String(decryptedBytes);
    }
    
    public String encriptar(String originalString) throws Exception {
        // Encrypt the string
        byte[] encryptedBytes = encrypt(originalString, key);
        String encryptedString = Base64.getEncoder().encodeToString(encryptedBytes);
        System.out.println("Encrypted String: " + encryptedString);
        return encryptedString;
    }
    
    public String decriptar(String encryptedString) throws Exception{
        // Decrypt the string
        String decryptedString = decrypt(Base64.getDecoder().decode(encryptedString), key);
        System.out.println("Decrypted String: " + decryptedString);
        return decryptedString;
    }
}
