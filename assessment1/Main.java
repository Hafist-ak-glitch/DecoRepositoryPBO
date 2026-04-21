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
                            System.out.println("4. View Content");
                            System.out.println("5. Logout");
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
                                    System.out.print("\033[H\033[2J");
                                    System.out.flush();
                                    System.out.println("Content List:");
                                    // Polymorphism: Loop melalui interface Content
                                    for (Content content : DummyData.quizzes()) {
                                        System.out.println("ID: " + content.getId() + ", Title: " + content.getTitle()
                                                + ", Content: " + content.getContent() + ", Author: " + content.getAuthor());
                                    }
                                    System.out.println("");
                                    break;
                                case "5":
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
                            System.out.println("2. View Content");
                            System.out.println("3. Pembayaran");
                            System.out.println("4. Logout");
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
                                    System.out.print("\033[H\033[2J");
                                    System.out.flush();
                                    System.out.println("Content List:");
                                    // Polymorphism: Loop melalui interface Content
                                    for (Content content : DummyData.quizzes()) {
                                        System.out.println("ID: " + content.getId() + ", Title: " + content.getTitle()
                                                + ", Content: " + content.getContent() + ", Author: " + content.getAuthor());
                                    }
                                    System.out.println("");
                                    break;
                                case "3":
                                    System.out.print("\033[H\033[2J");
                                    System.out.flush();
                                    System.out.println("=== Menu Pembayaran ===");
                                    System.out.println("1. Pembayaran Bank");
                                    System.out.println("2. Pembayaran E-Wallet");
                                    System.out.println("3. Kembali");
                                    System.out.print("Pilih metode pembayaran: ");
                                    String pembayaranChoice = sc.nextLine();
                                    
                                    switch (pembayaranChoice) {
                                        case "1":
                                            System.out.print("\033[H\033[2J");
                                            System.out.flush();
                                            System.out.println("=== Pembayaran Bank ===");
                                            System.out.print("ID Pembayaran: ");
                                            int idBank = Integer.parseInt(sc.nextLine());
                                            System.out.print("Nama Pemilik: ");
                                            String namaBank = sc.nextLine();
                                            System.out.print("Nominal (Rp): ");
                                            String nominalBank = sc.nextLine();
                                            System.out.print("Nama Bank: ");
                                            String namaBank2 = sc.nextLine();
                                            
                                            PembBank pembBank = new PembBank(idBank, namaBank, nominalBank, namaBank2);
                                            System.out.println("\n--- Rincian Pembayaran Bank ---");
                                            System.out.println("ID: " + pembBank.getId());
                                            System.out.println("Nama: " + pembBank.getNama());
                                            System.out.println("Nominal: " + pembBank.getNominal());
                                            System.out.println("Bank: " + pembBank.getBank());
                                            System.out.println("Pembayaran Bank berhasil!\n");
                                            break;
                                        case "2":
                                            System.out.print("\033[H\033[2J");
                                            System.out.flush();
                                            System.out.println("=== Pembayaran E-Wallet ===");
                                            System.out.print("ID Pembayaran: ");
                                            int idEWallet = Integer.parseInt(sc.nextLine());
                                            System.out.print("Nama Pemilik: ");
                                            String namaEWallet = sc.nextLine();
                                            System.out.print("Nominal (Rp): ");
                                            String nominalEWallet = sc.nextLine();
                                            System.out.print("Nama E-Wallet (GCash/PayMaya/OVO/etc): ");
                                            String namaEWallet2 = sc.nextLine();
                                            
                                            pembEWallet pembEW = new pembEWallet(idEWallet, namaEWallet, nominalEWallet, namaEWallet2);
                                            System.out.println("\n--- Rincian Pembayaran E-Wallet ---");
                                            System.out.println("ID: " + pembEW.getId());
                                            System.out.println("Nama: " + pembEW.getNama());
                                            System.out.println("Nominal: " + pembEW.getNominal());
                                            System.out.println("E-Wallet: " + pembEW.getEWallet());
                                            System.out.println("Pembayaran E-Wallet berhasil!\n");
                                            break;
                                        case "3":
                                            System.out.print("\033[H\033[2J");
                                            System.out.flush();
                                            break;
                                        default:
                                            System.out.println("Pilihan tidak valid");
                                            break;
                                    }
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
