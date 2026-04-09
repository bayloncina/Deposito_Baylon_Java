package decorator;

import strategy.StartegiaEvasione;

public abstract class DecoratorEvasione implements StartegiaEvasione {

    protected StartegiaEvasione strategia;

    public DecoratorEvasione(StartegiaEvasione strategia) {
        this.strategia = strategia;
    }

    @Override
    public void eseguiEvasione(double prezzo) {
        strategia.eseguiEvasione(prezzo);
    }
}
