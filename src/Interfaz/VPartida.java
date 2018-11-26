package Interfaz;

import Obligatorio.Jugador;
import Obligatorio.Partida;
import Obligatorio.Sistema;
import java.util.Observable;
import java.util.Observer;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;

//Autores: Santiago Rügnitz(215381) y Franco Galeano(230996)

public class VPartida extends javax.swing.JFrame implements Observer {

    /**
     * Creates new form VPartida
     */
    private Sistema modelo;

    public VPartida() {
        initComponents();
    }

    public VPartida(Sistema m) {
        initComponents();
        ButtonGroup btrGroup = new ButtonGroup();
        btrGroup.add(uno);
        btrGroup.add(dos);
        btrGroup.add(tres);
        modelo = m;
        ((JSpinner.DefaultEditor)spinnerMov.getEditor()).getTextField().setEditable(false);
        jRojo.setListData(modelo.getRankingFormat());
        jAzul.setListData(modelo.getRankingFormat());
        modelo.addObserver(this);
    }
    
    
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jScrollPane1 = new javax.swing.JScrollPane();
        jRojo = new javax.swing.JList<>();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jAzul = new javax.swing.JList<>();
        jLabel2 = new javax.swing.JLabel();
        uno = new javax.swing.JRadioButton();
        dos = new javax.swing.JRadioButton();
        tres = new javax.swing.JRadioButton();
        btnJugar = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        spinnerMov = new javax.swing.JSpinner();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(629, 380));
        setResizable(false);
        setSize(new java.awt.Dimension(629, 375));
        getContentPane().setLayout(null);

        jScrollPane1.setViewportView(jRojo);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(54, 69, 258, 130);

        jLabel1.setForeground(new java.awt.Color(255, 0, 0));
        jLabel1.setText("Jugador Rojo");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(60, 50, 100, 14);

        jScrollPane2.setViewportView(jAzul);

        getContentPane().add(jScrollPane2);
        jScrollPane2.setBounds(350, 70, 258, 130);

        jLabel2.setForeground(new java.awt.Color(0, 0, 255));
        jLabel2.setText("Jugador Azul");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(350, 50, 120, 14);

        buttonGroup1.add(uno);
        uno.setText("Limite de Movimientos");
        uno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                unoActionPerformed(evt);
            }
        });
        getContentPane().add(uno);
        uno.setBounds(10, 280, 220, 23);

        buttonGroup1.add(dos);
        dos.setSelected(true);
        dos.setText("Llegar con una pieza al otro lado");
        dos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dosActionPerformed(evt);
            }
        });
        getContentPane().add(dos);
        dos.setBounds(10, 220, 270, 23);

        buttonGroup1.add(tres);
        tres.setText("Llegar con todas las piezas al lado opuesto");
        tres.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tresActionPerformed(evt);
            }
        });
        getContentPane().add(tres);
        tres.setBounds(10, 250, 270, 23);

        btnJugar.setText("Jugar Partida");
        btnJugar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnJugarActionPerformed(evt);
            }
        });
        getContentPane().add(btnJugar);
        btnJugar.setBounds(270, 310, 150, 23);

        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Forma de terminación");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(30, 200, 190, 14);

        spinnerMov.setModel(new javax.swing.SpinnerNumberModel(10, 1, 112, 1));
        spinnerMov.setEnabled(false);
        getContentPane().add(spinnerMov);
        spinnerMov.setBounds(240, 280, 40, 20);

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Registrar.jpeg"))); // NOI18N
        getContentPane().add(jLabel4);
        jLabel4.setBounds(0, -30, 630, 410);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnJugarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnJugarActionPerformed
        int tipoTerm = 1;
        int movMax = Integer.MAX_VALUE;
        if (uno.isSelected()) {
            movMax = (Integer) spinnerMov.getValue();
            tipoTerm = 1;
        }
        if (dos.isSelected()) {
            tipoTerm = 2;
        }
        if (tres.isSelected()) {
            tipoTerm = 3;
        }
        if (jRojo.isSelectionEmpty() || jAzul.isSelectionEmpty()) {
            JOptionPane.showMessageDialog(null, "No selecciono ambos jugadores", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            if (jRojo.getSelectedIndex() != jAzul.getSelectedIndex()) {
                Jugador jugadorRojo = modelo.getListaRankings().get(jRojo.getSelectedIndex());
                Jugador jugadorAzul = modelo.getListaRankings().get(jAzul.getSelectedIndex());
                Partida p = new Partida(jugadorRojo, jugadorAzul, tipoTerm, movMax, false);
                modelo.agregarPartida(p);
                VJuego v = new VJuego(p, modelo);
                v.setVisible(true);
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Se seleccionó dos veces al mismo jugador", "Error", JOptionPane.ERROR_MESSAGE);
            }

        }

    }//GEN-LAST:event_btnJugarActionPerformed

    private void unoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_unoActionPerformed
        this.spinnerMov.setEnabled(true);
    }//GEN-LAST:event_unoActionPerformed

    private void dosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dosActionPerformed
        this.spinnerMov.setEnabled(false);
    }//GEN-LAST:event_dosActionPerformed

    private void tresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tresActionPerformed
        this.spinnerMov.setEnabled(false);

    }//GEN-LAST:event_tresActionPerformed

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
            java.util.logging.Logger.getLogger(VPartida.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VPartida.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VPartida.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VPartida.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VPartida().setVisible(true);
            }
        });
    }

    @Override
    public void update(Observable o, Object o1) {
        jRojo.setListData(modelo.getRankingFormat());
        jAzul.setListData(modelo.getRankingFormat());
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnJugar;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JRadioButton dos;
    private javax.swing.JList<String> jAzul;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JList<String> jRojo;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSpinner spinnerMov;
    private javax.swing.JRadioButton tres;
    private javax.swing.JRadioButton uno;
    // End of variables declaration//GEN-END:variables
}
