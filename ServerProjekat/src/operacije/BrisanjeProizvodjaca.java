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
public class BrisanjeProizvodjaca extends SistemskaOperacija {

    @Override
    protected ServerskiOdgovor commitOperacija(DomenskiObjekat dom) throws Exception {
        ServerskiOdgovor so = new ServerskiOdgovor();
        try {
            int id = db.getID(dom);
            ((Proizvodjac) dom).setProizvodjacID(id);
            db.delete(dom);
            so.setOperacija(Operacije.OBRISI_PROIZVODJACA);
            so.setPoruka("Sistem je uspešno obrisao proizvođača");
        } catch (SQLException e) {
            e.printStackTrace();
            so.setPoruka("Sistem ne može da obriše proizvođača");
            so.setOperacija(Operacije.OBRISI_PROIZVODJACA);
        }
        return so;
    }
    
}
