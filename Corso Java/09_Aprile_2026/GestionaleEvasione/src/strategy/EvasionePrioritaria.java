package strategy;

public class EvasionePrioritaria implements StartegiaEvasione {

    private double incremento = 0.15;

    @Override
    public void eseguiEvasione(double prezzo) {
      System.out.println("Il prezzo da pagare è euro " + (prezzo + (prezzo * incremento)));
   
   
    
}}
