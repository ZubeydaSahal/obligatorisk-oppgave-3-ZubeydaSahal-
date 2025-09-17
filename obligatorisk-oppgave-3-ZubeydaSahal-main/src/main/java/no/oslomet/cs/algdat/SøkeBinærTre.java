package no.oslomet.cs.algdat;

import java.util.*;

public class SøkeBinærTre<T>  implements Beholder<T> {

    // En del kode er ferdig implementert, hopp til linje 91 for Oppgave 1

    private static final class Node<T> { // En indre nodeklasse
        private T verdi; // Nodens verdi
        private Node<T> venstre, høyre, forelder; // barn og forelder

        // Konstruktører
        private Node(T verdi, Node<T> v, Node<T> h, Node<T> f) {
            this.verdi = verdi;
            venstre = v; høyre = h; forelder = f;
        }
        private Node(T verdi, Node<T> f) {
            this(verdi, null, null, f);
        }

        @Override
        public String toString() {return verdi.toString();}
    } // class Node

    private final class SBTIterator implements Iterator<T> {
        Node<T> neste;
        public SBTIterator() {
            neste = førstePostorden(rot);
        }

        public boolean hasNext() {
            return (neste != null);
        }

        public T next() {
            Node<T> denne = neste;
            neste = nestePostorden(denne);
            return denne.verdi;
        }
    }

    public Iterator<T> iterator() {
        return new SBTIterator();
    }

    private Node<T> rot;
    private int antall;
    private int endringer;

    private final Comparator<? super T> comp;

    public SøkeBinærTre(Comparator<? super T> c) {
        rot = null; antall = 0;
        comp = c;
    }

    public boolean inneholder(T verdi) {
        if (verdi == null) return false;

        Node<T> p = rot;
        while (p != null) {
            int cmp = comp.compare(verdi, p.verdi);
            if (cmp < 0) p = p.venstre;
            else if (cmp > 0) p = p.høyre;
            else return true;
        }
        return false;
    }

    public int antall() { return antall; }

    public String toStringPostOrder() {
        if (tom()) return "[]";

        StringJoiner s = new StringJoiner(", ", "[", "]");

        Node<T> p = førstePostorden(rot);
        while (p != null) {
            s.add(p.verdi.toString());
            p = nestePostorden(p);
        }

        return s.toString();
    }

    public boolean tom() { return antall == 0; }

    // Oppgave 1
    public boolean leggInn(T verdi) {
        Objects.requireNonNull(verdi, "Ulovlig med nullverdier!");

        Node<T> p = rot; // p starter i roten;
        Node<T> q = null;
        int cmp = 0; //hjelpevariabel

        while (p!= null){
            q = p;              //q er foreldre til p
            cmp = comp.compare(verdi, p.verdi);
            if (cmp<0){
                p=p.venstre;
            }else{
                p= p.høyre;
            }
        }
        //p havner utenfor treet. Vi skal videre til høyere eller venstre
        //men det er ingen node, der skal det legge inn en ny node
        //den blir barn til den siste noden vi passerte
        //venstre barn hvis siste sammenligning var mindre ellers høyere barn

        p = new Node<>(verdi,q);  //Oppretter ny node. Setter pekert til noden foreldre som er q her

        if (q == null)    //q er null som vil si det er ingen noder
            rot = p;      //setter p til rotnoda

        else if (cmp<0)       //q er større enn p
            q.venstre= p; // så setter p til venstre barn
        else
            q.høyre = p;       //ellers setter p til høyere barn;

        antall++;
        endringer++;
        return true;


    }


    // Oppgave 2
    public int antall(T verdi){
        int antallFjernet = 0; //lage en teller variabler;

        if (verdi==null){  //sjekker om verdi er null, og retunerer 0 hvis den er det
            return 0;
        }

        Node<T> p = rot;    //Setter p lik rot


        while (p!=null) {         //skal kjøre den helt til p ikke er null
            int cmp = comp.compare(verdi,p.verdi);

            if (cmp == 0 ) {   //sjekker førtst om p sin verdi er lik verdi
                antallFjernet++;    //plusser hvis den er det

            } if(cmp < 0)   p = p.venstre;                   //hvis den er større
                                //så flytter til høyere
            else
                p=p.høyre;                  //eller flytter den til venstre
        }
        return antallFjernet;        //retunerer antallverdier

    }

    // Oppgave 3
    private Node<T> førstePostorden(Node<T> p) {
        //Første postorden har ikke venstre eller høyre barn
        while(p!=null) {               //While løkke
            if (p.venstre != null)     //Må sjekke om p.venstre ikke er null
                p = p.venstre;
            else if (p.høyre != null)  //sjekker om p.høyere er null
                p = p.høyre;
            else                       //hvis både høyere og venstre er null så skal den retunere første noden.
                return p;
        }
        return null;

    }

