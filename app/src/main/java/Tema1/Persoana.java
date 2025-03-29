package Tema1;

public class Persoana {
    private String idAlegeri, nume, cnp;
    private int varsta;

    Persoana() {}
    Persoana(String idAlegeri, String nume, String cnp, int varsta) {
        this.idAlegeri = idAlegeri;
        this.nume = nume;
        this.cnp = cnp;
        this.varsta = varsta;
    }

    public String getIdAlegeri() {
        return this.idAlegeri;
    }
    public void setIdAlegeri(String idAlegeri) {
        this.idAlegeri = idAlegeri;
    }
    public String getNume() {
        return this.nume;
    }
    public void setNume(String nume) {
        this.nume = nume;
    }
    public String getCnp() {
        return this.cnp;
    }
    public void setCnp(String cnp) {
        this.cnp = cnp;
    }
    public int getVarsta() {
        return this.varsta;
    }
    public void setVarsta(int varsta) {
        this.varsta = varsta;
    }
}
