package org.ferris.clipj.menu;

import java.awt.PopupMenu;
import java.util.HashSet;
import java.util.Set;
import org.ferris.clipj.about.AboutMenuItem;
import org.ferris.clipj.exit.ExitMenuItem;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
public class ClipboardHistoryMenu extends PopupMenu {

    Set<String> uniqueStrings;        
    
    public ClipboardHistoryMenu() {
        super();
        add(new AboutMenuItem());
        add(new ExitMenuItem());
        addSeparator();
        
        uniqueStrings
            = new HashSet<>();
    }
    
    
    public void addString(String str) {
        if (! uniqueStrings.contains(str)) {
            uniqueStrings.add(str);
            
            int size 
                = getItemCount();            
            
            if (size == 13) {
                remove(size - 1);
            }
            
            insert(new StringMenuItem((str)), 3);
            
            for (int i=3; i<=getItemCount(); i++) {
                ((StringMenuItem)getItem(i)).setLabel(i - 2);
            }
        }
    }
}
