package org.ferris.clipj.window.tray;

import java.awt.AWTException;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.datatransfer.StringSelection;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import org.apache.log4j.Logger;
import org.ferris.clipj.window.about.About;
import org.ferris.clipj.window.about.AboutMenuItem;
import org.ferris.clipj.window.doubleclick.DoubleClickHandler;
import org.ferris.clipj.window.exit.ExitMenuItem;
import org.ferris.clipj.window.history.History;
import org.ferris.clipj.window.history.HistoryHandler;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
@ApplicationScoped
public class TrayView {

    @Inject
    protected Logger log;

    @Inject
    protected About about;
    
    @Inject
    protected TrayPopupMenu popupMenu;
    
    @Inject
    protected ExitMenuItem exitMenuItem;
    
    @Inject
    protected AboutMenuItem aboutMenuItem;

    @Inject
    protected TrayIcon trayIcon;
    
    @Inject
    protected HistoryHandler historyHandler;
    
    @Inject
    protected DoubleClickHandler doubleClickHandler;
    
    @PostConstruct
    public void putTheUiTogether() {
        log.info("ENTER");
        
        popupMenu.add(aboutMenuItem);
        popupMenu.add(exitMenuItem);
        popupMenu.addSeparator();
               
        History history
            = historyHandler.getHistory();
        
        if (! history.getItems().isEmpty()) {
            history.getItems().forEach(
                hi -> popupMenu.addString(hi.getValue())
            );
        }
        
        trayIcon.setToolTip(String.format("Ferris ClipJ (%s)", about.getVersion()));
        trayIcon.setPopupMenu(popupMenu);
        
        trayIcon.addActionListener(e -> {
            Toolkit.getDefaultToolkit()
                .getSystemClipboard()
                .setContents(
                      new StringSelection(doubleClickHandler.getDoubleClick().getItem())
                    , null 
                );
            trayIcon.displayMessage("Double-click", "Item copied to clipboard", TrayIcon.MessageType.INFO);
        });
    }

    public void startup() {
        log.info("ENTER");

        SystemTray tray
            = SystemTray.getSystemTray();

        try {
            tray.add(trayIcon);
        } catch (AWTException e) {
            throw new RuntimeException(e);
        }
    }

    void viewNewHistoryItem(String newHistoryItem) {
        log.info("ENTER");
        popupMenu.addString(newHistoryItem);
    }

}
