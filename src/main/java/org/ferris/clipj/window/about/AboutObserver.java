package org.ferris.clipj.window.about;

import javax.enterprise.event.Observes;
import javax.inject.Inject;
import org.slf4j.Logger;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
public class AboutObserver {
    @Inject
    protected Logger log;

    @Inject
    protected AboutView view;

    public void observes(@Observes AboutEvent event) {
        log.info("ENTER");
        view.show();
    }
}
