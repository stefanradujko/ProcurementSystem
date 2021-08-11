/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeli;

import domen.Narudzbenica;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Stefan
 */
public class ModelTabeleNarudzbenice extends AbstractTableModel {
    ArrayList<Narudzbenica> podaci;
    String[] kolone = {"Datum kreiranja", "Ukupna cena", "Zaposleni", "Proizvođač"};
    SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy.");

    public ModelTabeleNarudzbenice() {
        podaci = new ArrayList<>();
    }

    public ModelTabeleNarudzbenice(ArrayList<Narudzbenica> podaci) {
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
        Narudzbenica n = podaci.get(rowIndex);
        switch(columnIndex){
            case 0: return sdf.format(n.getDatumKreiranja());
            case 1: return n.getUkupnaCena();
            case 2: return n.getZaposleni();
            case 3: return n.getProizvodjac();
            default: return "Nepoznato";
        }
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

    public ArrayList<Narudzbenica> filtriraj(String kriterijum) throws ParseException {
        ArrayList<Narudzbenica> listaFiltrirana = new ArrayList<>();
        double cena;
        Date dat;
        try{
            cena = Double.parseDouble(kriterijum);
        }catch(NumberFormatException ex){
            cena = -1;
        }
        try{
            dat = sdf.parse(kriterijum);
        }catch(ParseException ex){
            dat = sdf.parse("01.01.2011.");
        }
        for(Narudzbenica n : podaci){
            if(n.getDatumKreiranja().equals(dat) || n.getUkupnaCena() == cena ||
               n.getZaposleni().toString().contains(kriterijum) ||
               n.getProizvodjac().toString().contains(kriterijum)){
                listaFiltrirana.add(n);
            }
        }
        podaci = listaFiltrirana;
        return listaFiltrirana;
    }

    public Narudzbenica vratiNarudzbenicu(int red) {
        return podaci.get(red);
    }
    
    
    
}
