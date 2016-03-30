package org.ferris.clipj.main;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JOptionPane;
import org.ferris.clipj.about.About;
import org.ferris.clipj.about.AboutMenuItem;
import org.ferris.clipj.exit.ExitMenuItem;

/**
 * The main() method for this application
 *
 * @author <a href="mailto:mjremijan@yahoo.com">Mike Remijan</a>
 */
public class Main {

    /**
     * @param args
     */
    public static void main(String[] args) {
        main = new Main();
    }

    private static Main main;

    private Main() {
        Thread.setDefaultUncaughtExceptionHandler((t, e) -> {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            sw.toString();
            JOptionPane.showMessageDialog(null, sw.toString(), "Opps :(", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        });

        // get the SystemTray instance
        SystemTray tray = SystemTray.getSystemTray();

        // construct a TrayIcon
        TrayIcon trayIcon = getMyTrayIcon();

        // add the tray image
        try {
            tray.add(trayIcon);
            trayIcon.displayMessage(
                "Heee-haw!", String.format(
                    "Ferris ClipJ %s", new About().getVersion()
                ), TrayIcon.MessageType.NONE
            );
        } catch (AWTException e) {
        }

        Timer timer
            = new Timer();

        class MyTimerTask extends TimerTask {

            @Override
            public void run() {
                Clipboard clip = Toolkit.getDefaultToolkit().getSystemClipboard();
                System.out.println("--------------------------");
                System.out.println("Object Name: " + clip.getName());
                Transferable contents = clip.getContents(null);
                if (contents == null) {
                    System.out.println("The clipboard is empty.");
                } else {

                }
                try {
                    System.out.println(contents.getTransferData(DataFlavor.stringFlavor));
                } catch (UnsupportedFlavorException ex) {
                } catch (IOException ex) {
                }
                System.out.println("==========================");
            }
        }

        timer.schedule(new MyTimerTask(), 0, 1000 * 2);
    }

    private Image image;

    private Image getImage() {
        if (image == null) {
            image = Toolkit.getDefaultToolkit().createImage(getClass().getResource("/tray.png"));
        }
        return image;
    }

    private TrayIcon myTrayIcon;

    private TrayIcon getMyTrayIcon() {
        if (myTrayIcon == null) {
            myTrayIcon = new TrayIcon(getImage(), "ClipJ", getPopupMenu());
        }
        myTrayIcon.addActionListener(e -> System.out.println("clicked on clipj"));
        return myTrayIcon;
    }

    private PopupMenu popupMenu;

    private PopupMenu getPopupMenu() {
        PopupMenu popup = new PopupMenu();
        {
            popup.addSeparator();
            popup.add(new AboutMenuItem());
            popup.add(new ExitMenuItem());
        }
        return popup;
    }
}
