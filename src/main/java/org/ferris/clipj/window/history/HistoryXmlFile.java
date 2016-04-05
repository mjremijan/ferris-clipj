package org.ferris.clipj.window.history;

import java.io.File;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import org.ferris.clipj.window.data.*;

@ApplicationScoped
public class HistoryXmlFile extends File {

    private static final long serialVersionUID = 7491901906021288631L;

    @Inject
    public HistoryXmlFile(DataDirectory datadir) {
        super(datadir, "history.xml");
        if (! exists()) {
            throw new RuntimeException(String.format("The history xml data file does not exist \"%s\"", getAbsolutePath()));
        }
    }
}
