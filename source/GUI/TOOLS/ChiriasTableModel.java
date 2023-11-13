package GUI.TOOLS;
import javax.swing.table.AbstractTableModel;
import java.util.List;

import domain.Chirias;
import domain.Masina;
import repository.AppConfig;
import repository.Repository;
import service.chiriasService;

public class ChiriasTableModel extends AbstractTableModel {
    private Repository<Chirias> chiriasrepo;
    chiriasService chiriasService;


    private final String[] columnNames = {"ID", "NUME", "PRENUME", "CNP"};

    public ChiriasTableModel(Repository<Chirias> chiriasrepo, chiriasService chiriasService) {
        this.chiriasrepo = chiriasrepo;
        this.chiriasService = chiriasService;
    }

    @Override
    public int getRowCount() {
        return chiriasrepo.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Chirias chirias = chiriasrepo.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return chirias.getId();
            case 1:
                return chirias.getNume();
            case 2:
                return chirias.getPrenume();
            case 3:
                return chirias.getCNP();
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }




    public void setRepository() {
        this.chiriasrepo = (Repository<Chirias>) AppConfig.getRepositoryChiriasi(); // Presupunând că AppConfig.getRepository() returnează noul repository
        this.chiriasService.onRepositoryChanged();
        System.out.println("Sunt in set repository.....");
        fireTableDataChanged(); // Notifică JTable că datele s-au schimbat
    }
}
