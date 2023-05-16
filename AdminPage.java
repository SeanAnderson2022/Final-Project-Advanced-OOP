package adminpage;
//import History.History;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Collections;

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
        JFrame frame = new JFrame("Myprogram");
        JLabel label = new JLabel();// create restaurant name text
    	label.setText("Route 66 (ADMIN) ");
    	label.setBounds(500, 0 , 500,100);
    	label.setFont(new Font("Lucida Sans Unicode", Font.BOLD, 45));

        Color button_color = new Color(255, 220, 175);
        
        JButton HistoryButton = new JButton("History Order");
    	HistoryButton.setBounds(50, 180, 240,75);
    	HistoryButton.setFont(new Font("Lucida Sans Unicode", Font.BOLD, 20));
        HistoryButton.setBackground(button_color);
        HistoryButton.setBorder(BorderFactory.createEtchedBorder());
        
        JButton saleStockButton = new JButton("Sale Stocks");
    	saleStockButton.setBounds(50, 275, 240,75);
    	saleStockButton.setFont(new Font("Lucida Sans Unicode", Font.BOLD, 20));
        saleStockButton.setBackground(button_color);
        saleStockButton.setBorder(BorderFactory.createEtchedBorder());

        JButton ProfitInformatiionButton = new JButton("Profit Information");
    	ProfitInformatiionButton.setBounds(50, 370, 240,75);
    	ProfitInformatiionButton.setFont(new Font("Lucida Sans Unicode", Font.BOLD, 20));
        ProfitInformatiionButton.setBackground(button_color);
        ProfitInformatiionButton.setBorder(BorderFactory.createEtchedBorder());
        
        JButton clear_history = new JButton("CLEAR PURCHASE HISTORY");
        clear_history.setBounds(400, 720, 250,50);
        clear_history.setFont(new Font("Lucida Sans Unicode", Font.BOLD, 20));
        clear_history.setBackground(button_color);
        ProfitInformatiionButton.setBorder(BorderFactory.createEtchedBorder());
        
        Color color = new Color(95,196,203);
        JPanel UpperPanel = new JPanel();
    	UpperPanel.setBackground(color);
    	UpperPanel.setBounds(0, 0, 1700, 120);
    
    	
    	JPanel WestPanel = new JPanel();
    	WestPanel.setBackground(Color.CYAN);
    	WestPanel.setBounds(0, 0, 400, 900);

        JPanel CenterPanel = new JPanel();
    	CenterPanel.setBackground(Color.white);
    	CenterPanel.setBounds(500, 120, 1200, 780);

        JPanel HistoryPanel = new JPanel();
    	HistoryPanel.setBackground(Color.LIGHT_GRAY);
    	HistoryPanel.setBounds(575, 120, 800, 780);
        HistoryPanel.setVisible(false);

        JPanel SalePanel = new JPanel();
    	SalePanel.setBackground(Color.LIGHT_GRAY);
    	SalePanel.setBounds(575, 120, 800, 780);
        SalePanel.setVisible(false);

        JPanel ProfitPanel = new JPanel();
    	ProfitPanel.setBackground(Color.LIGHT_GRAY);
    	ProfitPanel.setBounds(575, 120, 800, 780);
        ProfitPanel.setVisible(false);
        
        clear_history.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                clear_purchase_history();
                frame.dispose();
                new AdminPage();
            }
        });
        HistoryButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                // this will hide very panels excepy for history panel
                SalePanel.setVisible(false);
                ProfitPanel.setVisible(false);
                HistoryPanel.setVisible(true);
                HistoryPanel.removeAll();
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
                        data[4] = rs.getInt("Total");
                        dtm.addRow(data);
                    }
                    JTable history_table = new JTable(dtm);
                    
                    //Collections.reverse();
                    
                    HistoryPanel.add(new JScrollPane(history_table), BorderLayout.NORTH);
                    HistoryPanel.add(clear_history, BorderLayout.SOUTH);
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
                        data[2] = rs.getInt("Price");
                        data[3] = rs.getInt("Stock");
                        data[4] = rs.getInt("ConsumedStocks");
                        dtm.addRow(data);
                    }
                    JTable sales_table = new JTable(dtm);
                    SalePanel.add(new JScrollPane(sales_table));
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
                    
                    //for column headers
                    String[] col = {"ID", "Item Name", "Profit"};
              
                    
                    Statement pst = connection.createStatement();
                    ResultSet rs = pst.executeQuery(query);
                    
                    DefaultTableModel dtm = new DefaultTableModel(col, 0);
                    Object[] data = new Object[col.length];
                    int total_profit = 0;
                    while(rs.next()){
                        data[0] = rs.getInt("ProductID");
                        data[1] = rs.getString("ProductName");
                        int price = rs.getInt("Price");
                        int consumed_stocks = rs.getInt("ConsumedStocks");
                        int total_per_item = price*consumed_stocks;
                        total_profit += total_per_item;
                        data[2] = total_per_item;
                        dtm.addRow(data);
                    }
                    JTable profit_table = new JTable(dtm);
                    ProfitPanel.add(new JScrollPane(profit_table));
                    JLabel final_profit = new JLabel("TOTAL PROFIT: " + total_profit + " php");
                    final_profit.setBounds(700, 700, 200, 50);
                    final_profit.setFont(new Font("Lucida Sans Unicode", Font.BOLD, 19));
                    ProfitPanel.add(final_profit);
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
        UpperPanel.add(label);

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

