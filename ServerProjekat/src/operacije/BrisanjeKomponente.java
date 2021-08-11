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
public class BrisanjeKomponente extends SistemskaOperacija {

    @Override
    protected ServerskiOdgovor commitOperacija(DomenskiObjekat dom) throws Exception {
       ServerskiOdgovor so = new ServerskiOdgovor();
        try {
            db.update(dom);
            so.setPoruka("Sistem je uspešno obrisao komponentu");
            so.setOperacija(Operacije.OBRISI_KOMPONENTU);
        } catch (Exception e) {
            e.printStackTrace();
            so.setOperacija(Operacije.OBRISI_KOMPONENTU);
            so.setPoruka("Sistem ne može da obriše komponentu");
        }
        return so;
        /* ServerskiOdgovor so = new ServerskiOdgovor();
        try {
            int id = db.getID(dom);
            ((Komponenta) dom).setKomponentaID(id);
            db.delete(dom);
            so.setOperacija(Operacije.OBRISI_KOMPONENTU);
            so.setPoruka("Sistem je uspesno obrisao komponentu");
        } catch (SQLException e) {
            e.printStackTrace();
            so.setPoruka("Sistem ne moze da uspesno obrise komponentu");
            so.setOperacija(Operacije.OBRISI_KOMPONENTU);
        }
        return so;*/
    }
    
}
