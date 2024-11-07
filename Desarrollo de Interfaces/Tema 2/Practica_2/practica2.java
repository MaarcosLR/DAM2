//GUÍA PARA EL USUARIO
//Cuando el ratón pasa por encima del campo de texto, en el area de texto inferior aparecerá
//un texto que indica que ha salido o ha entrado del campo de texto.
//Al escribir en el campo de texto, en el área de texto aparecerá qué tecla fue presionada
//como un registro de acciones, así en resumen.
//Después de escribir, al clicar el botón, se borra lo que haya escrito automáticamente.

package practica2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class practica2 extends JFrame implements KeyListener, MouseListener, EventListener, FocusListener{
	
	private JPanel genpanel;
	private JPanel toppanel;
	private JPanel botpanel;
	private JLabel label;
	private JTextField campotxt;
	private JButton boton;
	private JTextArea areatxt;
	
    public practica2() {
    	
        // Configurar el JFrame
        setTitle("Práctica 2 UT2.1");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        setResizable(false);
        setLocationRelativeTo(null);
        
        //Crear panel general
        genpanel = new JPanel();
        genpanel.setLayout(new BoxLayout(genpanel, BoxLayout.Y_AXIS));
        
        //Crear el label
        label = new JLabel();
        label.setText("Campo de texto: ");

        //Crear el campo de texto y añadir los eventos al mismo
        campotxt = new JTextField(15);
        campotxt.addKeyListener(this);
        campotxt.addMouseListener(this);
        campotxt.addFocusListener(this);
        
        //Crear el botón con un listener que indique que el botón fue presionado
        //y se borre todo lo que haya escrito en el campo de texto
        boton = new JButton("Clic aquí");
        boton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				areatxt.append("Botón clicado. \n");
				campotxt.setText("");
				
			}
		});
        
        //Crear el panel superior
        toppanel = new JPanel();
        
        //Añadir componentes al panel superior
        toppanel.add(label);
        toppanel.add(campotxt);
        toppanel.add(boton);
        
        //Crear el panel que contenga el areatxt
        botpanel = new JPanel();
        
        //Crear el area de texto con scroll
        areatxt = new JTextArea(15,30);
        areatxt.setEditable(false);
        JScrollPane scroll = new JScrollPane(areatxt);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        
        //Añadir al panel
        botpanel.add(scroll);
        
        //Añadir panel general que contiene los otros 2 paneles al frame
        add(genpanel);
        
        //Añadir paneles al panel principal
        genpanel.add(toppanel);
        genpanel.add(botpanel);
        
        // Hacer visible la ventana
        setVisible(true);
    }

	@Override
	public void mouseEntered(MouseEvent e) {
		areatxt.append("Mouse entró en el área de texto. \n");
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		areatxt.append("Mouse salió del área de texto. \n");
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		areatxt.append("Tecla persionada: " + KeyEvent.getKeyText(e.getKeyCode()) + "\n");
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		areatxt.append("Tecla liberada: " + KeyEvent.getKeyText(e.getKeyCode()) + "\n");
		
	}

	@Override
	public void focusGained(FocusEvent e) {
		areatxt.append("Campo de texto ha ganado el foco. \n");
		
	}

	@Override
	public void focusLost(FocusEvent e) {
		areatxt.append("Campo de texto ha perdido el foco. \n");
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// NO USADO
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// NO USADO
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// NO USADO
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// NO USADO
		
	}
	
	public static void main(String[] args) {
        // Crear la ventana del ejemplo
        new practica2();
    }
}
