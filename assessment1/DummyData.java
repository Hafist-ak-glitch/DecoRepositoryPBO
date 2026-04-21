
import java.util.ArrayList;

public class DummyData {
    public static ArrayList<User> users() {
        ArrayList<User> userList = new ArrayList<>();

        userList.add(new User(1, "Deco", "deco@email.com", "12345", "manager", 40));
        userList.add(new User(2, "Akbar", "akbar@email.com", "12345", "stakeholder", 30));
        userList.add(new User(3, "Prasetya", "prasetya@email.com", "12345", "good person", 20));

        return userList;
    }

    public static ArrayList<Admin> admins() {
        ArrayList<Admin> adminList = new ArrayList<>();

        adminList.add(new Admin(1, "Sherlock Holmes", "sherlock@email.com", "12345"));
        adminList.add(new Admin(2, "Watson", "watson@email.com", "12345"));

        return adminList;
    }

    public static ArrayList<Content> quizzes() {
        ArrayList<Content> quizList = new ArrayList<>();

        quizList.add(new Quis(1, "Energi Terbarukan", "Apa itu energi terbarukan?", "Sherlock Holmes"));
        quizList.add(new Quis(2, "Panel Surya", "Bagaimana cara kerja panel surya?", "Watson"));
        quizList.add(new Quis(3, "Efisiensi Energi", "Tips menghemat energi di rumah", "Sherlock Holmes"));

        return quizList;
    }
}
