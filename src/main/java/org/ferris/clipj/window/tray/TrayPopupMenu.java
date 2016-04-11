package org.ferris.clipj.window.tray;

import java.awt.PopupMenu;
import javax.inject.Inject;
import org.apache.log4j.Logger;
import org.ferris.clipj.window.menu.StringMenuItem;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
public class TrayPopupMenu extends PopupMenu {

    @Inject
    protected Logger log;
    
    protected TrayPopupMenu() {
        super();
    }

    public void addString(String str) {
        if (getItemCount() == 13) {
            remove(getItemCount() - 1);
        }

        insert(new StringMenuItem((str)), 3);
        
        for (int i = 3; i < getItemCount(); i++) {
            StringMenuItem smi = (StringMenuItem)getItem(i);
            smi.setLabel(i - 2);
        }
    }
}
