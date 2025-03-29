BONUS:
Ce alte cazuri limită ați mai trata în această aplicație?
1. verificare CNP:
   - codul de judet este unul valid
   - ziua/luna/anul sunt date corecte calandesristice
   - prima cifra este 3/4 sau 5/6 in functie de anul nasterii
2. mesaj de eroare in cazul in care datele introduse sunt insuficiente/prea multe
3. verificare ca numele candidatilor/votantilor sa aiba atat nume cat si prenume

Cum ați refactoriza comenzile și răspunsurile din aplicație?
1. as mai aduaga optiunea de partide:
   - fiecare candidat are si un camp partid care reprezinta partidul din care face parte
   - pot exista alegeri intre partide (ex parlamentare)
2. pentru cazul alagerilor parlamentare fiecare partid poate avea o lista de candidati si din acel partid ajung in parlament un numar de candidati proportional cu procetul de voturi
3. optiunea de anulare a votului
4. cazul in care doi candidati obtin acelasi numar de voturi (trebuie gasit un criteriu suplimentar de departajare/repetare alegeri)

Clase (pentru encapsulare toate atributele sunt private):
- Alegeri:
    - atribute:
      - idAlegeri - identificator unic pt alegere
      - numeAlegeri - numele alegerii
      - stagiuAlegeri - arata daca alegerile sunt in curs sau nu (true - IN_CURS, false - NEINCEPUT)
      - candidati - lista care tine minte toti candidatii participanti la alegerea respectiva
    - constructor:
      - atribuie valori primite ca parametrii pt idAlegeri si numeAlegeri
      - initializeaza stagiuAlegeri cu false (initial alegerile nu sunt incepute)
      - initializeaza lista de candidati
    - metode:
      - gettere si settere pt fiecare atribut
      - cautaAlegere - cauta si returneaza pozitia din lista data ca parametru a obiectului curent in functie de idAlegeri, in cazul in care nu se afla in lista returneaza -1
      - afiseazaCandidati - sorteaza candidatii alegerii folosind comparatorul ComparatorPersoana si afiseaza date despe fiecare candidat: nume, cnp, varsta
- Persoana:
    - atribute:
      - idAlegeri - identificator unic pt alegere
      - nume - numele persoanei
      - cnp - cnp-ul unic al persoanei
      - varsta - varsta persoanei
    - cosntructor:
      - atribuie valorile primite ca parametrii tuturor atributelor clasei
    - metode:
      - gettere si settere pt fiecare atribut
- Candidat (extinde Persoana => mosteneste toate atributele si metodele din Persoana)
  - atribute:
    - nrVoturi - numarul de voturi al candidatului
  - constructor:
    - se foloseste de constructorul clasei parinte
    - initializeaza nrVoturi cu 0
  - metode:
    - getter pentru nrVoturi
    - setNrVoturi - calculeaza nr de voturi ale candidatului dintr-o lista de voturi, incrementand nrVoturi daca cnp-ul candidatului este egal cu cnp-ul votului respectiv si daca votantul nu este neindemanatic
    - cautaCandidat - cauta candidatul intr-o lista de candidati, verificand cnp-ul si returneaza pozitia acestuia in lista, daca nu se afla in lista este returnat -1
- Votant (extinde Persoana => mosteneste toate atributele si metodele din Persoana):
    - atribute:
      - numeCircumscriptie - numele circumscriptiei din care face parte votantul
      - neindemanatic - verifica daca votantul este neindemanatic sau nu (fals - daca este; true - daca nu este)
    - constructor:
      - se foloseste de constructorul clasei parinte
      - atribuie true/false atributului neindemanatic in functie de valoarea string-ului primit ca paremetru (da/nu)
      - atribuie numeCircumscriptie valoarea parametrului corespunzator
    - metode:
      - gettere si stettere pt fiecare atribut
      - cautaVotant - cauta votantul intr-o lista de votanti, in cazul in care cnp-ul votantului curent corespunde cu unul din lista este returnata pozitia din lista, altfel este returnat -1
- Circumscriptie:
    - atribute:
      - idAlegeri - identificatorul unic al alegerii
      - numeCircumscriptie - numele circumscriptiei
      - regiune - numele regiunii din care face parte circumscriptia
      - votanti - lista de votanti arondati circumscriptiei
    - costructori:
      - atribuie valorile date ca parametrii atributelor clasei
      - initializeaza lista de votanti
    - metode:
      - gettere si settere pt fiecare atribut
      - cautaCircumscriptie - returneaza pozitia circumscriptie curente in lista de circumscriptii data ca parametru, comparand numele, in cazul in care nu se afla in lista returneaza -1
      - afiseazaVotanti - sorteaza lista de votanti folosind comparatorul ComparatorPersoana si afiseaza datele fiecarui votant: nume, cnp, varsta
      - eliminareVoturi - elimina din lista de voturi primita ca parametru, voturile care au circumscriptia cu acelasi nume ca si a obiectului curent
      - getListaVoturiCircumscriptie - returneaza o lista care contine doar voturile din lista primita ca parametru care au numele circumscriptiei egal cu cel al obiectului curent
