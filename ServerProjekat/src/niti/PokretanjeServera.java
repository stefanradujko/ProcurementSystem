/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package niti;

import forme.GlavnaServerskaForma;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import kontroler.Kontroler;

/**
 *
 * @author Stefan
 */
public class PokretanjeServera extends Thread {
    GlavnaServerskaForma gsf;
    ServerSocket server;
    private static int port = 9000;
    private static int portN = -1;
    boolean radi = true;
    
    
    public PokretanjeServera(GlavnaServerskaForma gsf) {
        this.gsf = gsf;
        try {
            server = new ServerSocket(9000);
        } catch (IOException ex) {
            Logger.getLogger(PokretanjeServera.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void proveriPort(int p) throws IOException{
        try {
            ServerSocket so = new ServerSocket(p);
            portN = p;
            so.close();
        } catch (IOException e) {
            throw new IOException("Port nije slobodan");
        } 
        
    }
    
    public static void postaviPort(int port) {
        if(portN == -1){
            return;
        }
        port = portN;
    }

    @Override
    public void run() {
            try {
                gsf.uspesnoPokretanje();
            while(radi){
                Socket soket = server.accept();
                System.out.println("Klijent se povezao");
                ObradaZahteva oz = new ObradaZahteva(soket, this);
                Kontroler.getInstanca().dodajKlijenta(oz);
                oz.start();
            }
            } catch (IOException ex) {
                gsf.neuspesnoPokretanje();
                //Logger.getLogger(PokretanjeServera.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    public void pokreni() {
        radi = true;
    }
    
    public void zaustavi() {
        radi = false;
        Kontroler.getInstanca().odjaviSveKlijente();
    }
}
