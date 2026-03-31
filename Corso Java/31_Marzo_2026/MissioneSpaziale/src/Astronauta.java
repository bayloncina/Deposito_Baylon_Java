import java.util.Random;

public class Astronauta {
    protected String nome;
    protected String email;
    protected float creditoOssigeno;

    private static final Random random = new Random();

    public Astronauta(String nome, String email) {
        this.nome = nome;
        this.email = email;
        this.creditoOssigeno = login(); // inizializzato al momento della creazione
    }

    // Rigenera l'ossigeno in modo casuale (simula il login)
    public float login() {
        this.creditoOssigeno = 50 + random.nextFloat() * 50; // valore tra 50 e 100
        return this.creditoOssigeno;
    }

    public void visualizzaDati() {
        System.out.println("=== Dati Astronauta ===");
        System.out.println("Nome: " + nome);
        System.out.println("Email: " + email);
        System.out.printf("Credito Ossigeno: %.2f%n", creditoOssigeno);
    }

    public String getNome() { return nome; }
    public String getEmail() { return email; }
    public float getCreditoOssigeno() { return creditoOssigeno; }
}