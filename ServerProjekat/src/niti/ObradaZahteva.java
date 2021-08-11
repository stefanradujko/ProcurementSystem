/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package niti;

import domen.Komponenta;
import domen.Narudzbenica;
import domen.Proizvodjac;
import domen.StavkaNarudzbenice;
import domen.TipKomponente;
import domen.Zaposleni;
import interfejsi.Operacije;
import java.net.Socket;
import java.util.ArrayList;
import kom.Komunikacija;
import kontroler.Kontroler;
import transfer.KlijentskiZahtev;
import transfer.ServerskiOdgovor;

/**
 *
 * @author Stefan
 */
public class ObradaZahteva extends Thread {
    Socket soket;
    PokretanjeServera ps;
    boolean radi = true;

    public ObradaZahteva(Socket soket, PokretanjeServera ps) {
        this.soket = soket;
        this.ps = ps;
    }

    @Override
    public void run() {
        while(!isInterrupted()){
            try{
            KlijentskiZahtev kz = Komunikacija.getInstanca().prihvatiZahtev(soket);
            ServerskiOdgovor so = new ServerskiOdgovor();
            switch(kz.getOperacija()){
                case Operacije.PRIJAVI_SE:
                    Zaposleni z = (Zaposleni) kz.getParametar();
                    so = Kontroler.getInstanca().prijavi(z);
                    break;
                case Operacije.DODAJ_PROIZVODJACA:
                    Proizvodjac p = (Proizvodjac) kz.getParametar();
                    so = Kontroler.getInstanca().dodajProizvodjaca(p);
                    break;
                case Operacije.VRATI_SVE_PROIZVODJACE:
                    Proizvodjac pr = (Proizvodjac) kz.getParametar();
                    so = Kontroler.getInstanca().vratiProizvodjace(pr);
                    break;
                case Operacije.VRATI_PROIZVODJACE_ZA_USLOV:
                    Proizvodjac pro = (Proizvodjac) kz.getParametar();
                    so = Kontroler.getInstanca().vratiProizvodjaceUslov(pro);
                    break;
                case Operacije.OBRISI_PROIZVODJACA:
                    Proizvodjac proi = (Proizvodjac) kz.getParametar();
                    so = Kontroler.getInstanca().obrisiProizvodjaca(proi);
                    break;
                case Operacije.IZMENI_PROIZVODJACA:
                    Proizvodjac proiz = (Proizvodjac) kz.getParametar();
                    so = Kontroler.getInstanca().izmeniProizvodjaca(proiz);
                    break;
                case Operacije.VRATI_SVE_TIPOVE:
                    TipKomponente tk = (TipKomponente) kz.getParametar();
                    so = Kontroler.getInstanca().vratiTipove(tk);
                    break;
                case Operacije.DODAJ_KOMPONENTU:
                    Komponenta k = (Komponenta) kz.getParametar();
                    so = Kontroler.getInstanca().dodajKomponentu(k);
                    break;
                case Operacije.VRATI_SVE_KOMPONENTE:
                    Komponenta ko = (Komponenta) kz.getParametar();
                    so = Kontroler.getInstanca().vratiKomponente(ko);
                    break;
                case Operacije.OBRISI_KOMPONENTU:
                    Komponenta kom = (Komponenta) kz.getParametar();
                    so = Kontroler.getInstanca().obrisiKomponentu(kom);
                    break;
                case Operacije.DODAJ_NARUDZBENICU:
                    Narudzbenica n = (Narudzbenica) kz.getParametar();
                    so = Kontroler.getInstanca().dodajNarudzbenicu(n);
                    break;
                case Operacije.VRATI_SVE_NARUDZBENICE:
                    Narudzbenica na = (Narudzbenica) kz.getParametar();
                    so = Kontroler.getInstanca().vratiNarudzbenice(na);
                    break;
                case Operacije.VRATI_STAVKE_ZA_USLOV:
                    StavkaNarudzbenice s = (StavkaNarudzbenice) kz.getParametar();
                    so = Kontroler.getInstanca().vratiStavkeUslov(s);
                    break;
                case Operacije.OBRISI_NARUDZBENICU:
                    Narudzbenica nar = (Narudzbenica) kz.getParametar();
                    so = Kontroler.getInstanca().obrisiNarudzbenicu(nar);
                    break;
                case Operacije.EKSPORTUJ_U_EXCEL:
                    ArrayList<StavkaNarudzbenice> lista = (ArrayList<StavkaNarudzbenice>) kz.getParametar();
                    so = Kontroler.getInstanca().eksportujUExcel(lista);
                    break;
                case Operacije.PROVERI_PRIVILEGIJE:
                    Zaposleni za = (Zaposleni) kz.getParametar();
                    so = Kontroler.getInstanca().proveriPrivilegije(za);
                    break;
                case Operacije.DODAJ_ZAPOSLENOG:
                    Zaposleni zap = (Zaposleni) kz.getParametar();
                    so = Kontroler.getInstanca().dodajZaposlenog(zap);
                    break;
            }
            Komunikacija.getInstanca().posaljiOdgovor(soket, so);
            } catch(Exception ex){
                Kontroler.getInstanca().izbaciKlijenta(this);
            }
        }
    }

    public Socket getSoket() {
        return soket;
    }

    
    
}
