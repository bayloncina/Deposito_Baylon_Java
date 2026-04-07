public class App {
    public static void main(String[] args) throws Exception {
       // crea il subject
        ConcreteSubject subject = new ConcreteSubject();

        // crea due observer
        ConcreteObserver observer1 = new ConcreteObserver("Observer 1");
        ConcreteObserver observer2 = new ConcreteObserver("Observer 2");

        // registra gli observer
        subject.registerObserver(observer1);
        subject.registerObserver(observer2);

        // cambia lo stato — tutti gli observer vengono notificati
        subject.setState("Stato A");
        subject.setState("Stato B");

        // rimuovi observer1 e cambia di nuovo lo stato
        subject.removeObserver(observer1);
        subject.setState("Stato C");
    }
}
