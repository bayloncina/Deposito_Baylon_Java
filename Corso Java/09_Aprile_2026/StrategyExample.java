/*
    PATTERN STRATEGY IN JAVA

    Concetto:
    Permette di definire una famiglia di algoritmi, incapsularli in classi
    separate e scambiarli a runtime senza modificare il codice che li usa.

    Struttura:
    - Strategy (interfaccia): dichiara il metodo comune a tutti gli algoritmi
    - Concrete Strategy (classi concrete): implementano ciascuna un algoritmo specifico
    - Context (classe che usa la strategia): mantiene un riferimento a una Strategy
      e delega a lei l'esecuzione del comportamento

    Esempio: un personaggio che può attaccare in modi diversi
*/

public class StrategyExample {

    public static void main(String[] args) {

        // creazione delle strategie concrete
        StrategiaAttacco spada = new AttaccoSpada();
        StrategiaAttacco arco  = new AttaccoArco();
        StrategiaAttacco magia = new AttaccoMagia();

        // il personaggio viene creato con una strategia iniziale
        Personaggio player = new Personaggio("Hero", spada);
        player.eseguiAttacco(); // usa AttaccoSpada

        // la strategia può essere cambiata in qualsiasi momento a runtime
        player.setStrategia(arco);
        player.eseguiAttacco(); // usa AttaccoArco

        player.setStrategia(magia);
        player.eseguiAttacco(); // usa AttaccoMagia
    }
}


/*
    STRATEGY (interfaccia)
    Dichiara il metodo che tutte le strategie concrete devono implementare.
*/
interface StrategiaAttacco {
    void attacca();
}


/*
    CONCRETE STRATEGY
    Ogni classe implementa un comportamento specifico.
*/
class AttaccoSpada implements StrategiaAttacco {
    @Override
    public void attacca() {
        System.out.println("Attacco con la spada!");
    }
}

class AttaccoArco implements StrategiaAttacco {
    @Override
    public void attacca() {
        System.out.println("Attacco con l'arco!");
    }
}

class AttaccoMagia implements StrategiaAttacco {
    @Override
    public void attacca() {
        System.out.println("Attacco con la magia!");
    }
}


/*
    CONTEXT
    Mantiene un riferimento a una Strategy e delega a lei l'esecuzione
    del comportamento, senza sapere quale algoritmo viene usato.

    Il CLIENT è chi crea il Context e sceglie quale strategia assegnargli.
    La scelta può avvenire in vari modi: input utente, menu, Observer, ecc.
*/
class Personaggio {

    private String nome;
    private StrategiaAttacco strategia;

    public Personaggio(String nome, StrategiaAttacco strategia) {
        this.nome = nome;
        this.strategia = strategia;
    }

    // permette di sostituire la strategia a runtime
    public void setStrategia(StrategiaAttacco strategia) {
        this.strategia = strategia;
    }

    public void eseguiAttacco() {
        System.out.print(nome + ": ");
        strategia.attacca(); // delega l'esecuzione alla strategia corrente
    }
}