public class App {
    public static void main(String[] args) throws Exception {
        Messaggio base = new MessaggioBase("ciao");
        Messaggio dec = new DecoratoreMaiuscolo(base);
        System.out.println(dec.getContenuto());
    }
}
