/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shiro;

import domen.Zaposleni;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import org.apache.log4j.BasicConfigurator;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.ini4j.Wini;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Stefan
 */
public class Shiro {
    private static final transient Logger log = LoggerFactory.getLogger(Shiro.class);
    private static Subject currentUser;
    private static Shiro instanca;
    ArrayList<Subject> korisnici;
    
    public Shiro(){
        konfigurisi();
        korisnici = new ArrayList<>();
    }
    
    public static Shiro getInstanca(){
        if(instanca == null){
            instanca = new Shiro();
        }
        return instanca;
    }

    private void konfigurisi() {
        BasicConfigurator.configure();
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("shiro.ini");
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
    }
    
    public void prijavi(String u, String p){
        currentUser = SecurityUtils.getSubject();
        if (!currentUser.isAuthenticated()) {
            UsernamePasswordToken token = new UsernamePasswordToken(u, p);
            token.setRememberMe(true);
            try {
                currentUser.login(token);
            } catch (Exception ex) {
                System.out.println("Neuspesno prijavljivanje korisnika");
            }
            System.out.println("Korisnik [" + currentUser.getPrincipal() + "] se uspešno prijavio.");
            korisnici.add(currentUser);
        }
    }
    
    public String enkriptuj(String s){
        return new Md5Hash(s).toHex();
    }
    
    public boolean testirajPrivilegije(String user){
        for(Subject s : korisnici){
            if(s.getPrincipal().equals(user) && s.hasRole("admin")){
                return true;
            }
        }
        return false;
    }

    public void upisiUIni(Zaposleni zap) {
        FileInputStream fis = null;
        try {
            fis = new FileInputStream("shiro.ini");
            Wini ini = new Wini(fis);
            ini.put("users", zap.getKorisnickoIme(), zap.getSifra() + ", korisnik");
            FileOutputStream fos = new FileOutputStream("shiro.ini");
            ini.store(fos);
            fos.close();
        } catch (FileNotFoundException ex) {
            java.util.logging.Logger.getLogger(Shiro.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(Shiro.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fis.close();
            } catch (IOException ex) {
                java.util.logging.Logger.getLogger(Shiro.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
