/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Conexion.ConexionSQL;
import static Vista.ddManual.btnFinalizarBucle;
import static Vista.ddManual.btnG;
import static Vista.ddManual.btnG1;
import static Vista.ddManual.btnG2;
import static Vista.ddManual.btnGuardarEstadoSemaforo;
import static Vista.ddManual.btnGuardarEstadoSemaforo1;
import static Vista.ddManual.btnGuardarEstadoSemaforo2;
import static Vista.ddManual.btnIniciarBucle;
import static Vista.ddManual.btnSiguiente;
import static Vista.ddManual.horaM;
import static Vista.ddManual.jrRojo;
import static Vista.ddManual.jrRojo1;
import static Vista.ddManual.jrRojo2;
import static Vista.ddManual.jrVerde;
import static Vista.ddManual.jrVerde1;
import static Vista.ddManual.jrVerde2;
import static Vista.ddManual.sensor;
import static Vista.ddManual.txtImagenSemaforo;
import static Vista.ddManual.velocidadRamdon;
import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author jamt_
 */
public class eeIdentificacion extends javax.swing.JFrame {

    /**
     * Creates new form eeIdentificacion
     */
    public eeIdentificacion() {
        initComponents();
        this.getContentPane().setBackground(Color.BLACK);
        this.setLocationRelativeTo(null);
        txtUserName.setText(""+aaLogin.username);
        txtUserName.setEditable(false);
    }
    
