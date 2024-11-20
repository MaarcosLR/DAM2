package JDBC;

import java.sql.*;
import java.util.Scanner;

// Comprar libros nuevos: Agregar nuevos libros al inventario.
// Vender un libro: Disminuir el stock del libro vendido.
// En caso de que no haya suficientes ejemplares, se debe lanzar una excepcion y la venta no debe realizarse.
// Consultar libros disponibles: Mostrar el inventario de libros, con la opcion de consultar por genero, autor o titulo.
// Eliminar libros del inventario: Eliminar libros del inventario si ya no se van a vender.
// Registrar devoluciones: Permitir que los clientes devuelvan un libro.

public class Gestion_De_Libreria {
    private static final String url = "jdbc:mysql://localhost:3306/libreria";
    private static final String usuario = "root";
    private static final String contrasena = "1234";

    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        // Mostrar el menu y ejecutar las acciones hasta que el usuario decida salir
        while (true) {
            // Mostrar menu de opciones
            System.out.println("\nMenu de Inventario de Libros:");
            System.out.println("1. Comprar libro");
            System.out.println("2. Vender libro");
            System.out.println("3. Consultar libros disponibles");
            System.out.println("4. Eliminar libro del inventario");
            System.out.println("5. Registrar devolucion");
            System.out.println("6. Total de ventas");
            System.out.println("7. Salir");
            System.out.print("Selecciona una opcion: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de linea despues de nextInt()

            // Realizar la accion correspondiente segun la opcion seleccionada
            switch (opcion) {
                case 1:
                    // Comprar libro (aumentar stock)
                    System.out.println("\nCompra de libro seleccionada.");
                    addLibro(scanner);
                    break;

                case 2:
                    // Vender libro (disminuir stock)
                    System.out.println("\nVenta de libro seleccionada.");
                    sellLibro(scanner);
                    break;

                case 3:
                    // Consultar libros disponibles
                    System.out.println("\nConsulta de libros disponibles.");
                    availableLibros(scanner);  // Pasar el scanner para que no se cierre
                    break;

                case 4:
                    // Eliminar libro del inventario
                    System.out.println("\nEliminacion de libro seleccionada.");
                    removeLibro(scanner);
                    break;

                case 5:
                    // Registrar devolucion de libro
                    System.out.println("\nDevolucion de libro seleccionada.");
                    devLibro(scanner);
                    break;

                case 6:
                    //Ventas por cada género literario
                    System.out.println("\nTotal de ventas seleccionado.");
                    totalVentas();
                    return; // Termina el programa

                case 7:
                    // Salir
                    System.out.println("\nSaliendo del sistema...");
                    scanner.close(); // Cerrar el escaner cuando termine
                    return; // Termina el programa

                default:
                    // Opcion no valida
                    System.out.println("\nOpcion no valida. Intenta de nuevo.");
            }
        }
    }

