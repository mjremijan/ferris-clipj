package org.ferris.clipj.window.history;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
@XmlRootElement(name="Item")
@XmlAccessorType(XmlAccessType.FIELD)
public class HistoryItem {

    @XmlValue
    private String value;        
    
    public HistoryItem(){}
    
    public HistoryItem(String value) {
        setValue(value);
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
