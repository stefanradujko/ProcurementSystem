/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeli;

import domen.Komponenta;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Stefan
 */
public class ModelTabeleKomponente extends AbstractTableModel {
    ArrayList<Komponenta> podaci;
    String[] kolone = {"Naziv", "Tip", "Cena", "Proizvođač", "Kratak opis", "Godina proizvodnje"};

    public ModelTabeleKomponente() {
        podaci = new ArrayList<>();
    }

    public ModelTabeleKomponente(ArrayList<Komponenta> podaci) {
        this.podaci = podaci;
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
        Komponenta k = podaci.get(rowIndex);
        switch(columnIndex){
            case 0: return k.getNazivKomponenete();
            case 1: return k.getTipKomponente();
            case 2: return k.getCenaKomponente();
            case 3: return k.getProizvodjac();
            case 4: return k.getKratakOpis();
            case 5: return k.getGodinaProizvodnje();
            default: return "Nepoznato";
        }
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

    public ArrayList<Komponenta> filtriraj(String kriterijum) {
        ArrayList<Komponenta> listaFiltrirana = new ArrayList<>();
        int kritI;
        double kritD;
        try{
            kritD = Double.parseDouble(kriterijum);
        }catch(NumberFormatException ex){
            kritD = -1;
        }
        try{
            kritI = Integer.parseInt(kriterijum);
        }catch(NumberFormatException ex){
            kritI = -1;
        }
        for(Komponenta k : podaci){
            if(k.getNazivKomponenete().contains(kriterijum) || k.getTipKomponente().getNazivTipa().contains(kriterijum) ||
               k.getProizvodjac().getNazivProizvodjaca().contains(kriterijum) || k.getKratakOpis().contains(kriterijum) ||
               k.getCenaKomponente() == kritD || k.getGodinaProizvodnje() == kritI){
                listaFiltrirana.add(k);
            }
        }
        podaci = listaFiltrirana;
        return listaFiltrirana;
    }

    public Komponenta vratiKomponentu(int red) {
        return podaci.get(red);
    }
    
    
    
}
