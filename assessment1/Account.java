public class Account {
    private int id;
    private String name;
    private String email;
    private String pass;

    public Account(int id, String name, String email, String pass) {
        this.setId(id);
        this.setName(name);
        this.setEmail(email);
        this.setPass(pass);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("ID must be positive");
        }
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (email == null || !email.contains("@")) {
            throw new IllegalArgumentException("Invalid email format");
        }
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        if (pass == null || pass.length() < 5) {
            throw new IllegalArgumentException("Password must be at least 5 characters");
        }
        this.pass = pass;
    }
}
