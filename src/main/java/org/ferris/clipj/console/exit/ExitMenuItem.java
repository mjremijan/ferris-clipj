package org.ferris.clipj.console.exit;

import java.awt.MenuItem;

/**
 * 
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
public class ExitMenuItem extends MenuItem {

    private static final long serialVersionUID = 6379912347790948335L;

    public ExitMenuItem() {
        super("Exit");
        addActionListener(e -> System.exit(0));
    }
}
