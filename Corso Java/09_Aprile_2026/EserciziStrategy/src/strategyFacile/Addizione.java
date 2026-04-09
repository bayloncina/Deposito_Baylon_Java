package strategyFacile;
//concrete strategy 

public class Addizione implements Operazione{

    @Override
    public int esegui(int a, int b) {
       return a + b;
    }

    
}
