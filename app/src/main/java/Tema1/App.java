package Tema1;

import java.io.*;
import java.sql.SQLOutput;
import java.util.*;
import java.util.stream.Collectors;
import java.text.*;

public class App {
    private Scanner scanner;

    public App(InputStream input) {
        this.scanner = new Scanner(input);
    }

    public void run() {
        // Implementați aici cerințele din enunț
        // Pentru citirea datelor de la tastatura se folosește câmpul scanner.

        ArrayList<Alegeri> listaAlegeri = new ArrayList<Alegeri>();
        ArrayList<Circumscriptie> listaCircumscriptii = new ArrayList<Circumscriptie>();
        ArrayList<Votant> listaVotanti = new ArrayList<Votant>();
        ArrayList<Vot> listaVoturi = new ArrayList<Vot>();
        ArrayList<Vot> listaFraude = new ArrayList<Vot>();
        ArrayList<Regiune> listaRegiuni = new ArrayList<Regiune>();

        int comanda;
        do {
            comanda = scanner.nextInt();
            scanner.nextLine(); // citesc si newline de dupa nr comanda

            switch (comanda) {
                case 0: { // creare alegeri
                    String input = scanner.nextLine();
                    int indexSpace = input.indexOf(" ");
                    String idAlegere = input.substring(0, indexSpace);
                    String numeAlegere = input.substring(indexSpace + 1);

                    Alegeri alegere = new Alegeri(idAlegere, numeAlegere);
                    int verificAlegere = alegere.cautaAlegere(listaAlegeri);
                    if (verificAlegere < 0) {
                        listaAlegeri.add(alegere);
                        System.out.println("S-au creat alegerile " + numeAlegere);
                    } else {
                        System.out.println("EROARE: Deja exista alegeri cu id " + idAlegere);
                    }
                    break;
                }
                case 1: { // pornire alegeri
                    String idAlegere = scanner.nextLine();

                    Alegeri alegere = new Alegeri(idAlegere, "nume");
                    int verificAlegere = alegere.cautaAlegere(listaAlegeri);
                    if (verificAlegere < 0) {
                        System.out.println("EROARE: Nu exista alegeri cu acest id");
                    } else {
                        alegere = listaAlegeri.get(verificAlegere);
                        if (alegere.getStagiuAlegeri()) {
                            System.out.println("EROARE: Alegerile deja au inceput");
                        } else {
                            alegere.setStagiuAlegeri(true);
                            System.out.println("Au pornit alegerile " + alegere.getNumeAlegeri());
                        }
                    }
                    break;
                }
                case 2: { //adauga circumscriptie
                    String input = scanner.nextLine();
                    int indexSpace = input.indexOf(" ");
                    String idAlegere = input.substring(0, indexSpace);
                    input = input.substring(indexSpace + 1);

                    indexSpace = input.indexOf(" ");
                    String numeCircumscriptie = input.substring(0, indexSpace);
                    String numeRegiune = input.substring(indexSpace + 1);

                    Circumscriptie circumscriptie = new Circumscriptie(idAlegere, numeCircumscriptie, numeRegiune);
                    int verificareCircumscriptie = circumscriptie.cautaCircumscriptie(listaCircumscriptii);
                    if (verificareCircumscriptie < 0) {
                        Alegeri alegere = new Alegeri(idAlegere, "nume");
                        int verificareAlegere = alegere.cautaAlegere(listaAlegeri);
                        if (verificareAlegere < 0) {
                            System.out.println("EROARE: Nu exista alegeri cu acest id");
                        } else {
                            alegere = listaAlegeri.get(verificareAlegere);
                            if (alegere.getStagiuAlegeri()) {
                                listaCircumscriptii.add(circumscriptie);

                                Regiune regiune = new Regiune(circumscriptie.getRegiune());
                                int nrRegiune = regiune.cautaRegiune(listaRegiuni);
                                if (nrRegiune < 0) {
                                    listaRegiuni.add(regiune);
                                } else {
                                    regiune = listaRegiuni.get(nrRegiune);
                                }
                                regiune.getListaCircumscriptii().add(circumscriptie);

                                System.out.println("S-a adaugat circumscriptia " + circumscriptie.getNumeCircumscriptie());
                            } else {
                                System.out.println("EROARE: Nu este perioada de votare");
                            }
                        }

                    } else {
                        System.out.println("EROARE: Deja exista o circumscriptie cu numele " + circumscriptie.getNumeCircumscriptie());
                    }
                    break;
                }
                case 3: { // sterge circumscriptie
                    String input = scanner.nextLine();
                    int indexSpace = input.indexOf(" ");
                    String idAlegere = input.substring(0, indexSpace);
                    String numeCircumscriptie = input.substring(indexSpace + 1);

                    Alegeri alegere = new Alegeri(idAlegere, "nume");
                    int nrAlegere = alegere.cautaAlegere(listaAlegeri);
                    if (nrAlegere < 0) {
                        System.out.println("EROARE: Nu exista alegeri cu acest id");
                    } else {
                        alegere = listaAlegeri.get(nrAlegere);
                        if (alegere.getStagiuAlegeri()) {
                            Circumscriptie circumscriptie = new Circumscriptie(idAlegere, numeCircumscriptie, "regiune");
                            int nrCircumscriptie = circumscriptie.cautaCircumscriptie(listaCircumscriptii);
                            if (nrCircumscriptie < 0) {
                                System.out.println("EROARE: Nu exista o circumscriptie cu numele " + circumscriptie.getNumeCircumscriptie());
                            } else {
                                circumscriptie = listaCircumscriptii.get(nrCircumscriptie);
                                circumscriptie.eliminareVoturi(listaVoturi);
                                listaCircumscriptii.remove(circumscriptie);
                                System.out.println("S-a sters circumscriptia " + circumscriptie.getNumeCircumscriptie());
                            }
                        } else {
                            System.out.println("EROARE: Nu este perioada de votare");
                        }
                    }
                    break;
                }
                case 4: { // adaugare candidat
                    String input = scanner.nextLine();
                    int indexSpace = input.indexOf(" ");
                    String idAlegere = input.substring(0, indexSpace);
                    input = input.substring(indexSpace + 1);

                    indexSpace = input.indexOf(" ");
                    String cnp = input.substring(0, indexSpace);
                    input = input.substring(indexSpace + 1);

                    indexSpace = input.indexOf(" ");
                    int varsta = Integer.parseInt(input.substring(0, indexSpace));
                    String nume = input.substring(indexSpace + 1);

                    Candidat candidat = new Candidat(idAlegere, nume, cnp, varsta);
                    if (candidat.getVarsta() < 35) {
                        System.out.println("EROARE: Varsta invalida");
                    } else {
                        if (candidat.getCnp().length() != 13) {
                            System.out.println("EROARE: CNP invalid");
                        } else {
                            Alegeri alegere = new Alegeri(idAlegere, "nume");
                            int nrAlegere = alegere.cautaAlegere(listaAlegeri);
                            if (nrAlegere < 0) {
                                System.out.println("EROARE: Nu exista alegeri cu acest id");
                            } else {
                                alegere = listaAlegeri.get(nrAlegere);
                                if (alegere.getStagiuAlegeri()) {
                                    int verificareCandidat = candidat.cautaCandidat(alegere.getCandidati());
                                    if (verificareCandidat < 0) {
                                        alegere.getCandidati().add(candidat);
                                        System.out.println("S-a adaugat candidatul " + candidat.getNume());
                                    } else {
                                        System.out.println("EROARE: Candidatul " + candidat.getNume() + " are deja acelasi CNP");
                                    }
                                } else {
                                    System.out.println("EROARE: Nu este perioada de votare");
                                }
                            }

                        }
                    }
                    break;
                }
                case 5: { // eliminare candidat din alegeri
                    String input = scanner.nextLine();
                    int indexSpace = input.indexOf(" ");
                    String idAlegere = input.substring(0, indexSpace);
                    String cnp = input.substring(indexSpace + 1);

                    Alegeri alegere = new Alegeri(idAlegere, "nume");
                    int nrAlegere = alegere.cautaAlegere(listaAlegeri);
                    if (nrAlegere < 0) {
                        System.out.println("EROARE: Nu exista alegeri cu acest id");
                    } else {
                        alegere = listaAlegeri.get(nrAlegere);
                        if (alegere.getStagiuAlegeri()) {
                            Candidat candidat = new Candidat(idAlegere, "nume", cnp, 0);
                            int nrCandidat = candidat.cautaCandidat(alegere.getCandidati());
                            if (nrCandidat < 0) {
                                System.out.println("EROARE: Nu exista un candidat cu CNP-ul " + candidat.getCnp());
                            } else {
                                candidat = alegere.getCandidati().get(nrCandidat);
                                alegere.getCandidati().remove(candidat);
                                System.out.println("S-a sters candidatul " + candidat.getNume());
                            }
                        } else {
                            System.out.println("EROARE: Nu este perioada de votare");
                        }
                    }
                    break;
                }
                case 6: { // adaugare votant
                    String input = scanner.nextLine();
                    int indexSpace = input.indexOf(" ");
                    String idAlegere = input.substring(0, indexSpace);
                    input = input.substring(indexSpace + 1);

                    indexSpace = input.indexOf(" ");
                    String numeCircumscriptie = input.substring(0, indexSpace);
                    input = input.substring(indexSpace + 1);

                    indexSpace = input.indexOf(" ");
                    String cnp = input.substring(0, indexSpace);
                    input = input.substring(indexSpace + 1);

                    indexSpace = input.indexOf(" ");
                    int varsta = Integer.parseInt(input.substring(0, indexSpace));
                    input = input.substring(indexSpace + 1);

                    indexSpace = input.indexOf(" ");
                    String neindemanatic = input.substring(0, indexSpace);
                    String nume = input.substring(indexSpace + 1);

                    Votant votant = new Votant(idAlegere, numeCircumscriptie, cnp, varsta, neindemanatic, nume);
                    if (votant.getVarsta() < 18) {
                        System.out.println("EROARE: Varsta invalida");
                    } else {
                        if (votant.getCnp().length() != 13) {
                            System.out.println("EROARE: CNP invalid");
                        } else {
                            Circumscriptie circumscriptie = new Circumscriptie(idAlegere, numeCircumscriptie, "regiune");
                            int nrCircumscriptie = circumscriptie.cautaCircumscriptie(listaCircumscriptii);
                            if (nrCircumscriptie < 0) {
                                System.out.println("EROARE: Nu exista o circumscriptie cu numele " + circumscriptie.getNumeCircumscriptie());
                            } else {
                                circumscriptie = listaCircumscriptii.get(nrCircumscriptie);
                                Alegeri alegere = new Alegeri(idAlegere, "nume");
                                int nrAlegere = alegere.cautaAlegere(listaAlegeri);
                                if (nrAlegere < 0) {
                                    System.out.println("EROARE: Nu exista alegeri cu acest id");
                                } else {
                                    alegere = listaAlegeri.get(nrAlegere);
                                    if (alegere.getStagiuAlegeri()) {
                                        int verificareVotant = votant.cautaVotant(circumscriptie.getVotanti());
                                        if (verificareVotant < 0) {
                                            circumscriptie.getVotanti().add(votant);
                                            listaVotanti.add(votant);
                                            System.out.println("S-a adaugat votantul " + votant.getNume());
                                        } else {
                                            System.out.println("EROARE: Votantul " + votant.getNume() + " are deja acelasi CNP");
                                        }
                                    } else {
                                        System.out.println("EROARE: Nu este perioada de votare");
                                    }
                                }

                            }
                        }
                    }
                    break;
                }
                case 7: { // afisare candidati din alegeri
                    String idAlegere = scanner.nextLine();

                    Alegeri alegere = new Alegeri(idAlegere, "nume");
                    int nrAlegere = alegere.cautaAlegere(listaAlegeri);
                    if (nrAlegere < 0) {
                        System.out.println("EROARE: Nu exista alegeri cu acest id");
                    } else {
                        alegere = listaAlegeri.get(nrAlegere);
                        if (alegere.getStagiuAlegeri()) {
                            if (alegere.getCandidati().size() == 0) {
                                System.out.println("GOL: Nu sunt candidati");
                            } else {
                                System.out.println("Candidatii:");
                                alegere.afiseazaCandidati();
                            }
                        } else {
                            System.out.println("EROARE: Inca nu au inceput alegerile");
                        }
                    }

                    break;
                }
                case 8: { // afisare votanti din circumscriptie
                    String input = scanner.nextLine();
                    int indexSpace = input.indexOf(" ");
                    String idAlegere = input.substring(0, indexSpace);
                    String numeCircumscriptie = input.substring(indexSpace + 1);

                    Circumscriptie circumscriptie = new Circumscriptie(idAlegere, numeCircumscriptie, "regiune");
                    int nrCircumscriptie = circumscriptie.cautaCircumscriptie(listaCircumscriptii);
                    if (nrCircumscriptie < 0) {
                        System.out.println("EROARE: Nu exista o circumscriptie cu numele " + circumscriptie.getNumeCircumscriptie());
                    } else {
                        circumscriptie = listaCircumscriptii.get(nrCircumscriptie);
                        Alegeri alegere = new Alegeri(idAlegere, "nume");
                        int nrAlegere = alegere.cautaAlegere(listaAlegeri);
                        if (nrAlegere < 0) {
                            System.out.println("EROARE: Nu exista alegeri cu acest id");
                        } else {
                            alegere = listaAlegeri.get(nrAlegere);
                            if (alegere.getStagiuAlegeri()) {
                                if (circumscriptie.getVotanti().size() == 0) {
                                    System.out.println("GOL: Nu sunt votanti in " + circumscriptie.getNumeCircumscriptie());
                                } else {
                                    System.out.println("Votantii din " + circumscriptie.getNumeCircumscriptie() + ":");
                                    circumscriptie.afiseazaVotanti();
                                }
                            } else {
                                System.out.println("EROARE: Inca nu au inceput alegerile");
                            }
                        }
                    }
                    break;
                }
                case 9: { // adaugare vot
                    String input = scanner.nextLine();
                    int indexSpace = input.indexOf(" ");
                    String idAlegere = input.substring(0, indexSpace);
                    input = input.substring(indexSpace + 1);

                    indexSpace = input.indexOf(" ");
                    String numeCircumscriptie = input.substring(0, indexSpace);
                    input = input.substring(indexSpace + 1);

                    indexSpace = input.indexOf(" ");
                    String cnpVotant = input.substring(0, indexSpace);
                    String cnpCandidat = input.substring(indexSpace + 1);

                    Vot vot = new Vot(idAlegere, numeCircumscriptie, cnpVotant, cnpCandidat);
                    int nrVot = vot.cautaVot(listaVoturi);
                    if (nrVot < 0) {
                        Circumscriptie circumscriptie = new Circumscriptie(idAlegere, numeCircumscriptie, "regiune");
                        int nrCircumscriptie = circumscriptie.cautaCircumscriptie(listaCircumscriptii);
                        if (nrCircumscriptie < 0) {
                            System.out.println("EROARE: Nu exista o circumscriptie cu numele " + circumscriptie.getNumeCircumscriptie());
                        } else {
                            circumscriptie = listaCircumscriptii.get(nrCircumscriptie);
                            Votant votant = new Votant(idAlegere, numeCircumscriptie, cnpVotant, 0, "da", "nume");
                            int nrVotant = votant.cautaVotant(listaVotanti);
                            if (nrVotant < 0) {
                                System.out.println("EROARE: Nu exista un votant cu CNP-ul " + votant.getCnp());
                            } else {
                                nrVotant = votant.cautaVotant(circumscriptie.getVotanti());
                                if (nrVotant < 0) {
                                    listaFraude.add(vot);
                                    System.out.println("FRAUDa: Votantul cu CNP-ul " + votant.getCnp() + " a incercat sa comita o frauda. Votul a fost anulat");
                                } else {
                                    votant = circumscriptie.getVotanti().get(nrVotant);
                                    Alegeri alegere = new Alegeri(idAlegere, "nume");
                                    int nrAlegere = alegere.cautaAlegere(listaAlegeri);
                                    if (nrAlegere < 0) {
                                        System.out.println("EROARE: Nu exista alegeri cu acest id");
                                    } else {
                                        alegere = listaAlegeri.get(nrAlegere);
                                        if (alegere.getStagiuAlegeri()) {
                                            Candidat candidat = new Candidat(idAlegere, "nume", cnpCandidat, 0);
                                            int nrCandidat = candidat.cautaCandidat(alegere.getCandidati());
                                            if (nrCandidat < 0) {
                                                System.out.println("EROARE: Nu exista un candidat cu CNP-ul " + cnpCandidat);
                                            } else {
                                                candidat = alegere.getCandidati().get(nrCandidat);
                                                listaVoturi.add(vot);
                                                System.out.println(votant.getNume() + " a votat pentru " + candidat.getNume());
                                            }
                                        } else {
                                            System.out.println("EROARE: Nu este perioada de votare");
                                        }
                                    }
                                }
                            }
                        }
                    } else {
                        listaFraude.add(vot);
                        System.out.println("FRAUDa: Votantul cu CNP-ul " + cnpVotant + " a incercat sa comita o frauda. Votul a fost anulat");
                    }
                    break;
                }
                case 10: { // oprire alegeri
                    String idAlegeri = scanner.nextLine();

                    Alegeri alegere = new Alegeri(idAlegeri, "nume");
                    int nrAlegere = alegere.cautaAlegere(listaAlegeri);
                    if (nrAlegere < 0) {
                        System.out.println("EROARE: Nu exista alegeri cu acest id");
                    } else {
                        alegere = listaAlegeri.get(nrAlegere);
                        if (alegere.getStagiuAlegeri()) {
                            alegere.setStagiuAlegeri(false);
                            System.out.println("S-au terminat alegerile " + alegere.getNumeAlegeri());
                        } else {
                            System.out.println("EROARE: Nu este perioada de votare");
                        }
                    }
                    break;
                }
                case 11: { // rapot circumscriptie
                    String input = scanner.nextLine();
                    int indexSpace = input.indexOf(" ");
                    String idAlegere = input.substring(0, indexSpace);
                    String numeCircumscriptie = input.substring(indexSpace + 1);

                    Alegeri alegere = new Alegeri(idAlegere, "nume");
                    int nrAlegere = alegere.cautaAlegere(listaAlegeri);
                    if (nrAlegere < 0) {
                        System.out.println("EROARE: Nu exista alegeri cu acest id");
                    } else {
                        alegere = listaAlegeri.get(nrAlegere);
                        if (alegere.getStagiuAlegeri()) {
                            System.out.println("EROARE: Inca nu s-a terminat votarea");
                        } else {
                            Circumscriptie circumscriptie = new Circumscriptie(idAlegere, numeCircumscriptie, "regiune");
                            int nrCircumscriptie = circumscriptie.cautaCircumscriptie(listaCircumscriptii);
                            if (nrCircumscriptie < 0) {
                                System.out.println("EROARE: Nu exista o circumscriptie cu numele " + circumscriptie.getNumeCircumscriptie());
                            } else {
                                circumscriptie = listaCircumscriptii.get(nrCircumscriptie);
                                ArrayList<Vot> listaVoturiCircumscriptie = circumscriptie.getListaVoturiCircumscriptie(listaVoturi);
                                if (listaVoturiCircumscriptie.size() == 0) {
                                    System.out.println("GOL: Lumea nu isi exercita dreptul de vot in " + circumscriptie.getNumeCircumscriptie());
                                } else {
                                    System.out.println("Raport voturi " + circumscriptie.getNumeCircumscriptie() + ":");
                                    for (Candidat candidat: alegere.getCandidati()) {
                                        candidat.setNrVoturi(listaVoturiCircumscriptie, listaVotanti);
                                    }
                                    Collections.sort(alegere.getCandidati(), new ComparatorCandidati());
                                    for (Candidat candidat: alegere.getCandidati()) {
                                        System.out.println(candidat.getNume() + " " + candidat.getCnp() + " - " + candidat.getNrVoturi());
                                    }
                                }
                            }
                        }
                    }
                    break;
                }
                case 12: { // raport national
                    String idAlegeri = scanner.nextLine();

                    Alegeri alegere = new Alegeri(idAlegeri, "nume");
                    int nrAlegeri = alegere.cautaAlegere(listaAlegeri);
                    if (nrAlegeri < 0) {
                        System.out.println("EROARE: Nu exista alegeri cu acest id");
                    } else {
                        alegere = listaAlegeri.get(nrAlegeri);
                        if (alegere.getStagiuAlegeri()) {
                            System.out.println("EROARE: Inca nu s-a terminat votarea");
                        } else {
                            if (listaVoturi.size() == 0) {
                                System.out.println("GOL: Lumea nu isi exercita dreptul de vot in Romania");
                            } else {
                                System.out.println("Raport voturi Romania:");
                                for (Candidat candidat : alegere.getCandidati()) {
                                    candidat.setNrVoturi(listaVoturi, listaVotanti);
                                }
                                Collections.sort(alegere.getCandidati(), new ComparatorCandidati());
                                for (Candidat candidat: alegere.getCandidati()) {
                                    System.out.println(candidat.getNume() + " " + candidat.getCnp() + " - " + candidat.getNrVoturi());
                                }
                            }
                        }
                    }
                    break;
                }
                case 13: { // analiza circumscriptie
                    String input = scanner.nextLine();
                    int indexSpace = input.indexOf(" ");
                    String idAlegere = input.substring(0, indexSpace);
                    String numeCircumscriptie = input.substring(indexSpace + 1);

                    Alegeri alegere = new Alegeri(idAlegere, "nume");
                    int nrAlegere = alegere.cautaAlegere(listaAlegeri);
                    if (nrAlegere < 0) {
                        System.out.println("EROARE: Nu exista alegeri cu acest id");
                    } else {
                        alegere = listaAlegeri.get(nrAlegere);
                        if (alegere.getStagiuAlegeri()) {
                            System.out.println("EROARE: Inca nu s-a terminat votarea");
                        } else {
                            Circumscriptie circumscriptie = new Circumscriptie(idAlegere, numeCircumscriptie, "regiune");
                            int nrCircumscriptie = circumscriptie.cautaCircumscriptie(listaCircumscriptii);
                            if (nrCircumscriptie < 0) {
                                System.out.println("EROARE: Nu exista o circumscriptie cu numele " + circumscriptie.getNumeCircumscriptie());
                            } else {
                                circumscriptie = listaCircumscriptii.get(nrCircumscriptie);
                                ArrayList<Vot> listaVoturiCircumscriptie = circumscriptie.getListaVoturiCircumscriptie(listaVoturi);
                                if (listaVoturiCircumscriptie.size() == 0) {
                                    System.out.println("GOL: Lumea nu isi exercita dreptul de vot in " + circumscriptie.getNumeCircumscriptie());
                                } else {
                                    for (Candidat candidat : alegere.getCandidati()) {
                                        candidat.setNrVoturi(listaVoturiCircumscriptie, listaVotanti);
                                    }
                                    Collections.sort(alegere.getCandidati(), new ComparatorCandidati());
                                    Candidat castigator = alegere.getCandidati().get(alegere.getCandidati().size() - 1);
                                    System.out.println("in " + circumscriptie.getNumeCircumscriptie() + " au fost "
                                            + listaVoturiCircumscriptie.size() + " voturi din " + listaVoturi.size()
                                            + ". Adica " + (listaVoturiCircumscriptie.size() * 100 / listaVoturi.size())
                                            + "%. Cele mai multe voturi au fost stranse de " + castigator.getCnp() + " " + castigator.getNume()
                                            + ". Acestea constituie " + (castigator.getNrVoturi() * 100 / listaVoturiCircumscriptie.size())
                                            + "% din voturile circumscriptiei.");
                                }
                            }
                        }
                    }
                    break;
                }
                case 14: { // analiza national
                    String idAlegeri = scanner.nextLine();

                    Alegeri alegere = new Alegeri(idAlegeri, "nume");
                    int nrAlegeri = alegere.cautaAlegere(listaAlegeri);
                    if (nrAlegeri < 0) {
                        System.out.println("EROARE: Nu exista alegeri cu acest id");
                    } else {
                        alegere = listaAlegeri.get(nrAlegeri);
                        if (alegere.getStagiuAlegeri()) {
                            System.out.println("EROARE: Inca nu s-a terminat votarea");
                        } else {
                            if (listaVoturi.size() == 0) {
                                System.out.println("GOL: Lumea nu isi exercita dreptul de vot in Romania");
                            } else {
                                System.out.println("in Romania au fost " + listaVoturi.size() + " voturi.");
                                Collections.sort(listaRegiuni, new ComparatorRegiuni());
                                for (Regiune regiune : listaRegiuni) {
                                    ArrayList<Vot> listaVoturiRegiune = new ArrayList<Vot>();
                                    for (Circumscriptie circumscriptie : regiune.getListaCircumscriptii()) {
                                        listaVoturiRegiune.addAll(circumscriptie.getListaVoturiCircumscriptie(listaVoturi));
                                    }
                                    for (Candidat candidat : alegere.getCandidati()) {
                                        candidat.setNrVoturi(listaVoturiRegiune, listaVotanti);
                                    }
                                    Collections.sort(alegere.getCandidati(), new ComparatorCandidati());
                                    Candidat castigator = alegere.getCandidati().get(alegere.getCandidati().size() - 1);
                                    System.out.println("in " + regiune.getNumeRegiune() + " au fost "
                                            + listaVoturiRegiune.size() + " voturi din " + listaVoturi.size()
                                            + ". Adica " + (listaVoturiRegiune.size() * 100 / listaVoturi.size())
                                            + "%. Cele mai multe voturi au fost stranse de " + castigator.getCnp() + " " + castigator.getNume()
                                            + ". Acestea constituie " + (castigator.getNrVoturi() * 100 / listaVoturiRegiune.size())
                                            + "% din voturile regiunii.");
                                }
                            }
                        }
                    }
                    break;
                }
                case 15: { // afisare fraude
                    String idAlegere = scanner.nextLine();

                    Alegeri alegere = new Alegeri(idAlegere, "nume");
                    int nrAlegere = alegere.cautaAlegere(listaAlegeri);
                    if (nrAlegere < 0) {
                        System.out.println("EROARE: Nu exista alegeri cu acest id");
                    } else {
                        if (listaFraude.size() == 0) {
                            System.out.println("GOL: Romanii sunt cinstiti");
                        } else{
                            System.out.println("Fraude comise:");
                            for (Vot vot : listaFraude) {
                                if (alegere.getIdAlegeri().equals(vot.getIdAlegeri())) {
                                    Votant votant = new Votant(alegere.getIdAlegeri(), vot.getNumeCircumscriptie(), vot.getCnpVotant(), 0, "da", "nume");
                                    int nrVotant = votant.cautaVotant(listaVotanti);
                                    votant = listaVotanti.get(nrVotant);
                                    System.out.println("in " + votant.getNumeCircumscriptie() + ": " + votant.getCnp() + " " + votant.getNume());
                                }
                            }
                        }

                    }
                    break;
                }
                case 16: { // stergere alegeri
                    String idAlegere = scanner.nextLine();

                    Alegeri alegere = new Alegeri(idAlegere, "nume");
                    int nrAlegere = alegere.cautaAlegere(listaAlegeri);
                    if (nrAlegere < 0) {
                        System.out.println("EROARE: Nu exista alegeri cu acest id");
                    } else {
                        alegere = listaAlegeri.get(nrAlegere);
                        listaAlegeri.remove(alegere);
                        System.out.println("S-au sters alegerile " + alegere.getNumeAlegeri());
                    }
                    break;
                }
                case 17: { // listare alegeri
                    if (listaAlegeri.size() == 0) {
                        System.out.println("GOL: Nu sunt alegeri");
                    } else {
                        System.out.println("Alegeri:");
                        for (Alegeri alegere : listaAlegeri) {
                            System.out.println(alegere.getIdAlegeri() + " " + alegere.getNumeAlegeri());
                        }
                    }
                    break;
                }
                case 18: { // iesire
                    listaFraude.clear();
                    listaCircumscriptii.clear();
                    listaRegiuni.clear();
                    listaAlegeri.clear();
                    listaVoturi.clear();
                    listaVotanti.clear();
                    break;
                }
            }
        } while (comanda != 18);
    }

    public static void main(String[] args) {
        App app = new App(System.in);
        app.run();
    }
}