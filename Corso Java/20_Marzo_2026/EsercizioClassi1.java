class Libro {
    // attributi
    String nome;
    String autore;
    double prezzo;
    // static appartiene alla classe e non all'oggetto
    // condiviso tra tutti i libri si incrementa ad ogni nuovo libro creato
    static int codice_numerico_autoincrementante = 0;

    // costruttore viene chiamato quando si crea un nuovo oggetto Libro
    // this distingue l'attributo della classe dal parametro del costruttore
    Libro(String nome, String autore, double prezzo) {
        this.nome = nome;
        this.autore = autore;
        this.prezzo = prezzo;
        // incrementa il codice ogni volta che viene creato un nuovo libro
        System.out.println(codice_numerico_autoincrementante++);
    };

    // metodo che stampa il libro
    public void stampaLibro() {
        System.out.println(" TITOLO: " + nome + " AUTORE: " + autore + " PREZZO: euro" + prezzo);
    }

}

public class EsercizioClassi1 {
    public static void main(String[] args) {
        // crea due oggetti Libro usando il costruttore
        Libro libro1 = new Libro("Io non ho paura", "Nicolò Ammaniti", 10);
        Libro libro2 = new Libro("L'Alchimista", "Paulo Coelho", 10);

        // stampa gli attributi di ogni oggetto accedendo direttamente ai campi senza metodo
        /*
         * System.out.println(libro1.nome + " " + libro1.autore + " " + libro1.prezzo);
         * System.out.println(libro2.nome + " " + libro2.autore + " " + libro2.prezzo);
         */
        libro1.stampaLibro();
        libro2.stampaLibro();
    }

}
