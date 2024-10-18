package org.ferris.clipj.window.history;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.xml.bind.JAXBContext;
import static javax.xml.bind.JAXBContext.newInstance;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import org.ferris.clipj.window.configuration.PreferenceKey;
import org.ferris.clipj.window.io.ByteArrayReader;
import org.ferris.clipj.window.io.ByteArrayWriter;
import org.ferris.clipj.window.security.Aes;
import org.slf4j.Logger;


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
    protected HistoryEncryptedFile historyEncryptedFile;
    
    @Inject @PreferenceKey("MaxHistorySize")
    protected Integer maxHistorySize;
    
    @Inject
    protected Aes aes;
    
    public synchronized History getHistory() {
        log.info("ENTER");
        
        log.info(String.format("Decrypting history file \"%s\"", historyEncryptedFile.getAbsolutePath()));
        Optional<InputStream> decrypted
            = decrypt();

        log.info("Unmarshal or create new history data");
        History history 
            = decrypted.map(is -> unmarshal(is)).orElseGet(() -> new History());       

        return history;  
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

                log.info(String.format("Marshalling %d history items to byte array", items.size()));
                ByteArrayOutputStream marshalledBytes = new ByteArrayOutputStream();
                marshaller.marshal(history, marshalledBytes);
                
                log.info(String.format("Encrypting history items to \"%s\"", historyEncryptedFile.getAbsolutePath()));
                new ByteArrayWriter(historyEncryptedFile.toPath())
                    .write(aes.encrypt(
                        marshalledBytes.toByteArray()
                    )
                );

                historyEvent.fire(new HistoryEvent(newHistoryItem.getValue()));
            }
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }
    
    protected History unmarshal(InputStream inputStream) {
        log.info("ENTER");
        try {
            JAXBContext context
                = newInstance(History.class);

            Unmarshaller unmarshaller
                = context.createUnmarshaller();
            
            log.info("Unmarshal history data");
            return (History) unmarshaller.unmarshal(inputStream);
        
        } catch (JAXBException e) {
            log.info("Problem unmarshalling history data, most likely corrupt data. Return new history");
            return new History();
        }
    }
    
    
    protected Optional<InputStream> decrypt() {
        log.info("ENTER");
        
        if (!historyEncryptedFile.exists()) {
            return Optional.empty();
        } else {
            log.info(String.format("Encrypted history file exists! \"%s\"", historyEncryptedFile.getAbsolutePath()));
            try {
                log.info(String.format("Read and decrpt \"%s\"", historyEncryptedFile.getAbsolutePath()));
                return Optional.of(new ByteArrayInputStream(
                    aes.decrypt(
                        new ByteArrayReader(historyEncryptedFile.toPath()).read()
                    )
                ));
            } catch (Exception e) {
                throw new RuntimeException(String.format("Problem trying to read and decrypt \"%s\"", historyEncryptedFile.toString()), e);
            }
        }
    }
}
