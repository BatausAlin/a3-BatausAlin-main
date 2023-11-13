package GUI.PanelChiriasi;

import GUI.PanelChiriasi.AdaugaChiriasButton;
import GUI.PanelChiriasi.AfisareListaChiriasiButton;
import GUI.PanelMasini.AfisareListaMasiniButton;
import GUI.PanelMasini.PanelMasini;
import service.chiriasService;

import javax.swing.*;
import java.awt.*;

import javax.swing.*;
import java.awt.*;

public class PanelChiriasi extends JPanel {
    chiriasService service;



    private CardLayout cardLayout = new CardLayout();
    private JPanel cards = new JPanel(cardLayout);
    private AdaugaChiriasButton adaugaChiriasPanel; // Clasa care gestionează panoul de adăugare
    private AfisareListaChiriasiButton listaChiriasiPanel; // Clasa care gestionează panoul cu lista



    public PanelChiriasi(chiriasService service) {
        this.service = service;
        setLayout(new BorderLayout());
        initializeUI();
    }


    private void initializeUI() {

        JButton btnAdaugaChirias = new JButton("Adaugă Chirias");
        JButton btnAfisareListaChiriasi = new JButton("Afișare Lista Chiriasi");

        // Adăugarea butoanelor la un panou
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(btnAdaugaChirias);
        buttonPanel.add(btnAfisareListaChiriasi);
        add(buttonPanel, BorderLayout.NORTH);

        // Inițializare panouri
        adaugaChiriasPanel = new AdaugaChiriasButton(service);
        listaChiriasiPanel = new AfisareListaChiriasiButton(service);

        // Adăugarea panourilor la carduri
        cards.add(adaugaChiriasPanel, "AdaugaChirias");
        cards.add(listaChiriasiPanel, "ListaChiriasi");

        // Adăugarea panoului de carduri la panoul principal
        add(cards, BorderLayout.CENTER);

        // Legătura butoanelor la acțiuni
        btnAdaugaChirias.addActionListener(e -> showCard("AdaugaChirias"));
        btnAfisareListaChiriasi.addActionListener(e -> {
            AfisareListaChiriasiButton.reset();
            showCard("ListaChiriasi");
        });
    }

    private void showCard(String listaMasini) {
        cardLayout.show(cards, listaMasini);
    }

}
