package facadeFacile;

public class GestioneLuciFacade {

    private LuceCamera luceCamere;
    private LuceCucina luceCucina;

    public GestioneLuciFacade() {
        luceCamere = new LuceCamera();
        luceCucina = new LuceCucina();
    }

    public void accendiLuci() {
        System.out.println("Preparazione Accensione luci...");
        luceCamere.accendi();
        luceCucina.accendi();
    }

}
