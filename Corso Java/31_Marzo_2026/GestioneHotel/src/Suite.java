public class Suite extends Camera {

    private String serviziExtra;

    Suite(int numero, float prezzo, String serviziExtra) {
        super(numero, prezzo);
        this.serviziExtra = serviziExtra;

    }

    public String getServiziExtra() {
        return serviziExtra;
    }

    public void setServiziExtra(String serviziExtra) {
        this.serviziExtra = serviziExtra;
    }
    @Override
    public void dettagli() {
        System.out.println("Camera nuemro: " + this.getNumero() + " al prezzo di: " + this.getPrezzo() + " Servizi extra: "+ serviziExtra);
    }

    @Override
    public void dettagli(boolean conPrezzo) {
        super.dettagli(conPrezzo);
    }
}
