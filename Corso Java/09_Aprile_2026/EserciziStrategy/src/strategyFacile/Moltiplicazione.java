package strategyFacile;
//concrete strategy 
public class Moltiplicazione implements Operazione{

    @Override
    public int esegui(int a, int b) {
       return a * b;
    }
    
    
}
