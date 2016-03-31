package org.ferris.clipj.window.exit;

import java.awt.MenuItem;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
@ApplicationScoped
public class ExitMenuItem extends MenuItem {

    private static final long serialVersionUID = 6379912347790948335L;

    @Inject
    protected Event<ExitEvent> exitEvent;

    public ExitMenuItem() {
        super("Exit");
        addActionListener(e -> exitEvent.fire(new ExitEvent()));
    }

}
