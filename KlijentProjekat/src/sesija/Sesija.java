/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sesija;

import domen.Zaposleni;

/**
 *
 * @author Stefan
 */
public class Sesija {
    private static Sesija instanca;
    private Zaposleni ulogovaniZaposleni;

    private Sesija() {
    }

    public static Sesija getInstanca() {
        if (instanca == null) {
            instanca = new Sesija();
        }
        return instanca;
    }

    public Zaposleni getUlogovaniZaposleni() {
        return ulogovaniZaposleni;
    }

    public void setUlogovaniZaposleni(Zaposleni ulogovaniZaposleni) {
        this.ulogovaniZaposleni = ulogovaniZaposleni;
    }
}
