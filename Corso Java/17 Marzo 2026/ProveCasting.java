public class ProveCasting {
   public static void main(String[] args) {

      // int myInt = 9;
      // double myDouble = myInt; //casting automatico (coercizione)

      // System.out.println(myDouble);
      // System.out.println(myInt);

      double myDouble = 9.78;
      int myInt = (int) myDouble; // casting manuale
      /* String miaParola = (str) myDouble; -----> erroreeeee!!!!! */

      System.out.println(myDouble);
      System.out.println(myInt);
   }

}
