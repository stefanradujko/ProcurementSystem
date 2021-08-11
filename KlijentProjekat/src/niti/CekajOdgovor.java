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
import forme.FormaBrisanjeKomp;
import forme.FormaDodavanjeZap;
import forme.FormaLogIn;
import forme.FormaPBNar;
import forme.FormaPIBPro;
import forme.FormaPrikazNar;
import forme.FormaUnosKomp;
import forme.FormaUnosNar;
import forme.FormaUnosPro;
import forme.GlavnaKlijentskaForma;
import interfejsi.Operacije;
import java.util.ArrayList;
import kom.Komunikacija;
import sesija.Sesija;
import transfer.ServerskiOdgovor;

/**
 *
 * @author Stefan
 */
public class CekajOdgovor extends Thread {
    FormaLogIn fli;
    GlavnaKlijentskaForma gkf;
    FormaUnosPro fup;
    FormaPIBPro fpib;
    FormaUnosKomp fuk;
    FormaBrisanjeKomp fbk;
    FormaUnosNar fun;
    FormaPBNar fpbn;
    FormaDodavanjeZap fdz;
    boolean radi = true;

    public CekajOdgovor(FormaLogIn fli) {
        this.fli = fli;
    }

    @Override
    public void run() {
        while(radi){
            ServerskiOdgovor so = Komunikacija.getInstanca().primiOdgovor();
            switch(so.getOperacija()){
                case Operacije.PRIJAVI_SE:
                    ArrayList<Zaposleni> lista = (ArrayList<Zaposleni>) so.getOdgovor();
                    if(lista.isEmpty()){
                        fli.neuspesnoPrijavljivanje();
                    } else{
                        Zaposleni z = lista.get(0);
                        Sesija.getInstanca().setUlogovaniZaposleni(z);
                        gkf = fli.uspesnoPrijavljivanje();
                    }
                    break;
                case Operacije.DODAJ_PROIZVODJACA:
                    fup = gkf.getFormaUP();
                    fup.dodavanjeProizvodjaca(so.getPoruka());
                    break;
                case Operacije.VRATI_SVE_PROIZVODJACE:
                    fpib = gkf.getFormaPIB();
                    fuk = gkf.getFormaUK();
                    fun = gkf.getFormaUN();
                    ArrayList<Proizvodjac> sviProizvodjaci = (ArrayList<Proizvodjac>) so.getOdgovor();
                    if(sviProizvodjaci.isEmpty()){
                        if(fpib != null){
                            fpib.neuspesnoVracanje();
                        }
                        if(fuk != null){
                            fuk.neuspesnoVracanjeProizvodjaca();
                        }
                        if(fun != null){
                            fun.neuspesnoVracanjeProizvodjaca();
                        }
                    } else{
                        if(fpib != null){
                            fpib.vraceniProizvodjaci(sviProizvodjaci);
                        }
                        if(fuk != null){
                            fuk.vraceniProizvodjaci(sviProizvodjaci);
                        }
                        if(fun != null){
                            fun.vraceniProizvodjaci(sviProizvodjaci);
                        }
                    }
                    break;
                case Operacije.VRATI_PROIZVODJACE_ZA_USLOV:
                    fpib = gkf.getFormaPIB();
                    ArrayList<Proizvodjac> listaProizvodjaca = (ArrayList<Proizvodjac>) so.getOdgovor();
                    if(listaProizvodjaca.isEmpty()){
                        fpib.neuspesnoVracanje();
                    } else{
                        fpib.vraceniProizvodjaciZaUslov(listaProizvodjaca);
                    }
                    break;
                case Operacije.OBRISI_PROIZVODJACA:
                    fpib = gkf.getFormaPIB();
                    fpib.brisanjeProizvodjaca(so.getPoruka());
                    break;
                case Operacije.IZMENI_PROIZVODJACA:
                    fpib = gkf.getFormaPIB();
                    fpib.izmenaProizvodjaca(so.getPoruka());
                    break;
                case Operacije.VRATI_SVE_TIPOVE:
                    fuk = gkf.getFormaUK();
                    ArrayList<TipKomponente> sviTipovi = (ArrayList<TipKomponente>) so.getOdgovor();
                    if(sviTipovi.isEmpty()){
                        fuk.neuspesnoVracanjeTipova();
                    } else{
                        fuk.vraceniTipovi(sviTipovi);
                    }
                    break;
                case Operacije.DODAJ_KOMPONENTU:
                    fuk = gkf.getFormaUK();
                    fuk.dodavanjeKomponente(so.getPoruka());
                    break;
                case Operacije.VRATI_SVE_KOMPONENTE:
                    fbk = gkf.getFormaBK();
                    fun = gkf.getFormaUN();
                    ArrayList<Komponenta> sveKomponente = (ArrayList<Komponenta>) so.getOdgovor();
                    if(sveKomponente.isEmpty()){
                        if(fbk != null){
                            fbk.neuspesnoVracanjeKomponenti();
                        }
                        if(fun != null){
                            fun.neuspesnoVracanjeKomponenti();
                        }
                    } else{
                        if(fbk != null){
                            fbk.vraceneKomponente(sveKomponente);
                        }
                        if(fun != null){
                            fun.vraceneKomponente(sveKomponente);
                        }
                    }
                    break;
                case Operacije.OBRISI_KOMPONENTU:
                    fbk = gkf.getFormaBK();
                    fbk.brisanjeKomponente(so.getPoruka());
                    break;
                case Operacije.DODAJ_NARUDZBENICU:
                    fun = gkf.getFormaUN();
                    fun.dodavanjeNarudzbenice(so.getPoruka());
                    break;
                case Operacije.VRATI_SVE_NARUDZBENICE:
                    fpbn = gkf.getFormaPBN();
                    ArrayList<Narudzbenica> sveNarudzbenice = (ArrayList<Narudzbenica>) so.getOdgovor();
                    if(sveNarudzbenice.isEmpty()){
                        fpbn.neuspesnoVracanjeNarudzbenica();
                    } else{
                        fpbn.vraceneNarudzbenice(sveNarudzbenice);
                    }
                    break;
                case Operacije.VRATI_STAVKE_ZA_USLOV:
                    fpbn = gkf.getFormaPBN();
                    ArrayList<StavkaNarudzbenice> listaStavki = (ArrayList<StavkaNarudzbenice>) so.getOdgovor();
                    if(listaStavki.isEmpty()){
                        fpbn.neuspesnoVracanjeStavki();
                    } else{
                        fpbn.vraceneStavke(listaStavki);
                    }
                    break;
                case Operacije.OBRISI_NARUDZBENICU:
                    fpbn = gkf.getFormaPBN();
                    fpbn.brisanjeNarudzbenice(so.getPoruka());
                    break;
                case Operacije.ODJAVI_SVE_KLIJENTE:
                    gkf.odjavi(so.getPoruka());
                    break;
                case Operacije.EKSPORTUJ_U_EXCEL:
                    fpbn = gkf.getFormaPBN();
                    fpbn.eksport(so.getPoruka());
                    break;
                case Operacije.PROVERI_PRIVILEGIJE:
                    gkf.proverenePrivilegije((boolean)so.getOdgovor());
                    fpbn = gkf.getFormaPBN();
                    if(fpbn != null){
                        fpbn.proverenePrivilegije((boolean) so.getOdgovor());
                    }
                    break;
                case Operacije.DODAJ_ZAPOSLENOG:
                    fdz = gkf.getFormaDZ();
                    fdz.dodavanjeZaposlenog(so.getPoruka());
                    break;
            }
        }
    }
    
    
}
