package Tema1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Alegeri {
    private String idAlegeri, numeAlegeri;
    private boolean stagiuAlegeri;
    private ArrayList<Candidat> candidati;

    public Alegeri() {}
    public Alegeri(String idAlegeri, String numeAlegeri) {
        this.idAlegeri = idAlegeri;
        this.numeAlegeri = numeAlegeri;
        this.stagiuAlegeri = false;
        this.candidati = new ArrayList<Candidat>();
    }

    public String getIdAlegeri() {
        return this.idAlegeri;
    }
    public void setIdAlegeri(String idAlegeri) {
        this.idAlegeri = idAlegeri;
    }
    public String getNumeAlegeri() {
        return this.numeAlegeri;
    }
    public void setNumeAlegeri(String numeAlegeri) {
        this.numeAlegeri = numeAlegeri;
    }
    public boolean getStagiuAlegeri() {
        return this.stagiuAlegeri;
    }
    public void setStagiuAlegeri(boolean stagiuAlegeri) {
        this.stagiuAlegeri = stagiuAlegeri;
    }
    public ArrayList<Candidat> getCandidati() {
        return this.candidati;
    }
    public void setCandidati(ArrayList<Candidat> candidati) {
        this.candidati = candidati;
    }

    public int cautaAlegere(ArrayList<Alegeri> listaAlegeri) {
        for (int i = 0; i < listaAlegeri.size(); i++) {
            if (this.idAlegeri.equals(listaAlegeri.get(i).idAlegeri)) {
                return i;
            }
        }
        return -1;
    }
    public void afiseazaCandidati() {
        Collections.sort(this.candidati, new ComparatorPersoana());
        for (Candidat candidat : this.candidati) {
            System.out.println(candidat.getNume() + " " + candidat.getCnp() + " " + candidat.getVarsta());
        }
    }
}
