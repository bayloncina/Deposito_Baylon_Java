import java.util.ArrayList;
import java.util.Collections;

public class EsempioArrayList {
    public static void main(String[] args) {

        //array dinamico a dimensione variabile
       ArrayList<Integer> numeri = new ArrayList<>();
       numeri.add(10);
       numeri.add(20);
       numeri.add(30);

       //si usano "classi" di dati (Wrapper) come Integer - String - Double anzichè tipi primitivi
       // si usa size() per la dimensione dell ArrayList 
       ArrayList<String> nomi = new ArrayList<>();
       nomi.add("Alice");
       nomi.add("Bob");
       nomi.add("Carlo");

        //creo lista di numeri
        ArrayList<Integer> numeri2 = new ArrayList<>();

        //aggiungo 10 numeri casuali alla lista
        for(int i = 0; i< 10; i++){
            numeri2.add((int) (Math.random() * 100) + 1);
        }
        //stampa lista originale
        System.out.println("Lista originila nuemri" + numeri2);

        //ordina lista
        Collections.sort(numeri2);

        //stampa lista ordinata
        System.out.println("Lista ordinata" + numeri2);


    }
    
}
