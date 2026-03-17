
public class ProvaOperatori {
    public static void main(String[] args) {
        int numero = 10;
        int numero2 = 5;

        numero += 1;
        System.out.println(numero);
        numero -= 5;
        System.out.println(numero);
        numero *= 2;
        System.out.println(numero);
        numero /= 2;
        System.out.println(numero);

        System.out.println(numero);

        // operatori di confronto

        System.out.println(numero == numero2);
        System.out.println(numero != numero2);
        System.out.println(numero > numero2);
        System.out.println(numero < numero2);
        
        /*
         * System.out.println(numero ++);
         * System.out.println(++numero);
         */

        // operatori di confronto
        // AND solo se entrambe sono vere
        System.out.println(numero < 10 && numero > 5);
        // OR se almeno una è vera
        System.out.println(numero < 10 || numero > 5);
        // NOT inverte il valore
        System.out.println(!(numero < 10));

    }

}
