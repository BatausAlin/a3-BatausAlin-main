package GUI.PanelInchirieri;

import GUI.PanelMasini.PanelMasini;
import GUI.TOOLS.CarTableModel;
import GUI.TOOLS.CustomRenderer;
import GUI.TOOLS.InchiriereTableModel;
import domain.Masina;
import repository.Repository;
import service.InchirieriService;
import service.carService;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AfisareInchirieriPanel extends JPanel {
    InchirieriService inchirieriService;

    static InchiriereTableModel tableModel;


    public AfisareInchirieriPanel(InchirieriService inchirieriService) {
        this.inchirieriService = inchirieriService;
//        this.panelMasini = panelMasini;
        initializeUI();
    }

    public static void reset(){
        tableModel.setRepository();
    }

    private void initializeUI() {
        setLayout(new BorderLayout()); // Setează un layout manager pentru acest panel

        // Titlu
        JLabel titleLabel = new JLabel("Lista mașinilor", JLabel.CENTER);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 24)); // Schimbă fontul pentru a-l face mai mare
        titleLabel.setForeground(Color.WHITE); // Setează culoarea textului
        titleLabel.setOpaque(true);
        titleLabel.setBackground(new Color(20,20,20)); // Setează culoarea de fundal
        titleLabel.setPreferredSize(new Dimension(1920, 50)); // Setează dimensiunea preferată pentru titlu


        // Tabel
        tableModel = new InchiriereTableModel(inchirieriService.getInchiriere(), inchirieriService); // Presupunând că `service.getcar()` returnează Repository<Masina>
        JTable table = new JTable(tableModel);
        table.setFillsViewportHeight(true); // Acest lucru va asigura că tabelul folosește întregul viewport
        CustomRenderer customRenderer = new CustomRenderer();
        table.setDefaultRenderer(Object.class, customRenderer);

        table.setSelectionForeground(Color.MAGENTA);
        table.setSelectionBackground(new Color(20,20,20));

        table.setBackground(new Color(20,20,20));
        table.setForeground(Color.WHITE);
        Font f = new Font("Ariel", Font.BOLD, 14);
        table.setFont(f);


        // Setează renderer-ul pentru centrarea textului în celule
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int columnIndex = 0; columnIndex < table.getColumnCount(); columnIndex++) {
            table.getColumnModel().getColumn(columnIndex).setCellRenderer(centerRenderer);
        }

        // Scroll Pane
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.getViewport().setBackground(new Color(20,20,20)); // Setează culoarea de fundal pentru viewport

        // Adaugă componente la panel
        add(titleLabel, BorderLayout.NORTH); // Adaugă titlul în partea de sus
        add(scrollPane, BorderLayout.CENTER); // Adaugă scrollPane în centru


        JPopupMenu popupMenu = new JPopupMenu();

// Adaugă opțiuni la meniul popup
        JMenuItem deleteItem = new JMenuItem("Șterge");
        deleteItem.addActionListener(e -> {
            // Acțiunea de ștergere
//            int row = table.getSelectedRow();
//            if (row >= 0) {
//                // Obține ID-ul sau cheia primară a înregistrării selectate
//                int id = (Integer) table.getModel().getValueAt(row, 0);
//                // Apelarea metodei de ștergere din serviciul sau repository-ul tău
//                service.deleteCar(id);
//                // Actualizarea modelului de tabel
//                ((AbstractTableModel) table.getModel()).fireTableDataChanged();
//            }

            int row = table.getSelectedRow();
            if (row >= 0) {
                table.getModel().getRowCount();
                int modelRow = table.convertRowIndexToModel(row);
                int id = (Integer) table.getModel().getValueAt(modelRow, 0);
                inchirieriService.deleteInchiriere(id);
                ((AbstractTableModel) table.getModel()).fireTableRowsDeleted(modelRow, modelRow);

            }
        });
        popupMenu.add(deleteItem);

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                // Verifică dacă a fost click dreapta
                if (e.isPopupTrigger()) {
                    // Obține rândul la poziția cursorului
                    int r = table.rowAtPoint(e.getPoint());
                    if (r >= 0 && r < table.getRowCount()) {
                        table.setRowSelectionInterval(r, r);
                    } else {
                        table.clearSelection();
                    }

                    // Obține coordonatele cursorului și afișează meniul popup
                    int rowindex = table.getSelectedRow();
                    if (rowindex < 0)
                        return;
                    if (e.isPopupTrigger() && e.getComponent() instanceof JTable ) {
                        popupMenu.show(e.getComponent(), e.getX(), e.getY());
                    }
                }
            }
        });

    }
}
