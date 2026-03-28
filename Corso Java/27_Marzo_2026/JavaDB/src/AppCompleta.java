
import java.sql.*;

public class AppCompleta {

    static String url = "jdbc:mysql://localhost:8889/";
    static String user = "root";
    static String password = "root";
    static String nomeDB = "dbJavaSqL";


    public static void createDB(String nome) {
        String query = "create database " + nome;
        try (Connection conn = DriverManager.getConnection(url, user, password); Statement stmt = conn.createStatement();) {

            stmt.executeUpdate(query);
        } catch ( SQLException e) {
            System.out.println("Errore");
        }
    }

    public static void createTable(String query) {
        try (Connection conn = DriverManager.getConnection(url+nomeDB, user, password); Statement stmt = conn.createStatement();) {

            stmt.executeUpdate(query);
        } catch (SQLException e) {
            System.out.println("Errore");
        }
    }

    public static void insert(String nome, int eta) {
        String query = "INSERT INTO PERSONE (NOME, ETA) VALUES (?,?)";
        try (Connection conn = DriverManager.getConnection(url+nomeDB, user, password); PreparedStatement pstmt = conn.prepareStatement(query);) {
            pstmt.setString(1, nome);
            pstmt.setInt(2, eta);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Errore");
        }
    }

    public static void read() {
        String query = "SELECT * FROM PERSONE";
        try (Connection conn = DriverManager.getConnection(url+nomeDB, user, password); PreparedStatement pstmt = conn.prepareStatement(query);) {
            ResultSet rs = pstmt.executeQuery();
          
            System.out.println("ID|ETA|NOME");
            

            while (rs.next()) {

                System.out.print(rs.getInt("ID"));
                System.out.print("|");
                System.out.print(rs.getInt("ETA"));
                System.out.print("|");
                System.out.print(rs.getString("NOME"));
                System.out.println();
            }
            System.out.println();
        } catch (SQLException e) {
            System.out.println("Errore");
        }
    }

    public static void update(int id, String nome, int eta) {
        String query = "UPDATE PERSONE SET NOME = ? , ETA = ? WHERE ID = ?";
        try (Connection conn = DriverManager.getConnection(url+nomeDB, user, password); PreparedStatement pstmt = conn.prepareStatement(query);) {
            pstmt.setString(1, nome);
            pstmt.setInt(2, eta);
            pstmt.setInt(3, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Errore");
        }
    }

    public static void delete(int id) {
        String query = "DELETE FROM PERSONE WHERE ID = ?";
        try (Connection conn = DriverManager.getConnection(url+nomeDB, user, password); PreparedStatement pstmt = conn.prepareStatement(query);) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Errore");
        }
    }

    public static void main(String[] args) {
        try {
            createDB(nomeDB);
            String query = "create table PERSONE(ID int AUTO_INCREMENT primary key, NOME varchar(50), ETA int)";
            createTable(query);
        } catch (Exception e) {
            System.out.println("DB presente");
        }
        

        insert("Tommaso", 49);
        insert("Giovanna", 18);
        insert("Teresa", 32);
        read();
        update(1, "Pino", 51);
        delete(2);
        read();

    }
}
