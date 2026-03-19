import java.lang.Math;

public class ProvaMath {
    public static void main(String[] args) {
        int x = 10;
        int y = 20;
        //calcola il max tra numeri
        System.out.println(Math.max(x, y));
        System.out.println(Math.min(x, y));
        System.out.println(Math.sqrt(y));
        //abs valore assoluto del numero
        System.out.println(Math.abs(-4.7));
        //random restituisce un numero casuale compreso 
        System.out.println(Math.random()*101);
        int randomNum = (int)(Math.random()*101);
        System.out.println(randomNum);
    }
}
