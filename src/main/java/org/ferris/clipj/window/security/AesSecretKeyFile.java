package org.ferris.clipj.window.security;

import java.io.File;
import javax.inject.Inject;

public class AesSecretKeyFile extends File {

    private static final long serialVersionUID = 7491901906021288631L;

    @Inject
    public AesSecretKeyFile(SecurityDirectory securityDir) {
        super(securityDir, "Aes256.key");
    }
}
