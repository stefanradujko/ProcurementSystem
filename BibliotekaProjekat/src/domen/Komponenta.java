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
public class Komponenta implements DomenskiObjekat {
    private int komponentaID;
    private String nazivKomponenete;
    private double cenaKomponente;
    private int godinaProizvodnje;
    private String kratakOpis;
    private TipKomponente tipKomponente;
    private Proizvodjac proizvodjac;
    private String status;

    public Komponenta() {
    }

    public Komponenta(int komponentaID, String nazivKomponenete, double cenaKomponente, int godinaProizvodnje, String kratakOpis, TipKomponente tipKomponente, Proizvodjac proizvodjac, String status) {
        this.komponentaID = komponentaID;
        this.nazivKomponenete = nazivKomponenete;
        this.cenaKomponente = cenaKomponente;
        this.godinaProizvodnje = godinaProizvodnje;
        this.kratakOpis = kratakOpis;
        this.tipKomponente = tipKomponente;
        this.proizvodjac = proizvodjac;
        this.status = status;
    }

    public Proizvodjac getProizvodjac() {
        return proizvodjac;
    }

    public void setProizvodjac(Proizvodjac proizvodjac) {
        this.proizvodjac = proizvodjac;
    }

    public int getKomponentaID() {
        return komponentaID;
    }

    public void setKomponentaID(int komponentaID) {
        this.komponentaID = komponentaID;
    }

    public String getNazivKomponenete() {
        return nazivKomponenete;
    }

    public void setNazivKomponenete(String nazivKomponenete) {
        this.nazivKomponenete = nazivKomponenete;
    }

    public double getCenaKomponente() {
        return cenaKomponente;
    }

    public void setCenaKomponente(double cenaKomponente) {
        this.cenaKomponente = cenaKomponente;
    }

    public int getGodinaProizvodnje() {
        return godinaProizvodnje;
    }

    public void setGodinaProizvodnje(int godinaProizvodnje) {
        this.godinaProizvodnje = godinaProizvodnje;
    }

    public String getKratakOpis() {
        return kratakOpis;
    }

    public void setKratakOpis(String kratakOpis) {
        this.kratakOpis = kratakOpis;
    }

    public TipKomponente getTipKomponente() {
        return tipKomponente;
    }

    public void setTipKomponente(TipKomponente tipKomponente) {
        this.tipKomponente = tipKomponente;
    }
    
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return nazivKomponenete;
    }

    @Override
    public String vratiImeTabele() {
        return "Komponenta";
    }

    @Override
    public String vratiPoljaZaInsert() {
        return "(nazivKomponente, cenaKomponente, godinaProizvodnje, kratakOpis, tipID, proizvodjacID, status)";
    }

    @Override
    public String vratiVrednostZaInsert() {
        return "'" + nazivKomponenete + "', '" + cenaKomponente + "', '" + godinaProizvodnje + "', '" + kratakOpis + "', '" + tipKomponente.getTipID() + "', '" + proizvodjac.getProizvodjacID() + "', '" + status + "'";
    }

    @Override
    public List<DomenskiObjekat> vratiListuPoRS(ResultSet rs) {
        List<DomenskiObjekat> lista = new ArrayList<>();
        try {
            while (rs.next()) {
                int pid = rs.getInt("proizvodjacID");
                String nazivP = rs.getString("nazivProizvodjaca");
                String email = rs.getString("emailProizvodjaca");
                Proizvodjac p = new Proizvodjac(pid, nazivP, email);
                
                int tid = rs.getInt("tipID");
                String nazivT = rs.getString("nazivTipa");
                TipKomponente tk = new TipKomponente(tid, nazivT);
                
                int id = rs.getInt("komponentaID");
                String nazivK = rs.getString("nazivKomponente");
                double cena = rs.getDouble("cenaKomponente");
                String opis = rs.getString("kratakOpis");
                int godina = rs.getInt("godinaProizvodnje");
                String stat = rs.getString("status");
                Komponenta k = new Komponenta(id, nazivK, cena, godina, opis,tk, p, stat);
                lista.add(k);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    @Override
    public String vratiTabelaID() {
        return "komponentaID";
    }

    @Override
    public String vratiPK() {
        return "komponentaID = " + "'" + komponentaID + "'";
    }

    @Override
    public void postaviVrednostPoRS(ResultSet rs) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String vratiVrednostZaPromenu() {
        return "status = 'Povucen'";
    }

    @Override
    public String vratiJoin() {
        return "JOIN TipKomponente tk ON k.tipID = tk.tipID JOIN Proizvodjac p ON k.proizvodjacID = p.proizvodjacID";
    }

    @Override
    public String vratiAlias() {
        return " k ";
    }

    @Override
    public String whereConn() {
        return " WHERE k.status = 'Aktivan'";
    }

    
    
    
}
