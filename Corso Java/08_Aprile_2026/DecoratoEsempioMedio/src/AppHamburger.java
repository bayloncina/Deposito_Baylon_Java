public class AppHamburger {
    public static void main(String[] args) throws Exception {
        // Base
        Hamburger ordine = new BaseBurger();

        // Aggiungo decoratori
        ordine = new FormaggioDecorator(ordine);
        ordine = new BaconDecorator(ordine);

        System.out.println(ordine.getDescrizione());
        System.out.println("Prezzo: " + ordine.getPrezzo() + " euro");
    }
}
