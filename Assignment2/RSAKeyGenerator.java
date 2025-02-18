package Assignment2;
import java.security.*;
import java.io.*;

public class RSAKeyGenerator {
    private static final String PUBLIC_KEY_FILE = "publicKey";
    private static final String PRIVATE_KEY_FILE = "privateKey";

    public static void generateAndStoreKeys() throws Exception {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(2048);
        KeyPair keyPair = keyGen.generateKeyPair();

        try (ObjectOutputStream publicOut = new ObjectOutputStream(new FileOutputStream(PUBLIC_KEY_FILE));
             ObjectOutputStream privateOut = new ObjectOutputStream(new FileOutputStream(PRIVATE_KEY_FILE))) {
            publicOut.writeObject(keyPair.getPublic());
            privateOut.writeObject(keyPair.getPrivate());
        }
    }

    public static PublicKey loadPublicKey() throws Exception {
        try (ObjectInputStream publicIn = new ObjectInputStream(new FileInputStream("publicKey"))) {
            return (PublicKey) publicIn.readObject();
        }
    }

    public static PrivateKey loadPrivateKey() throws Exception {
        try (ObjectInputStream privateIn = new ObjectInputStream(new FileInputStream("privateKey"))) {
            return (PrivateKey) privateIn.readObject();
        }
    }
}
