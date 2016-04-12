package org.ferris.clipj.window.history;

import java.util.List;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.xml.bind.JAXBContext;
import static javax.xml.bind.JAXBContext.newInstance;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import org.apache.log4j.Logger;
import org.ferris.clipj.window.configuration.PreferenceKey;


/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
public class HistoryHandler {

    @Inject
    protected Logger log;
    
    @Inject
    protected Event<HistoryEvent> historyEvent;
    
    @Inject
    protected HistoryXmlFile historyXmlFile;
    
    @Inject @PreferenceKey("MaxHistorySize")
    protected Integer maxHistorySize;
    
    public synchronized History getHistory() {
        log.info("ENTER");
        
        try {
            JAXBContext context
                = newInstance(History.class);

            Unmarshaller unmarshaller
                = context.createUnmarshaller();

            log.info(String.format("Unmarshalling history file %s", historyXmlFile.getAbsolutePath()));
            History history
                = (History) unmarshaller.unmarshal(historyXmlFile);

            return history;        
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }   
    
    public synchronized void addToHistory(String newHistoryItemStr) {
        log.info("ENTER");
        
        try {
            History history
                = getHistory();

            List<HistoryItem> items
                = history.getItems();
            
            HistoryItem newHistoryItem
                = new HistoryItem(newHistoryItemStr);

            if (!items.contains(newHistoryItem)) 
            {
                items.add(0, newHistoryItem);        
            
                if (items.size() > maxHistorySize) {            
                    items.remove(items.size() - 1);
                }
                
                history.setItems(items);
                
                JAXBContext context
                    = newInstance(History.class);

                Marshaller marshaller = context.createMarshaller();
                marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

                log.info(String.format("Marshalling %d itmes to history file %s", items.size(), historyXmlFile.getAbsolutePath()));
                marshaller.marshal(history, historyXmlFile);

                historyEvent.fire(new HistoryEvent(newHistoryItem.getValue()));
            }
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }
}
