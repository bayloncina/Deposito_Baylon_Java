import java.util.ArrayList;

public class Hotel {
    private String nome;
    private ArrayList<Camera> camere = new ArrayList<>();

    public Hotel(String nome) {
        this.nome = nome;
    }

    public void aggiungiCamera(Camera camera) {
        camere.add(camera);
        System.out.println("Camera aggiunta: " + camera.getNumero());
    }

    public static int contaSuite(ArrayList<Camera> lista) {
        int count = 0;
        for (Camera c : lista) {
            if (c instanceof Suite) {
                count++;
            }
        }
        return count;
    }

    public ArrayList<Camera> getCamere() {
        return camere;
    }

    public String getNome() {
        return nome;
    }
}