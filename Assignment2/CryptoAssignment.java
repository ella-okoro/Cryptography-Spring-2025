package Assignment2;
import java.security.*;
import java.util.Scanner;

public class CryptoAssignment {
    public static void main(String[] args) {
        try {
            // Generate and store RSA keys (run only once)
            RSAKeyGenerator.generateAndStoreKeys();

            // Load existing keys
            PublicKey publicKey = RSAKeyGenerator.loadPublicKey();
            PrivateKey privateKey = RSAKeyGenerator.loadPrivateKey();

            // Get user input
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter a message to encrypt and sign: ");
            String userMessage = scanner.nextLine();

            // Encrypt the message
            String encryptedMessage = RSAEncryption.encrypt(userMessage, publicKey);
            System.out.println("\nEncrypted Message: " + encryptedMessage);

            // Sign the message
            String signature = RSASignature.sign(userMessage, privateKey);
            System.out.println("\nDigital Signature: " + signature);

            // Simulate decryption and verification
            System.out.println("\nDecrypting and Verifying...\n");

            String decryptedMessage = RSAEncryption.decrypt(encryptedMessage, privateKey);
            System.out.println("Decrypted Message: " + decryptedMessage);

            boolean isVerified = RSASignature.verify(userMessage, signature, publicKey);
            System.out.println("\nSignature Verified: " + isVerified);

            scanner.close(); // Close scanner

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
