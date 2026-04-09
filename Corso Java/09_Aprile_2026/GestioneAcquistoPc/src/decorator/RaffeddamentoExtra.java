package decorator;

public class RaffeddamentoExtra extends ComputerDecorator {

    public RaffeddamentoExtra(IComputer computer) {
        super(computer);
    }

    @Override
    public String getDescrizione() {
        return super.getDescrizione() + " Raffreddamento Extra ";
    }

    @Override
    public double getPrezzo() {
        return super.getPrezzo() + 200;
    }

}
