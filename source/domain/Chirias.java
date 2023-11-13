package domain;
import domain.Entity;

import java.io.Serializable;

public class Chirias extends Entity implements Serializable {
    private int id;
    private String nume;
    private String prenume;
    private String CNP;

    public Chirias(int id, String nume, String prenume, String CNP) {
        super(id);
        this.id = id;
        this.nume = nume;
        this.prenume = prenume;
        this.CNP = CNP;
    }

    @Override
    public int getId() {
        return id;
    }

    public String getNume() {
        return nume;
    }

    public String getPrenume() {
        return prenume;
    }

    public String getCNP() {
        return CNP;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }

    public void setCNP(String CNP) {
        this.CNP = CNP;
    }
}
