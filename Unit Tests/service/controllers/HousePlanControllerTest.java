/*
 *  Bitforge Software Labs
 *  (c)2017 
 *  http://bitforge.co.ke
 *  <muhindi@bitforge.co.ke><muhindi09@gmail.com>
 */
package service.controllers;

import java.util.HashMap;
import java.util.Map;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.when;

/**
 *
 * @author kelly
 */
public class HousePlanControllerTest {
//    
//        private static EJBContainer container;
//        private static Context context;
        private static ExternalContext context;
        private static Map<String,Object> map;
        private static FacesContext facesContext;
        
    public HousePlanControllerTest() {
    }
    
    @Before
    public static void setUp() {
//         container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
//         context = container.getContext();
         context = Mockito.mock(ExternalContext.class);
         map = Mockito.mock(HashMap.class);
         facesContext = Mockito.mock(FacesContext.class);
    }
    
    @Test
    public void testGeneratePlanDirNames(){
        
        when(facesContext.isPostback()).thenReturn(Boolean.FALSE);
        
    }
    @After
    public void tearDown() {
    }

    
//    @Test
//    public void testAccess() throws NamingException{
//        HousePlanController controller =
//             (HousePlanController)context.lookup("java:global/classes/HousePlanController");
//        RoomcountFacade rmFacade =
//             (RoomcountFacade)context.lookup("java:global/classes/RoomcountFacade");
//        Assert.assertNotNull(rmFacade);
//        Assert.assertNotNull(controller);
//    }
}
