package repository;

import domain.Entity;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;


public class Repository<T extends Entity> {
//    Repository<T> repository = new Repository<>();
//    BinaryFileRepository<T> binaryFileRepository = new BinaryFileRepository<>("gigle.bin");


    private final List<T> items = new ArrayList<>();

    public void add(T item) {
        items.add(item);
    }

    public void remove(T item) {
        items.remove(item);
    }

    public List<T> getAll() {
        return new ArrayList<>(items);
    }

    public T findById(int id) {
        return items.stream()
                .filter(item -> item.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public List<T> findByPredicate(Predicate<T> predicate) {
        return items.stream()
                .filter(predicate)
                .collect(Collectors.toList());
    }

    public int size() {
        return items.size();
    }

    public T get(int rowIndex) {
        if(rowIndex >= 0 && rowIndex < items.size()) {
            return items.get(rowIndex);
        } else {
            throw new IndexOutOfBoundsException("Index: " + rowIndex + ", Size: " + items.size());
        }
    }

    public void clear() {
        items.clear(); // Golește lista de elemente
    }

    public static Repository<?> createMemoryRepository() {

        return new Repository<>();
    }

    public static Repository<?> createBinaryFileRepository(String fileName) {
        return new BinaryFileRepository<>(fileName);
    }

    public static Repository<?> createDatabaseRepository(String url, String username, String password) {
        return new DatabaseRepository<>(url, username, password);
    }

//    public Repository<?> getBinaryRepo(){
//        return this.binaryFileRepository;
//    }
//    public Repository getMemoryRepo(){
//        return this.repository;
//    }
//
//    public void setBinaryFileRepository(String filename){
//        this.binaryFileRepository = (BinaryFileRepository<T>) getBinaryRepo();
//    }
//    public void setMemoryRepository(){
//        repository = getMemoryRepo();
//    }
}
