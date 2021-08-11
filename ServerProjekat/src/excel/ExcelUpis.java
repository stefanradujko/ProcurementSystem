/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package excel;

import domen.StavkaNarudzbenice;
import interfejsi.Operacije;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import transfer.ServerskiOdgovor;

/**
 *
 * @author Stefan
 */
public class ExcelUpis {
    SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy.");
    
    public ServerskiOdgovor upisiUExcel(ArrayList<StavkaNarudzbenice> lista){
        try {
            ExcelUtil.getInstanca().postaviBrojNar(lista.get(0).getNarudzbenica().getBrojNarudzbenice());
            ExcelUtil.getInstanca().setExcelFile();
            ExcelUtil.getInstanca().setCellData("Broj narudzbenice", 0, 0);
            ExcelUtil.getInstanca().setCellData("Datum kreiranja",0, 1);
            ExcelUtil.getInstanca().setCellData("Ukupna cena", 0, 2);
            ExcelUtil.getInstanca().setCellData("Zaposleni", 0, 3);
            ExcelUtil.getInstanca().setCellData("Proizvodjac", 0, 4);
            StavkaNarudzbenice sn = lista.get(0);
            ExcelUtil.getInstanca().setCellData(sn.getNarudzbenica().getBrojNarudzbenice() + "", 1, 0);
            ExcelUtil.getInstanca().setCellData(sdf.format(sn.getNarudzbenica().getDatumKreiranja()), 1, 1);
            ExcelUtil.getInstanca().setCellData(sn.getNarudzbenica().getUkupnaCena() + "", 1, 2);
            ExcelUtil.getInstanca().setCellData(sn.getNarudzbenica().getZaposleni().toString(), 1, 3);
            ExcelUtil.getInstanca().setCellData(sn.getNarudzbenica().getProizvodjac().toString(), 1, 4);
            
            ExcelUtil.getInstanca().setCellData("Stavke narudzbenice:", 3, 0);
            ExcelUtil.getInstanca().setCellData("Broj stavke", 4, 0);
            ExcelUtil.getInstanca().setCellData("Naziv komponente",4, 1);
            ExcelUtil.getInstanca().setCellData("Cena(kom)", 4, 2);
            ExcelUtil.getInstanca().setCellData("Kolicina", 4, 3);
            ExcelUtil.getInstanca().setCellData("Kratak opis", 4, 4);
            ExcelUtil.getInstanca().setCellData("Godina proizvodnje", 4, 5);
            for(int i = 0; i < lista.size(); i++){
                StavkaNarudzbenice s = lista.get(i);
                ExcelUtil.getInstanca().setCellData(s.getBrojStavke() + "", i + 5, 0);
                ExcelUtil.getInstanca().setCellData(s.getKomponenta().getNazivKomponenete(), i + 5, 1);
                ExcelUtil.getInstanca().setCellData(s.getKomponenta().getCenaKomponente() + "", i + 5, 2);
                ExcelUtil.getInstanca().setCellData(s.getKolicinaKomponente() + "", i + 5, 3);
                ExcelUtil.getInstanca().setCellData(s.getKomponenta().getKratakOpis() + "", i + 5, 4);
                ExcelUtil.getInstanca().setCellData(s.getKomponenta().getGodinaProizvodnje() + "", i + 5, 5);
            }
        } catch (Exception ex) {
            ServerskiOdgovor so = new ServerskiOdgovor(Operacije.EKSPORTUJ_U_EXCEL, "Neuspešno eksportovanje narudžbenice", false);
            return so;
        }
        ServerskiOdgovor so = new ServerskiOdgovor(Operacije.EKSPORTUJ_U_EXCEL, "Uspešno eksportovanje narudžbenice", true);
        try {
            ExcelUtil.getInstanca().otvoriFajl();
        } catch (Exception ex) {
            System.out.println("Sistem ne može da otvori excel fajl");
        }
        return so;
    }
}
