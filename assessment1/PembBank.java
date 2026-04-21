public class PembBank extends Pembayaran {
    private String bank;

    public PembBank(int id, String nama, String nominal, String bank) {
        super(id, nama, nominal);
        this.bank = bank;
    }

    public String getBank() {
        return bank;
    }
}