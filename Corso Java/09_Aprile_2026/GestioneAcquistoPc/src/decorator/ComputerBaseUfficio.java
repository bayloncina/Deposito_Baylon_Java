package decorator;

public class ComputerBaseUfficio implements IComputer {

    @Override
    public String getDescrizione() {
        return "Computer base ufficio";
    }

    @Override
    public double getPrezzo() {
        return 1000;
    }


}
