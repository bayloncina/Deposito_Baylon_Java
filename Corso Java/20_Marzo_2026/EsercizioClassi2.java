class Persona {
    // attributi
    String nome;
    int eta;
    String city;

    // costruttore
    Persona(String nome, int eta, String city) {
        this.nome = nome;
        this.eta = eta;
        this.city = city;
    }

    Persona (){

    }

    // metodo che stampa dettagli
    public void stampaDettagli() {
        System.out.println(" Nome " + nome + " Età " + eta + " Città " + city);
    }
}

public class EsercizioClassi2 {
    public static void main(String[] args) {
        // creo oggetto perona
        Persona persona1 = new Persona("Miriam", 27, "Barletta");
        Persona persona2 = new Persona("Giorgia", 24, "Bari");
        Persona persona3 = new Persona();
        persona3.nome = "Giulia";
        persona3.eta = 55;
        persona3.city = "Molfetta";

        // richiamo metodo stampaDettagli classe Persona
        persona1.stampaDettagli();
        persona2.stampaDettagli();
        persona3.stampaDettagli();
    }

}
