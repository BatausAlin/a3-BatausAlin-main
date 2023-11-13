package GUI.PanelMasini;

import javax.swing.*;
import java.awt.*;

import GUI.TOOLS.CarTableModel;
import service.carService;

public class PanelMasini extends JPanel {
    carService service;


    private CardLayout cardLayout = new CardLayout();
    private JPanel cards = new JPanel(cardLayout);
    private AdaugaMasinaButton adaugaMasinaPanel; // Clasa care gestionează panoul de adăugare
    private AfisareListaMasiniButton listaMasiniPanel; // Clasa care gestionează panoul cu lista

    public PanelMasini(carService service) {
        this.service = service;

        setLayout(new BorderLayout());
        initializeUI();
    }

    private void initializeUI() {



        // Inițializare butoane
        JButton btnAdaugaMasina = new JButton("Adaugă Mașină");
        JButton btnAfisareListaMasini = new JButton("Afișare Lista Mașini");

        // Adăugarea butoanelor la un panou
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(btnAdaugaMasina);
        buttonPanel.add(btnAfisareListaMasini);
        add(buttonPanel, BorderLayout.NORTH);

        // Inițializare panouri
        adaugaMasinaPanel = new AdaugaMasinaButton( service);
        listaMasiniPanel = new AfisareListaMasiniButton(this, service);

        // Adăugarea panourilor la carduri
        cards.add(adaugaMasinaPanel, "AdaugaMasina");
        cards.add(listaMasiniPanel, "ListaMasini");

        // Adăugarea panoului de carduri la panoul principal
        add(cards, BorderLayout.CENTER);

        // Legătura butoanelor la acțiuni
        btnAdaugaMasina.addActionListener(e -> showCard("AdaugaMasina"));
        btnAfisareListaMasini.addActionListener(e -> {
            AfisareListaMasiniButton.reset();
            showCard("ListaMasini");

        });
    }
    public void showCard(String cardName) {
        cardLayout.show(cards, cardName);
    }
}
