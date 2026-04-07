import java.util.ArrayList;
import java.util.List;

// Observer: interfaccia che tutti gli "ascoltatori" devono implementare
// chiunque voglia ricevere notifiche deve avere un metodo update()
interface Observer {
    void update(String message);
}

// Subject: interfaccia per chi "pubblica" i cambiamenti
// chi la implementa deve poter aggiungere, rimuovere e notificare gli observer
interface Subject {
    void registerObserver(Observer o);  // aggiunge un observer alla lista
    void removeObserver(Observer o);    // rimuove un observer dalla lista
    void notifyObservers();             // avvisa tutti gli observer
}

// ConcreteSubject: è il "pubblicatore" concreto
// tiene la lista degli observer e uno stato interno
class ConcreteSubject implements Subject {
    // lista di tutti gli observer registrati
    private List<Observer> observers = new ArrayList<>();
    // lo stato che quando cambia viene comunicato a tutti
    private String state;

    // quando lo stato cambia, notifica automaticamente tutti gli observer
    public void setState(String state) {
        this.state = state;
        notifyObservers(); // avvisa subito tutti
    }

    // aggiunge un observer alla lista
    public void registerObserver(Observer o) {
        observers.add(o);
    }

    // rimuove un observer dalla lista
    public void removeObserver(Observer o) {
        observers.remove(o);
    }

    // scorre tutta la lista e chiama update() su ognuno
    public void notifyObservers() {
        for (Observer o : observers) {
            o.update(state); // passa lo stato aggiornato a ogni observer
        }
    }
}

// ConcreteObserver: è il "ascoltatore" concreto
// ogni istanza ha un nome e sa cosa fare quando riceve una notifica
class ConcreteObserver implements Observer {
    private String name;

    public ConcreteObserver(String name) {
        this.name = name;
    }

    // questo metodo viene chiamato automaticamente da notifyObservers()
    public void update(String message) {
        System.out.println(name + " ha ricevuto aggiornamento: " + message);
    }
}