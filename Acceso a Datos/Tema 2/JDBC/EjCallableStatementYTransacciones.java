import java.sql.*;

public class EjCallableStatementYTransacciones {
    public static void main(String[] args) {
        // Configuración de conexión a la base de datos
        String url = "jdbc:mysql://localhost:3306/operaciones"; // Cambiar según tus datos
        String usuario = "root"; // Cambiar según tus credenciales
        String contraseña = "1234"; // Cambiar según tus credenciales

        Connection con = null;

        // Datos del nuevo empleado
        String nombreEmpleado = "Marcelo Pérez";
        int departamentoIdStr = 1; // Cambiar a un valor válido para pruebas
        double salario = 3000.50;

        // Validaciones
        if (salario < 0) {
            System.err.println("Error: El salario no puede ser negativo.");
            return;
        }

        if (esNumerico(nombreEmpleado)) {
            System.err.println("Error: El nombre no puede ser un número.");
            return;
        }

        if (!esNumeroEntero(String.valueOf(departamentoIdStr))) {
            System.err.println("Error: El ID del departamento no puede contener letras.");
            return;
        }

        // Convertir departamentoIdStr a entero después de la validación
        int departamentoId = Integer.parseInt(String.valueOf(departamentoIdStr));

        try {
            // Establecer conexión con la base de datos
            con = DriverManager.getConnection(url, usuario, contraseña);

            // Preparar la llamada al procedimiento almacenado
            String sql = "{CALL agregarEmpleado(?, ?, ?)}";
            CallableStatement cstmt = con.prepareCall(sql);

            // Configurar los parámetros del procedimiento
            cstmt.setString(1, nombreEmpleado);
            cstmt.setInt(2, departamentoId);
            cstmt.setDouble(3, salario);

            // Ejecutar el procedimiento almacenado
            cstmt.execute();

            System.out.println("Empleado agregado exitosamente.");
            System.out.println("Nombre: " + nombreEmpleado);
            System.out.println("Departamento ID: " + departamentoId);
            System.out.println("Salario: " + salario);

        } catch (SQLException e) {
            System.err.println("Error al ejecutar las operaciones. No se realizaron cambios en la base de datos.");
            e.printStackTrace();
        } finally {
            // Cerrar la conexión
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException closeEx) {
                    System.err.println("Error al cerrar la conexión.");
                    closeEx.printStackTrace();
                }
            }
        }
    }

    // Método para verificar si un string es numérico
    private static boolean esNumerico(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    // Método para verificar si un string es un número entero válido
    private static boolean esNumeroEntero(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
