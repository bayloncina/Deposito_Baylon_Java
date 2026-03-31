
class Calculator {
    // Metodo per sommare due numeri interi
    public int add(int a, int b) {
        return a + b;
    }

    // metodo con 3 interi
    public int add(int a, int b, int c) {
        return a + b + c;
    }

    // metodo con 2 double
    public double add(double a, double b) {
        return a + b;
    }
}

public class ProvaOverloading {
    public static void main(String[] args) {
        Calculator clc = new Calculator();

        // in base al tipo e al numero di parametri che passo esegue il metodo
        // corrispondente
        System.out.println(clc.add(1, 2)); // metodo con 2 interi
        System.out.println(clc.add(1, 2, 3));// metodo con 3 interi
        System.out.println(clc.add(1.5, 2.2));// metodo con 2 double

    }

}
