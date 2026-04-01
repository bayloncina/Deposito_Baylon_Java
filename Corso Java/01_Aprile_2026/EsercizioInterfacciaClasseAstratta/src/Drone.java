public class Drone extends VeicoloConsegna implements ITracciabile {

    public Drone(String targa, float caricoMassimo) {
        super(targa, caricoMassimo);
    }

    @Override
    public void tracciaConsegna(String codiceTracking) {
        System.out.println("Tracking drone con targa: " + getTarga() + " - Codice di tracciamento: " + codiceTracking
                + " | Stato: In volo");
    }

    @Override
    public void consegnaPacco(String destinazione, float peso) {
        System.out.println("Drone con la targa: " + getTarga() + " sta consegnando in: " + destinazione);
    }

    @Override
    public String toString() {
        return "Drone | " + super.toString();
    }
}
