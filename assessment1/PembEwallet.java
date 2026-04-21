public class PembEWallet extends Pembayaran {
    private String eWallet;

    public PembEWallet(int id, String nama, String nominal, String eWallet) {
        super(id, nama, nominal);
        this.eWallet = eWallet;
    }

    public String getEWallet() {
        return eWallet;

    }
}
