package org.ferris.clipj.window.doubleclick;

import java.io.File;
import javax.inject.Inject;
import org.ferris.clipj.window.data.*;

public class DoubleClickEncryptedFile extends File {

    private static final long serialVersionUID = 7491901906021288631L;

    @Inject
    public DoubleClickEncryptedFile(DataDirectory datadir) {
        super(datadir, "clipj-double-click.bin");
    }
}
