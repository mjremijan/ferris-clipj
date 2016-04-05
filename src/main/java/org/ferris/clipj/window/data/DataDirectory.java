package org.ferris.clipj.window.data;

import java.io.File;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import org.ferris.clipj.window.application.*;

@ApplicationScoped
public class DataDirectory extends File {

    private static final long serialVersionUID = 7491901906021288631L;

    @Inject
    public DataDirectory(ApplicationDirectory appdir) {
        super(appdir, "data");
        if (! exists()) {
            throw new RuntimeException(String.format("Data directory does not exist \"%s\"", getAbsolutePath()));
        }
    }
}
