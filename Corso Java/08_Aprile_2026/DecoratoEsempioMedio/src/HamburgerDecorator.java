abstract class HamburgerDecorator implements IHamburger {
    protected IHamburger hamburger;

    public HamburgerDecorator(IHamburger hamburger) {
        this.hamburger = hamburger;
    }
}