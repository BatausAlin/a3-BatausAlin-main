package GUI;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumnModel;
import java.awt.*;

import java.awt.Frame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import GUI.TOOLS.CarTableModel;
import domain.ValidationMasina;

import java.util.List;

import service.carService;




public class BackUpMainGui {

    public static void backupgui(carService service){
        JTable carTable = new JTable(new CarTableModel(service.getcar(), service));
        CarTableModel carTableModel = new CarTableModel(service.getcar(), service);

        Frame frame = new JFrame("Sistem de Gestiune Mașini");
        ((JFrame) frame).setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(700, 400);
        frame.setBackground(Color.green);



        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 2, 5, 5)); // 3 rânduri, 1 coloană, spațiere între butoane

        // Crearea butoanelor
        JButton addMasinaButton = new JButton("Adaugă Mașină");
        JButton deleteMasinaButton = new JButton("Șterge Mașină");
        JButton modifyMasinaButton = new JButton("Modifică Mașină");
        JButton listaMasiniButton = new JButton("Listă Mașinî");

        addMasinaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Logica pentru adăugarea unei mașini
                JTextField IDField = new JTextField(10);
                JTextField marcaField = new JTextField(10);
                JTextField modelField = new JTextField(10);
                JTextField culoareField = new JTextField(10);
                JTextField numarInmatriculareField = new JTextField(10);
                // Pe viitor mai multe aici ....

                JPanel panel = new JPanel();
                panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));


                // LABELS
                {
                    panel.add(new JLabel("ID:"));
                    panel.add(IDField);
                    panel.add(Box.createRigidArea(new Dimension(0, 5))); // Adaugă un spațiu între componente

                    panel.add(new JLabel("Marca:"));
                    panel.add(marcaField);
                    panel.add(Box.createRigidArea(new Dimension(0, 5))); // Adaugă un spațiu între componente

                    panel.add(new JLabel("Model:"));
                    panel.add(modelField);
                    panel.add(Box.createRigidArea(new Dimension(0, 5))); // Adaugă un spațiu între componente

                    panel.add(new JLabel("Culoare:"));
                    panel.add(culoareField);
                    panel.add(Box.createRigidArea(new Dimension(0, 5))); // Adaugă un spațiu între componente

                    panel.add(new JLabel("Numar matriculare:"));
                    panel.add(numarInmatriculareField);
                }

                int result = JOptionPane.showConfirmDialog(null, panel,
                        "Introduceți detaliile mașinii", JOptionPane.OK_CANCEL_OPTION);
                if (result == JOptionPane.OK_OPTION) {

                    String idStr = IDField.getText();
                    String marca = marcaField.getText();
                    String model = modelField.getText();
                    String culoare = culoareField.getText();
                    String numarInmatriculare = numarInmatriculareField.getText();

                    List<String> validationErrors = ValidationMasina.validate(idStr, marca, model, culoare, numarInmatriculare);

                    if (!validationErrors.isEmpty()) {
                        String errorMessage = String.join("\n", validationErrors);
                        JOptionPane.showMessageDialog(null, errorMessage, "Eroare de Validare", JOptionPane.ERROR_MESSAGE);
                    } else {
                        service.addMasina(Integer.parseInt(idStr), marca, model, culoare, numarInmatriculare);
                        JOptionPane.showMessageDialog(null, "Mașină adăugată cu succes!");
                    }
                }
            }
        });

        deleteMasinaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JTable carTable = new JTable(new CarTableModel(service.getcar(), service));
                JScrollPane scrollPane = new JScrollPane(carTable); // Încapsularea tabelului într-un JScrollPane
                carTable.setFillsViewportHeight(true);

// Acum trebuie să adăugăm scrollPane la containerul tău principal, fie el un JFrame sau un JDialog.
// Presupunând că folosești un JFrame ca fereastră principală, așa arată codul:
                frame.add(scrollPane); // Adaugă scrollPane la frame

                frame.pack(); // Acesta va face ca fereastra să se dimensioneze pentru a se potrivi cu conținutul său.
                frame.setVisible(true); // Face fereastra vizibilă.

                int selectedRow = carTable.getSelectedRow();
                if (selectedRow >= 0) { // Verifică dacă un rând este selectat
                    int decision = JOptionPane.showConfirmDialog(
                            frame,
                            "Ești sigur că vrei să ștergi mașina selectată?",
                            "Confirmare ștergere",
                            JOptionPane.YES_NO_OPTION
                    );
                    if (decision == JOptionPane.YES_OPTION) {
                        // Șterge mașina din modelul tabelului
                        //((CarTableModel) carTable.getModel()).removeRow(selectedRow);
                    }
                } else {
                    JOptionPane.showMessageDialog(
                            frame,
                            "Nicio mașină selectată pentru ștergere!",
                            "Eroare",
                            JOptionPane.ERROR_MESSAGE
                    );
                }
            }
        });



        // Momentan afiseaza super bine
        listaMasiniButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CarTableModel carTableModel = new CarTableModel(service.getcar(), service);
                JTable table = new JTable(carTableModel);
                DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
                centerRenderer.setHorizontalAlignment(JLabel.CENTER);

                // Afiseaza datele din tabel centrate
                int columnCount = table.getColumnCount();
                for (int i = 0; i < columnCount; i++) {
                    table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);

                }
                // Nu permite sa poti schimba lungimea coloanelor
                TableColumnModel columnModel = table.getColumnModel();
                for (int i = 0; i < columnModel.getColumnCount(); i++) {
                    columnModel.getColumn(i).setResizable(false);
                }


                JScrollPane scrollPane = new JScrollPane(table);
                table.setFillsViewportHeight(true);
                table.getTableHeader().setReorderingAllowed(false);
                table.setBackground(Color.black);
                table.setForeground(Color.green);
                table.setSelectionBackground(Color.DARK_GRAY);
                table.setSelectionForeground(Color.MAGENTA);


                JDialog dialog = new JDialog(frame, "Lista Mașini", true);
                dialog.getContentPane().add(scrollPane, BorderLayout.CENTER);
                dialog.setSize(900, 300);
                dialog.setLocationRelativeTo(frame);
                dialog.setVisible(true);


            }
        });

        panel.add(addMasinaButton);
        panel.add(deleteMasinaButton);
        panel.add(modifyMasinaButton);
        panel.add(listaMasiniButton);

        // Adăugarea panel-ului la frame
        frame.add(panel);

        // Centrarea ferestrei pe ecran și afișarea ei
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
