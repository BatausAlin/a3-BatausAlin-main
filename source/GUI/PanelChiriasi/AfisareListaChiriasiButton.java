package GUI.PanelChiriasi;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;

import GUI.TOOLS.*;
import service.chiriasService;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AfisareListaChiriasiButton extends JPanel {
    chiriasService service;
    static ChiriasTableModel tableModel;

    public AfisareListaChiriasiButton( chiriasService service){
        this.service = service;
        initializeUI();
    }

    public static void reset(){
        tableModel.setRepository();
    }

    private void initializeUI(){
        setLayout(new BorderLayout()); // Setează un layout manager pentru acest panel
        JLabel titleLabel = new JLabel("Lista chiriasilor", JLabel.CENTER);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 24)); // Schimbă fontul pentru a-l face mai mare
        titleLabel.setForeground(Color.WHITE); // Setează culoarea textului
        titleLabel.setOpaque(true);
        titleLabel.setBackground(new Color(20,20,20)); // Setează culoarea de fundal
        titleLabel.setPreferredSize(new Dimension(1920, 50)); // Setează dimensiunea preferată pentru titlu


        tableModel = new ChiriasTableModel(service.getchirias(), service); // Presupunând că `service.getcar()` returnează Repository<Masina>


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


        JMenuItem deleteItem = new JMenuItem("Șterge");
        deleteItem.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row >= 0) {
                table.getModel().getRowCount();
                int modelRow = table.convertRowIndexToModel(row);
                int id = (Integer) table.getModel().getValueAt(modelRow, 0);

                ConfirmationAction action = new ConfirmationAction() {
                    @Override
                    public void onConfirm() {
                        System.out.println("A ajuns aici dar degeaba");
                        // Serviciul de ștergere este apelat pentru a elimina chiriașul din baza de date sau din lista internă.
                        service.deleteChirias(id);

                        // Actualizăm modelul tabelului pentru a reflecta ștergerea.
                        ((AbstractTableModel) table.getModel()).fireTableRowsDeleted(modelRow, modelRow);
                        System.out.println("A ajuns si aici dar tot degeaba");

                    }

                    @Override
                    public void onCancel() {
                        // Logica pentru anulare dacă este necesar
                    }
                };

                JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(AfisareListaChiriasiButton.this);
                ConfirmationOverlay overlay = new ConfirmationOverlay(topFrame, action);
                topFrame.setGlassPane(overlay);
                overlay.setVisible(true);
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
