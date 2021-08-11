/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kontroler;

import dbb.DBBroker;
import domen.Komponenta;
import domen.Narudzbenica;
import domen.Proizvodjac;
import domen.StavkaNarudzbenice;
import domen.TipKomponente;
import domen.Zaposleni;
import excel.ExcelUpis;
import interfejsi.Operacije;
import java.sql.SQLException;
import java.util.ArrayList;
import kom.Komunikacija;
import niti.ObradaZahteva;
import operacije.AzuriranjeNarudzbenice;
import operacije.BrisanjeKomponente;
import operacije.BrisanjeNarudzbenice;
import operacije.BrisanjeProizvodjaca;
import operacije.DodavanjeKomponente;
import operacije.DodavanjeNarudzbenice;
import operacije.DodavanjeProizvodjaca;
import operacije.DodavanjeZaposlenog;
import operacije.IzmenaProizvodjaca;
import operacije.PrijavljivanjeZaposlenog;
import operacije.VracanjeKomponenti;
import operacije.VracanjeNarudzbenica;
import operacije.VracanjeProizvodjaca;
import operacije.VracanjeProizvodjacaUslov;
import operacije.VracanjeStavkiUslov;
import operacije.VracanjeTipova;
import shiro.Shiro;
import transfer.ServerskiOdgovor;

/**
 *
 * @author Stefan
 */
public class Kontroler {
    private static Kontroler instanca;
    ArrayList<ObradaZahteva> listaKorisnika;

    DBBroker db;
    
    private Kontroler(){
        db = new DBBroker();
        listaKorisnika = new ArrayList<>();
    }
    
    public static Kontroler getInstanca(){
        if(instanca == null){
            instanca = new Kontroler();
        }
        return instanca;
    }

    public void testirajParametre(String url, String user, String pass) throws SQLException {
        db.testirajParametre(url, user, pass);
    }    

    public ServerskiOdgovor prijavi(Zaposleni z){
        z.setSifra(Shiro.getInstanca().enkriptuj(z.getSifra()));
        ServerskiOdgovor so = new PrijavljivanjeZaposlenog().procesTransakcije(z);
        ArrayList<Zaposleni> lista = (ArrayList<Zaposleni>) so.getOdgovor();
        if(!lista.isEmpty()){
            Shiro.getInstanca().prijavi(z.getKorisnickoIme(), z.getSifra());
        }
        return so;
    }

    public ServerskiOdgovor dodajProizvodjaca(Proizvodjac p) {
        return new DodavanjeProizvodjaca().procesTransakcije(p);
    }

    public ServerskiOdgovor vratiProizvodjace(Proizvodjac pr) {
        return new VracanjeProizvodjaca().procesTransakcije(pr);
    }

    public ServerskiOdgovor vratiProizvodjaceUslov(Proizvodjac pro) {
        return new VracanjeProizvodjacaUslov().procesTransakcije(pro);
    }

    public ServerskiOdgovor obrisiProizvodjaca(Proizvodjac proi) {
        return new BrisanjeProizvodjaca().procesTransakcije(proi);
    }

    public ServerskiOdgovor izmeniProizvodjaca(Proizvodjac proiz) {
        return new IzmenaProizvodjaca().procesTransakcije(proiz);
    }

    public ServerskiOdgovor vratiTipove(TipKomponente tk) {
        return new VracanjeTipova().procesTransakcije(tk);
    }

    public ServerskiOdgovor dodajKomponentu(Komponenta k) {
        return new DodavanjeKomponente().procesTransakcije(k);
    }

    public ServerskiOdgovor vratiKomponente(Komponenta ko) {
        return new VracanjeKomponenti().procesTransakcije(ko);
    }

    public ServerskiOdgovor obrisiKomponentu(Komponenta kom) {
        return new BrisanjeKomponente().procesTransakcije(kom);
    }

    public ServerskiOdgovor dodajNarudzbenicu(Narudzbenica n) {
        return new DodavanjeNarudzbenice().procesTransakcije(n);
    }

    public ServerskiOdgovor vratiNarudzbenice(Narudzbenica na) {
        return new VracanjeNarudzbenica().procesTransakcije(na);
    }

    public ServerskiOdgovor vratiStavkeUslov(StavkaNarudzbenice s) {
        return new VracanjeStavkiUslov().procesTransakcije(s);
    }

    public ServerskiOdgovor obrisiNarudzbenicu(Narudzbenica nar) {
        return new BrisanjeNarudzbenice().procesTransakcije(nar);
    }
    
    public void dodajKlijenta(ObradaZahteva oz) {
        listaKorisnika.add(oz);
    }
    
    public void izbaciKlijenta(ObradaZahteva klijentNit) {
        klijentNit.interrupt();
        listaKorisnika.remove(klijentNit);
    }

    public void odjaviSveKlijente() {
        ArrayList<ObradaZahteva> zaIzbacivanje = new ArrayList<>();
        for(ObradaZahteva klijent : listaKorisnika){
            zaIzbacivanje.add(klijent);
            ServerskiOdgovor so = new ServerskiOdgovor(Operacije.ODJAVI_SVE_KLIJENTE, "Server je zaustavljen", true);
            Komunikacija.getInstanca().posaljiOdgovor(klijent.getSoket(), so);
        }
        for(ObradaZahteva klijentZaIzb : zaIzbacivanje){
            izbaciKlijenta(klijentZaIzb);
        }
    }

    public ServerskiOdgovor eksportujUExcel(ArrayList<StavkaNarudzbenice> lista) {
        ServerskiOdgovor so = new ExcelUpis().upisiUExcel(lista);
        if((boolean)so.getOdgovor()){
            azurirajStatus(lista.get(0).getNarudzbenica());
        }
        return so;
    }

    private void azurirajStatus(Narudzbenica n) {
        new AzuriranjeNarudzbenice().procesTransakcije(n);
    }

    public ServerskiOdgovor proveriPrivilegije(Zaposleni za) {
        ServerskiOdgovor so = new ServerskiOdgovor();
        so.setOperacija(Operacije.PROVERI_PRIVILEGIJE);
        so.setOdgovor(Shiro.getInstanca().testirajPrivilegije(za.getKorisnickoIme()));
        return so;
    }

    public ServerskiOdgovor dodajZaposlenog(Zaposleni zap) {
        zap.setSifra(Shiro.getInstanca().enkriptuj(zap.getSifra()));
        ServerskiOdgovor so = new DodavanjeZaposlenog().procesTransakcije(zap);
        if((boolean) so.getOdgovor()){
            Shiro.getInstanca().upisiUIni(zap);
        }
        return so;
    }

}
