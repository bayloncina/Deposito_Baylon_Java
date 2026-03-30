import java.util.ArrayList;

class Auto {
    String marca;
    int anno;

    Auto(String marca, int anno) {
        this.marca = marca;
        this.anno = anno;
        
    }
    public String toString(){
        return "Nome: " + marca + " Anno: " + anno;
    }
}
public class ProvaOggettiInArrayList {
    public static void main(String[] args) {


        //Creo ArrayList di auto
        //tipizzare arrayList
        ArrayList<Auto> autoList = new ArrayList<>();

        //creare oggetti direttamente nell'arraylist
        autoList.add(new Auto("Tesla", 2023));
        autoList.add(new Auto("Fiat", 2025));

        for (Auto auto : autoList) {
            System.out.println(auto.marca + " - " + auto.anno);


        }
    }
}
