public class BaseBurger implements Hamburger {

    @Override
    public String getDescrizione() {
        return "Hamburger base";
    }

    @Override
    public double getPrezzo() {
        return 3.0;
    }
}