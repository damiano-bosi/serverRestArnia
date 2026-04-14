/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arniadigitale_bosi;

/**
 *
 * @author bosi.damiano
 */
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ArniaService {
    // Credenziali XAMPP di default
    private static final String URL = "jdbc:mysql://localhost:3306/apicultura";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    // Metodo per la GET
    public List<ArniaResponse> getTutteLeArnie() throws SQLException {
        List<ArniaResponse> listaArnie = new ArrayList<>();
        String query = "SELECT * FROM arnia";

        try {
            // FORZA JAVA A CARICARE IL DRIVER MYSQL
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("DRIVER MYSQL NON TROVATO! Assicurati di aver aggiunto il file .jar");
            e.printStackTrace();
            return listaArnie;
        }

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
        

            while (rs.next()) {
                ArniaResponse arnia = new ArniaResponse();
                arnia.setArn_id(rs.getInt("arn_id"));
                if (rs.getDate("arn_dataInst") != null) {
                    arnia.setArn_dataInst(rs.getDate("arn_dataInst").toString());
                }
                arnia.setArn_piena(rs.getBoolean("arn_piena"));
                arnia.setArn_macAddress(rs.getString("arn_macAddress"));
                arnia.setArn_api_id(rs.getInt("arn_api_id"));
                arnia.setMessaggio("OK");
                listaArnie.add(arnia);
            }
        }
        return listaArnie;
    }

    // Metodo per la POST
    public void inserisciArnia(ArniaRequest arnia) throws SQLException {
        String query = "INSERT INTO arnia (arn_dataInst, arn_piena, arn_macAddress, arn_api_id) VALUES (?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setDate(1, java.sql.Date.valueOf(arnia.getArn_dataInst()));
            pstmt.setBoolean(2, arnia.isArn_piena());
            pstmt.setString(3, arnia.getArn_macAddress());
            pstmt.setInt(4, arnia.getArn_api_id());
            
            pstmt.executeUpdate();
        }
    }
}
