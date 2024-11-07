import java.sql.*;
import java.util.Scanner;

public class EjercicioInyeccionesStatment {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/usuarios";
        String usuario = "root";
        String contraseña = "1234";

        try (Connection con = DriverManager.getConnection(url, usuario, contraseña);
             Statement stmt = con.createStatement()) {

            Scanner sc = new Scanner(System.in);
            System.out.println("Introduzca su usuario: ");
            String usuarioCust = sc.nextLine();
            System.out.println("Introduzca su contraseña: ");
            String contraseniaCust = sc.nextLine();

            String sql = "SELECT nombre, contraseña FROM usuarios WHERE nombre = '" + usuarioCust + "'AND contraseña = '" + contraseniaCust + "'";
            ResultSet rs = stmt.executeQuery(sql);

            if (rs.next()) {
                System.out.println("Inicio de sesión exitoso.");
            } else {
                System.out.println("Usuario o contraseña incorrectos");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
