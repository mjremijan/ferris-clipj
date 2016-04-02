package org.ferris.clipj.window.tray;

import java.awt.PopupMenu;
import java.util.HashSet;
import java.util.Set;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import org.ferris.clipj.window.about.AboutMenuItem;
import org.ferris.clipj.window.exit.ExitMenuItem;
import org.ferris.clipj.window.menu.StringMenuItem;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
@ApplicationScoped
public class TrayPopupMenu extends PopupMenu {

    private Set<String> uniqueStrings;    

    @Inject
    public TrayPopupMenu(ExitMenuItem exitMenuItem, AboutMenuItem aboutMenuItem) {
        super();
        
        add(aboutMenuItem);
        add(exitMenuItem);
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
