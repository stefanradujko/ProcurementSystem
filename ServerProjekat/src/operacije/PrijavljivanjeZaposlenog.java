/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operacije;

import interfejsi.DomenskiObjekat;
import interfejsi.Operacije;
import java.util.ArrayList;
import transfer.ServerskiOdgovor;

/**
 *
 * @author Stefan
 */
public class PrijavljivanjeZaposlenog extends SistemskaOperacija {

    @Override
    protected ServerskiOdgovor commitOperacija(DomenskiObjekat dom) throws Exception {
        ServerskiOdgovor so = new ServerskiOdgovor();
        ArrayList<DomenskiObjekat> lista = (ArrayList<DomenskiObjekat>) dom.vratiListuPoRS(db.select(dom));

        try {

            if (!lista.isEmpty()) {
                so.setOperacija(Operacije.PRIJAVI_SE);
                so.setOdgovor(lista);
                //so.setUspesno(true);

            } else {

                so.setOperacija(Operacije.PRIJAVI_SE);
                so.setOdgovor(lista);
                //so.setUspesno(false);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return so;
    }
    
}
