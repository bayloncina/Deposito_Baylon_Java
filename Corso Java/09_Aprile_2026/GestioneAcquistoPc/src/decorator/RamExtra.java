package decorator;

public class RamExtra extends ComputerDecorator {

    public RamExtra(IComputer computer) {
        super(computer);
        //TODO Auto-generated constructor stub
    }

    @Override
    public String getDescrizione() {
        return super.getDescrizione() + " RAM extra";
    }

    @Override
    public double getPrezzo() {
        return super.getPrezzo() + 1000;
    }
    
}
