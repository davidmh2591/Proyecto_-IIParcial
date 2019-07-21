/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hn.uth.pa1.p1.aplicacion.humanos;

import hn.uth.pa1.p1.datos.Conexion;
import hn.uth.pa1.p1.objetos.Humano;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author uth
 */
public class FrmGestionarHumano extends javax.swing.JFrame {
    Conexion con = new Conexion();
    Connection cn;
    PreparedStatement ps;
    Statement st;
    ResultSet rs;
    DefaultTableModel modelo;
    Humano humano = new Humano(0,"","",0,"","","");
    int id;
    
    public void Listar() {
        String sql = "select *From HUMANOS";
        try{
            cn= con.getConnection();
            st=cn.createStatement();
            rs=st.executeQuery(sql);
            Object[] Humano= new Object[7];
            modelo =(DefaultTableModel)tblHumanos.getModel();
            while (rs.next()){
                Humano[0]=rs.getInt("ID");
                Humano[1]=rs.getString("NOMBRE");
                Humano[2]=rs.getString("APELLIDO");
                Humano[3]=rs.getDouble("PESO");
                Humano[4]=rs.getString("SEXO");
                Humano[5]=rs.getString("DEPARTAMENTO");
                Humano[6]=rs.getString("MUNICIPIO");
                modelo.addRow(Humano);
                
            }
            tblHumanos.setModel(modelo);
            
            
        }catch (Exception e) {
            System.out.println("Error: "+ e.getMessage());
            
        }
    
    }
     
     public void limpiarTabla(){
      txtId.setText(null);
      txtNombre.setText(null);
      txtApellido.setText(null);
      txtPeso.setText(null);
      optFemenino.isSelected();
      cmbDepartamento.setSelectedIndex(0);
      lstMunicipio.setSelectedIndex(0);
     
         
     }
     public void Buscar(){
        
         try{
           
             cn= con.getConnection();
             ps = cn.prepareStatement("SELECT *FROM HUMANOS WHERE ID = ?");
             ps.setInt(1, humano.getId());
             
             rs = ps.executeQuery();
             if (rs.next()){
                 txtId.setText(String.valueOf(rs.getInt("ID")));
                 txtNombre.setText(rs.getString("NOMBRE"));
                 txtApellido.setText(rs.getString("APELLIDO"));
                 txtPeso.setText(String.valueOf(rs.getDouble("PESO")));
                 if(rs.getString("SEXO").equals("F")){
                     optFemenino.isSelected();
                 }else{
                     optMasculino.isSelected();
                 }
                 cmbDepartamento.setSelectedItem(rs.getString("DEPARTAMENTO"));
                 lstMunicipio.setSelectedValue(rs.getString("MUNICIPIO"), true);
                 
             }else{
                 JOptionPane.showMessageDialog(null, "Humano no encontrado");
             }
             ps.close();
             cn.close();
         }catch(Exception e){
             System.out.println("Error en Buscar: "+ e.toString());
         }
     }
    public   void guardar(){
            humano.setId(Integer.parseInt(txtId.getText())); 
            humano.setNombre(txtNombre.getText());
            humano.setApellido(txtApellido.getText());
            humano.setPeso(Double.parseDouble(txtPeso.getText()));

            String sexo = "F";
            if (optMasculino.isSelected()) {
                sexo = "M";
            }
            humano.setSexo(sexo);

            String departamento = cmbDepartamento.getSelectedItem().toString();
            humano.setDepartamento(departamento);
            String municipio = lstMunicipio.getSelectedValue();
            humano.setMunicipio(municipio);
                
               
            try{
                cn = con.getConnection();
                ps = cn.prepareStatement("INSERT INTO HUMANOS(ID,NOMBRE,APELLIDO,PESO,SEXO,DEPARTAMENTO,MUNICIPIO)VALUES(?,?,?,?,?,?,?)");
                ps.setInt(1, humano.getId());
                ps.setString(2, humano.getNombre());
                ps.setString(3, humano.getApellido());
                ps.setDouble(4, humano.getPeso());
                ps.setString(5, humano.getSexo());
                ps.setString(6, humano.getDepartamento());
                ps.setString(7, humano.getMunicipio());
                int res = ps.executeUpdate();
                if (res >0){
                    JOptionPane.showMessageDialog(null, "Humano Agregado...!!");
                }else{
                    JOptionPane.showMessageDialog(null, "Error al guardar Humano");
                }
                ps.close();
                cn.close();
                limpiarTabla();
            }catch (Exception e){
                System.out.println("Error en Guardar: "+e.toString());
                limpiarTabla();
            }
    }
    public void Modificar(){
        humano.setId(Integer.parseInt(txtId.getText())); 
            humano.setNombre(txtNombre.getText());
            humano.setApellido(txtApellido.getText());
            humano.setPeso(Double.parseDouble(txtPeso.getText()));

            String sexo = "F";
            if (optMasculino.isSelected()) {
                sexo = "M";
            }
            humano.setSexo(sexo);

            String departamento = cmbDepartamento.getSelectedItem().toString();
            humano.setDepartamento(departamento);
            String municipio = lstMunicipio.getSelectedValue();
            humano.setMunicipio(municipio);
                
               
            try{
                cn = con.getConnection();
                ps = cn.prepareStatement("UPDATE HUMANOS SET ID=?,NOMBRE=?,APELLIDO=?,PESO=?,SEXO=?,DEPARTAMENTO=?,MUNICIPIO=? WHERE ID=?");
                ps.setInt(1, humano.getId());
                ps.setString(2, humano.getNombre());
                ps.setString(3, humano.getApellido());
                ps.setDouble(4, humano.getPeso());
                ps.setString(5, humano.getSexo());
                ps.setString(6, humano.getDepartamento());
                ps.setString(7, humano.getMunicipio());
                int res = ps.executeUpdate();
                if (res >0){
                    JOptionPane.showMessageDialog(null, "Humano Modifcado...!!");
                }else{
                    JOptionPane.showMessageDialog(null, "Error al modificar Humano");
                }
                ps.close();
                cn.close();
                limpiarTabla();
            }catch (Exception e){
                System.out.println("Error en Modificar: "+e.toString());
                limpiarTabla();
            }
    }
     public void Eliminar(){
               
               
            try{
                cn = con.getConnection();
                ps = cn.prepareStatement("DELETE FROM HUMANOS WHERER ID=?");
                ps.setInt(1, humano.getId());
           
                int res = ps.executeUpdate();
                if (res >0){
                    JOptionPane.showMessageDialog(null, "Humano Eliminado...!!");
                }else{
                    JOptionPane.showMessageDialog(null, "Error al elimiar Humano");
                }
                ps.close();
                cn.close();
                limpiarTabla();
            }catch (Exception e){
                System.out.println("Error en Guardar: "+e.toString());
                limpiarTabla();
            }
     }
    
