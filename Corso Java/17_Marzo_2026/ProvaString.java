import java.util.Arrays;

public class ProvaString {
    public static void main(String[] args) {
        // String greeting = "Hello";
        String txt = "Hello World";
        System.out.println("La lunghezza della stringa è: " + txt.length());
        System.out.println(txt.toUpperCase());
        System.out.println(txt.toLowerCase());
        //
        System.out.println(txt.indexOf("World"));

        // concatenazione
        String firstName = "John";
        String lastName = "Doe";
        System.out.println(firstName + " " + lastName);
        // accetta solo stringhe
        System.out.println(firstName.concat(lastName));
        // usare backslash per trasformare i caratteri speciali in caratteri stringa
        System.out.println("We are the so-called \"Vikings\" from the north");
        System.out.println("Riga 1\nRiga 2"); // va a capo
        System.out.println("Nome:\tMiriam"); // allinea con tab
        System.out.println("Dice \"ciao\""); // stampa le virgolette
        System.out.print("Hello World\rJava");
        System.out.print("Hello\b");
        System.out.print("Pagina 1\fPagina 2");
        String str = "Hello World";
        //divide una stringa in più parti in base a un separatore, e restituisce un array di stringhe.
        String [] words = str.split("\\s" );
        System.out.print(Arrays.toString(words));
    

    }
}
