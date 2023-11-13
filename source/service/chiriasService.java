package service;

import domain.Chirias;
import domain.Masina;
import repository.AppConfig;
import repository.Repository;
import repository.RepositoryChangeListener;

public class chiriasService implements RepositoryChangeListener {
    Repository<Chirias> repository;

    public chiriasService(){
        this.repository = (Repository<Chirias>) AppConfig.getRepositoryCar();
    }

    public void addChirias(int id, String nume, String prenume, String CNP){
        repository.add(new Chirias(id, nume, prenume, CNP));
    }
    public Repository<Chirias> getchirias(){
        return repository;
    }

    public void deleteChirias(int id){
        repository.remove(repository.findById(id));
    }


    public void onRepositoryChanged() {
        updateRepository();
    }

    public void updateRepository() {
        System.out.println("S-a schimbat repo-ul idiotule");
        System.out.println(this.repository.getAll());
        this.repository = (Repository<Chirias>) AppConfig.getRepositoryChiriasi();
        System.out.println(this.repository.getAll());
    }

}
