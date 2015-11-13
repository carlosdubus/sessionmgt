import java.util.HashMap;
import java.util.Map;

public class User {
	private static int id_counter = 1;
	private static Map<Integer, User> users = new HashMap<Integer, User>();

	int id;
	String name;
	String password;

	public User(String name, String password) {
		this.id = User.id_counter;
		this.name = name;
		this.password = password;

		User.users.put(id, this);
		id_counter += 1;
	}

	public String getName() {
		return name;
	}

	public String getPassword() {
		return password;
	}

	public int getId() {
		return id;
	}

	static User find(String name){
		for (User user : users.values()) {
			if(user.getName().equals(name)){
				return user;
			}
		}
		return null;
	}

	public String toString(){
		return name;
	}
}
