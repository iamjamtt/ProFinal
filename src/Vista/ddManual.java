/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Conexion.ConexionSQL;
import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.TimerTask;
import javax.swing.ImageIcon;
import java.util.Timer;
import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author jamt_
 */
public class ddManual extends javax.swing.JInternalFrame {

    /**
     * Creates new form ddManual
     */
    
    public static int estado, estadoSemaforo, estadoSemaforo1, estadoSemaforo2;
    public static int sensor;
    
    //cambiando
    public static ImageIcon normal;
    
    public static int velocidadRamdon, fotoAuto;
    public static int btnG, btnG1, btnG2;
    
    //cambiando
    public static String fecha;
    public static String horaM, fechaM;
    public static String codManAut, codAux;
    
    //cambiado
    public static Timer timer;
    public static TimerTask task;
    
    //agregado
    public static Statement st;
    public static int contador=2;
    public static int tiempo;
    
    public ddManual() {
        initComponents();
        this.getContentPane().setBackground(Color.BLACK);
        btnIniciarBucle.setVisible(false);
        btnFinalizarBucle.setVisible(false);
        btnGuardarEstadoSemaforo.setVisible(false);
        btnGuardarEstadoSemaforo1.setVisible(false);
        btnGuardarEstadoSemaforo2.setVisible(false);
        btnSiguiente.setVisible(false);
        jrRojo.setEnabled(false);
        jrVerde.setEnabled(false);
        jrRojo1.setEnabled(false);
        jrVerde1.setEnabled(false);
        jrRojo2.setEnabled(false);
        jrVerde2.setEnabled(false);
        gropoDeModos.clearSelection();
        limpiarPantalla1();
        limpiarPantalla2();
        limpiarPantalla3();
    }
    
    //se cambio
    public static void limpiarPantalla1(){
        txtNroSendor.setText("Nro --");
        txtVelocidadAuto.setText("--- km/h");
        txtFecha.setText("--/--/--");
        txtHora.setText("--:--");
        normal = new ImageIcon("src/Imagenes/Semaforo.png");
        txtImagenSemaforo.setIcon(normal);
        grupoEstadoSemaforo.clearSelection();
        foto1.setIcon(null);
    }
    
    //se cambio
    public static void limpiarPantalla2(){
        txtNroSendor1.setText("Nro --");
        txtVelocidadAuto1.setText("--- km/h");
        txtFecha1.setText("--/--/--");
        txtHora1.setText("--:--");
        normal = new ImageIcon("src/Imagenes/Semaforo.png");
        txtImagenSemaforo.setIcon(normal);
        grupoEstadoSemaforo1.clearSelection();
        foto2.setIcon(null);
    }
    
    //se cambio
    public static void limpiarPantalla3(){
        txtNroSendor2.setText("Nro --");
        txtVelocidadAuto2.setText("--- km/h");
        txtFecha2.setText("--/--/--");
        txtHora2.setText("--:--");
        normal = new ImageIcon("src/Imagenes/Semaforo.png");
        txtImagenSemaforo.setIcon(normal);
        grupoEstadoSemaforo2.clearSelection();
        foto3.setIcon(null);
    }
      
    public static void pause(){
        try{
            Thread.sleep(1000);
        }catch(InterruptedException ex){
            Thread.currentThread().interrupt();
        }
    }
    
    //se cambio
    public static String codig(){
        int j;
        int cont=1;
        String num="";
        String c="";
        String SQL="select max(CodigoManuAuto) from ManuAuto";
        
        try {
            st = cn.createStatement();
            ResultSet rs=st.executeQuery(SQL);
            if(rs.next())
            {              
                 c=rs.getString(1);
            }
                    
            if(c==null){
                codManAut = "PER-001";
            }
            else{
                char r1=c.charAt(4);
                char r2=c.charAt(5);
                char r3=c.charAt(6);
                String r="";
                r=""+r1+r2+r3;
            
                 j=Integer.parseInt(r);
                 GenerarCodigos gen= new GenerarCodigos();
                 gen.generar(j);
                 codManAut = "PER-"+gen.serie();            
            }            
         
        } catch (SQLException ex) {
            System.out.println("Error codigo  -- " + ex);
        }
        return codManAut;
    }
    
