package fabbrica.personale;

import java.util.Scanner;

import fabbrica.produzione.BustinaSolubile;
import fabbrica.produzione.Compressa;
import fabbrica.produzione.Macchina;

public class OperaioSpeciale extends Operaio {

    private Scanner scannerInt = new Scanner(System.in);
    private Scanner scannerStr = new Scanner(System.in);

    public OperaioSpeciale(String nome) {
        super(nome);
    }

    @Override
    public void lavora(Macchina m) {
        super.lavora(m);
    }

    // produce compresse o bustine a scelta
    @Override
    public void eseguiMansione(Macchina m) {
        lavora(m);
        System.out.println("\n--- Scegli prodotto da creare ---");
        System.out.println("1. Compressa");
        System.out.println("2. Bustina Solubile");
        System.out.print("Scelta: ");
        int scelta = scannerInt.nextInt();
        scannerInt.nextLine();

        switch (scelta) {
            case 1 -> {
                System.out.print("Nome: ");
                String nome = scannerStr.nextLine();
                System.out.print("Descrizione: ");
                String descrizione = scannerStr.nextLine();
                System.out.print("Rivestimento: ");
                String rivestimento = scannerStr.nextLine();
                m.creaProdotto(new Compressa(nome, descrizione, rivestimento));
            }
            case 2 -> {
                System.out.print("Nome: ");
                String nome = scannerStr.nextLine();
                System.out.print("Descrizione: ");
                String descrizione = scannerStr.nextLine();
                System.out.print("Gusto: ");
                String gusto = scannerStr.nextLine();
                System.out.print("Grammi: ");
                double grammi = scannerInt.nextDouble();
                scannerInt.nextLine();
                m.creaProdotto(new BustinaSolubile(nome, descrizione, gusto, grammi));
            }
            default -> System.out.println("Scelta non valida.");
        }
    }

    @Override
    public String toString() {
        return "Operaio Speciale " + super.toString();
    }
}