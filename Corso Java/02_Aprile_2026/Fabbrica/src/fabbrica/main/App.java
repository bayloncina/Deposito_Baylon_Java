package fabbrica.main;

import fabbrica.produzione.GestioneFabbrica;

public class App {
    public static void main(String[] args) throws Exception {
        GestioneFabbrica gestore = new GestioneFabbrica();
        gestore.startFabbrica();
    }
}
