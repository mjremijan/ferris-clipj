package org.ferris.clipj.window.about;

import javax.enterprise.event.Event;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
public class AboutMenuItemProducer {

    @Inject
    protected Event<AboutEvent> aboutEvent;

    @Produces
    public AboutMenuItem produceAboutMenuItem() {
        AboutMenuItem mi
            = new AboutMenuItem("About");
        mi.addActionListener(e -> aboutEvent.fire(new AboutEvent()));
        return mi;
    }
}
