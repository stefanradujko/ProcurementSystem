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
public class StavkaNarudzbenice implements DomenskiObjekat {
    private Narudzbenica narudzbenica;
    private int brojStavke;
    private int kolicinaKomponente;
    private double cenaKomponente;
    private Komponenta komponenta;

    public StavkaNarudzbenice() {
    }

    public StavkaNarudzbenice(Narudzbenica narudzbenica, int brojStavke, int kolicinaKomponente, double cenaKomponente, Komponenta komponenta) {
        this.narudzbenica = narudzbenica;
        this.brojStavke = brojStavke;
        this.kolicinaKomponente = kolicinaKomponente;
        this.cenaKomponente = cenaKomponente;
        this.komponenta = komponenta;
    }

    public Komponenta getKomponenta() {
        return komponenta;
    }

    public void setKomponenta(Komponenta komponenta) {
        this.komponenta = komponenta;
    }

    public Narudzbenica getNarudzbenica() {
        return narudzbenica;
    }

    public void setNarudzbenica(Narudzbenica narudzbenica) {
        this.narudzbenica = narudzbenica;
    }

    public int getBrojStavke() {
        return brojStavke;
    }

    public void setBrojStavke(int brojStavke) {
        this.brojStavke = brojStavke;
    }

    public int getKolicinaKomponente() {
        return kolicinaKomponente;
    }

    public void setKolicinaKomponente(int kolicinaKomponente) {
        this.kolicinaKomponente = kolicinaKomponente;
    }

    public double getCenaKomponente() {
        return cenaKomponente;
    }

    public void setCenaKomponente(double cenaKomponente) {
        this.cenaKomponente = cenaKomponente;
    }

    @Override
    public String vratiImeTabele() {
        return "StavkaNarudzbenice";
    }

    @Override
    public String vratiPoljaZaInsert() {
        return "(brojNarudzbenice, kolicinaKomponente, cenaKomponente, komponentaID)";
    }

    @Override
    public String vratiVrednostZaInsert() {
        return "'" + narudzbenica.getBrojNarudzbenice() + "', '" + kolicinaKomponente + "', '" + cenaKomponente + "', '" + komponenta.getKomponentaID() + "'";
    }

    @Override
    public List<DomenskiObjekat> vratiListuPoRS(ResultSet rs) {
        List<DomenskiObjekat> lista = new ArrayList<>();
        try {
            while (rs.next()) {
                int kid = rs.getInt("komponentaID");
                String nazivK = rs.getString("nazivKomponente");
                double cena = rs.getDouble("cenaKomponente");
                String opis = rs.getString("kratakOpis");
                int godina = rs.getInt("godinaProizvodnje");
                Komponenta k = new Komponenta(kid, nazivK, cena, godina, opis, null, null, null);
                
                int id = rs.getInt("brojStavke");
                int kolS = rs.getInt("kolicinaKomponente");
                double cenaS = rs.getDouble("cenaKomponente");
                StavkaNarudzbenice s = new StavkaNarudzbenice(narudzbenica, id, kolS, cenaS, k);
                lista.add(s);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    @Override
    public String vratiTabelaID() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String vratiPK() {
        return "brojStavke = " + "'" + brojStavke + "' AND brojNarudzbenice = '" + narudzbenica.getBrojNarudzbenice() + "'";
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
        return " JOIN Komponenta k ON s.komponentaID = k.komponentaID ";
    }

    @Override
    public String vratiAlias() {
        return " s ";
    }

    @Override
    public String whereConn() {
        return " WHERE s.brojNarudzbenice = '" + narudzbenica.getBrojNarudzbenice() + "'";
    }
    
    
}
