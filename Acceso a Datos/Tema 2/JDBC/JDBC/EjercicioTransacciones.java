package JDBC;

import java.sql.*;

public class EjercicioTransacciones {
    public static void main(String[] args) throws SQLException {
        // Configuración de conexión a la base de datos
        String url = "jdbc:mysql://localhost:3306/transacciones";
        String usuario = "root";
        String contraseña = "1234";

        Connection con = null;

        try {
            con = DriverManager.getConnection(url, usuario, contraseña);
            con.setAutoCommit(false); // Desactiva el auto-commit para iniciar la transacción

            // Actualización de saldos
            Statement stmt = con.createStatement();
            stmt.executeUpdate("UPDATE cuentas SET saldo = saldo - 500 WHERE cuenta_id = 1");  // Debita de la cuenta 1
            stmt.executeUpdate("UPDATE cuentas SET saldo = saldo + 500 WHERE cuenta_id = 2");  // Acredita a la cuenta 2

            // Consultar los saldos después de las operaciones
            PreparedStatement pstmt1 = con.prepareStatement("SELECT saldo FROM cuentas WHERE cuenta_id = 1");
            PreparedStatement pstmt2 = con.prepareStatement("SELECT saldo FROM cuentas WHERE cuenta_id = 2");
            PreparedStatement pstmt11 = con.prepareStatement("SELECT titular FROM cuentas WHERE cuenta_id = 1");
            PreparedStatement pstmt22 = con.prepareStatement("SELECT titular FROM cuentas WHERE cuenta_id = 2");

            ResultSet rs1 = pstmt1.executeQuery();
            ResultSet rs2 = pstmt2.executeQuery();
            ResultSet rs11 = pstmt11.executeQuery();
            ResultSet rs22 = pstmt22.executeQuery();

            String titular1 = "";
            String titular2 = "";

            double saldo1 = 0;
            double saldo2 = 0;

            if (rs1.next()) {
                saldo1 = rs1.getDouble("saldo");
            }

            if (rs2.next()) {
                saldo2 = rs2.getDouble("saldo");
            }
            if (rs11.next()) {
                titular1 = rs11.getString("titular");
            }

            if (rs22.next()) {
                titular2 = rs22.getString("titular");
            }

            // Validar si algún saldo es negativo
            if (saldo1 < 0 || saldo2 < 0) {
                System.err.println("El saldo es negativo, revirtiendo transacción...");
                con.rollback();
                return;
            }

            // Confirmar la transacción si todo está bien
            con.commit();
            System.out.println("Transferencia realizada con éxito.");
            System.out.println("Saldo cuenta 1: " + saldo1 + " Titular: " + titular1);
            System.out.println("Saldo cuenta 2: " + saldo2 + " Titular: " + titular2);

        } catch (SQLException e) {
            if (con != null) {
                con.rollback(); // Deshace ambas operaciones si alguna falla
            }
            System.err.println("Error en la transferencia, se ha revertido la operación.");
            e.printStackTrace();
        } finally {
            if (con != null) {
                con.setAutoCommit(true); // Vuelve a activar el auto-commit
                con.close(); // Cierra la conexión
            }
        }
    }
}
