package org.ferris.clipj.window.tray;

import java.awt.PopupMenu;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import org.apache.log4j.Logger;
import org.ferris.clipj.window.configuration.PreferenceKey;
import org.ferris.clipj.window.history.HistoryMenuItem;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
public class TrayPopupMenu extends PopupMenu {

    @Inject
    protected Logger log;
    
    @Inject
    protected Instance<HistoryMenuItem> instance;
    
    @Inject @PreferenceKey("MaxHistorySize")
    protected Integer maxHistorySize;
    
    protected TrayPopupMenu() {
        super();
    }

    public void addString(String str) {
        if (getItemCount() == (maxHistorySize + 3)) {
            remove(getItemCount() - 1);
        }

        HistoryMenuItem hmi 
            = instance.get();
        hmi.init(str);
        
        insert(hmi, 3);
        
        for (int i = 3; i < getItemCount(); i++) {
            HistoryMenuItem smi = (HistoryMenuItem)getItem(i);
            smi.setLabel(i - 2);
        }
    }
}
