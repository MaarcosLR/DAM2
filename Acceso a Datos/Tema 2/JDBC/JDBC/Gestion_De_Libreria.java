package JDBC;

import java.sql.*;
import java.util.Scanner;

public class Gestion_De_Libreria {
    private static final String url = "jdbc:mysql://localhost:3306/libreria";
    private static final String usuario = "root";
    private static final String contrasena = "1234";

    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        while (true) {
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
            scanner.nextLine();

            switch (opcion) {
                case 1 -> addLibro(scanner);
                case 2 -> sellLibro(scanner);
                case 3 -> availableLibros(scanner);
                case 4 -> removeLibro(scanner);
                case 5 -> devLibro(scanner);
                case 6 -> totalVentas();
                case 7 -> {
                    System.out.println("\nSaliendo del sistema...");
                    scanner.close();
                    return;
                }
                default -> System.out.println("\nOpcion no valida. Intenta de nuevo.");
            }
        }
    }

    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, usuario, contrasena);
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
        scanner.nextLine();
        System.out.print("Precio: ");
        double precio = scanner.nextDouble();

        if (stock < 0 || precio <= 0) {
            System.out.println("Error: El stock o precio no pueden ser negativos o cero.");
            return;
        }

        try (Connection con = getConnection()) {
            PreparedStatement pstmt = con.prepareStatement(
                    "INSERT INTO libros (titulo, autor, genero, stock, precio) VALUES (?, ?, ?, ?, ?)"
            );
            pstmt.setString(1, titulo);
            pstmt.setString(2, autor);
            pstmt.setString(3, genero);
            pstmt.setInt(4, stock);
            pstmt.setDouble(5, precio);

            int rs = pstmt.executeUpdate();
            System.out.println(rs > 0 ? "El libro se ha registrado correctamente." : "El libro no se ha registrado.");
        }
    }

    public static void sellLibro(Scanner scanner) {
        try (Connection con = getConnection()) {
            con.setAutoCommit(false);
            System.out.println("\n¿Qué libro desea vender?: ");
            mostrarLibros();

            int libroselect = scanner.nextInt();
            scanner.nextLine();

            String sql2 = "SELECT * FROM libros WHERE id = ?";
            PreparedStatement stmt2 = con.prepareStatement(sql2);
            stmt2.setInt(1, libroselect);
            ResultSet rs2 = stmt2.executeQuery();

            if (rs2.next()) {
                String titulo = rs2.getString("titulo");
                int stock = rs2.getInt("stock");

                System.out.println("\nLibro seleccionado: " + titulo + " con un stock de: " + stock);
                System.out.print("¿Cuántos libros desea vender? ");
                int cantlibros = scanner.nextInt();
                scanner.nextLine();

                if (cantlibros <= stock && cantlibros > 0) {
                    int nuevostock = stock - cantlibros;

                    String sql3 = "UPDATE libros SET stock = ? WHERE id = ?";
                    PreparedStatement stmt3 = con.prepareStatement(sql3);
                    stmt3.setInt(1, nuevostock);
                    stmt3.setInt(2, libroselect);

                    stmt3.executeUpdate();
                    con.commit();
                    System.out.println("Venta realizada con éxito. Nuevo stock de " + titulo + ": " + nuevostock);
                } else {
                    con.rollback();
                    throw new StockInsuficienteException("Stock insuficiente para completar la venta.");
                }
            } else {
                System.out.println("No existe el libro.");
            }
        } catch (SQLException | StockInsuficienteException e) {
            System.err.println("Error durante la venta: " + e.getMessage());
        }
    }

    public static void availableLibros(Scanner scanner) {
        try (Connection con = getConnection()) {
            mostrarLibros();

            System.out.println("\n¿Por qué tipo deseas buscar los libros?");
            System.out.println("1. Por Género");
            System.out.println("2. Por Autor");
            System.out.println("3. Por Título");
            int opcion = scanner.nextInt();
            scanner.nextLine();

            String sql = switch (opcion) {
                case 1 -> "SELECT * FROM libros WHERE genero = ?";
                case 2 -> "SELECT * FROM libros WHERE autor = ?";
                case 3 -> "SELECT * FROM libros WHERE titulo = ?";
                default -> null;
            };

            if (sql != null) {
                System.out.print("Introduce el valor a buscar: ");
                String valor = scanner.nextLine();
                PreparedStatement pstmt = con.prepareStatement(sql);
                pstmt.setString(1, valor);

                ResultSet rs = pstmt.executeQuery();
                mostrarResultados(rs);
            } else {
                System.out.println("Opción no válida.");
            }
        } catch (SQLException e) {
            System.err.println("Error al consultar libros: " + e.getMessage());
        }
    }

    private static void mostrarResultados(ResultSet rs) throws SQLException {
        while (rs.next()) {
            String titulo = rs.getString("titulo");
            String autor = rs.getString("autor");
            String genero = rs.getString("genero");
            int stock = rs.getInt("stock");
            double precio = rs.getDouble("precio");

            System.out.println("Título: " + titulo + ", Autor: " + autor + ", Género: " + genero +
                    ", Stock: " + stock + ", Precio: " + precio);
        }
    }

    public static void removeLibro(Scanner scanner) {
        try (Connection con = getConnection()) {
            System.out.println("\n¿Que libro desea eliminar?");
            mostrarLibros();

            System.out.print("Introduce el ID del libro que deseas eliminar: ");
            int ideliminar = scanner.nextInt();
            scanner.nextLine();

            String sql2 = "DELETE FROM libros WHERE id = ?";
            PreparedStatement stmt2 = con.prepareStatement(sql2);
            stmt2.setInt(1, ideliminar);

            int rows = stmt2.executeUpdate();
            System.out.println(rows > 0 ? "Libro eliminado correctamente." : "No se pudo eliminar el libro.");
        } catch (SQLException e) {
            System.err.println("Error al eliminar el libro: " + e.getMessage());
        }
    }

    public static void devLibro(Scanner scanner) {
        try (Connection con = getConnection()) {
            con.setAutoCommit(false);

            System.out.println("¿Qué libro desea devolver?");
            mostrarLibros();

            int libroID = scanner.nextInt();
            scanner.nextLine();

            System.out.print("¿Cuántos libros deseas devolver? ");
            int cantidadDevuelta = scanner.nextInt();
            scanner.nextLine();

            String sql2 = "SELECT * FROM libros WHERE id = ?";
            PreparedStatement pstmt = con.prepareStatement(sql2);
            pstmt.setInt(1, libroID);
            ResultSet rs2 = pstmt.executeQuery();

            if (rs2.next() && cantidadDevuelta > 0) {
                String titulo = rs2.getString("titulo");
                int stockActual = rs2.getInt("stock");

                int nuevoStock = stockActual + cantidadDevuelta;

                String sql3 = "UPDATE libros SET stock = ? WHERE id = ?";
                PreparedStatement pstmtUpdate = con.prepareStatement(sql3);
                pstmtUpdate.setInt(1, nuevoStock);
                pstmtUpdate.setInt(2, libroID);

                pstmtUpdate.executeUpdate();
                con.commit();
                System.out.println("Devolución realizada con éxito. Nuevo stock de " + titulo + ": " + nuevoStock);
            } else {
                con.rollback();
                System.out.println("No se encontró el libro o cantidad inválida.");
            }
        } catch (SQLException e) {
            System.err.println("Error al procesar la devolución: " + e.getMessage());
        }
    }

    public static void totalVentas() {
        try (Connection con = getConnection()) {
            CallableStatement stmt = con.prepareCall("{CALL calcular_total_ventas_por_genero()}");
            ResultSet rs = stmt.executeQuery();

            System.out.println("\nTotal de ventas por género literario:");
            while (rs.next()) {
                String genero = rs.getString("genero");
                double totalVentas = rs.getDouble("total_ventas");
                System.out.println("Género: " + genero + " | Total de ventas: " + totalVentas);
            }
        } catch (SQLException e) {
            System.err.println("Error al calcular ventas: " + e.getMessage());
        }
    }

    private static void mostrarLibros() {
        try (Connection con = getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM libros")) {

            System.out.println("\nLibros disponibles:");
            while (rs.next()) {
                int id = rs.getInt("id");
                String titulo = rs.getString("titulo");
                String autor = rs.getString("autor");
                String genero = rs.getString("genero");
                int stock = rs.getInt("stock");
                double precio = rs.getDouble("precio");

                System.out.println("ID: " + id + ", Título: " + titulo + ", Autor: " + autor +
                        ", Género: " + genero + ", Stock: " + stock + ", Precio: " + precio);
            }
        } catch (SQLException e) {
            System.err.println("Error al mostrar los libros: " + e.getMessage());
        }
    }
}

class StockInsuficienteException extends Exception {
    public StockInsuficienteException(String mensaje) {
        super(mensaje);
    }
}
