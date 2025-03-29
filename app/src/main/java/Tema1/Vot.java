package Tema1;

import java.util.ArrayList;

public class Vot {
    private String idAlegeri, numeCircumscriptie, cnpVotant, cnpCandidat;

    Vot() {}
    Vot(String idAlegeri, String numeCircumscriptie, String cnpVotant, String cnpCandidat) {
        this.idAlegeri = idAlegeri;
        this.numeCircumscriptie = numeCircumscriptie;
        this.cnpVotant = cnpVotant;
        this.cnpCandidat = cnpCandidat;
    }

    public String getIdAlegeri() {
        return this.idAlegeri;
    }
    public void setIdAlegeri(String idAlegeri) {
        this.idAlegeri = idAlegeri;
    }
    public String getNumeCircumscriptie() {
        return this.numeCircumscriptie;
    }
    public void setNumeCircumscriptie(String numeCircumscriptie) {
        this.numeCircumscriptie = numeCircumscriptie;
    }
    public String getCnpVotant() {
        return this.cnpVotant;
    }
    public void setCnpVotant(String cnpVotant) {
        this.cnpVotant = cnpVotant;
    }
    public String getCnpCandidat() {
        return this.cnpCandidat;
    }
    public void setCnpCandidat(String cnpCandidat) {
        this.cnpCandidat = cnpCandidat;
    }

    public int cautaVot(ArrayList<Vot> listaVoturi){
        for (int i = 0; i < listaVoturi.size(); i++) {
            if (this.getCnpVotant().equals(listaVoturi.get(i).getCnpVotant())) {
                return i;
            }
        }
        return -1;
    }
}
