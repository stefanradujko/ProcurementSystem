/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forme;

import domen.Komponenta;
import domen.Narudzbenica;
import domen.Proizvodjac;
import domen.StavkaNarudzbenice;
import domen.Zaposleni;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import kontroler.Kontroler;
import modeli.ModelTabeleStavke;
import sesija.Sesija;

/**
 *
 * @author Stefan
 */
public class FormaUnosNar extends javax.swing.JFrame {
    SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy.");
    Proizvodjac p;
    /**
     * Creates new form FormaUnosNar
     */
    public FormaUnosNar() {
        initComponents();
        btnDodaj.setEnabled(false);
        btnObrisi.setEnabled(false);
        btnSacuvaj.setEnabled(false);
        popuniCBProizvodjac();
        srediDatum();
        srediTabeluInicijalno();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        labDatum = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblStavke = new javax.swing.JTable();
        btnDodaj = new javax.swing.JButton();
        btnObrisi = new javax.swing.JButton();
        btnSacuvaj = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        cBProizvodjac = new javax.swing.JComboBox();
        btnUcitaj = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Unos narud??benice");

        jLabel1.setText("Datum:");

        labDatum.setText("jLabel1");

        tblStavke.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tblStavke);

        btnDodaj.setText("Dodaj red");
        btnDodaj.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDodajActionPerformed(evt);
            }
        });

        btnObrisi.setText("Obri??i izabranu stavku");
        btnObrisi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnObrisiActionPerformed(evt);
            }
        });

        btnSacuvaj.setText("Sa??uvaj");
        btnSacuvaj.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSacuvajActionPerformed(evt);
            }
        });

        jLabel2.setText("Izaberite proizvo??a??a:");

        cBProizvodjac.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btnUcitaj.setText("U??taj komponente");
        btnUcitaj.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUcitajActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(25, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnSacuvaj)
                        .addGap(246, 246, 246))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(33, 33, 33)
                                .addComponent(btnObrisi)
                                .addContainerGap())
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnDodaj)
                                .addGap(43, 43, 43))))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(26, 26, 26)
                        .addComponent(labDatum, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(cBProizvodjac, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnUcitaj)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(labDatum))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cBProizvodjac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnUcitaj))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(btnDodaj)
                        .addGap(18, 18, 18)
                        .addComponent(btnObrisi)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnSacuvaj)
                        .addContainerGap())))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnDodajActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDodajActionPerformed
        ModelTabeleStavke mts = (ModelTabeleStavke) tblStavke.getModel();
        mts.dodajRed();
    }//GEN-LAST:event_btnDodajActionPerformed

    private void btnObrisiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnObrisiActionPerformed
        ModelTabeleStavke mts = (ModelTabeleStavke) tblStavke.getModel();
        int red = tblStavke.getSelectedRow();
        if(red == -1){
            JOptionPane.showMessageDialog(this, "Morate izabrati stavku u tabeli");
            return;
        }
        mts.obrisi(red);
    }//GEN-LAST:event_btnObrisiActionPerformed

    private void btnSacuvajActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSacuvajActionPerformed
        ModelTabeleStavke mts = (ModelTabeleStavke) tblStavke.getModel();
        ArrayList<StavkaNarudzbenice> lista = mts.vratiListuStavki();
        Zaposleni z = Sesija.getInstanca().getUlogovaniZaposleni();
        if(lista.isEmpty()){
            JOptionPane.showMessageDialog(this, "Morate uneti bar jednu stavku narud??benice");
            return;
        }
        double ukupnaCena = 0;
        for(StavkaNarudzbenice s : lista){
            ukupnaCena += s.getCenaKomponente();
        }
        Date dat = null;
        try {
            dat = sdf.parse(labDatum.getText());
        } catch (ParseException ex) {
            Logger.getLogger(FormaUnosNar.class.getName()).log(Level.SEVERE, null, ex);
        }
        String stat = "Neobradjena";
        Narudzbenica n = new Narudzbenica(0, dat, ukupnaCena, z, p, stat);
        for(int i = 0; i < lista.size(); i++){
            lista.get(i).setNarudzbenica(n);
        }
        n.setStavke(lista);
        Kontroler.getInstanca().unosNarudzbenice(n);
        
    }//GEN-LAST:event_btnSacuvajActionPerformed

    private void btnUcitajActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUcitajActionPerformed
        p = (Proizvodjac) cBProizvodjac.getSelectedItem();
        vratiKomponente();
    }//GEN-LAST:event_btnUcitajActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FormaUnosNar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormaUnosNar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormaUnosNar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormaUnosNar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormaUnosNar().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDodaj;
    private javax.swing.JButton btnObrisi;
    private javax.swing.JButton btnSacuvaj;
    private javax.swing.JButton btnUcitaj;
    private javax.swing.JComboBox cBProizvodjac;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labDatum;
    private javax.swing.JTable tblStavke;
    // End of variables declaration//GEN-END:variables

    private void srediDatum() {
        labDatum.setText(sdf.format(new Date()));
    }

    private void srediTabelu(ArrayList<Komponenta> lista) {
        ModelTabeleStavke mts = new ModelTabeleStavke(this);
        tblStavke.setModel(mts);
        
        JComboBox<Komponenta> kombo = new JComboBox<>();
        kombo.removeAllItems();
        for(Komponenta k : lista){
            if(k.getProizvodjac().getProizvodjacID() == p.getProizvodjacID()){
               kombo.addItem(k); 
            }
        }
        TableColumnModel tcm = tblStavke.getColumnModel();
        TableColumn kolona = tcm.getColumn(0);
        kolona.setCellEditor(new DefaultCellEditor(kombo));
    }

    private void vratiKomponente() {
        Kontroler.getInstanca().vratiKomponente();
    }

    public void neuspesnoVracanjeKomponenti() {
        JOptionPane.showMessageDialog(this, "Sistem ne mo??e da u??ita komponenete za izabranog proizov??a??a");
    }

    public void vraceneKomponente(ArrayList<Komponenta> sveKomponente) {
        JOptionPane.showMessageDialog(this, "Sistem je uspe??no u??itao komponente za izabranog proizvo??a??a");
        btnDodaj.setEnabled(true);
        btnObrisi.setEnabled(true);
        btnSacuvaj.setEnabled(true);
        btnUcitaj.setEnabled(false);
        srediTabelu(sveKomponente);
    }

    private void popuniCBProizvodjac() {
        Kontroler.getInstanca().vratiSveProizvodjace();
    }

    public void neuspesnoVracanjeProizvodjaca() {
        JOptionPane.showMessageDialog(this, "Sistem ne mo??e da vrati proizvo??a??e");
    }

    public void vraceniProizvodjaci(ArrayList<Proizvodjac> sviProizvodjaci) {
        cBProizvodjac.removeAllItems();
        for(Proizvodjac pro : sviProizvodjaci){
            cBProizvodjac.addItem(pro);
        }
    }

    public void dodavanjeNarudzbenice(String poruka) {
        ModelTabeleStavke mts = (ModelTabeleStavke) tblStavke.getModel();
        mts.isprazni();
        JOptionPane.showMessageDialog(this, poruka);
    }

    private void srediTabeluInicijalno() {
        ModelTabeleStavke mts = new ModelTabeleStavke();
        tblStavke.setModel(mts);
    }
}
