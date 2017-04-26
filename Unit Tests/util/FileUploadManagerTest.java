/*
 *  Bitforge Software Labs
 *  (c)2017 
 *  http://bitforge.co.ke
 *  <muhindi@bitforge.co.ke><muhindi09@gmail.com>
 */
package util;

import java.io.IOException;
import java.io.InputStream;
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
    public FileUploadManagerTest() {
    }
    
    @Before
    public void setUp() {
        fm = new FileUploadManager("dirX","/home/kelly/");
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
        when(fEvent.getFile().getInputstream()).thenReturn(inputStream);
//        when(fm.upload(fEvent)).thenReturn(true);
        fm.upload(fEvent);
        Assert.assertThat(fm.getDestinationDir(),is(same("/home/kelly/")));
        
    }
//
//    
//    @Test(expected = IOException.class)
//    public void testUploadThrowsException() throws IOException{
//        when(fm.upload(fEvent)).thenThrow(new IOException("Error"));
//    }

}
