package practica1;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;
import java.sql.Connection;
import java.sql.DriverManager;
import javax.print.DocFlavor.URL;
public class InformeEmpleados {
    public static void main(String[] args) {
        String jdbcUrl =
                "jdbc:mysql://localhost:3306/practica_informes";
        String username = "root";
        String password = "1234";
        try {
// Conexión a la base de datos
            System.out.println("Conectando a la base de datos...");
            Connection connection =
                    DriverManager.getConnection(jdbcUrl, username, password);
            System.out.println("Conexión realizada con éxito.");
                    java.net.URL resourceUrl =
                            InformeEmpleados.class.getClassLoader().getResource("empleado s_report.jasper");
            if (resourceUrl == null) {
                throw new RuntimeException("No se encontró el archivo empleados_report.jasper en el classpath.");
            }
            String reportPath = resourceUrl.getPath();
            System.out.println("Ruta del informe: " + reportPath);
            // Cargar el informe desde el classpath
            System.out.println("Cargando el informe...");
            System.out.println("Ruta del informe: " + reportPath);
            if (reportPath == null) {
                throw new RuntimeException("El archivo empleados_report.jasper no se encontró en el classpath.");
            }
            // Llenar el informe con datos
            JasperPrint jasperPrint = JasperFillManager.fillReport(reportPath, null, connection);
            // Mostrar el informe
            JasperViewer.viewReport(jasperPrint, false);
            // Exportar a PDF
            JasperExportManager.exportReportToPdfFile(jasperPrint, "empleados_informe.pdf");
            System.out.println("Informe exportado como empleados_informe.pdf");
                    connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

