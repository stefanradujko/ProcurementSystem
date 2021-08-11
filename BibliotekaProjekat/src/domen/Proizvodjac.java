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
public class Proizvodjac implements DomenskiObjekat {
    private int proizvodjacID;
    private String nazivProizvodjaca;
    private String emailProizvodjaca;

    public Proizvodjac() {
    }

    public Proizvodjac(int proizvodjacID, String nazivProizvodjaca, String emailProizvodjaca) {
        this.proizvodjacID = proizvodjacID;
        this.nazivProizvodjaca = nazivProizvodjaca;
        this.emailProizvodjaca = emailProizvodjaca;
    }

    public String getEmailProizvodjaca() {
        return emailProizvodjaca;
    }

    public void setEmailProizvodjaca(String emailProizvodjaca) {
        this.emailProizvodjaca = emailProizvodjaca;
    }

    public int getProizvodjacID() {
        return proizvodjacID;
    }

    public void setProizvodjacID(int proizvodjacID) {
        this.proizvodjacID = proizvodjacID;
    }

    public String getNazivProizvodjaca() {
        return nazivProizvodjaca;
    }

    public void setNazivProizvodjaca(String nazivProizvodjaca) {
        this.nazivProizvodjaca = nazivProizvodjaca;
    }

    @Override
    public String toString() {
        return nazivProizvodjaca;
    }

    @Override
    public String vratiImeTabele() {
        return "Proizvodjac";
    }

    @Override
    public String vratiPoljaZaInsert() {
        return "(nazivProizvodjaca, emailProizvodjaca)";
    }

    @Override
    public String vratiVrednostZaInsert() {
        return "'" + nazivProizvodjaca + "', '" + emailProizvodjaca + "'";
    }

    @Override
    public List<DomenskiObjekat> vratiListuPoRS(ResultSet rs) {
        List<DomenskiObjekat> lista = new ArrayList<>();
        try {
            while (rs.next()) {
                int id = rs.getInt("proizvodjacID");
                String naziv = rs.getString("nazivProizvodjaca");
                String email = rs.getString("emailProizvodjaca");
                Proizvodjac p = new Proizvodjac(id, naziv, email);
                lista.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    @Override
    public String vratiTabelaID() {
        return "proizvodjacID";
    }

    @Override
    public String vratiPK() {
        return "proizvodjacID = " + "'" + proizvodjacID + "'";
    }

    @Override
    public void postaviVrednostPoRS(ResultSet rs) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String vratiVrednostZaPromenu() {
        return "nazivProizvodjaca = '" + nazivProizvodjaca + "',  emailProizvodjaca = '" + emailProizvodjaca + "'";
    }

    @Override
    public String vratiJoin() {
        return "";
    }

    @Override
    public String vratiAlias() {
        return " p ";
    }

    @Override
    public String whereConn() {
        if(nazivProizvodjaca != null && emailProizvodjaca != null && nazivProizvodjaca.equals(emailProizvodjaca)){
            return "WHERE p.nazivProizvodjaca LIKE '%" + nazivProizvodjaca+ "%' OR p.emailProizvodjaca LIKE '%" + emailProizvodjaca + "%'";
        }
        return "";
    }
    
    
}
