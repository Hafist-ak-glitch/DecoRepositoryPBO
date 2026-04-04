import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        boolean closed = false;
        boolean loggedIn = false;

        System.out.print("\033[H\033[2J");
        System.out.flush();

        while (closed == false) {
            System.out.println("Welcome to 'Energy Bersih dan Terjangkau' ");
            System.out.println("please login first!");
            System.out.println("1. Login");
            System.out.println("2. Close");

            String input = sc.nextLine();

            switch (input) {
                case "1":
                    System.out.print("\033[H\033[2J");
                    System.out.flush();

                    System.out.print("Email: ");
                    String email = sc.nextLine();

                    System.out.print("\033[H\033[2J");
                    System.out.flush();

                    System.out.print("Password: ");
                    String pass = sc.nextLine();

                    Auth auth = new Auth(email, pass, DummyData.users(), DummyData.admins());
                    Auth.Role role = auth.login();

                    if (role == Auth.Role.ADMIN) {
                        System.out.print("\033[H\033[2J");
                        System.out.flush();

                        System.out.println("Logged in as Admin");
                        loggedIn = true;

                        while (loggedIn) {
                            System.out.println("Admin Menu:");
                            System.out.println("1. View Users");
                            System.out.println("2. View Admins");
                            System.out.println("3. View Profile");
                            System.out.println("4. Logout");
                            System.out.print("Enter your choice: ");
                            String adminChoice = sc.nextLine();

                            switch (adminChoice) {
                                case "1":
                                    System.out.print("\033[H\033[2J");
                                    System.out.flush();
                                    System.out.println("Users:");
                                    for (User user : DummyData.users()) {
                                        System.out.println("ID: " + user.getId() + ", Name: " + user.getName()
                                                + ", Email: " + user.getEmail() + ", Pekerjaan: " + user.getPekerjaan()
                                                + ", Usia: " + user.getUsia());
                                    }
                                    System.out.println("");
                                    break;
                                case "2":
                                    System.out.print("\033[H\033[2J");
                                    System.out.flush();
                                    System.out.println("Admins:");
                                    for (Admin admin : DummyData.admins()) {
                                        System.out.println("ID: " + admin.getId() + ", Name: " + admin.getName()
                                                + ", Email: " + admin.getEmail());
                                    }
                                    System.out.println("");
                                    break;
                                case "3":
                                    System.out.print("\033[H\033[2J");
                                    System.out.flush();
                                    System.out.println("Profile:");
                                    for (Admin admin : DummyData.admins()) {
                                        if (admin.getEmail().equals(email)) {
                                            System.out.println("ID: " + admin.getId() + ", Name: " + admin.getName()
                                                    + ", Email: " + admin.getEmail());
                                            break;
                                        }
                                    }
                                    for (User user : DummyData.users()) {
                                        if (user.getEmail().equals(email)) {
                                            System.out.println("ID: " + user.getId() + ", Name: " + user.getName() + ", Email: " + user.getEmail()
                                                    + ", Pekerjaan: " + user.getPekerjaan() + ", Usia: " + user.getUsia());
                                            break;
                                        }
                                    }
                                    System.out.println("");
                                    break;
                                case "4":
                                    loggedIn = false;
                                    System.out.print("\033[H\033[2J");
                                    System.out.flush();
                                    break;
                                default:
                                    System.out.println("Invalid choice");
                                    break;
                            }
                        }
                    } else if (role == Auth.Role.USER) {
                        System.out.print("\033[H\033[2J");
                        System.out.flush();

                        System.out.println("Logged in as User");
                        loggedIn = true;

                        while (loggedIn) {
                            System.out.println("User Menu:");
                            System.out.println("1. View Profile");
                            System.out.println("2. Logout");
                            System.out.print("Enter your choice: ");
                            String userChoice = sc.nextLine();

                            switch (userChoice) {
                                case "1":
                                    System.out.print("\033[H\033[2J");
                                    System.out.flush();
                                    for (User user : DummyData.users()) {
                                        if (user.getEmail().equals(email)) {
                                            System.out.println("ID: " + user.getId() + ", Name: " + user.getName()
                                                    + ", Email: " + user.getEmail() + ", Pekerjaan: "
                                                    + user.getPekerjaan()
                                                    + ", Usia: " + user.getUsia());
                                            break;
                                        }
                                    }
                                    System.out.println("");
                                    break;
                                case "2":
                                    loggedIn = false;
                                    System.out.print("\033[H\033[2J");
                                    System.out.flush();
                                    break;
                                default:
                                    System.out.println("Invalid choice");
                                    break;
                            }
                        }
                    } else {
                        System.out.print("\033[H\033[2J");
                        System.out.flush();
                        System.out.println("Login failed");
                        System.out.println("");
                    }
                    break;
                case "2":
                    closed = true;
                    break;
                default:
                    System.out.println("Invalid input");
            }
        }
    }
}
