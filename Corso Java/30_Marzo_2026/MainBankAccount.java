import java.util.ArrayList;
import java.util.Scanner;

class BankAccount {

    String accountHolderName;
    double balance;
    String username;
    String password;

    BankAccount(String accountHolderName, double balance, String username, String password) {
        this.accountHolderName = accountHolderName;
        this.balance = balance;
        this.username = username;
        this.password = password;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposito di " + amount + "euro effettuato.");
        } else {
            System.out.println("Importo non valido.");
        }
    }

    public void withdraw(double amount) {
        if (amount > balance) {
            System.out.println("Saldo insufficiente. Prelievo non consentito.");
        } else if (amount <= 0) {
            System.out.println("Importo non valido.");
        } else {
            balance -= amount;
            System.out.println("Prelievo di " + amount + "euro effettuato.");
        }
    }

    public String toString() {
        return "Nome: " + accountHolderName + " | Saldo: " + balance + "euro";
    }
}

public class MainBankAccount {
    public static void main(String[] args) {

        Scanner scannerStr = new Scanner(System.in);
        Scanner scannerInt = new Scanner(System.in);

        ArrayList<BankAccount> accountsList = new ArrayList<>();
        accountsList.add(new BankAccount("Miriam",   1000.0, "miriam",   "pass1"));
        accountsList.add(new BankAccount("Giorgia",  2500.0, "giorgia",  "pass2"));
        accountsList.add(new BankAccount("Giovanni",  500.0, "giovanni", "pass3"));

        login(accountsList, scannerStr, scannerInt);
    }

    private static void login(ArrayList<BankAccount> accountsList, Scanner scannerStr, Scanner scannerInt) {

        //inizializzo l'oggetto a null
        BankAccount accountScelto = null;

        while (accountScelto == null) {
            System.out.print("Username: ");
            String username = scannerStr.next();

            System.out.print("Password: ");
            String password = scannerStr.next();

            // per ogni account in accountList controllo username e password finchè non trovo la corrispondenza 
            for (BankAccount acc : accountsList) {
                if (acc.username.equals(username) && acc.password.equals(password)) {
                    accountScelto = acc;
                    break;
                }
            }

            if (accountScelto == null) {
                System.out.println("Credenziali errate. Riprova.");
            }
        }

        System.out.println("Benvenuto, " + accountScelto.accountHolderName + "!");
        //chiamo il metodo menu per effettuare le varie azioni sul conto
        menu(accountScelto, scannerStr, scannerInt);
    }

    private static void menu(BankAccount account, Scanner scannerStr, Scanner scannerInt) {
        int scelta = 0;

        while (scelta != 4) {
            System.out.println("\nMENU");
            System.out.println("1. Deposito");
            System.out.println("2. Prelievo");
            System.out.println("3. Visualizza saldo");
            System.out.println("4. Esci");
            System.out.print("Scegli un'operazione: ");

            scelta = scannerInt.nextInt(); 

            switch (scelta) {
                case 1:
                    System.out.print("Importo da depositare: ");
                    account.deposit(scannerInt.nextDouble()); 
                    break;

                case 2:
                    System.out.print("Importo da prelevare: ");
                    account.withdraw(scannerInt.nextDouble()); 
                    break;

                case 3:
                    System.out.println(account.toString());
                    break;

                case 4:
                    System.out.println("Arrivederci, " + account.accountHolderName + "!");
                    break;

                default:
                    System.out.println("Opzione non valida. Riprova.");
            }
        }
    }
}