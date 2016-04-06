package org.ferris.clipj.window.tray;

import javax.enterprise.inject.Produces;
import org.ferris.clipj.window.about.AboutMenuItem;
import org.ferris.clipj.window.exit.ExitMenuItem;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
public class TrayPopupMenuProducer {
    @Produces
    public TrayPopupMenu produce(ExitMenuItem exitMenuItem, AboutMenuItem aboutMenuItem) {
        return new TrayPopupMenu(exitMenuItem, aboutMenuItem);
    }
}
