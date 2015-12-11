import org.junit.*;
import junit.framework.JUnit4TestAdapter;

public class SessionManagerTest{
  @Before
  public void setUp(){
    new User("Hue", "1234", "admin");
  }

  @Test
  public void testAuthenticationWithValidCredentials(){
    SessionManager s = new SessionManager();
    Assert.assertNotNull(s.authenticate("Hue", "1234"));
  }

  @Test
  public void testAuthenticationWithInvalidCredentials(){
    SessionManager s = new SessionManager();
    Assert.assertNull(s.authenticate("Hue", "invalid"));
  }

  @After
  public void tearDown(){
  }

  public static junit.framework.Test suite(){
    return new JUnit4TestAdapter(SessionManagerTest.class);
  }
}
