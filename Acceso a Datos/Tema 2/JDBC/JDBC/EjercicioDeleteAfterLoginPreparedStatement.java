package JDBC;

import java.sql.*;
import java.util.Scanner;

public class EjercicioDeleteAfterLoginPreparedStatement {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/usuarios";
        String usuario = "root";
        String contraseña = "1234";

        try (Connection con = DriverManager.getConnection(url, usuario, contraseña)) {

            PreparedStatement pstmt = con.prepareStatement("SELECT nombre, contraseña FROM usuarios WHERE nombre = ? AND contraseña = ?");
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

                PreparedStatement pstmt2 = con.prepareStatement("DELETE FROM usuarios WHERE nombre = ? AND contraseña = ?");
                System.out.println("¿Desea eliminar algún usuario?: ");
                String reply = sc.nextLine();
                if (reply.toLowerCase().equals("si")) {
                    System.out.println("Introduzca el usuario: ");
                    String usuarioCust2 = sc.nextLine();
                    System.out.println("Introduzca la contraseña: ");
                    String contraseniaCust2 = sc.nextLine();
                    pstmt2.setString(1, usuarioCust2);
                    pstmt2.setString(2, contraseniaCust2);
                    int filasAfectadas = pstmt2.executeUpdate();
                    System.out.println("Filas afectadas: " + filasAfectadas);
                    if (filasAfectadas > 0) {
                        System.out.println("Usuario eliminado.");
                    } else {
                        System.out.println("Usuario o contraseña incorrectos o no encontrado.");
                    }
                } else {
                    System.out.println("De acuerdo, Adiós");
                }

            } else {
                System.out.println("Usuario o contraseña incorrectos");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
