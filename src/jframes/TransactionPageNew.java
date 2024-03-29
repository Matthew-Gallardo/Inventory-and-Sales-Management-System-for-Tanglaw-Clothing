/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package jframes;

import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.table.DefaultTableModel;
import static jframes.DBConnection.con;
import java.awt.Color;

/**
 *
 * @author Administrator
 */
public class TransactionPageNew extends javax.swing.JFrame {

    /**
     * Creates new form CategoryPage
     */
    public TransactionPageNew() {
        initComponents();
        setTransactionDetailsToTable();
    }
    DefaultTableModel model;
    
    public void setTransactionDetailsToTable(){
        
        try {
            con = DBConnection.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM orderedproducts AS a ,order_details AS b ,product_details AS c WHERE a.idorder = b.idorder AND a.idproduct =c.idproduct ");
            
            while(rs.next()){
                String idorderedprod = rs.getString("idorderedproducts");
                String idorder = rs.getString("idorder");
                String idcustomer = rs.getString("idcustomer");
                String date = rs.getString("date");
                String idproduct = rs.getString("idproduct");
                String product = rs.getString("product");
                String price= rs.getString("price");
                String quantity = rs.getString("quantity");
                String total= rs.getString("total");
                
                
               
                
                
                Object[] obj = {idorderedprod,idorder,idcustomer,date,idproduct,product,price,quantity,total};
                model =(DefaultTableModel) tabletransaction.getModel();
                model.addRow(obj);
            }
        } catch (Exception e) {
            e.printStackTrace();
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

        jPanel4 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        panelcat4 = new javax.swing.JPanel();
        staff = new javax.swing.JLabel();
        panelcat3 = new javax.swing.JPanel();
        transactions = new javax.swing.JLabel();
        panelcat2 = new javax.swing.JPanel();
        orders = new javax.swing.JLabel();
        panelcat1 = new javax.swing.JPanel();
        products = new javax.swing.JLabel();
        panelcat = new javax.swing.JPanel();
        categorylabel1 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jLabel35 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabletransaction = new rojeru_san.complementos.RSTableMetro();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel8.setBackground(new java.awt.Color(128, 0, 0));
        jPanel8.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8-close-24.png"))); // NOI18N
        jLabel21.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel21MouseClicked(evt);
            }
        });
        jPanel8.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(1260, 30, 30, 20));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(204, 204, 204));
        jLabel4.setText("Polytechnic University of the Philippines ");
        jPanel8.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 30, 530, -1));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Tanglaw Assets/image 1.png"))); // NOI18N
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });
        jPanel8.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        jPanel4.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1330, 80));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel7MouseClicked(evt);
            }
        });
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel18.setBackground(new java.awt.Color(204, 0, 0));
        jLabel18.setFont(new java.awt.Font("Verdana", 3, 17)); // NOI18N
        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Tanglaw Assets/HOME/vuesax/bold/element-3.png"))); // NOI18N
        jLabel18.setText("Home Page");
        jLabel18.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel18MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel18MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel18MouseExited(evt);
            }
        });
        jPanel7.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, 140, 40));

        jPanel2.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 120, 370, 60));

        panelcat4.setBackground(new java.awt.Color(255, 255, 255));
        panelcat4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panelcat4MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                panelcat4MouseExited(evt);
            }
        });
        panelcat4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        staff.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        staff.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Tanglaw Assets/customericon.png"))); // NOI18N
        staff.setText("Staff");
        staff.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                staffMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                staffMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                staffMouseExited(evt);
            }
        });
        panelcat4.add(staff, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, 160, 40));

        jPanel2.add(panelcat4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 440, 350, 60));

        panelcat3.setBackground(new java.awt.Color(143, 83, 85));
        panelcat3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panelcat3MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                panelcat3MouseExited(evt);
            }
        });
        panelcat3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        transactions.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        transactions.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Tanglaw Assets/transaction.png"))); // NOI18N
        transactions.setText("Transactions");
        transactions.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                transactionsMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                transactionsMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                transactionsMouseExited(evt);
            }
        });
        panelcat3.add(transactions, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, 160, 40));

        jPanel2.add(panelcat3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 380, 350, 60));

        panelcat2.setBackground(new java.awt.Color(255, 255, 255));
        panelcat2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panelcat2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                panelcat2MouseExited(evt);
            }
        });
        panelcat2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        orders.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        orders.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Tanglaw Assets/order.png"))); // NOI18N
        orders.setText("Orders (Point of Sales)");
        orders.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ordersMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                ordersMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                ordersMouseExited(evt);
            }
        });
        panelcat2.add(orders, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, 280, 40));

        jPanel2.add(panelcat2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 310, 350, 60));

        panelcat1.setBackground(new java.awt.Color(255, 255, 255));
        panelcat1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panelcat1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                panelcat1MouseExited(evt);
            }
        });
        panelcat1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        products.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        products.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Tanglaw Assets/HOME/vuesax/linear/chart.png"))); // NOI18N
        products.setText("Products");
        products.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                productsMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                productsMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                productsMouseExited(evt);
            }
        });
        panelcat1.add(products, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, 150, 40));

        jPanel2.add(panelcat1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 240, 350, 60));

        panelcat.setBackground(new java.awt.Color(255, 255, 255));
        panelcat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panelcatMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                panelcatMouseExited(evt);
            }
        });
        panelcat.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        categorylabel1.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        categorylabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Tanglaw Assets/HOME/vuesax/linear/calendar-edit.png"))); // NOI18N
        categorylabel1.setText("Categories");
        categorylabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                categorylabel1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                categorylabel1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                categorylabel1MouseExited(evt);
            }
        });
        panelcat.add(categorylabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, 150, 40));

        jPanel2.add(panelcat, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 180, 350, 60));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Tanglaw Assets/aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa 2.png"))); // NOI18N
        jLabel6.setText("jLabel2");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 350, -1, -1));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Tanglaw Assets/Zasa 2.png"))); // NOI18N
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 0, -1, -1));

        jPanel4.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 350, 710));

        jPanel5.setBackground(new java.awt.Color(143, 88, 85));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel9.setLayout(new java.awt.CardLayout());
        jPanel5.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 410, -1, -1));

        jLabel35.setFont(new java.awt.Font("Verdana", 1, 30)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(255, 255, 255));
        jLabel35.setText("Transaction History");
        jPanel5.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 350, -1));

        tabletransaction.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Transaction ID", "Order ID", "Customer ID", "Date", "Product ID", "Product", "Price", "Quantity", "Total"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabletransaction.setColorBackgoundHead(java.awt.Color.gray);
        tabletransaction.setColorFilasBackgound2(new java.awt.Color(255, 255, 255));
        tabletransaction.setColorFilasForeground1(new java.awt.Color(0, 0, 0));
        tabletransaction.setColorFilasForeground2(new java.awt.Color(0, 0, 0));
        tabletransaction.setColorForegroundHead(java.awt.Color.black);
        tabletransaction.setColorSelBackgound(new java.awt.Color(204, 0, 0));
        tabletransaction.setIntercellSpacing(new java.awt.Dimension(0, 0));
        tabletransaction.setRowHeight(30);
        jScrollPane1.setViewportView(tabletransaction);

        jPanel5.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 70, 840, 550));

        jPanel4.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 90, 920, 640));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Tanglaw Assets/aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa 2.png"))); // NOI18N
        jLabel1.setText("jLabel1");
        jPanel4.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 430, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        setSize(new java.awt.Dimension(1300, 788));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel21MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel21MouseClicked
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_jLabel21MouseClicked

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        // TODO add your handling code here:
        // TODO add your handling code here:
        HomePage home = new HomePage();
        home.setVisible(true);
        dispose();
    }//GEN-LAST:event_jLabel2MouseClicked

    private void jLabel18MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel18MouseClicked
        // TODO add your handling code here:
        HomePage homepage = new HomePage();
        homepage.setVisible(true);
        dispose();
    }//GEN-LAST:event_jLabel18MouseClicked

    private void jLabel18MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel18MouseEntered
        // TODO add your handling code here:
        jLabel18.setForeground(new Color(143,83,85));
    }//GEN-LAST:event_jLabel18MouseEntered

    private void jLabel18MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel18MouseExited
        // TODO add your handling code here:
        jLabel18.setForeground(new Color(0,0,0));
    }//GEN-LAST:event_jLabel18MouseExited

    private void staffMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_staffMouseClicked
        // TODO add your handling code here:
        CustomerPageNew customer = new CustomerPageNew();
        customer.setVisible(true);
        dispose();
    }//GEN-LAST:event_staffMouseClicked

    private void staffMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_staffMouseEntered
        // TODO add your handling code here:
        staff.setForeground(new Color(143,83,85));
    }//GEN-LAST:event_staffMouseEntered

    private void staffMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_staffMouseExited
        // TODO add your handling code here:
        staff.setForeground(new Color(0,0,0));
    }//GEN-LAST:event_staffMouseExited

    private void panelcat4MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelcat4MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_panelcat4MouseEntered

    private void panelcat4MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelcat4MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_panelcat4MouseExited

    private void transactionsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_transactionsMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_transactionsMouseClicked

    private void transactionsMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_transactionsMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_transactionsMouseEntered

    private void transactionsMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_transactionsMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_transactionsMouseExited

    private void panelcat3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelcat3MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_panelcat3MouseEntered

    private void panelcat3MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelcat3MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_panelcat3MouseExited

    private void ordersMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ordersMouseClicked
        // TODO add your handling code here:
        OrderPage order = new OrderPage();
        order.setVisible(true);
        dispose();
    }//GEN-LAST:event_ordersMouseClicked

    private void ordersMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ordersMouseEntered
        // TODO add your handling code here:
        orders.setForeground(new Color(143,88,85));
    }//GEN-LAST:event_ordersMouseEntered

    private void ordersMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ordersMouseExited
        // TODO add your handling code here:
        orders.setForeground(new Color(0,0,0));
    }//GEN-LAST:event_ordersMouseExited

    private void panelcat2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelcat2MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_panelcat2MouseEntered

    private void panelcat2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelcat2MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_panelcat2MouseExited

    private void productsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_productsMouseClicked
        // TODO add your handling code here:
        ProductPageNew product = new ProductPageNew();
        product.setVisible(true);
        dispose();
    }//GEN-LAST:event_productsMouseClicked

    private void productsMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_productsMouseEntered
        // TODO add your handling code here:
        products.setForeground(new Color(143,88,85));
    }//GEN-LAST:event_productsMouseEntered

    private void productsMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_productsMouseExited
        // TODO add your handling code here:
        products.setForeground(new Color(0,0,0));
    }//GEN-LAST:event_productsMouseExited

    private void panelcat1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelcat1MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_panelcat1MouseEntered

    private void panelcat1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelcat1MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_panelcat1MouseExited

    private void categorylabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_categorylabel1MouseClicked
        // TODO add your handling code here:
        CategoryPageNew catpg = new CategoryPageNew();
        catpg.setVisible(true);
        dispose();
    }//GEN-LAST:event_categorylabel1MouseClicked

    private void categorylabel1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_categorylabel1MouseEntered
        // TODO add your handling code here:
        categorylabel1.setForeground(new Color(143,83,85));
    }//GEN-LAST:event_categorylabel1MouseEntered

    private void categorylabel1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_categorylabel1MouseExited
        // TODO add your handling code here:
        categorylabel1.setForeground(new Color(0,0,0));
    }//GEN-LAST:event_categorylabel1MouseExited

    private void panelcatMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelcatMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_panelcatMouseEntered

    private void panelcatMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelcatMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_panelcatMouseExited

    private void jPanel7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel7MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jPanel7MouseClicked

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
            java.util.logging.Logger.getLogger(TransactionPageNew.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TransactionPageNew.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TransactionPageNew.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TransactionPageNew.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TransactionPageNew().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel categorylabel1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel orders;
    private javax.swing.JPanel panelcat;
    private javax.swing.JPanel panelcat1;
    private javax.swing.JPanel panelcat2;
    private javax.swing.JPanel panelcat3;
    private javax.swing.JPanel panelcat4;
    private javax.swing.JLabel products;
    private javax.swing.JLabel staff;
    private rojeru_san.complementos.RSTableMetro tabletransaction;
    private javax.swing.JLabel transactions;
    // End of variables declaration//GEN-END:variables
}
