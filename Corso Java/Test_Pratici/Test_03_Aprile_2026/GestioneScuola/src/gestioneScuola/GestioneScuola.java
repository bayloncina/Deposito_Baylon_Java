package gestioneScuola;

import classi.*;
import java.util.ArrayList;
import java.util.Scanner;

public class GestioneScuola {

    // Liste private per ogni tipo di entità — incapsulamento
    private ArrayList<Studente> studenti = new ArrayList<>();
    private ArrayList<DocenteElementari> docentiElementari = new ArrayList<>();
    private ArrayList<DocenteMedie> docentiMedie = new ArrayList<>();
    private ArrayList<DocenteSuperiori> docentiSuperiori = new ArrayList<>();

    // Due Scanner separati: uno per stringhe, uno per interi
    private Scanner scStr = new Scanner(System.in);
    private Scanner scInt = new Scanner(System.in);

    // punto di accesso al programma
    public void startGestionaleScuola() {
        int scelta;

        do {
            System.out.println("\n===== MENU =====");
            System.out.println("1.  Crea Studente");
            System.out.println("2.  Crea Docente Elementari");
            System.out.println("3.  Crea Docente Medie");
            System.out.println("4.  Crea Docente Superiori");
            System.out.println("5.  Assegna studente a docente");
            System.out.println("6.  Assegna voto");
            System.out.println("7.  Stampa voti e statistiche studente");
            System.out.println("8.  Stampa ruoli (polimorfismo)");
            System.out.println("0.  Esci");

            scelta = scInt.nextInt(); // legge la scelta come intero

            // Ogni caso delega a un metodo privato dedicato
            switch (scelta) {
                case 1 -> creaStudente();
                case 2 -> creaDocenteElementari();
                case 3 -> creaDocenteMedie();
                case 4 -> creaDocenteSuperiori();
                case 5 -> assegnaStudente();
                case 6 -> assegnaVoto();
                case 7 -> stampaStatistiche();
                case 8 -> stampaRuoli();
            }

        } while (scelta != 0);

        scStr.close();
        scInt.close();
    }

    // Legge i dati da tastiera e aggiunge un nuovo Studente alla lista
    // chiede il tipo (Medie o Superiori) e istanzia la sottoclasse corretta
    private void creaStudente() {
        System.out.println("Tipo studente: 1 = Medie  2 = Superiori");
        int tipo = scInt.nextInt();

        System.out.print("Nome: ");
        String nome = scStr.nextLine();
        System.out.print("Età: ");
        int eta = scInt.nextInt();
        System.out.print("Classe: ");
        String classe = scStr.nextLine();

        switch (tipo) {
            case 1 -> {
                // StudenteMedie ha un attributo specifico: materia preferita
                System.out.print("Materia preferita: ");
                String mat = scStr.nextLine();
                studenti.add(new StudenteMedie(nome, eta, classe, mat));
            }
            case 2 -> {
                // StudenteSuperiori ha un attributo specifico: indirizzo di studio
                System.out.print("Indirizzo di studio: ");
                String ind = scStr.nextLine();
                studenti.add(new StudenteSuperiori(nome, eta, classe, ind));
            }
            default -> { System.out.println("Tipo non valido!"); return; }
        }
        System.out.println("Studente creato!");
    }

    // Legge i dati da tastiera e aggiunge un nuovo DocenteElementari alla lista
    private void creaDocenteElementari() {
        System.out.print("Nome: ");
        String nome = scStr.nextLine();
        System.out.print("Età: ");
        int eta = scInt.nextInt();
        System.out.print("Attività didattiche: ");
        String att = scStr.nextLine();
        docentiElementari.add(new DocenteElementari(nome, eta, att));
        System.out.println("Docente Elementari creato!");
    }

    // Legge i dati da tastiera e aggiunge un nuovo DocenteMedie alla lista
    private void creaDocenteMedie() {
        System.out.print("Nome: ");
        String nome = scStr.nextLine();
        System.out.print("Età: ");
        int eta = scInt.nextInt();
        System.out.print("Materia: ");
        String mat = scStr.nextLine();
        docentiMedie.add(new DocenteMedie(nome, eta, mat));
        System.out.println("Docente Medie creato!");
    }

