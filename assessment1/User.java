
public class User extends Account {
    private String pekerjaan;
    private int usia;

    public User(int id, String name, String email, String pass, String pekerjaan, int usia) {
        super(id, name, email, pass);
        this.setPekerjaan(pekerjaan);
        this.setUsia(usia);
    }

    public String getPekerjaan() {
        return pekerjaan;
    }

    public void setPekerjaan(String pekerjaan) {
        if (pekerjaan == null || pekerjaan.trim().isEmpty()) {
            throw new IllegalArgumentException("Pekerjaan cannot be empty");
        }
        this.pekerjaan = pekerjaan;
    }

    public int getUsia() {
        return usia;
    }

    public void setUsia(int usia) {
        if (usia <= 0 || usia > 150) {
            throw new IllegalArgumentException("Usia must be between 1 and 150");
        }
        this.usia = usia;
    }
}