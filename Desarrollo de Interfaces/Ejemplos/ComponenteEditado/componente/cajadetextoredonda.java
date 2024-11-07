package componente;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class cajadetextoredonda extends JTextField {
    
    // Constructor correcto con el mismo nombre que la clase
    public cajadetextoredonda() {
        // Configuración del área de texto
        Font f = new Font("Serif", Font.BOLD, 16);
        setFont(f);
        setBackground(Color.BLACK);
        setForeground(Color.RED); 
        setBorder(new EmptyBorder(10, 10, 10, 10)); // Espaciado interno
        setOpaque(false); // Hacer que el área de texto no pinte su fondo
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        
        // Mejorar la calidad de los bordes
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Dibujar un fondo redondeado
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20); // Ajusta 20 para más o menos curvatura

        // Dibujar el área de texto
        super.paintComponent(g);
        
        g2.dispose();
    }
}
