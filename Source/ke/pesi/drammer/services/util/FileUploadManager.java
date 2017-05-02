/*
 *  Bitforge Software Labs
 *  (c)2017 
 *  http://bitforge.co.ke
 *  <muhindi@bitforge.co.ke><muhindi09@gmail.com>
 */
package ke.pesi.drammer.services.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;
import org.primefaces.event.FileUploadEvent;

/**
 * Upload images and optional files(pdf & Excel) for a house plan
 *
 * @author kelly
 */
@ManagedBean
public class FileUploadManager implements Serializable {

    private String destinationDir;
    private String rootDir;

    
    public FileUploadManager(){
        
    }
    
    public FileUploadManager(String destinationDir, String root) {
        this.destinationDir = destinationDir;
        this.rootDir = root;
        Logger.getAnonymousLogger().log(Level.INFO, "Created file uploader");
    }

    /*
     * Upload file to server 
     * @param filename name of the file
     * @param fileStream input stream of the file to be copied
     * @return success status of upload true/false
     */
    public boolean upload(FileUploadEvent uploadEvent) throws IOException, NullPointerException {
        boolean success = false;
        //create directory
        InputStream inStream = uploadEvent.getFile().getInputstream();
        String fileName = uploadEvent.getFile().getFileName();
        File directory = new File(this.rootDir + File.pathSeparator + 
                this.destinationDir + File.pathSeparator);
        directory.mkdir();

        FileOutputStream of = new FileOutputStream(new File(directory + fileName));
        byte[] bytes = new byte[1024];
        while ((inStream.read(bytes)) != -1) {
            of.write(bytes);
        }
        inStream.close();
        of.flush();
        Logger.getAnonymousLogger().log(Level.INFO, "Uploaded file to dir: {0}",
                directory.getName());
        return success;
    }

    public String getDestinationDir() {
        return destinationDir;
    }

    public void setDestinationDir(String destinationDir) {
        this.destinationDir = destinationDir;
    }

    public String getRootDir() {
        return rootDir;
    }

    public void setRootDir(String rootDir) {
        this.rootDir = rootDir;
    }

}
