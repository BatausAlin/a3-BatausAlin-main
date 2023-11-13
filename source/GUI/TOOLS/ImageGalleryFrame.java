package GUI.TOOLS;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


import java.util.List;
import java.util.ArrayList;


public class ImageGalleryFrame extends JFrame {

    private List<File> files = new ArrayList<>();

    private JLabel imageLabel;
    private List<ImageIcon> images;
    private int currentIndex = 0;

    public ImageGalleryFrame(List<ImageIcon> images) {
        super("Image Gallery");
        this.images = images;
        initializeUI();
    }

    private void initializeUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1920, 1080);
        setLayout(new BorderLayout());

        imageLabel = new JLabel();
        imageLabel.setHorizontalAlignment(JLabel.CENTER);
        imageLabel.setVerticalAlignment(JLabel.CENTER);

        JButton prevButton = new JButton("Previous");
        JButton nextButton = new JButton("Next");
        JButton exitButton = new JButton("Exit");

        JPanel controlPanel = new JPanel(new FlowLayout());
        controlPanel.add(prevButton);
        controlPanel.add(nextButton);
        controlPanel.add(exitButton);

        add(imageLabel, BorderLayout.CENTER);
        add(controlPanel, BorderLayout.SOUTH);

        prevButton.addActionListener(e -> showPreviousImage());
        nextButton.addActionListener(e -> showNextImage());

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0); // 0 indică o închidere normală
            }
        });






        JButton deleteButton = new JButton("Șterge Imagine");

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentIndex >= 0 && currentIndex < images.size()) {
                    // Confirmare înainte de ștergere
                    int confirm = JOptionPane.showConfirmDialog(
                            ImageGalleryFrame.this,
                            "Ești sigur că vrei să ștergi această imagine?",
                            "Confirmare Ștergere",
                            JOptionPane.YES_NO_OPTION
                    );
                    if (confirm == JOptionPane.YES_OPTION) {


                        File dir = new File("carImages/");
                        File[] fileArray = dir.listFiles((d, name) -> name.matches(".*\\.(jpg|png|jpeg|gif|bmp)$"));
                        List<ImageIcon> images = new ArrayList<>();
                        if (fileArray != null) {
                            for (File file : fileArray) {
                                ImageIcon icon = new ImageIcon(file.getAbsolutePath());
                                images.add(icon);
                                // Adaugă fișierul la lista de fișiere
                                files.add(file);
                            }
                        }


                        File toDelete = files.get(currentIndex); // Presupunând că ai o listă de File numită files
                        if (toDelete.delete()) { // Încearcă să ștergi fișierul
                            images.remove(currentIndex); // Șterge din lista de imagini
                            if (images.size() == 0) { // Dacă nu mai sunt imagini, închide galeria
                                System.exit(0);
                            } else { // Altfel, arată următoarea imagine
                                if (currentIndex >= images.size()) {
                                    currentIndex = images.size() - 1;
                                }
                                updateImage();
                            }
                        } else {
                            JOptionPane.showMessageDialog(
                                    ImageGalleryFrame.this,
                                    "Nu s-a putut șterge imaginea.",
                                    "Eroare",
                                    JOptionPane.ERROR_MESSAGE
                            );
                        }
                    }
                }
            }
        });








        // Set keyboard focus manager to listen for arrow key presses
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(new KeyEventDispatcher() {
            @Override
            public boolean dispatchKeyEvent(KeyEvent e) {
                if (e.getID() == KeyEvent.KEY_PRESSED) {
                    if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                        showPreviousImage();
                    } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                        showNextImage();
                    }
                }
                return false; // Allow the key event to be dispatched to other components
            }
        });

        // Show the first image
        updateImage();
    }

    private void showPreviousImage() {
        if (currentIndex > 0) {
            currentIndex--;
            updateImage();
        }
    }

    private void showNextImage() {
        if (currentIndex < images.size() - 1) {
            currentIndex++;
            updateImage();
        }
    }


    private void updateImage() {
        ImageIcon icon = images.get(currentIndex);
        imageLabel.setIcon(icon);
        setTitle(String.format("Image Gallery (%d/%d)", currentIndex + 1, images.size()));
    }
}
