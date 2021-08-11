/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operacije;

import domen.Proizvodjac;
import interfejsi.DomenskiObjekat;
import interfejsi.Operacije;
import transfer.ServerskiOdgovor;

/**
 *
 * @author Stefan
 */
public class IzmenaProizvodjaca extends SistemskaOperacija {

    @Override
    protected ServerskiOdgovor commitOperacija(DomenskiObjekat dom) throws Exception {
        ServerskiOdgovor so = new ServerskiOdgovor();
        try {
            db.update(dom);
            so.setPoruka("Sistem je uspešno izmenio proizvođača");
            so.setOperacija(Operacije.IZMENI_PROIZVODJACA);
        } catch (Exception e) {
            e.printStackTrace();
            so.setOperacija(Operacije.IZMENI_PROIZVODJACA);
            so.setPoruka("Sistem ne može da izmeni proizvođača");
        }
        return so;
    }
    
}
