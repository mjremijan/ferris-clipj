package org.ferris.clipj.console.main;

import java.util.Arrays;
import java.util.List;
import javax.enterprise.inject.spi.CDI;
import javax.inject.Inject;
import org.apache.log4j.Logger;

/**
 * The main() method for this application
 *
 * @author <a href="mailto:mjremijan@yahoo.com">Mike Remijan</a>
 */
public class Main {
    public static void main(String[] args) {
        CDI<Object> cdi = CDI.getCDIProvider().initialize();
        
        Main main
            = cdi.select(Main.class).get();
        main.main(Arrays.asList(args));
    }
    
    @Inject
    protected Logger log;
    
    protected void main(List<String> args) {
        log.info("Welcome to ClipJ!");
    }

//    private static Main main;
//
//    private Main() {
//        Thread.setDefaultUncaughtExceptionHandler((t, e) -> {
//            StringWriter sw = new StringWriter();
//            PrintWriter pw = new PrintWriter(sw);
//            e.printStackTrace(pw);
//            sw.toString();
//            JOptionPane.showMessageDialog(null, sw.toString(), "Opps :(", JOptionPane.ERROR_MESSAGE);
//            System.exit(1);
//        });
//
//        // get the SystemTray instance
//        SystemTray tray = SystemTray.getSystemTray();
//
//        // construct a TrayIcon
//        TrayIcon trayIcon = getMyTrayIcon();
//
//        // add the tray image
//        try {
//            tray.add(trayIcon);
//            trayIcon.displayMessage(
//                "Heee-haw!", String.format(
//                    "Ferris ClipJ %s", new About().getVersion()
//                ), TrayIcon.MessageType.NONE
//            );
//        } catch (AWTException e) {
//        }
//
//        Timer timer
//            = new Timer();
//
//        class MyTimerTask extends TimerTask {
//
//            @Override
//            public void run() {
//                try {                    
//                    Clipboard clip 
//                        = Toolkit.getDefaultToolkit().getSystemClipboard();
//
//                    Transferable contents 
//                        = clip.getContents(null);
//
//                    if (contents != null) {
//                        
//                        if (contents.isDataFlavorSupported(DataFlavor.stringFlavor)) {
//                            String str
//                                = (String)contents.getTransferData(DataFlavor.stringFlavor);
//                            ((ClipboardHistoryMenu)trayIcon.getPopupMenu()).addString(str);
//                        }
//                        else
//                        if (contents.isDataFlavorSupported(DataFlavor.imageFlavor)) {
//                            Image img
//                                = (Image)contents.getTransferData(DataFlavor.imageFlavor);
//                        }
//                    }
//                } catch (Exception e) {
//                }
//            }
//        }
//
//        timer.schedule(new MyTimerTask(), 0, 1000 * 2);
//    }
//
//    private Image image;
//
//    private Image getImage() {
//        if (image == null) {
//            image = Toolkit.getDefaultToolkit().createImage(getClass().getResource("/tray.png"));
//        }
//        return image;
//    }
//
//    private TrayIcon myTrayIcon;
//
//    private TrayIcon getMyTrayIcon() {
//        if (myTrayIcon == null) {
//            myTrayIcon = new TrayIcon(getImage(), "ClipJ", new ClipboardHistoryMenu());
//        }
//        myTrayIcon.addActionListener(e -> System.out.println("clicked on clipj"));
//        return myTrayIcon;
//    }
}
