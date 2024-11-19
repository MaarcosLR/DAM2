package JDBC;

import java.sql.*;
import java.util.Scanner;

public class EjercicioDeletePreparedStatement {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/usuarios";
        String usuario = "root";
        String contraseña = "1234";

        try (Connection con = DriverManager.getConnection(url, usuario, contraseña)) {
            PreparedStatement pstmt = con.prepareStatement("DELETE FROM usuarios WHERE nombre = ? AND contraseña = ?");
            Scanner sc = new Scanner(System.in);
            System.out.println("Introduzca su usuario: ");
            String usuarioCust = sc.nextLine();
            System.out.println("Introduzca su contraseña: ");
            String contraseniaCust = sc.nextLine();

            pstmt.setString(1, usuarioCust);
            pstmt.setString(2, contraseniaCust);

            int rowsAffected = pstmt.executeUpdate();
            System.out.println("Filas afectadas: " + rowsAffected);

            if (rowsAffected > 0) {
                System.out.println("Usuario eliminado.");
            } else {
                System.out.println("Usuario o contraseña incorrectos o no encontrado.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
