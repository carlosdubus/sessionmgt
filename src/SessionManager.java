import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.security.SecureRandom;


public class SessionManager {
	final Map<String, User> users = new HashMap<String, User>() {
		{
			put("carlos", new User(1, "carlos", "passc"));
			put("hector", new User(2, "hector", "passh"));
			put("rahul", new User(3, "rahul", "passr"));
		}
	};

	public String authenticate(String email, String password) {
		if (!users.containsKey(email)) {
			return null;
		}
		if (users.get(email).getPassword().equals(password)) {
			return createToken();
		}
		return null;
	}

	String createToken() {
		
	    SecureRandom random = new SecureRandom();
	    byte bytes[] = new byte[20];
	    random.nextBytes(bytes);
	    String token = bytes.toString();
	    return token;		
	}
}
