package GUI;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import GUI.PanelChiriasi.PanelChiriasi;
import GUI.PanelInchirieri.PanelInchirieri;
import GUI.PanelMasini.PanelMasini;
import GUI.panelsetari.PanelSetari;
import repository.AppConfig;
import service.*;


public class GUI extends JFrame {
    carService carService;
    chiriasService chiriasService;

    InchirieriService inchirieriService;

    private PanelMasini panelMasini; // Referința la PanelMasini


    AppConfig appConfig;

    private JPanel contentPane;
    private Border lineBorder;
    private Color currentColor = Color.RED;
    private int red = 255;
    private int green = 0;
    private int blue = 0;
    private int increment = 15;
    private JPanel cards;
    public GUI(carService carService, chiriasService chiriasService, InchirieriService inchirieriService){
        this.carService = carService;
        this.chiriasService = chiriasService;
        this.inchirieriService = inchirieriService;


        this.panelMasini = new PanelMasini(carService); // Inițializarea PanelMasini


        // Inițializează interfața utilizatorului
        initializeUI();
        // Activam modul fullscreen
        FullScreen();
    }


    private void FullScreen(){
        try {
            // Setarea ferestrei să fie nedecorată (fără titlu, bordură, butoane)
            setUndecorated(true);

            // Obținerea device-ului grafic implicit (monitorul)
            GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
            System.out.println("Modul fullscreen suportat: " + device.isFullScreenSupported());

            // Verificarea dacă modul fullscreen este suportat
            if (device.isFullScreenSupported()) {
                // Activarea modului fullscreen
                device.setFullScreenWindow(this);
                System.out.println("Modul fullscreen activat.");
            } else {
                System.err.println("Modul fullscreen nu este suportat");
                // Poți gestiona cazul în care modul fullscreen nu este suportat
                setSize(800, 600); // sau orice altă dimensiune
                setVisible(true);
            }
            setDefaultCloseOperation(EXIT_ON_CLOSE);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void initializeUI() {

        // Setează layout manager-ul pentru JFrame
        setLayout(new BorderLayout());

        setBorder();

        SetsColors();

        // Creează meniul de butoane
        JPanel menuPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        menuPanel.setBackground(new Color(20, 20, 20));

        // Adaugă butoanele la meniu
        addButtonToMenu(menuPanel, "Mașini", "cardMasini");
        addButtonToMenu(menuPanel, "Date chiriași", "cardChiriasi");
        addButtonToMenu(menuPanel, "Inchirieri", "cardInchirieri");
        addButtonToMenu(menuPanel, "Settings", "cardSettings");
        // Adaugă meniul la partea de sus a JFrame-ului
        add(menuPanel, BorderLayout.NORTH);

        // Creează card-urile
        cards = new JPanel(new CardLayout());

        // Adaugă diferite card-uri
        cards.add(new PanelMasini(carService), "cardMasini");
        cards.add(new PanelChiriasi(chiriasService), "cardChiriasi");
        cards.add(new PanelInchirieri(carService, chiriasService, inchirieriService),  "cardInchirieri");
        cards.add(new PanelSetari(), "cardSettings");

        // Adaugă panoul de card-uri la centrul JFrame-ului
        add(cards, BorderLayout.CENTER);

    }


    private void addButtonToMenu(JPanel menuPanel, String title, String cardName) {
        JButton button = new JButton(title);

        button.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cardLayout = (CardLayout) cards.getLayout();
                cardLayout.show(cards, cardName);
            }
        });
        menuPanel.add(button);
    }

    private JPanel createCard(String text) {
        JPanel card = new JPanel();
        card.add(new JLabel(text));
        return card;
    }


    private void SetsColors(){
        // Culoarea aplicatiei
        UIManager.put("Panel.background", new Color(20, 20, 20));

        // Setează culoarea de fundal pentru toate butoanele
        //UIManager.put("Button.background", Color.BLACK);
        //UIManager.put("Button.foreground", Color.WHITE);

        // Setează culoarea de fundal pentru toate meniurile
        UIManager.put("Menu.background", Color.DARK_GRAY);
        UIManager.put("Menu.foreground", Color.WHITE);
        UIManager.put("MenuItem.background", Color.DARK_GRAY);
        UIManager.put("MenuItem.foreground", Color.WHITE);
    }






    private void setBorder(){

        contentPane = (JPanel) this.getContentPane();
        lineBorder = BorderFactory.createLineBorder(currentColor, 5);
        contentPane.setBorder(lineBorder);

        // Setează timer-ul pentru a schimba culoarea bordurii la fiecare 100 de milisecunde
        Timer timer = new Timer(100, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Calcularea noii culori
                updateColor();
                // Crearea unei noi borduri cu noua culoare și aplicarea acesteia
                lineBorder = BorderFactory.createLineBorder(currentColor, 5);
                contentPane.setBorder(lineBorder);
            }
        });

        timer.start(); // Pornirea timer-ului
    }


    // Asta este ok, schimba culoarea borderului RGB
    private void updateColor() {
        // Modifică componentele RGB în mod ciclic
        if (red == 255 && blue == 0 && green < 255) {
            green += increment;
            if (green > 255) green = 255;
        } else if (green == 255 && blue == 0 && red > 0) {
            red -= increment;
            if (red < 0) red = 0;
        } else if (red == 0 && green == 255 && blue < 255) {
            blue += increment;
            if (blue > 255) blue = 255;
        } else if (red == 0 && blue == 255 && green > 0) {
            green -= increment;
            if (green < 0) green = 0;
        } else if (green == 0 && blue == 255 && red < 255) {
            red += increment;
            if (red > 255) red = 255;
        } else if (red == 255 && green == 0 && blue > 0) {
            blue -= increment;
            if (blue < 0) blue = 0;
        }

        // Actualizarea culorii curente
        currentColor = new Color(red, green, blue);
    }



}
