/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfejsi;

import java.io.Serializable;
import java.sql.ResultSet;
import java.util.List;

/**
 *
 * @author Stefan
 */
public interface DomenskiObjekat extends Serializable {
    
    public String vratiImeTabele();

    public String vratiPoljaZaInsert();

    public String vratiVrednostZaInsert();

    public List<DomenskiObjekat> vratiListuPoRS(ResultSet rs);

    public String vratiTabelaID();

    public String vratiPK();

    public void postaviVrednostPoRS(ResultSet rs);

    public String vratiVrednostZaPromenu();
    
    public String vratiJoin();
    
    public String vratiAlias();
    
    public String whereConn();
}
