package Tema1;

import java.util.Comparator;

public class ComparatorPersoana implements Comparator<Persoana> {
    public int compare(Persoana c1, Persoana c2) {
        return c1.getCnp().compareTo(c2.getCnp());
    }
}
