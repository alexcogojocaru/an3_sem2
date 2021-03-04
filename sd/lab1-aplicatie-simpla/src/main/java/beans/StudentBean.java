package beans;

import java.io.Serializable;

public class StudentBean implements Serializable {
    private String nume = null;
    private String prenume = null;
    private int varsta = 0;
    private String grupa = null;
    private double medie = 0.0;

    public StudentBean() {

    }

    public String getNume() { return nume; }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getPrenume() {
        return prenume;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }

    public int getVarsta() {
        return varsta;
    }

    public void setVarsta(int varsta) {
        this.varsta = varsta;
    }

    public String getGrupa() { return grupa; }

    public void setGrupa(String grupa) { this.grupa = grupa; }

    public double getMedie() { return medie; }

    public void setMedie(double medie) { this.medie = medie; }
}
