/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author kelly
 */
@ManagedBean
@SessionScoped
public class ImageService {

    private List<Image> images = new ArrayList<Image>();
    /**
     * Creates a new instance of ImageService
     */
    public ImageService() {
    }
    
    @PostConstruct
    public void addImages(){
        Image img1 = new Image();
        img1.setFilename("detail2.jpg");
        Image img2 = new Image();
        img2.setFilename("detail3.jpg");
        Image img3 = new Image();
        img3.setFilename("detail4.jpg");
        Image img4 = new Image();
        img4.setFilename("details1.jpeg");
        getImages().add(img1);
        getImages().add(img2);
        getImages().add(img3);
        getImages().add(img4);
    }

    /**
     * @return the images
     */
    public List<Image> getImages() {
        return images;
    }

    /**
     * @param images the images to set
     */
    public void setImages(List<Image> images) {
        this.images = images;
    }
}
