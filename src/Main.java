
public class Main {
    public static void main(String[] args) throws Exception {

        new User("Hue", "1234");
        new User("Hector", "1234");
        new User("Carlos", "1234");
        new User("Rahul", "1234");

        SessionManager sm = new SessionManager();
        String htoken = sm.authenticate("Hector", "1234");
        String ctoken = sm.authenticate("Carlos", "1234");

        System.out.println(htoken);
        System.out.println(ctoken);

        System.out.println(sm.checkToken(htoken));
        System.out.println(sm.checkToken(ctoken));
        System.out.println(sm.checkToken("wrong_token"));
    }
}
