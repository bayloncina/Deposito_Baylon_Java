public class Furgone extends VeicoloConsegna implements ITracciabile{

    public Furgone(String targa, float caricoMassimo) {
        super(targa, caricoMassimo);
    }


    //metodo astratto Obbligatorio della classe astratta
    @Override
    public void consegnaPacco(String destinazione, float peso) {
        System.out.println("Furgone con la targa: " + getTarga() + " sta consegnando in: " + destinazione);
    }

    //metodo obbligatorio dell'interfaccia
    @Override
    public void tracciaConsegna(String codiceTracking) {
        System.out.println("Tracking Furgone con targa: " + getTarga() + " - Codice di tracciamento: " + codiceTracking + " | Stato: In transito su strada");
    }
    
    @Override
    public String toString() {
        return "Furgone | " + super.toString();
    }
}
