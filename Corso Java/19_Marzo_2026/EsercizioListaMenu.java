import java.util.ArrayList;
import java.util.Scanner;

public class EsercizioListaMenu {
    public static void main(String[] args) {
        /*
         * gestire lista nomi con menu interattivo
         * utilizzare un arrayList di stringhe per salvare i nomi inseriti
         * mostrare menu che si ripete fichè l'utente non esce
         * 
         */

        // inizializzo due scanner per i tipi di input diversi
        Scanner scObjInt = new Scanner(System.in);
        Scanner scObjStr = new Scanner(System.in);
        // istanza dell'arrayList
        ArrayList<String> nomi = new ArrayList<>();
        ArrayList<Integer> eta = new ArrayList<>();
        ArrayList<Float> voti = new ArrayList<>();
        ArrayList<Boolean> presenti = new ArrayList<>();
        // condizione per il do while
        boolean continua = true;

        do {
            System.out.println("MENU");
            System.out.println("Digita 1 per Aggiungere studente");
            System.out.println("Digita 2 per Visualizzare tutti studenti");
            System.out.println("Digita 3 per cercare uno Studente");
            System.out.println("Digita 4 per rimuovere uno Studente");
            System.out.println("Digita 5 per uscire");
            System.out.print("Scelta: ");
            int scelta = scObjInt.nextInt();
            // gestire la scelta con lo switch

            switch (scelta) {
                case 1:
                    System.out.println("Inserisci nome");
                    String nome = scObjStr.nextLine();
                    // inserisco nome nell'arrayList
                    nomi.add(nome);

                    System.out.println("Inserisci età");
                    int etaAnagrafica = scObjInt.nextInt();
                    eta.add(etaAnagrafica);

                    System.out.println("Inserisci voto");
                    float voto = scObjInt.nextFloat();
                    voti.add(voto);

                    System.out.println("Studente presente? Digita S per Si e N per No: ");
                    String presenza = scObjStr.nextLine();
                    if (presenza.equalsIgnoreCase("s")) {
                        presenti.add(true);
                    } else {
                        presenti.add(false);
                    }
                    System.out.println("Studente aggiunto!");

                    break;
                case 2:
                    // visualizzo tutti i nomi dell'arrayList
                    if (nomi.isEmpty()) {
                        System.out.println("Lista vuota!");
                    } else {
                        System.out.println("Lista studenti:");
                        for (int i = 0; i < nomi.size(); i++) {
                            System.out.println((i + 1) + ". " + nomi.get(i) +
                                    " Età: " + eta.get(i) +
                                    " Voto: " + voti.get(i) +
                                    " Presente: " + (presenti.get(i)));
                        }
                    }
                    break;
                case 3:
                    // cerco nome
                    System.out.println("Inserisci il nome da cercare: ");
                    String nomeCercato = scObjStr.nextLine();
                    if (nomi.contains(nomeCercato)) {
                        int indice = nomi.indexOf(nomeCercato);
                        System.out.println("Nome trovato!");
                        System.out.println("Età: " + eta.get(indice));
                        System.out.println("Voto: " + voti.get(indice));
                        System.out.println("Presente: " + (presenti.get(indice)));
                    } else {
                        System.out.println("Nome non trovato!");
                    }
                    break;
                case 4:
                    // elimino nome
                    System.out.println("Inserisci il nome da eliminare: ");
                    String nomeDaEliminare = scObjStr.nextLine();
                    int indiceElimina = nomi.indexOf(nomeDaEliminare);
                    // cerca l'indice dello studente nella lista dei nomi
                    // indexOf restituisce -1 se lo studente non esiste
                    if (indiceElimina != -1) {
                        nomi.remove(indiceElimina);
                        eta.remove(indiceElimina);
                        voti.remove(indiceElimina);
                        presenti.remove(indiceElimina);
                        System.out.println("Studente rimosso!");
                    } else {
                        System.out.println("Nome non trovato!");
                    }

                    break;
                case 5:
                    System.out.println("Arrivederci");
                    continua = false;

                    break;
                default:
                    System.out.println("Scelta non valida");
                    break;
            }
        } while (continua);

        scObjInt.close();
        scObjStr.close();
    }

}
