package adminpage;
//import History.History;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
//import java.util.Collections;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.*;

public class AdminPage {
    public static void clear_purchase_history(){
        try{
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection connection = DriverManager.getConnection(databaseURL);
            //System.out.println("Connected to MS Access database");
            Statement pst = connection.createStatement();
            pst.execute("DELETE FROM PurchaseOrder;");
            //pst.executeUpdate("ALTER TABLE PurchaseOrder ALTER COLUMN OrderID Counter(1,1);");
            //pst.execute("ALTER TABLE PurchaseOrder ALTER COLUMN OrderID AUTOINCREMENT;");
            connection.close();
        } catch(SQLException error){
            error.printStackTrace();
        } catch(ClassNotFoundException error){
            error.printStackTrace();
        }
    }
    
    public static String databaseURL = "jdbc:ucanaccess://C:/Users/emman/OneDrive/Desktop/sample_2/database.accdb;memory=false";

    AdminPage(){
        JFrame frame = new JFrame("ROUTE 66 (Admin View)");
        
        JLabel label = new JLabel();// create restaurant name text
    	label.setText("ADMINISTRATOR VIEW");
    	label.setBounds(550, 10 , 600,100);
    	label.setFont(new Font("Lucida Sans Unicode", Font.BOLD, 40));
        
        JLabel logo = new JLabel(); //JLabel Creation
        ImageIcon imageIcon = new ImageIcon("C:/Users/emman/OneDrive/Desktop/sample_2/design_img/logo_final.png"); // load the image to a imageIcon
        Image image = imageIcon.getImage(); // transform it 
        Image newimg = image.getScaledInstance(60, 60,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
        imageIcon = new ImageIcon(newimg);  // transform it back
        logo.setIcon(imageIcon);
        logo.setBounds(30, 25, 65, 65); //Sets the location of the image
        ImageIcon iconLogo = new ImageIcon("C:/Users/emman/OneDrive/Desktop/sample_2/design_img/logo_final.png");
        //iconLogo.setBounds(10, 10, 40, 40);
        frame.setIconImage(iconLogo.getImage());
        
        JLabel restaurantName = new JLabel();// create restaurant name text
        restaurantName.setText("ROUTE 66");
        restaurantName.setBounds(110,45 , 500, 20);
        restaurantName.setFont(new Font("Verdana", Font.BOLD, 25));
            
        Color unselected_button = new Color(230, 230, 230);
        Color content_panel = new Color(251, 246, 240);
        Color orange_button = new Color(255, 220, 175);
        
        JButton HistoryButton = new JButton("HISTORY ORDER");
    	HistoryButton.setBounds(0, 130, 400,100);
    	HistoryButton.setFont(new Font("Lucida Sans Unicode", Font.PLAIN, 20));
        HistoryButton.setBackground(unselected_button);
        HistoryButton.setBorder(BorderFactory.createEtchedBorder());
        HistoryButton.setBorderPainted(false);
        HistoryButton.setFocusPainted(false);
        
        JButton saleStockButton = new JButton("SALE STOCKS");
    	saleStockButton.setBounds(0, 231, 400,100);
    	saleStockButton.setFont(new Font("Lucida Sans Unicode", Font.PLAIN, 20));
        saleStockButton.setBackground(unselected_button);
        saleStockButton.setBorder(BorderFactory.createEtchedBorder());
        saleStockButton.setBorderPainted(false);
        saleStockButton.setBorder(BorderFactory.createEtchedBorder());
        saleStockButton.setFocusPainted(false);
        
        JButton ProfitInformatiionButton = new JButton("PROFIT INFORMATION");
    	ProfitInformatiionButton.setBounds(0, 332, 400,100);
    	ProfitInformatiionButton.setBorder(BorderFactory.createEtchedBorder());
        ProfitInformatiionButton.setFont(new Font("Lucida Sans Unicode", Font.PLAIN, 20));
        ProfitInformatiionButton.setBackground(unselected_button);
        ProfitInformatiionButton.setBorder(BorderFactory.createEtchedBorder());
        ProfitInformatiionButton.setFocusPainted(false);
        ProfitInformatiionButton.setBorderPainted(false);
        
        JButton clear_history = new JButton("CLEAR PURCHASE HISTORY");
        clear_history.setBounds(600, 480, 340,50);
        clear_history.setFont(new Font("Lucida Sans Unicode", Font.BOLD, 18));
        clear_history.setBackground(orange_button);
        clear_history.setBorder(BorderFactory.createEtchedBorder());
        clear_history.setFocusPainted(false);
        
        Color color = new Color(174, 230, 230);
        JPanel UpperPanel = new JPanel();
    	UpperPanel.setBackground(color);
    	UpperPanel.setBounds(0, 0, 1700, 130);

    	JPanel WestPanel = new JPanel();
    	WestPanel.setBackground(unselected_button);
    	WestPanel.setBounds(0, 0, 400, 900);

        JPanel CenterPanel = new JPanel();
    	CenterPanel.setBackground(Color.WHITE);
    	CenterPanel.setBounds(500, 120, 1200, 780);
        
        JPanel HistoryPanel = new JPanel();
    	HistoryPanel.setBackground(content_panel);
    	HistoryPanel.setBounds(470, 160, 1000, 560);
        HistoryPanel.setVisible(false);
        HistoryPanel.setLayout(null);

        JPanel SalePanel = new JPanel();
    	SalePanel.setBackground(content_panel);
    	SalePanel.setBounds(470, 160, 1000, 560);
        SalePanel.setVisible(false);
        SalePanel.setLayout(null);

        JPanel ProfitPanel = new JPanel();
    	ProfitPanel.setBackground(content_panel);
    	ProfitPanel.setBounds(470, 160, 1000, 560);
        ProfitPanel.setVisible(false);
        ProfitPanel.setLayout(null);

        clear_history.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                clear_purchase_history();
                frame.dispose();
                new AdminPage();
            }
        });
        
        //for indentation of cells 
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(JLabel.RIGHT);
        DefaultTableCellRenderer leftRenderer = new DefaultTableCellRenderer();
        leftRenderer.setHorizontalAlignment(JLabel.LEFT);
                    
        HistoryButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                // this will hide very panels excepy for history panel
                HistoryButton.setBackground(Color.WHITE);
                saleStockButton.setBackground(unselected_button);
                ProfitInformatiionButton.setBackground(unselected_button);
                SalePanel.setVisible(false);
                ProfitPanel.setVisible(false);
                HistoryPanel.setVisible(true);
                HistoryPanel.removeAll();
                HistoryPanel.add(clear_history);
                //create table
                try{
                    Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
                    Connection connection = DriverManager.getConnection(databaseURL);
                    System.out.println("Connected to DB");
                    String extract1 = "SELECT PurchaseOrder.OrderID, PurchaseOrder.CustomerID, PurchaseOrder.Quantity, PurchaseOrder.Total, Product.ProductName FROM PurchaseOrder, Product WHERE PurchaseOrder.ProductID = Product.ProductID";
                    
                    //for column headers
                    String[] col = {"OrderID", "CustomerID", "Item", "Qty", "Total"};
                    
                    
                    Statement pst = connection.createStatement();
                    ResultSet rs = pst.executeQuery(extract1);
                    
                    DefaultTableModel dtm = new DefaultTableModel(col, 0);
                    Object[] data = new Object[col.length];
                    
                    while(rs.next()){
                        data[0] = rs.getInt("OrderID");
                        data[1] = rs.getInt("CustomerID");
                        data[2] = rs.getString("ProductName");
                        data[3] = rs.getInt("Quantity");
                        data[4] = rs.getInt("Total") + " php";
                        dtm.addRow(data);
                    }
                    JTable history_table = new JTable(dtm);
                    
                    //Collections.reverse();
                    
                    history_table.getColumnModel().getColumn(0).setPreferredWidth(100);
                    history_table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
                    history_table.getColumnModel().getColumn(1).setPreferredWidth(170);
                    history_table.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
                    
                    history_table.getColumnModel().getColumn(2).setPreferredWidth(300);
                    history_table.getColumnModel().getColumn(2).setCellRenderer(leftRenderer);
                    history_table.getColumnModel().getColumn(3).setPreferredWidth(70);
                    history_table.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
                    history_table.getColumnModel().getColumn(4).setPreferredWidth(150);
                    history_table.getColumnModel().getColumn(4).setCellRenderer(rightRenderer);
                    history_table.setRowHeight(30);
                    history_table.setBackground(Color.WHITE);
                    history_table.setForeground(Color.BLACK);
                    history_table.setBorder(BorderFactory.createLineBorder(Color.black));
                    history_table.setFont(new Font("Lucidas Sans Unicode", Font.PLAIN, 20));
                    history_table.getTableHeader().setBackground(Color.WHITE);
                    history_table.getTableHeader().setFont(new Font("Lucida Sans Unicode", Font.BOLD, 22));
                    history_table.getTableHeader().setForeground(Color.BLACK);
                    history_table.getTableHeader().setBorder(BorderFactory.createLineBorder(Color.black));
                    
                    JScrollPane table_display = new JScrollPane(history_table);
                    
                    table_display.getViewport().setBackground(content_panel);
                    table_display.setBorder(BorderFactory.createEmptyBorder());
                    table_display.setBounds(50, 20, 800, 440);
                    HistoryPanel.add(table_display);
                    

                    rs.close();
                    pst.close();
                } catch(SQLException error){
                    error.printStackTrace();
                } catch(ClassNotFoundException error){
                    error.printStackTrace();
                }
            
            }
        });

        saleStockButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                //adjust color codes of buttons
                HistoryButton.setBackground(unselected_button);
                saleStockButton.setBackground(Color.WHITE);
                ProfitInformatiionButton.setBackground(unselected_button);
                HistoryPanel.setVisible(false);
                ProfitPanel.setVisible(false);
                SalePanel.setVisible(true);
                SalePanel.removeAll();
                try{
                    Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
                    Connection connection = DriverManager.getConnection(databaseURL);
                    System.out.println("Connected to DB");
                    //query ni purchase order table with 'productname' nung id nung food which came from a separate table
                    String query = "SELECT * FROM Product";
                    
                    //for column headers
                    String[] col = {"Product ID", "Item Name", "Price", "Stocks Remaining", "Stocks Consumed"};
              
                    
                    Statement pst = connection.createStatement();
                    ResultSet rs = pst.executeQuery(query);
                    
                    DefaultTableModel dtm = new DefaultTableModel(col, 0);
                    Object[] data = new Object[col.length];
                    
                    while(rs.next()){
                        data[0] = rs.getInt("ProductID");
                        data[1] = rs.getString("ProductName");
                        data[2] = rs.getInt("Price") + " php";
                        data[3] = rs.getInt("Stock");
                        data[4] = rs.getInt("ConsumedStocks");
                        dtm.addRow(data);
                    }
                    JTable sales_table = new JTable(dtm);
                    
                    sales_table.getColumnModel().getColumn(0).setPreferredWidth(250);
                    sales_table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
                    sales_table.getColumnModel().getColumn(1).setPreferredWidth(450);
                    sales_table.getColumnModel().getColumn(1).setCellRenderer(leftRenderer);
                    
                    sales_table.getColumnModel().getColumn(2).setPreferredWidth(250);
                    sales_table.getColumnModel().getColumn(2).setCellRenderer(rightRenderer);
                    sales_table.getColumnModel().getColumn(3).setPreferredWidth(400);
                    sales_table.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
                    sales_table.getColumnModel().getColumn(4).setPreferredWidth(400);
                    sales_table.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
                    sales_table.setRowHeight(40);
                    sales_table.setBackground(Color.WHITE);
                    sales_table.setForeground(Color.BLACK);
                    sales_table.setBorder(BorderFactory.createLineBorder(Color.black));
                    sales_table.setFont(new Font("Lucidas Sans Unicode", Font.PLAIN, 20));
                    sales_table.getTableHeader().setBackground(Color.WHITE);
                    sales_table.getTableHeader().setFont(new Font("Lucida Sans Unicode", Font.BOLD, 18));
                    sales_table.getTableHeader().setForeground(Color.BLACK);
                    sales_table.getTableHeader().setBorder(BorderFactory.createLineBorder(Color.black));
                    
                   
                    JScrollPane table_display = new JScrollPane(sales_table);
                    
                    table_display.getViewport().setBackground(content_panel);
                    table_display.setBorder(BorderFactory.createEmptyBorder());
                    table_display.setBounds(50, 20, 900, 500);
                    SalePanel.add(table_display);
                    
                    rs.close();
                    pst.close();
                } catch(SQLException error){
                    error.printStackTrace();
                } catch(ClassNotFoundException error){
                    error.printStackTrace();
                }

            }
        });

        
        ProfitInformatiionButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                //adjust color codes of buttons
                saleStockButton.setBackground(unselected_button);
                ProfitInformatiionButton.setBackground(Color.WHITE);
                HistoryButton.setBackground(unselected_button);
                HistoryPanel.setVisible(false);
                SalePanel.setVisible(false);
                ProfitPanel.setVisible(true);
                ProfitPanel.removeAll();
                        
                
                try{
                    Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
                    Connection connection = DriverManager.getConnection(databaseURL);
                    System.out.println("Connected to DB");
                    //query ni purchase order table with 'productname' nung id nung food which came from a separate table
                    String query = "SELECT * FROM Product";
                    String get_sum_query = "SELECT SUM(Stock) AS total_profit_query FROM Product";
                    //for column headers
                    int sum_query = 0;
                    
                    String[] col = {"ID", "Item Name", "Profit"};
                    
                    
                    Statement pst = connection.createStatement();
                    ResultSet rs = pst.executeQuery(query);
                    
                    Statement sum = connection.createStatement();
                    ResultSet total_profit = sum.executeQuery(get_sum_query);
                    
                   
                    DefaultTableModel dtm = new DefaultTableModel(col, 0);
                    Object[] data = new Object[col.length];
                    
                    while(rs.next()){
                        data[0] = rs.getInt("ProductID");
                        data[1] = rs.getString("ProductName");
                        int price = rs.getInt("Price");
                        int consumed_stocks = rs.getInt("ConsumedStocks");
                        int total_per_item = price*consumed_stocks;
                        
                        data[2] = total_per_item + " php";
                        dtm.addRow(data);
                    }
                    JTable profit_table = new JTable(dtm);
                    profit_table.getColumnModel().getColumn(0).setPreferredWidth(250);
                    profit_table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
                    profit_table.getColumnModel().getColumn(1).setPreferredWidth(500);
                    profit_table.getColumnModel().getColumn(1).setCellRenderer(leftRenderer);
                    
                    profit_table.getColumnModel().getColumn(2).setPreferredWidth(400);
                    profit_table.getColumnModel().getColumn(2).setCellRenderer(rightRenderer);

                    profit_table.setRowHeight(30);
                    profit_table.setBackground(Color.WHITE);
                    profit_table.setForeground(Color.BLACK);
                    profit_table.setBorder(BorderFactory.createLineBorder(Color.black));
                    profit_table.setFont(new Font("Lucidas Sans Unicode", Font.PLAIN, 22));
                    profit_table.getTableHeader().setBackground(Color.WHITE);
                    profit_table.getTableHeader().setFont(new Font("Lucida Sans Unicode", Font.BOLD, 18));
                    profit_table.getTableHeader().setForeground(Color.BLACK);
                    profit_table.getTableHeader().setBorder(BorderFactory.createLineBorder(Color.black));
                    
                    JScrollPane table_display = new JScrollPane(profit_table);
                    
                    table_display.getViewport().setBackground(content_panel);
                    table_display.setBorder(BorderFactory.createEmptyBorder());
                    table_display.setBounds(50, 40, 900, 400);
                    ProfitPanel.add(table_display);
                    
                    
                    while (total_profit.next()) {
                        int c = total_profit.getInt(1);
                        sum_query = sum_query + c;
                    }
                    
                    JLabel final_profit = new JLabel("TOTAL PROFIT: " + sum_query + " php");
                    final_profit.setBounds(600, 470, 400, 50);
                    final_profit.setFont(new Font("Lucida Sans Unicode", Font.BOLD, 25));
                    ProfitPanel.add(final_profit);
                    
                    total_profit.close();
                    sum.close();
                    rs.close();
                    pst.close();
                } catch(SQLException error){
                    error.printStackTrace();
                } catch(ClassNotFoundException error){
                    error.printStackTrace();
                }
            }
        });
        
        
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setSize(1550, 1000);
        
        frame.setVisible(true);
        frame.add(HistoryButton);
        frame.add(saleStockButton);
        frame.add(ProfitInformatiionButton);
        frame.add(label);
        frame.add(logo);
        frame.add(restaurantName);
        
        frame.add(UpperPanel);
        frame.add(WestPanel);
        
        frame.add(HistoryPanel);
        frame.add(SalePanel);
        frame.add(ProfitPanel);
        frame.add(CenterPanel);
        
        
    }
    public static void main(String[] args) { 	
    	new AdminPage();
    } 
}

