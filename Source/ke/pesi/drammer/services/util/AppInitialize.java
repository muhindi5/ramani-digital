/*
 *  Bitforge Software Labs
 *  (c)2017 
 *  http://bitforge.co.ke
 *  <muhindi@bitforge.co.ke><muhindi09@gmail.com>
 */
package ke.pesi.drammer.services.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author kelly
 *
 * Managed bean will be created at application startup
 */
@Named(value = "appInitialize")
@ApplicationScoped
@ManagedBean(eager = true)
public class AppInitialize {

    /**
     * Creates a new instance of AppInitialize
     */
    private Map<String, String> startVars;
    private Properties props;
    private String server=null;
    private String imgDir=null;

    /*
     * Load and read from config.properties in classpath, use the values to set 
     * the appropriate configurations.
     */
    @PostConstruct
    public void init() {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        //file is in application classpath
        InputStream is = context.getResourceAsStream("/WEB-INF/config.properties");
        props = new Properties();
        try {
            props.load(is);
        } catch (IOException ex) {
            Logger.getLogger(AppInitialize.class.getName()).log(Level.SEVERE, null, ex);
            //set some startup props
        }
            setServer(props.getProperty("server"));
            setImgDir(props.getProperty("imagedir"));
    }

    public AppInitialize() {
        props = new Properties();
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        Logger.getAnonymousLogger().log(Level.INFO, "Setting server to: {0}",props.getProperty("server"));
        this.server = server;
    }

    public String getImgDir() {
        return imgDir;
    }

    public void setImgDir(String imgDir) {
        Logger.getAnonymousLogger().log(Level.INFO, "Setting images dir to: {0}",props.getProperty("imagedir"));
        this.imgDir = imgDir;
    }
    
    

}
