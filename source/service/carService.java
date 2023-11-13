package service;

import domain.Masina;
import repository.AppConfig;
import repository.Repository;
import repository.RepositoryChangeListener;


public class carService implements RepositoryChangeListener {
    private Repository<Masina> repository;

    public carService(){
        this.repository = (Repository<Masina>) AppConfig.getRepositoryCar();
    };


    public void onRepositoryChanged() {
        updateRepository();
    }

    public void updateRepository() {
        System.out.println("S-a schimbat repo-ul idiotule");
        System.out.println(this.repository.getAll());
        this.repository = (Repository<Masina>) AppConfig.getRepositoryCar();
        System.out.println(this.repository.getAll());
    }


    public void addMasina(int id, String marca, String model, String culoare, String numarMatriculare){
        repository.add(new Masina(id, marca, model, culoare, numarMatriculare));
    }

    public Repository<Masina> getcar(){
        return repository;
    }

    public void deleteMasinaById(int masinaId) {
        repository.remove(repository.findById(masinaId));
    }

    public void deleteCar(int id){
        repository.remove(repository.findById(id));
    }

}