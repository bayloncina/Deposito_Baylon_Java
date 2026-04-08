class MessaggioBase implements Messaggio {

    private String testo;

    public MessaggioBase(String testo) {
        this.testo = testo;
    }

    @Override
    public String getContenuto() {
        return testo;
    }

}