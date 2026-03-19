public class EsempioFunzioni {

    public static void main(String[] args) {

        System.out.println(somma(10,20));
        mostra(10);
        mostra("Ciao");

    }

    static int somma (int a, int b){
        return a+b;
    }

    static void mostra (String testo){
        System.out.println("Testo: " + testo);
    }

    static void mostra (int numero){
        System.out.println("Numero: " + numero);
    }

    
}
