package org.ferris.clipj.window.doubleclick;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
@XmlRootElement(name = "DoubleClick")
@XmlAccessorType(XmlAccessType.FIELD)
public class DoubleClick {

    @XmlElement(name = "Item")
    private String item;

    public String getItem() {
        return item;
    }

    public void setItems(String item) {
        this.item = item;
    }
}
