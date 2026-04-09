package decorator;

public abstract class ComputerDecorator implements IComputer {

    protected IComputer computer;

    public ComputerDecorator(IComputer computer) {
        this.computer = computer;
    }

    @Override
    public String getDescrizione() {
        return "Computer";
    }

    @Override
    public double getPrezzo() {
        return 1500;
    }

}
