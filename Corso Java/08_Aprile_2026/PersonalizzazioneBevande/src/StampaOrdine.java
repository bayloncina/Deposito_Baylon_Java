public class StampaOrdine implements OrdineObserver {
    @Override
    public void onOrdineConfermato(String descrizione, double costo) {
        System.out.println("Nuovo ordine ricevuto: " + descrizione);
        System.out.printf("   Totale: %.2f euro%n", costo);
    }
}