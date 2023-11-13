package GUI.PanelMasini;

import GUI.PanelChiriasi.AfisareListaChiriasiButton;
import GUI.TOOLS.CarTableModel;
import GUI.TOOLS.ConfirmationAction;
import GUI.TOOLS.ConfirmationOverlay;
import GUI.TOOLS.CustomRenderer;
import domain.Masina;
import jdk.jfr.Experimental;
import repository.AppConfig;
import repository.Repository;
import service.carService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableRowSorter;



public class AfisareListaMasiniButton extends JPanel {
    carService service;
    static CarTableModel tableModel;
    public AfisareListaMasiniButton(PanelMasini panelMasini, carService service) {
        this.service = service;
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
        titleLabel.setBackground(new Color(20, 20, 20)); // Setează culoarea de fundal
        titleLabel.setPreferredSize(new Dimension(1920, 50)); // Setează dimensiunea preferată pentru titlu



        tableModel = new CarTableModel(service.getcar(), service); // Presupunând că `service.getcar()` returnează Repository<Masina>


        JTable table = new JTable(tableModel); // Inițializați JTable aici cu modelul de tabel
        table.setFillsViewportHeight(true); // Acest lucru va asigura că tabelul folosește întregul viewport
        CustomRenderer customRenderer = new CustomRenderer();
        table.setDefaultRenderer(Object.class, customRenderer);

        table.setSelectionForeground(Color.MAGENTA);
        table.setSelectionBackground(new Color(20, 20, 20));

        table.setBackground(new Color(20, 20, 20));
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
        scrollPane.getViewport().setBackground(new Color(20, 20, 20)); // Setează culoarea de fundal pentru viewport

        // Adaugă componente la panel
        add(titleLabel, BorderLayout.CENTER); // Adaugă titlul în partea de sus
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
                        service.deleteCar(id);

                        // Actualizăm modelul tabelului pentru a reflecta ștergerea.
                        ((AbstractTableModel) table.getModel()).fireTableRowsDeleted(modelRow, modelRow);
                        System.out.println("A ajuns si aici dar tot degeaba");

                    }

                    @Override
                    public void onCancel() {
                        // Logica pentru anulare dacă este necesar
                    }
                };

                JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(AfisareListaMasiniButton.this);
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
                    if (e.isPopupTrigger() && e.getComponent() instanceof JTable) {
                        popupMenu.show(e.getComponent(), e.getX(), e.getY());
                    }
                }
//                tableModel.setRepository();
            }

        });



    }

}
