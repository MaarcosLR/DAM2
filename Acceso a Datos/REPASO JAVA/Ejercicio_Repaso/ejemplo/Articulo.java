package ejemplo;

class Articulo {
    private String nombre;
    private double precio;
    private double IVA;
    private int stock;

    public Articulo(String nombre, double precio, double IVA, int stock) {
        setNombre(nombre);
        setPrecio(precio);
        setIVA(IVA);
        setStock(stock);
    }

    public void setNombre(String nombre) {
        if (nombre != null && !nombre.trim().isEmpty()) {
            this.nombre = nombre;
        } else {
            System.out.println("El nombre no puede estar vacío");
        }
    }

    public void setPrecio(double precio) {
        if (precio >= 0) {
            this.precio = precio;
        } else {
            System.out.println("El precio no puede ser negativo");
        }
    }

    public void setIVA(double IVA) {
        if (IVA >= 0) {
            this.IVA = IVA;
        } else {
            System.out.println("El IVA no puede ser negativo");
        }
    }

    public void setStock(int stock) {
        if (stock >= 0) {
            this.stock = stock;
        } else {
            System.out.println("El stock no puede ser negativo");
        }
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public double getIVA() {
        return IVA;
    }

    public int getStock() {
        return stock;
    }
    public double getPVP() {
        return precio * IVA / 100;
    }
    public double getPrecioDescuento(double descuento){
        if (descuento >= 0 && descuento <= 100) {
         double pvp = getPVP();
         return pvp = (pvp * descuento / 100);
        } else {
            System.out.println("El descuento no puede ser negativo");
            return getPVP();
        }
    }
    public boolean Vender (int cantidad) {
        if (cantidad >= 0) {
            stock += cantidad;
            return true;
        } else {
            System.out.println("El cantidad no puede ser negativo");
            return false;
        }
    }
    public boolean almacenar(int cantidad) {
        if (cantidad >= 0) {
            stock += cantidad;
            return true;
        } else {
            System.out.println("El cantidad no puede ser negativo");
            return false;
        }
    }
    @Override
    public String toString() {
        return nombre + " - Precio: " + precio + " € " + " - IVA: " + IVA + " - PVP " + getPVP() + " € ";
    }
}
