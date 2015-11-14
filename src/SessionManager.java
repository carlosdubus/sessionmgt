import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.security.SecureRandom;

public class SessionManager {
    public String authenticate(String name, String password) {
        User a_user = User.find(name);
        if(a_user != null){
            if (a_user.getPassword().equals(password)) {
                return createToken(a_user);
            }
        }
        return null;
    }

    String createToken(User user) {
        SecureRandom random = new SecureRandom();
        byte bytes[] = new byte[20];
        random.nextBytes(bytes);
        String token = bytes.toString();
        new Session(user, token);
        return token;
    }

    Session checkToken(String token){
        return Session.find(token);
    }

    Boolean checkRole(User user, String role){
        return user.getRole().equals(role);
    }
}
