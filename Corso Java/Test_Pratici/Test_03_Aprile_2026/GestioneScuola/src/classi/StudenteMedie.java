package classi;

public class StudenteMedie extends Studente {

    private String materiaPreferia;

    public StudenteMedie(String nome, int eta, String classe, String materiaPreferia) {
        super(nome, eta, classe);
        this.materiaPreferia = materiaPreferia;
    }

    public String getMateriaPreferia() {
        return materiaPreferia;
    }

    public void setMateriaPreferia(String m) {
        this.materiaPreferia = m;
    }

    @Override
    public void descriviRuolo() {
        System.out.println("Sono uno studente delle medie della classe "
                + getClasseFrequentata() + ", materia preferita: " + materiaPreferia);
    }

    @Override
    public String toString() {
        return "StudenteMedie: " + super.toString();
    }
}