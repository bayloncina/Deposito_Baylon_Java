
import java.sql.*;

public class App3 {

    public static void main(String[] args) throws Exception {
        String host = "localhost";
        String port = "3306";
        String url = "jdbc:mysql://" + host + ":" + port + "/sakila";
        String user = "root";
        String psw = "";

        String query = "select * from actor limit ?";

        int nRow = 5;

        try (Connection conn = DriverManager.getConnection(url, user, psw); 
                 PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, nRow);
            ResultSet result = pstmt.executeQuery();

            ResultSetMetaData meta = result.getMetaData();
            int numColumns = meta.getColumnCount();

            while (result.next()) {
                for (int i = 1; i < numColumns; i++) {
                    String colName = meta.getColumnName(i);
                    Object val = result.getObject(i);
                    System.out.print(colName + ":" + val);
                    if (i < numColumns) {
                        System.out.print("|");
                    }
                }
                System.out.println();
            }
          

        } catch (SQLException e) {
            e.printStackTrace();

        }

    }
}
