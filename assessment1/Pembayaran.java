public class Pembayaran {
    protected int id;
    protected String nama;
    protected String nominal;

    public Pembayaran(int id, String nama, String nominal) {
        this.id = id;
        this.nama = nama;
        this.nominal = nominal;
    }

    public int getId() {
        return id;
    }

    public String getNama() {
        return nama;
    }

    public String getNominal() {
        return nominal;
    }
}
