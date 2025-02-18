package Assignment1;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Scanner;

public class Okoro_AESEncryption {
    public static void main(String[] args) {
        try {

            Scanner scan = new Scanner(System.in);

            System.out.println("Enter the information you want to be encrypted: ");
            String inputData = scan.nextLine();

            // Generate AES Key
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            keyGenerator.init(128);
            SecretKey secretKey = keyGenerator.generateKey();

            // Encode key in Base64
            String encodedKey = Base64.getEncoder().encodeToString(secretKey.getEncoded());

            // Create SecretKey from encoded key
            byte[] keyBytes = Base64.getDecoder().decode(encodedKey);
            SecretKeySpec keySpec = new SecretKeySpec(keyBytes, "AES");

            // Initialize Cipher for Encryption
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, keySpec);

            // Encrypt Data
            byte[] encryptedData = cipher.doFinal(inputData.getBytes());

            // Convert to Base64
            String ciphertext = Base64.getEncoder().encodeToString(encryptedData);

            // Print the Key and Ciphertext
            System.out.println();
            System.out.println("Encoded AES Key: " + encodedKey);
            System.out.println();
            System.out.println("Ciphertext: " + ciphertext);
            System.out.println();
            System.out.println("Next Step: Go the file labeled Okoro_AESDecryption.java and enter\nin the the AES and Ciphertext codes generated for you here.");

            scan.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
