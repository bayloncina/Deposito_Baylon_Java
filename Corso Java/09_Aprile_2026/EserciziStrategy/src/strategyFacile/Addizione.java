package strategyFacile;
//concrete strategy 

public class Addizione implements IOperazione{

    @Override
    public int esegui(int a, int b) {
       return a + b;
    }

    
}
