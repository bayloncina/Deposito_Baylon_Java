package classi;

public class StudenteSuperiori extends Studente {

    private String indirizzoStudio; // es. "Scientifico", "Classico", "Informatico"

    public StudenteSuperiori(String nome, int eta, String classe, String indirizzoStudio) {
        super(nome, eta, classe);
        this.indirizzoStudio = indirizzoStudio;
    }

    public String getIndirizzoStudio() { return indirizzoStudio; }
    public void setIndirizzoStudio(String i) { this.indirizzoStudio = i; }

    @Override
    public void descriviRuolo() {
        System.out.println("Sono uno studente superiore della classe "
            + getClasseFrequentata() + ", indirizzo: " + indirizzoStudio);
    }

    @Override
    public String toString() {
        return "StudenteSuperiori: " + super.toString();
    }
}