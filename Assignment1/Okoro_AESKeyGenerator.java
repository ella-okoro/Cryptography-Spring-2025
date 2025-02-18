package Assignment1;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
    //aes key generator
    public class Okoro_AESKeyGenerator {
        public static String generateAESKey() throws NoSuchAlgorithmException {
            //generating a 128 bit aes key
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            keyGenerator.init(128);
            SecretKey secretKey = keyGenerator.generateKey();
            return Base64.getEncoder().encodeToString(secretKey.getEncoded());
        }
    }
