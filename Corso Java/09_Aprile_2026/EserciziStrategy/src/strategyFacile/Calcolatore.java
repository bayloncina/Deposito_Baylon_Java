package strategyFacile;
//Context

public class Calcolatore {

    private Operazione operazione;

    public Calcolatore(Operazione operazione) {
        this.operazione = operazione;
    }

    public void setOperazione(Operazione operazione) {
        this.operazione = operazione;
    }

    public void eseguiOperazione(int a , int b){
       int risultato = operazione.esegui(a, b);
    System.out.println("Risultato: " + risultato);
    }
}
