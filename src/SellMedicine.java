/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

import javax.swing.JOptionPane;
import javax.swing.table.*;
import dao.ConnectionProvider;
import java.sql.*;

import java.text.*;
import java.util.Date;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.Paragraph;
import common.OpenPdf;
import java.io.*;
import dao.PharmacyUtils;
import java.util.Calendar;

/**
 *
 * @author 91727
 */
public class SellMedicine extends javax.swing.JFrame {
    
    public String username = "";
    public String numberPattern = "^[0-9]*$";
    private int finalTotalPrice = 0;
    private String billId = "";
    
    /**
     * Creates new form SellMedicine
     */
    public SellMedicine() {
        initComponents();
    }

    public SellMedicine(String tempUsername) {
        initComponents();
        username = tempUsername;
        setLocationRelativeTo(null);
    }
    
    private void medicineName(String nameOrUniqueId){
        DefaultTableModel model = (DefaultTableModel) medicineTable.getModel();
        model.setRowCount(0);
        try{
            Connection con = ConnectionProvider.getCon();
            String query = "SELECT * FROM medicine WHERE pharmacist_username = ? AND (name LIKE ? or uniqueId LIKE ?);";
            PreparedStatement st = con.prepareStatement(query);

            // Assuming username and nameOrUniqueId are variables
            st.setString(1, username);
            st.setString(2, "%" + nameOrUniqueId + "%");  // Using "%" for SQL LIKE pattern
            st.setString(3, "%" + nameOrUniqueId + "%");

            ResultSet rs = st.executeQuery();
            
            while(rs.next()){
                model.addRow(new Object[]{rs.getString("uniqueId")+"-"+ rs.getString("name")}); 
            }
            
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
    }
    
    private void alertLowQuantityMedicines() {
        DefaultTableModel alertTableModel = (DefaultTableModel) jTable2.getModel();
        alertTableModel.setRowCount(0); // Clear existing rows

        try {
            Connection con = ConnectionProvider.getCon();
            String query = "SELECT * FROM medicine WHERE pharmacist_username = ? AND quantity < ?";
            PreparedStatement st = con.prepareStatement(query);

            st.setString(1, username);
            st.setInt(2,50);

            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                alertTableModel.addRow(new Object[]{rs.getString("uniqueId"), rs.getString("quantity")});
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    private void clearMedicineFields(){
        txtUniqueId.setText("");
        txtName.setText("");
        txtCompanyName.setText("");
        txtPricePerUnit.setText("");
        txtNoofUnits.setText("");
        txtTotalPrice.setText("");
    }
    
    public String getUniqueId(String prefix){
        return prefix + System.nanoTime();
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane3 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        txtSearch = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        medicineTable = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        txtUniqueId = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtCompanyName = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtPricePerUnit = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtNoofUnits = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtTotalPrice = new javax.swing.JTextField();
        btnAddToCart = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        cartTable = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();
        lblFinalTotalPrice = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel10 = new javax.swing.JLabel();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane3.setViewportView(jTable1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                formComponentShown(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel1.setText("Sell Medicine");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(518, 22, -1, -1));

        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 0, 51));
        jButton1.setText("Close");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1222, 6, -1, -1));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setText("Search");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(202, 121, -1, -1));

