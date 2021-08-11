/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kom;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import transfer.KlijentskiZahtev;
import transfer.ServerskiOdgovor;

/**
 *
 * @author Stefan
 */
public class Komunikacija {
    private static Komunikacija instanca;

    private Komunikacija() {
    }

    public static Komunikacija getInstanca() {
        if (instanca == null) {
            instanca = new Komunikacija();
        }

        return instanca;
    }

    public KlijentskiZahtev prihvatiZahtev(Socket soket) throws IOException, ClassNotFoundException {
        KlijentskiZahtev kz = new KlijentskiZahtev();
        ObjectInputStream ois;
       // try {
            ois = new ObjectInputStream(soket.getInputStream());
            kz = (KlijentskiZahtev) ois.readObject();
        /*} catch (IOException ex) {
            Logger.getLogger(Komunikacija.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Komunikacija.class.getName()).log(Level.SEVERE, null, ex);
        }*/
        return kz;

    }
    
    public void posaljiOdgovor(Socket soket, ServerskiOdgovor so) {
        try {
            ObjectOutputStream ous = new ObjectOutputStream(soket.getOutputStream());
            ous.writeObject(so);
        } catch (IOException ex) {
            Logger.getLogger(Komunikacija.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
