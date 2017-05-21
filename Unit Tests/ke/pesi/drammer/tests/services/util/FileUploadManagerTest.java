/*
 *  Bitforge Software Labs
 *  (c)2017 
 *  http://bitforge.co.ke
 *  <muhindi@bitforge.co.ke><muhindi09@gmail.com>
 */
package ke.pesi.drammer.tests.services.util;

import ke.pesi.drammer.services.util.FileUploadManager;
import java.io.IOException;
import java.io.InputStream;
import ke.pesi.drammer.services.HousePlanController;
import ke.pesi.drammer.services.util.FileUploadManager;
import static org.hamcrest.CoreMatchers.is;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.mockito.Matchers.same;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.when;
import org.mockito.runners.MockitoJUnitRunner;
import org.primefaces.event.FileUploadEvent;

/**
 *
 * @author kelly
 */
@RunWith(MockitoJUnitRunner.class)
public class FileUploadManagerTest {
    
//    @InjectMocks
    
    @Mock private FileUploadManager fm;
    @Mock private InputStream inputStream;
    @Mock private FileUploadEvent fEvent;
    @Mock private HousePlanController hpc;
    public FileUploadManagerTest() {
    }
    
    @Before
    public void setUp() {
        fm = new FileUploadManager(hpc);
        fEvent = Mockito.mock(FileUploadEvent.class);
        inputStream = Mockito.mock(InputStream.class);
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void testFileUploadObjNutNull(){
        Assert.assertNotNull("Object is null",fm);
    }
    
    @Test
    public void testFileUploadSuccess() throws IOException{
        when(fEvent.getFile().getInputstream()) .thenReturn(inputStream);
//        when(fm.upload(fEvent)).thenReturn(true);
        fm.upload(fEvent);
        Assert.assertThat(fm.getRootDir(),
                is(same("/home/kelly/glassfish4/glassfish/domains/domain1/docroot/catalog-docs")));
        
    }
//
//    
//    @Test(expected = IOException.class)
//    public void testUploadThrowsException() throws IOException{
//        when(fm.upload(fEvent)).thenThrow(new IOException("Error"));
//    }

}
