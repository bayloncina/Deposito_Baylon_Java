package strategy;

public class EvasioneControllata implements StartegiaEvasione {

    private double incremento = -0.05;

    @Override
    public void eseguiEvasione(double prezzo) {
         System.out.println("Il prezzo da pagare è euro " + (prezzo + (prezzo * incremento)));
    }

}
