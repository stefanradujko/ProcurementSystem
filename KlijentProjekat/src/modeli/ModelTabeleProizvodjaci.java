/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeli;

import domen.Proizvodjac;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Stefan
 */
public class ModelTabeleProizvodjaci extends AbstractTableModel {
    ArrayList<Proizvodjac> podaci;
    String[] kolone = {"ID", "Naziv", "Email"};

    public ModelTabeleProizvodjaci() {
        podaci = new ArrayList<>();
    }

    public ModelTabeleProizvodjaci(ArrayList<Proizvodjac> podaci) {
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
        Proizvodjac p = podaci.get(rowIndex);
        switch(columnIndex){
            case 0: return p.getProizvodjacID();
            case 1: return p.getNazivProizvodjaca();
            case 2: return p.getEmailProizvodjaca();
            default: return "Nepoznato";
        }
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

    public Proizvodjac vratiProizvodjaca(int red) {
        return podaci.get(red);
    }

    
    
    
    
    
    
}
