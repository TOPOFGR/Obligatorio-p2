/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz;

import Obligatorio.Ficha;
import Obligatorio.Partida;
import Obligatorio.Sistema;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JOptionPane;

/**
 *
 * @author Santiago
 */
public class VJuego extends javax.swing.JDialog {

    /**
     * Creates new form VJuego
     */
    private JButton[][] botones;
    private Partida modelo;
    private ArrayList<String> movimientos;
    private Sistema sist;

    public VJuego() {
        initComponents();
    }

    public VJuego(Partida unJuego,Sistema s) {
        initComponents();
        this.setModal(true);
        modelo = unJuego;
        sist = s;
        ButtonGroup btrGroup = new ButtonGroup();
        btrGroup.add(A);
        btrGroup.add(D);
        btrGroup.add(I);
        panelJuego.setLayout(new GridLayout(8, 9));
        botones = new JButton[9][10];
        for (int i = 1; i <= 8; i++) {
            for (int j = 1; j <= 9; j++) {
                JButton jButton = new JButton();
                jButton.setMargin(new Insets(-5, -5, -5, -5));
                jButton.addActionListener(new ListenerBoton(i, j));
                panelJuego.add(jButton);
                botones[i][j] = jButton;
            }
        }
        btnCont.setVisible(false);
        btnSigMov.setVisible(false);
        if (modelo.isReplay()) {
            this.replay();
        }
        armarBotones();
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
        panelJuego = new javax.swing.JPanel();
        A = new javax.swing.JRadioButton();
        D = new javax.swing.JRadioButton();
        I = new javax.swing.JRadioButton();
        turno = new javax.swing.JLabel();
        btnRendirse = new javax.swing.JButton();
        btnSigMov = new javax.swing.JButton();
        btnCont = new javax.swing.JButton();
        btnPasar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(400, 600));
        getContentPane().setLayout(null);

