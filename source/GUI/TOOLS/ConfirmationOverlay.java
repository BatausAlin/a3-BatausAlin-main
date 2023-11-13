package GUI.TOOLS;

import service.chiriasService;

import javax.swing.*;
import java.awt.*;


// Clasa care reprezintă overlay-ul de confirmare
public class ConfirmationOverlay extends JComponent {
    private final JFrame frame;

    private ConfirmationAction action;

    chiriasService service;


    public ConfirmationOverlay(JFrame frame, ConfirmationAction action) {
        this.frame = frame;
        this.action = action;
        setLayout(new GridBagLayout()); // Folosiți GridBagLayout pentru a poziționa și a redimensiona componentele
        setOpaque(false);

        // Creează constrângerile pentru GridBagLayout
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER; // Componentele următoare vor fi pe următoarea linie
        gbc.anchor = GridBagConstraints.CENTER; // Ancorează componentele la centrul spațiului disponibil
        gbc.insets = new Insets(10, 0, 10, 0); // Adaugă spațiu în jurul componentelor

        // Mesajul de confirmare
        JLabel messageLabel = new JLabel("Ești sigur că vrei să ștergi această mașină?");
        messageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        messageLabel.setOpaque(true);
        messageLabel.setBackground(new Color(0, 0, 0, 150)); // Fundal semi-transparent
        messageLabel.setForeground(Color.WHITE);
        messageLabel.setPreferredSize(new Dimension(300, 50)); // Setează dimensiunea preferată
        add(messageLabel, gbc);

        JButton yesButton = new JButton("Da");
        yesButton.setPreferredSize(new Dimension(150, 50)); // Mărește butonul "Da"
        yesButton.addActionListener(e -> {
            action.onConfirm(); // Apelăm onConfirm din obiectul action transmis
            frame.getGlassPane().setVisible(false);
            frame.remove(this);
        });
        add(yesButton, gbc);

        // Butonul "Nu"
        JButton noButton = new JButton("Nu");
        noButton.setPreferredSize(new Dimension(150, 50)); // Mărește butonul "Nu"
        noButton.addActionListener(e -> {
            action.onCancel(); // Apelăm onCancel din obiectul action transmis
            frame.getGlassPane().setVisible(false);
            frame.remove(this);
        });
        add(noButton, gbc);

        // Asigurați-vă că overlay-ul este vizibil și poate interacționa cu utilizatorul
        setVisible(true);
        setFocusable(true);
    }

    private void onConfirm() {
        // Logică pentru confirmare
        System.out.println("Confirmat!");

        frame.getGlassPane().setVisible(false);
        frame.remove(this);

    }

    private void onCancel() {
        // Logică pentru anulare
        frame.getGlassPane().setVisible(false);
        frame.remove(this);
    }

    @Override
    protected void paintComponent(Graphics g) {
        // Desenează overlay-ul
        super.paintComponent(g);
        g.setColor(new Color(0, 0, 0, 100));
        g.fillRect(0, 0, getWidth(), getHeight());
    }
}
