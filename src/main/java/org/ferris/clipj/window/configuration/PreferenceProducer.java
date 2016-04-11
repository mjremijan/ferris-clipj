package org.ferris.clipj.window.configuration;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.inject.Inject;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
public class PreferenceProducer {
    private Properties props;
    
    @Inject
    public PreferenceProducer(ConfigurationDirectory configurationDirectory) throws Exception {
        props = new Properties();
        props.load(new FileInputStream(new File(configurationDirectory,"preferences.properties")));
    }
    
    @Produces @PreferenceKey
    public String produceStringProperty(InjectionPoint ip) {        
        PreferenceKey m = ip.getAnnotated().getAnnotation(PreferenceKey.class);
        return props.getProperty(m.value(), "-UNKNOWN-");
    }
    
    @Produces @PreferenceKey
    public Integer produceIntegerProperty(InjectionPoint ip) {        
        PreferenceKey m = ip.getAnnotated().getAnnotation(PreferenceKey.class);
        return new Integer(props.getProperty(m.value(), "0"));
    }
}
