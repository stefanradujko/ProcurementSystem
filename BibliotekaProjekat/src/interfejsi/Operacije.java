/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfejsi;

import java.io.Serializable;

/**
 *
 * @author Stefan
 */
public interface Operacije extends Serializable {
    public static final int PRIJAVI_SE = 1;
    public static final int DODAJ_PROIZVODJACA = 2;
    public static final int VRATI_SVE_PROIZVODJACE = 3;
    public static final int VRATI_PROIZVODJACE_ZA_USLOV = 4;
    public static final int OBRISI_PROIZVODJACA = 5;
    public static final int IZMENI_PROIZVODJACA = 6;
    public static final int VRATI_SVE_TIPOVE = 7;
    public static final int DODAJ_KOMPONENTU = 8;
    public static final int VRATI_SVE_KOMPONENTE = 9;
    public static final int OBRISI_KOMPONENTU = 10;
    public static final int DODAJ_NARUDZBENICU = 11;
    public static final int VRATI_SVE_NARUDZBENICE = 12;
    public static final int VRATI_STAVKE_ZA_USLOV = 13;
    public static final int OBRISI_NARUDZBENICU = 14;
    public static final int ODJAVI_SVE_KLIJENTE = 15;
    public static final int EKSPORTUJ_U_EXCEL = 16;
    public static final int PROVERI_PRIVILEGIJE = 17;
    public static final int DODAJ_ZAPOSLENOG = 18;
}
