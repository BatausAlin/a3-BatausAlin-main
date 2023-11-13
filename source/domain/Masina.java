package domain;

import java.io.Serializable;

public class Masina extends Entity implements Serializable {
    private int id;
    private String marca;
    private String model;
    private String culoare;
    private String numarInmatriculare;

    // poate mai adaugam

    public Masina(int id, String marca, String model, String culoare, String numarInmatriculare) {
        super(id);
        this.id = id;
        this.marca = marca;
        this.model = model;
        this.culoare = culoare;
        this.numarInmatriculare = numarInmatriculare;
    }


    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getCuloare() {
        return culoare;
    }

    public void setCuloare(String culoare) {
        this.culoare = culoare;
    }

    public String getnumarInmatriculare() {
        return numarInmatriculare;
    }

    public void setnumarInmatriculare(String numarInmatriculare) {
        this.numarInmatriculare = numarInmatriculare;
    }


    @Override
    public String toString() {
        return "Masina{" +
                "id=" + id +
                ", marca='" + marca + '\'' +
                ", model='" + model + '\'' +
                ", culoare='" + culoare + '\'' +
                ", numarMatriculare='" + numarInmatriculare + '\'' +
                '}';
    }
}
