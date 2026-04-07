public class RepartoSpedizioni implements OsservatoreOrdine {
    @Override
    public void aggiorna(Ordine ordine) {
        if (ordine.getStato() == StatoOrdine.SPEDITO) {
            System.out.println("[SPEDIZIONI] Ordine " + ordine.getId()
                    + " pronto per la spedizione al cliente: "
                    + ordine.getCliente());
        }
    }
}