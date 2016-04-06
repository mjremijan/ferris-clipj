package org.ferris.clipj.window.exception;

import java.io.PrintWriter;
import java.io.StringWriter;
import javax.inject.Inject;
import javax.swing.JOptionPane;
import org.apache.log4j.Logger;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
public class UncaughtExceptionView {

    @Inject
    protected Logger log;

    public void view(Throwable e) {
        log.fatal(e.getMessage(), e);
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        sw.toString();
        JOptionPane.showMessageDialog(null, sw.toString(), "Opps :(", JOptionPane.ERROR_MESSAGE);
    }
}
