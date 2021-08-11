/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeli;

import domen.Komponenta;
import domen.StavkaNarudzbenice;
import forme.FormaUnosNar;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Stefan
 */
public class ModelTabeleStavke extends AbstractTableModel {
    ArrayList<StavkaNarudzbenice> podaci = new ArrayList<>();
    String[] kolone = {"Komponenta", "Količina", "Cena(Ukupno)"};
    FormaUnosNar fun;

    public ModelTabeleStavke() {
    }

    
    public ModelTabeleStavke(FormaUnosNar fun) {
        this.fun = fun;
    }
    
    public ModelTabeleStavke(ArrayList<StavkaNarudzbenice> lista){
        podaci = lista;
    }

    @Override
    public int getRowCount() {
        return podaci.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        StavkaNarudzbenice s = podaci.get(rowIndex);
        switch(columnIndex){
            case 0: return s.getKomponenta(); 
            case 1: return s.getKolicinaKomponente();
            case 2: return s.getCenaKomponente();
            default: return "Nepoznato";
        }
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        if(columnIndex == 2){
            return false;
        }
        return true;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        StavkaNarudzbenice s = podaci.get(rowIndex);
        switch(columnIndex){
            case 0: Komponenta k = (Komponenta) aValue;
                    for(StavkaNarudzbenice sn : podaci){
                        if(sn.getKomponenta().getKomponentaID() == k.getKomponentaID()){
                            JOptionPane.showMessageDialog(fun, "Izabrana komponenta je vec uneta kao stavka");
                            return;
                        }
                    }
                    s.setKomponenta(k);
                    s.setCenaKomponente(s.getKomponenta().getCenaKomponente() * s.getKolicinaKomponente());
                    fireTableDataChanged();
                    break;
            case 1: int broj = Integer.parseInt((String) aValue); 
                    if(broj <= 0){
                        JOptionPane.showMessageDialog(fun, "Kolicina komponente mora biti veca od 0");
                        return;
                    }
                    s.setKolicinaKomponente(broj);
                    s.setCenaKomponente(s.getKomponenta().getCenaKomponente() * s.getKolicinaKomponente());
                    fireTableDataChanged();
                    break;
        }
    }

    public void dodajRed() {
        StavkaNarudzbenice s = new StavkaNarudzbenice();
        Komponenta k = new Komponenta();
        k.setNazivKomponenete("Izaberite komponentu");
        k.setCenaKomponente(0);
        s.setKolicinaKomponente(1);
        s.setKomponenta(k);
        podaci.add(s);
        fireTableDataChanged();
    }

    public void obrisi(int red) {
        podaci.remove(red);
        fireTableDataChanged();
    }

    public ArrayList<StavkaNarudzbenice> vratiListuStavki() {
        ArrayList<StavkaNarudzbenice> listaZaVracanje = new ArrayList<>();
        for(StavkaNarudzbenice s: podaci){
            if(!s.getKomponenta().getNazivKomponenete().equals("Izaberite komponentu")){
                listaZaVracanje.add(s);
            }
        }
        return listaZaVracanje;
    }

    public void isprazni() {
        podaci = new ArrayList<>();
        fireTableDataChanged();
    }
}
