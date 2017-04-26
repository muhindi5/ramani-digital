/*
 *  Bitforge Software Labs
 *  (c)2017 
 *  http://bitforge.co.ke
 *  <muhindi@bitforge.co.ke><muhindi09@gmail.com>
 */
package service.controllers;

import java.io.IOException;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.when;
import org.mockito.runners.MockitoJUnitRunner;
import org.primefaces.event.FileUploadEvent;
import util.ContextMocker;
import util.FileUploadManager;

/**
 *
 * @author kelly
 */
@RunWith(MockitoJUnitRunner.class)
//@PrepareForTest({FacesContext})
public class HousePlanControllerTest {

    private HousePlanController hpController;
    private FacesContext facesContext;
    @Mock private ExternalContext exContext;

    @Before
    public void setUp() {
        facesContext = ContextMocker.mockFacesContext();
        hpController = new HousePlanController(facesContext);
        exContext = facesContext.getExternalContext();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testInstancesNotNull() {
        Assert.assertNotNull(hpController);
        Assert.assertNotNull(facesContext);
        Assert.assertNotNull(exContext);
    }
    
    @Test
    public void testDirectoriesMapIsNullonFormPostback(){
        when(hpController.getContext().isPostback()).thenReturn(Boolean.TRUE);
        hpController.generatePlanDirNames();
        Assert.assertTrue("Map should be empty", hpController.getKeyStore().isEmpty());
    }
    
    @Test
    public void testDirectoriesMapHasImgKeyOnFormPostback(){
        when(hpController.getContext().isPostback()).thenReturn(Boolean.FALSE);
        hpController.generatePlanDirNames();
        when(hpController.getContext().isPostback()).thenReturn(Boolean.TRUE);
            Assert.assertThat("Map should have keys",is(not(equalTo(hpController.getKeyStore().isEmpty()))));
            Assert.assertThat(hpController.getKeyStore().size(),is(equalTo(2)));
    }
    
    @Test
    public void testDirectoriesMapHasKeysOnFormLoad(){
        when(hpController.getContext().isPostback()).thenReturn(Boolean.FALSE);
        hpController.generatePlanDirNames();
//        Iterator iterator = hpController.getKeyStore().entrySet().iterator();
//        while (iterator.hasNext()) {
//            Map.Entry pair = (Map.Entry) iterator.next();
//            Logger.getAnonymousLogger().log(Level.INFO,"Key = ["+pair.getKey()+"] "
//            +"Value: ["+pair.getValue()+"]");
//            
//        }
        Assert.assertThat("2 Keys should exist in this map", hpController.getKeyStore().size(), is(2));
    }
    
    @Test
    public void testImgFileUpload() throws IOException{
        when(hpController.getContext().isPostback()).thenReturn(Boolean.FALSE);
        hpController.generatePlanDirNames();
        FileUploadEvent fEvent = Mockito.mock(FileUploadEvent.class);
        FileUploadManager manager = hpController.getUploadManager();
        when(manager.upload(fEvent)).thenReturn(Boolean.TRUE);
        Assert.assertSame(null, fEvent.getFile().getFileName(),this);
        
        
    }
    



}
