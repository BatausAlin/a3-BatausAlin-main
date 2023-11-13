package GUI;


import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import domain.ValidationMasina;

import GUI.*;

import java.util.List;

import domain.Masina;
import repository.Repository;

public class MasinaGUI {
    public static void masinaGUI(){
        // Crearea ferestrei principale
        JFrame Masiniframe = new JFrame("Mașini");
        Masiniframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        Masiniframe.setSize(700, 400);
        Masiniframe.setBackground(Color.green);
        Masiniframe.setForeground(Color.green);

        JLabel label = new JLabel("Bine ai venit la al doilea GUI!");
        Masiniframe.add(label);

        Masiniframe.setVisible(true);

    }
}
