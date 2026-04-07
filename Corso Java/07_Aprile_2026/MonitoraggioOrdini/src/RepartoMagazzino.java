public class RepartoMagazzino implements OsservatoreOrdine {
    @Override
    public void aggiorna(Ordine ordine) {
        if (ordine.getStato() == StatoOrdine.IN_PREPARAZIONE) {
            System.out.println("[MAGAZZINO] Ordine " + ordine.getId()
                    + " entrato in preparazione.");
        }
    }
}