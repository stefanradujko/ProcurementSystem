/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operacije;

import domen.Narudzbenica;
import interfejsi.DomenskiObjekat;
import interfejsi.Operacije;
import java.sql.SQLException;
import transfer.ServerskiOdgovor;

/**
 *
 * @author Stefan
 */
public class BrisanjeNarudzbenice extends SistemskaOperacija {

    @Override
    protected ServerskiOdgovor commitOperacija(DomenskiObjekat dom) throws Exception {
        ServerskiOdgovor so = new ServerskiOdgovor();
        try {
           // int id = db.getID(dom);
            //((Narudzbenica) dom).setBrojNarudzbenice(id);
            for(int i = 0; i < ((Narudzbenica)dom).getStavke().size(); i++){
                db.delete(((Narudzbenica)dom).getStavke().get(i));
            }
            db.delete(dom);
            so.setOperacija(Operacije.OBRISI_NARUDZBENICU);
            so.setPoruka("Sistem je uspešno obrisao narudžbenicu");
        } catch (SQLException e) {
            e.printStackTrace();
            so.setPoruka("Sistem ne može da obriše narudžbenicu");
            so.setOperacija(Operacije.OBRISI_NARUDZBENICU);
        }
        return so;
    }
    
}
