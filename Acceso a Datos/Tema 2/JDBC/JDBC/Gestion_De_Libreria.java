package JDBC;

import java.sql.*;
import java.util.Scanner;

// Comprar libros nuevos: Agregar nuevos libros al inventario.
// Vender un libro: Disminuir el stock del libro vendido.
// En caso de que no haya suficientes ejemplares, se debe lanzar una excepción y la venta no debe realizarse.
// Consultar libros disponibles: Mostrar el inventario de libros, con la opción de consultar por género, autor o título.
// Eliminar libros del inventario: Eliminar libros del inventario si ya no se van a vender.
// Registrar devoluciones: Permitir que los clientes devuelvan un libro.
// Llamar a un procedimiento almacenado con CallableStatement que calcule el total de ventas por cada género literario.
// Usar transacciones para asegurarse de que una venta o una devolución se registre correctamente: si no se puede completar
// alguna parte del proceso, la operación entera debe revertirse.

public class Gestion_De_Libreria {
    private static final String url = "jdbc:mysql://localhost:3306/libreria";
    private static final String usuario = "root";
    private static final String contraseña = "1234";
    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        int opcion;
            do {
                // Mostrar menú de opciones
                System.out.println("\nMenú de Inventario de Libros:");
                System.out.println("1. Comprar libro");
                System.out.println("2. Vender libro");
                System.out.println("3. Consultar libros disponibles");
                System.out.println("4. Eliminar libro del inventario");
                System.out.println("5. Registrar devolución");
                System.out.println("6. Salir");
                System.out.print("Selecciona una opción: ");

                opcion = scanner.nextInt();  // Leer la opción del usuario

                // Realizar la acción correspondiente según la opción seleccionada
                switch (opcion) {
                    case 1:
                        // Comprar libro (aumentar stock)
                        System.out.println("\nCompra de libro seleccionada.");
                        addLibro();
                        break;

                    case 2:
                        // Vender libro (disminuir stock)
                        System.out.println("\nVenta de libro seleccionada.");
                        sellLibro();
                        break;

                    case 3:
                        // Consultar libros disponibles
                        System.out.println("\nConsulta de libros disponibles.");
                        availableLibros();
                        break;

                    case 4:
                        // Eliminar libro del inventario
                        System.out.println("\nEliminación de libro seleccionada.");
                        removeLibro();
                        break;

                    case 5:
                        // Registrar devolución de libro
                        System.out.println("\nDevolución de libro seleccionada.");
                        devLibro();
                        break;

                    case 6:
                        // Salir
                        System.out.println("\nSaliendo del sistema...");
                        break;

                    default:
                        // Opción no válida
                        System.out.println("\nOpción no válida. Intenta de nuevo.");
                }

            } while (opcion != 6); // El ciclo se repite hasta que elija la opción "Salir"

            scanner.close(); // Cerrar el escáner cuando ya no sea necesario
    }

    public static void addLibro() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nIngrese los datos del libro: ");
        System.out.println("Título: ");
        String titulo = scanner.nextLine();
        System.out.println("Autor: ");
        String autor = scanner.nextLine();
        System.out.println("Género: ");
        String genero = scanner.nextLine();
        System.out.println("Stock: ");
        int stock = scanner.nextInt();
        System.out.println("Precio: ");
        double precio = scanner.nextDouble();

        try (Connection con = DriverManager.getConnection(url, usuario, contraseña)){
            PreparedStatement pstmt1 = con.prepareStatement("INSERT INTO libros (titulo, autor, genero, stock, precio) VALUES (?, ?, ?, ?, ?)");
            pstmt1.setString(1, titulo);
            pstmt1.setString(2, autor);
            pstmt1.setString(3, genero);
            pstmt1.setInt(4, stock);
            pstmt1.setDouble(5, precio);

            int rs = pstmt1.executeUpdate();
            if (rs > 0) {
                System.out.println("El libro se ha registrado correctamente.");
            } else {
                System.out.println("El libro no se ha registrado correctamente.");
            }

        } catch (SQLException e){
            throw new SQLException(e);
        }
        scanner.close();

    }

    public static void sellLibro(){
        Scanner sc = new Scanner(System.in);
        try (Connection con = DriverManager.getConnection(url, usuario, contraseña);
             Statement stmt1 = con.createStatement()){

        System.out.println("\n¿Qué libro desea vender?: ");

        String sql1 = "SELECT * FROM libros";
        ResultSet rs = stmt1.executeQuery(sql1);
        System.out.println("Existen los siguientes libros:");

        while (rs.next()) {
            String id = rs.getString("id");
            String tituloLibro = rs.getString("titulo");

            System.out.println("Libro " + id +": " + tituloLibro);
        }
        int libroselect = sc.nextInt();

        Statement stmt2 = con.createStatement();
        String sql2 = "SELECT stock FROM libros WHERE id =" + libroselect;


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        sc.close();
    }

    public static void availableLibros(){

    }
    public static void removeLibro(){

    }
    public static void devLibro(){

    }
}
