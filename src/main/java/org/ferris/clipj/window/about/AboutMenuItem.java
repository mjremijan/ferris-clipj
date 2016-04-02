package org.ferris.clipj.window.about;

import java.awt.MenuItem;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;

/**
 * 
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
@ApplicationScoped
public class AboutMenuItem extends MenuItem {

    private static final long serialVersionUID = 6379912347790948335L;
    
    @Inject
    protected Event<AboutEvent> aboutEvent;
    
    public AboutMenuItem() {
        super("About");
        addActionListener(e -> aboutEvent.fire(new AboutEvent()));
    }
}
