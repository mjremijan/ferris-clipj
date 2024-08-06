package org.ferris.clipj.window.about;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import org.slf4j.Logger;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
public class AboutView {

    @Inject
    protected Logger log;

    @Inject
    protected About about;

    private AboutDialog dialog;

    @PostConstruct
    public void postConstruct() {
        dialog = new AboutDialog(about);
    }

    public void show() {
        dialog.setVisible(true);
    }
}
