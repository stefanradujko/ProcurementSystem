/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operacije;

import domen.Komponenta;
import domen.Proizvodjac;
import interfejsi.DomenskiObjekat;
import interfejsi.Operacije;
import java.sql.SQLException;
import transfer.ServerskiOdgovor;

/**
 *
 * @author Stefan
 */
public class DodavanjeKomponente extends SistemskaOperacija {

    @Override
    protected ServerskiOdgovor commitOperacija(DomenskiObjekat dom) throws Exception {
        ServerskiOdgovor so = new ServerskiOdgovor();
        try {

            db.insert(dom);
            int id = db.getID(dom);
            ((Komponenta) dom).setKomponentaID(id);
            so.setOperacija(Operacije.DODAJ_KOMPONENTU);
            so.setPoruka("Sistem je uspešno zapamtio komponentu");
            } catch (SQLException e) {
            so.setPoruka("Sistem ne može da zapamti komponentu");
            so.setOperacija(Operacije.DODAJ_KOMPONENTU);
        }
        return so;
    }
    
}
