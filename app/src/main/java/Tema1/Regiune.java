package Tema1;

import java.util.ArrayList;

public class Regiune {
    private String numeRegiune;
    private ArrayList<Circumscriptie> listaCircumscriptii;

    Regiune() {}
    Regiune(String numeRegiune) {
        this.numeRegiune = numeRegiune;
        this.listaCircumscriptii = new ArrayList<Circumscriptie>();
    }

    public String getNumeRegiune() {
        return this.numeRegiune;
    }
    public void setNumeRegiune(String numeRegiune) {
        this.numeRegiune = numeRegiune;
    }
    public ArrayList<Circumscriptie> getListaCircumscriptii() {
        return this.listaCircumscriptii;
    }
    public void setListaCircumscriptii(ArrayList<Circumscriptie> listaCircumscriptii) {
        this.listaCircumscriptii = listaCircumscriptii;
    }

    public int cautaRegiune(ArrayList<Regiune> listaRegiuni){
        for (int i = 0; i < listaRegiuni.size(); i++) {
            if (this.numeRegiune.equals(listaRegiuni.get(i).getNumeRegiune())) {
                return i;
            }
        }
        return -1;
    }
}