    //se cambio
    public static void bucleManual(){
        velocidadRamdon = (int) ((60*Math.random()) + 20);
        
        if(sensor==0){
            fotoAuto = (int) ((9*Math.random()) + 1);
            System.out.println("random para lafoto << "+fotoAuto+" >>");
            
            codManAut=codig();
        }
        
        System.out.println("Codigo auto ==== " + codManAut);
        
        sensor++;
        
        System.out.println("Sensor ==== " + sensor);
        
        Date fechaActual = new Date();
        int anioactual = fechaActual.getYear()+1900;
        int mesactual = fechaActual.getMonth()+1;
        int diaactual = fechaActual.getDate();
        int hora = fechaActual.getHours();
        int minuto = fechaActual.getMinutes();
        int segundo = fechaActual.getSeconds();
        String horaModi;
        
        if(hora<10){
            horaModi="0"+hora;
        }else{
            horaModi=""+hora;
        }
        
        fecha = anioactual+"-"+mesactual+"-"+diaactual;
        fechaM = diaactual+"/"+mesactual+"/"+anioactual;
        horaM = horaModi+":"+minuto+":"+segundo;
        
        if(sensor==1){
            limpiarPantalla1();
            limpiarPantalla2();
            limpiarPantalla3();
            
            System.out.println("\nnro sensor 1 = " + sensor);
            txtNroSendor.setText("");
            txtNroSendor.setText("Nro "+sensor);
        
            System.out.println("\nvelocidad random 1 = " + velocidadRamdon);
            txtVelocidadAuto.setText("");
            txtVelocidadAuto.setText(""+velocidadRamdon+" km/h");
            
            txtFecha.setText("");
            txtFecha.setText(fechaM);

            txtHora.setText("");
            txtHora.setText(horaM);
            
            txtSemaforoNroSensor.setText("");
            txtSemaforoNroSensor.setText("Semaforo del Sensor Nro: "+sensor);
            
            normal = new ImageIcon("src/Imagenes/Semaforo.png");
            txtImagenSemaforo.setIcon(normal);
            
            mostrarFoto(fotoAuto);
        }else if(sensor==2){
            System.out.println("\nnro sensor 2 = " + sensor);
            txtNroSendor1.setText("");
            txtNroSendor1.setText("Nro "+sensor);
            
            System.out.println("\nvelocidad random 2 = " + velocidadRamdon);
            txtVelocidadAuto1.setText("");
            txtVelocidadAuto1.setText(""+velocidadRamdon+" km/h");
            
            txtFecha1.setText("");
            txtFecha1.setText(fechaM);

            txtHora1.setText("");
            txtHora1.setText(horaM);
            
            txtSemaforoNroSensor.setText("");
            txtSemaforoNroSensor.setText("Semaforo del Sensor Nro: "+sensor);
            
            normal = new ImageIcon("src/Imagenes/Semaforo.png");
            txtImagenSemaforo.setIcon(normal);
            
            mostrarFoto(fotoAuto);
        }else if(sensor==3){
            System.out.println("\nnro sensor 3 = " + sensor);
            txtNroSendor2.setText("");
            txtNroSendor2.setText("Nro "+sensor);
            
            System.out.println("\nvelocidad random 3 = " + velocidadRamdon);
            txtVelocidadAuto2.setText("");
            txtVelocidadAuto2.setText(""+velocidadRamdon+" km/h");
            
            txtFecha2.setText("");
            txtFecha2.setText(fechaM);

            txtHora2.setText("");
            txtHora2.setText(horaM);
            
            txtSemaforoNroSensor.setText("");
            txtSemaforoNroSensor.setText("Semaforo del Sensor Nro: "+sensor);
            
            normal = new ImageIcon("src/Imagenes/Semaforo.png");
            txtImagenSemaforo.setIcon(normal);
            
            mostrarFoto(fotoAuto);
        }
    }
    
    //se cambiooo
    public static void mostrarFoto(int valor){
        String mostrar="SELECT * FROM ImagenCarro WHERE idImagenCarro="+valor;
        
        try {
                st = cn.createStatement();
                ResultSet rs = st.executeQuery(mostrar);
                
                while(rs.next())
                {
                    Blob foto = rs.getBlob(2);
                    byte[] data = foto.getBytes(1, (int) foto.length());
                    BufferedImage img = null;
                    
                    try {
                        img = ImageIO.read(new ByteArrayInputStream(data));
                    } catch (Exception e) {
                        System.out.println("error imageio pelicula " + e);
                    }
                    
                    if(sensor==1){
                        Image images = img.getScaledInstance(foto1.getWidth(),foto1.getHeight(),Image.SCALE_SMOOTH);
                        foto1.setIcon(new ImageIcon(images));
                    }else if(sensor==2){
                        Image images = img.getScaledInstance(foto2.getWidth(),foto2.getHeight(),Image.SCALE_SMOOTH);
                        foto2.setIcon(new ImageIcon(images));
                    }else if(sensor==3){
                        Image images = img.getScaledInstance(foto3.getWidth(),foto3.getHeight(),Image.SCALE_SMOOTH);
                        foto3.setIcon(new ImageIcon(images));
                    }
                }
        } catch (SQLException ex) {
            System.out.println("Error en mostrar foto: " + ex);
        }
    }
    
    
    public static void bucleAutoRetro(){
        int distancia;
        distancia=150;
        tiempo=(distancia/velocidadRamdon)-1;
        System.out.println("/nTiempo "+ tiempo + "\n");
        
        
        for(int i=0; i<tiempo; i++){
            pause();
        }
        
    }
    
