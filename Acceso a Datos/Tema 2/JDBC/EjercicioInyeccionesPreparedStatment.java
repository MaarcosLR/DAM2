import java.sql.*;
import java.util.Scanner;

public class EjercicioInyeccionesPreparedStatment {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/usuarios";
        String usuario = "root";
        String contraseña = "1234";

        try (Connection con = DriverManager.getConnection(url, usuario, contraseña);
             PreparedStatement pstmt = con.prepareStatement("SELECT nombre, contraseña FROM usuarios WHERE nombre = ? AND contraseña = ?")) {

            Scanner sc = new Scanner(System.in);
            System.out.println("Introduzca su usuario: ");
            String usuarioCust = sc.nextLine();
            System.out.println("Introduzca su contraseña: ");
            String contraseniaCust = sc.nextLine();

            pstmt.setString(1, usuarioCust);
            pstmt.setString(2, contraseniaCust);

            ResultSet rs = pstmt.executeQuery();

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
