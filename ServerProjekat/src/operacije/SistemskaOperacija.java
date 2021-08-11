/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operacije;

import dbb.DBBroker;
import interfejsi.DomenskiObjekat;
import java.util.logging.Level;
import java.util.logging.Logger;
import transfer.ServerskiOdgovor;

/**
 *
 * @author Stefan
 */
public abstract class SistemskaOperacija {
    public DBBroker db;

    public SistemskaOperacija() {
        db = new DBBroker();
    }
    

    public ServerskiOdgovor procesTransakcije(DomenskiObjekat dom) {

        ServerskiOdgovor so = new ServerskiOdgovor();

        try {
            //db.ucitajDrajver();
            db.otvoriKonekciju();
            so = commitOperacija(dom);
            db.commit();

        } catch (Exception ex) {

            Logger.getLogger(SistemskaOperacija.class.getName()).log(Level.SEVERE, null, ex);
            db.rollback();
            so.setPoruka(ex.getMessage());
        } finally {
            db.zatvoriKonekciju();

        }
        return so;
    }

    protected abstract ServerskiOdgovor commitOperacija(DomenskiObjekat dom) throws Exception;

}