    public static void bucleAuto(){
        velocidadRamdon = (int) ((60*Math.random()) + 20);
        
        if(sensor==0){
            fotoAuto = (int) ((9*Math.random()) + 1);
            System.out.println("random para lafoto << "+fotoAuto+" >>");
            
            codManAut=codig();
        }
        
        System.out.println("\nCodigo auto ==== " + codManAut);
        
        sensor++;
        System.out.println("Sensor ==== " + sensor);
        
        Date fechaActual = new Date();
        int anioactual = fechaActual.getYear()+1900;
        int mesactual = fechaActual.getMonth()+1;
        int diaactual = fechaActual.getDate();

        int hora = fechaActual.getHours();
        int minuto = fechaActual.getMinutes();
        int segundo = fechaActual.getSeconds();

        String horaModi;
        
        if(hora<10){
            horaModi="0"+hora;
        }else{
            horaModi=""+hora;
        }
        
        fecha = anioactual+"-"+mesactual+"-"+diaactual;
        fechaM = diaactual+"/"+mesactual+"/"+anioactual;
        horaM = horaModi+":"+minuto+":"+segundo;;
        
        if(estado==2){
            if(sensor==1){
                limpiarPantalla1();
                limpiarPantalla2();
                limpiarPantalla3();

                System.out.println("\nnro sensor 1 = " + sensor);
                txtNroSendor.setText("");
                txtNroSendor.setText("Nro "+sensor);

                System.out.println("\nvelocidad random 1 = " + velocidadRamdon);
                txtVelocidadAuto.setText("");
                txtVelocidadAuto.setText(""+velocidadRamdon+" km/h");

                txtFecha.setText("");
                txtFecha.setText(fechaM);

                txtHora.setText("");
                txtHora.setText(horaM);
                
                if(velocidadRamdon<50){
                    ImageIcon verde = new ImageIcon("src/Imagenes/SemaforoVerde.png");
                    txtImagenSemaforo.setIcon(verde);
                    jrVerde.setSelected(true);
                }else{
                    ImageIcon rojo = new ImageIcon("src/Imagenes/SemaforoRojo.png");
                    txtImagenSemaforo.setIcon(rojo);
                    jrRojo.setSelected(true);
                }
                
                txtSemaforoNroSensor.setText("");
                txtSemaforoNroSensor.setText("Semaforo del Sensor Nro: "+sensor);

                mostrarFoto(fotoAuto);
                
                if(jrRojo.isSelected()==true){
                    estadoSemaforo = 1;
                    System.out.println("nroSelecionado estado semaforo ¿¿ " + estadoSemaforo);
                }else if(jrVerde.isSelected()==true){
                    estadoSemaforo = 2;
                    System.out.println("nroSelecionado estado semaforo ¿¿ " + estadoSemaforo);
                }  
            }else if(sensor==2){
                System.out.println("\nnro sensor 2 = " + sensor);
                txtNroSendor1.setText("");
                txtNroSendor1.setText("Nro "+sensor);

                System.out.println("\nvelocidad random 2 = " + velocidadRamdon);
                txtVelocidadAuto1.setText("");
                txtVelocidadAuto1.setText(""+velocidadRamdon+" km/h");

                txtFecha1.setText("");
                txtFecha1.setText(fechaM);

                txtHora1.setText("");
                txtHora1.setText(horaM);
                
                if(velocidadRamdon<50){
                    ImageIcon verde = new ImageIcon("src/Imagenes/SemaforoVerde.png");
                    txtImagenSemaforo.setIcon(verde);
                    jrVerde1.setSelected(true);
                }else{
                    ImageIcon rojo = new ImageIcon("src/Imagenes/SemaforoRojo.png");
                    txtImagenSemaforo.setIcon(rojo);
                    jrRojo1.setSelected(true);
                }
                
                txtSemaforoNroSensor.setText("");
                txtSemaforoNroSensor.setText("Semaforo del Sensor Nro: "+sensor);

                mostrarFoto(fotoAuto);
                
                if(jrRojo1.isSelected()==true){
                    estadoSemaforo1 = 1;
                    System.out.println("nroSelecionado estado semaforo 2¿¿ " + estadoSemaforo1);
                }else if(jrVerde1.isSelected()==true){
                    estadoSemaforo1 = 2;
                    System.out.println("nroSelecionado estado semaforo 2¿¿ " + estadoSemaforo1);
                }
                
            }else if(sensor==3){                
                System.out.println("\nnro sensor 3 = " + sensor);
                txtNroSendor2.setText("");
                txtNroSendor2.setText("Nro "+sensor);

                System.out.println("\nvelocidad random 3 = " + velocidadRamdon);
                txtVelocidadAuto2.setText("");
                txtVelocidadAuto2.setText(""+velocidadRamdon+" km/h");

                txtFecha2.setText("");
                txtFecha2.setText(fechaM);

                txtHora2.setText("");
                txtHora2.setText(horaM);
                
                if(velocidadRamdon<50){
                    ImageIcon verde = new ImageIcon("src/Imagenes/SemaforoVerde.png");
                    txtImagenSemaforo.setIcon(verde);
                    jrVerde2.setSelected(true);
                }else{
                    ImageIcon rojo = new ImageIcon("src/Imagenes/SemaforoRojo.png");
                    txtImagenSemaforo.setIcon(rojo);
                    jrRojo2.setSelected(true);
                }
                
                txtSemaforoNroSensor.setText("");
                txtSemaforoNroSensor.setText("Semaforo del Sensor Nro: "+sensor);

                mostrarFoto(fotoAuto);
                
                if(jrRojo2.isSelected()==true){
                    estadoSemaforo2 = 1;
                    System.out.println("nroSelecionado estado semaforo 3¿¿ " + estadoSemaforo2);
                }else if(jrVerde2.isSelected()==true){
                    estadoSemaforo2 = 2;
                    System.out.println("nroSelecionado estado semaforo 3¿¿ " + estadoSemaforo2);
                }
            }
            
            ingresarDatos();
            
            //bucleAutoRetro();
        }
    }
    
    //cambiando
    public static void ingresarDatos(){
        if(sensor==0){
            sensor=3;
        }
        String sql="INSERT INTO ManuAuto (CodigoManuAuto,fecha,hora,estadoSemaforo,velocidadAuto,idModoOrigen,idSemaforo,idUsuario,idSensor,idImagenCarro) VALUES (?,?,?,?,?,?,?,?,?,?)";
            try {
                PreparedStatement pst  = cn.prepareStatement(sql);
                
                pst.setString(1, codManAut);
                pst.setString(2, fecha);
                pst.setString(3, horaM);
                pst.setInt(4, 1);
                pst.setDouble(5, velocidadRamdon);
                pst.setInt(6, estado);
                if(sensor==1){
                    pst.setInt(7, estadoSemaforo);
                }else if(sensor==2){
                    pst.setInt(7, estadoSemaforo1);
                }else if(sensor==3){
                    pst.setInt(7, estadoSemaforo2);
                }
                pst.setInt(8, aaLogin.idUsuario);
                pst.setInt(9, sensor);
                pst.setInt(10, fotoAuto);

                pst.executeUpdate();
                
                if(sensor==3){
                    sensor=0;
                }
                
                System.out.println("\nDATOS INGRESADOS CON EXITO\n");
                
            } catch (SQLException ex) {
                System.out.println("Error al ingresar datos: " + ex);
            }
    }
    
