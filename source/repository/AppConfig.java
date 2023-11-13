package repository;

import domain.Masina;

import java.util.ArrayList;
import java.util.List;

public class AppConfig {

    private static StorageType currentStorageType;

    private static List<RepositoryChangeListener> listeners = new ArrayList<>();

    public static void addRepositoryChangeListener(RepositoryChangeListener listener) {
        listeners.add(listener);
    }


    public void setCurrentStorageType(StorageType storageType) {
        currentStorageType = storageType;
        initializeRepository(storageType);
    }



    public enum StorageType {
        MEMORY, BINARY_FILE, DATABASE;
    }

    private static Repository<?> carRepository;
    private static Repository<?> carRepositoryMemory = new Repository<>();
    private static BinaryFileRepository<?> CarBinaryFileRepository = new BinaryFileRepository<>("Masini.bin");


    private static Repository<?> ChiriasiRepository;
    private static Repository<?> ChiriasiRepositoryMemory = new Repository<>();
    private static BinaryFileRepository<?> ChiriasiBinaryFileRepository = new BinaryFileRepository<>("Chiriasi.bin");


    private static Repository<?> InchirieriRepository;
    private static Repository<?> InchirieriRepositoryMemory = new Repository<>();
    private static Repository<?> InchirieriBinaryFileRepository = new BinaryFileRepository<>("Inchirieri.bin");


    public AppConfig(){
        currentStorageType = StorageType.MEMORY;
    };


    public static void initializeRepository(StorageType storageType, String... args) {
        switch (storageType) {
            case MEMORY:
                carRepository = carRepositoryMemory;
                ChiriasiRepository = ChiriasiRepositoryMemory;
                InchirieriRepository = InchirieriRepositoryMemory;

                System.out.println("Am trecut la " + storageType);
                break;
            case BINARY_FILE:
                carRepository = CarBinaryFileRepository;
                ChiriasiRepository = ChiriasiBinaryFileRepository;
                InchirieriRepository = InchirieriBinaryFileRepository;

                System.out.println("Am trecut la " + storageType);
                break;
            case DATABASE:
//                String dbUrl = args.length > 0 ? args[0] : "db_url";
//                String dbUsername = args.length > 1 ? args[1] : "username";
//                String dbPassword = args.length > 2 ? args[2] : "password";
//                repository = Repository.createDatabaseRepository(dbUrl, dbUsername, dbPassword);
                break;
            default:
                throw new IllegalArgumentException("Unsupported storage type");
        }

        for (RepositoryChangeListener listener : listeners) {
            listener.onRepositoryChanged();
        }
    }

    public void setMemory() {
        initializeRepository(StorageType.MEMORY);
    }

    public void setBinary(String fileName) {
        initializeRepository(StorageType.BINARY_FILE, fileName);
    }

    public static StorageType getCurrentStorageType(){
        return currentStorageType;
    }

    public static Repository<?> getRepositoryCar() {
        if (carRepository == null) {
            throw new IllegalStateException("Repository not initialized");
        }
        return carRepository;
    }

    public static Repository<?> getRepositoryChiriasi() {
        if (ChiriasiRepository == null) {
            throw new IllegalStateException("Repository not initialized");
        }
        return ChiriasiRepository;
    }

    public static Repository<?> getRepositoryInchirieri() {
        if (InchirieriRepository == null) {
            throw new IllegalStateException("Repository not initialized");
        }
        return InchirieriRepository;
    }
}

