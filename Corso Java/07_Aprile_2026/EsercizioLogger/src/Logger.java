public class Logger {

    // nessuno può creare un Logger
    private Logger() {
    }

    // campo dell'istanza private
    // È una variabile della classe, quindi esiste una sola volta
    // crea l'oggetto Logger e lo conserva in una variabile istanza
    private static Logger istanzaLogger;

    public static Logger getIstanza() {
        if (istanzaLogger == null) {
            istanzaLogger = new Logger(); // la crea solo la prima volta
        }
        return istanzaLogger; // le volte successive restituisce quella già creata
    }

    public void scriviMessaggio(String messaggio) {
        System.out.println(new java.util.Date() + " - " + messaggio);
    }
}
