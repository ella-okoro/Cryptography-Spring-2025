package Assignment1;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.GeneralSecurityException;
import java.util.Base64;
import java.util.Scanner;

public class Okoro_AESDecryption {
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);

            // Get user input for encoded key and ciphertext
            System.out.print("Enter the encoded AES key: ");
            String encodedKey = scanner.nextLine();

            System.out.print("Enter the ciphertext: ");
            String ciphertext = scanner.nextLine();

            scanner.close();

            // Decode the key
            byte[] keyBytes = Base64.getDecoder().decode(encodedKey);
            SecretKey secretKey = new SecretKeySpec(keyBytes, "AES");

            // Initialize Cipher for decryption
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);

            // Decode and Decrypt
            byte[] decryptedData = cipher.doFinal(Base64.getDecoder().decode(ciphertext));

            System.out.println("Decrypted Plaintext: " + new String(decryptedData));

        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }
    }
}
