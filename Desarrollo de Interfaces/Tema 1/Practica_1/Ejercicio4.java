import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Ejercicio4 {

    public static void main(String[] args) {

        // Crear el JFrame
        JFrame frame = new JFrame("Ejemplo de componentes");

        // Configurar el GridLayout: 3 filas, 2 columnas con separación horizontal y vertical de 10px
        frame.setLayout(new GridLayout(3, 2, 10, 10));

        //Crear campos de texto
        JLabel nombreLabel = new JLabel("Nombre:");
        JTextField nombreField = new JTextField(10);

        JLabel apellidosLabel = new JLabel("Apellidos:");
        JTextField apellidosField = new JTextField(10);

        //Crear Botones
        JButton enviar = new JButton("Enviar");
        JButton cerrar = new JButton("Cerrar");

        //Acción para el mensaje de confirmación del envio de datos
        enviar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, "Datos enviados correctamente.");
                frame.dispose();
            }
        });
        //Acción para cerrar la ventana sin mensaje
        cerrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose(); // Cerrar el diálogo
            }
        });

        //Añadir texto y botones al JFrame
        frame.add(nombreLabel);
        frame.add(nombreField);
        frame.add(apellidosLabel);
        frame.add(apellidosField);
        frame.add(enviar);
        frame.add(cerrar);

        // Configurar el tamaño del JFrame
        frame.setSize(400, 300);

        //Comportamiento de la ventana al cerrar
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Hacer visible la ventana
        frame.setVisible(true);
    }
}