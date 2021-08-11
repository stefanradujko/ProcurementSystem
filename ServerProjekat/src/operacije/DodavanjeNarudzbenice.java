/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operacije;

import domen.Narudzbenica;
import domen.StavkaNarudzbenice;
import interfejsi.DomenskiObjekat;
import interfejsi.Operacije;
import java.sql.SQLException;
import transfer.ServerskiOdgovor;

/**
 *
 * @author Stefan
 */
public class DodavanjeNarudzbenice extends SistemskaOperacija {

    @Override
    protected ServerskiOdgovor commitOperacija(DomenskiObjekat dom) throws Exception {
        ServerskiOdgovor so = new ServerskiOdgovor();
        try {
            db.insert(dom);
            int id = db.getID(dom);
            ((Narudzbenica) dom).setBrojNarudzbenice(id);
            so.setOperacija(Operacije.DODAJ_NARUDZBENICU);
            for(int i = 0; i < ((Narudzbenica)dom).getStavke().size(); i++){
                ((Narudzbenica)dom).getStavke().get(i).getNarudzbenica().setBrojNarudzbenice(id);
                db.insert(((Narudzbenica)dom).getStavke().get(i));
            }
            so.setPoruka("Sistem je uspešno zapamtio narudžbenicu");
            } catch (SQLException e) {
            so.setPoruka("Sistem ne može da zapamti narudžbenicu");
            so.setOperacija(Operacije.DODAJ_NARUDZBENICU);
        }
        return so;
    }
    
}
