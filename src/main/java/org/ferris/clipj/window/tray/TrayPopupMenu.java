package org.ferris.clipj.window.tray;

import java.awt.PopupMenu;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import org.ferris.clipj.window.history.HistoryMenuItem;
import org.slf4j.Logger;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
public class TrayPopupMenu extends PopupMenu {

    @Inject
    protected Logger log;
    
    @Inject
    protected Instance<HistoryMenuItem> instance;
    
    protected TrayPopupMenu() {
        super();
    }

    public void addString(String str) {
        if (getItemCount() == 13) {
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
