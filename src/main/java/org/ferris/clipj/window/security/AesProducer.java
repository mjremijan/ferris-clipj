package org.ferris.clipj.window.security;

import javax.crypto.SecretKey;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;

public class AesProducer {
    
    @Inject
    SecretKey secretKey;
     
    @Produces
    public Aes getAes() {
        return new AesUsingSinglePartEncryption(secretKey);        
    }
}
