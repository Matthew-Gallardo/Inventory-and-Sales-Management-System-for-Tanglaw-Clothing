/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package jframes;

import com.mysql.cj.xdevapi.Statement;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.print.PrinterException;
import static java.lang.String.format;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import static jframes.DBConnection.con;

/**
 *
 * @author Administrator
 */
public class OrderPageNew extends javax.swing.JFrame {

    /**
     * Creates new form CategoryPage
     */
    public OrderPageNew() {
        initComponents();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime now =LocalDateTime.now();
        String date = formatter.format(now);
        txtdatetoday.setText(date);
       
      
    }
    Connection con;
    PreparedStatement insert;
    ResultSet rs;
    DefaultTableModel model = new DefaultTableModel();
    
  
   
    private void pos(){
      String  productid =txtproductid.getText();
     
        try {
            con = DBConnection.getConnection();
            
            insert=con.prepareStatement("select * from product_details where idproduct=?");
            insert.setString(1, productid);
            rs=insert.executeQuery();
    
            while(rs.next()){
                //qty=current quantity . quantity=new quantity
                int qty;
                qty=rs.getInt("quantity");
               // JOptionPane.showMessageDialog(this, quantity);
               int price = Integer.parseInt(txtprice.getText());
               int quantity = Integer.parseInt(txtquantity.getText());
               
               int total = price *quantity;
               if(quantity>=qty)
               {
                   JOptionPane.showMessageDialog(this, "Available product"+"="+qty +"pcs");
                   JOptionPane.showMessageDialog(this, "Not enough Quantity");
               }else{
                   model= (DefaultTableModel)tableorder.getModel();
                   model.addRow(new Object[]
                   {
                       txtcustomerid.getText(),
                       txtproductid.getText(),
                       txtproductname.getText(),
                       txtprice.getText(),
                       txtquantity.getText(),
                       total
                   } );    
                   
                         int sum=0;
                         for(int i=0;i<tableorder.getRowCount();i++){
                             sum=sum +Integer.parseInt(tableorder.getValueAt(i, 5).toString());
                         }
                         txtsubtotal.setText(Integer.toString(sum));
                   
                      
                      
                        txtproductid.setText("");
                        txtproductname.setText("");
                        txtprice.setText("");
                        txtquantity.setText("");
                        
                        
                        txtproductid.requestFocus();
               }
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderPage.class.getName()).log(Level.SEVERE, null, ex);
        }
}
      private void order(){
       
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime now =LocalDateTime.now();
        String date = formatter.format(now);
        
        int customerid = Integer.parseInt(txtcustomerid.getText());
 
        String customer = txtcustomername.getText();
        String subtotal= txtsubtotal.getText();
        String pay = txtpay.getText();
        String balance = txtbalance.getText();
        String mop = txtmop.getSelectedItem().toString();
        int lastinsertid=0;
     

        
        try {
            con = DBConnection.getConnection();
            String sql = ("Insert into order_details(idcustomer,customer,subtotal,pay,balance,mop,date)values(?,?,?,?,?,?,?)");
            insert=con.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
                    
            insert.setInt(1, customerid);
            insert.setString(2, customer);
            insert.setString(3, subtotal);
            insert.setString(4, pay);
            insert.setString(5, balance);
            insert.setString(6, mop);
            insert.setString(7, date);
          
            insert.executeUpdate();
            ResultSet generatedKeyResult =insert.getGeneratedKeys();
          
            if(generatedKeyResult.next()){
                lastinsertid = generatedKeyResult.getInt(1);
            }
            JOptionPane.showMessageDialog(this,"Order ID"+":" +lastinsertid);
            
            txtordernum.setText(String.valueOf(lastinsertid));
            
            
            
            int rows = tableorder.getRowCount();
            
            String query = ("Insert into orderedproducts(idorder,idproduct,product,price,quantity,total)values(?,?,?,?,?,?)");
            insert=con.prepareStatement(query);
            
            String idproduct="";
            String product="";
            String price="";
            String quantity="";
            int total=0;
                    
            for(int i=0;i<tableorder.getRowCount();i++){
               idproduct=(String)tableorder.getValueAt(i, 1);
               product=(String)tableorder.getValueAt(i, 2);
               price=(String)tableorder.getValueAt(i, 3);
               quantity=(String)tableorder.getValueAt(i, 4);
               total=(int)tableorder.getValueAt(i, 5);
               
              insert.setInt(1, lastinsertid);
              insert.setString(2, idproduct);
              insert.setString(3, product); 
              insert.setString(4, price);
              insert.setString(5, quantity);
              insert.setInt(6, total); 
              insert.executeUpdate();
            }
            //update stocks/quantity
            String query1 = ("update product_details set quantity=quantity-? where idproduct=?");
            insert=con.prepareStatement(query1);
            
             for(int i=0;i<tableorder.getRowCount();i++){
               idproduct=(String)tableorder.getValueAt(i, 1);
               quantity=(String)tableorder.getValueAt(i, 4);

              insert.setString(1, quantity);
              insert.setString(2, idproduct);
            
              insert.execute();
            }

            insert.addBatch();
            JOptionPane.showMessageDialog(this, "Ordered Product Saved");
            
        } catch (SQLException ex) {
            Logger.getLogger(OrderPage.class.getName()).log(Level.SEVERE, null, ex);
        }       
    } 
      public void print(){
          String customerid=txtcustomerid.getText();
          String sub=txtbalance.getText();
          String pay=txtpay.getText();
          String balance=txtbalance.getText();
          String mop = txtmop.getSelectedItem().toString();
          String ordernum=txtordernum.getText();
          
        try {
            new print(ordernum,customerid,sub,pay,balance,mop,tableorder.getModel()).setVisible(true);
        } catch (PrinterException ex) {
            Logger.getLogger(OrderPageNew.class.getName()).log(Level.SEVERE, null, ex);
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
        jLabel22 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        txtmop = new javax.swing.JComboBox<>();
        jLabel23 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        txtordernum = new javax.swing.JLabel();
        txtorderTextField = new javax.swing.JTextField();
        txtpay = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        txtsubtotal = new javax.swing.JTextField();
        txtdatetoday = new javax.swing.JLabel();
        txtdate = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        txtbalance = new javax.swing.JTextField();
        jLabel32 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        cusdisplay = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtcustomername = new javax.swing.JTextField();
        txtemail = new javax.swing.JTextField();
        txtcustomerid = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        productdisplay = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        txtproductname = new javax.swing.JTextField();
        txtquantity = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jButtonDelete = new javax.swing.JButton();
        jButtonAdd = new javax.swing.JButton();
        txtprice = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        txtproductid = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableorder = new javax.swing.JTable();
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
        jLabel26 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel4.setBackground(new java.awt.Color(211, 211, 211));
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

        jLabel22.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(204, 204, 204));
        jLabel22.setText("Polytechnic University of the Philippines ");
        jPanel8.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 30, 530, -1));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Tanglaw Assets/image 1.png"))); // NOI18N
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });
        jPanel8.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        jPanel4.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1330, 80));

        jPanel5.setBackground(new java.awt.Color(143, 88, 85));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel9.setLayout(new java.awt.CardLayout());
        jPanel5.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 410, -1, -1));

        jPanel10.setBackground(new java.awt.Color(128, 0, 0));
        jPanel10.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel19.setFont(new java.awt.Font("Verdana", 1, 35)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("Manage");
        jPanel10.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 160, 60));

        txtmop.setBackground(new java.awt.Color(233, 239, 192));
        txtmop.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtmop.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Cash on Delivery", "Gcash", "Paypal", "Bank Transfer" }));
        jPanel10.add(txtmop, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, 280, 40));

        jLabel23.setFont(new java.awt.Font("Verdana", 1, 35)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(255, 255, 255));
        jLabel23.setText("Point of Sales");
        jPanel10.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 280, 60));

        jLabel27.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(255, 255, 255));
        jLabel27.setText("Pay");
        jPanel10.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 250, 30, 30));

        jLabel24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Tanglaw Assets/Line 1.png"))); // NOI18N
        jPanel10.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 230, 300, 20));

        txtordernum.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jPanel10.add(txtordernum, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 410, 250, 30));

        txtorderTextField.setEditable(false);
        txtorderTextField.setBackground(new java.awt.Color(255, 255, 255));
        txtorderTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtorderTextFieldActionPerformed(evt);
            }
        });
        jPanel10.add(txtorderTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 410, 270, 30));

        txtpay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtpayActionPerformed(evt);
            }
        });
        jPanel10.add(txtpay, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 280, 120, 30));
        txtpay.getAccessibleContext().setAccessibleName("");

        jLabel28.setFont(new java.awt.Font("Verdana", 1, 15)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(255, 255, 255));
        jLabel28.setText("Mode of Payment");
        jPanel10.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, 170, 30));

        jLabel29.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(255, 255, 255));
        jLabel29.setText("Balance");
        jPanel10.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 310, 60, 30));

        txtsubtotal.setEditable(false);
        txtsubtotal.setBackground(new java.awt.Color(255, 255, 255));
        txtsubtotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtsubtotalActionPerformed(evt);
            }
        });
        jPanel10.add(txtsubtotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 280, 120, 30));

        txtdatetoday.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jPanel10.add(txtdatetoday, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 340, 100, 30));

        txtdate.setEditable(false);
        txtdate.setBackground(new java.awt.Color(255, 255, 255));
        txtdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtdateActionPerformed(evt);
            }
        });
        jPanel10.add(txtdate, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 340, 120, 30));

        jLabel30.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(255, 255, 255));
        jLabel30.setText("Subtotal");
        jPanel10.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 250, 70, 30));

        jLabel31.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(255, 255, 255));
        jLabel31.setText("Order Number");
        jPanel10.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 380, 100, 30));

        txtbalance.setEditable(false);
        txtbalance.setBackground(new java.awt.Color(255, 255, 255));
        txtbalance.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtbalanceActionPerformed(evt);
            }
        });
        jPanel10.add(txtbalance, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 340, 120, 30));

        jLabel32.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(255, 255, 255));
        jLabel32.setText("Date");
        jPanel10.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 310, 40, 30));

        jButton4.setBackground(new java.awt.Color(255, 255, 255));
        jButton4.setLabel("Print Invoice");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel10.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 450, 110, 30));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Tanglaw Assets/shop.png"))); // NOI18N
        jPanel10.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 480, -1, -1));

        jPanel5.add(jPanel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 0, 370, 640));

        jLabel15.setFont(new java.awt.Font("Verdana", 1, 35)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Point of Sales");
        jPanel5.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 280, 60));

        jPanel3.setBackground(new java.awt.Color(194, 194, 194));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cusdisplay.setBackground(new java.awt.Color(255, 255, 255));
        cusdisplay.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel3.add(cusdisplay, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 120, 100));

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel9.setText("Email Address:");
        jPanel3.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 60, -1, -1));

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel10.setText("Staff Name:");
        jPanel3.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 10, -1, -1));

        txtcustomername.setEditable(false);
        txtcustomername.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtcustomernameActionPerformed(evt);
            }
        });
        jPanel3.add(txtcustomername, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 30, 180, 30));

        txtemail.setEditable(false);
        jPanel3.add(txtemail, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 80, 180, 30));

        txtcustomerid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtcustomeridActionPerformed(evt);
            }
        });
        txtcustomerid.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtcustomeridKeyPressed(evt);
            }
        });
        jPanel3.add(txtcustomerid, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 80, 120, 30));

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel14.setText("Staff ID:");
        jPanel3.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 60, -1, -1));

        jLabel20.setFont(new java.awt.Font("Tahoma", 0, 22)); // NOI18N
        jLabel20.setText("Staff Details");
        jPanel3.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 20, -1, -1));

        jPanel5.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 530, 120));

        jLabel8.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel8.setForeground(java.awt.Color.lightGray);
        jLabel8.setText("Welcome back! Your progress is really good. Keep it up!");
        jPanel5.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 370, 20));

        jPanel1.setBackground(new java.awt.Color(194, 194, 194));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        productdisplay.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.add(productdisplay, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 120, 100));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Product ID:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 40, -1, -1));

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel16.setText("Product Name:");
        jPanel1.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 10, -1, 20));

        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel17.setText("Quantity:");
        jPanel1.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 70, -1, -1));

        txtproductname.setEditable(false);
        txtproductname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtproductnameActionPerformed(evt);
            }
        });
        jPanel1.add(txtproductname, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 30, 180, 30));

        txtquantity.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtquantityActionPerformed(evt);
            }
        });
        txtquantity.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtquantityKeyPressed(evt);
            }
        });
        jPanel1.add(txtquantity, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 90, 70, 30));

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 22)); // NOI18N
        jLabel12.setText("Product Details");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 10, -1, -1));

        jButtonDelete.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jButtonDelete.setText("Delete");
        jButtonDelete.setToolTipText("");
        jButtonDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDeleteActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonDelete, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 100, 70, -1));

        jButtonAdd.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jButtonAdd.setText("Add");
        jButtonAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonAdd, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 100, 60, -1));

        txtprice.setEditable(false);
        jPanel1.add(txtprice, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 90, 100, 30));

        jLabel25.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel25.setText("Price:");
        jPanel1.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 70, -1, -1));

        txtproductid.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtproductidKeyPressed(evt);
            }
        });
        jPanel1.add(txtproductid, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 60, 150, 30));
        txtproductid.getAccessibleContext().setAccessibleName("");

        jPanel5.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 210, 530, 130));

        tableorder.setBackground(new java.awt.Color(233, 239, 192));
        tableorder.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(153, 204, 0)));
        tableorder.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Customer ID", "Product ID", "Product Name", "Price", "Quantity", "Total"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableorder.setGridColor(new java.awt.Color(102, 204, 0));
        tableorder.setSelectionBackground(new java.awt.Color(0, 255, 0));
        tableorder.setSelectionForeground(new java.awt.Color(0, 0, 0));
        tableorder.setShowGrid(false);
        tableorder.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableorderMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tableorder);

        jPanel5.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 350, 560, 280));

        jPanel4.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 80, 910, 640));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel18.setBackground(new java.awt.Color(0, 0, 0));
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

        panelcat3.setBackground(new java.awt.Color(255, 255, 255));
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

        panelcat2.setBackground(new java.awt.Color(143, 83, 85));
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
        orders.setForeground(java.awt.Color.gray);
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

        jLabel26.setFont(new java.awt.Font("Verdana", 1, 16)); // NOI18N
        jLabel26.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Tanglaw Assets/logout.png"))); // NOI18N
        jLabel26.setText("Log out");
        jLabel26.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel26MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel26MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel26MouseExited(evt);
            }
        });
        jPanel2.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 620, 140, 40));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Tanglaw Assets/aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa 2.png"))); // NOI18N
        jLabel7.setText("jLabel2");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 350, -1, -1));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Tanglaw Assets/Zasa 2.png"))); // NOI18N
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 0, -1, -1));

        jPanel4.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 350, 710));

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

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        // TODO add your handling code here:
        // TODO add your handling code here:
        HomePage home = new HomePage();
        home.setVisible(true);
        dispose();
    }//GEN-LAST:event_jLabel2MouseClicked

    private void jLabel21MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel21MouseClicked
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_jLabel21MouseClicked

    private void jLabel18MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel18MouseClicked
        // TODO add your handling code here:
        HomePage homepage = new HomePage();
        homepage.setVisible(true);
        dispose();
    }//GEN-LAST:event_jLabel18MouseClicked

    private void jLabel18MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel18MouseEntered
        // TODO add your handling code here:
        jLabel18.setForeground(new Color(143,88,85));
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
        TransactionPageNew transaction = new TransactionPageNew();
        transaction.setVisible(true);
        dispose();
    }//GEN-LAST:event_transactionsMouseClicked

    private void transactionsMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_transactionsMouseEntered
        // TODO add your handling code here:
        transactions.setForeground(new Color(143,88,85));
    }//GEN-LAST:event_transactionsMouseEntered

    private void transactionsMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_transactionsMouseExited
        // TODO add your handling code here:
        transactions.setForeground(new Color(0,0,0));
    }//GEN-LAST:event_transactionsMouseExited

    private void panelcat3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelcat3MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_panelcat3MouseEntered

    private void panelcat3MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelcat3MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_panelcat3MouseExited

    private void ordersMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ordersMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_ordersMouseClicked

    private void ordersMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ordersMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_ordersMouseEntered

    private void ordersMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ordersMouseExited
        // TODO add your handling code here:
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

    private void txtcustomeridActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtcustomeridActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtcustomeridActionPerformed

    private void txtcustomeridKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcustomeridKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode()== KeyEvent.VK_ENTER){
            String  customerid =txtcustomerid.getText();
            try {
                con = DBConnection.getConnection();
                insert=con.prepareStatement("select * from customer_details where idcustomer=?");
                insert.setString(1, customerid);
                rs= insert.executeQuery();
                if (rs.next()==false){
                    JOptionPane.showMessageDialog(this, "Customer ID not found");
                } else{
                    String customer =rs.getString("customer");
                    String email =rs.getString("email");
                    String address =rs.getString("shipping_address");

                    byte[] imagedata =rs.getBytes("customer_img");
                    ImageIcon format = new ImageIcon(imagedata);
                    Image mm = format.getImage();
                    Image img2 = mm.getScaledInstance(cusdisplay.getWidth(),cusdisplay.getHeight(),Image.SCALE_SMOOTH);
                    ImageIcon image = new ImageIcon(img2);
                    cusdisplay.setIcon(image);

                    txtcustomername.setText(customer.trim());
                    txtemail.setText(email.trim());

                }
            } catch (SQLException ex) {
                Logger.getLogger(OrderPage.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }//GEN-LAST:event_txtcustomeridKeyPressed

    private void txtcustomernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtcustomernameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtcustomernameActionPerformed

    private void txtquantityKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtquantityKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode()== KeyEvent.VK_ENTER){
            String  productid =txtquantity.getText();
            try {
                con = DBConnection.getConnection();
                insert=con.prepareStatement("select * from product_details where idproduct=?");
                insert.setString(1, productid);
                rs= insert.executeQuery();
                if (rs.next()==false){
                    JOptionPane.showMessageDialog(this, "Product ID not found");
                } else{
                    String product =rs.getString("product");
                    String price =rs.getString("price");

                    /*byte[] imagedata =rs.getBytes("imageFile");
                    ImageIcon format1 = new ImageIcon(imagedata);
                    Image mm1 = format1.getImage();
                    Image img3 = mm1.getScaledInstance(productdisplay.getWidth(),productdisplay.getHeight(),Image.SCALE_SMOOTH);
                    ImageIcon image1 = new ImageIcon(img3);
                    productdisplay.setIcon(image1);*/

                    txtproductname.setText(product.trim());
                    txtquantity.setText(price.trim());

                }
            } catch (SQLException ex) {
                Logger.getLogger(OrderPage.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }//GEN-LAST:event_txtquantityKeyPressed

    private void txtproductnameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtproductnameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtproductnameActionPerformed

    private void tableorderMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableorderMouseClicked
        // TODO add your handling code here:
        int rowNo = tableorder.getSelectedRow();
        TableModel model = tableorder.getModel();

        txtproductid.setText(model.getValueAt(rowNo, 1).toString());
    }//GEN-LAST:event_tableorderMouseClicked

    private void txtquantityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtquantityActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtquantityActionPerformed

    private void txtpayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtpayActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtpayActionPerformed

    private void txtsubtotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtsubtotalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtsubtotalActionPerformed

    private void txtdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtdateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtdateActionPerformed

    private void txtbalanceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtbalanceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtbalanceActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        int pay = Integer.parseInt(txtpay.getText());
        int subtotal = Integer.parseInt(txtsubtotal.getText());

        int balance = pay-subtotal;
        txtbalance.setText(String.valueOf(balance));

        order();
        print();

    }//GEN-LAST:event_jButton4ActionPerformed

    private void txtproductidKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtproductidKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode()== KeyEvent.VK_ENTER){
            String  productid =txtproductid.getText();
            try {
                con = DBConnection.getConnection();
                insert=con.prepareStatement("select * from product_details where idproduct=?");
                insert.setString(1, productid);
                rs= insert.executeQuery();
                if (rs.next()==false){
                    JOptionPane.showMessageDialog(this, "Product ID not found");
                } else{
                    String product =rs.getString("product");
                    String price =rs.getString("price");

                    /*byte[] imagedata =rs.getBytes("imageFile");
                    ImageIcon format1 = new ImageIcon(imagedata);
                    Image mm1 = format1.getImage();
                    Image img3 = mm1.getScaledInstance(productdisplay.getWidth(),productdisplay.getHeight(),Image.SCALE_SMOOTH);
                    ImageIcon image1 = new ImageIcon(img3);
                    productdisplay.setIcon(image1);*/

                    txtproductname.setText(product.trim());
                    //txtbrand.setText(brand.trim());
                    txtprice.setText(price.trim());

                }
            } catch (SQLException ex) {
                Logger.getLogger(OrderPage.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }//GEN-LAST:event_txtproductidKeyPressed

    private void jButtonDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDeleteActionPerformed
        // TODO add your handling code here:
        if(tableorder.getSelectedRow() != -1) {
        model.removeRow(tableorder.getSelectedRow());
         }
       int sum=0;
          for(int i=0;i<tableorder.getRowCount();i++){
                             sum=sum + Integer.parseInt(tableorder.getValueAt(0, 6).toString());
                         }
                         txtsubtotal.setText(Integer.toString(sum));
        
        txtproductid.setText("");
                   
    }//GEN-LAST:event_jButtonDeleteActionPerformed

    private void jButtonAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddActionPerformed
        // TODO add your handling code here:
        pos();
    }//GEN-LAST:event_jButtonAddActionPerformed

    private void txtorderTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtorderTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtorderTextFieldActionPerformed

    private void jLabel26MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel26MouseClicked
        // TODO add your handling code here:
        LoginPage login = new LoginPage();
        login.setVisible(true);
        dispose();
    }//GEN-LAST:event_jLabel26MouseClicked

    private void jLabel26MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel26MouseEntered
        // TODO add your handling code here:
        jLabel26.setBackground(new Color(255,255,255));
    }//GEN-LAST:event_jLabel26MouseEntered

    private void jLabel26MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel26MouseExited
        // TODO add your handling code here:
       jLabel26.setBackground(new Color(143,88,85));
    }//GEN-LAST:event_jLabel26MouseExited

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
            java.util.logging.Logger.getLogger(OrderPageNew.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(OrderPageNew.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(OrderPageNew.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(OrderPageNew.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new OrderPageNew().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel categorylabel1;
    private javax.swing.JLabel cusdisplay;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButtonAdd;
    private javax.swing.JButton jButtonDelete;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel orders;
    private javax.swing.JPanel panelcat;
    private javax.swing.JPanel panelcat1;
    private javax.swing.JPanel panelcat2;
    private javax.swing.JPanel panelcat3;
    private javax.swing.JPanel panelcat4;
    private javax.swing.JLabel productdisplay;
    private javax.swing.JLabel products;
    private javax.swing.JLabel staff;
    private javax.swing.JTable tableorder;
    private javax.swing.JLabel transactions;
    private javax.swing.JTextField txtbalance;
    private javax.swing.JTextField txtcustomerid;
    private javax.swing.JTextField txtcustomername;
    private javax.swing.JTextField txtdate;
    private javax.swing.JLabel txtdatetoday;
    private javax.swing.JTextField txtemail;
    private javax.swing.JComboBox<String> txtmop;
    private javax.swing.JTextField txtorderTextField;
    private javax.swing.JLabel txtordernum;
    private javax.swing.JTextField txtpay;
    private javax.swing.JTextField txtprice;
    private javax.swing.JTextField txtproductid;
    private javax.swing.JTextField txtproductname;
    private javax.swing.JTextField txtquantity;
    private javax.swing.JTextField txtsubtotal;
    // End of variables declaration//GEN-END:variables
}
