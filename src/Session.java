import java.util.Date;
import java.util.HashMap;
import java.util.Map;

class Session {
    private static Map<String, Session> sessions = new HashMap<String, Session>();
    private User user;
    private Date expireDate;
    private String token;
    final static long ONE_MINUTE_IN_MILLIS = 60000;//millisecs
    final static int minutes = 1;

    public Session(User user, String token) {
        setExpireDate();
        this.user = user;
        this.token = token;
        Session.sessions.put(token, this);
    }

    public Date getExpireDate(){
        return expireDate;
    }

    public String getToken(){
        return token;
    }

    private void setExpireDate(){
        this.expireDate = new Date((new Date()).getTime() + (minutes * ONE_MINUTE_IN_MILLIS));
    }

    static public Session find(String token){
        return Session.sessions.get(token);
    }

    public User getUser(){
        return user;
    }

    public String toString(){
        return "user: " + user + " token: " + token + " expire: " + expireDate;
    }
}