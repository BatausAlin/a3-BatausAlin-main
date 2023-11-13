    package GUI.PanelChiriasi;

    import domain.ValidationChirias;
    import service.chiriasService;

    import javax.swing.*;


    import GUI.TOOLS.RoundedTextField;


    import javax.swing.*;
    import java.awt.*;
    import java.awt.event.ActionEvent;
    import java.awt.event.ActionListener;
    import java.awt.event.MouseAdapter;
    import java.awt.event.MouseEvent;

    import javax.swing.table.AbstractTableModel;
    import javax.swing.table.DefaultTableCellRenderer;

    import GUI.TOOLS.CarTableModel;
    import GUI.TOOLS.CustomRenderer;

    import java.awt.event.MouseAdapter;
    import java.awt.event.MouseEvent;
    import java.util.List;
    import javax.swing.table.AbstractTableModel;
    import javax.swing.table.DefaultTableCellRenderer;

    public class AdaugaChiriasButton extends JPanel {
        chiriasService service;
        ValidationChirias validationChirias;
        private RoundedTextField idField;
        private RoundedTextField numeField;
        private RoundedTextField prenumeField;
        private RoundedTextField CNPField;



        public AdaugaChiriasButton(chiriasService service){
            this.service = service;
            this.validationChirias = new ValidationChirias(service);
            setLayout(null);
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

            JLabel titleLabel = new JLabel("Formular adaugare chirias");
            titleLabel.setFont(new Font("Serif", Font.BOLD, 24));
            Dimension titleSize = titleLabel.getPreferredSize();

            titleLabel.setBounds((screenSize.width - titleSize.width) / 2, 15, titleSize.width, titleSize.height);


            titleLabel.setBounds((screenSize.width - titleSize.width) / 2, 15, titleSize.width, titleSize.height);
            add(titleLabel);

            titleLabel.setBackground(Color.BLACK); // Setează culoarea de fundal
            titleLabel.setForeground(Color.WHITE); // Setează culoarea textului
            int labelWidth = 150;
            int fieldWidth = 250;
            int height = 25;
            int xLabel = 50;
            int yStart = 150;
            int yGap = 35;
            idField = addLabelAndTextField("ID:", xLabel, yStart, labelWidth, fieldWidth, height);
            numeField = addLabelAndTextField("NUME:", xLabel, yStart + yGap, labelWidth, fieldWidth, height);
            prenumeField = addLabelAndTextField("PRENUME:", xLabel, yStart + yGap * 2, labelWidth, fieldWidth, height);
            CNPField = addLabelAndTextField("CNP:", xLabel, yStart + yGap * 3, labelWidth, fieldWidth, height);


            JButton saveButton = new JButton("Salvează Chiriasul");
            saveButton.setBounds(xLabel, yStart + yGap * 5, labelWidth + fieldWidth, height);

            saveButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Procesul de salvare
                    String id = idField.getText();
                    String nume = numeField.getText();
                    String prenume = prenumeField.getText();
                    String CNP = CNPField.getText();


//                    List<String> validationErrors = ValidationChirias.validate(id, nume, prenume, CNP);
                    List<String> validationErrors = validationChirias.validate(id, nume, prenume, CNP);
                    if (!validationErrors.isEmpty()) {
                        clearErrorLabels();
                        addErrors(validationErrors, xLabel, labelWidth, fieldWidth, height);
                        revalidate();
                        repaint();
                    }else {
                        clearErrorLabels();
                        JLabel errorLabel = new JLabel("Chiriasul a fost adaugata cu succes !!!!");
                        errorLabel.setForeground(Color.GREEN);
                        errorLabel.setBackground(Color.GREEN);
                        errorLabel.setBounds(xLabel,360, labelWidth + fieldWidth, height); // Poziționarea manuală
                        add(errorLabel); // Adaugă eticheta pe panou

                        service.addChirias(Integer.parseInt(id), nume, prenume, CNP);
                    }
                }
            });
            add(saveButton);
        }


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
        private int startY = 360; // Coordonata y de început pentru prima etichetă de eroare
        private int gap = 5; // Spațiul între etichete
        private void addErrorLabel(String errorMessage, int x, int labelWidth, int fieldWidth, int height) {
            JLabel errorLabel = new JLabel(errorMessage);
            errorLabel.setForeground(Color.RED); // Setează culoarea textului la roșu pentru erori
            errorLabel.setBounds(x, startY, labelWidth + fieldWidth, height); // Poziționarea manuală
            add(errorLabel); // Adaugă eticheta pe panou

            startY += height + gap; // Incrementăm startY pentru a plasa următoarea etichetă mai jos
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
    }
