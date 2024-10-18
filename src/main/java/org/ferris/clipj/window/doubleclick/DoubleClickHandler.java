package org.ferris.clipj.window.doubleclick;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.file.Files;
import javax.inject.Inject;
import javax.xml.bind.JAXBContext;
import static javax.xml.bind.JAXBContext.newInstance;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import org.ferris.clipj.window.io.ByteArrayReader;
import org.ferris.clipj.window.io.ByteArrayWriter;
import org.ferris.clipj.window.security.Aes;
import org.slf4j.Logger;


/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
public class DoubleClickHandler {

    @Inject
    protected Logger log;
    
    @Inject
    protected DoubleClickXmlFile xmlFile;
    
    @Inject
    protected DoubleClickEncryptedFile encryptedFile;
    
    @Inject
    protected Aes aes;
    
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

            log.info(String.format("Decrypting and unmarshalling double-click file \"%s\"", encryptedFile.getAbsolutePath()));
            
            DoubleClick doubleClick
                = (DoubleClick) unmarshaller.unmarshal(getInputStream());

            return doubleClick;        
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }  
    
    protected InputStream getInputStream() {
        log.info("ENTER");
        
        if (xmlFile.exists()) {
            log.info(String.format("Plain text double-click file exists! \"%s\"", xmlFile.getAbsolutePath()));
            
            // Encrypt
            try {
                log.info(String.format("Read and encrypt \"%s\"", xmlFile.getAbsolutePath()));
                new ByteArrayWriter(encryptedFile.toPath())
                    .write(aes.encrypt(
                        Files.readAllBytes(xmlFile.toPath())
                    )
                );
            } catch (Exception e) {
                throw new RuntimeException(String.format("Problem trying to read and encrypt \"%s\"", xmlFile.getAbsolutePath()), e);
            }

            // Delete
            try {
                log.info(String.format("Attempt to delete \"%s\"", xmlFile.getAbsolutePath()));
                for (int i=0; xmlFile.delete() == false; i++) {
                    // 1 minute == 60000 miliseconds
                    if (i >= 300) {
                        break;
                    }
                    log.info(String.format("Delete attempt #%d", i));
                    Thread.sleep(200);                    
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(String.format("Problem deleting \"%s\"", xmlFile.getAbsolutePath()));
            }
        }
        
        if (encryptedFile.exists()) {
            log.info(String.format("Encrypted double-click file exists! \"%s\"", encryptedFile.getAbsolutePath()));
            try {
                
                log.info(String.format("Read and decrpt \"%s\"", encryptedFile.getAbsolutePath()));
                return new ByteArrayInputStream(
                    aes.decrypt(
                        new ByteArrayReader(encryptedFile.toPath()).read()
                    )
                );
            } catch (Exception e) {
                throw new RuntimeException(String.format("Problem trying to read and decrypt \"%s\"", encryptedFile.toString()), e);
            }
        } else {
            throw new RuntimeException(String.format("Encrypted double-click file does not exist: \"%s\"", encryptedFile.toString()));
        }
    }
}
