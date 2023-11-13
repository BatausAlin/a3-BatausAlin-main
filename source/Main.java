

import GUI.*;
import GUI.TOOLS.ImageGalleryFrame;
import GUI.TOOLS.ImageUploader;
import domain.Chirias;
import domain.Inchiriere;
import repository.AppConfig;
import service.InchirieriService;
import service.carService;
import domain.Masina;
import repository.Repository;
import service.chiriasService;

import javax.swing.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Repository<Inchiriere> inchiriereRepository = new Repository<>();
        AppConfig appConfig = new AppConfig();

        appConfig.initializeRepository(AppConfig.StorageType.MEMORY);
        carService carService = new carService();
        AppConfig.addRepositoryChangeListener(carService);

        chiriasService chiriasService  = new chiriasService();
        AppConfig.addRepositoryChangeListener(chiriasService);

        InchirieriService inchiriereService = new InchirieriService(inchiriereRepository);

        // Start the gallery
        SwingUtilities.invokeLater(() -> {
            new GUI(carService, chiriasService, inchiriereService);
        });
        }
}


