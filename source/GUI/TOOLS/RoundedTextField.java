package GUI.TOOLS;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;


public class RoundedTextField extends JTextField {
    private Shape shape;
    private Color borderColor = Color.red; // culoarea implicită pentru bordură

    public RoundedTextField(int size) {
        super(size);
        setOpaque(false); // Facem câmpul de text transparent
        setHorizontalAlignment(JTextField.CENTER);

        // Alte inițializări dacă este necesar
    }

    protected void paintComponent(Graphics g) {
        // Desenează un fundal alb cu colțuri rotunjite
        g.setColor(getBackground());
        g.fillRoundRect(0, 0, getWidth()-1, getHeight()-1, 25, 25);
        super.paintComponent(g);
    }

    protected void paintBorder(Graphics g) {
        // Desenează o bordură cu colțuri rotunjite
//        g.setColor(borderColor); // folosește culoarea setată pentru bordură
//        g.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, 17, 17);\
        Graphics2D g2 = (Graphics2D) g;
        int thickness = 2; // Setează grosimea dorită aici
        g2.setStroke(new BasicStroke(thickness));
        g2.setColor(borderColor); // folosește culoarea setată pentru bordură
        g2.drawRoundRect(thickness / 2, thickness / 2, getWidth() - thickness, getHeight() - thickness, 25, 25);

    }

    public boolean contains(int x, int y) {
        // Verifică dacă punctul dat este în interiorul colțurilor rotunjite
        if (shape == null || !shape.getBounds().equals(getBounds())) {
            shape = new RoundRectangle2D.Float(0, 0, getWidth()-1, getHeight()-1, 25, 25);
        }
        return shape.contains(x, y);
    }

    // Setter pentru culoarea bordurii
    public void setBorderColor(Color color) {
        borderColor = color;
        repaint(); // re-desenează componenta cu noua culoare
    }
}