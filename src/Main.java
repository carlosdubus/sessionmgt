
public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("Hola");
        new User("Hue", "1234");
        new User("Hector", "1234");
        new User("Carlos", "1234");
        new User("Rahul", "1234");
        User u = User.find("Hue");
        System.out.println(u.getName());

    }
}
