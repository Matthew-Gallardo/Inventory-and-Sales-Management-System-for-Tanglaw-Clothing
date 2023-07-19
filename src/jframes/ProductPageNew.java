/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

package jframes;
import java.awt.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import static jframes.DBConnection.con;
/**
 *
 * @author Administrator
 */
public class ProductPageNew extends javax.swing.JFrame {

    /**
     * Creates new form CategoryPage
     */
    public ProductPageNew() {
        initComponents();
        setProductDetailsToTable();
        category();
        categorySelect();
        
    }
    PreparedStatement pst;
    ResultSet rs;
    
    String brand,status;
    int idbrand;
    DefaultTableModel model;
    
    
    
    public void setProductDetailsToTable(){
        
        try {
            con = DBConnection.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from product_details");
            
            while(rs.next()){
                String idproduct = rs.getString("idproduct");
                String product = rs.getString("product");
                String category = rs.getString("category");
                String idcategory = rs.getString("idcategory");
                String price= rs.getString("price");
                String quantity = rs.getString("quantity");
                String status = rs.getString("status");
                
               
                
                
                Object[] obj = {idproduct,product,category,idcategory,price,quantity,status};
                model =(DefaultTableModel) tableproduct.getModel();
                model.addRow(obj);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
     public class CategoryItem{
         int id;
         String name;
         public CategoryItem(int id, String name){
             this.id=id;
             this.name=name;
     
         }
         public String toString(){
             return name;
         }
     }
     
     
     public class BrandItem{
         int id;
         String name;
         public BrandItem(int id, String name){
             this.id=id;
             this.name=name;
     
         }
         public String toString(){
             return name;
         }
     }
     
     
     
     
     private void category()
     {
        try {
            con = DBConnection.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from category_details");
            txtcat.removeAllItems();
            
            while(rs.next()){
            txtcat.addItem(new CategoryItem (rs.getInt(1),rs.getString(2)));
            
            
            }
                    
        }
        catch (SQLException ex) {
            Logger.getLogger(ProductPageNew.class.getName()).log(Level.SEVERE, null, ex);
        }
      
     }
        
     
      private void categorySelect()
     {
        try {
            con = DBConnection.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from category_details ");
            selectcat.removeAllItems();
            
            while(rs.next()){
               String dd=rs.getString("category");
               selectcat.addItem(dd);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductPageNew.class.getName()).log(Level.SEVERE, null, ex);
        }
      
     }   
   
    //method to clear table
    public void clearTable(){
        DefaultTableModel model = (DefaultTableModel) tableproduct.getModel();
        model.setRowCount(0);
    }
     public void setProductCatDetailsToTable(){
        
        try {
            con = DBConnection.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select category  from product_details");
            
            while(rs.next()){
                String idproduct = rs.getString("idproduct");
                String product = rs.getString("product");
                String category = rs.getString("category");
                String idcategory = rs.getString("idcategory");
                String price= rs.getString("price");
                String quantity = rs.getString("quantity");
                String status = rs.getString("status");
                
               
                
                
                Object[] obj = {idproduct,product,category,idcategory,brand,idbrand,price,quantity,status};
                model =(DefaultTableModel) tableproduct.getModel();
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

        jPanel10 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        panelout = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
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
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableproduct = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        txtcat = new javax.swing.JComboBox();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jLabel29 = new javax.swing.JLabel();
        txtprice = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        txtquantity = new javax.swing.JTextField();
        txtproductstatus = new javax.swing.JComboBox<>();
        jLabel32 = new javax.swing.JLabel();
        txtproduct = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        selectcat = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        txt1 = new javax.swing.JTextField();
        txt2 = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        btnrange = new javax.swing.JButton();
        refresh = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel3.setBackground(new java.awt.Color(143, 88, 85));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
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

        panelout.setBackground(new java.awt.Color(143, 88, 85));
        panelout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                paneloutMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                paneloutMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                paneloutMouseExited(evt);
            }
        });
        panelout.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel20.setFont(new java.awt.Font("Verdana", 1, 16)); // NOI18N
        jLabel20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Tanglaw Assets/logout.png"))); // NOI18N
        jLabel20.setText("Log out");
        jLabel20.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel20MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel20MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel20MouseExited(evt);
            }
        });
        panelout.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 140, 40));

        jPanel2.add(panelout, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 590, 140, 50));

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

        panelcat1.setBackground(new java.awt.Color(143, 83, 85));
        panelcat1.setForeground(java.awt.Color.gray);
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
        products.setForeground(java.awt.Color.gray);
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

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Tanglaw Assets/Zasa 2.png"))); // NOI18N
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 0, -1, -1));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Tanglaw Assets/aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa 2.png"))); // NOI18N
        jLabel7.setText("jLabel2");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 350, -1, -1));

        jPanel3.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 350, 710));

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

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Tanglaw Assets/image 1.png"))); // NOI18N
        jPanel8.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, -1, -1));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(204, 204, 204));
        jLabel4.setText("Polytechnic University of the Philippines ");
        jPanel8.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 30, 530, -1));

        jPanel3.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1330, 80));

        tableproduct.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(153, 204, 0)));
        tableproduct.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Product ID", "Product", "Category", "Category ID", "Price", "Quantity", "Status"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tableproduct.setGridColor(new java.awt.Color(102, 204, 0));
        tableproduct.setSelectionBackground(new java.awt.Color(0, 255, 0));
        tableproduct.setSelectionForeground(new java.awt.Color(0, 0, 0));
        tableproduct.setShowGrid(false);
        tableproduct.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableproductMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableproduct);
        if (tableproduct.getColumnModel().getColumnCount() > 0) {
            tableproduct.getColumnModel().getColumn(3).setHeaderValue("Category ID");
        }

        jPanel3.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 170, 520, 480));

        jPanel1.setBackground(new java.awt.Color(128, 0, 0));
        jPanel1.setPreferredSize(new java.awt.Dimension(1300, 300));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel5.setBackground(new java.awt.Color(128, 0, 0));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel5.add(txtcat, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 140, 200, 30));

        jButton4.setText("ADD");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 440, 80, 30));

        jButton5.setText("UPDATE");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 440, 90, 30));

        jButton6.setText("DELETE");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 440, 80, 30));

        jLabel29.setFont(new java.awt.Font("Verdana", 1, 15)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(255, 255, 255));
        jLabel29.setText("Price");
        jPanel5.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, 140, 30));

        txtprice.setBackground(new java.awt.Color(229, 230, 235));
        txtprice.setBorder(null);
        txtprice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtpriceActionPerformed(evt);
            }
        });
        jPanel5.add(txtprice, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 230, 60, 20));

        jLabel31.setFont(new java.awt.Font("Verdana", 1, 15)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(255, 255, 255));
        jLabel31.setText("Quantity");
        jPanel5.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 260, 140, 30));

        txtquantity.setBackground(new java.awt.Color(229, 230, 235));
        txtquantity.setBorder(null);
        txtquantity.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtquantityActionPerformed(evt);
            }
        });
        jPanel5.add(txtquantity, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 300, 60, 20));

        txtproductstatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "inStock", "OutOfStock" }));
        jPanel5.add(txtproductstatus, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 370, 100, 30));

        jLabel32.setFont(new java.awt.Font("Verdana", 1, 15)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(255, 255, 255));
        jLabel32.setText("Status");
        jPanel5.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 330, 140, 30));

        txtproduct.setBackground(new java.awt.Color(229, 230, 235));
        txtproduct.setBorder(null);
        txtproduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtproductActionPerformed(evt);
            }
        });
        jPanel5.add(txtproduct, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 60, 260, 20));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8-ok-30.png"))); // NOI18N
        jPanel5.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 370, 40, 30));

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DB ICONS/icons8-shopping-cart-30.png"))); // NOI18N
        jPanel5.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 40, 30));

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DB ICONS/icons8-peso-symbol-30.png"))); // NOI18N
        jPanel5.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 220, 40, 30));

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DB ICONS/icons8-how-many-quest-30.png"))); // NOI18N
        jPanel5.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 290, 40, 30));

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/adminIcons/icons8-opened-folder-30.png"))); // NOI18N
        jPanel5.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, 40, 30));

        jLabel16.setFont(new java.awt.Font("Verdana", 1, 25)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(233, 239, 192));
        jLabel16.setText("Manage Product");
        jPanel5.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, -100, 280, 120));

        jLabel28.setFont(new java.awt.Font("Verdana", 1, 15)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(255, 255, 255));
        jLabel28.setText("Product Name:");
        jPanel5.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 20, 140, 30));

        jLabel35.setFont(new java.awt.Font("Verdana", 1, 15)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(255, 255, 255));
        jLabel35.setText("Category:");
        jPanel5.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, 140, 30));

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Tanglaw Assets/short input.png"))); // NOI18N
        jPanel5.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 290, -1, -1));

        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Tanglaw Assets/Rectangle 29.png"))); // NOI18N
        jPanel5.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 50, -1, -1));

        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Tanglaw Assets/short input.png"))); // NOI18N
        jPanel5.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 220, -1, -1));

        jPanel1.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, 350, 480));

        jLabel27.setFont(new java.awt.Font("Verdana", 1, 35)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(255, 255, 255));
        jLabel27.setText("Product");
        jPanel1.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 240, 30));

        jLabel34.setFont(new java.awt.Font("Verdana", 1, 35)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(255, 255, 255));
        jLabel34.setText("Manage");
        jPanel1.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 240, -1));

        jPanel3.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 90, 390, 660));

        jLabel30.setFont(new java.awt.Font("Verdana", 1, 35)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(255, 255, 255));
        jLabel30.setText("Product Details");
        jPanel3.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 120, 310, 30));

        jPanel9.setBackground(new java.awt.Color(143, 88, 85));
        jPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        selectcat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectcatActionPerformed(evt);
            }
        });
        jPanel9.add(selectcat, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, 170, 30));

        jButton1.setText("SEARCH");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel9.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 40, -1, -1));
        jPanel9.add(txt1, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 20, 60, -1));
        jPanel9.add(txt2, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 20, 70, -1));

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel15.setText("-");
        jPanel9.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 20, 10, 20));

        jLabel33.setFont(new java.awt.Font("Verdana", 1, 15)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(255, 255, 255));
        jLabel33.setText("Search By:");
        jPanel9.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, 140, 30));

        btnrange.setText("RANGE");
        btnrange.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnrangeActionPerformed(evt);
            }
        });
        jPanel9.add(btnrange, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 60, -1, 23));

        refresh.setText("REFRESH");
        refresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshActionPerformed(evt);
            }
        });
        jPanel9.add(refresh, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 60, -1, 23));

        jPanel3.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 650, 530, 90));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 950, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        jPanel3.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 50, 950, 40));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Tanglaw Assets/aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa 2.png"))); // NOI18N
        jLabel2.setText("jLabel2");
        jPanel3.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 410, -1, -1));

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 70, Short.MAX_VALUE)
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 460, Short.MAX_VALUE)
        );

        jPanel3.add(jPanel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(1280, 50, 70, 460));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 765, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        setSize(new java.awt.Dimension(1300, 765));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel18MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel18MouseClicked
        // TODO add your handling code here:
        HomePage homepg = new HomePage();
        homepg.setVisible(true);
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

    private void jLabel20MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel20MouseClicked
        // TODO add your handling code here:

        LoginPage login = new LoginPage();
        login.setVisible(true);
        dispose();
    }//GEN-LAST:event_jLabel20MouseClicked

    private void jLabel20MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel20MouseEntered
        // TODO add your handling code here:
        panelout.setBackground(new Color(255,255,255));
    }//GEN-LAST:event_jLabel20MouseEntered

    private void jLabel20MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel20MouseExited
        // TODO add your handling code here:
        panelout.setBackground(new Color(143,88,85));
    }//GEN-LAST:event_jLabel20MouseExited

    private void paneloutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_paneloutMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_paneloutMouseClicked

    private void paneloutMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_paneloutMouseEntered
        // TODO add your handling code here:
        panelout.setBackground(new Color(143,88,85));
    }//GEN-LAST:event_paneloutMouseEntered

    private void paneloutMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_paneloutMouseExited
        // TODO add your handling code here:
        panelout.setBackground(new Color(255,255,255));
    }//GEN-LAST:event_paneloutMouseExited

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
    }//GEN-LAST:event_productsMouseClicked

    private void productsMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_productsMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_productsMouseEntered

    private void productsMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_productsMouseExited
        // TODO add your handling code here:
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

    private void jLabel21MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel21MouseClicked
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_jLabel21MouseClicked

    private void tableproductMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableproductMouseClicked
        // TODO add your handling code here:
        int rowNo = tableproduct.getSelectedRow();
        TableModel model = tableproduct.getModel();

        txtproduct.setText(model.getValueAt(rowNo, 1).toString());
        txtprice.setText(model.getValueAt(rowNo, 4).toString());
        txtquantity.setText(model.getValueAt(rowNo, 5).toString());
        
        String category = model.getValueAt(rowNo, 2).toString();
        switch(category){
            case "Tanglaw Casual":
                txtcat.setSelectedIndex(0);
                break;
            case "Tanglaw Klasika Minimalista Collection":
                txtcat.setSelectedIndex(1);
                break;
            case "Tanglaw Klasika Collection":
                txtcat.setSelectedIndex(2);
                break;
            case "Tanglaw Merch":
                txtcat.setSelectedIndex(3);
                break;
            case "Tanglaw Drifit Collection":
                txtcat.setSelectedIndex(4);
                break;
            case "Tanglaw Activewear":
                txtcat.setSelectedIndex(5);
                break;
            case "Tanglaw Hoodies":
                txtcat.setSelectedIndex(6);
                break;
            case "Tanglaw Special and Limited Collection":
                txtcat.setSelectedIndex(7);
                break;
            case "Tanglaw Minimalist Collection":
                txtcat.setSelectedIndex(8);
                break;
            case "Tanglaw Streetwear Collection":
                txtcat.setSelectedIndex(9);
                break;
            case "Tanglaw Classic Collection":
                txtcat.setSelectedIndex(10);
                break;
        }
        
        String status1 = model.getValueAt(rowNo, 6).toString();
        
        if(status1.equals("inStock")){
            txtproductstatus.setSelectedIndex(0);
        }
        else{
            txtproductstatus.setSelectedIndex(1);
        }
        
    }//GEN-LAST:event_tableproductMouseClicked

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        String product = txtproduct.getText();
        String category = txtcat.getSelectedItem().toString(); //categoryname
        CategoryItem catitem =(CategoryItem)txtcat.getSelectedItem();//categoryid
        String price = txtprice.getText();
        String quantity =txtquantity.getText();
        String status = txtproductstatus.getSelectedItem().toString();


        try {
            Connection con =DBConnection.getConnection();
            String sql = ("insert into product_details(product,category,idcategory,price,quantity,status) values(?,?,?,?,?,?)");
            PreparedStatement pst = con.prepareStatement(sql);

            pst.setString(1, product);
            pst.setString(2, category);
            pst.setInt(3, catitem.id);
            pst.setString(4, price);
            pst.setString(5, quantity);
            pst.setString(6, status);

            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Product Added");

            clearTable();
            // setProductDetailsToTable();

            txtproduct.setText("");
            txtcat.setSelectedIndex(-1);
            txtprice.setText("");
            txtquantity.setText("");
            txtproductstatus.setSelectedIndex(-1);
            new ProductPageNew().setVisible(true);
            this.dispose();

            txtproduct.requestFocus();

        } catch (SQLException ex) {
            Logger.getLogger(ProductPageNew.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        DefaultTableModel d1 = (DefaultTableModel)tableproduct.getModel();
        int selectIndex = tableproduct.getSelectedRow();
        int id = Integer.parseInt(d1.getValueAt(selectIndex, 0).toString());

        String product = txtproduct.getText();
        String category = txtcat.getSelectedItem().toString(); //categoryname
        CategoryItem catitem =(CategoryItem)txtcat.getSelectedItem();//categoryid
        String price = txtprice.getText();
        String quantity =txtquantity.getText();
        String status = txtproductstatus.getSelectedItem().toString();


        try {
            Connection con = DBConnection.getConnection();
            pst = con.prepareStatement("update product_details set product=?,category=?,idcategory=?,price=?,quantity=?,status=? where idproduct= ? ");
            pst.setString(1, product);
            pst.setString(2, category);
            pst.setInt(3, catitem.id);
            pst.setString(4, price);
            pst.setString(5, quantity);
            pst.setString(6, status);
            pst.setInt(7, id);

            pst.executeUpdate();
            JOptionPane.showMessageDialog(null,"Product Updated");
            clearTable();
            setProductDetailsToTable();
            txtproduct.setText("");
            txtcat.setSelectedIndex(-1);
            txtproduct.requestFocus();
            txtprice.setText("");
            txtquantity.setText("");
            txtproductstatus.setSelectedIndex(-1);
            jButton5.setEnabled(true);
            new ProductPageNew().setVisible(true);
            this.dispose();

            txtproduct.requestFocus();

        }
        catch (SQLException ex) {
            Logger.getLogger(ProductPageNew.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        DefaultTableModel d1 = (DefaultTableModel)tableproduct.getModel();
        int selectIndex = tableproduct.getSelectedRow();

        int id = Integer.parseInt(d1.getValueAt(selectIndex, 0).toString());

        int dialogResult = JOptionPane.showConfirmDialog(null, "Do you want to Delete the Record","Warning",JOptionPane.YES_NO_OPTION);

        if(dialogResult == JOptionPane.YES_OPTION)
        {

            try {
                pst = con.prepareStatement("delete from product_details where idproduct =?");
                pst.setInt(1, id);
                pst.executeUpdate();
                JOptionPane.showMessageDialog(null,"Product Deleted");
                clearTable();
                setProductDetailsToTable();
                txtproduct.setText("");
                txtcat.setSelectedIndex(-1);
                txtproduct.requestFocus();
                txtprice.setText("");
                txtquantity.setText("");
                txtproductstatus.setSelectedIndex(-1);
                jButton5.setEnabled(true);
                new ProductPageNew().setVisible(true);
                this.dispose();
                txtproduct.requestFocus();

            } catch (SQLException ex) {
                Logger.getLogger(ProductPageNew.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void txtpriceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtpriceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtpriceActionPerformed

    private void txtquantityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtquantityActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtquantityActionPerformed

    private void txtproductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtproductActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtproductActionPerformed

    private void selectcatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectcatActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_selectcatActionPerformed

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        // TODO add your handling code here:
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/tanglaw?serverTimezone=UTC&useSSL=false", "root", "12345678");
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from product_details where category='"+selectcat.getSelectedItem().toString()+"'");
            model.setRowCount(0);

            while(rs.next()){
                String idproduct = rs.getString("idproduct");
                String product = rs.getString("product");
                String category = rs.getString("category");
                String idcategory = rs.getString("idcategory");
                String price= rs.getString("price");
                String quantity = rs.getString("quantity");
                String status = rs.getString("status");

                Object[] obj = {idproduct,product,category,idcategory,price,quantity,status};
                model =(DefaultTableModel) tableproduct.getModel();
                model.addRow(obj);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }//GEN-LAST:event_jButton1MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnrangeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnrangeActionPerformed
        // TODO add your handling code here:
        PreparedStatement insert;
        String  value1 =txt1.getText();
        String value2=txt2.getText();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/tanglaw?serverTimezone=UTC&useSSL=false", "root", "12345678");
            insert=con.prepareStatement("select * from product_details where price between ? and ? order by price");
            insert.setString(1, value1);
            insert.setString(2, value2);
            model.setRowCount(0);

            rs= insert.executeQuery();
            while(rs.next()){
                String idproduct = rs.getString("idproduct");
                String product = rs.getString("product");
                String category = rs.getString("category");
                String idcategory = rs.getString("idcategory");
                String price= rs.getString("price");
                String quantity = rs.getString("quantity");
                String status = rs.getString("status");

                Object[] obj = {idproduct,product,category,idcategory,price,quantity,status};
                model =(DefaultTableModel) tableproduct.getModel();
                model.addRow(obj);
            }

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(OrderPage.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(OrderPage.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_btnrangeActionPerformed

    private void refreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshActionPerformed
        // TODO add your handling code here:
        new ProductPageNew().setVisible(true);
        this.dispose();

    }//GEN-LAST:event_refreshActionPerformed

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
            java.util.logging.Logger.getLogger(ProductPageNew.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ProductPageNew.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ProductPageNew.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ProductPageNew.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
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
                new ProductPageNew().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnrange;
    private javax.swing.JLabel categorylabel1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
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
    private javax.swing.JPanel panelout;
    private javax.swing.JLabel products;
    private javax.swing.JButton refresh;
    private javax.swing.JComboBox<String> selectcat;
    private javax.swing.JLabel staff;
    private javax.swing.JTable tableproduct;
    private javax.swing.JLabel transactions;
    private javax.swing.JTextField txt1;
    private javax.swing.JTextField txt2;
    private javax.swing.JComboBox txtcat;
    private javax.swing.JTextField txtprice;
    private javax.swing.JTextField txtproduct;
    private javax.swing.JComboBox<String> txtproductstatus;
    private javax.swing.JTextField txtquantity;
    // End of variables declaration//GEN-END:variables
}
