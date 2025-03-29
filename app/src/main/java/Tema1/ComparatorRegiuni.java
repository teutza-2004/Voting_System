package Tema1;

import java.util.Comparator;

public class ComparatorRegiuni implements Comparator<Regiune> {
    public int compare(Regiune c1, Regiune c2) {
        return c2.getNumeRegiune().compareTo(c1.getNumeRegiune());
    }
}
