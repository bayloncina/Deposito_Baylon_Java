public class Utente {

    private String nome;
    private String password;

    private static Utente istanzaUtente;

    private Utente() {
    }

    public static Utente getIstanzaUtente() {
        if (istanzaUtente == null) {
            istanzaUtente = new Utente();
        }
        return istanzaUtente;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNome() {
        return nome;
    }

    public String getPassword() {
        return password;
    }

}
