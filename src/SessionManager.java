import java.util.HashMap;
import java.util.Map;
import java.util.Random;

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
        Random rand = new Random();
        String token = Integer.toString(rand.nextInt(50) + 1);
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
