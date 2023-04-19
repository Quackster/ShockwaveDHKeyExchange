import java.math.BigInteger;
import java.security.SecureRandom;

/**
 * Class written by Quackster
 * Copyright: 2023
 *
 * <3
 */
public class DHKeyExchangeExample {
    // Static values for p and g
    private static final BigInteger p = new BigInteger("ffffffffffffffffc90fdaa22168c234c4c6628b80dc1cd129024" +
            "e088a67cc74020bbea63b139b22514a08798e3404ddef9519b3cd" +
            "3a431b302b0a6df25f14374fe1356d6d51c245e485b576625e7ec" +
            "6f44c42e9a63a3620ffffffffffffffff", 16);
    private static final BigInteger g = BigInteger.valueOf(2);

    public static void main(String[] args) {
        // Generate private keys for Alice and Bob
        BigInteger alicePrivateKey = generatePrivateKey();
        BigInteger bobPrivateKey = generatePrivateKey();

        // Compute public keys for Alice and Bob
        BigInteger alicePublicKey = computePublicKey(alicePrivateKey);
        BigInteger bobPublicKey = computePublicKey(bobPrivateKey);

        // Compute shared secret key for Alice and Bob
        BigInteger aliceSharedSecret = computeSharedSecret(alicePrivateKey, bobPublicKey);
        BigInteger bobSharedSecret = computeSharedSecret(bobPrivateKey, alicePublicKey);

        // Ensure both shared secrets are the same
        if (!aliceSharedSecret.equals(bobSharedSecret)) {
            throw new RuntimeException("Shared secrets do not match");
        }
        
        System.out.println("alicePrivateKey: " + alicePrivateKey);
        System.out.println("bobPrivateKey: " + bobPrivateKey);
        System.out.println();
        System.out.println("alicePublicKey: " + alicePublicKey);
        System.out.println("bobPublicKey: " + bobPublicKey);
        System.out.println();
        System.out.println("aliceSharedSecret: " + aliceSharedSecret);
        System.out.println("bobSharedSecret: " + bobSharedSecret);
    } 

    private static BigInteger generatePrivateKey() {
        SecureRandom random = new SecureRandom();
        BigInteger privateKey;
        do {
            privateKey = new BigInteger(p.bitLength() - 1, random);
        } while (privateKey.compareTo(BigInteger.ZERO) == 0);
        return privateKey;
    }

    private static BigInteger computePublicKey(BigInteger privateKey) {
        return g.modPow(privateKey, p);
    }

    private static BigInteger computeSharedSecret(BigInteger privateKey, BigInteger publicKey) {
        return publicKey.modPow(privateKey, p);
    }
}
