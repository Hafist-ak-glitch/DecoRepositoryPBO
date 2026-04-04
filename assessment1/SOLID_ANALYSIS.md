# SOLID & Encapsulation Analysis

## Project: Energy Bersih dan Terjangkau

---

## 1. Encapsulation Analysis

### BEFORE (Non-Encapsulated)

```java
// Account.java — properties were protected, no validation
public class Account {
    protected int id;
    protected String name;
    protected String email;
    protected String pass;

    public Account(int id, String name, String email, String pass) {
        this.id = id;          // no validation
        this.name = name;      // could be null or empty
        this.email = email;    // could be "not-an-email"
        this.pass = pass;      // could be "1" (too short)
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getPass() { return pass; }
    // NO SETTERS — cannot update data after creation
}
```

```java
// User.java — protected fields, anyone in the same package can write directly
public class User extends Account {
    protected String pekerjaan;  // direct access: user.pekerjaan = anything
    protected int usia;          // direct access: user.usia = -999 (invalid!)
}
```

```java
// Auth.java — package-private access (no modifier), no getters/setters
public class Auth {
    String email;              // package-private, no protection
    String pass;               // package-private, no protection
    Role role;                 // unused field, wastes memory
    ArrayList<User> users;     // exposed directly
    ArrayList<Admin> admins;   // exposed directly
}
```

**Problems:**
- `protected` allows any subclass or same-package class to bypass validation
- No setters means data cannot be safely updated after object creation
- No validation at all — objects can be created with invalid data
- Auth fields were package-private (default access) — even worse than protected

---

### AFTER (Properly Encapsulated)

```java
// Account.java — private fields + validated setters
public class Account {
    private int id;
    private String name;
    private String email;
    private String pass;

    public Account(int id, String name, String email, String pass) {
        this.setId(id);       // constructor uses setters → validation runs
        this.setName(name);
        this.setEmail(email);
        this.setPass(pass);
    }

    public void setId(int id) {
        if (id <= 0) throw new IllegalArgumentException("ID must be positive");
        this.id = id;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty())
            throw new IllegalArgumentException("Name cannot be empty");
        this.name = name;
    }

    public void setEmail(String email) {
        if (email == null || !email.contains("@"))
            throw new IllegalArgumentException("Invalid email format");
        this.email = email;
    }

    public void setPass(String pass) {
        if (pass == null || pass.length() < 5)
            throw new IllegalArgumentException("Password must be at least 5 characters");
        this.pass = pass;
    }
}
```

```java
// User.java — private fields + validated setters
public class User extends Account {
    private String pekerjaan;
    private int usia;

    public void setPekerjaan(String pekerjaan) {
        if (pekerjaan == null || pekerjaan.trim().isEmpty())
            throw new IllegalArgumentException("Pekerjaan cannot be empty");
        this.pekerjaan = pekerjaan;
    }

    public void setUsia(int usia) {
        if (usia <= 0 || usia > 150)
            throw new IllegalArgumentException("Usia must be between 1 and 150");
        this.usia = usia;
    }
}
```

```java
// Auth.java — private fields + validated setters, removed unused field
public class Auth {
    private String email;
    private String pass;
    private ArrayList<User> users;
    private ArrayList<Admin> admins;

    // Each setter validates input before assignment
}
```

**What changed:**
| Aspect | Before | After |
|--------|--------|-------|
| Access modifiers | `protected` / package-private | `private` |
| Setters | None | All fields have validated setters |
| Constructor | Direct assignment | Calls setters (validation runs at creation) |
| Unused fields | `Role role` in Auth | Removed |

---

## 2. SOLID Principles Analysis

### S — Single Responsibility Principle (SRP)

> "A class should have one, and only one, reason to change."

**Violation in `Main.java`:**

```java
// Main.java has AT LEAST 4 responsibilities:
public class Main {
    public static void main(String[] args) {
        // Responsibility 1: Application startup & main loop
        while (closed == false) { ... }

        // Responsibility 2: Login/authentication flow
        Auth auth = new Auth(email, pass, DummyData.users(), DummyData.admins());
        Auth.Role role = auth.login();

        // Responsibility 3: Admin menu logic (50+ lines)
        while (loggedIn) {
            System.out.println("Admin Menu:");
            System.out.println("1. View Users");
            // ... view users, admins, profile, logout
        }

        // Responsibility 4: User menu logic (30+ lines)
        while (loggedIn) {
            System.out.println("User Menu:");
            System.out.println("1. View Profile");
            // ... view profile, logout
        }
    }
}
```

**Why it violates SRP:**
- If the admin menu changes → you modify Main.java
- If the user menu changes → you modify Main.java
- If login logic changes → you modify Main.java
- If display format changes → you modify Main.java
- One class, four reasons to change

**How to fix:** Extract `AdminMenu` and `UserMenu` into separate classes, each with one responsibility.

---

### O — Open/Closed Principle (OCP)

> "Open for extension, closed for modification."

