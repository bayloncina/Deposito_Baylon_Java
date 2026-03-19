public class EsempioArray {
    public static void main(String[] args) {
        int[] numeri = { 10, 20, 30, 30, 50 };
        System.out.println(numeri[0]);

        for (int i = 0; i < numeri.length; i++) {
            numeri[i] = i + 1;
        }

        System.out.println("Elementi dell'array");
        for (int i = 0; i < numeri.length; i++) {
            System.out.println(numeri[i] + " ");
        }
    }

    //Array 2D
    int [][] matrice = new int[3][3];
//inizializzazione diretta
    int [] [] matricepredefinita = {
        {1,2,3},
        {4,5,6},
        {7,8,9}
    };

}
