package GUI.panelsetari;

import repository.AppConfig;
import repository.Repository;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelSetari extends JPanel {
    private JComboBox<String> storageOptions;
    private JTextField serverTextField;
    private JButton findButton;

    private AppConfig appConfig;
    Repository<?> repository;


    public PanelSetari() {

        appConfig = new AppConfig();

        // Setarea fundalului și a fontului pentru panel
//        setBackground(Color.DARK_GRAY); // Schimbă culoarea de fundal după preferințe
        Font font = new Font("Arial", Font.BOLD, 30);

        // Crearea JComboBox și adăugarea opțiunilor
        String[] options = {"Memory", "BinaryFile", "SSMS"};
        storageOptions = new JComboBox<>(options);
        storageOptions.setFont(font);
        storageOptions.setForeground(Color.WHITE); // Schimbă culoarea textului în JComboBox
        storageOptions.setBackground(Color.DARK_GRAY); // Schimbă culoarea de fundal în JComboBox

        // Adăugarea unui ascultător de evenimente pentru JComboBox
        storageOptions.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedOption = (String) storageOptions.getSelectedItem();
                handleStorageSelection(selectedOption);

            }
        });

        // Crearea și adăugarea etichetei
        JLabel label = new JLabel("Cum vrei să se salveze datele?");
        label.setFont(font);
        label.setForeground(Color.WHITE); // Setarea culorii textului în alb

        // Setarea layout-ului și adăugarea unui spațiu suplimentar în partea de sus
        setLayout(new FlowLayout(FlowLayout.LEFT));
        setBorder(BorderFactory.createEmptyBorder(40, 0, 0, 0)); // 40 pixeli spațiu sus

        // Adăugarea etichetei și JComboBox la panel
        add(label);
        add(storageOptions);serverTextField = new JTextField(20);
        serverTextField.setFont(font);
        serverTextField.setForeground(Color.WHITE); // Schimbă culoarea textului în JComboBox
        serverTextField.setBackground(Color.DARK_GRAY); // Schimbă culoarea de fundal în JComboBox

        serverTextField.setVisible(false); // Inițial nu este vizibil

        // Crearea JButton pentru căutarea serverului
        findButton = new JButton("FIND");
        findButton.setFont(font);
        findButton.setForeground(Color.WHITE); // Schimbă culoarea textului în JComboBox
        findButton.setBackground(Color.DARK_GRAY);
        findButton.setVisible(false); // Inițial nu este vizibil
        findButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //checkDatabaseConnection(serverTextField.getText());
            }
        });

        // Adăugarea JTextField și JButton la panel
        add(serverTextField);
        add(findButton);
    }

    private void handleStorageSelection(String selectedOption) {
        switch (selectedOption) {
            case "Memory":
                // Logica pentru Memory
                serverTextField.setVisible(false);
                findButton.setVisible(false);
                appConfig.setCurrentStorageType(AppConfig.StorageType.MEMORY);
                System.out.println("Am trecut la " + selectedOption);
                break;
            case "BinaryFile":
                // Logica pentru BinaryFile
                serverTextField.setVisible(false);
                findButton.setVisible(false);
                appConfig.setCurrentStorageType(AppConfig.StorageType.BINARY_FILE);
                System.out.println("Am trecut la " + selectedOption);

                break;
            case "SSMS":
                // Logica pentru SSMS
                serverTextField.setVisible(true);
                findButton.setVisible(true);
                // Aici puteți adăuga logica pentru configurarea conexiunii la baza de date SQL Server
                break;
        }
    }

}

