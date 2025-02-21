package Assignment3.src;

import java.math.BigInteger;
import java.util.Random;
import Assignment3.src.utils.MathUtils;

public class RSA {
    private BigInteger p, q, n, phi, e, d;

    public RSA(int bitLength) {
        Random rand = new Random();

        // Generate distinct prime numbers p and q
        do {
            p = BigInteger.probablePrime(bitLength / 2, rand);
        } while (!MathUtils.isPrime(p, 10));

        do {
            q = BigInteger.probablePrime(bitLength / 2, rand);
        } while (!MathUtils.isPrime(q, 10) || p.equals(q)); // Ensure q is distinct from p

        // Compute n and φ(n)
        n = p.multiply(q);
        phi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));

        // Choose e (commonly 65537) and ensure gcd(e, φ(n)) = 1
        e = BigInteger.valueOf(65537);
        if (!MathUtils.gcd(e, phi).equals(BigInteger.ONE)) {
            do {
                e = BigInteger.probablePrime(bitLength / 2, rand);
            } while (!MathUtils.gcd(e, phi).equals(BigInteger.ONE) || e.compareTo(phi) >= 0);
        }

        // Compute d (modular inverse of e)
        d = e.modInverse(phi);
    }

    public BigInteger encrypt(BigInteger message, BigInteger e, BigInteger n) {
        return message.modPow(e, n);
    }

    public BigInteger decrypt(BigInteger ciphertext, BigInteger d, BigInteger n) {
        return ciphertext.modPow(d, n);
    }

    public BigInteger getPublicKey() { return e; }
    public BigInteger getPrivateKey() { return d; }
    public BigInteger getModulus() { return n; }
    public BigInteger getP() { return p; }
    public BigInteger getQ() { return q; }
    public BigInteger getPhi() { return phi; }
}
