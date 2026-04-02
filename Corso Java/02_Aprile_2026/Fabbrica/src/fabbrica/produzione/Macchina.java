package fabbrica.produzione;

public class Macchina {


    private String nome;
    private boolean accesa;

    public Macchina(String nome) {
        this.nome = nome;
        this.accesa = false;
    }

    public String getNome() { return nome; }
    public boolean isAccesa() { return accesa; }

    public void accendi() {
        if (accesa) {
            System.out.println(nome + " è già accesa.");
        } else {
            accesa = true;
            System.out.println(nome + " accesa!");
        }
    }

    public void spegni() {
        if (!accesa) {
            System.out.println(nome + " è già spenta.");
        } else {
            accesa = false;
            System.out.println(nome + " spenta!");
        }
    }

    // crea il prodotto solo se la macchina è accesa
    public void creaProdotto(Prodotto p) {
    if (accesa) {
        System.out.println(nome + " ha prodotto:");
        p.mostraDettagli(); // in base al prodotto chiama Compressa o BustinaSolubile
    } else {
        System.out.println("Impossibile produrre — " + nome + " è spenta!");
    }}

    public void stampaStato() {
        System.out.println("Macchina: " + nome + " | Stato: " + (accesa ? "ACCESA" : "SPENTA"));
    }


    }
    
