
import java.util.ArrayList;

public class Auth {
    enum Role {
        ADMIN,
        USER
    }

    private String email;
    private String pass;
    private ArrayList<User> users;
    private ArrayList<Admin> admins;

    public Auth(String email, String pass, ArrayList<User> userList, ArrayList<Admin> adminList) {
        this.setEmail(email);
        this.setPass(pass);
        this.setUsers(userList);
        this.setAdmins(adminList);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("Email cannot be empty");
        }
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        if (pass == null || pass.trim().isEmpty()) {
            throw new IllegalArgumentException("Password cannot be empty");
        }
        this.pass = pass;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        if (users == null) {
            throw new IllegalArgumentException("User list cannot be null");
        }
        this.users = users;
    }

    public ArrayList<Admin> getAdmins() {
        return admins;
    }

    public void setAdmins(ArrayList<Admin> admins) {
        if (admins == null) {
            throw new IllegalArgumentException("Admin list cannot be null");
        }
        this.admins = admins;
    }

    public Role login() {
        for (Admin a : admins) {
            if (a.getEmail().equals(email) && a.getPass().equals(pass)) {
                return Role.ADMIN;
            }
        }

        for (User u : users) {
            if (u.getEmail().equals(email) && u.getPass().equals(pass)) {
                return Role.USER;
            }
        }
        return null;
    }
}
