package domain;

import java.time.LocalDate;

public class Inchiriere extends Entity {
    private int id;
    private String numeChirias;
    private String prenumeChirias;
    private String CNPChirias;
    private String numeMasina;
    private String modelMasina;
    private String numarInmatriculare;
    // # TODO: Mai avem de pus restul, asta dupa ce-l facem sa mearga (an fabricatie, varsta chirias, experienta chirias)


    public Inchiriere(int id, String numeChirias, String prenumeChirias, String CNPChirias, String numeMasina, String modelMasina, String numarInmatriculare) {
        super(id);
        this.id = id;
        this.numeChirias = numeChirias;
        this.prenumeChirias = prenumeChirias;
        this.CNPChirias = CNPChirias;
        this.numeMasina = numeMasina;
        this.modelMasina = modelMasina;
        this.numarInmatriculare = numarInmatriculare;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public void setNumeChirias(String numeChirias) {
        this.numeChirias = numeChirias;
    }

    public void setPrenumeChirias(String prenumeChirias) {
        this.prenumeChirias = prenumeChirias;
    }

    public void setCNPChirias(String CNPChirias) {
        this.CNPChirias = CNPChirias;
    }

    public void setNumeMasina(String numeMasina) {
        this.numeMasina = numeMasina;
    }

    public void setModelMasina(String modelMasina) {
        this.modelMasina = modelMasina;
    }

    public void setNumarInmatriculare(String numarInmatriculare) {
        this.numarInmatriculare = numarInmatriculare;
    }

    @Override
    public int getId() {
        return id;
    }

    public String getNumeChirias() {
        return numeChirias;
    }

    public String getPrenumeChirias() {
        return prenumeChirias;
    }

    public String getCNPChirias() {
        return CNPChirias;
    }

    public String getNumeMasina() {
        return numeMasina;
    }

    public String getModelMasina() {
        return modelMasina;
    }

    public String getNumarInmatriculare() {
        return numarInmatriculare;
    }
}
