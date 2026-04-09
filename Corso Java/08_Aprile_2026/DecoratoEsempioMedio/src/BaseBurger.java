public class BaseBurger implements IHamburger {

    @Override
    public String getDescrizione() {
        return "Hamburger base";
    }

    @Override
    public double getPrezzo() {
        return 3.0;
    }
}