    //cambiando
    public static void timerTask(){
            timer  = new Timer();
            task = new TimerTask() {
                @Override
                public void run() {
                    bucleAuto();
                    contador=1;
                }
            };
            timer.scheduleAtFixedRate(task, 2500, 2500); 
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        grupoEstadoSemaforo = new javax.swing.ButtonGroup();
        gropoDeModos = new javax.swing.ButtonGroup();
        grupoEstadoSemaforo1 = new javax.swing.ButtonGroup();
        grupoEstadoSemaforo2 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtVelocidadAuto = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtFecha = new javax.swing.JLabel();
        txtHora = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jrRojo = new javax.swing.JRadioButton();
        jrVerde = new javax.swing.JRadioButton();
        btnGuardarEstadoSemaforo = new LIB.FSButtonMD();
        jLabel5 = new javax.swing.JLabel();
        txtNroSendor = new javax.swing.JLabel();
        foto1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jrManual = new javax.swing.JRadioButton();
        jrAuto = new javax.swing.JRadioButton();
        btnIniciarBucle = new LIB.FSButtonMD();
        btnFinalizarBucle = new LIB.FSButtonMD();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        txtVelocidadAuto1 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtFecha1 = new javax.swing.JLabel();
        txtHora1 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jrRojo1 = new javax.swing.JRadioButton();
        jrVerde1 = new javax.swing.JRadioButton();
        btnGuardarEstadoSemaforo1 = new LIB.FSButtonMD();
        jLabel11 = new javax.swing.JLabel();
        txtNroSendor1 = new javax.swing.JLabel();
        foto2 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        txtVelocidadAuto2 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txtFecha2 = new javax.swing.JLabel();
        txtHora2 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jrRojo2 = new javax.swing.JRadioButton();
        jrVerde2 = new javax.swing.JRadioButton();
        btnGuardarEstadoSemaforo2 = new LIB.FSButtonMD();
        jLabel16 = new javax.swing.JLabel();
        txtNroSendor2 = new javax.swing.JLabel();
        foto3 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        btnSiguiente = new LIB.FSButtonMD();
        jLabel17 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        txtImagenSemaforo = new javax.swing.JLabel();
        txtSemaforoNroSensor = new javax.swing.JLabel();

        setBackground(new java.awt.Color(0, 0, 0));
        setClosable(true);
        setResizable(true);
        setTitle("Modos");
        setMaximumSize(new java.awt.Dimension(649, 464));
        setMinimumSize(new java.awt.Dimension(649, 464));

        jPanel1.setBackground(new java.awt.Color(255, 0, 0));
        jPanel1.setMaximumSize(new java.awt.Dimension(577, 358));
        jPanel1.setMinimumSize(new java.awt.Dimension(577, 358));

        jPanel2.setBackground(new java.awt.Color(0, 0, 0));

        jLabel1.setFont(new java.awt.Font("Microsoft YaHei", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Velocidad de auto:");

        txtVelocidadAuto.setFont(new java.awt.Font("Microsoft YaHei", 1, 14)); // NOI18N
        txtVelocidadAuto.setForeground(new java.awt.Color(255, 255, 102));

        jLabel2.setFont(new java.awt.Font("Microsoft YaHei", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Fecha:");

        jLabel3.setFont(new java.awt.Font("Microsoft YaHei", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Hora:");

        txtFecha.setFont(new java.awt.Font("Microsoft YaHei", 1, 14)); // NOI18N
        txtFecha.setForeground(new java.awt.Color(255, 255, 102));
        txtFecha.setText("as/ds/dasd");

        txtHora.setFont(new java.awt.Font("Microsoft YaHei", 1, 14)); // NOI18N
        txtHora.setForeground(new java.awt.Color(255, 255, 102));
        txtHora.setText("asdasd pm.");

        jLabel4.setFont(new java.awt.Font("Microsoft YaHei", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Estado de  Semaforo");

        grupoEstadoSemaforo.add(jrRojo);
        jrRojo.setFont(new java.awt.Font("Microsoft YaHei", 1, 14)); // NOI18N
        jrRojo.setForeground(new java.awt.Color(255, 255, 255));
        jrRojo.setText("Rojo");
        jrRojo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jrRojoMouseClicked(evt);
            }
        });
        jrRojo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrRojoActionPerformed(evt);
            }
        });

        grupoEstadoSemaforo.add(jrVerde);
        jrVerde.setFont(new java.awt.Font("Microsoft YaHei", 1, 14)); // NOI18N
        jrVerde.setForeground(new java.awt.Color(255, 255, 255));
        jrVerde.setText("Verde");
        jrVerde.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrVerdeActionPerformed(evt);
            }
        });

