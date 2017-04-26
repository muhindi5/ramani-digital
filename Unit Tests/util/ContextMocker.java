/*
 *  Bitforge Software Labs
 *  (c)2017 
 *  http://bitforge.co.ke
 *  <muhindi@bitforge.co.ke><muhindi09@gmail.com>
 */

package util;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import org.mockito.Mockito;
import static org.mockito.Mockito.when;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

/**
 *
 * @project: ramani-digital
 * @author kelly
 * @date Apr 26, 2017 11:03:11 AM
 * A custom utility class to mock FacesContext and ExternalContext for unit testing
 * beans...took me 3 straight days of research to figure this out...
 * DONT TOUCH PLEASE ;)
 */

public abstract class ContextMocker extends FacesContext{
    
    private ContextMocker(){}

    private static final Release RELEASE = new Release();
    
    //set null when release() is called on the context instance.
    private static class Release implements Answer<Void>{
    @Override
        public Void answer(InvocationOnMock invocationOnMock){
            setCurrentInstance(null);
            return null;
        }
    }
    
    public static FacesContext mockFacesContext(){
        FacesContext context  = Mockito.mock(FacesContext.class);
        setCurrentInstance(context);
        when(context.getExternalContext()).thenReturn(setMockExternalContext());
        Mockito.doAnswer(RELEASE).when(context).release();
        return context;
    }
    
    private static ExternalContext setMockExternalContext(){
        ExternalContext exContext = Mockito.mock(ExternalContext.class);
        return exContext;
    }
}
