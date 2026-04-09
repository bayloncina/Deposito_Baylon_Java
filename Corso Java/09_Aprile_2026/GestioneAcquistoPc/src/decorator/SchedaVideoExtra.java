package decorator;

public class SchedaVideoExtra extends ComputerDecorator{

    public SchedaVideoExtra(IComputer computer) {
        super(computer);
        //TODO Auto-generated constructor stub
    }

    @Override
    public String getDescrizione() {
        return super.getDescrizione() + " Scheda Video Extra ";
    }

    @Override
    public double getPrezzo() {
        return super.getPrezzo() +  500;
    }
    
}
