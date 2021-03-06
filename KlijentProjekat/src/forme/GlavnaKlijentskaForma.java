/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forme;

import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import kontroler.Kontroler;
import sesija.Sesija;

/**
 *
 * @author Stefan
 */
public class GlavnaKlijentskaForma extends javax.swing.JFrame {
    FormaLogIn fli;
    FormaUnosPro formaUP;
    FormaPIBPro formaPIB;
    FormaUnosKomp formaUK;
    FormaBrisanjeKomp formaBK;
    FormaUnosNar formaUN;
    FormaPBNar formaPBN;
    FormaDodavanjeZap formaDZ;

    /**
     * Creates new form GlavnaKlijentskaForma
     */
    public GlavnaKlijentskaForma() {
        initComponents();
        srediLab();
        srediSliku();
        labDodaj.setVisible(false);
        proveriPrivilegije();
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
        labZap = new javax.swing.JLabel();
        labDodaj = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        mProizvodjac = new javax.swing.JMenu();
        mIUnosPro = new javax.swing.JMenuItem();
        mIPIBPro = new javax.swing.JMenuItem();
        mKomponenta = new javax.swing.JMenu();
        mIUnosKomp = new javax.swing.JMenuItem();
        mIBrisanje = new javax.swing.JMenuItem();
        mNarudzbenica = new javax.swing.JMenu();
        mIUnosNar = new javax.swing.JMenuItem();
        mIPBNar = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Glavna forma");

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel1.setText("Prijavljeni zaposleni:");

        labZap.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        labZap.setText("jLabel2");

        labDodaj.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labDodajMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(35, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(labDodaj)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(labZap, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(28, 28, 28))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(31, Short.MAX_VALUE)
                .addComponent(labDodaj)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(labZap))
                .addGap(39, 39, 39))
        );

        mProizvodjac.setText("Proizvo??a??");

        mIUnosPro.setText("Unos");
        mIUnosPro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mIUnosProActionPerformed(evt);
            }
        });
        mProizvodjac.add(mIUnosPro);

        mIPIBPro.setText("Pretraga, izmena, brisanje");
        mIPIBPro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mIPIBProActionPerformed(evt);
            }
        });
        mProizvodjac.add(mIPIBPro);

        jMenuBar1.add(mProizvodjac);

        mKomponenta.setText("Komponenta");

        mIUnosKomp.setText("Unos");
        mIUnosKomp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mIUnosKompActionPerformed(evt);
            }
        });
        mKomponenta.add(mIUnosKomp);

        mIBrisanje.setText("Brisanje");
        mIBrisanje.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mIBrisanjeActionPerformed(evt);
            }
        });
        mKomponenta.add(mIBrisanje);

        jMenuBar1.add(mKomponenta);

        mNarudzbenica.setText("Narud??benica");

        mIUnosNar.setText("Unos");
        mIUnosNar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mIUnosNarActionPerformed(evt);
            }
        });
        mNarudzbenica.add(mIUnosNar);

        mIPBNar.setText("Pretraga, brisanje");
        mIPBNar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mIPBNarActionPerformed(evt);
            }
        });
        mNarudzbenica.add(mIPBNar);

        jMenuBar1.add(mNarudzbenica);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void mIPIBProActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mIPIBProActionPerformed
        formaPIB = new FormaPIBPro();
        formaPIB.setLocationRelativeTo(null);
        formaPIB.setVisible(true);
    }//GEN-LAST:event_mIPIBProActionPerformed

    private void mIUnosKompActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mIUnosKompActionPerformed
        formaUK = new FormaUnosKomp();
        formaUK.setLocationRelativeTo(null);
        formaUK.setVisible(true);
    }//GEN-LAST:event_mIUnosKompActionPerformed

    private void mIUnosNarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mIUnosNarActionPerformed
        formaUN = new FormaUnosNar();
        formaUN.setLocationRelativeTo(null);
        formaUN.setVisible(true);
    }//GEN-LAST:event_mIUnosNarActionPerformed

    private void mIUnosProActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mIUnosProActionPerformed
        formaUP = new FormaUnosPro();
        formaUP.setLocationRelativeTo(null);
        formaUP.setVisible(true);
    }//GEN-LAST:event_mIUnosProActionPerformed

    private void mIBrisanjeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mIBrisanjeActionPerformed
        formaBK = new FormaBrisanjeKomp();
        formaBK.setLocationRelativeTo(null);
        formaBK.setVisible(true);
    }//GEN-LAST:event_mIBrisanjeActionPerformed

    private void mIPBNarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mIPBNarActionPerformed
        formaPBN = new FormaPBNar();
        formaPBN.setLocationRelativeTo(null);
        formaPBN.setVisible(true);
    }//GEN-LAST:event_mIPBNarActionPerformed

    private void labDodajMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labDodajMouseClicked
        formaDZ = new FormaDodavanjeZap();
        formaDZ.setLocationRelativeTo(null);
        formaDZ.setVisible(true);
    }//GEN-LAST:event_labDodajMouseClicked

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
            java.util.logging.Logger.getLogger(GlavnaKlijentskaForma.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GlavnaKlijentskaForma.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GlavnaKlijentskaForma.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GlavnaKlijentskaForma.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GlavnaKlijentskaForma().setVisible(true);
            }
        });
        
        
    }

    public FormaUnosPro getFormaUP() {
        return formaUP;
    }

    public FormaPIBPro getFormaPIB() {
        return formaPIB;
    }

    public FormaUnosKomp getFormaUK() {
        return formaUK;
    }

    public FormaBrisanjeKomp getFormaBK() {
        return formaBK;
    }

    public FormaUnosNar getFormaUN() {
        return formaUN;
    }

    public FormaPBNar getFormaPBN() {
        return formaPBN;
    }

    public void setFli(FormaLogIn fli) {
        this.fli = fli;
    }

    public FormaDodavanjeZap getFormaDZ() {
        return formaDZ;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel labDodaj;
    private javax.swing.JLabel labZap;
    private javax.swing.JMenuItem mIBrisanje;
    private javax.swing.JMenuItem mIPBNar;
    private javax.swing.JMenuItem mIPIBPro;
    private javax.swing.JMenuItem mIUnosKomp;
    private javax.swing.JMenuItem mIUnosNar;
    private javax.swing.JMenuItem mIUnosPro;
    private javax.swing.JMenu mKomponenta;
    private javax.swing.JMenu mNarudzbenica;
    private javax.swing.JMenu mProizvodjac;
    // End of variables declaration//GEN-END:variables

    private void srediLab() {
        labZap.setText(Sesija.getInstanca().getUlogovaniZaposleni().toString());
    }

    public void odjavi(String poruka) {
        JOptionPane.showMessageDialog(this, poruka);
        this.dispose();
        if(fli != null){
            fli.dispose();
        }
        if(formaBK != null){
            formaBK.dispose();
        }
        if(formaPBN != null){
            formaPBN.dispose();
        }
        if(formaPIB != null){
            formaPIB.dispose();
        }
        if(formaUK != null){
            formaUK.dispose();
        }
        if(formaUN != null){
            formaUN.dispose();
        }
        if(formaUP != null){
            formaUP.dispose();
        }
        if(formaDZ != null){
            formaDZ.dispose();
        }
    }

    private void proveriPrivilegije() {
        Kontroler.getInstanca().proveravanjePrivilegija(Sesija.getInstanca().getUlogovaniZaposleni());
    }

    public void proverenePrivilegije(boolean odgovor) {
        if(odgovor){
            labDodaj.setVisible(true);
        }
    }

    private void srediSliku() {
        ImageIcon icon = new ImageIcon("s1.png");
        labDodaj.setIcon(icon);
        
    }

}
