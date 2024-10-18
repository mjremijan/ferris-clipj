package org.ferris.clipj.window.security;

import org.ferris.clipj.window.io.ByteArrayReader;
import org.ferris.clipj.window.io.ByteArrayWriter;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
public class AesSecretKeyProducer {

    @Inject
    AesSecretKeyFile secretKeyFile;

    @Produces
    public SecretKey produce() {
        return secretKeyFile.exists() ? read() : save(create());
    }

    public SecretKey read() {
        try {
            return new SecretKeySpec(new ByteArrayReader(secretKeyFile.toPath()).read(), "AES");
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    SecretKey save(SecretKey secretKey) {
        ByteArrayWriter writer = new ByteArrayWriter(secretKeyFile.toPath());
        writer.write(secretKey.getEncoded());
        return secretKey;
    }

    SecretKey create() {
        KeyGenerator keyGen;
        try {
            keyGen = KeyGenerator.getInstance("AES");
            keyGen.init(256);
            SecretKey secretKey = keyGen.generateKey();
            return secretKey;
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
