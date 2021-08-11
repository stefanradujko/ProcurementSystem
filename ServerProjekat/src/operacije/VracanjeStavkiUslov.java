/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operacije;

import interfejsi.DomenskiObjekat;
import interfejsi.Operacije;
import java.sql.SQLException;
import java.util.ArrayList;
import transfer.ServerskiOdgovor;

/**
 *
 * @author Stefan
 */
public class VracanjeStavkiUslov extends SistemskaOperacija {

    @Override
    protected ServerskiOdgovor commitOperacija(DomenskiObjekat dom) throws Exception {
        ServerskiOdgovor so = new ServerskiOdgovor();
        try {
            ArrayList<DomenskiObjekat> lista = (ArrayList<DomenskiObjekat>) dom.vratiListuPoRS(db.select(dom));
            so.setOdgovor(lista);
            so.setOperacija(Operacije.VRATI_STAVKE_ZA_USLOV);
            so.setPoruka("Sistem je vratio stavke narudžbenice");
            } catch (SQLException e) {
            so.setOperacija(Operacije.VRATI_STAVKE_ZA_USLOV);
            so.setPoruka("Sistem ne može da vrati stavke narudžbenice");
            }
       return so;
    }
    
}
