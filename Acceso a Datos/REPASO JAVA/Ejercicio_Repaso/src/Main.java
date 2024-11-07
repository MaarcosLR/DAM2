
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Articulo articulo = new Articulo("Pijama", 25, 21, 30);
        int opcion;
        do {
            System.out.println("\n---- MENÚ ----");
            System.out.println("1. Ver información");
            System.out.println("2. Cambiar el Precio");
            System.out.println("3. Ver PVP con IVA");
            System.out.println("4. Ver PVP con descuento");
            System.out.println("5. Vender una cantidad");
            System.out.println("6. Almacenar una cantidad");
            System.out.println("7. Salir");
            opcion = sc.nextInt();

            switch (opcion) {
                case 1:
                    System.out.println(articulo);
                    break;
                case 2:
                    System.out.println("Introduce el nuevo precio");
                    double NuevoPrecio = sc.nextDouble();
                    articulo.setPrecio(NuevoPrecio);
                    break;
                case 3:
                    System.out.println("El PVP con IVA es " + articulo.getPVP() + " € ");
                    break;
                case 4:
                    System.out.println("Introduce el porcentaje de descuento");
                    double descuento = sc.nextDouble();
                    System.out.println("El PVP con " + descuento + " % de descuento es: " + articulo.getPrecioDescuento(descuento) + " € ");
                    break;
                case 5:
                    System.out.println("Introduce la cantidad a Vender");
                    int cantidadVender = sc.nextInt();
                    if (articulo.Vender(cantidadVender)) {
                        System.out.println("Venta exitosa");
                    } else {
                        System.out.println("No se puedo realizar la venta");
                    }break;
                case 6:
                    System.out.println("Introduce cantidad a almacenar");
                    int cantidadAlmacenar = sc.nextInt();
                    if (articulo.almacenar(cantidadAlmacenar)) {
                        System.out.println("Almacenaje exitosa");
                    } else {
                        System.out.println("No se puedo realizar el almacenamiento");
                    }break;
                case 7:
                    System.out.println("Salir del programa");
                    break;
                default:
                    System.out.println("Opcion no valida");
                    break;
            }
        } while (opcion != 7);
        sc.close();
    }

}
