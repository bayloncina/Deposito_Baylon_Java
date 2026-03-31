public class Animale {
    private String nome;
    private int eta;

    Animale (String nome,int eta ){
        this.nome = nome;
        this.eta = eta;
    }

    public int getEta() {
        return eta;
    }
    public String getNome() {
        return nome;
    }
    public void setEta(int eta) {
        this.eta = eta;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public void faiVerso(){
        System.out.println("Verso generico");
    }

    public String toString() {
        return "Animale: " + nome + " Età: " + eta;
    }
    
}
