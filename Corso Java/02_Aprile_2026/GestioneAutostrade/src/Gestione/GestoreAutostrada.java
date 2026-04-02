package Gestione;

import Classi.*;

import java.util.ArrayList;
import java.util.Scanner;

public class GestoreAutostrada {
    private ArrayList<Veicolo> veicoli = new ArrayList<>();
    private Scanner scannerInt = new Scanner(System.in);
    private Scanner scannerStr = new Scanner(System.in);

    // ── metodo start: punto di ingresso dell'applicazione ──
    public void start() {
        boolean running = true;
        while (running) {
            System.out.println("\n=== SISTEMA AUTOSTRADALE ===");
            System.out.println("1. Aggiungi veicolo");
            System.out.println("2. Visualizza veicoli");
            System.out.println("3. Calcola pedaggi");
            System.out.println("4. Esci");
            System.out.print("Scelta: ");
            int scelta = scannerInt.nextInt();
            scannerInt.nextLine();

            switch (scelta) {
                case 1 -> aggiungiVeicolo();
                case 2 -> mostraVeicoli();
                case 3 -> calcolaTuttiPedaggi();
                case 4 -> running = false;
                default -> System.out.println("Scelta non valida.");
            }
        }
        System.out.println("Arrivederci!");
        scannerInt.close();
    }

    // ── aggiunge un veicolo alla lista ──
    private void aggiungiVeicolo() {
        System.out.println("Tipo veicolo: 1. Auto  2. Camion  3. Moto");
        int tipo = scannerInt.nextInt();
        scannerInt.nextLine();

        System.out.print("Targa: ");
        String targa = scannerStr.nextLine();
        System.out.print("Velocità (km/h): ");
        double velocita = scannerInt.nextDouble();
        scannerInt.nextLine();
        System.out.print("Numero assi: ");
        int assi = scannerInt.nextInt();
        scannerInt.nextLine();

        switch (tipo) {
            case 1 -> {
                System.out.print("Cilindrata (cc): ");
                double cilindrata = scannerInt.nextDouble();
                scannerInt.nextLine();
                veicoli.add(new Auto(targa, velocita, assi, cilindrata));
                System.out.println("Auto aggiunta!");
            }
            case 2 -> {
                System.out.print("Peso carico (kg): ");
                double peso = scannerInt.nextDouble();
                scannerInt.nextLine();
                veicoli.add(new Camion(targa, velocita, assi, peso));
                System.out.println("Camion aggiunto!");
            }
            case 3 -> {
                System.out.print("Tipo moto (es. sportiva, custom): ");
                String tipoMoto = scannerStr.nextLine();
                veicoli.add(new Moto(targa, velocita, assi, tipoMoto));
                System.out.println("Moto aggiunta!");
            }
            default -> System.out.println("Tipo non valido.");
        }
    }

    // ── calcola e stampa il pedaggio per ogni veicolo ──
    private void calcolaTuttiPedaggi() {
        if (veicoli.isEmpty()) {
            System.out.println("Nessun veicolo presente.");
            return;
        }
        System.out.println("\n======= PEDAGGI =======");
        for (Veicolo v : veicoli) {
            System.out.println(v.getTarga() + " Pedaggio: " +
                    String.format("%.2f", v.calcolaPedaggio()) + "euro");
        }
    }

    // ── mostra tutti i veicoli con indice ──
    private void mostraVeicoli() {
        if (veicoli.isEmpty()) {
            System.out.println("Nessun veicolo presente.");
            return;
        }
        System.out.println("\n======= VEICOLI =======");
        for (int i = 0; i < veicoli.size(); i++) {
            System.out.println((i + 1) + ". " + veicoli.get(i));
        }
    }
}