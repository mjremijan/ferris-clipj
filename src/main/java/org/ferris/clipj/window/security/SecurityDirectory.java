package org.ferris.clipj.window.security;

import java.io.File;
import javax.inject.Inject;
import org.ferris.clipj.window.application.*;

public class SecurityDirectory extends File {

    private static final long serialVersionUID = 7491901906021288631L;

    @Inject
    public SecurityDirectory(ApplicationDirectory appdir) {
        super(appdir, "security");
        if (! exists()) {
            throw new RuntimeException(String.format("Security directory does not exist \"%s\"", getAbsolutePath()));
        }
    }
}