    void confirmarUsuario(String contra){
        int cont=0;
        String mostrar = "select * from Usuario WHERE pasword = '"+contra+"' and idUsuario="+aaLogin.idUsuario;
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(mostrar);
            
            
            if(rs.next()){
                cont++;
            }
            
            
            if(cont==1){
                if(ddManual.estado==1){ 
                    if(ddManual.sensor==0 && ddManual.velocidadRamdon==0){
                        btnIniciarBucle.setVisible(true);
                    }
                    
                    if(btnG==1){
                        jrRojo.setEnabled(false);
                        jrVerde.setEnabled(false);
                        btnGuardarEstadoSemaforo.setVisible(false);
                        btnG=0;
                    }
                    
                    if(btnG1==1){
                        jrRojo1.setEnabled(false);
                        jrVerde1.setEnabled(false);
                        btnGuardarEstadoSemaforo1.setVisible(false);
                        btnG1=0;
                    }
                    
                    if(btnG2==1){
                        jrRojo2.setEnabled(false);
                        jrVerde2.setEnabled(false);
                        btnGuardarEstadoSemaforo2.setVisible(false);
                        btnG2=0;
                    }
                    
                    if(btnIniciarBucle.isVisible()==false){
                        if(sensor>=3){
                            sensor=0;
                        }
                        
                        if(ddManual.contador==0){
                            ddManual.bucleManual();
                            
                            if(sensor==1){
                                btnGuardarEstadoSemaforo.setVisible(true);
                                btnGuardarEstadoSemaforo.setEnabled(true);
                                jrRojo.setEnabled(true);
                                jrVerde.setEnabled(true);
                                btnSiguiente.setVisible(true);
                                btnSiguiente.setEnabled(false);
                            }else if(sensor==2){
                                btnGuardarEstadoSemaforo1.setVisible(true);
                                btnGuardarEstadoSemaforo1.setEnabled(true);
                                jrRojo1.setEnabled(true);
                                jrVerde1.setEnabled(true);
                                btnSiguiente.setVisible(true);
                                btnSiguiente.setEnabled(false);
                            }else if(sensor>=3){
                                btnGuardarEstadoSemaforo2.setVisible(true);
                                btnGuardarEstadoSemaforo2.setEnabled(true);
                                jrRojo2.setEnabled(true);
                                jrVerde2.setEnabled(true);
                                btnSiguiente.setVisible(true);
                                btnSiguiente.setEnabled(false);
                            }

                            System.out.println("\n\tSe hizo el cambio de Automatico a Manual con exito");
                            ddManual.contador=2;
                        }
                    } 
                }else if(ddManual.estado==2){
                    if(ddManual.sensor==0 && ddManual.velocidadRamdon==0){
                        btnIniciarBucle.setVisible(true);
                    }
                    
                    if(btnIniciarBucle.isVisible()==false){
                        btnSiguiente.setVisible(false);
                        if(btnGuardarEstadoSemaforo.isVisible()==true || btnGuardarEstadoSemaforo1.isVisible()==true || btnGuardarEstadoSemaforo2.isVisible()==true){
                            if(velocidadRamdon>=50){
                                ImageIcon verde = new ImageIcon("src/Imagenes/SemaforoVerde.png");
                                txtImagenSemaforo.setIcon(verde);
                                if(sensor==1){
                                    jrVerde.setSelected(true);
                                }else if(sensor==2){
                                    jrVerde1.setSelected(true);
                                }else if(sensor==3){
                                    jrVerde2.setSelected(true);
                                }
                            }else{
                                ImageIcon rojo = new ImageIcon("src/Imagenes/SemaforoRojo.png");
                                txtImagenSemaforo.setIcon(rojo);
                                 if(sensor==1){
                                    jrRojo.setSelected(true);
                                }else if(sensor==2){
                                    jrRojo1.setSelected(true);
                                }else if(sensor==3){
                                    jrRojo2.setSelected(true);
                                }
                            }

                            jrVerde.setEnabled(true);
                            jrVerde1.setEnabled(true);
                            jrVerde2.setEnabled(true);
                            jrRojo.setEnabled(true);
                            jrRojo1.setEnabled(true);
                            jrRojo2.setEnabled(true);
                            
                            ddManual.ingresarDatos();

                        }
                        btnGuardarEstadoSemaforo.setVisible(false);
                        btnGuardarEstadoSemaforo1.setVisible(false);
                        btnGuardarEstadoSemaforo2.setVisible(false);
                        
                        /*if(sensor>=3){
                            sensor=0;
                        }*/
                        
                        ddManual.timerTask();
                        
                        System.out.println("\n\tCambio de Manual a Automatico exitoso");
                    }
                }
                
                this.dispose();
            }else{
                JOptionPane.showMessageDialog(null, "Contraseña Incorrecta");
                txtpaSS.setText("");
            }
                 
        } catch (SQLException ex) {
            System.out.println("Error Logear Usuario -- " + ex);
        }   
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
        jPanel2 = new javax.swing.JPanel();
        txtpaSS = new LIB.FSPasswordFieldMD();
        txtUserName = new LIB.FSTexFieldMD();
        jLabel1 = new javax.swing.JLabel();
        btnCerrar = new javax.swing.JButton();
        btnConfirmar = new LIB.FSButtonMD();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(0, 0, 0));

        txtpaSS.setBackground(new java.awt.Color(0, 0, 0));
        txtpaSS.setPlaceholder("Password");

        txtUserName.setColorTransparente(true);
        txtUserName.setPlaceholder("UserName");

        jLabel1.setFont(new java.awt.Font("Microsoft YaHei", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Complete su identificacion");

        btnCerrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Cerrar2.png"))); // NOI18N
        btnCerrar.setBorderPainted(false);
        btnCerrar.setContentAreaFilled(false);
        btnCerrar.setMaximumSize(new java.awt.Dimension(32, 32));
        btnCerrar.setMinimumSize(new java.awt.Dimension(32, 32));
        btnCerrar.setPreferredSize(new java.awt.Dimension(32, 32));
        btnCerrar.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Cerrar2.png"))); // NOI18N
        btnCerrar.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Cerrar.png"))); // NOI18N
        btnCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarActionPerformed(evt);
            }
        });

        btnConfirmar.setText("Confirmar");
        btnConfirmar.setColorNormal(new java.awt.Color(255, 0, 0));
        btnConfirmar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfirmarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnCerrar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(145, 145, 145)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtUserName, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(txtpaSS, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(btnConfirmar, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap(158, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnCerrar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtUserName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtpaSS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnConfirmar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(53, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

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

    private void btnCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btnCerrarActionPerformed

    private void btnConfirmarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmarActionPerformed
        // TODO add your handling code here:
        String contra= new String(txtpaSS.getPassword());
        if(contra.equals("")){
            JOptionPane.showMessageDialog(null, "Campo Vacio");
        }else{
            confirmarUsuario(contra);
        }
        
    }//GEN-LAST:event_btnConfirmarActionPerformed

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
            java.util.logging.Logger.getLogger(eeIdentificacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(eeIdentificacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(eeIdentificacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(eeIdentificacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new eeIdentificacion().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCerrar;
    private LIB.FSButtonMD btnConfirmar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private LIB.FSTexFieldMD txtUserName;
    private LIB.FSPasswordFieldMD txtpaSS;
    // End of variables declaration//GEN-END:variables
Conexion.ConexionSQL cc = new ConexionSQL();
Connection cn= ConexionSQL.conexionn();
}
