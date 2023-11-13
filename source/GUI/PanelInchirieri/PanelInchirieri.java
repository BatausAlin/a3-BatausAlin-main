
package GUI.PanelInchirieri;

import GUI.PanelMasini.AdaugaMasinaButton;
import service.InchirieriService;

import javax.swing.*;
import java.awt.*;

import service.InchirieriService;
import service.carService;
import service.chiriasService;

public class PanelInchirieri extends JPanel {
    carService carService;
    chiriasService chiriasService;

    InchirieriService inchirieriService;

    private CardLayout cardLayout = new CardLayout();
    private JPanel cards = new JPanel(cardLayout);

    private AdaugaInchirieriPanel adaugaInchirieriPanel;
    private AfisareInchirieriPanel afiseazaInchirieriPanel;


    public PanelInchirieri(carService carService, chiriasService chiriasService, InchirieriService inchirieriService){
        this.carService = carService;
        this.chiriasService = chiriasService;
        this.inchirieriService = inchirieriService;
        setLayout(new BorderLayout());
        initializeUI();
    }

    private void initializeUI(){
        JButton btnAdaugaInchiriere = new JButton("Adaugă Inchiriere");
        JButton btnAfisareListaInchirieri = new JButton("Afișare Lista Inchirieri");

        // Adăugarea butoanelor la un panou
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(btnAdaugaInchiriere);
        buttonPanel.add(btnAfisareListaInchirieri);
        add(buttonPanel, BorderLayout.NORTH);

        adaugaInchirieriPanel = new AdaugaInchirieriPanel(carService, chiriasService, inchirieriService);
        afiseazaInchirieriPanel = new AfisareInchirieriPanel(inchirieriService);
        cards.add(adaugaInchirieriPanel, "AdaugaInchiriere");
        cards.add(afiseazaInchirieriPanel, "AfiseazaInchirieri");


        add(cards, BorderLayout.CENTER);
        btnAdaugaInchiriere.addActionListener(e -> {
            AdaugaInchirieriPanel.reset();
            showCard("AdaugaInchiriere");
        });
        btnAfisareListaInchirieri.addActionListener((e -> {
            AfisareInchirieriPanel.reset();
            showCard("AfiseazaInchirieri");
        }));
    }

    public void showCard(String cardName) {
        cardLayout.show(cards, cardName);
    }
}
