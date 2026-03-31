class Vehicle{

    protected String brand = "Ford";

    public void honk(){
        System.out.println("Tuuut, Tuuut!!!!");
    }
}

class Car extends Vehicle{

    public String modelName = "Fiat";
    
}


class ProvaEreditarieta extends Vehicle{
    public static void main(String[] args) {
        

        Car myCar = new Car();
        myCar.honk();
        System.out.println(myCar.brand + " " + myCar.modelName);


    }

}