public class EsempioFunzioni {

    public static void main(String[] args) {

        System.out.println(somma(10, 20));
        mostra(10);
        mostra("Ciao");

    }

    static int somma(int a, int b) {
        return a + b;
    }

    static void mostra(String testo) {
        System.out.println("Testo: " + testo);
    }

    static void mostra(int numero) {
        System.out.println("Numero: " + numero);
    }

    // ricorsione
    static int fattoriale2(int n) {
        if (n == 1)
            return 1;
        return n * fattoriale2(n - 1);
    }

    // passaggio per valore

    /*
     * Il metodo lavora sulla copia, non sull'originale. Quindi qualsiasi modifica
     * fatta dentro
     * il metodo rimane confinata lì dentro e quando il metodo finisce la modifica
     * sparisce
     * la variabile originale nel main non cambia.
     */
    static void modifica(int x) {
        x = x + 10; // questa modifica non sarà visibile all'esterno
    }
    // N.B. con i primitivi passi il valore, con gli array passi l'indirizzo
    /*
     * PASSAGGIO PER RIFERIMENTO
     * Quando passi un array invece non viene copiato il contenuto, viene passato
     * l'indirizzo in memoria dove l'array si trova. Il metodo quindi lavora
     * direttamente sull'array originale.
     * Qualsiasi modifica fatta dentro il metodo è visibile anche fuori perché stai
     * modificando lo stesso oggetto in memoria.
     */
    static void modifica(int[] arr) {
        arr[0] = 99; // questa modifica sarà visibile all'esterno
    }
}
