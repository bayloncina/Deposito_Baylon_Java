public class EsercizoFunzioni {

    public static void main(String[] args) {
        System.out.println(fattoriale(30));
        fattoriale("Maria");

    }

    // metodo che accetta intero
    static int fattoriale(int numero) {
        int fatt = 1;
        //attribuisco 1 a i perchè qualsiasi numero per 0 è 0
        for (int i = 1; i <= numero; i++) {
        //salvo il risultato in fatt ad ogni iterazione
            fatt *= i;
        }
        return fatt;
    }

    // metodo che accetta string
    static void fattoriale(String parola) {
        System.out.println("Inserisci numero valido");
    }
    
}
