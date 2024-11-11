import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class VentanaAgregarEmpleado extends JFrame {

    private JPanel panelComponentes;
    private JLabel lblNombre;
    private JTextField txtNombre;
    private JLabel lblApellidos;
    private JTextField txtApellidos;
    private JLabel lblDepartamento;
    private JComboBox comboDepartamento;
    private JButton btnAgregar;
    private JButton btnCancelar;
    private JCheckBoxPersonalizado checkBoxPersonalizado;
    private JLabel estadoComprobante;
    private ArrayList<String> listaEmpleados;

    public VentanaAgregarEmpleado(ArrayList<String> listaEmpleados) {
        this.listaEmpleados = listaEmpleados; // Recibe la lista de empleados

        // Configuración del frame
        setTitle("Agregar Empleado");
        setSize(600, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout());

        // Creación de panel de los botones
        panelComponentes = new JPanel();
        panelComponentes.setLayout(new GridLayout(7, 2));

        // Añadir panel al frame
        add(panelComponentes);

        // Creación de componentes
        lblNombre = new JLabel("Nombre: ");
        txtNombre = new JTextField(10);
        lblApellidos = new JLabel("Apellidos: ");
        txtApellidos = new JTextField(10);
        lblDepartamento = new JLabel("Departamento: ");
        comboDepartamento = new JComboBox();
        comboDepartamento.addItem(null);
        comboDepartamento.addItem("Ventas");
        comboDepartamento.addItem("IT");
        comboDepartamento.addItem("Recursos Humanos");
        checkBoxPersonalizado = new JCheckBoxPersonalizado();
        checkBoxPersonalizado.setBackground(Color.red);
        btnAgregar = new JButton("Agregar");
        btnCancelar = new JButton("Cancelar");
        estadoComprobante = new JLabel("");

        btnAgregar.setEnabled(false);

        // Añadir botones al frame
        panelComponentes.add(lblNombre);
        panelComponentes.add(txtNombre);
        panelComponentes.add(lblApellidos);
        panelComponentes.add(txtApellidos);
        panelComponentes.add(lblDepartamento);
        panelComponentes.add(comboDepartamento);
        panelComponentes.add(new JLabel(""));
        panelComponentes.add(checkBoxPersonalizado);
        panelComponentes.add(new JLabel(""));
        panelComponentes.add(estadoComprobante);
        panelComponentes.add(btnAgregar);
        panelComponentes.add(btnCancelar);

        // ActionListener para el botón cancelar para volver a la ventana principal
        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new VentanaPrincipal();
            }
        });

        // ActionListener para agregar los empleados
        btnAgregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                comprobarCampos();
                agregarEmpleado();
                JOptionPane.showMessageDialog(null, "Empleado Agregado");
                dispose();
                new VentanaPrincipal(); // Vuelve a la ventana principal después de agregar
            }
        });

        // Hacer visible la ventana
        setVisible(true);

        // Listener para comprobar que no esté vacío
        txtNombre.getDocument().addDocumentListener(new DocumentListener() {
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

        // Listener para comprobar que no esté vacío
        txtApellidos.getDocument().addDocumentListener(new DocumentListener() {
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

        // Listener para el combo del departamento
        comboDepartamento.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                comprobarCampos();
            }
        });

        // ActionListener para cambiar el color del checkboxpersonalizado
        checkBoxPersonalizado.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (checkBoxPersonalizado.isSelected()) {
                    checkBoxPersonalizado.setBackground(Color.green);
                } else {
                    checkBoxPersonalizado.setBackground(Color.red);
                }
            }
        });

    }

    // Método para comprobar si los campos están completos y habilitar el botón de registro
    private boolean comprobarCampos() {
        // Verificar si los campos están completos y correctos
        if (txtNombre.getText().trim().isEmpty()) {
            estadoComprobante.setText("El nombre es obligatorio");
            return false;
        } else if(txtApellidos.getText().trim().isEmpty()){
            estadoComprobante.setText("Los apellidos son obligatorios");
            return false;
        } else if (comboDepartamento.getSelectedItem() == null) {
            estadoComprobante.setText("El departamento es obligatorio");
            return false;
        } else {
            btnAgregar.setEnabled(true); // Si todo está correcto, habilita el botón de registro
            return true;
        }
    }

    // Añado la información al ArrayList
    private void agregarEmpleado() {
        String departamento = comboDepartamento.getSelectedItem().toString();
        listaEmpleados.add(txtNombre.getText() + " " + txtApellidos.getText() + " - " + departamento);
    }
}
