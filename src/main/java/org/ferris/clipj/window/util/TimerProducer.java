package org.ferris.clipj.window.util;

import java.util.Timer;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
@ApplicationScoped
public class TimerProducer {
    protected Timer timer;
    
    public TimerProducer() {
        timer = new Timer();
    }
    
    @Produces
    public Timer produceTimer() {
        return timer;
    }
}
