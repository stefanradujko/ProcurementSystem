/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operacije;

import domen.Komponenta;
import domen.Zaposleni;
import interfejsi.DomenskiObjekat;
import interfejsi.Operacije;
import java.sql.SQLException;
import transfer.ServerskiOdgovor;

/**
 *
 * @author Stefan
 */
public class DodavanjeZaposlenog extends SistemskaOperacija{

    @Override
    protected ServerskiOdgovor commitOperacija(DomenskiObjekat dom) throws Exception {
        ServerskiOdgovor so = new ServerskiOdgovor();
        try {
            int id = db.getID(dom) + 1;
            ((Zaposleni) dom).setZaposleniID(id);
            db.insert(dom);
            so.setOperacija(Operacije.DODAJ_ZAPOSLENOG);
            so.setPoruka("Sistem je uspešno zapamtio zaposlenog");
            so.setOdgovor(true);
            } catch (SQLException e) {
            so.setPoruka("Sistem ne može da zapamti zaposlenog");
            so.setOdgovor(false);
            so.setOperacija(Operacije.DODAJ_ZAPOSLENOG);
        }
        return so;
    }
    
}
