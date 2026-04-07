//Observer

public class InvestitorePrivato implements Investitore {
   
    private String nome;

    public InvestitorePrivato(String nome) {
        this.nome = nome;
    }

    @Override
    public void notifica(String azione, double valore) {

        System.out.println(nome + azione + " il valore attuale è " + valore);
    }

}