- Regiune:
    - atribute:
      - numeRegiune - numele regiunii
      - listaCircumscriptii - lista circumscriptiilor din regiune
    - constructor:
      - atribuie numeRegiune, primit ca parametru
      - initializeaza lista de circumscriptii
    - metode:
      - gettere si settere pt fiecare atribut
      - cautaRegiune - returneaza pozitia in lista de regiuni primita ca parametru a regiunii curente, in functie de nume, daca nu se ala in lista returneaza -1
- Vot:
    - atribute:
      - idAlegeri - identificator unic pentru alegeri
      - numeCircumscriptie - numele circumscriptiei unde voteaza un votant
      - cnpVotant - cnp-ul votantului
      - cnpCandidat - cnp-ul candidatului votat de votant
    - constructor:
      - atribuie valorile primite ca parametrii fiecarui atribut al clasei
    - metode:
      - gettere si settere pt fiecare atribut
      - cautaVot - returneaza pozitia din lsta de voturi primita ca parametru a votului curent, in functie de cnp-ul votantului, in cazul in care nu se afla in lista este returnat -1

Clase Comparator:
- ComparatorPersoana (pt sortarea votantilor/candidatilor necesaara afisarii listelor):
  - implementeaza interfata Comparator, iar functia compare compara cnp-urile persoanelor
- ComparatorCandidati (pt sortarea candidatilor pentru raport):
  - implementeaza interfata Comparator, iar functia compare compara candidatii in functie de numarul de voturi, in cazul in care acesta este egal, se compara dupa cnp
- CompareRegiuni (pt sortarea regiunilor pentru raport national):
  - implementeaza interfata Comparator, iar functia compare compara numele regiunilor

App:
- declar si initializez liste pentru:
    - listaAlegri - tin minte toate alegerile
    - listaCircumscriptii - tin minte toate circumscriptiile
    - listaVotanti - tin minte toti votantii (util numai pentru verificarea la vot)
    - listaVoturi - tin minte toate voturile
    - listaFraude - tin minte toate voturile frauduloase
    - listaRegiuni - tin minte toate regiunile din care am circumscriptii
