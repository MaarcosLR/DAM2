import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Ejercicio1{

    public static void main(String[] args) {
        // Crear el JFrame (ventana principal)
        JFrame frame = new JFrame("Ejercicio 1");
        JLabel text = new JLabel("¿Qué acción desea realizar?");
        frame.add(text);

        // Configurar el tamaño del JFrame
        frame.setSize(300, 150);

        //Comportamiento de la ventana al cerrar
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Establecer un layout al frame
        frame.setLayout(new FlowLayout());

        // Crear los botones
        JButton pagarbutton = new JButton("Pagar");
        JButton cancelarbutton = new JButton("Cancelar");

        // Acción para pasar a la ventana del método de pago cuando se haga clic en el botón
        pagarbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Llamar al método para mostrar el diálogo
                metodosdepago(frame);
            }
        });

        //Acción para el botón cancelar
        cancelarbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, "Has cancelado la operación");
                frame.dispose(); //Cerrar la ventana
            }
        });

        // Añadir el botón al JFrame
        frame.add(pagarbutton);
        frame.add(cancelarbutton);

        // Hacer visible el JFrame
        frame.setVisible(true);
    }

    // Método para mostrar los métodos de pago
    private static void metodosdepago(JFrame parentFrame) {
        // Crear el JDialog de Confrmación
        JDialog pagar = new JDialog(parentFrame, "Confirmación", true);
        // "true" para bloquear la ventana principal

        // Configurar el tamaño del diálogo
        pagar.setSize(300, 150);
        pagar.setLayout(new FlowLayout());

        // Crear la etiqueta del mensaje
        JLabel messageLabel = new JLabel("Elija el método de pago:");
        pagar.add(messageLabel);

        // Crear los radiobutton para el método de pago y un botón de cancelar
        JRadioButton paypal = new JRadioButton("Paypal");
        JRadioButton tarjeta = new JRadioButton("Tarjeta de crédito");
        JRadioButton bizum = new JRadioButton("Bizum");

        //Botones de confirmación y cancelar
        JButton confirmButton = new JButton("Confirmar");
        JButton cancelButton = new JButton("Cancelar");

        //Agrupar los botones
        ButtonGroup metodosgroup = new ButtonGroup();
        metodosgroup.add(paypal);
        metodosgroup.add(tarjeta);
        metodosgroup.add(bizum);

        // Acción para el botón "Confirmar"
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(parentFrame, "Has seleccionado el método de pago.");
                pagar.dispose(); // Cerrar el diálogo
            }
        });

        // Acción para el botón "Cancelar"
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(parentFrame, "Has cancelado.");
                pagar.dispose(); // Cerrar el diálogo
            }
        });

        // Añadir los botones al diálogo
        pagar.add(paypal);
        pagar.add(tarjeta);
        pagar.add(bizum);
        pagar.add(confirmButton);
        pagar.add(cancelButton);

        // Mostrar el diálogo
        pagar.setVisible(true);
    }
}

// PREGUNTAS
// a) Para crear las ventanas se usará JFrame.
// b) Cuando creemos los botones, tendremos que asociarlos a un eventListener para que cada uno tenga
// asignado una acción diferente.
// c) JLabel, para cuando queramos introducir un texto, como por ejemplo, una pregunta para el usuario.
// JRadio-Button cuando queramos que el usuario solo elija 1 opción de pago de las que le demos.
// JButton cuando le queramos dar una opción al usuario tanto de Pagar, Confirmar o Cancelar para cerrar la ventana.


