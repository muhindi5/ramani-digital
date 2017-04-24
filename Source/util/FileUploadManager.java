/*
 *  Bitforge Software Labs
 *  (c)2017 
 *  http://bitforge.co.ke
 *  <muhindi@bitforge.co.ke><muhindi09@gmail.com>
 */
package util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.ManagedBean;

/**
 * Upload images and optional files(pdf & Excel) for a house plan
 *
 * @author kelly
 */
@ManagedBean
public class FileUploadManager implements Serializable {

    private final String destinationDir;
    private final String rootDir;

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
    public boolean upload(String fileName, InputStream fileStream) throws IOException {
        boolean success = false;
        //create directory
        File directory = new File(this.rootDir + File.pathSeparator + this.destinationDir + File.pathSeparator);
        directory.mkdir();

        FileOutputStream of = new FileOutputStream(new File(directory + fileName));
        byte[] bytes = new byte[1024];
        while ((fileStream.read(bytes)) != -1) {
            of.write(bytes);
        }
        fileStream.close();
        of.flush();
        Logger.getAnonymousLogger().log(Level.INFO, "Uploaded file to dir: {0}", directory.getName());
        return success;
    }

}
