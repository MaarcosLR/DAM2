package practica3extra;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class VentanaPrincipal extends JFrame {

    private JLabel txtPpal;
    private JPanel panelBotones;
    private JButton addEmpleado;
    private JButton viewEmpleados;
    private JButton salir;
    private ArrayList<String> listaEmpleados;

    public VentanaPrincipal() {
        listaEmpleados = new ArrayList<>();

        // Configuración del frame
        setTitle("Ventana Principal");
        setSize(600, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout());

        // Añadir el label de texto
        txtPpal = new JLabel("¿Qué acción desea realizar?:");

        // Añadir label al frame
        add(txtPpal);

        // Creación de panel de los botones
        panelBotones = new JPanel();
        panelBotones.setLayout(new GridLayout(3, 1));

        // Añadir panel al frame
        add(panelBotones);

        // Creación de botones
        addEmpleado = new JButton("Agregar Empleado");
        viewEmpleados = new JButton("Ver Empleados");
        salir = new JButton("Salir");

        // Añadir botones al frame
        panelBotones.add(addEmpleado);
        panelBotones.add(viewEmpleados);
        panelBotones.add(salir);

        // ActionListeners para cada botón
        addEmpleado.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new VentanaAgregarEmpleado(listaEmpleados);
            }
        });

        viewEmpleados.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new VentanaListaEmpleados(listaEmpleados);
            }
        });

        salir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        // Hacer visible la ventana
        setVisible(true);
    }

    public static void main(String[] args) {
        new VentanaPrincipal();
    }
}
