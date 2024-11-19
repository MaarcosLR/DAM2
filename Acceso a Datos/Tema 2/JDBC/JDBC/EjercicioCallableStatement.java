package JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.CallableStatement; // Importación correcta para CallableStatement

public class EjercicioCallableStatement {
    public static void main(String[] args) {
        // Configuración de conexión a la base de datos
        String url = "jdbc:mysql://localhost:3306/gestion_empleados";
        String usuario = "root";
        String contraseña = "1234";

        Connection con = null;
        CallableStatement cstmt = null;

        try {
            // 1. Establecer la conexión
            con = DriverManager.getConnection(url, usuario, contraseña);

            // 2. Preparar la llamada al procedimiento almacenado
            String procedimiento = "{CALL agregarEmpleado(?, ?, ?, ?)}";
            cstmt = con.prepareCall(procedimiento);

            // 3. Establecer los parámetros del procedimiento almacenado
            cstmt.setString(1, "Juan");
            cstmt.setString(2, "Pérez");
            cstmt.setDouble(3, 3500.50);
            cstmt.setString(4, "Ventas");

            // 4. Ejecutar el procedimiento almacenado
            cstmt.execute();

            System.out.println("Empleado agregado exitosamente.");

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // 5. Cerrar recursos
            try {
                if (cstmt != null) cstmt.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
