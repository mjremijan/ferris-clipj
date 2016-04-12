package org.ferris.clipj.window.doubleclick;

import javax.inject.Inject;
import javax.xml.bind.JAXBContext;
import static javax.xml.bind.JAXBContext.newInstance;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import org.apache.log4j.Logger;


/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
public class DoubleClickHandler {

    @Inject
    protected Logger log;
    
    @Inject
    protected DoubleClickXmlFile xmlFile;
    
    /**
     * 
     * @return Return the data from the clipj-double-click.xml file inside
     * a {@link DoubleClick} object.
     */
    public synchronized DoubleClick getDoubleClick() {
        log.info("ENTER");
        
        try {
            JAXBContext context
                = newInstance(DoubleClick.class);

            Unmarshaller unmarshaller
                = context.createUnmarshaller();

            log.info(String.format("Unmarshalling double-click file %s", xmlFile.getAbsolutePath()));
            
            DoubleClick doubleClick
                = (DoubleClick) unmarshaller.unmarshal(xmlFile);

            return doubleClick;        
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }   
}