        btnGuardarEstadoSemaforo.setBackground(new java.awt.Color(255, 102, 0));
        btnGuardarEstadoSemaforo.setText("Guardar");
        btnGuardarEstadoSemaforo.setColorNormal(new java.awt.Color(255, 102, 0));
        btnGuardarEstadoSemaforo.setColorPressed(new java.awt.Color(255, 102, 0));
        btnGuardarEstadoSemaforo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarEstadoSemaforoActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Microsoft YaHei", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Sensor:");

        txtNroSendor.setFont(new java.awt.Font("Microsoft YaHei", 1, 14)); // NOI18N
        txtNroSendor.setForeground(new java.awt.Color(255, 255, 102));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtNroSendor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(txtVelocidadAuto, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel3))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(1, 1, 1))))
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jrRojo)
                        .addGap(18, 18, 18)
                        .addComponent(jrVerde)))
                .addGap(17, 17, 17)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnGuardarEstadoSemaforo, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(txtHora, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(29, 29, 29)
                .addComponent(foto1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(391, 391, 391))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(foto1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtHora, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtNroSendor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtVelocidadAuto, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jrRojo)
                                    .addComponent(jrVerde)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(btnGuardarEstadoSemaforo, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(2, 2, 2)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 675, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel3.setBackground(new java.awt.Color(0, 0, 0));
        jPanel3.setMaximumSize(new java.awt.Dimension(625, 52));
        jPanel3.setMinimumSize(new java.awt.Dimension(625, 52));

        jLabel6.setFont(new java.awt.Font("Microsoft YaHei", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Modo:");

        gropoDeModos.add(jrManual);
        jrManual.setFont(new java.awt.Font("Microsoft YaHei", 1, 18)); // NOI18N
        jrManual.setForeground(new java.awt.Color(255, 255, 255));
        jrManual.setText("Manual");
        jrManual.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrManualActionPerformed(evt);
            }
        });

        gropoDeModos.add(jrAuto);
        jrAuto.setFont(new java.awt.Font("Microsoft YaHei", 1, 18)); // NOI18N
        jrAuto.setForeground(new java.awt.Color(255, 255, 255));
        jrAuto.setText("Automatico");
        jrAuto.setToolTipText("");
        jrAuto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrAutoActionPerformed(evt);
            }
        });

        btnIniciarBucle.setText("Iniciar");
        btnIniciarBucle.setColorNormal(new java.awt.Color(153, 153, 153));
        btnIniciarBucle.setColorPressed(new java.awt.Color(153, 153, 153));
        btnIniciarBucle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIniciarBucleActionPerformed(evt);
            }
        });

        btnFinalizarBucle.setBackground(new java.awt.Color(153, 153, 153));
        btnFinalizarBucle.setText("Finalizar");
        btnFinalizarBucle.setColorNormal(new java.awt.Color(153, 153, 153));
        btnFinalizarBucle.setColorPressed(new java.awt.Color(153, 153, 153));
        btnFinalizarBucle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFinalizarBucleActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jrManual)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jrAuto)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 474, Short.MAX_VALUE)
                .addComponent(btnIniciarBucle, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnFinalizarBucle, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnIniciarBucle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnFinalizarBucle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jrAuto)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jrManual, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(6, 6, 6))))
        );

        jPanel4.setBackground(new java.awt.Color(255, 0, 0));
        jPanel4.setMaximumSize(new java.awt.Dimension(577, 358));
        jPanel4.setMinimumSize(new java.awt.Dimension(577, 358));

        jPanel5.setBackground(new java.awt.Color(0, 0, 0));

        jLabel7.setFont(new java.awt.Font("Microsoft YaHei", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Velocidad de auto:");

        txtVelocidadAuto1.setFont(new java.awt.Font("Microsoft YaHei", 1, 14)); // NOI18N
        txtVelocidadAuto1.setForeground(new java.awt.Color(255, 255, 102));

        jLabel8.setFont(new java.awt.Font("Microsoft YaHei", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Fecha:");

        jLabel9.setFont(new java.awt.Font("Microsoft YaHei", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Hora:");

        txtFecha1.setFont(new java.awt.Font("Microsoft YaHei", 1, 14)); // NOI18N
        txtFecha1.setForeground(new java.awt.Color(255, 255, 102));
        txtFecha1.setText("as/ds/dasd");

        txtHora1.setFont(new java.awt.Font("Microsoft YaHei", 1, 14)); // NOI18N
        txtHora1.setForeground(new java.awt.Color(255, 255, 102));
        txtHora1.setText("asdasd pm.");

        jLabel10.setFont(new java.awt.Font("Microsoft YaHei", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Estado de  Semaforo");

        grupoEstadoSemaforo1.add(jrRojo1);
        jrRojo1.setFont(new java.awt.Font("Microsoft YaHei", 1, 14)); // NOI18N
        jrRojo1.setForeground(new java.awt.Color(255, 255, 255));
        jrRojo1.setText("Rojo");
        jrRojo1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jrRojo1MouseClicked(evt);
            }
        });
        jrRojo1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrRojo1ActionPerformed(evt);
            }
        });

        grupoEstadoSemaforo1.add(jrVerde1);
        jrVerde1.setFont(new java.awt.Font("Microsoft YaHei", 1, 14)); // NOI18N
        jrVerde1.setForeground(new java.awt.Color(255, 255, 255));
        jrVerde1.setText("Verde");
        jrVerde1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrVerde1ActionPerformed(evt);
            }
        });

        btnGuardarEstadoSemaforo1.setBackground(new java.awt.Color(255, 102, 0));
        btnGuardarEstadoSemaforo1.setText("Guardar");
        btnGuardarEstadoSemaforo1.setColorNormal(new java.awt.Color(255, 102, 0));
        btnGuardarEstadoSemaforo1.setColorPressed(new java.awt.Color(255, 102, 0));
        btnGuardarEstadoSemaforo1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarEstadoSemaforo1ActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Microsoft YaHei", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Sensor:");

        txtNroSendor1.setFont(new java.awt.Font("Microsoft YaHei", 1, 14)); // NOI18N
        txtNroSendor1.setForeground(new java.awt.Color(255, 255, 102));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtNroSendor1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(txtVelocidadAuto1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel9))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addGap(1, 1, 1))))
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jrRojo1)
                        .addGap(18, 18, 18)
                        .addComponent(jrVerde1)))
                .addGap(17, 17, 17)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(txtHora1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtFecha1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnGuardarEstadoSemaforo1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addComponent(foto2, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(386, 386, 386))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtFecha1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                                    .addComponent(txtHora1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtNroSendor1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtVelocidadAuto1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jrRojo1)
                            .addComponent(jrVerde1)))
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(foto2, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnGuardarEstadoSemaforo1, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 675, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel6.setBackground(new java.awt.Color(255, 0, 0));
        jPanel6.setMaximumSize(new java.awt.Dimension(577, 358));
        jPanel6.setMinimumSize(new java.awt.Dimension(577, 358));

        jPanel7.setBackground(new java.awt.Color(0, 0, 0));

        jLabel12.setFont(new java.awt.Font("Microsoft YaHei", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Velocidad de auto:");

        txtVelocidadAuto2.setFont(new java.awt.Font("Microsoft YaHei", 1, 14)); // NOI18N
        txtVelocidadAuto2.setForeground(new java.awt.Color(255, 255, 102));

        jLabel13.setFont(new java.awt.Font("Microsoft YaHei", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Fecha:");

        jLabel14.setFont(new java.awt.Font("Microsoft YaHei", 1, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Hora:");

        txtFecha2.setFont(new java.awt.Font("Microsoft YaHei", 1, 14)); // NOI18N
        txtFecha2.setForeground(new java.awt.Color(255, 255, 102));
        txtFecha2.setText("as/ds/dasd");

        txtHora2.setFont(new java.awt.Font("Microsoft YaHei", 1, 14)); // NOI18N
        txtHora2.setForeground(new java.awt.Color(255, 255, 102));
        txtHora2.setText("asdasd pm.");

        jLabel15.setFont(new java.awt.Font("Microsoft YaHei", 1, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Estado de  Semaforo");

        grupoEstadoSemaforo2.add(jrRojo2);
        jrRojo2.setFont(new java.awt.Font("Microsoft YaHei", 1, 14)); // NOI18N
        jrRojo2.setForeground(new java.awt.Color(255, 255, 255));
        jrRojo2.setText("Rojo");
        jrRojo2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jrRojo2MouseClicked(evt);
            }
        });
        jrRojo2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrRojo2ActionPerformed(evt);
            }
        });

        grupoEstadoSemaforo2.add(jrVerde2);
        jrVerde2.setFont(new java.awt.Font("Microsoft YaHei", 1, 14)); // NOI18N
        jrVerde2.setForeground(new java.awt.Color(255, 255, 255));
        jrVerde2.setText("Verde");
        jrVerde2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrVerde2ActionPerformed(evt);
            }
        });

        btnGuardarEstadoSemaforo2.setBackground(new java.awt.Color(255, 102, 0));
        btnGuardarEstadoSemaforo2.setText("Guardar");
        btnGuardarEstadoSemaforo2.setColorNormal(new java.awt.Color(255, 102, 0));
        btnGuardarEstadoSemaforo2.setColorPressed(new java.awt.Color(255, 102, 0));
        btnGuardarEstadoSemaforo2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarEstadoSemaforo2ActionPerformed(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Microsoft YaHei", 1, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Sensor:");

        txtNroSendor2.setFont(new java.awt.Font("Microsoft YaHei", 1, 14)); // NOI18N
        txtNroSendor2.setForeground(new java.awt.Color(255, 255, 102));

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(jLabel16)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtNroSendor2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jLabel12))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(txtVelocidadAuto2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel14))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                                .addComponent(jLabel13)
                                .addGap(1, 1, 1))))
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jrRojo2)
                        .addGap(18, 18, 18)
                        .addComponent(jrVerde2)))
                .addGap(17, 17, 17)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(txtHora2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtFecha2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnGuardarEstadoSemaforo2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addComponent(foto3, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(392, 392, 392))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtFecha2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtHora2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtNroSendor2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtVelocidadAuto2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jrRojo2)
                            .addComponent(jrVerde2)))
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(foto3, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnGuardarEstadoSemaforo2, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 675, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel8.setBackground(new java.awt.Color(0, 0, 0));
        jPanel8.setMaximumSize(new java.awt.Dimension(237, 52));
        jPanel8.setMinimumSize(new java.awt.Dimension(237, 52));

        btnSiguiente.setText("Siguiente");
        btnSiguiente.setColorPressed(new java.awt.Color(0, 204, 51));
        btnSiguiente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSiguienteActionPerformed(evt);
            }
        });

        jLabel17.setText("jLabel17");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnSiguiente, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnSiguiente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel9.setBackground(new java.awt.Color(255, 0, 0));

        jPanel10.setBackground(new java.awt.Color(0, 0, 0));

        txtImagenSemaforo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Semaforo.png"))); // NOI18N

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtImagenSemaforo)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addComponent(txtImagenSemaforo)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        txtSemaforoNroSensor.setFont(new java.awt.Font("Microsoft YaHei", 1, 14)); // NOI18N
        txtSemaforoNroSensor.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(31, 31, 31))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(txtSemaforoNroSensor, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(56, 56, 56))))
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(21, 21, 21)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(21, 21, 21)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 27, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtSemaforoNroSensor, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(160, 160, 160)))
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jrRojoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jrRojoMouseClicked
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jrRojoMouseClicked

    private void jrVerdeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrVerdeActionPerformed
        // TODO add your handling code here:
        ImageIcon verde = new ImageIcon("src/Imagenes/SemaforoVerde.png");
        txtImagenSemaforo.setIcon(verde);
    }//GEN-LAST:event_jrVerdeActionPerformed

    private void jrRojoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrRojoActionPerformed
        // TODO add your handling code here:
        ImageIcon rojo = new ImageIcon("src/Imagenes/SemaforoRojo.png");
        txtImagenSemaforo.setIcon(rojo);
    }//GEN-LAST:event_jrRojoActionPerformed

    private void btnIniciarBucleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIniciarBucleActionPerformed
        // TODO add your handling code here:
        if(estado==2){
            timerTask();
        }else if(estado==1){  
            bucleManual();
            btnGuardarEstadoSemaforo.setVisible(true);
            btnGuardarEstadoSemaforo.setEnabled(true);
            jrRojo.setEnabled(true);
            jrVerde.setEnabled(true);
            btnSiguiente.setVisible(true);
            btnSiguiente.setEnabled(false);
        }
        btnIniciarBucle.setVisible(false);
        btnFinalizarBucle.setVisible(true);
    }//GEN-LAST:event_btnIniciarBucleActionPerformed

    private void btnFinalizarBucleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFinalizarBucleActionPerformed
        // TODO add your handling code here:
        if(estado==2){
            timer.cancel();
            timer.purge();
            contador=0;
        }
        btnIniciarBucle.setVisible(true);
        btnFinalizarBucle.setVisible(false);
        btnSiguiente.setVisible(false);
        
        jrRojo.setEnabled(false);
        jrVerde.setEnabled(false);
        jrRojo1.setEnabled(false);
        jrVerde1.setEnabled(false);
        jrRojo2.setEnabled(false);
        jrVerde2.setEnabled(false);
        
        sensor=0;
        velocidadRamdon=0;
        fotoAuto=0;
        
        limpiarPantalla1();
        limpiarPantalla2();
        limpiarPantalla3();
    }//GEN-LAST:event_btnFinalizarBucleActionPerformed

    private void jrManualActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrManualActionPerformed
        // TODO add your handling code here:
        estado = 1;
        System.out.println("Estado Manual == " + estado);
        
        if(contador==1){
            ddManual.timer.cancel();
            contador=0;
        }
        
        eeIdentificacion iden = new eeIdentificacion();
        iden.setVisible(true);
        
    }//GEN-LAST:event_jrManualActionPerformed

    private void jrAutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrAutoActionPerformed
        // TODO add your handling code here:
        estado = 2;
        System.out.println("Estado Auto == " + estado);
        
        eeIdentificacion iden = new eeIdentificacion();
        iden.setVisible(true);
        
    }//GEN-LAST:event_jrAutoActionPerformed

    private void btnSiguienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSiguienteActionPerformed
        // TODO add your handling code here:
        if(estado==1){
            System.out.println(":v:v");
            if(jrRojo.isSelected() || jrVerde.isSelected() || jrRojo1.isSelected() || jrVerde1.isSelected() || jrRojo2.isSelected() || jrVerde2.isSelected()){
                bucleManual();
                if(sensor>=3){
                    sensor=0;
                }
                if(sensor==1){
                    jrRojo.setEnabled(true);
                    jrVerde.setEnabled(true);
                    jrRojo1.setEnabled(false);
                    jrVerde1.setEnabled(false);
                    jrRojo2.setEnabled(false);
                    jrVerde2.setEnabled(false);
                    btnGuardarEstadoSemaforo.setVisible(true);
                    btnGuardarEstadoSemaforo1.setVisible(false);
                    btnGuardarEstadoSemaforo2.setVisible(false);
                }else if(sensor==2){
                    jrRojo.setEnabled(false);
                    jrVerde.setEnabled(false);
                    jrRojo1.setEnabled(true);
                    jrVerde1.setEnabled(true);
                    jrRojo2.setEnabled(false);
                    jrVerde2.setEnabled(false);
                    btnGuardarEstadoSemaforo1.setVisible(true);
                    btnGuardarEstadoSemaforo2.setVisible(false);
                    btnGuardarEstadoSemaforo.setVisible(false);
                }else if(sensor==0){
                    jrRojo.setEnabled(false);
                    jrVerde.setEnabled(false);
                    jrRojo1.setEnabled(false);
                    jrVerde1.setEnabled(false);
                    jrRojo2.setEnabled(true);
                    jrVerde2.setEnabled(true);
                    btnGuardarEstadoSemaforo2.setVisible(true);
                    btnGuardarEstadoSemaforo.setVisible(false);
                    btnGuardarEstadoSemaforo1.setVisible(false);
                }
                btnSiguiente.setEnabled(false);
            }else{
                JOptionPane.showMessageDialog(null, "Campos sin seleccionar...");
            } 
        }else if(estado==2){ 
            //ingresarDatos();
        }
        
        
        
    }//GEN-LAST:event_btnSiguienteActionPerformed

    private void btnGuardarEstadoSemaforoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarEstadoSemaforoActionPerformed
        // TODO add your handling code here:
        if(estado==1){
            if(jrRojo.isSelected() || jrVerde.isSelected()){
                    if(jrRojo.isSelected()==true){
                        estadoSemaforo = 1;
                        System.out.println("nroSelecionado ¿¿ estado semaforo manual " + estadoSemaforo);
                    }else if(jrVerde.isSelected()==true){
                        estadoSemaforo = 2;
                        System.out.println("nroSelecionado ¿¿ estado semaforo manual " + estadoSemaforo);
                    }

                System.out.println("boton GUARDAR 1 ¿? " + btnG + " ¿? eeenable");
                btnG=1;
                eeIdentificacion iden = new eeIdentificacion();
                iden.setVisible(true);

                ingresarDatos();
                
                btnSiguiente.setEnabled(true);
            }else{
                JOptionPane.showMessageDialog(null, "Campos sin seleccionar...");
            } 
        }else if(estado==2){ 
            System.out.println(":v:v");
        }
        
        
        
        
    }//GEN-LAST:event_btnGuardarEstadoSemaforoActionPerformed

    private void jrRojo1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jrRojo1MouseClicked
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jrRojo1MouseClicked

    private void jrRojo1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrRojo1ActionPerformed
        // TODO add your handling code here:
        ImageIcon rojo = new ImageIcon("src/Imagenes/SemaforoRojo.png");
        txtImagenSemaforo.setIcon(rojo);
    }//GEN-LAST:event_jrRojo1ActionPerformed

    private void jrVerde1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrVerde1ActionPerformed
        // TODO add your handling code here:
        ImageIcon verde = new ImageIcon("src/Imagenes/SemaforoVerde.png");
        txtImagenSemaforo.setIcon(verde);
    }//GEN-LAST:event_jrVerde1ActionPerformed

    private void btnGuardarEstadoSemaforo1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarEstadoSemaforo1ActionPerformed
        // TODO add your handling code here:
        if(estado==1){
            if(jrRojo1.isSelected() || jrVerde1.isSelected()){
                if(jrRojo1.isSelected()==true){
                    estadoSemaforo1 = 1;
                    System.out.println("nroSelecionado ¿¿ estado semaforo manual " + estadoSemaforo1);
                }else if(jrVerde1.isSelected()==true){
                    estadoSemaforo1 = 2;
                    System.out.println("nroSelecionado ¿¿ estado semaforo manual " + estadoSemaforo1);
                }

                System.out.println("boton GUARDAR 2 ¿? " + btnG1 + " ¿? eeenable");
                btnG1=1;

                eeIdentificacion iden = new eeIdentificacion();
                iden.setVisible(true);

                ingresarDatos();
                
                btnSiguiente.setEnabled(true);
        }else{
                JOptionPane.showMessageDialog(null, "Campos sin seleccionar...");
            } 
        }else if(estado==2){ 
            System.out.println(":v:v");
        }
    }//GEN-LAST:event_btnGuardarEstadoSemaforo1ActionPerformed

    private void jrRojo2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jrRojo2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jrRojo2MouseClicked

    private void jrRojo2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrRojo2ActionPerformed
        // TODO add your handling code here:
        ImageIcon rojo = new ImageIcon("src/Imagenes/SemaforoRojo.png");
        txtImagenSemaforo.setIcon(rojo);
    }//GEN-LAST:event_jrRojo2ActionPerformed

    private void jrVerde2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrVerde2ActionPerformed
        // TODO add your handling code here:
        ImageIcon verde = new ImageIcon("src/Imagenes/SemaforoVerde.png");
        txtImagenSemaforo.setIcon(verde);
    }//GEN-LAST:event_jrVerde2ActionPerformed

    private void btnGuardarEstadoSemaforo2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarEstadoSemaforo2ActionPerformed
        // TODO add your handling code here:
        if(estado==1){
            if(jrRojo2.isSelected() || jrVerde2.isSelected()){
                if(jrRojo2.isSelected()==true){
                    estadoSemaforo2 = 1;
                    System.out.println("nroSelecionado ¿¿ estado semaforo manual " + estadoSemaforo2);
                }else if(jrVerde2.isSelected()==true){
                    estadoSemaforo2 = 2;
                    System.out.println("nroSelecionado ¿¿ estado semaforo manual " + estadoSemaforo2);
                }

                System.out.println("boton GUARDAR 3 ¿? " + btnG2 + " ¿? eeenable");
                btnG2=1;

                eeIdentificacion iden = new eeIdentificacion();
                iden.setVisible(true);

                ingresarDatos();
                
                btnSiguiente.setEnabled(true);
        }else{
                JOptionPane.showMessageDialog(null, "Campos sin seleccionar...");
            } 
        }else if(estado==2){ 
            System.out.println(":v:v");
        }
    }//GEN-LAST:event_btnGuardarEstadoSemaforo2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static LIB.FSButtonMD btnFinalizarBucle;
    public static LIB.FSButtonMD btnGuardarEstadoSemaforo;
    public static LIB.FSButtonMD btnGuardarEstadoSemaforo1;
    public static LIB.FSButtonMD btnGuardarEstadoSemaforo2;
    public static LIB.FSButtonMD btnIniciarBucle;
    public static LIB.FSButtonMD btnSiguiente;
    public static javax.swing.JLabel foto1;
    public static javax.swing.JLabel foto2;
    public static javax.swing.JLabel foto3;
    private javax.swing.ButtonGroup gropoDeModos;
    public static javax.swing.ButtonGroup grupoEstadoSemaforo;
    public static javax.swing.ButtonGroup grupoEstadoSemaforo1;
    public static javax.swing.ButtonGroup grupoEstadoSemaforo2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    public static javax.swing.JRadioButton jrAuto;
    public static javax.swing.JRadioButton jrManual;
    public static javax.swing.JRadioButton jrRojo;
    public static javax.swing.JRadioButton jrRojo1;
    public static javax.swing.JRadioButton jrRojo2;
    public static javax.swing.JRadioButton jrVerde;
    public static javax.swing.JRadioButton jrVerde1;
    public static javax.swing.JRadioButton jrVerde2;
    public static javax.swing.JLabel txtFecha;
    public static javax.swing.JLabel txtFecha1;
    public static javax.swing.JLabel txtFecha2;
    public static javax.swing.JLabel txtHora;
    public static javax.swing.JLabel txtHora1;
    public static javax.swing.JLabel txtHora2;
    public static javax.swing.JLabel txtImagenSemaforo;
    public static javax.swing.JLabel txtNroSendor;
    public static javax.swing.JLabel txtNroSendor1;
    public static javax.swing.JLabel txtNroSendor2;
    public static javax.swing.JLabel txtSemaforoNroSensor;
    public static javax.swing.JLabel txtVelocidadAuto;
    public static javax.swing.JLabel txtVelocidadAuto1;
    public static javax.swing.JLabel txtVelocidadAuto2;
    // End of variables declaration//GEN-END:variables
Conexion.ConexionSQL cc = new ConexionSQL();
public static Connection cn= ConexionSQL.conexionn();
}
