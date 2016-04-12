package org.ferris.clipj.window.doubleclick;

import java.io.File;
import javax.inject.Inject;
import org.ferris.clipj.window.data.*;

public class DoubleClickXmlFile extends File {

    private static final long serialVersionUID = 7491901906021288631L;

    @Inject
    public DoubleClickXmlFile(DataDirectory datadir) {
        super(datadir, "clipj-double-click.xml");
        if (! exists()) {
            throw new RuntimeException(String.format("The double-click xml data file does not exist \"%s\"", getAbsolutePath()));
        }
    }
}
