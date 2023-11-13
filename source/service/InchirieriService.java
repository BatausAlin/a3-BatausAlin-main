package service;

import domain.Chirias;
import domain.Inchiriere;
import repository.AppConfig;
import repository.Repository;


public class InchirieriService {
    private Repository<Inchiriere> repo;
    private int nextId; // numele variabilei a fost schimbat pentru claritate

    public InchirieriService(Repository<Inchiriere> repo) {
        this.repo = repo;
        initializeNextId();
        // Adaugă date de test, dacă e necesar
    }

    private void initializeNextId() {
        nextId = 0;
        for (Inchiriere inchiriere : repo.getAll()) {
            if (inchiriere.getId() >= nextId) {
                nextId = inchiriere.getId() + 1;
            }
        }
    }

    public void addInchiriere(String numeChirias, String prenumeChirias, String CNPChirias, String numeMasina, String modelMasina, String numarInmatriculare) {
        this.repo.add(new Inchiriere(nextId++, numeChirias, prenumeChirias, CNPChirias, numeMasina, modelMasina, numarInmatriculare));
    }

    public Repository<Inchiriere> getInchiriere(){
        return repo;
    }

    public void deleteInchiriere(int idInchiriere){
        repo.remove(repo.findById(idInchiriere));
    }


    public void onRepositoryChanged() {
        updateRepository();
    }

    public void updateRepository() {
        System.out.println("S-a schimbat repo-ul idiotule");
        System.out.println(this.repo.getAll());
        this.repo = (Repository<Inchiriere>) AppConfig.getRepositoryInchirieri();
        System.out.println(this.repo.getAll());
    }

}
