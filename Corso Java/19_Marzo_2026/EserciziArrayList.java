import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class EserciziArrayList {
    public static void main(String[] args) {

        /*
         * scrivi un sistema Java che:
         * Crea un arrayList di stringhe per memorizzare nomi di studenti
         * l'utente puo aggiungere nomi finche non scrive fine
         * il programma deve stampare tutti i nomi in ordine alfabetico
         * il programma deve dire quanti studenti sono stati inseriti
         * il programma deve permettere di eliminare degli studenti indicati dall utente
         */

        /*
         * extra: Gestire l'inserimento in parallelo anche dell'età e del voto
         * int e float e inserire tutti e tre glia rraylist in un'altro arraylsit
         * che li contiene e rende il tutto modificabile
         */

        Scanner scObjStr = new Scanner(System.in);
        Scanner scObjInt = new Scanner(System.in);
        Scanner scObjFloat = new Scanner(System.in);

        ArrayList<String> nomi = new ArrayList<>();
        ArrayList<Integer> eta = new ArrayList<>();
        ArrayList<Float> voti = new ArrayList<>();


        // aggiunge nomi finché l'utente non scrive fine
        System.out.println("Inserisci il nome dello studente o scrivi fine per terminare: ");
        String nome = scObjStr.nextLine();

        // se nome è diverso da fine aggiungi nome all'arraylist finchè nome è diverso
        // da fine
        while (!nome.equalsIgnoreCase("fine")) {
            nomi.add(nome);

            // aggiunge l'età
            System.out.println("Inserisci l'età di " + nome + ": ");
            int etaStudente = scObjInt.nextInt();
            while (etaStudente <= 0) {
                System.out.println("Età non valida");
                etaStudente = scObjInt.nextInt();
            }
            eta.add(etaStudente);
            // aggiunge il voto
            System.out.println("Inserisci il voto di " + nome + " ");
            float votoStudente = scObjFloat.nextFloat();
            while (votoStudente < 0 || votoStudente > 30) {
                System.out.println("Voto non valido! Inserisci un valore tra 0 e 30 ");
                votoStudente = scObjFloat.nextFloat();
            }
            voti.add(votoStudente);

            System.out.println("Inserisci il nome dello studente o scrivi fine per terminare");
            nome = scObjStr.nextLine();
        }

        // controllo della lista
        if (nomi.isEmpty()) {
            System.out.println("Nessuno studente inserito!");
        } else {

            // uso collections per ordine alfabetico
            Collections.sort(nomi);

            // stampo tutti i nomi
            System.out.println("Studenti in ordine alfabetico:");
            for (int i = 0; i < nomi.size(); i++) {
                System.out.println(nomi.get(i));
            }

            // stampa quanti studenti sono stati inseriti
            System.out.println("Totale studenti: " + nomi.size());

            // elimina uno studente
            System.out.println("Inserisci il nome dello studente da eliminare: ");
            String daEliminare = scObjStr.nextLine();

            if (nomi.remove(daEliminare)) {
                System.out.println(daEliminare + " eliminato!");
                System.out.println("Lista aggiornata: " + nomi);
            } else {
                System.out.println("Studente non trovato!");
            }
        }
        scObjStr.close();
        scObjFloat.close();
        scObjInt.close();
    }

}
