package practica3extra;

import javax.swing.*;

public class JCheckBoxPersonalizado extends JCheckBox{

    public JCheckBoxPersonalizado () {
        setText("¿Empleado activo?");
    }

    public static void main(String[] args) {
        new JCheckBoxPersonalizado();
    }
}