- declar o variabila in care citesc numarul comenzii folosind do-while (pentru a face si actiunile respective comenzii inainte de a verifica daca s-a dat comanda de oprire)
- folosesc un switch-case pentru a executa mai cu usurinta comenzile in functie de numarul acestora
    - case 0 (creare alegeri):
      - citesc in string-ul input intreaga linie, dupa care o impart in idAlegere si numeAlegere
      - creez un obiect de tip Alegere pe care apoi il caut in lista de alegeri, in cazul in care nu se afla il adaug si afisez mesaj de succes, altfel afisez mesaj de eroare
    - case 1 (pornire alegeri):
      - citesc idAlegere si creez un obiect tip Alegere folosind idAlegere si un nume aleator
      - verific daca exista deja in lista un obiect cu acest id, iar in cazul in care nu exista afisez mesaj de eroare
      - altfel extrag obiectul din lista cu id-ul respectiv si verific stagiul alegerii
      - in cazul in care stagiul este true afisez mesaj de eroare deoarece alagerile sunt deja pornite, altfel setez stagiul ca true si afisez mesaj de succes
    - case 2 (adauga circumscriptie):
      - citesc input-ul si il separ in idAlegere, numeCircumscriptie si numeRegiune
      - cu ajutorul acestora creez un nou obiect Circumscriptie ep care il cut in lista de circumscriptii
      - in cazul in care exista deja, afisez mesaj de eroare
      - altfel creez un obiect Alegeri si verific existenta acestuia in lista de alegeri
      - in cazul in care nu exista, afisez mesaj de eroare
      - altfel verific stagiul alegerilor si in cazul in care nu este pornita alegerea afisez mesaj de eroare
      - altfel adaug circumscriptia in lista si afisez mesaj de succes
      - de asemenea, creez un obiect Regiune cu numeRegiune si verific daca exista in lista de regiuni, in caz negativ adaug regiunea in lista, dupa care (pentru ambele cazuri) adaug circumscriptia in lista de circumscriptii aferente regiunii
    - case 3 (sterge circumscriptie):
      - citesc input-ul pe care il despart in idAlegere si numeCircumscriptie
      - verific existenta alegerilor in lista de alegeri, daca nu exista afisez mesaj de eroare
      - altfel verific stagiul alegerilor, iar in cazul in care nu sunt pornite afisez mesaj de eroare
      - altfel verific existenta circumscriptiei in lista de circumscriptii, iar in caz in care nu este afisez mesaj de eroare
      - altfel sterg voturile aferente circumscriptiei din lista de voturi, elimin circumscriptia in lista si afisez mesaj de succes
    - case 4 (adauga candidat):
      - citesc input-ul si il despart in idAlegere, cnp, varsta si nume
      - verific daca varsta este valida (>= 35), in caz contrar afisez mesaj de eroare
      - altfel verific lungimea cnp-ului sa fie 13, iar in cazul in care nu este afisez mesaj de eroare
      - altfel verific existenta alagerilor in lista de alegeri si in caz negativ afisez mesaj de eroare
      - altfel verific stagiul alagerilor, iar in cazul in care nu sunt pornite afisez mesaj de eroare
      - altfel verific daca candidatul se afla deja in lista candodatilor aferenti alegerii si in caz afirmativ afisez mesaj de eroare
      - altfel adaug candidatul in lista alegerilor si afisez mesaj de succes
    - case 5 (eliminare candidat din alegeri):
      - citesc input-ul si il separ in idAlegere si cnp
      - caut alegerea in lista de alegeri, iar in cazul in care nu exista afisez mesaj de eroare
      - altfel verifc stagiul alagerilor si daca nu sunt pornite afisez mesaj de eroare
      - altfel verific existenta candidatului in lista aferenta alegrilor, iar in cazul in care nu este afisez mesaj de eroare
      - altfel sterg candidatul din lista de candidati a alegerii si afisez mesaj de succes
    - case 6 (adauga votant):
      - citesc input-ul pe care il despart in idAlegere, numeCircumscriptie, cnp, varsta, neindemanatic si nume
      - verific ca varsta votantului sa fie minim 18, in caz negativ afisez mesaj de eroare
      - altfel verific lungimea cnp-ului, iar in cazul in care nu este 13 afisez mesaj de eroare
      - altfel verific existenta circumscriptiei in lista de circumscriptii, iar in cazul in care nu este afisez mesaj de eroare
      - altfel verific ca alegerile sa fie in lista de alegeri, iar in cazul in care nu exista afisez mesaj de eroare
      - altfel verifc ca stagiul alagerilor sa fie pornit, in caz negativ afisez mesaj de eroare
      - altfel verific daca votantul se afla in lista de votanti aferenti circumscriptiei si in caz afirmativ afisez mesaj de eroare
      - altfel adaug votantul atat in lista de votanti a circumscriptiei cat si in cea cu toti votantii si afisez mesaj de succes
    - case 7 (afisare candidati alegere):
      - citesc idAlegere si verific daca se afla alegerea respectiv in lista de alegeri, iar in caz negativ afisez mesaj de eroare
      - altfel verific stagiul alegerilor, iar daca acestea nu sunt pornite afisez mesaj de eroare
      - altfel verific daca lista de candidati a alegerii are lungimea diferita de 0, iar in caz negativ afisez mesaj ca este gaoala lista
      - in cazul in care exista cel putin un candidat in lista afisez candidatii aferenti alegerii
    - case 8 (afisare votanti circumscriptie):
      - citesc input-ul pe care il despart in idAlegere si numeCircumscriptie
      - verific existenta circumscriptiei in lista de circumscriptii, iar in caz negativ afisez mesaj de eroare
      - altfel verific existenta alegerii in lista de alegeri si in caz negativ afisez mesaj de eroare
      - altfel verific staiul alegerii, iar in cazul in care alegera nu este pornita afisez mesaj de eroare
      - in cazul in care alegerile sunt pornite verific daca exista votanti in circumscriptie, iar in cazul in cae nu sunt afisez ca lista de votanti este goala
      - in cazul in care exista votanti ii afisez
    - case 9 (adaugare vot):
      - citesc input-ul pe care il despart in idAlegere, numeCircumscriptie, cnpVotant si cnpCandidat
      - verific daca exista deja votul in lista de voturi, iar in caz afirmativ afisez mesaj de incercare de frauda si adaug votul in lista de fraude
      - altfel verific daca circumscriptia se afla in lista de circumscriptii, iar in caz negativ afisez mesaj de eroare
      - altfel verific daca exista votantul in lista tuturor votantilor, iar in caz negativ afisez mesaj de eroare
      - altfel verific daca votantul se afla in lista votantilor circumscriptiei si in caz in care nu este afisez mesaj de incercare de frauda si adaug votul in lista de fraude
      - altfel verific existenta elegerii in lista de alegeri, iar daca aceasta nu este afisej mesaj de eroare
      - altfel verific stagiul alegerii, iar daca acestea nu sunt pornite afisez mesaj de eroare
      - daca sunt pornite, verific existenta candidatului in lista candidatilor alegerii si in cazul in care nu este afisez mesaj de eroare
      - altfel adaug votul in lista de voturi si afisez mesaj de succes
    - case 10 (oprire alegeri):
      - citesc idAlegere si ii verific existenta in lista de alegeri, iar in caz negativ afisez mesaj de eroare
      - altfel verific stagiul alegerilor, iar in cazul in care acetea sunt pornite schimb stagiul in false si afisez mesaj de succes
      - in cazul in care nu sunt pornite afisez mesaj de eroare
    - case 11 (raport circumscriptie):
      - citesc input-ul si il despart in idAlegere si numeCircumscriptie
      - verific existenta alegerii in lista de alegeri, iar in cazul in care nu este afisez mesaj de eroare
      - altfel verific stagiul alegerilor, iar daca acestea sunt pornite afisez mesaj de eroare
      - daca sunt pornite veiric existanta circumscriptiei in lista de circumscriptii si in cazul in care nu este afisez mesaj de eroare
      - altfel selectez in lisaVoturiCircumscriptie din lista cu toate voturile cele care sunt din circumscriptia data
      - in cazul in care lista este goala adisez mesaj ca este goala
      - altfel setez numarul de voturi ale fiecarui candidat aferente circumscriptiei folosind listaVoturiCircumscriptie
      - dupa sortez candidatii in functie de nr de votusi si cnp, apoi ii afisez: nume, cnp, nr voturi
    - case 12 (raport national):
      - citesc idAlegeri si verific daca exista alegerile in lista de alegeri, iar in caz negativ afisez mesaj de eroare
      - altfel verific stagiul alegerilor, iar daca acestea nu tunt oprite afisez mesaj de eroare
      - daca sunt oprite, verific daca lista de voturi este goala si in caz afirmativ afisez ca este goala
      - daca nu este goala setez nr de voturi ale candidatilor alegerii folosind listaVoturi
      - sortez candidatii in functie de nr de voturi apoi cnp si ii afisez: nume, cnp, nr voturi
    - case 13 (analiza circumscriptie):
      - citesc input-ul si il despart in idAlegere si numeCircumascriptie
      - verific daca elegerile fac parte din lista de alegeri, iar daca nu sunt afisej mesaj de eroare
      - altfel verific stagiul alegerii si daca este pornita dau mesaj de eroare
      - daca nu sunt pornite verific existenta circumscriptiei in lista de circumscriptii, iar daca aceasta nu este afisez mesaj de eroare
      - altfel selectez in listaVoturiCircumscriptie voturile din lista cu toate voturile care sunt corespondente circumscriptiei respective
      - daca lista are dimensiunea 0, afisez mesaj ca lista este goala
      - altfel setez numarul voturilor circumscriptiei candidatilor folosind listaVoturiCircumscriptie si o sortez dupa numarul voturilor candidatilor si cnp
      - selectez ultimul candidat din lista (cel cu cele mai multe voturi) si afisez statisticile circumscriptiei
    - case 14 (analiza nationala):
      - citesc idAlegeri si verific existenta alegerii in lista de alegeri, iar in caz negatif afisez mesaj de eroare
      - altfel verific stagiul alegerii, iar daca aceste sunt pornite afisez mesaj de eroare
      - daca nu sunt pornite verofic lungimea listei de voturi si in cazul in care este 0 afisez mesaj de lista goala
      - daca nu este goala sortez lista de regiuni alfabetic dupa nume
      - pentru fiecare regiune parcurg lista de circumscriptii aferente si adaug la listaVoturiRegiune voturile fiecarei circumscriptii
      - calculez nr de voturi ale fiecarui candidat in functie de regiune cu ajutorul listei listaVoturiRegiune
      - sortez candidatii dupa nr de voturi si cnp, dupa care il aleg pe putimul din lista (cel cu cele mai multe voturi) si afisez statisticile fiecarei regiuni
    - case 15 (afisare fraude):
      - citesc idAlegere si verific daca alegerea se afla in lista de alegeri, iar in caz negativ afisez mesaj de eroare
      - altfel verific marimea listei de fraude, iar daca aceasta este 0 afisez mesaj ca este goala
      - daca nu este goala parcurg lista tuturor fraudelor si daca acestea au id-ul egal cu id-ul alegerii, caut votantul in lista de votanti si ii fisez datele: numeCircumscriptie, cnp, nume
    - case 16 (stergere alegeri):
      - citesc idAlegere si verific daca exista alegerea in lista de alegeri, iar in caz negativ afisez mesaj de eroare
      - altfel elimin alegerea din lista de alegeri si afisez mesaj de succes
    - case 17 (listare alegeri):
      - verific marimea listei de alegeri, iar daca este 0 afisez mesaj ca este goala lista
      - daca nu este goala afisez datele fiecarei alegeri: idAlegeri, numeAlegeri
    - case 18 (iesire):
      - golesc toate listele si ies din do-while