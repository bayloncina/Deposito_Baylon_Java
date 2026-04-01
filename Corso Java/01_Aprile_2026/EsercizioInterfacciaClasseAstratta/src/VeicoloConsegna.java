abstract class VeicoloConsegna {

    private String targa;
    private float caricoMassimo;

    public VeicoloConsegna(String targa, float caricoMassimo) {
        this.targa = targa;
        this.caricoMassimo = caricoMassimo;
    }

    // getter e setter
    public String getTarga() {
        return targa;
    }

    public void setTarga(String targa) {
        this.targa = targa;
    }

    public float getCaricoMassimo() {
        return caricoMassimo;
    }

    public void setCaricoMassimo(float caricoMassimo) {
        this.caricoMassimo = caricoMassimo;
    }

    // metodo astratto ogni sottoclasse lo implementa a modo suo
    public abstract void consegnaPacco(String destinazione, float peso);

    @Override
    public String toString() {
        return "Targa: " + targa + " | Carico massimo: " + caricoMassimo + " kg";
    }
}