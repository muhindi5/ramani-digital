/*
 *  Bitforge Software Labs
 *  (c)2017 
 *  http://bitforge.co.ke
 *  <muhindi@bitforge.co.ke><muhindi09@gmail.com>
 */
package util;

import java.io.IOException;
import java.io.InputStream;
import org.junit.After;
import org.junit.Assert;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.runners.MockitoJUnitRunner;

/**
 *
 * @author kelly
 */
@RunWith(MockitoJUnitRunner.class)
public class FileUploadManagerTest {
    
//    @InjectMocks
    
    @Mock
    private FileUploadManager fm;
    @Mock
    private InputStream inputStream;
    
    public FileUploadManagerTest() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void testFileUploadObjNutNull(){
        Assert.assertNotNull("Object is null",fm);
    }
    
    @Test
    public void testUploadSuccess() throws IOException{
        when(fm.upload("file2", inputStream)).thenReturn(true);
        assertTrue(fm.upload("file2", inputStream));
    }
    
    @Test
    public void testUploadFail() throws IOException{
        when(fm.upload("file", inputStream)).thenReturn(Boolean.FALSE);
        Assert.assertFalse(fm.upload("file", inputStream));
    }
    
    @Test(expected = IOException.class)
    public void testUploadThrowsException() throws IOException{
        when(fm.upload("file2", inputStream)).thenThrow(new IOException("Error"));
        fm.upload("file2", inputStream);
    }

}
