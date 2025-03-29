package Tema1;

import java.util.Comparator;

public class ComparatorCandidati implements Comparator<Candidat> {
    public int compare(Candidat c1, Candidat c2) {
        if (c1.getNrVoturi() != c2.getNrVoturi()){
            return c1.getNrVoturi()-c2.getNrVoturi();
        } else {
            return c1.getCnp().compareTo(c2.getCnp());
        }
    }
}