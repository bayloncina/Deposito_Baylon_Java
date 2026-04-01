abstract class Animal {
    public abstract void animalSound();

    public void sleep() {
    }
}

class Dog extends Animal {

    @Override
    public void animalSound() {

        throw new UnsupportedOperationException("Unimplemented method 'animalSound'");
    }

}

interface Animal2 {
    public void animalSond();

    public void run();
}

public class ProvaAstrazione {
    public static void main(String[] args) {

    }

}