    public static void addLibro(Scanner scanner) throws SQLException {
        System.out.println("\nIngrese los datos del libro: ");
        System.out.print("Titulo: ");
        String titulo = scanner.nextLine();
        System.out.print("Autor: ");
        String autor = scanner.nextLine();
        System.out.print("Genero: ");
        String genero = scanner.nextLine();
        System.out.print("Stock: ");
        int stock = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de linea despues de nextInt()
        System.out.print("Precio: ");
        double precio = scanner.nextDouble();

        try (Connection con = DriverManager.getConnection(url, usuario, contrasena)){
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
    }

    public static void sellLibro(Scanner scanner) {
        try (Connection con = DriverManager.getConnection(url, usuario, contrasena)) {

            System.out.println("\nQue libro desea vender?: ");
            mostrarLibros();

            int libroselect = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de linea despues de nextInt()

            String sql2 = "SELECT * FROM libros WHERE id = ?";
            PreparedStatement stmt2 = con.prepareStatement(sql2);
            stmt2.setInt(1, libroselect);
            ResultSet rs2 = stmt2.executeQuery();

            if (rs2.next()) {
                String titulo = rs2.getString("titulo");
                int stock = rs2.getInt("stock");

                System.out.println("\nLibro seleccionado: " + titulo + " con un stock de: " + stock);
                System.out.print("¿Cuantos libros desea vender? ");
                int cantlibros = scanner.nextInt();
                scanner.nextLine(); // Consumir el salto de linea despues de nextInt()

                if (cantlibros <= stock) {
                    int nuevostock = stock - cantlibros;

                    String sql3 = "UPDATE libros SET stock = ? WHERE id = ?";
                    PreparedStatement stmt3 = con.prepareStatement(sql3);
                    stmt3.setInt(1, nuevostock);
                    stmt3.setInt(2, libroselect);

                    int rows = stmt3.executeUpdate();

                    if (rows > 0 ) {
                        System.out.println("Venta realizada con exito. El nuevo stock de " + titulo + ": " + nuevostock);
                    } else {
                        System.out.println("Error al actualizar el stock.");
                    }
                } else {
                    System.out.println("No hay suficiente stock.");
                }

            } else {
                System.out.println("No existe el libro.");
            }

            // Cerrar los recursos
            rs2.close();
            stmt2.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void availableLibros(Scanner scanner) {  // Asegurarse de no cerrar el scanner aqui
        try (Connection con = DriverManager.getConnection(url, usuario, contrasena)) {
            mostrarLibros();

            // Solicitar al usuario que tipo de busqueda desea realizar
            System.out.println("\n¿Por que tipo deseas buscar los libros?");
            System.out.println("1. Por Genero");
            System.out.println("2. Por Autor");
            System.out.println("3. Por Titulo");
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir la nueva linea pendiente despues de nextInt()

            // Consultas preparadas
            String sql1 = "SELECT * FROM libros WHERE genero = ?"; // Genero
            String sql2 = "SELECT * FROM libros WHERE autor = ?"; // Autor
            String sql3 = "SELECT * FROM libros WHERE titulo = ?"; // Titulo

            PreparedStatement gen = con.prepareStatement(sql1);
            PreparedStatement author = con.prepareStatement(sql2);
            PreparedStatement title = con.prepareStatement(sql3);

            switch (opcion) {
                case 1: // Buscar por genero
                    System.out.print("Introduce el genero: ");
                    String generoLibro = scanner.nextLine();
                    gen.setString(1, generoLibro);
                    ResultSet rsGenero = gen.executeQuery();
                    mostrarResultados(rsGenero);
                    break;
                case 2: // Buscar por autor
                    System.out.print("Introduce el autor: ");
                    String autorLibro = scanner.nextLine();
                    author.setString(1, autorLibro);
                    ResultSet rsAutor = author.executeQuery();
                    mostrarResultados(rsAutor);
                    break;
                case 3: // Buscar por titulo
                    System.out.print("Introduce el titulo: ");
                    String tituloLibro = scanner.nextLine();
                    title.setString(1, tituloLibro);
                    ResultSet rsTitulo = title.executeQuery();
                    mostrarResultados(rsTitulo);
                    break;
                default:
                    System.out.println("Opcion no valida.");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Metodo auxiliar para mostrar los resultados
    private static void mostrarResultados(ResultSet rs) throws SQLException {
        while (rs.next()) {
            String titulo = rs.getString("titulo");
            String autor = rs.getString("autor");
            String genero = rs.getString("genero");
            int stock = rs.getInt("stock");
            double precio = rs.getDouble("precio");

            System.out.println("Titulo: " + titulo + ", Autor: " + autor + ", Genero: " + genero +
                    ", Stock: " + stock + ", Precio: " + precio);
        }
    }

    public static void removeLibro(Scanner scanner) throws SQLException {
        try (Connection con = DriverManager.getConnection(url, usuario, contrasena)) {

            System.out.println("\n¿Que libro desea eliminar?");
            mostrarLibros();

            System.out.print("Introduce el ID del libro que deseas eliminar: ");
            int ideliminar = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de linea despues de nextInt()

            String sql2 = "DELETE FROM libros WHERE id = ?";
            PreparedStatement stmt2 = con.prepareStatement(sql2);
            stmt2.setInt(1, ideliminar);

            int rows = stmt2.executeUpdate();
            if (rows > 0) {
                System.out.println("Libro eliminado correctamente.");
            } else {
                System.out.println("No se pudo eliminar el libro.");
            }

            // Cerrar recursos
            stmt2.close();
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

    public static void devLibro(Scanner scanner) {
        try (Connection con = DriverManager.getConnection(url, usuario, contrasena)) {

            System.out.println("¿Qué libro desea devolver?");
            mostrarLibros();

            int libroID = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea después de nextInt()

            System.out.print("¿Cuántos libros deseas devolver? ");
            int cantidadDevuelta = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea después de nextInt()

            // Consultar si el libro existe en la base de datos
            String sql2 = "SELECT * FROM libros WHERE id = ?";
            PreparedStatement pstmt = con.prepareStatement(sql2);
            pstmt.setInt(1, libroID);
            ResultSet rs2 = pstmt.executeQuery();

            // Si el libro existe, proceder con la devolución
            if (rs2.next()) {
                String titulo = rs2.getString("titulo");
                int stockActual = rs2.getInt("stock");

                System.out.println("Libro seleccionado: " + titulo + " con un stock actual de: " + stockActual);

                // Aumentar el stock con la cantidad devuelta
                int nuevoStock = stockActual + cantidadDevuelta;

                // Actualizar el stock en la base de datos
                String sql3 = "UPDATE libros SET stock = ? WHERE id = ?";
                PreparedStatement pstmtUpdate = con.prepareStatement(sql3);
                pstmtUpdate.setInt(1, nuevoStock);
                pstmtUpdate.setInt(2, libroID);

                int rows = pstmtUpdate.executeUpdate();
                if (rows > 0) {
                    System.out.println("Devolución realizada con éxito. El nuevo stock de " + titulo + " es: " + nuevoStock);
                } else {
                    System.out.println("Error al actualizar el stock.");
                }
            } else {
                System.out.println("No se encontró el libro con ese ID.");
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al procesar la devolución", e);
        }
    }

    public static void totalVentas() {
        // Intentar conectar a la base de datos y ejecutar el procedimiento
        try (Connection con = DriverManager.getConnection(url, usuario, contrasena)) {
            // Preparar el CallableStatement para llamar al procedimiento almacenado
            CallableStatement stmt = con.prepareCall("{CALL calcular_total_ventas_por_genero()}");

            // Ejecutar la consulta y obtener los resultados en un ResultSet
            ResultSet rs = stmt.executeQuery();

            // Mostrar los resultados (ventas por género)
            System.out.println("\nTotal de ventas por género literario:");
            while (rs.next()) {
                String genero = rs.getString("genero");
                double totalVentas = rs.getDouble("total_ventas");
                System.out.println("Género: " + genero + " | Total de ventas: " + totalVentas);
            }

            // Cerrar el ResultSet y el CallableStatement
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            // Manejo de excepciones
            throw new RuntimeException(e);
        }
    }


    public static void mostrarLibros(){
        try (Connection con = DriverManager.getConnection(url, usuario, contrasena);
             Statement stmt1 = con.createStatement()) {
            String sql1 = "SELECT * FROM libros";
            ResultSet rs = stmt1.executeQuery(sql1);
            System.out.println("\nExisten los siguientes libros:");

            while (rs.next()) {
                String id = rs.getString("id");
                String tituloLibro = rs.getString("titulo");
                String autor = rs.getString("autor");
                String genero = rs.getString("genero");
                int stock = rs.getInt("stock");
                double precio = rs.getDouble("precio");

                System.out.println("Libro " + id + ": " + tituloLibro + " | autor: " + autor + " | género: " + genero + " | stock: " + stock + " | precio: " + precio);
            }
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}