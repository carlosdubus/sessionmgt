
public class Main {
    public static void main(String[] args) throws Exception {

        new User("Hue", "1234");
        new User("Hector", "1234");
        new User("Carlos", "1234");
        new User("Rahul", "1234");

        Server.run();
    }
}
