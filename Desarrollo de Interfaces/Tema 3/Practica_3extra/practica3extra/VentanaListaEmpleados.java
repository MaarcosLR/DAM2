package practica3extra;

import javax.swing.*;
import java.util.ArrayList;

public class VentanaListaEmpleados extends JFrame {

    private JTextArea areaLista;

    public VentanaListaEmpleados(ArrayList<String> listaEmpleados) {
        // Configurar el frame
        setTitle("Lista de empleados");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(500, 500);
        setLocationRelativeTo(null);

        // Área de texto para mostrar la lista
        areaLista = new JTextArea();
        areaLista.setEditable(false);

        // Añadir la lista de empleados al área de texto
        for (String empleado : listaEmpleados) {
            areaLista.append(empleado + "\n");
        }

        // Añadir el área de texto al frame
        add(new JScrollPane(areaLista));

        // Hacer visible la ventana
        setVisible(true);
    }
}
