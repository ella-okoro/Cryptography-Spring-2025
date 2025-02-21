package Assignment3.src;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            
            // Ensure output directory exists
            File outputDir = new File("Assignment3/output");
            if (!outputDir.exists()) {
                outputDir.mkdirs(); // Create directory if it doesn't exist
            }

            // Key Generation
            RSA rsa = new RSA(1024);
            BigInteger e = rsa.getPublicKey();
            BigInteger d = rsa.getPrivateKey();
            BigInteger n = rsa.getModulus();

            // Write key generation output to key_generation.txt
            FileWriter keyWriter = new FileWriter("Assignment3/output/key_generation.txt");
            keyWriter.write("Key Generation:\n");
            keyWriter.write("----------------\n");
            keyWriter.write("Prime p: " + rsa.getP() + "\n");
            keyWriter.write("Prime q: " + rsa.getQ() + "\n");
            keyWriter.write("Modulus n: " + n + "\n");
            keyWriter.write("Totient φ(n): " + rsa.getPhi() + "\n");
            keyWriter.write("Public Key (e, n): (" + e + ", " + n + ")\n");
            keyWriter.write("Private Key (d, n): (" + d + ", " + n + ")\n");
            keyWriter.close();

            // Encryption
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter a message to encrypt: ");
            String message = scanner.nextLine();
            scanner.close(); // Close scanner after use

            BigInteger plaintext = new BigInteger(message.getBytes());
            BigInteger ciphertext = rsa.encrypt(plaintext, e, n);

            // Write encryption output to encryption.txt
            FileWriter encWriter = new FileWriter("Assignment3/output/encryption.txt");
            encWriter.write("Encryption:\n");
            encWriter.write("-----------\n");
            encWriter.write("Original Message: " + message + "\n");
            encWriter.write("Plaintext (Numerical): " + plaintext + "\n");
            encWriter.write("Ciphertext: " + ciphertext + "\n");
            encWriter.close();

            // Decryption
            BigInteger decryptedMessage = rsa.decrypt(ciphertext, d, n);
            String originalMessage = new String(decryptedMessage.toByteArray());

            // Write decryption output to decryption.txt
            FileWriter decWriter = new FileWriter("Assignment3/output/decryption.txt");
            decWriter.write("Decryption:\n");
            decWriter.write("-----------\n");
            decWriter.write("Ciphertext: " + ciphertext + "\n");
            decWriter.write("Decrypted Numerical: " + decryptedMessage + "\n");
            decWriter.write("Original Message: " + originalMessage + "\n");
            decWriter.close();

            System.out.println("Encryption and decryption completed successfully!");

        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file.");
            e.printStackTrace();
        }
    }
}
