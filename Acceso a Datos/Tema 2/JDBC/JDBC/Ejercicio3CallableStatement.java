package JDBC;

import java.sql.*;

public class Ejercicio3CallableStatement {
    public static void main(String[] args) {
        // Configuración de conexión a la base de datos
        String url = "jdbc:mysql://localhost:3306/gestion_empleados_horas";
        String usuario = "root";
        String contraseña = "1234";

        Connection con = null;
        CallableStatement cstmt = null;
        ResultSet rs = null;

        try {
            // 1. Establecer la conexión
            con = DriverManager.getConnection(url, usuario, contraseña);

            // 2. Preparar la llamada al procedimiento almacenado
            String procedimiento = "{CALL saberHorasTrabajadas(?)}";
            cstmt = con.prepareCall(procedimiento);

            // 3. Establecer el parámetro de entrada
            cstmt.setString(1, "App2"); // Parámetro de entrada: Proyecto (App1)

            // 4. Ejecutar el procedimiento almacenado
            rs = cstmt.executeQuery();

            // 5. Mostrar los resultados obtenidos
            while (rs.next()) {
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                int horas = rs.getInt("horas");

                System.out.println("Empleado: " + nombre + " " + apellido + ", Horas trabajadas: " + horas);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // 6. Cerrar recursos
            try {
                if (rs != null) rs.close();
                if (cstmt != null) cstmt.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