    // Legge i dati da tastiera e aggiunge un nuovo DocenteSuperiori alla lista
    private void creaDocenteSuperiori() {
        System.out.print("Nome: ");
        String nome = scStr.nextLine();
        System.out.print("Età: ");
        int eta = scInt.nextInt();
        System.out.print("Materia specializzata: ");
        String mat = scStr.nextLine();
        System.out.print("Coordinatore di classe? (s/n): ");
        String coord = scStr.nextLine().trim().toUpperCase();
        boolean isCoord = coord.equals("S"); // true solo se risponde "s" o "S"
        docentiSuperiori.add(new DocenteSuperiori(nome, eta, mat, isCoord));
        System.out.println("Docente Superiori creato!");
    }

    /**
     * Permette di assegnare uno studente alla lista di un docente.
     * Solo il docente selezionato potrà poi assegnare voti a quello studente.
     */
    private void assegnaStudente() {
        if (studenti.isEmpty()) {
            System.out.println("Nessuno studente!");
            return;
        }

        System.out.println("Tipo docente: 1 = Elementari  2 = Medie  3 = Superiori");
        int tipo = scInt.nextInt();

        // Recupera il docente dalla lista corretta tramite metodo helper
        Docente docente = selezionaDocente(tipo);
        if (docente == null) return;

        System.out.println("Seleziona studente:");
        for (int i = 0; i < studenti.size(); i++)
            System.out.println(i + " - " + studenti.get(i).getNome());
        int idx = scInt.nextInt();

        docente.aggiungiStudente(studenti.get(idx));
        System.out.println("Studente assegnato!");
    }

    /**
     * Permette a un docente di assegnare un voto a uno studente.
     * Il metodo assegnaVoto() in Docente verifica che lo studente
     * sia nella lista del docente prima di procedere.
     */
    private void assegnaVoto() {
        if (studenti.isEmpty()) {
            System.out.println("Nessuno studente!");
            return;
        }

        System.out.println("Tipo docente: 1 = Elementari  2 = Medie  3 = Superiori");
        int tipo = scInt.nextInt();

        Docente docente = selezionaDocente(tipo);
        if (docente == null) return;

        System.out.println("Seleziona studente:");
        for (int i = 0; i < studenti.size(); i++)
            System.out.println(i + " - " + studenti.get(i).getNome());
        int si = scInt.nextInt();
        System.out.print("Voto: ");
        int voto = scInt.nextInt();

        docente.assegnaVoto(studenti.get(si), voto);
    }

    /**
     * Stampa voti, media, massimo e minimo di uno studente selezionato.
     * Usa i metodi dell'interfaccia Valutabile implementata da Studente.
     */
    private void stampaStatistiche() {
        if (studenti.isEmpty()) {
            System.out.println("Nessuno studente!");
            return;
        }

        System.out.println("Seleziona studente:");
        for (int i = 0; i < studenti.size(); i++)
            System.out.println(i + " - " + studenti.get(i).getNome());
        int idx = scInt.nextInt();

        Studente st = studenti.get(idx);
        st.stampaVoti(); // stampa voti + statistiche (calcolaMedia, votoMax, votoMin)
    }

    /**
     * Chiama descriviRuolo() su riferimenti di tipo Persona,
     * ma esegue l'override della sottoclasse corretta — polimorfismo.
     */
    private void stampaRuoli() {
        System.out.println("\n--- Studenti ---");
        for (Persona p : studenti)
            p.descriviRuolo();
        System.out.println("--- Docenti Elementari ---");
        for (Persona p : docentiElementari)
            p.descriviRuolo();
        System.out.println("--- Docenti Medie ---");
        for (Persona p : docentiMedie)
            p.descriviRuolo();
        System.out.println("--- Docenti Superiori ---");
        for (Persona p : docentiSuperiori)
            p.descriviRuolo();
    }

    // Metodo helper per la scelta del docente —
    // usa wildcard <? extends Docente> per gestire le tre liste con un unico riferimento
    private Docente selezionaDocente(int tipo) {
        ArrayList<? extends Docente> lista = switch (tipo) {
            case 1 -> docentiElementari;
            case 2 -> docentiMedie;
            case 3 -> docentiSuperiori;
            default -> null;
        };

        if (lista == null || lista.isEmpty()) {
            System.out.println("Nessun docente disponibile!");
            return null;
        }

        System.out.println("Seleziona docente:");
        for (int i = 0; i < lista.size(); i++)
            System.out.println(i + " - " + lista.get(i).getNome());
        int idx = scInt.nextInt();
        return lista.get(idx);
    }
}