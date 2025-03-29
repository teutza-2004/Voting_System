package Tema1;

import java.util.ArrayList;
import java.util.Collections;

public class Circumscriptie {
    private String idAlegeri, numeCircumscriptie, regiune;
    private ArrayList<Votant> votanti;

    Circumscriptie() {}
    Circumscriptie(String idAlegeri, String numeCircumscriptie, String regiune) {
        this.idAlegeri = idAlegeri;
        this.numeCircumscriptie = numeCircumscriptie;
        this.regiune = regiune;
        this.votanti = new ArrayList<Votant>();
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
    public String getRegiune() {
        return this.regiune;
    }
    public void setRegiune(String regiune) {
        this.regiune = regiune;
    }
    public ArrayList<Votant> getVotanti() {
        return this.votanti;
    }
    public void setVotanti(ArrayList<Votant> votanti) {
        this.votanti = votanti;
    }

    public int cautaCircumscriptie(ArrayList<Circumscriptie> listaCircumscriptie) {
        for (int i = 0; i < listaCircumscriptie.size(); i++) {
            if (this.numeCircumscriptie.equals(listaCircumscriptie.get(i).numeCircumscriptie)) {
                return i;
            }
        }
        return -1;
    }
    public void afiseazaVotanti() {
        Collections.sort(this.votanti, new ComparatorPersoana());
        for (Votant votant : this.votanti) {
            System.out.println(votant.getNume() + " " + votant.getCnp() + " " + votant.getVarsta());
        }
    }
    public void eliminareVoturi(ArrayList<Vot> listaVoturi) {
        for (int i = 0; i < listaVoturi.size(); i++) {
            if (this.numeCircumscriptie.equals(listaVoturi.get(i).getNumeCircumscriptie())) {
                listaVoturi.remove(i);
            }
        }
    }
    public ArrayList<Vot> getListaVoturiCircumscriptie(ArrayList<Vot> listaVoturi) {
        ArrayList<Vot> listaVoturiCircumscriptie = new ArrayList<Vot>();
        for (int i = 0; i < listaVoturi.size(); i++) {
            if (this.numeCircumscriptie.equals(listaVoturi.get(i).getNumeCircumscriptie())) {
                listaVoturiCircumscriptie.add(listaVoturi.get(i));
            }
        }
        return listaVoturiCircumscriptie;
    }
}
