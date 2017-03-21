
package controllerTests;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.sql.DataSource;
import model.HousePlan;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author kelly
 */
public class HousePlanBeanUnitTest {
    
    private HousePlan plan;
    private static EntityManagerFactory emf;
    private EntityManager em;
    private static Context context;
    private static DataSource ds;
    private static Connection connection;
    
    public HousePlanBeanUnitTest() {
        em = emf.createEntityManager();
    }
    
    @BeforeClass
    public static void setUpClass() {
        /*Connect to database*/
        Properties props = new Properties();
        props.put(Context.INITIAL_CONTEXT_FACTORY,"com.sun.jndi.cosnaming.CNCtxFactory");
        props.put(Context.PROVIDER_URL,"tcp://localhost:3700");
        emf =  Persistence.createEntityManagerFactory("ramani-digitalPU");
        try {
            context = new InitialContext(props);
            ds = (DataSource)context.lookup("jdbc:/ramaniDataSource");
            connection = ds.getConnection("rd_admin","adm1n@rd+");
        } catch (NamingException | SQLException ex) {
            Logger.getLogger(HousePlanBeanUnitTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        plan = em.find(HousePlan.class,1001);
    }
    
    @After
    public void tearDown() {
        em.close();
    }

    @Test
    public void testGetPlan(){
        
        Assert.assertEquals(new BigDecimal(12000),plan.getPriceBoq());
    }
}
