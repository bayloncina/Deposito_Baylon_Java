public class DisplayConsole implements IDisplay{

    private String name;

    public DisplayConsole(String name){
        this.name = name;
    }

    @Override
    public void aggiorna(float temperatura) {
        System.out.println(name + " la temperatura attuale è " + temperatura);
    }
    
}
