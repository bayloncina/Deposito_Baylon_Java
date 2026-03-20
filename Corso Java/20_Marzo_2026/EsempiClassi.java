
class Auto {
    String marca;
    int anno;
    double prezzo;

    void mostraInfo() {
        System.out.println(marca + " " + anno + " €" + prezzo);
    }
}

class Studente {
    String nome; //Variabile di istanza
    static int totaleStudenti = 0; //variabile statica
    double prezzo;

   Studente (String nome){ //parametro
    this.nome = nome;
    totaleStudenti++; //incremento studenti
   }
}

public class EsempiClassi {
    public static void main(String[] args) {

        // crea oggetto Auto
        Auto miaAuto = new Auto();
        // Studente studente1 = new Studente ("Miriam");

        // assegna valori agli attributi
        miaAuto.marca = "Tesla";
        miaAuto.anno = 2024;
        miaAuto.prezzo = 45000.00;

        // chiama il metodo dell'oggetto
        miaAuto.mostraInfo();

        // puoi creare quanti oggetti vuoi dallo stesso modello
        Auto altraAuto = new Auto();
        altraAuto.marca = "Ferrari";
        altraAuto.anno = 2023;
        altraAuto.prezzo = 250000.00;
        altraAuto.mostraInfo();
    }
}