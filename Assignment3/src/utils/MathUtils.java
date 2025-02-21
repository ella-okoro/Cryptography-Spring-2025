package Assignment3.src.utils;

import java.math.BigInteger;

public class MathUtils {
    public static BigInteger gcd(BigInteger a, BigInteger b) {
        return a.gcd(b);
    }

    public static boolean isPrime(BigInteger num, int certainty) {
        return num.isProbablePrime(certainty);
    }
}