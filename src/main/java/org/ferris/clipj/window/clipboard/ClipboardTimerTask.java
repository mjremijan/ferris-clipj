package org.ferris.clipj.window.clipboard;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.util.TimerTask;
import javax.inject.Inject;
import org.ferris.clipj.window.history.HistoryHandler;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
public class ClipboardTimerTask extends TimerTask {

    private String cache;

    private Clipboard clip;

    @Inject
    protected HistoryHandler historyHandler;

    public ClipboardTimerTask() {
        clip = Toolkit.getDefaultToolkit().getSystemClipboard();
        if (clip == null) {
            throw new RuntimeException("Toolkit got null for the system clipboard");
        }
    }

    @Override
    public synchronized void run() {
        try {
            Transferable contents
                = clip.getContents(null);

            if (contents != null) {
                if (contents.isDataFlavorSupported(DataFlavor.stringFlavor)) {
                    String str
                        = (String) contents.getTransferData(DataFlavor.stringFlavor);
                    
                    if (str != null) {
                        if (cache == null) {
                            cache = str;
                        } 
                        else if (!cache.equals(str)) {
                            cache = str;
                            historyHandler.addToHistory(str);
                        }
                    }
                } 
//                else if (contents.isDataFlavorSupported(DataFlavor.imageFlavor)) {
//                    Image img
//                        = (Image) contents.getTransferData(DataFlavor.imageFlavor);
//                }
            }
        } catch (IllegalStateException e) {
            // Do nothing
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

}
