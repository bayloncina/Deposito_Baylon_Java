// Cane estende Animale — 
// eredita tutti gli attributi e metodi della classe padre
public class Cane extends Animale {

    Cane(String nome, int eta) {
        // costruttore
        // passa nome ed età al costruttore della classe padre tramite super
        super(nome, eta);
    }

    // Override: sovrascrive il metodo faiVerso() della classe padre
    // invece di "Verso generico" stampa "Bau Bau"
    @Override
    public void faiVerso() {
        System.out.println("Bau Bau");

    }

    // sovrascrive il toString() della classe padre
    // personalizza la stampa aggiungendo "Nome cane" come prefisso
    // usa getNome() e getEta() perché nome ed età sono privati in Animale
    @Override
    public String toString() {
        return "Nome cane " + this.getNome() + " Età: " + this.getEta();
    }
}
