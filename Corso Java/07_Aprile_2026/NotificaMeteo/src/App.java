public class App {
    public static void main(String[] args) throws Exception {
        // crea la stazione meteo
        StazioneMeteo stazione = new StazioneMeteo();

        // crea i display
        DisplayConsole console = new DisplayConsole("Console");
        DisplayMobile mobile = new DisplayMobile("Mobile");

        // registra i display
        stazione.aggiungiDisplay(console);
        stazione.aggiungiDisplay(mobile);

        // cambia la temperatura entrambi i display vengono notificati
        stazione.setTemperatura(30.5f);
        stazione.setTemperatura(22.0f);

        // rimuovi il display console e cambia ancora
        stazione.rimuoviDisplay(console);
        stazione.setTemperatura(10.0f);
    }
}
