package org.ferris.clipj.window.main;

import java.util.Arrays;
import java.util.List;
import javax.enterprise.event.Event;
import javax.enterprise.inject.spi.CDI;
import javax.inject.Inject;
import org.slf4j.Logger;

/**
 * The main() method for this application
 *
 * @author <a href="mailto:mjremijan@yahoo.com">Mike Remijan</a>
 */
public class Main {
      public static void main(String[] args) {
        CDI<Object> cdi = CDI.getCDIProvider().initialize();
        
        Main main
            = cdi.select(Main.class).get();
        main.main(Arrays.asList(args));
    }
    
    @Inject
    protected Logger log;
    
    @Inject
    protected Event<StartupEvent> startupEvent;
    
    protected void main(List<String> args) {
        log.info("Welcome to ClipJ!");
        
        log.info("Fire StartupEvent");
        startupEvent.fire(new StartupEvent());
    }
}
