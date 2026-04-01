public class Giacca extends CapoPrincipale {


    private int numeroBottoni;


    public Giacca(String codice, String nome, String tessuto, String colore, String taglia, double prezzo,
            int numeroBottoni) {
        super(codice, nome, tessuto, colore, taglia, prezzo);
        this.numeroBottoni = numeroBottoni;
    }

    public int getNumeroBottoni() {
        return numeroBottoni;
    }

    public void setNumeroBottoni(int numeroBottoni) {
        this.numeroBottoni = numeroBottoni;
    }

    @Override
    public void mostraDettagli() {
        super.mostraDettagli();
        System.out.println(" | Numero bottoni: " + numeroBottoni);
    }
}
