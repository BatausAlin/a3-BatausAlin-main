package GUI.PanelMasini;

import GUI.TOOLS.RoundedTextField;
import domain.ValidationMasina;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import service.carService;

public class AdaugaMasinaButton extends JPanel {
    carService service;

    ValidationMasina validationMasina;
    private RoundedTextField idField;
    private RoundedTextField marcaField;
    private RoundedTextField modelField;
    private RoundedTextField culoareField;
    private RoundedTextField numarInmatriculareField;

    public AdaugaMasinaButton(carService service) {
        this.service = service;
        this.validationMasina = new ValidationMasina(service);

        setLayout(null); // Niciun layout manager (poziționare manuală)
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        // Calculează coordonatele pentru centrarea titlului pe orizontală
        JLabel titleLabel = new JLabel("Formular adăugare mașină", JLabel.CENTER);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 24)); // Schimbă fontul pentru a-l face mai mare


        Dimension titleSize = titleLabel.getPreferredSize();

        titleLabel.setBounds((screenSize.width - titleSize.width) / 2, 15, titleSize.width, titleSize.height);


        titleLabel.setBounds((screenSize.width - titleSize.width) / 2, 15, titleSize.width, titleSize.height);
        add(titleLabel);

        titleLabel.setBackground(Color.BLACK); // Setează culoarea de fundal
        titleLabel.setForeground(Color.WHITE); // Setează culoarea textului
        // Adaugă etichetele și câmpurile de text manual
        int labelWidth = 150;
        int fieldWidth = 250;
        int height = 25;
        int xLabel = 50;
        int yStart = 150;
        int yGap = 35;
        idField = addLabelAndTextField("ID:", xLabel, yStart, labelWidth, fieldWidth, height);
        marcaField = addLabelAndTextField("MARCA:", xLabel, yStart + yGap, labelWidth, fieldWidth, height);
        modelField = addLabelAndTextField("MODEL:", xLabel, yStart + yGap * 2, labelWidth, fieldWidth, height);
        culoareField = addLabelAndTextField("CULOARE:", xLabel, yStart + yGap * 3, labelWidth, fieldWidth, height);
        numarInmatriculareField = addLabelAndTextField("NUMAR INMATRICULARE:", xLabel, yStart + yGap * 4, labelWidth, fieldWidth, height);


        JButton saveButton = new JButton("Salvează Mașina");
        saveButton.setBounds(xLabel, yStart + yGap * 5, labelWidth + fieldWidth, height);
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // Procesul de salvare
                String id = idField.getText();
                String marca = marcaField.getText();
                String model = modelField.getText();
                String culoare = culoareField.getText();
                String numarInmatriculare = numarInmatriculareField.getText();


//                List<String> validationErrors = ValidationMasina.validate(id, marca, model, culoare, numarInmatriculare);
                List<String> validationErrors = validationMasina.validate(id, marca, model, culoare, numarInmatriculare);

                if (!validationErrors.isEmpty()) {
                    clearErrorLabels();
                    addErrors(validationErrors, xLabel, labelWidth, fieldWidth, height);
                    revalidate();
                    repaint();
                }else {
                    clearErrorLabels();
                    JLabel errorLabel = new JLabel("Masina a fost adaugata cu succes !!!!");
                    errorLabel.setForeground(Color.GREEN);
                    errorLabel.setBackground(Color.GREEN);
                    errorLabel.setBounds(xLabel,360, labelWidth + fieldWidth, height); // Poziționarea manuală
                    add(errorLabel); // Adaugă eticheta pe panou

                    service.addMasina(Integer.parseInt(id), marca, model, culoare, numarInmatriculare);
                }

            }
        });
        add(saveButton);



    }



    private void clearErrorLabels() {
        // Parcurgem toate componentele panoului
        Component[] components = getComponents();
        for (Component component : components) {
            // Verificăm dacă componenta este o etichetă de eroare
            if (component instanceof JLabel && component.getForeground().equals(Color.RED)) {
                remove(component); // Dacă este, o eliminăm
            }
            if (component instanceof JLabel && component.getForeground().equals(Color.GREEN)) {
                remove(component); // Dacă este, o eliminăm
            }
        }
        // După eliminarea etichetelor, revalidate și repaint pentru a actualiza UI-ul
        revalidate();
        repaint();
    }
    private int startY = 360; // Coordonata y de început pentru prima etichetă de eroare
    private int gap = 5; // Spațiul între etichete

    // Această funcție adaugă o etichetă de eroare și actualizează coordonata y
    private void addErrorLabel(String errorMessage, int x, int labelWidth, int fieldWidth, int height) {
        JLabel errorLabel = new JLabel(errorMessage);
        errorLabel.setForeground(Color.RED); // Setează culoarea textului la roșu pentru erori
        errorLabel.setBounds(x, startY, labelWidth + fieldWidth, height); // Poziționarea manuală
        add(errorLabel); // Adaugă eticheta pe panou

        startY += height + gap; // Incrementăm startY pentru a plasa următoarea etichetă mai jos
    }

    // Când vrei să adaugi erorile, folosești bucla for așa:
    public void addErrors(List<String> errorMessages, int x, int labelWidth, int fieldWidth, int height) {
        for (String errorMessage : errorMessages) {
            addErrorLabel(errorMessage, x, labelWidth, fieldWidth, height);
        }
        // După ce ai terminat de adăugat toate erorile, resetează startY la valoarea inițială
        startY = 360; // Resetarea lui startY pentru a putea începe din nou de la poziția inițială pentru următoarele erori
    }
    private RoundedTextField addLabelAndTextField(String labelText, int x, int y, int labelWidth, int fieldWidth, int height) {
        JLabel label = new JLabel(labelText);
        label.setForeground(Color.WHITE);

        RoundedTextField textField = new RoundedTextField(1);
        textField.setBackground(Color.WHITE);
        Font f = new Font("Ariel", Font.BOLD, 18);
        textField.setFont(f);

//        lineBorder = BorderFactory.createLineBorder(currentColor, 5);
//
//        textField.setBorder(lineBorder);

        label.setBounds(x, y, labelWidth, height);
        textField.setBounds(x + labelWidth, y, fieldWidth, height);

        add(label);
        add(textField);

        return textField; // Returnează câmpul de text pentru a-l putea folosi mai târziu
    }


    private void addLabel(String labelText, int x, int y, int labelWidth,int fieldWidth, int height) {
        JLabel label = new JLabel(labelText);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Ariel", Font.BOLD, 15));
        label.setBounds(x, y, 300, height);
        add(label);
    }

}
