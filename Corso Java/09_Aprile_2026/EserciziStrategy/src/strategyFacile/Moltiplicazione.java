package strategyFacile;
//concrete strategy 
public class Moltiplicazione implements IOperazione{

    @Override
    public int esegui(int a, int b) {
       return a * b;
    }
    
    
}
