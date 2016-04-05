package org.ferris.clipj.window.history;

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
public class HistoryHandler {

    @Inject
    protected Logger log;
    
    @Inject
    protected HistoryXmlFile historyXmlFile;
    
    protected History getHistory() throws JAXBException {
        log.info("ENTER");
        JAXBContext context
            = newInstance(History.class);
        Unmarshaller unmarshaller
            = context.createUnmarshaller();

        log.debug(String.format("Unmarshalling history file %s", historyXmlFile.getAbsolutePath()));
        History history
            = (History) unmarshaller.unmarshal(historyXmlFile);
        
        return history;
        
    }   
}