**Violation in `Auth.java`:**

```java
// Adding a new role (e.g., Moderator) requires MODIFYING this class:
public class Auth {
    enum Role { ADMIN, USER }  // 1. Add MODERATOR here

    ArrayList<User> users;
    ArrayList<Admin> admins;
    // ArrayList<Moderator> moderators;  // 2. Add new field

    public Role login() {
        for (Admin a : admins) { ... }       // existing loop
        for (User u : users) { ... }         // existing loop
        // for (Moderator m : moderators)    // 3. Add new loop
        return null;
    }
}
```

**Why it violates OCP:**
- Every new role requires 3 modifications to Auth.java:
  1. New enum value
  2. New ArrayList field + constructor parameter
  3. New for-loop in login()
- The class is NOT closed for modification

**How to fix:** Use a single `List<Account>` and one loop. New account types just extend Account — Auth stays unchanged.

---

### L — Liskov Substitution Principle (LSP)

> "Subclasses should be replaceable for their superclass without breaking the application."

**Violation in `Main.java`:**

```java
// Main.java cannot treat Admin and User as Account interchangeably:
// It uses Auth.Role enum to distinguish types, then writes TYPE-SPECIFIC code:

if (role == Auth.Role.ADMIN) {
    // Admin-specific display: manually calls admin.getId(), admin.getName()...
    for (Admin admin : DummyData.admins()) {
        System.out.println("ID: " + admin.getId() + ", Name: " + admin.getName()
            + ", Email: " + admin.getEmail());
    }
} else if (role == Auth.Role.USER) {
    // User-specific display: manually calls user.getPekerjaan(), user.getUsia()...
    for (User user : DummyData.users()) {
        System.out.println("ID: " + user.getId() + ", Name: " + user.getName()
            + ", Pekerjaan: " + user.getPekerjaan() + ", Usia: " + user.getUsia());
    }
}
```

**Why it violates LSP:**
- Auth returns a `Role` enum, not an `Account` object — polymorphism is lost
- Main.java has separate display logic for each subclass
- User and Admin are NOT interchangeable through their Account superclass
- If you add a new subclass, Main breaks until you add another else-if

**How to fix:** Auth.login() returns `Account`. Each subclass overrides a `displayInfo()` method. Main calls `account.displayInfo()` without knowing the concrete type.

---

### I — Interface Segregation Principle (ISP)

> "No client should be forced to depend on methods it does not use."

**Violation (missing interfaces):**

```java
// Main.java depends on the FULL Account/User/Admin classes just to display info.
// It calls getId(), getName(), getEmail(), getPass(), getPekerjaan(), getUsia()...
// But for DISPLAY purposes, it only needs a "give me your info" method.

// Without an interface, any code that just needs to display an account
// is forced to depend on ALL Account methods (setters, getters, password, etc.)
```

**Why it violates ISP:**
- No interfaces exist — clients depend on full concrete classes
- Display logic doesn't need setters or password access, but gets them anyway

**How to fix:** Create a `Displayable` interface with `displayInfo()` method.

---

### D — Dependency Inversion Principle (DIP)

> "Depend on abstractions, not concretions."

**Violation in `Auth.java` and `Main.java`:**

```java
// Auth.java depends on CONCRETE types:
public class Auth {
    private ArrayList<User> users;    // concrete: ArrayList<User>
    private ArrayList<Admin> admins;  // concrete: ArrayList<Admin>

    public Auth(String email, String pass,
                ArrayList<User> userList,    // depends on concrete User
                ArrayList<Admin> adminList)  // depends on concrete Admin
}

// Main.java depends on CONCRETE DummyData methods:
Auth auth = new Auth(email, pass,
    DummyData.users(),     // concrete: returns ArrayList<User>
    DummyData.admins()     // concrete: returns ArrayList<Admin>
);
```

**Why it violates DIP:**
- Auth (high-level module) depends on User and Admin (low-level modules) directly
- Uses `ArrayList` (concrete) instead of `List` (abstraction)
- Maintains separate lists per type instead of one unified `List<Account>`

**How to fix:** Auth receives `List<Account>` — depends on the abstraction (Account superclass), not the concretions (User, Admin).

---

## 3. Summary Table

| Principle | Status | Where | Issue |
|-----------|--------|-------|-------|
| **Encapsulation** | ✅ Fixed | Account, User, Auth | `protected` → `private`, added validated setters |
| **SRP** | ❌ Violated | Main.java | 4 responsibilities in one class |
| **OCP** | ❌ Violated | Auth.java | Must modify to add new roles |
| **LSP** | ❌ Violated | Main.java | Type-specific code, no polymorphism |
| **ISP** | ❌ Violated | All classes | No interfaces, clients depend on full classes |
| **DIP** | ❌ Violated | Auth.java, Main.java | Depends on concrete types, not abstractions |

> **Note:** Encapsulation has been implemented in this project. The SOLID violations above are documented as analysis for educational purposes — showing what the current code violates and how it could be improved.
