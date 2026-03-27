import java.sql.*;

public class GestinaleVoti {
    public static void main(String[] args) {

        String host = "localhost";
        String port = "3306";
        String url = "jdbc:mysql://" + host + ":" + port;
        String user = "root";
        String psw = "";

        try (Connection conn = DriverManager.getConnection(url, user, psw);
                Statement stmt = conn.createStatement()) {

            stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS gestionale_scolastico");
            stmt.executeUpdate("USE gestionale_scolastico");
            stmt.executeUpdate(
                    "CREATE TABLE IF NOT EXISTS Classe (" +
                            "  id_classe INT PRIMARY KEY AUTO_INCREMENT," +
                            "  nome VARCHAR(50) NOT NULL," +
                            "  anno_scolastico VARCHAR(9) NOT NULL," +
                            "  sezione CHAR(1) NOT NULL," +
                            "  capienza INT NOT NULL" +
                            ")");
            stmt.executeUpdate(
                    "CREATE TABLE IF NOT EXISTS Studente (" +
                            "  id_studente INT PRIMARY KEY AUTO_INCREMENT," +
                            "  nome VARCHAR(50) NOT NULL," +
                            "  cognome VARCHAR(50) NOT NULL," +
                            "  data_di_nascita DATE NOT NULL," +
                            "  codice_fiscale  CHAR(16) NOT NULL UNIQUE," +
                            "  id_classe INT NOT NULL," +
                            "  FOREIGN KEY (id_classe) REFERENCES Classe(id_classe)" +
                            "  ON DELETE RESTRICT ON UPDATE CASCADE" +
                            ")");
            stmt.executeUpdate(
                    "CREATE TABLE IF NOT EXISTS Voto (" +
                            "  id_voto INT PRIMARY KEY AUTO_INCREMENT," +
                            "  materia VARCHAR(100) NOT NULL," +
                            "  data DATE NOT NULL," +
                            "  valore FLOAT NOT NULL," +
                            "  id_studente INT NOT NULL," +
                            "  FOREIGN KEY (id_studente) REFERENCES Studente(id_studente)" +
                            "  ON DELETE CASCADE ON UPDATE CASCADE" +
                            ")");

        } catch (SQLException e) {
        System.err.println("Errore: " + e.getMessage());
        e.printStackTrace();
    }
}

}


