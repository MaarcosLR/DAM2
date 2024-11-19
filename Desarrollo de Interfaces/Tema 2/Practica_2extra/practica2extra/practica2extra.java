package practica2extra;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;

public class practica2extra extends JFrame {

    // Componentes de la interfaz
    private JButton regbtn;
    private JLabel nombre, correo, tipo, registxt, espacio, espacio2, espacio3;
    private JTextField nombretxt, correotxt;
    private JCheckBox terminos;
    private JButton confreg;
    private JComboBox<String> tipos;

    // Constructor de la ventana principal
    public practica2extra() {
        setTitle("Gestión de Clientes");
        setSize(600, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Botón para abrir la ventana de registro
        regbtn = new JButton("Registrar Nuevo Cliente");
        add(regbtn);

        // Listener para abrir la ventana de registro
        regbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ventanaRegistro();
            }
        });

        setVisible(true);
    }

    // Método para mostrar la ventana de registro
    private void ventanaRegistro() {
        JFrame frame = new JFrame("Registro de Cliente");
        frame.setSize(600, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new GridLayout(6, 2, 10, 10));

        // Creación de componentes
        nombre = new JLabel("Nombre:");
        nombretxt = new JTextField(15);
        correo = new JLabel("Correo Electrónico:");
        correotxt = new JTextField(15);
        tipo = new JLabel("Tipo de Cliente:");
        tipos = new JComboBox<>(new String[] {null, "Regular", "Premium", "VIP"});
        terminos = new JCheckBox("Acepto términos y condiciones.");
        confreg = new JButton("Confirmar Registro");
        registxt = new JLabel("");
        espacio = new JLabel("");
        espacio2 = new JLabel("");
        espacio3 = new JLabel("");

        // Añadir componentes a la ventana
        frame.add(nombre);
        frame.add(nombretxt);
        frame.add(correo);
        frame.add(correotxt);
        frame.add(tipo);
        frame.add(tipos);
        frame.add(espacio); // Espacio vacío
        frame.add(terminos);
        frame.add(espacio2); // Espacio vacío
        frame.add(confreg);
        frame.add(espacio3); // Espacio vacío
        frame.add(registxt);

        // Listener para resaltar campos al enfocarlos
        nombretxt.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                nombretxt.setBackground(Color.YELLOW);
            }

            @Override
            public void focusLost(FocusEvent e) {
                nombretxt.setBackground(Color.WHITE);
            }
        });

        correotxt.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                correotxt.setBackground(Color.YELLOW);
            }

            @Override
            public void focusLost(FocusEvent e) {
                correotxt.setBackground(Color.WHITE);
            }
        });

        // Listener para verificar campos al escribir
        nombretxt.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
            	comprobarCampos();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
            	comprobarCampos();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
            	comprobarCampos();
            }
        });

        correotxt.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                comprobarCampos();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                comprobarCampos();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                comprobarCampos();
            }
        });

        // Listener para el combo de tipo de cliente
        tipos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                comprobarCampos();
            }
        });

        // Listener para la casilla de términos y condiciones
        terminos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                comprobarCampos();
            }
        });

        // Acción del botón de confirmar registro
        confreg.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(comprobarCampos()) {
                	registxt.setText("Cliente registrado: " + nombretxt.getText() + ", Tipo: " + tipos.getSelectedItem());
                };
            }
        });

        frame.setVisible(true);
    }

    // Método para validar email
    private boolean validarEmail(String correo) {
        return correo.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}$");
    }

  
    // Método para comprobar si los campos están completos y habilitar el botón de registro
    private boolean comprobarCampos() {
        registxt.setText("");
        confreg.setBackground(null);

        // Verificar si los campos están completos y correctos
        if (nombretxt.getText().trim().isEmpty() || correotxt.getText().trim().isEmpty()) {
           registxt.setText("Por favor, rellena todos los campos.");
           return false;
        } else if (!validarEmail(correotxt.getText())) {
            registxt.setText("El correo no es válido.");
            return false;
        } else if (tipos.getSelectedItem() == null) {
            registxt.setText("Por favor, selecciona un tipo de cliente.");
            return false;
        } else if (!terminos.isSelected()) {
            registxt.setText("Por favor, acepte los términos y condiciones.");
            return false;
        } else {
            confreg.setBackground(Color.green); // Si todo está correcto, se cambia de color el botón de registro
            return true;
        }
    }
    
    //Llamo al método con un main para poder ejecutar el código
    public static void main(String[] args) {
        new practica2extra();
    }
}
