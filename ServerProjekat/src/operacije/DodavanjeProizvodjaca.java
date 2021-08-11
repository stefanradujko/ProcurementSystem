/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operacije;

import domen.Proizvodjac;
import interfejsi.DomenskiObjekat;
import interfejsi.Operacije;
import java.sql.SQLException;
import transfer.ServerskiOdgovor;

/**
 *
 * @author Stefan
 */
public class DodavanjeProizvodjaca extends SistemskaOperacija {

    @Override
    protected ServerskiOdgovor commitOperacija(DomenskiObjekat dom) throws Exception {
        ServerskiOdgovor so = new ServerskiOdgovor();
        try {

            db.insert(dom);
            int id = db.getID(dom);
            ((Proizvodjac) dom).setProizvodjacID(id);
            so.setOperacija(Operacije.DODAJ_PROIZVODJACA);
            so.setPoruka("Sistem je uspešno zapamtio proizvođača");
            } catch (SQLException e) {
            so.setPoruka("Sistem ne može da zapamti proizvođača");
            so.setOperacija(Operacije.DODAJ_PROIZVODJACA);
        }
        return so;
    }
    
}
