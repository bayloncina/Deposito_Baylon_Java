public class Persona {

    // Prova Incapsulamento


    private String name;
    private int eta;



    public Persona(String nome){
        this.name = nome;
    }



    //metodo private
    private boolean verificaMaggiorenne(){
        return this.eta >=18;
    }

    //metodo pubblico che richiama il metodo privato
    public void stampaStatus(){
        if(verificaMaggiorenne()){
            System.out.println("è maggiorenne");
        }else{
            System.out.println("non è maggiorenne");
        }
    }
    //getter e setter
    public String getName() {return name;}
    public void setName(String newName) {this.name = newName;}
    

    public static void main(String[] args) {

        //Persona myObj = new Persona();
        //myObj.setName("Pippo"); //settiamo il valore di persona = Pippo
        //System.out.println(myObj.getName());

    }
}
