import facadeFacile.GestioneLuciFacade;
import facadeMedio.ComputerFacade;

public class App {
    public static void main(String[] args) throws Exception {

        System.out.println("--------------- esercizio facile -----------------");
        GestioneLuciFacade g = new GestioneLuciFacade();
        g.accendiLuci();
        System.out.println("-------------------------------");



        System.out.println("--------------- esercizio medio -----------------");
        ComputerFacade cf = new ComputerFacade();
        cf.accendiComputer();
        System.out.println("-------------------------------");
    }
}
