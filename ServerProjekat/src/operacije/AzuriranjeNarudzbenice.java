/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operacije;

import interfejsi.DomenskiObjekat;
import transfer.ServerskiOdgovor;

/**
 *
 * @author Stefan
 */
public class AzuriranjeNarudzbenice extends SistemskaOperacija{

    @Override
    protected ServerskiOdgovor commitOperacija(DomenskiObjekat dom) throws Exception {
        ServerskiOdgovor so = new ServerskiOdgovor();
        try {
            db.update(dom);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return so;
    }
    
}
