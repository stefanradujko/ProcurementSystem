/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transfer;

import java.io.Serializable;

/**
 *
 * @author Stefan
 */
public class ServerskiOdgovor implements Serializable {
    private int operacija;
    private String poruka;
    private Object odgovor;

    public ServerskiOdgovor() {
    }

    public ServerskiOdgovor(int operacija, String poruka, Object odgovor) {
        this.operacija = operacija;
        this.poruka = poruka;
        this.odgovor = odgovor;
    }

    public Object getOdgovor() {
        return odgovor;
    }

    public void setOdgovor(Object odgovor) {
        this.odgovor = odgovor;
    }

    public int getOperacija() {
        return operacija;
    }

    public void setOperacija(int operacija) {
        this.operacija = operacija;
    }

    public String getPoruka() {
        return poruka;
    }

    public void setPoruka(String poruka) {
        this.poruka = poruka;
    }
    
    
    
}
