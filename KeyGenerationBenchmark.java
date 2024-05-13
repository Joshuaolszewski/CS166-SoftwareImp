import java.security.*;
import java.util.concurrent.TimeUnit;
import java.security.spec.ECGenParameterSpec;

public class KeyGenerationBenchmark {
    public static void main(String[] args) {
        int dhSize = 3072;
        int rsaSize = 1024;
        int numIterations = 1000;

        long dhStartTime = System.nanoTime();
        for (int i = 0; i < numIterations; i++) {
            try {
                KeyPairGenerator dhKeyGen = KeyPairGenerator.getInstance("DiffieHellman");
                dhKeyGen.initialize(dhSize);
                KeyPair dhKeyPair = dhKeyGen.generateKeyPair();
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        }
        long dhEndTime = System.nanoTime();
        long dhElapsedTime = TimeUnit.NANOSECONDS.toMillis(dhEndTime - dhStartTime);
        System.out.println("Diffie-Hellman Key Generation Time: " + dhElapsedTime + " ms");

        long rsaStartTime = System.nanoTime();
        for (int i = 0; i < numIterations; i++) {
            try {
                KeyPairGenerator rsaKeyGen = KeyPairGenerator.getInstance("RSA");
                rsaKeyGen.initialize(rsaSize);
                KeyPair rsaKeyPair = rsaKeyGen.generateKeyPair();
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        }
        long rsaEndTime = System.nanoTime();
        long rsaElapsedTime = TimeUnit.NANOSECONDS.toMillis(rsaEndTime - rsaStartTime);
        System.out.println("RSA Key Generation Time: " + rsaElapsedTime + " ms");

        long ecStartTime = System.nanoTime();
        for (int i = 0; i < numIterations; i++) {
            try {
                KeyPairGenerator ecKeyGen = KeyPairGenerator.getInstance("EC");
                ecKeyGen.initialize(new ECGenParameterSpec("secp256r1"));
                KeyPair ecKeyPair = ecKeyGen.generateKeyPair();
            } catch (NoSuchAlgorithmException | InvalidAlgorithmParameterException e) {
                e.printStackTrace();
            }
        }
        long ecEndTime = System.nanoTime();
        long ecElapsedTime = TimeUnit.NANOSECONDS.toMillis(ecEndTime - ecStartTime);
        System.out.println("Elliptic Curve Key Generation Time: " + ecElapsedTime + " ms");
    }
}