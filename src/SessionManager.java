import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SessionManager {
    public String authenticate(String name, String password) {
        User a_user = User.find(name);
        if(a_user != null){
            if (a_user.getPassword().equals(password)) {
                return createToken();
            }
        }
        return null;
    }

    String createToken() {
        return "234234ghj2g4jh42g4g234";
    }
}
