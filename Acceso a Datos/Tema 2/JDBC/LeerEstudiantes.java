import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;

public class LeerEstudiantes {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/estudiantes";
        String usuario = "root";
        String contraseña = "1234";

        try (Connection con = DriverManager.getConnection(url, usuario, contraseña);
             Statement stmt = con.createStatement()) {

            String sql = "SELECT * FROM Estudiantes";
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                int edad = rs.getInt("edad");

                System.out.println("ID: " + id + ", Nombre: " + nombre + ", Edad: " + edad);
            }

        } catch (SQLException e) {
            System.out.println("Error en la lectura: " + e.getMessage());
        }
    }
}