    private Node<T> nestePostorden(Node<T> p) {
        if (p.forelder == null) {   //hvis p sin foreldre er null så er roten siste verdi i postorden
            return null;
        }if (p.forelder.høyre == p) {  //hvis p sin foreldre har et høyere barn så skal p sin foreldre retunreres
            return p.forelder;

        }else if (p.forelder.høyre!=null){ //p har ikke høyere barn, men venstre barn så må vi finne førstePostorden for å finne første noden
            return førstePostorden(p.forelder.høyre);

        }else
            return p.forelder;  // p sin foreldre har ikke høyere barn da blir neste i postorden p foreldre
    }


    // Oppgave 4
    public void postOrden(Oppgave<? super T> oppgave) {
        Node <T> p=førstePostorden(rot);

        while (p!=null){
            oppgave.utførOppgave(p.verdi);
            p=nestePostorden(p);
        }

    }

    public void postOrdenRekursiv(Oppgave<? super T> oppgave) {
        postOrdenRekursiv(rot, oppgave); // Ferdig implementert
    }

    private void postOrdenRekursiv(Node<T> p, Oppgave<? super T> oppgave) {
        //Postorden: venstre,høyre også utfør oppgave(skriv ut node)
        if (p.venstre!=null)
            postOrdenRekursiv(p.venstre,oppgave);
        if (p.høyre!=null)
            postOrdenRekursiv(p.høyre,oppgave);
        oppgave.utførOppgave(p.verdi);
    }


    // Oppgave 5
    public boolean fjern(T verdi) {
        if (verdi == null){
            return false;
        }
        Node <T> p = rot, q = null;
        while (p != null)            // Leter etter verdi
        {
            int cmp = comp.compare(verdi,p.verdi);      // Sammenligner
            if (cmp < 0) { q = p; p = p.venstre; }      // Går til venstre
            else if (cmp > 0) { q = p; p = p.høyre; }   // Går til høyre
            else break;                                 // Den søkte verdien ligger i p
        }
        if (p == null) return false;   // Finner ikke verdi

        if (p.venstre == null || p.høyre == null)  // Hvis noden som skal fjernes har 0 eller 1 barn
        {
            Node<T> b = p.venstre != null ? p.venstre : p.høyre;  // b er barnet til p noden
            if (p == rot) {
                rot = b;
            }
            else if (p == q.venstre){
                q.venstre = b;
                if(b != null){
                    b.forelder = q;
                }
            }
            else {
                q.høyre = b;
                if(b != null){
                    b.forelder = q;
                }
            }
        }
        else  // Hvis noden som skal fjernes har 2 barn
        {
            Node<T> s = p, r = p.høyre;   // Finner neste i inorden
            while (r.venstre != null)
            {
                s = r;                      // s er forelder til r
                r = r.venstre;
            }

            p.verdi = r.verdi;   // Kopierer verdien i r til p

            if (s != p) {
                s.venstre = r.høyre;
                if(r.høyre != null){
                    r.høyre.forelder = s;
                }

            }
            else {
                s.høyre = r.høyre;
                if(r.høyre != null){
                    r.høyre.forelder = s;
                }
            }
        }

        antall--;      // Antall noder reduseres, en mindre node i treet
        endringer++;   // Det er gjort en ny endring på treet, så endringer økes
        return true;

    }

    public int fjernAlle(T verdi) {
        if (antall==0)  //sjekker for om treet er tomt
            return 0;

        int antallFjernet  =0;   //lager en teller variabel som skal telle antall verider

        while (fjern(verdi)){         //en while løkke som skal kjøre helt fram til alle veriden er tomme
            antallFjernet++;         //teller hver gang løkken kjøer
        }
        return antallFjernet;    //retunere antallet
    }

    public void nullstill() {
        int temp = antall;                // Tar vare på antall (før noder fjernes)
        nullstiller(rot);              // Kaller på den rekursiv metode (hjelpe metoden)
        rot = null;
        antall = 0;
        endringer += temp;
        // Legger til antall noder som ble fjernet til endringer
    }

    //hjelpemetode som rekursivt nuller ut alle verdier og pekeret i treet
    private void nullstiller(Node<T> p) {
        if(p == null) { return; }

        nullstiller(p.venstre);
        nullstiller(p.høyre);

        // Fjerner alle verider og pekere til nodene
        p.verdi = null;
        p.venstre = null;
        p.høyre = null;
        p.forelder = null;
    }

}