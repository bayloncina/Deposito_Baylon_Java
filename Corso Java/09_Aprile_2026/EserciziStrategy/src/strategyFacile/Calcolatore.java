package strategyFacile;
//Context

public class Calcolatore {

    private IOperazione operazione;

    public Calcolatore(IOperazione operazione) {
        this.operazione = operazione;
    }

    public void setOperazione(IOperazione operazione) {
        this.operazione = operazione;
    }

    public void eseguiOperazione(int a , int b){
       int risultato = operazione.esegui(a, b);
    System.out.println("Risultato: " + risultato);
    }
}
