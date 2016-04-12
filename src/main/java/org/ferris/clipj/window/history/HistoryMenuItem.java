package org.ferris.clipj.window.history;

import java.awt.Font;
import java.awt.MenuItem;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;

/**
 * 
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
public class HistoryMenuItem extends MenuItem {
    
    private static final long serialVersionUID = 6379912347790948335L;
    private static final Font font = new Font("Courier",Font.PLAIN,12);
    private String str;

    public HistoryMenuItem() {
        super();
        setFont(font);
    }
    
    public void init(String str) {
        this.str = str;        
        addActionListener(e -> 
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(str), null)
        );
    }
    
    
    public void setLabel(int idx) {
        String idxStr
            = " (" + idx + ")";
        
        int maxLength = 100;
        int remaining = maxLength - (idxStr.length());
        
        String strStr 
            = str.replaceAll("\r", "").replaceAll("\n", "");
        
        if (strStr.length() > remaining) {
            strStr = strStr.substring(0, remaining - 3);
            strStr += "...";
        } else {
            strStr = String.format("%-"+remaining+"s",strStr);
        }
        
        setLabel(strStr + idxStr);        
    }
}

