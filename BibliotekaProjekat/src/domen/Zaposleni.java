/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import interfejsi.DomenskiObjekat;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Stefan
 */
public class Zaposleni implements DomenskiObjekat{
    private int zaposleniID;
    private String ime;
    private String prezime;
    private String korisnickoIme;
    private String sifra;

    public Zaposleni() {
    }

    public Zaposleni(int zaposleniID, String ime, String prezime, String korisnickoIme, String sifra) {
        this.zaposleniID = zaposleniID;
        this.ime = ime;
        this.prezime = prezime;
        this.korisnickoIme = korisnickoIme;
        this.sifra = sifra;
    }

    public String getSifra() {
        return sifra;
    }

    public void setSifra(String sifra) {
        this.sifra = sifra;
    }

    public int getZaposleniID() {
        return zaposleniID;
    }

    public void setZaposleniID(int zaposleniID) {
        this.zaposleniID = zaposleniID;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }

    @Override
    public String toString() {
        return ime + " " + prezime;
    }

    @Override
    public String vratiImeTabele() {
        return "Zaposleni";
    }

    @Override
    public String vratiPoljaZaInsert() {
        return "(ZaposleniID,Ime,Prezime,KorisnickoIme,Sifra)";
    }

    @Override
    public String vratiVrednostZaInsert() {
        return "'" + zaposleniID + "', '" + ime + "', '" + prezime + "', '" + korisnickoIme + "', '" + sifra + "'";
    }

    @Override
    public List<DomenskiObjekat> vratiListuPoRS(ResultSet rs) {
        List<DomenskiObjekat> lista = new ArrayList<>();
        try {
            while (rs.next()) {
                int id = rs.getInt("zaposleniID");
                String i = rs.getString("ime");
                String prez = rs.getString("prezime");
                String korI = rs.getString("korisnickoIme");
                String sif = rs.getString("sifra");
                Zaposleni z = new Zaposleni(id, i, prez, korI, sif);
                lista.add(z);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    @Override
    public String vratiTabelaID() {
        return "zaposleniID";
    }

    @Override
    public String vratiPK() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void postaviVrednostPoRS(ResultSet rs) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String vratiVrednostZaPromenu() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String vratiJoin() {
        return "";
    }

    @Override
    public String vratiAlias() {
        return " z ";
    }

    @Override
    public String whereConn() {
        return " where z.korisnickoIme = " + "'" +korisnickoIme+"'" + " and z.sifra = " + "'"+sifra+"'";
    }
    
    
}
