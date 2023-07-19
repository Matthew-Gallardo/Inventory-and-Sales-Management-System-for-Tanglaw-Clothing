/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package jframes;

import java.awt.BorderLayout;
import java.awt.Color;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DecimalFormat;
import javax.swing.table.DefaultTableModel;
import static jframes.DBConnection.con;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;


/**
 *
 * @author sunil
 */
public class HomePage extends javax.swing.JFrame {

    /**
     * Creates new form SignupPage
     */
    
            
    public HomePage() {
        initComponents();
        showPieChart();
        setProductDetailsToTable();
        setOutofStockToTable();
        setDataToCard();
    }
     DefaultTableModel model;
     
     
     public void setDataToCard(){
         Statement st=null;
         ResultSet rs=null;
         try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DBConnection.getConnection();
            st = con.createStatement();
            rs = st.executeQuery("select * from product_details");
            rs.last();
            prodnum.setText(Integer.toString(rs.getRow()));
            
            rs = st.executeQuery("select * from customer_details"); 
            rs.last();
            cusnum.setText(Integer.toString(rs.getRow()));
            
            rs = st.executeQuery("select sum(total) as totals from orderedproducts"); 
            rs.last();
            DecimalFormat ff = new DecimalFormat("#,##0;(#,##0)");
            String t =ff.format(rs.getInt("totals"));
            totalsales.setText(String.valueOf(t));       
                     
         } catch (Exception e) {
              e.printStackTrace();
         }
     }
     
     
      public void setProductDetailsToTable(){
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DBConnection.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from orderedproducts");
            
            while(rs.next()){
                String idproduct = rs.getString("idproduct");
                String product = rs.getString("product");
                
                String price= rs.getString("price");
                String quantity = rs.getString("quantity");
               
                
               
                
                
                Object[] obj = {idproduct,product,price,quantity};
                model =(DefaultTableModel) homeprod.getModel();
                model.addRow(obj);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
      //to set the category details into the table
    public void setOutofStockToTable(){
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/tanglaw?serverTimezone=UTC&useSSL=false", "root", "12345678");
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from product_details where quantity <= 3");
            
            while(rs.next()){
                String idcategory = rs.getString("idproduct");
                String category = rs.getString("product");
                String quantity = rs.getString("quantity");
               
                
                Object[] obj = {idcategory,category,quantity};
                model =(DefaultTableModel) homecat.getModel();
                model.addRow(obj);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
    public void showPieChart(){
        
        //create dataset
      DefaultPieDataset barDataset = new DefaultPieDataset( );
      
        try {
           Class.forName("com.mysql.jdbc.Driver");
           con = DriverManager.getConnection("jdbc:mysql://localhost:3306/tanglaw?serverTimezone=UTC&useSSL=false", "root", "12345678");
           Statement st = con.createStatement();
           ResultSet rs = st.executeQuery("select product, count(*) as product_count from orderedproducts group  by idproduct");
           
           while(rs.next()){
               barDataset.setValue(rs.getString("product"), new Double(rs.getDouble("product_count")));
                       
           }
           
           
        } catch (Exception e) {
        }
      
      //create chart
       JFreeChart piechart = ChartFactory.createPieChart("Tanglaw Product Sales",barDataset, false,true,false);//explain
      
        PiePlot piePlot =(PiePlot) piechart.getPlot();
      
       //changing pie chart blocks colors
       piePlot.setSectionPaint("IPhone 5s", new Color(255,255,102));
        piePlot.setSectionPaint("SamSung Grand", new Color(102,255,102));
        piePlot.setSectionPaint("MotoG", new Color(255,102,153));
        piePlot.setSectionPaint("Nokia Lumia", new Color(0,204,204));
      
       
        piePlot.setBackgroundPaint(Color.white);
        
        //create chartPanel to display chart(graph)
        ChartPanel barChartPanel = new ChartPanel(piechart);
        panelPieChart.removeAll();
        panelPieChart.add(barChartPanel, BorderLayout.CENTER);
        panelPieChart.validate();
    }
    
    public void setUsername(String username) {
        this.userName.setText("Welcome back " + username + "!");
        
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
        panelout = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        homecat = new rojeru_san.complementos.RSTableMetro();
        userName = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        jLabel27 = new javax.swing.JLabel();
        prodnum = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        homeprod = new rojeru_san.complementos.RSTableMetro();
        jPanel9 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        panelPieChart = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel30 = new javax.swing.JLabel();
        totalsales = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel33 = new javax.swing.JLabel();
        cusnum = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
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
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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

        jPanel1.add(panelout, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 690, 140, 50));

        jPanel4.setBackground(new java.awt.Color(143, 88, 85));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        homecat.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Product ID", "Product", "Stock"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        homecat.setColorBackgoundHead(new java.awt.Color(128, 0, 0));
        homecat.setColorFilasBackgound2(new java.awt.Color(255, 255, 255));
        homecat.setColorFilasForeground1(new java.awt.Color(0, 0, 0));
        homecat.setColorFilasForeground2(new java.awt.Color(0, 0, 0));
        homecat.setColorSelBackgound(new java.awt.Color(204, 0, 0));
        homecat.setIntercellSpacing(new java.awt.Dimension(0, 0));
        homecat.setRowHeight(25);
        jScrollPane2.setViewportView(homecat);

        jPanel4.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 280, 480, 140));

        userName.setFont(new java.awt.Font("Verdana", 1, 30)); // NOI18N
        userName.setForeground(java.awt.Color.lightGray);
        userName.setText("Welcome Back User!");
        jPanel4.add(userName, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 20, 370, -1));

        jPanel12.setBackground(java.awt.Color.lightGray);
        jPanel12.setBorder(javax.swing.BorderFactory.createMatteBorder(15, 0, 0, 0, new java.awt.Color(128, 0, 0)));
        jPanel12.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel27.setFont(new java.awt.Font("Segoe UI Black", 1, 50)); // NOI18N
        jLabel27.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/view product.png"))); // NOI18N
        jPanel12.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 30, -1, -1));

        prodnum.setFont(new java.awt.Font("Segoe UI Black", 1, 50)); // NOI18N
        prodnum.setText("69");
        jPanel12.add(prodnum, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 20, 70, -1));

        jLabel32.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLabel32.setText("NUMBER OF PRODUCTS");
        jPanel12.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 240, -1));

        jPanel4.add(jPanel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 100, 230, 120));

        homeprod.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ProductID", "ProductName", "Price", "Quantity"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        homeprod.setColorBackgoundHead(new java.awt.Color(128, 0, 0));
        homeprod.setColorFilasBackgound2(new java.awt.Color(255, 255, 255));
        homeprod.setColorFilasForeground1(new java.awt.Color(0, 0, 0));
        homeprod.setColorFilasForeground2(new java.awt.Color(0, 0, 0));
        homeprod.setColorSelBackgound(new java.awt.Color(204, 0, 0));
        homeprod.setIntercellSpacing(new java.awt.Dimension(0, 0));
        homeprod.setRowHeight(30);
        jScrollPane1.setViewportView(homeprod);

        jPanel4.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 460, 520, 160));

        jPanel9.setLayout(new java.awt.CardLayout());
        jPanel4.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 410, -1, -1));

        jPanel10.setBackground(new java.awt.Color(128, 0, 0));
        jPanel10.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelPieChart.setBackground(java.awt.Color.lightGray);
        panelPieChart.setLayout(new java.awt.BorderLayout());
        jPanel10.add(panelPieChart, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 480, 300, 140));

        jPanel5.setBackground(java.awt.Color.lightGray);
        jPanel5.setBorder(javax.swing.BorderFactory.createMatteBorder(15, 0, 0, 0, new java.awt.Color(143, 88, 85)));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel30.setFont(new java.awt.Font("Segoe UI Black", 1, 50)); // NOI18N
        jLabel30.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DB ICONS/icons8-total-sales-65.png"))); // NOI18N
        jPanel5.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        totalsales.setFont(new java.awt.Font("Segoe UI Black", 1, 50)); // NOI18N
        totalsales.setText("420");
        jPanel5.add(totalsales, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 20, 220, -1));

        jLabel12.setFont(new java.awt.Font("Verdana", 1, 16)); // NOI18N
        jLabel12.setText("TOTAL SALES");
        jPanel5.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 80, 200, -1));

        jPanel10.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 350, 300, 110));

        jPanel4.add(jPanel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 0, 390, 720));

        jLabel13.setFont(new java.awt.Font("Verdana", 1, 20)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Best-selling Products");
        jPanel4.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 430, 240, -1));

        jPanel6.setBackground(java.awt.Color.lightGray);
        jPanel6.setBorder(javax.swing.BorderFactory.createMatteBorder(15, 0, 0, 0, new java.awt.Color(128, 0, 0)));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel33.setFont(new java.awt.Font("Segoe UI Black", 1, 50)); // NOI18N
        jLabel33.setIcon(new javax.swing.ImageIcon(getClass().getResource("/adminIcons/icons8-customer-64.png"))); // NOI18N
        jPanel6.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, -1, -1));

        cusnum.setFont(new java.awt.Font("Segoe UI Black", 1, 50)); // NOI18N
        cusnum.setText("420");
        jPanel6.add(cusnum, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 20, -1, 70));

        jLabel14.setBackground(new java.awt.Color(0, 0, 0));
        jLabel14.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLabel14.setText("NUMBER OF STAFF");
        jPanel6.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 90, 160, -1));

        jPanel4.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 100, 230, 120));

        jLabel15.setFont(new java.awt.Font("Verdana", 1, 20)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Out of Stock Products");
        jPanel4.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 250, 250, -1));

        jLabel34.setFont(new java.awt.Font("Verdana", 1, 24)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(255, 255, 255));
        jLabel34.setText("Overview");
        jPanel4.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 60, 140, -1));

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 90, 900, 640));

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

        jPanel1.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1330, 80));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Tanglaw Assets/aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa 2.png"))); // NOI18N
        jLabel1.setText("jLabel1");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 430, -1, -1));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel7.setBackground(new java.awt.Color(143, 83, 85));
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel18.setBackground(new java.awt.Color(204, 0, 0));
        jLabel18.setFont(new java.awt.Font("Verdana", 3, 17)); // NOI18N
        jLabel18.setForeground(java.awt.Color.gray);
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

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Tanglaw Assets/aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa 2.png"))); // NOI18N
        jLabel2.setText("jLabel2");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 350, -1, -1));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Tanglaw Assets/Zasa 2.png"))); // NOI18N
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 0, -1, -1));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 350, 710));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1300, 790));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel21MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel21MouseClicked
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_jLabel21MouseClicked

    private void jLabel18MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel18MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel18MouseClicked

    private void jLabel18MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel18MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel18MouseEntered

    private void jLabel18MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel18MouseExited
        // TODO add your handling code here:
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
            java.util.logging.Logger.getLogger(HomePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HomePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HomePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HomePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold> 
        //</editor-fold> 
        //</editor-fold> 
        //</editor-fold> 

        /* Create and display the form */
      
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new HomePage().setVisible(true);
            }
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel categorylabel1;
    private javax.swing.JLabel cusnum;
    private rojeru_san.complementos.RSTableMetro homecat;
    private rojeru_san.complementos.RSTableMetro homeprod;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel orders;
    private javax.swing.JPanel panelPieChart;
    private javax.swing.JPanel panelcat;
    private javax.swing.JPanel panelcat1;
    private javax.swing.JPanel panelcat2;
    private javax.swing.JPanel panelcat3;
    private javax.swing.JPanel panelcat4;
    private javax.swing.JPanel panelout;
    private javax.swing.JLabel prodnum;
    private javax.swing.JLabel products;
    private javax.swing.JLabel staff;
    private javax.swing.JLabel totalsales;
    private javax.swing.JLabel transactions;
    private javax.swing.JLabel userName;
    // End of variables declaration//GEN-END:variables

}