        javax.swing.GroupLayout panelJuegoLayout = new javax.swing.GroupLayout(panelJuego);
        panelJuego.setLayout(panelJuegoLayout);
        panelJuegoLayout.setHorizontalGroup(
            panelJuegoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 260, Short.MAX_VALUE)
        );
        panelJuegoLayout.setVerticalGroup(
            panelJuegoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 230, Short.MAX_VALUE)
        );

        getContentPane().add(panelJuego);
        panelJuego.setBounds(10, 50, 260, 230);

        buttonGroup1.add(A);
        A.setSelected(true);
        A.setText("Adelante");
        getContentPane().add(A);
        A.setBounds(280, 90, 110, 23);

        buttonGroup1.add(D);
        D.setText("Derecha");
        getContentPane().add(D);
        D.setBounds(280, 130, 110, 23);

        buttonGroup1.add(I);
        I.setText("Izquierda");
        getContentPane().add(I);
        I.setBounds(280, 170, 110, 23);

        turno.setText("Turno del jugador");
        getContentPane().add(turno);
        turno.setBounds(20, 20, 170, 14);

        btnRendirse.setText("Rendirse");
        btnRendirse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRendirseActionPerformed(evt);
            }
        });
        getContentPane().add(btnRendirse);
        btnRendirse.setBounds(280, 40, 100, 23);

        btnSigMov.setText("Siguiente Movimiento");
        btnSigMov.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSigMovActionPerformed(evt);
            }
        });
        getContentPane().add(btnSigMov);
        btnSigMov.setBounds(20, 310, 210, 23);

        btnCont.setText("Continuar");
        btnCont.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnContActionPerformed(evt);
            }
        });
        getContentPane().add(btnCont);
        btnCont.setBounds(200, 350, 120, 23);

        btnPasar.setText("Pasar");
        btnPasar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPasarActionPerformed(evt);
            }
        });
        getContentPane().add(btnPasar);
        btnPasar.setBounds(300, 200, 59, 23);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnRendirseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRendirseActionPerformed
        modelo.recibirComando("X");
        this.ventanaTerm();
    }//GEN-LAST:event_btnRendirseActionPerformed

    private void btnPasarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPasarActionPerformed
        if (modelo.recibirComando("CT")) {
            this.armarBotones();
        }
    }//GEN-LAST:event_btnPasarActionPerformed

    private void btnSigMovActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSigMovActionPerformed
        if (this.movimientos.size() > 0) {
            modelo.recibirComando(this.movimientos.get(0));
            this.movimientos.remove(0);
            this.armarBotones();
        }
    }//GEN-LAST:event_btnSigMovActionPerformed

    private void btnContActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnContActionPerformed
        modelo.setReplay(false);
        this.replay();
        
    }//GEN-LAST:event_btnContActionPerformed

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
            java.util.logging.Logger.getLogger(VJuego.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VJuego.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VJuego.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VJuego.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VJuego().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton A;
    private javax.swing.JRadioButton D;
    private javax.swing.JRadioButton I;
    private javax.swing.JButton btnCont;
    private javax.swing.JButton btnPasar;
    private javax.swing.JButton btnRendirse;
    private javax.swing.JButton btnSigMov;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JPanel panelJuego;
    private javax.swing.JLabel turno;
    // End of variables declaration//GEN-END:variables

    private class ListenerBoton implements ActionListener {

        private int x;
        private int y;

        public ListenerBoton(int i, int j) {
// en el constructor se almacena la fila y columna que se presionó
            x = i;
            y = j;
        }

        public void actionPerformed(ActionEvent e) {
// cuando se presiona un botón, se ejecutará este método
            clickBoton(x, y);
        }
    }

    private void clickBoton(int fila, int columna) {
        if (!modelo.isReplay()) {
            String comando = "";
            Ficha[][] mat = modelo.getTablero();
            Ficha f = mat[fila][columna];

            comando += f.getValor();

            if (A.isSelected()) {
                comando += "A";
            }
            if (D.isSelected()) {
                comando += "D";
            }
            if (I.isSelected()) {
                comando += "I";
            }
            comando += f.getTipo().charAt(0);
            if (modelo.recibirComando(comando)) {
                armarBotones();
                this.ventanaTerm();

            }
        }

    }

    private void ventanaTerm() {
        if (modelo.isTerminado()) {
            String mensaje = "";

            if (modelo.getContador() >= 2) {
                mensaje += ("Ya no hay movimientos posibles. ");
            }
            if (modelo.getResultado().equals("Rojo")) {
                mensaje += ("Ganó el jugador rojo de Alias: " + modelo.getJugadorRojo().getAlias());
            } else {
                if (modelo.getResultado().equals("Azul")) {
                    mensaje += ("Ganó el jugador azul de Alias " + modelo.getJugadorAzul().getAlias());
                } else {
                    mensaje += ("Juego terminado en empate");
                }
            }
            JOptionPane.showMessageDialog(null, mensaje, "Juego terminado", JOptionPane.INFORMATION_MESSAGE);
            this.dispose();

        }
    }

    private void armarBotones() {
        Ficha[][] mat = modelo.getTablero();
        if (modelo.isTurnoRojo()) {
            turno.setText("Turno del Jugador Rojo");
            turno.setForeground(Color.red);
        } else {
            turno.setText("Turno del Jugador Azul");
            turno.setForeground(Color.blue);
        }
        for (int i = 1; i < 9; i++) {
            for (int j = 1; j < 10; j++) {
                if (mat[i][j].getTipo().equals("Rojo")) {
                    JButton boton = botones[i][j];
                    boton.setBackground(Color.BLACK);
                    int valor = mat[i][j].getValor();
                    boton.setText("" + mat[i][j].getValor());
                    boton.setForeground(Color.RED);
                    if (modelo.getMovimientos()[valor] && modelo.isTurnoRojo()) {
                        boton.setBackground(Color.red);
                        boton.setForeground(Color.white);
                    }
                }
                if (mat[i][j].getTipo().equals("Azul")) {
                    JButton boton = botones[i][j];
                    boton.setBackground(Color.BLACK);
                    int valor = mat[i][j].getValor();
                    boton.setText("" + valor);
                    boton.setForeground(Color.CYAN);
                    if (modelo.getMovimientos()[valor] && !modelo.isTurnoRojo()) {
                        boton.setBackground(Color.blue);
                        boton.setForeground(Color.white);
                    }
                }
                if (mat[i][j].getTipo().equals("Vacio")) {
                    JButton boton = botones[i][j];
                    boton.setBackground(Color.LIGHT_GRAY);
                    boton.setText("");
                }
            }
        }

    }

    private void replay() {
        boolean esReplay = modelo.isReplay();
        btnCont.setVisible(esReplay);
        btnSigMov.setVisible(esReplay);
        btnRendirse.setVisible(!esReplay);
        A.setVisible(!esReplay);
        I.setVisible(!esReplay);
        D.setVisible(!esReplay);
        btnPasar.setVisible(!esReplay);
        if (esReplay) {
            movimientos = (ArrayList<String>) modelo.getListaMovimientos().clone();
            Partida p = new Partida(modelo.getJugadorRojo(), modelo.getJugadorAzul(), modelo.getTipoTerm(), modelo.getMovMax(), true);
            this.modelo = p;
        } else {
            sist.agregarPartida(modelo);
        }
    }

}
