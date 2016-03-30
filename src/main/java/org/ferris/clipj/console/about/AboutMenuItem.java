package org.ferris.clipj.console.about;

import java.awt.MenuItem;

/**
 * 
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
public class AboutMenuItem extends MenuItem {

    private static final long serialVersionUID = 6379912347790948335L;

    public AboutMenuItem() {
        super("About");
        addActionListener(e -> new AboutDialog(null).setVisible(true));
    }
}
