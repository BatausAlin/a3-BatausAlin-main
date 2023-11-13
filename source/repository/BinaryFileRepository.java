package repository;

import domain.Entity;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class BinaryFileRepository<T extends Entity> extends Repository<T> {
    private final String filePath;

    public BinaryFileRepository(String filePath) {
        this.filePath = filePath;
        loadFromFile();
    }

    private void saveToFile(List<T> items) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filePath))) {
            out.writeObject(items); // Scrie lista în fișier
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadFromFile() {
        File file = new File(filePath);

        if (!file.exists() || file.length() == 0) {
            // Fișierul este nou sau gol, inițializăm un repository gol
            System.out.println("Fișierul este gol sau nu există. Se creează un nou repository gol.");
            super.clear(); // Elimină toate elementele din repository pentru a-l face gol
            saveToFile(new ArrayList<>()); // Salvăm un repository gol
            return; // Nu avem ce să citim, deci ieșim din metodă
        }

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {

            List<T> items = (List<T>) in.readObject();
            for (T item : items) {
                super.add(item); // Adaugă elementele încărcate în repository
            }

        } catch (EOFException e) {
            System.out.println("Fișierul este corupt. Se creează un nou repository gol: " + e.getMessage());
            // Aici poți alege să rescrii fișierul cu un repository gol dacă este necesar
            saveToFile(new ArrayList<>()); // Salvăm un repository gol
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void remove(T item) {
        super.remove(item); // Îndepărtează elementul din lista în memorie
        saveToFile(getAll());       // Salvează starea actualizată în fișierul binar
    }

    @Override
    public void add(T item) {
        super.add(item); // Adaugă elementul în lista din memorie
        saveToFile(getAll());    // Salvează starea actualizată în fișierul binar
    }


}
