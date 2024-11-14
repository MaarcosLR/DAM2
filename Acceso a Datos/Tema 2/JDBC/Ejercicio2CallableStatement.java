import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.CallableStatement;
import java.sql.SQLException;

public class Ejercicio2CallableStatement {
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
            String procedimiento = "{CALL calcularSalarioTotal(?, ?)}";
            cstmt = con.prepareCall(procedimiento);

            // 3. Establecer los parámetros del procedimiento almacenado
            cstmt.setString(1, "Ventas"); // Parámetro de entrada: Departamento
            cstmt.registerOutParameter(2, java.sql.Types.DECIMAL); // Parámetro de salida: Salario total

            // 4. Ejecutar el procedimiento almacenado
            cstmt.execute();

            // 5. Obtener el valor del parámetro de salida
            double salarioTotal = cstmt.getDouble(2);
            System.out.println("El salario total del departamento Ventas es: " + salarioTotal);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // 6. Cerrar recursos
            try {
                if (cstmt != null) cstmt.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}