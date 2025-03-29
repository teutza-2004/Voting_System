package Tema1;

import java.util.ArrayList;
import java.util.Collections;

public class Candidat extends Persoana{
    private int nrVoturi;

    Candidat() {
        super();
    }
    Candidat(String idAlegeri, String nume, String cnp, int varsta) {
        super(idAlegeri, nume, cnp, varsta);
        this.nrVoturi = 0;
    }

    public int getNrVoturi() {
        return this.nrVoturi;
    }
    public void setNrVoturi(ArrayList<Vot> listaVoturi, ArrayList<Votant> listaVotanti) {
        this.nrVoturi = 0;
        for (Vot vot : listaVoturi) {
            if (vot.getCnpCandidat().equals(this.getCnp())) {
                Votant votant = new Votant(vot.getIdAlegeri(), vot.getNumeCircumscriptie(), vot.getCnpVotant(), 0, "da", "nume");
                int nrVotant = votant.cautaVotant(listaVotanti);
                votant = listaVotanti.get(nrVotant);
                if (votant.getNeindemanatic()) {
                    this.nrVoturi++;
                }
            }
        }
    }

    public int cautaCandidat(ArrayList<Candidat> listaCandidati){
        for (int i = 0; i < listaCandidati.size(); i++) {
            if (this.getCnp().equals(listaCandidati.get(i).getCnp())) {
                return i;
            }
        }
        return -1;
    }
}
