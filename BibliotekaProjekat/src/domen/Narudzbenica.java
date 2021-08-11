/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import interfejsi.DomenskiObjekat;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Stefan
 */
public class Narudzbenica implements DomenskiObjekat {
    private int brojNarudzbenice;
    private Date datumKreiranja;
    private double ukupnaCena;
    private Zaposleni zaposleni;
    private Proizvodjac proizvodjac;
    private ArrayList<StavkaNarudzbenice> stavke;
    private String status;

    public Narudzbenica() {
    }

    public Narudzbenica(int brojNarudzbenice, Date datumKreiranja, double ukupnaCena, Zaposleni zaposleni, Proizvodjac proizvodjac, String status) {
        this.brojNarudzbenice = brojNarudzbenice;
        this.datumKreiranja = datumKreiranja;
        this.ukupnaCena = ukupnaCena;
        this.zaposleni = zaposleni;
        this.proizvodjac = proizvodjac;
        this.status = status;
    }

    public Proizvodjac getProizvodjac() {
        return proizvodjac;
    }

    public void setProizvodjac(Proizvodjac proizvodjac) {
        this.proizvodjac = proizvodjac;
    }

    public int getBrojNarudzbenice() {
        return brojNarudzbenice;
    }

    public void setBrojNarudzbenice(int brojNarudzbenice) {
        this.brojNarudzbenice = brojNarudzbenice;
    }

    public Date getDatumKreiranja() {
        return datumKreiranja;
    }

    public void setDatumKreiranja(Date datumKreiranja) {
        this.datumKreiranja = datumKreiranja;
    }

    public double getUkupnaCena() {
        return ukupnaCena;
    }

    public void setUkupnaCena(double ukupnaCena) {
        this.ukupnaCena = ukupnaCena;
    }

    public Zaposleni getZaposleni() {
        return zaposleni;
    }

    public void setZaposleni(Zaposleni zaposleni) {
        this.zaposleni = zaposleni;
    }
    
     public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String vratiImeTabele() {
        return "Narudzbenica";
    }

    @Override
    public String vratiPoljaZaInsert() {
        return "(datumKreiranja, ukupnaCena, zaposleniID, proizvodjacID, status)";
    }

    @Override
    public String vratiVrednostZaInsert() {
        return "'" + new java.sql.Date(datumKreiranja.getTime()) + "', '" + ukupnaCena + "', '" + zaposleni.getZaposleniID() + "', '" + proizvodjac.getProizvodjacID() + "', '" + status + "'";
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
                
                int zid = rs.getInt("zaposleniID");
                String ime = rs.getString("ime");
                String prezime = rs.getString("prezime");
                Zaposleni z = new Zaposleni(zid, ime, prezime, null, null);
                
                int brojN = rs.getInt("brojNarudzbenice");
                Date dat = rs.getDate("datumKreiranja");
                double ukupnaC = rs.getDouble("ukupnaCena");
                String stat = rs.getString("Status");
                Narudzbenica n = new Narudzbenica(brojN, dat, ukupnaC, z, p, stat);
                lista.add(n);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    @Override
    public String vratiTabelaID() {
        return "brojNarudzbenice";
    }

    @Override
    public String vratiPK() {
        return "brojNarudzbenice = " + "'" + brojNarudzbenice + "'";
    }

    @Override
    public void postaviVrednostPoRS(ResultSet rs) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String vratiVrednostZaPromenu() {
        return "status = 'Obradjena'";
    }

    @Override
    public String vratiJoin() {
        return "JOIN Zaposleni z ON n.zaposleniID = z.ZaposleniID JOIN Proizvodjac p ON n.proizvodjacID = p.proizvodjacID";
    }

    @Override
    public String vratiAlias() {
        return " n ";
    }

    @Override
    public String whereConn() {
        return "";
    }

    public ArrayList<StavkaNarudzbenice> getStavke() {
        return stavke;
    }

    public void setStavke(ArrayList<StavkaNarudzbenice> stavke) {
        this.stavke = stavke;
    }

}
