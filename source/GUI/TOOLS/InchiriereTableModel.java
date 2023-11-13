package GUI.TOOLS;

import domain.Chirias;
import domain.Inchiriere;
import domain.Masina;
import repository.AppConfig;
import repository.Repository;
import service.InchirieriService;

import javax.swing.table.AbstractTableModel;

public class InchiriereTableModel extends AbstractTableModel {
    private Repository<Inchiriere> inchiriereRepository = new Repository<Inchiriere>();
    private final String[] columnNames = {"ID", "NUME CHIRIAS", "PRENUME CHIRIAS", "CNP CHIRIAS", "NUME MASINA", "MODEL MASINA", "NUMAR INMATRICULARE"};

    InchirieriService inchirieriService;

    public InchiriereTableModel(Repository<Inchiriere> inchiriereRepository, InchirieriService inchirieriService) {
        this.inchiriereRepository = inchiriereRepository;
        this.inchirieriService = inchirieriService;

    }

    @Override
    public int getRowCount() {
        return inchiriereRepository.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Inchiriere chirias = inchiriereRepository.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return chirias.getId();
            case 1:
                return chirias.getNumeChirias();
            case 2:
                return chirias.getPrenumeChirias();
            case 3:
                return chirias.getCNPChirias();
            case 4:
                return chirias.getNumeMasina();
            case 5:
                return chirias.getModelMasina();
            case 6:
                return chirias.getNumarInmatriculare();
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }



    public void setRepository() {
        this.inchiriereRepository = (Repository<Inchiriere>) AppConfig.getRepositoryInchirieri(); // Presupunând că AppConfig.getRepository() returnează noul repository
        this.inchirieriService.onRepositoryChanged();
        System.out.println("Sunt in set repository.....");
        fireTableDataChanged(); // Notifică JTable că datele s-au schimbat
    }
}
