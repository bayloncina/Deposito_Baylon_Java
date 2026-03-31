public class Gatto extends Animale {

    Gatto(String nome, int eta) {
        super(nome, eta);
    }

    @Override
    public void faiVerso() {
        System.out.println("Miao");
    }

    
    @Override
    public String toString() {
        return "Nome gatto " + this.getNome() + " Età: " + this.getEta();
    }
    
}
