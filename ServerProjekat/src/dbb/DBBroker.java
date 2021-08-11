/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbb;

import interfejsi.DomenskiObjekat;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import properties.PropertyReader;

/**
 *
 * @author Stefan
 */
public class DBBroker {
    Connection konekcija;
    
    public void ucitajDrajver() throws ClassNotFoundException{
        Class.forName(PropertyReader.getInstanca().getDriver());
    }
    
    public void testirajParametre(String url, String username, String password) throws SQLException{
        System.out.println("Testiranje parametara:");
        try {
            ucitajDrajver();
            konekcija = DriverManager.getConnection(url,username,password);
        }catch (SQLException ex) {
            throw new SQLException("Parametri nisu ispravni");
        }catch (ClassNotFoundException ex) { 
            throw new SQLException("Drajver nije pronadjen");
        }
        System.out.println("Sve je u redu");
    }
    
    public void otvoriKonekciju(){
        try {
            ucitajDrajver();
            konekcija = DriverManager.getConnection(PropertyReader.getInstanca().getURL(),PropertyReader.getInstanca().getUsername(),PropertyReader.getInstanca().getPassword());
            konekcija.setAutoCommit(false);
            System.out.println("Uspesno povezivanje sa bazom");
        } catch (SQLException ex) {
            System.out.println("Neuspesno povezivanje: " + ex.getMessage());
        } catch (ClassNotFoundException ex) {
            System.out.println("Drajver nije pronadjen");
         }
    }
    
    public void zatvoriKonekciju(){
        try {
            konekcija.close();
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void commit(){
        try {
            konekcija.commit();
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void rollback(){
        try {
            konekcija.rollback();
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ResultSet select(DomenskiObjekat dom) throws SQLException {
        String upit = "SELECT * FROM "+dom.vratiImeTabele()+ dom.vratiAlias()+ dom.vratiJoin()+dom.whereConn();
        System.out.println(upit);
        Statement s = konekcija.createStatement();
        return s.executeQuery(upit);
    }

    public void insert(DomenskiObjekat dom) throws SQLException {
        String upit = "INSERT INTO "+dom.vratiImeTabele()+dom.vratiPoljaZaInsert()+" VALUES("+dom.vratiVrednostZaInsert()+")";        
        System.out.println(upit);
        Statement s = konekcija.createStatement();
        s.executeUpdate(upit);
    }

    public int getID(DomenskiObjekat dom) throws SQLException {
        String upit = "SELECT MAX("+dom.vratiTabelaID()+") AS id FROM "+dom.vratiImeTabele();
        System.out.println(upit);
        Statement s = konekcija.createStatement();
        ResultSet rs = s.executeQuery(upit);
        Integer id = 0;
        while(rs.next()){
            id = rs.getInt("id");
        } 
        return id;
    }

    public void delete(DomenskiObjekat dom) throws SQLException {
        String upit = "DELETE FROM "+ dom.vratiImeTabele() + " WHERE "+ dom.vratiPK();
        System.out.println(upit);
        Statement s = konekcija.createStatement();
        s.executeUpdate(upit);
    }

    public void update(DomenskiObjekat dom) throws SQLException {
        String upit = "UPDATE "+dom.vratiImeTabele()+ " SET "+dom.vratiVrednostZaPromenu()+" WHERE "+dom.vratiPK();
        System.out.println(upit);
        Statement s = konekcija.createStatement();
        s.executeUpdate(upit);
    }
}
