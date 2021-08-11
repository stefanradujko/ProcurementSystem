/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kontroler;

import domen.Komponenta;
import domen.Narudzbenica;
import domen.Proizvodjac;
import domen.StavkaNarudzbenice;
import domen.TipKomponente;
import domen.Zaposleni;
import interfejsi.Operacije;
import java.util.ArrayList;
import kom.Komunikacija;
import transfer.KlijentskiZahtev;

/**
 *
 * @author Stefan
 */
public class Kontroler {
    private static Kontroler instanca;
    

    private Kontroler() {
        
    }

    public static Kontroler getInstanca() {
        if (instanca == null) {
            instanca = new Kontroler();
        }
        Komunikacija.getInstanca();
        return instanca;
    }

    public void prijavljivanje(String user, String pass) {
        Zaposleni z = new Zaposleni();
        z.setKorisnickoIme(user);
        z.setSifra(pass);
        KlijentskiZahtev kz = new KlijentskiZahtev(Operacije.PRIJAVI_SE, z);
        Komunikacija.getInstanca().posaljiZahtev(kz);
    }

    public void unosProizvodjaca(String naziv, String email) {
        Proizvodjac p = new Proizvodjac();
        p.setNazivProizvodjaca(naziv);
        p.setEmailProizvodjaca(email);
        KlijentskiZahtev kz = new KlijentskiZahtev(Operacije.DODAJ_PROIZVODJACA, p);
        Komunikacija.getInstanca().posaljiZahtev(kz);
    }

    public void vratiSveProizvodjace() {
        KlijentskiZahtev kz = new KlijentskiZahtev(Operacije.VRATI_SVE_PROIZVODJACE, new Proizvodjac());
        Komunikacija.getInstanca().posaljiZahtev(kz);
    }

    public void vratiProizvodjaceZaUslov(String kriterijum) {
        Proizvodjac p = new Proizvodjac();
        p.setNazivProizvodjaca(kriterijum);
        p.setEmailProizvodjaca(kriterijum);
        KlijentskiZahtev kz = new KlijentskiZahtev(Operacije.VRATI_PROIZVODJACE_ZA_USLOV, p);
        Komunikacija.getInstanca().posaljiZahtev(kz);
    }

    public void obrisiProizvodjaca(Proizvodjac p) {
        KlijentskiZahtev kz = new KlijentskiZahtev(Operacije.OBRISI_PROIZVODJACA, p);
        Komunikacija.getInstanca().posaljiZahtev(kz);
    }

    public void izmeniProizvodjaca(Proizvodjac p) {
        KlijentskiZahtev kz = new KlijentskiZahtev(Operacije.IZMENI_PROIZVODJACA, p);
        Komunikacija.getInstanca().posaljiZahtev(kz);
    }

    public void vratiSveTipove() {
        KlijentskiZahtev kz = new KlijentskiZahtev(Operacije.VRATI_SVE_TIPOVE, new TipKomponente());
        Komunikacija.getInstanca().posaljiZahtev(kz);
    }

    public void dodajKomponentu(Komponenta k) {
        KlijentskiZahtev kz = new KlijentskiZahtev(Operacije.DODAJ_KOMPONENTU, k);
        Komunikacija.getInstanca().posaljiZahtev(kz);
    }

    public void vratiKomponente() {
        KlijentskiZahtev kz = new KlijentskiZahtev(Operacije.VRATI_SVE_KOMPONENTE, new Komponenta());
        Komunikacija.getInstanca().posaljiZahtev(kz);
    }

    public void obrisiKomponentu(Komponenta k) {
        KlijentskiZahtev kz = new KlijentskiZahtev(Operacije.OBRISI_KOMPONENTU, k);
        Komunikacija.getInstanca().posaljiZahtev(kz);
    }

    public void unosNarudzbenice(Narudzbenica n) {
        KlijentskiZahtev kz = new KlijentskiZahtev(Operacije.DODAJ_NARUDZBENICU, n);
        Komunikacija.getInstanca().posaljiZahtev(kz);
    }

    public void vratiNarudzbenice() {
        KlijentskiZahtev kz = new KlijentskiZahtev(Operacije.VRATI_SVE_NARUDZBENICE, new Narudzbenica());
        Komunikacija.getInstanca().posaljiZahtev(kz);
    }

    public void vratiStavkeUslov(Narudzbenica n) {
        StavkaNarudzbenice s = new StavkaNarudzbenice();
        s.setNarudzbenica(n);
        KlijentskiZahtev kz = new KlijentskiZahtev(Operacije.VRATI_STAVKE_ZA_USLOV, s);
        Komunikacija.getInstanca().posaljiZahtev(kz);
    }

    public void obrisiNarudzbenicu(Narudzbenica n) {
        KlijentskiZahtev kz = new KlijentskiZahtev(Operacije.OBRISI_NARUDZBENICU, n);
        Komunikacija.getInstanca().posaljiZahtev(kz);
    }

    public void exportujUExcel(ArrayList<StavkaNarudzbenice> lista) {
        KlijentskiZahtev kz = new KlijentskiZahtev(Operacije.EKSPORTUJ_U_EXCEL, lista);
        Komunikacija.getInstanca().posaljiZahtev(kz);
    }

    public void proveravanjePrivilegija(Zaposleni z) {
        KlijentskiZahtev kz = new KlijentskiZahtev(Operacije.PROVERI_PRIVILEGIJE, z);
        Komunikacija.getInstanca().posaljiZahtev(kz);
    }

    public void dodajZaposlenog(Zaposleni z) {
        KlijentskiZahtev kz = new KlijentskiZahtev(Operacije.DODAJ_ZAPOSLENOG, z);
        Komunikacija.getInstanca().posaljiZahtev(kz);
    }
}
