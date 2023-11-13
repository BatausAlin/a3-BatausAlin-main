package GUI.PanelInchirieri;

import GUI.TOOLS.CarTableModel;
import GUI.TOOLS.ChiriasTableModel;
import domain.Chirias;
import domain.Masina;
import service.carService;
import service.chiriasService;
import service.InchirieriService;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;

public class AdaugaInchirieriPanel extends JPanel {
    private carService carService;
    private chiriasService chiriasService;
    private InchirieriService inchirieriService;
    private JTable tableMasini;
    private JTable tableChiriasi;
    private JButton btnSave;

    static CarTableModel carTableModel;
    static ChiriasTableModel chiriasTableModel;

    public AdaugaInchirieriPanel(carService carService, chiriasService chiriasService, InchirieriService inchirieriService) {
        this.carService = carService;
        this.chiriasService = chiriasService;
        this.inchirieriService = inchirieriService;
        initializeUI();
    }

//    private void initializeUI() {
//        setLayout(new BorderLayout());
//
//
//        CarTableModel carTableModel = new CarTableModel(carService.getcar());
//        ChiriasTableModel chiriasTableModel = new ChiriasTableModel(chiriasService.getchirias());
//
//        tableMasini = new JTable(carTableModel);
//        tableChiriasi = new JTable(chiriasTableModel);
//
//        btnSave = new JButton("Salvează Închiriere");
//        btnSave.addActionListener(e -> saveSelection());
//
//        add(new JScrollPane(tableMasini), BorderLayout.WEST);
//        add(new JScrollPane(tableChiriasi), BorderLayout.EAST);
//        add(btnSave, BorderLayout.SOUTH);
//
//
//
//        add(new JScrollPane(tableMasini), BorderLayout.WEST);
//        add(new JScrollPane(tableChiriasi), BorderLayout.EAST);
//        add(btnSave, BorderLayout.SOUTH);
//
//    }

    public static void reset(){
        carTableModel.setRepository();
        chiriasTableModel.setRepository();
    }
    private void initializeUI(){
        Font f = new Font("Ariel", Font.BOLD, 14);

         carTableModel = new CarTableModel(carService.getcar(), carService);
         chiriasTableModel = new ChiriasTableModel(chiriasService.getchirias(), chiriasService);


        tableMasini = new JTable(carTableModel);
        tableChiriasi = new JTable(chiriasTableModel);

        tableMasini.setBackground(new Color(20, 20, 20));
        tableMasini.setFillsViewportHeight(true);
        tableMasini.setSelectionBackground(new Color(20, 20, 20));
        tableMasini.setSelectionForeground(Color.MAGENTA);
        tableMasini.setForeground(Color.WHITE);
        tableMasini.setFont(f);

        tableChiriasi.setBackground(new Color(20, 20, 20));
        tableChiriasi.setFillsViewportHeight(true);
        tableChiriasi.setSelectionForeground(Color.MAGENTA);
        tableChiriasi.setSelectionBackground(new Color(20, 20, 20));
        tableChiriasi.setForeground(Color.WHITE);
        tableChiriasi.setFont(f);

        btnSave = new JButton("Salvează Închiriere");
        btnSave.addActionListener(e -> saveSelection());

        // Panou pentru mașini
        JPanel panelMasini = new JPanel(new BorderLayout());
        JLabel masini = new JLabel("Masini");
        masini.setForeground(Color.WHITE);
        masini.setFont(new Font("Serif", Font.BOLD, 24));

        JPanel panelTitluMasini = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelTitluMasini.add(masini);
        panelMasini.add(panelTitluMasini, BorderLayout.NORTH);
        panelMasini.add(new JScrollPane(tableMasini), BorderLayout.CENTER);


        // Panou pentru chiriași
        JPanel panelChiriasi = new JPanel(new BorderLayout());
        JLabel chiriasiLabel = new JLabel("Chiriași");
        chiriasiLabel.setForeground(Color.WHITE);
        chiriasiLabel.setFont(new Font("Serif", Font.BOLD, 24));

        JPanel panelTitluChiriasi = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelTitluChiriasi.add(chiriasiLabel);
        panelChiriasi.add(panelTitluChiriasi, BorderLayout.NORTH);
        panelChiriasi.add(new JScrollPane(tableChiriasi), BorderLayout.CENTER);

        // Panou central
        JPanel panelCentral = new JPanel(new GridLayout(1, 2)); // 1 rând, 2 coloane
        panelCentral.add(panelMasini);
        panelCentral.add(panelChiriasi);

        // Setarea layout-ului și adăugarea panourilor
        setLayout(new BorderLayout());
        add(panelCentral, BorderLayout.CENTER);
        add(btnSave, BorderLayout.SOUTH);

        // Setarea bordurilor
        panelMasini.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        panelChiriasi.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }

    private void saveSelection() {
        int selectedRowMasina = tableMasini.getSelectedRow();
        int selectedRowChirias = tableChiriasi.getSelectedRow();

        if (selectedRowMasina != -1 && selectedRowChirias != -1) {
            // Presupunem că prima coloană conține ID-ul pentru fiecare tabel
            Object masinaID = tableMasini.getValueAt(selectedRowMasina, 0);
            Object chiriasID = tableChiriasi.getValueAt(selectedRowChirias, 0);

            Masina masina = carService.getcar().findById(Integer.parseInt(masinaID.toString()));
            Chirias chirias = chiriasService.getchirias().findById(Integer.parseInt(chiriasID.toString()));

            inchirieriService.addInchiriere(String.valueOf(chirias.getNume()),chirias.getPrenume(),chirias.getCNP(),masina.getMarca(),masina.getModel(),masina.getnumarInmatriculare());
            // Aici ar trebui să adăugați logica pentru a salva o închiriere folosind datele selectate
            // Exemplu: inchirieriService.saveInchiriere(new Inchiriere(...));

            //JOptionPane.showMessageDialog(this, "Închirierea a fost salvată cu succes!");
        } else {
            //JOptionPane.showMessageDialog(this, "Vă rugăm să selectați o mașină și un chiriaș pentru închiriere.");
        }
    }
}
