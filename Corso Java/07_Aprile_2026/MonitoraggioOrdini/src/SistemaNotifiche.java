public class SistemaNotifiche implements OsservatoreOrdine {
    @Override
    public void aggiorna(Ordine ordine) {
        System.out.println("[NOTIFICHE] Cliente " + ordine.getCliente()
                + " il tuo ordine " + ordine.getId()
                + " è ora: " + ordine.getStato());
    }
}