    public FrmGestionarHumano() {
        initComponents();
        setLocationRelativeTo(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btgSexo = new javax.swing.ButtonGroup();
        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        txtId = new javax.swing.JTextField();
        lblNombre = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        lblApellido = new javax.swing.JLabel();
        txtApellido = new javax.swing.JTextField();
        lblTituloPrincipal = new javax.swing.JLabel();
        lblId = new javax.swing.JLabel();
        lblDepartamento = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        lstMunicipio = new javax.swing.JList<>();
        lblMunicipios = new javax.swing.JLabel();
        txtPeso = new javax.swing.JTextField();
        lblPeso = new javax.swing.JLabel();
        lblSexo = new javax.swing.JLabel();
        optFemenino = new javax.swing.JRadioButton();
        optMasculino = new javax.swing.JRadioButton();
        cmbDepartamento = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblHumanos = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        btnGuardar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnBuscar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblNombre.setText("Nombre:");

        lblApellido.setText("Apelliido:");

        lblTituloPrincipal.setText("Gestionar Humano");

        lblId.setText("Id:");

        lblDepartamento.setText("Departamento:");

        lstMunicipio.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Tegucigalpa", "Comayagua", "La Paz", "San Pedro Sula", "Siguatepeque" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(lstMunicipio);

        lblMunicipios.setText("Municipios:");

        lblPeso.setText("Peso:");

        lblSexo.setText("Sexo:");

        btgSexo.add(optFemenino);
        optFemenino.setSelected(true);
        optFemenino.setText("Femenino");

        btgSexo.add(optMasculino);
        optMasculino.setText("Masculino");

        cmbDepartamento.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "seleccione", "Francisco Morazan", "Cortes", "La Paz" }));
        cmbDepartamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbDepartamentoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(132, 132, 132)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblSexo)
                        .addGap(50, 50, 50)
                        .addComponent(optFemenino)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(optMasculino)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(lblPeso)
                                    .addGap(61, 61, 61)
                                    .addComponent(txtPeso, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(lblApellido)
                                    .addGap(42, 42, 42)
                                    .addComponent(txtApellido, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(lblNombre)
                                    .addGap(42, 42, 42)
                                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(lblId)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addGap(42, 42, 42)
                                            .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(lblTituloPrincipal)
                                            .addGap(72, 72, 72)))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblDepartamento)
                                    .addComponent(lblMunicipios))
                                .addGap(60, 60, 60)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addComponent(cmbDepartamento, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addContainerGap(108, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(lblTituloPrincipal)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblId)
                    .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNombre)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblApellido)
                    .addComponent(txtApellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPeso)
                    .addComponent(txtPeso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblSexo)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(optFemenino)
                        .addComponent(optMasculino)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblDepartamento)
                    .addComponent(cmbDepartamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblMunicipios)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(51, Short.MAX_VALUE))
        );

        tblHumanos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"1", "a", "a", "a", "a", null, null},
                {"2", "3b", "4b", "b", "b", null, null}
            },
            new String [] {
                "id", "nombre", "apellido", "peso", "sexo", "departamento", "municipio"
            }
        ));
        tblHumanos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHumanosMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblHumanos);

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        btnModificar.setText("Modificar");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });

        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(btnGuardar)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnModificar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnEliminar))
                    .addComponent(btnBuscar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(28, 28, 28)
                .addComponent(btnCancelar)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardar)
                    .addComponent(btnCancelar)
                    .addComponent(btnModificar)
                    .addComponent(btnEliminar))
                .addGap(18, 18, 18)
                .addComponent(btnBuscar)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 425, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(13, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(102, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
        int respuesta=JOptionPane.showConfirmDialog(this
            , "¿Esta seguro de querer salir?"
            ,"Saliendo de la app"
            ,JOptionPane.YES_NO_OPTION);

        if (respuesta==JOptionPane.YES_OPTION) {
            JOptionPane.showMessageDialog(this,"saliendo de la app");
            this.dispose(); //para cerrar la aplicacion
        }
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
            
        guardar();
            Listar();

  
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void cmbDepartamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbDepartamentoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbDepartamentoActionPerformed

    private void tblHumanosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHumanosMouseClicked
        int fila= tblHumanos.getSelectedRow();
        if (fila==-1){
            JOptionPane.showMessageDialog(null, "Humano no Seleccionado");
        }else{
            id=Integer.parseInt((String)tblHumanos.getValueAt(fila, 0).toString());
            String nombre=(String)tblHumanos.getValueAt(fila, 1);
            String apellido=(String)tblHumanos.getValueAt(fila, 2);
            double peso=Double.parseDouble((String)tblHumanos.getValueAt(fila, 3).toString());
            String sexo=(String)tblHumanos.getValueAt(fila, 4);
            String departamento=(String)tblHumanos.getValueAt(fila, 5);
            String municipio=(String)tblHumanos.getValueAt(fila, 6);
            txtId.setText(""+id);
            txtNombre.setText(nombre);
            txtApellido.setText(apellido);
            txtPeso.setText(""+peso);
            if (sexo == "F"){
            optFemenino.setSelected(true);
            optMasculino.setSelected(false);
            }else {
                optMasculino.setSelected(true);
                optFemenino.setSelected(false);
            }
            
            cmbDepartamento.setSelectedItem(departamento);
            lstMunicipio.setSelectedValue(municipio, true);
            
        }
    }//GEN-LAST:event_tblHumanosMouseClicked

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        Modificar();
        Listar();
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        Buscar();
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        Eliminar();
        Listar();
    
    }//GEN-LAST:event_btnEliminarActionPerformed

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
            java.util.logging.Logger.getLogger(FrmGestionarHumano.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmGestionarHumano.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmGestionarHumano.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmGestionarHumano.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmGestionarHumano().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup btgSexo;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnModificar;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cmbDepartamento;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblApellido;
    private javax.swing.JLabel lblDepartamento;
    private javax.swing.JLabel lblId;
    private javax.swing.JLabel lblMunicipios;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblPeso;
    private javax.swing.JLabel lblSexo;
    private javax.swing.JLabel lblTituloPrincipal;
    private javax.swing.JList<String> lstMunicipio;
    private javax.swing.JRadioButton optFemenino;
    private javax.swing.JRadioButton optMasculino;
    private javax.swing.JTable tblHumanos;
    private javax.swing.JTextField txtApellido;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtPeso;
    // End of variables declaration//GEN-END:variables
}
