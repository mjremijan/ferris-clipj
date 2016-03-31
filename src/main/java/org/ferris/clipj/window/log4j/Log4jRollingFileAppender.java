package org.ferris.clipj.window.log4j;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * Extends {@link RollingFileAppender} to programmatically 
 * find the application's "logs" directory.
 * 
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
public class Log4jRollingFileAppender extends org.apache.log4j.RollingFileAppender {
    public Log4jRollingFileAppender() throws URISyntaxException {
        // This code assumes the following directory structure
        //
        // /project
        //    /bin
        //    /lib
        //      ferris-project-app-1.0.0.0-SNAPSHOT.jar
        //    /logs
        //
        // So the the log directory will be 1 directory up from where the 
        // JAR file is located.
        URL jarURL = this.getClass().getProtectionDomain().getCodeSource().getLocation();
        URI jarURI = jarURL.toURI();
        File jarFile = new File(jarURI);
        File parentDir = jarFile.getParentFile().getParentFile();
        File logsDir = new File(parentDir, "logs");
        File logFile = new File(logsDir, "application.log");
        super.setFile(logFile.toString());
    }    
}
