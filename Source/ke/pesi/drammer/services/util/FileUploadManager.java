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
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import ke.pesi.drammer.services.HousePlanController;
import org.primefaces.event.FileUploadEvent;

/**
 * Upload images and optional files(pdf & Excel) for a house plan
 *
 * @author kelly
 */
@ManagedBean
@ViewScoped
public class FileUploadManager implements Serializable {

    private String rootDir;
    private String uploadDir;
    private File directory;
    private static FacesContext context;
    private HousePlanController controller;
    private static final String IMG_DIR_KEY = "img.dir";
    private static final String DOC_DIR_KEY = "doc.dir";

    /*Create file uploader with root directory set */
    public FileUploadManager(HousePlanController controller) {
        this.controller = controller;
        context = FacesContext.getCurrentInstance();
        StringBuilder builder = new StringBuilder();
        builder.append(System.getProperty("com.sun.aas.instanceRoot")).append("/docroot/catalog-docs");
        this.rootDir = builder.toString();
        Logger.getAnonymousLogger().log(Level.INFO, "File uploader root dir: {0}", rootDir);
    }

    /*
     * Upload file to server 
     * @param filename name of the file
     * @param fileStream input stream of the file to be copied
     * @return success status of upload true/false
     */
    public boolean upload(FileUploadEvent uploadEvent) throws IOException, NullPointerException {

        String fileType = uploadEvent.getFile().getContentType();
        Logger.getAnonymousLogger().log(Level.INFO,"Type of file: {0}",fileType);
        if (fileType.equals("image/jpeg") || fileType.equals("image/png")) {
            //set the upload directory to img.dir
            uploadDir = (String)controller.getKeyStore().get(IMG_DIR_KEY);
        } else {
            uploadDir = (String)controller.getKeyStore().get(DOC_DIR_KEY);
        }

        boolean success;
        FileOutputStream of;
        try (InputStream inStream = uploadEvent.getFile().getInputstream()) {
            String fileName = uploadEvent.getFile().getFileName();
            this.directory = new File(this.getRootDir() + "/" + this.getUploadDir());
            this.directory.mkdir();
            of = new FileOutputStream(directory.getPath() + File.separator + fileName);
            byte[] bytes = new byte[1024];
            while ((inStream.read(bytes)) != -1) {
                of.write(bytes);
            }
        }
        of.flush();
        success = true;
        Logger.getAnonymousLogger().log(Level.INFO, "Uploaded file to dir: {0}",
                directory.getName());
        return success;
    }

    public String getRootDir() {
        return rootDir;
    }

    public void setRootDir(String rootDir) {
        this.rootDir = rootDir;
    }

    public String getUploadDir() {
        return uploadDir;
    }

    public void setUploadDir(String uploadDir) {
        this.uploadDir = uploadDir;
    }

//    /*Determine file type to be uploaded*/
//    private String getFileType(File file) throws IOException {
//        String type = Files.probeContentType(file.toPath());
//        if (type != null) {
//            return type;
//        } else {
//            return "Notype";
//        }
//    }

}
