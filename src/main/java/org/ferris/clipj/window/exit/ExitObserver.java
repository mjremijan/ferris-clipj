package org.ferris.clipj.window.exit;

import javax.enterprise.event.Observes;
import javax.inject.Inject;
import org.slf4j.Logger;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
public class ExitObserver {

    @Inject
    protected Logger log;

    public void observes(@Observes ExitEvent exitEvent) {
        log.info("Exit application");
        System.exit(0);
    }

}