        txtSearch.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearchActionPerformed(evt);
            }
        });
        txtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchKeyReleased(evt);
            }
        });
        getContentPane().add(txtSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(77, 149, 300, -1));

        medicineTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Medicine"
            }
        ));
        medicineTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                medicineTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(medicineTable);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 190, 300, 300));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setText("Medicine ID");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(476, 121, -1, -1));

        txtUniqueId.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        getContentPane().add(txtUniqueId, new org.netbeans.lib.awtextra.AbsoluteConstraints(476, 149, 300, -1));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setText("Name");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(476, 189, 37, -1));

        txtName.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        getContentPane().add(txtName, new org.netbeans.lib.awtextra.AbsoluteConstraints(476, 217, 300, -1));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setText("Company");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(476, 257, -1, -1));

        txtCompanyName.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        getContentPane().add(txtCompanyName, new org.netbeans.lib.awtextra.AbsoluteConstraints(476, 285, 300, -1));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setText("Price Per Unit");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(903, 121, -1, -1));

        txtPricePerUnit.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        getContentPane().add(txtPricePerUnit, new org.netbeans.lib.awtextra.AbsoluteConstraints(903, 149, 300, -1));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel7.setText("No of Units");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(903, 189, -1, -1));

        txtNoofUnits.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtNoofUnits.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNoofUnitsKeyReleased(evt);
            }
        });
        getContentPane().add(txtNoofUnits, new org.netbeans.lib.awtextra.AbsoluteConstraints(903, 217, 300, -1));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel8.setText("Total Price");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(903, 257, -1, -1));

        txtTotalPrice.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        getContentPane().add(txtTotalPrice, new org.netbeans.lib.awtextra.AbsoluteConstraints(903, 285, 300, -1));

        btnAddToCart.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnAddToCart.setText("Add to Cart");
        btnAddToCart.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAddToCartMouseClicked(evt);
            }
        });
        btnAddToCart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddToCartActionPerformed(evt);
            }
        });
        getContentPane().add(btnAddToCart, new org.netbeans.lib.awtextra.AbsoluteConstraints(1108, 334, -1, -1));

        cartTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Medicine ID", "Name", "Company Name", "Price Per Unit", "No. of Units ", "Total Price"
            }
        ));
        cartTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cartTableMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(cartTable);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(476, 390, 727, 200));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel9.setText("RS:");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 610, -1, -1));

        lblFinalTotalPrice.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblFinalTotalPrice.setText("0000");
        getContentPane().add(lblFinalTotalPrice, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 610, 90, -1));

        jButton3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton3.setText("Purchase and Save");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1068, 608, -1, -1));

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Medicine ID", "Quantity"
            }
        ));
        jScrollPane4.setViewportView(jTable2);

        getContentPane().add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 530, 300, 150));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 0, 0));
        jLabel10.setText("Alert: ");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 510, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        setVisible(false);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSearchActionPerformed

    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
        // TODO add your handling code here:
        medicineName("");
        alertLowQuantityMedicines();
        txtUniqueId.setEditable(false);
        txtName.setEditable(false);
        txtCompanyName.setEditable(false);
        txtPricePerUnit.setEditable(false);
        txtTotalPrice.setEditable(false);
    }//GEN-LAST:event_formComponentShown

    private void txtSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyReleased
        // TODO add your handling code here:
        String search = txtSearch.getText();
        medicineName(search);
    }//GEN-LAST:event_txtSearchKeyReleased

    private void medicineTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_medicineTableMouseClicked
        // TODO add your handling code here:
        int index = medicineTable.getSelectedRow();
        TableModel model = medicineTable.getModel();
        String nameOrUniqueId = model.getValueAt(index, 0).toString();
        
        String uniqueId[] = nameOrUniqueId.split("-",0);
        
        try{
            Connection con = ConnectionProvider.getCon();
            String query = "SELECT * FROM medicine WHERE pharmacist_username = ? AND uniqueId = ?;";
            PreparedStatement st = con.prepareStatement(query);

           
            st.setString(1, username);
            st.setString(2, uniqueId[0]);  

            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                txtUniqueId.setText(rs.getString("uniqueId"));
                txtName.setText(rs.getString("name"));
                txtCompanyName.setText(rs.getString("companyName"));
                txtPricePerUnit.setText(rs.getString("price"));
                txtNoofUnits.setText("");
                txtTotalPrice.setText("");
            }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_medicineTableMouseClicked

    private void txtNoofUnitsKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNoofUnitsKeyReleased
        // TODO add your handling code here:
        String noofUnits = txtNoofUnits.getText();
        if(!noofUnits.equals("")){
            String price = txtPricePerUnit.getText();
            if(!noofUnits.matches(numberPattern)){
                JOptionPane.showMessageDialog(null, "No of Units field is invalid");
            }
            
            int totalPrice = Integer.parseInt(noofUnits) * Integer.parseInt(price);
            txtTotalPrice.setText(String.valueOf(totalPrice));
        }
        else{
            txtTotalPrice.setText("");
        }
    }//GEN-LAST:event_txtNoofUnitsKeyReleased

    private void btnAddToCartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddToCartActionPerformed
        // TODO add your handling code here:
        String noofUnits = txtNoofUnits.getText();
        String uniqueId = txtUniqueId.getText();
        
        if(!noofUnits.equals("") && !uniqueId.equals("")){
            String name = txtName.getText();
            String companyName = txtCompanyName.getText();
            String pricePerUnit = txtPricePerUnit.getText();
            String totalPrice = txtTotalPrice.getText();
            int checkStock = 0;
            int checkMedicineAlreadyExistInCart = 0;

            
            try{
                Connection con = ConnectionProvider.getCon();
                PreparedStatement ps = con.prepareStatement("SELECT * FROM Medicine WHERE pharmacist_username = ? AND uniqueId = "+uniqueId+";");
                ps.setString(1,username);
                ResultSet rs = ps.executeQuery();

               
                while(rs.next()){
                    if(rs.getInt("quantity") >= Integer.parseInt(noofUnits)){
                        checkStock = 1;
                    }
                    else{
                        JOptionPane.showMessageDialog(null,"Medicine is out of stock. Only "+rs.getInt("quantity")+" Left.;");
                    }
                } 
            }
            catch(Exception e){
                JOptionPane.showMessageDialog(null, e);
            }
            
            if(checkStock == 1){
                DefaultTableModel dtm = (DefaultTableModel) cartTable.getModel();
                if(cartTable.getRowCount() != 0){
                    for(int i = 0; i<cartTable.getRowCount(); i++){
                        if(Integer.parseInt(dtm.getValueAt(i,0).toString()) == Integer.parseInt(uniqueId)){
                            checkMedicineAlreadyExistInCart = 1;
                            JOptionPane.showMessageDialog(null,"Medicine already exist in cart.");
                        }
                    }
                }
                if(checkMedicineAlreadyExistInCart == 0){
                    dtm.addRow(new Object[]{ uniqueId, name, companyName, pricePerUnit, noofUnits, totalPrice });
                    finalTotalPrice  = finalTotalPrice + Integer.parseInt(totalPrice);
                    lblFinalTotalPrice.setText(String.valueOf(finalTotalPrice));
                    JOptionPane.showMessageDialog(null, "Added Successfully!");                  
                }
                clearMedicineFields();
            }
                   
        }else{
            JOptionPane.showMessageDialog(null, "No of Units and Medicine Id field is required.");
        }
    }//GEN-LAST:event_btnAddToCartActionPerformed

    private void btnAddToCartMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAddToCartMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAddToCartMouseClicked

    private void cartTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cartTableMouseClicked
        // TODO add your handling code here:
        int index = cartTable.getSelectedRow();
        int a = JOptionPane.showConfirmDialog(null,"Do You want to remove this Medicine","Select",JOptionPane.YES_NO_OPTION);
        if(a == 0){
            TableModel model = cartTable.getModel();
            String total = model.getValueAt(index,5).toString();
            finalTotalPrice = finalTotalPrice - Integer.parseInt(total);
            lblFinalTotalPrice.setText(String.valueOf(finalTotalPrice));
            ((DefaultTableModel) cartTable.getModel()).removeRow(index);
        }
    }//GEN-LAST:event_cartTableMouseClicked

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
    Connection con = null;
    try {
        con = ConnectionProvider.getCon();
        con.setAutoCommit(false); // Start the transaction

        if (finalTotalPrice != 0) {
            billId = getUniqueId("Bill-");

            DefaultTableModel dtm = (DefaultTableModel) cartTable.getModel();
            if (cartTable.getRowCount() != 0) {
                for (int i = 0; i < cartTable.getRowCount(); i++) {
                    try {
                        PreparedStatement ps = con.prepareStatement("Update Medicine set quantity = quantity-? WHERE pharmacist_username = ? AND uniqueId = ?");
                        ps.setInt(1, Integer.valueOf(dtm.getValueAt(i, 4).toString()));
                        ps.setString(2, username);
                        ps.setInt(3, Integer.valueOf(dtm.getValueAt(i, 0).toString()));
                        ps.executeUpdate();
                    } catch (Exception e) {
                        con.rollback(); // Rollback the transaction if an exception occurs
                        JOptionPane.showMessageDialog(null, e);
                        return;
                    }
                }
            }

            try {
                SimpleDateFormat myFormat = new SimpleDateFormat("dd-MM-yyyy");
                Calendar cal = Calendar.getInstance();
                PreparedStatement ps = con.prepareStatement("insert into bill(billId,billDate,totalPaid,generatedBy) values(?,?,?,?)");
                ps.setString(1, billId);
                ps.setString(2, myFormat.format(cal.getTime()));
                ps.setInt(3, finalTotalPrice);
                ps.setString(4, username);
                ps.executeUpdate();
            } catch (Exception e) {
                con.rollback(); // Rollback the transaction if an exception occurs
                JOptionPane.showMessageDialog(null, e);
                return;
            }

            // Create Bill
            com.itextpdf.text.Document doc = new com.itextpdf.text.Document();
            try {
                // ... your existing PDF creation code
		PdfWriter.getInstance(doc,new FileOutputStream(PharmacyUtils.billPath+""+billId+".pdf"));
                doc.open();
                Paragraph pharmacyName = new Paragraph("                                                    Pharmacy Managemant System\n");
                doc.add(pharmacyName);
                Paragraph starLine = new Paragraph("-------------------------------------------------------------------------------------------------------------------------------\n");
                doc.add(starLine);
                Paragraph detail = new Paragraph("\tBill ID: "+billId+"\nDate: "+ new Date() +"\n Total Paid: "+ finalTotalPrice + "\n Seller: "+ username);
                doc.add(detail);
                doc.add(starLine);
                
                PdfPTable tb1 = new PdfPTable(6);
                tb1.addCell("Medicine ID");
                tb1.addCell("Name");
                tb1.addCell("Company Name");
                tb1.addCell("Price Per Unit");
                tb1.addCell("No of Units");
                tb1.addCell("Sub Total Price");
                
                for(int i = 0 ;i<cartTable.getRowCount(); i++){
                    String a = cartTable.getValueAt(i,0 ).toString();
                    String b = cartTable.getValueAt(i,1 ).toString();
                    String c = cartTable.getValueAt(i,2 ).toString();
                    String d = cartTable.getValueAt(i,3).toString();
                    String e = cartTable.getValueAt(i,4 ).toString();
                    String f = cartTable.getValueAt(i,5 ).toString();
                    
                    tb1.addCell(a);
                    tb1.addCell(b);
                    tb1.addCell(c);
                    tb1.addCell(d);
                    tb1.addCell(e);
                    tb1.addCell(f);
                    
                }
                
                doc.add(tb1);
                doc.add(starLine);
                Paragraph thank = new Paragraph("                                                   Thankyou, Please Visit Again!");
                doc.add(thank);
                //Open pdf
                OpenPdf.openBy(String.valueOf(billId));
		
            } catch (Exception e) {
                con.rollback(); // Rollback the transaction if an exception occurs
                JOptionPane.showMessageDialog(null, e);
                return;
            }
            doc.close();

            con.commit(); // Commit the transaction

            // ... your existing code
	    setVisible(false);
            new SellMedicine(username).setVisible(true);	

        } else {
            JOptionPane.showMessageDialog(null, "Please add some Medicine to cart");
        }
    } catch (Exception e) {
        try {
            if (con != null) {
                con.rollback(); // Rollback the transaction if an exception occurs
            }
        } catch (Exception rollbackException) {
            JOptionPane.showMessageDialog(null, "Something Went Wrong!");
        }
        JOptionPane.showMessageDialog(null, e);
    } finally {
        try {
            if (con != null) {
                con.setAutoCommit(true); // Set AutoCommit back to true
                con.close();
            }
        } catch (Exception closeException) {
            // Handle closing exception
	    JOptionPane.showMessageDialog(null, "Something Went Wrong!");	
        }
    }
    }//GEN-LAST:event_jButton3ActionPerformed

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
            java.util.logging.Logger.getLogger(SellMedicine.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SellMedicine.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SellMedicine.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SellMedicine.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SellMedicine().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddToCart;
    private javax.swing.JTable cartTable;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JLabel lblFinalTotalPrice;
    private javax.swing.JTable medicineTable;
    private javax.swing.JTextField txtCompanyName;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtNoofUnits;
    private javax.swing.JTextField txtPricePerUnit;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTextField txtTotalPrice;
    private javax.swing.JTextField txtUniqueId;
    // End of variables declaration//GEN-END:variables
}
