import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JCheckBoxPersonalizado extends JCheckBox{

    public JCheckBoxPersonalizado () {
        setText("¿Empleado activo?");
    }

    public static void main(String[] args) {
        new JCheckBoxPersonalizado();
    }
}
