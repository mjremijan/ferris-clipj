package org.ferris.clipj.window.configuration;

import java.io.File;
import javax.inject.Inject;
import org.ferris.clipj.window.application.*;

public class ConfigurationDirectory extends File {

    private static final long serialVersionUID = 859190458621288475L;

    @Inject
    public ConfigurationDirectory(ApplicationDirectory appdir) {
        super(appdir, "conf");
        if (! exists()) {
            throw new RuntimeException(
                String.format(
                      "Configuration directory does not exist \"%s\""
                    , getAbsolutePath()
                )
            );
        }
    }
}
