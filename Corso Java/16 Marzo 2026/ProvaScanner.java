import java.util.Scanner;

public class ProvaScanner{
    public static void main(String[] args) {
    Scanner myObjString = new Scanner(System.in);
    Scanner myObjNum = new Scanner(System.in);
    System.out.println("Enter username");

    String userName = myObjString.nextLine();
    System.out.println("Username is: " + userName);
    System.out.println("Enter username");

    /* String userName = myObjString.nextLine(); */
}
}