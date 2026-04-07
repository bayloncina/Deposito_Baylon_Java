public class App {
    public static void main(String[] args) throws Exception {
        
        AgenziaBorsa agB = new AgenziaBorsa();

        InvestitoreBancario iBancario = new InvestitoreBancario("Investitore Bancario");

        InvestitorePrivato iPrivato = new InvestitorePrivato("Investitore Privato");

        agB.aggiungiInestitore(iBancario);
        agB.aggiungiInestitore(iPrivato);
        agB.aggiornaValoreazione("Azione1", 20000);
        agB.aggiornaValoreazione("Azione2", 40000);

        // rimuovi uno e cambia ancora
        agB.rimuoviInvestitore(iBancario);
        agB.aggiornaValoreazione("Azione3", 60000); // solo iPrivato riceve

    }
}
