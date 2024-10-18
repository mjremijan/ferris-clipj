package org.ferris.clipj.window.security;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.SecureRandom;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
public abstract class Aes {

    // If you don't have the Java Cryptography Extension
    // (JCE) Unlimited Strength packaged installed, use
    // a 128 bit KEY_SIZE.
    public static int KEY_SIZE = 256;
    
    public static int IV_SIZE = 12; // 12bytes * 8 = 96bits
    public static int TAG_BIT_SIZE = 128;
    public static String ALGORITHM_NAME = "AES";
    public static String MODE_OF_OPERATION = "GCM";
    //public static String PADDING_SCHEME = "PKCS5Padding";
    public static String PADDING_SCHEME = "NoPadding"; // switched to NoPadding for Zulu 8 JVM
//    public static byte[] AAD; 
//    static {
//        try { 
//            AAD = "fEris_cLIPj_AAD".getBytes("UTF-8");
//        } catch (UnsupportedEncodingException e) {
//            throw new RuntimeException(String.format("Problem getting the byte[] from the AAD string"), e);
//        }
//    }
    
    protected SecretKey secretKey;
    protected SecureRandom secureRandom;

    protected Aes(SecretKey secretKey) {
        this.secretKey = secretKey;
        this.secureRandom = new SecureRandom();
    }


    public byte[] encrypt(byte[] message) {
        try {
            // Transformation specifies algortihm, mode of operation and padding
            Cipher c = Cipher.getInstance(
                String.format("%s/%s/%s",ALGORITHM_NAME,MODE_OF_OPERATION,PADDING_SCHEME)
            );

            // Generate IV
            byte iv[] = new byte[IV_SIZE];
            secureRandom.nextBytes(iv); // SecureRandom initialized using self-seeding

            // Initialize GCM Parameters
            GCMParameterSpec spec = new GCMParameterSpec(TAG_BIT_SIZE, iv);

            // Init for encryption
            c.init(Cipher.ENCRYPT_MODE, secretKey, spec, secureRandom);

            // AAD
            //c.updateAAD(AAD);

            // I demonstrate 2 different ways of getting the
            // encrypted bytes. See the 2 sub-classes which
            // implement the method of this abstract class.
            ByteArrayOutputStream baos = getEncryptedBytes(message, c);
            
            // Concatinate IV and encrypted bytes.  The IV is needed later
            // in order to to decrypt.  The IV value does not need to be
            // kept secret, so it's OK to encode it in the return value
            //
            // Create a new byte[] the combined length of IV and encryptedBytes
            byte[] ivPlusEncryptedBytes = new byte[iv.length + baos.size()];
            // Copy IV bytes into the new array
            System.arraycopy(iv, 0, ivPlusEncryptedBytes, 0, iv.length);
            // Copy encryptedBytes into the new array
            System.arraycopy(baos.toByteArray(), 0, ivPlusEncryptedBytes, iv.length, baos.size());

            // Return
            return ivPlusEncryptedBytes;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public byte[] decrypt(byte[] ivPlusEncryptedBytes) {

        try {
            // Get IV
            byte iv[] = new byte[IV_SIZE];
            System.arraycopy(ivPlusEncryptedBytes, 0, iv, 0, IV_SIZE);

            // Initialize GCM Parameters
            GCMParameterSpec spec = new GCMParameterSpec(TAG_BIT_SIZE, iv);

            // Transformation specifies algortihm, mode of operation and padding
            Cipher c = Cipher.getInstance(
                String.format("%s/%s/%s",ALGORITHM_NAME,MODE_OF_OPERATION,PADDING_SCHEME)
            );

            // Get encrypted bytes
            byte [] encryptedBytes = new byte[ivPlusEncryptedBytes.length - IV_SIZE];
            System.arraycopy(ivPlusEncryptedBytes, IV_SIZE, encryptedBytes, 0, encryptedBytes.length);

            // Init for decryption
            c.init(Cipher.DECRYPT_MODE, secretKey, spec, secureRandom);

            // Add AAD tag data if present
            //c.updateAAD(AAD);

            // Add message to decrypt
            c.update(encryptedBytes);

            // Decrypt
            byte[] decryptedBytes
                = c.doFinal();

            // Return
            return decryptedBytes;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    
    /**
     * An abstract method to be implemented by a subclass following the 
     * Gang of four <a href="https://www.digitalocean.com/community/tutorials/template-method-design-pattern-in-java">Template Method Design Pattern</a>.
     * This method is to use the {@link Cipher} provided to return the 
     * encrypted bytes of the {@link message} parameter.
     * 
     * @param message The bytes to be encrypted.
     * @param cipher  The Cipher object used to encrypt the message.
     * @return
     * @throws BadPaddingException
     * @throws IllegalBlockSizeException
     * @throws IOException 
     */
    abstract ByteArrayOutputStream getEncryptedBytes(byte[] message, Cipher cipher) throws BadPaddingException, IllegalBlockSizeException, IOException;
}
