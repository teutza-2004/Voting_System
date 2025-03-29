package Tema1;

import java.util.ArrayList;

public class Votant extends Persoana {
    private String numeCircumscriptie;
    private boolean neindemanatic;

    Votant() {
        super();
    }
    Votant(String idAlegeri, String numeCircumscriptie, String cnp, int varsta, String neindemanatic, String nume) {
        super(idAlegeri, nume, cnp, varsta);
        if (neindemanatic.equals("da")) {
            this.neindemanatic = true;
        } else {
            this.neindemanatic = false;
        }
        this.numeCircumscriptie = numeCircumscriptie;
    }

    public String getNumeCircumscriptie() {
        return this.numeCircumscriptie;
    }
    public void setNumeCircumscriptie(String numeCircumscriptie) {
        this.numeCircumscriptie = numeCircumscriptie;
    }
    public boolean getNeindemanatic() {
        return this.neindemanatic;
    }
    public void setNeindemanatic(boolean neindemanatic) {
        this.neindemanatic = neindemanatic;
    }

    public int cautaVotant(ArrayList<Votant> listaVotanti){
        for (int i = 0; i < listaVotanti.size(); i++) {
            if (this.getCnp().equals(listaVotanti.get(i).getCnp())) {
                return i;
            }
        }
        return -1;
    }
}
