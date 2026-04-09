package decorator;

public class SsdExtra extends ComputerDecorator{

    public SsdExtra(IComputer computer) {
        super(computer);
    }
@Override
    public String getDescrizione() {
        return super.getDescrizione() +  " SSD Extra ";
    }

    @Override
    public double getPrezzo() {
        return super.getPrezzo() +  500;
    }
    
}
