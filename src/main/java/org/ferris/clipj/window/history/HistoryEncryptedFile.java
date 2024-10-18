package org.ferris.clipj.window.history;

import java.io.File;
import javax.inject.Inject;
import org.ferris.clipj.window.data.*;

public class HistoryEncryptedFile extends File {

    private static final long serialVersionUID = 7491901906021288631L;

    @Inject
    public HistoryEncryptedFile(DataDirectory datadir) {
        super(datadir, "clipj-history.bin");        
    }
}
