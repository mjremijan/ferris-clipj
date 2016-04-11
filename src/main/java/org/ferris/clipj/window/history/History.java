package org.ferris.clipj.window.history;

import java.util.LinkedList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
@XmlRootElement(name="History")
@XmlAccessorType(XmlAccessType.FIELD)
public class History {

    @XmlElement(name="Item")
    private List<HistoryItem> items = new LinkedList<>();

    public List<HistoryItem> getItems() {
        return items;
    }

    public void setItems(List<HistoryItem> items) {        
        this.items = items;
    }
}
