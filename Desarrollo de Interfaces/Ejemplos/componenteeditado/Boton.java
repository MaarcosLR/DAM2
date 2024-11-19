package componenteeditado;

import java.awt.*;
import javax.swing.*;

public class Boton extends JButton {
    
    public Boton(String label) {
        super(label);
        Font f = new Font("Serif", Font.BOLD, 16);
        setFont(f);
        setBackground(Color.BLACK);
        setForeground(Color.RED); 
    }
}
