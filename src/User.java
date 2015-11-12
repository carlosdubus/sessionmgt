class User {
	int id;
	String email;
	String password;

	public User(int id, String email, String password) {
		this.id = id;
		this.email = email;
		this.password = password;
	}

	public String getPassword() {
		return password;
	}
}
