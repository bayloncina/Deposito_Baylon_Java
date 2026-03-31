public class Camera {
    private int numero;
    private float prezzo;

    Camera(int numero, float prezzo) {
        this.numero = numero;
        this.prezzo = prezzo;
    }

    public int getNumero() {
        return numero;
    }

    public float getPrezzo() {
        return prezzo;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public void setPrezzo(float prezzo) {
        this.prezzo = prezzo;
    }

    public void dettagli() {
        System.out.println("Camera nuemro: " + numero + " al prezzo di: " + prezzo);
    };

    public void dettagli(boolean conPrezzo) {
        if (conPrezzo) {
            System.out.println("Camera nuemro: " + numero + " al prezzo di: " + prezzo);
        } else {
            System.out.println("Camera nuemro: " + numero);
        }

    }
}