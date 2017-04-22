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
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import org.primefaces.event.FileUploadEvent;

/**
 *
 * @author kelly
 */
@Named(value = "fileUpload")
@RequestScoped
public class FileUploadManager implements Serializable{

    private final String ROOTDIR_PATH = "/home/kelly/rm-temp/";
    private Map<Object,Object> store;
    
    @PostConstruct
    private void setup(){
            store = FacesContext.getCurrentInstance().getAttributes();
            Logger.getAnonymousLogger().log(Level.INFO, "current file upload directories: {0}",
                    new Object[]{store.get("img.dir"),
                                 store.get("doc.dir")});
            
            
    }
    
    public FileUploadManager() {
        Logger.getAnonymousLogger().log(Level.INFO,"Created file uploader");
    }
    
    public void handleImgFileUpload(FileUploadEvent event) throws IOException{
            String imgDir = (String)store.get("img.dir");
            copyFile(event.getFile().getFileName(),imgDir,event.getFile().getInputstream());
        FacesMessage msg = new FacesMessage("Images uploaded successfully",event.getFile().getFileName()+"uploaded");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    public void handleDocFileUpload(FileUploadEvent event) throws IOException{
        //invoke when file is uploaded
        
            String docDir = (String)store.get("doc.dir");
            copyFile(event.getFile().getFileName(),docDir,event.getFile().getInputstream());
        FacesMessage msg = new FacesMessage("Documents uploaded successfully",event.getFile().getFileName()+"uploaded");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    private void copyFile(String fileName, String chDir, InputStream fileStream){
        try{
            //create directory
            File directory = new File(ROOTDIR_PATH+"/"+chDir+"/");
            directory.mkdir();
            
            FileOutputStream of = new FileOutputStream(new File(directory+"/"+fileName));
            int read = 0;
            byte[] bytes = new byte[1024];
            while((read = fileStream.read(bytes))!=-1 ){
                of.write(bytes);
            }
            fileStream.close();
            of.flush();
            of.flush();
            Logger.getAnonymousLogger().log(Level.INFO, "Uploaded file to dir: {0}",directory.getName());
        }catch(IOException exp){
            Logger.getAnonymousLogger().log(Level.SEVERE, "Error uploading files: {0}", exp.getLocalizedMessage());
        }
    }
    
}
