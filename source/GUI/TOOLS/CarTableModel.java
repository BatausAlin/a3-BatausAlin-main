//package GUI.TOOLS;
//
//import javax.swing.table.AbstractTableModel;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.function.Predicate;
//
//import domain.Masina;
//import repository.Repository;
//
//public class CarTableModel extends AbstractTableModel {
//    private Repository<Masina> repository;
//    private List<Masina> filteredCars;
//
//    private final String[] columnNames = {"ID", "MARCA", "MODEL", "CULOARE", "NUMAR DE IMATRICULARE"};
//
//    public CarTableModel(Repository<Masina> cars) {
//        this.repository = cars;
//        this.filteredCars = new ArrayList<>(cars.getAll()); // Inițial, lista filtrată va avea toate mașinile
//    }
//
//    // Metoda pentru filtrarea mașinilor bazată pe un predicat
//    public void filter(Predicate<Masina> predicate) {
//        filteredCars.clear();
//        repository.getAll().stream().filter(predicate).forEach(filteredCars::add);
//        fireTableDataChanged(); // Notifică JTable că datele s-au schimbat
//    }
//
//    @Override
//    public int getRowCount() {
//        return filteredCars.size();
//    }
//
//    @Override
//    public int getColumnCount() {
//        return columnNames.length;
//    }
//
//    @Override
//    public Object getValueAt(int rowIndex, int columnIndex) {
//        Masina car = filteredCars.get(rowIndex);
//        switch (columnIndex) {
//            case 0:
//                return car.getId();
//            case 1:
//                return car.getMarca();
//            case 2:
//                return car.getModel();
//            case 3:
//                return car.getCuloare();
//            case 4:
//                return car.getnumarInmatriculare();
//            default:
//                return null;
//        }
//    }
//
//    @Override
//    public String getColumnName(int column) {
//        return columnNames[column];
//    }
//
//    // Alte metode dacă sunt necesare...
//}



package GUI.TOOLS;

import domain.Masina;
import repository.AppConfig;
import repository.Repository;

import service.carService;

import javax.swing.table.AbstractTableModel;


public class CarTableModel extends AbstractTableModel {
    private Repository<Masina> repository;
    carService carService;
    private final String[] columnNames = {"ID", "MARCA", "MODEL", "CULOARE", "NUMAR DE IMATRICULARE"};

    public CarTableModel(Repository<Masina> cars, carService carService) {
        this.carService = carService;
        this.repository = cars;
    }

    // Metoda pentru filtrarea mașinilor bazată pe un predicat



    public void setRepository() {
        this.repository = (Repository<Masina>) AppConfig.getRepositoryCar(); // Presupunând că AppConfig.getRepository() returnează noul repository
        this.carService.onRepositoryChanged();
        System.out.println("Sunt in set repository.....");
        fireTableDataChanged(); // Notifică JTable că datele s-au schimbat
    }



    @Override
    public int getRowCount() {
//        System.out.println("Suntem in get row Count" + repository.size());
        return repository.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Masina car = repository.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return car.getId();
            case 1:
                return car.getMarca();
            case 2:
                return car.getModel();
            case 3:
                return car.getCuloare();
            case 4:
                return car.getnumarInmatriculare();
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

}
