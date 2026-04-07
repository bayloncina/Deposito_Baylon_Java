// Definizione della classe Singleton
class Singleton {
  // Istanza privata statica della classe Singleton
  private static Singleton instance;

  // Costruttore privato per impedire l'istanziazione diretta
  private Singleton() {
  }

  // Metodo pubblico statico per ottenere l'unica istanza della classe
  public static Singleton getInstance() {
    // Se l'istanza non esiste, viene creata
    if (instance == null) {
      instance = new Singleton();
    }
    // Restituisce l'istanza esistente o appena creata
    return instance;
  }

  // Metodo di esempio che può essere chiamato sull'istanza Singleton
  public void DoSomething() {
    System.out.println("Singleton: DoSomething() called");
  }
}

class Logger{

  //Itsanza privata statica della classe logger
  private static Logger instanceLogger;

  

}

public class EsempioSingleton {
  public static void main(String[] args) {
    Singleton s1 = Singleton.getInstance();
    Singleton s2 = Singleton.getInstance();

    // Chiama il metodo
    s1.DoSomething();

    // Verifica che s1 e s2 siano la stessa istanza
    System.out.println(s1 == s2); // true
  }

}