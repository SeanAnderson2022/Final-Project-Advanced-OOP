package src;
import Order.Order;
import ReceiptWindow.ReceiptWindow;
import javax.swing.*;
import java.sql.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.util.ArrayList;

public class CustomerOrder {
        public static String value = "";
        public static int price = 0;
        public static int id = 0;
        public static int item_stock = 0;
        public static int food_check = 0;
        public static String[] food_array = {"Egg Salad", "Salmon Balls", "Dynamite Cheese", "Tempura", 
                                            "Beef Steak", "Whole Chicken", "Bulalo", "Fish",
                                            "Spaghetti", "Pesto", "French Fries", "Burger",
                                            "Cake", "Cupcake", "Ice Cream", "Cookies",
                                            "Beer", "Coffee", "Soda", "Frappe"};
        public static int[] price_array = {60, 50, 30, 80,
                                          150, 260, 350, 120,
                                          70, 115, 60, 90,
                                          60, 45, 60, 50,
                                          40, 50, 25, 65};
        public static int[] product_id = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 
                                       11, 12, 13, 14, 15, 16, 17, 18, 19, 20};
        static int search(ArrayList<String> food_order, String value){	
            int i;
            for (i = 0; i <food_order.size(); i++)
            {
                    if (food_order.get(i).equals(value))
                    {
                            return i;
                    }
            }
            return -1;	
        }
        
        public static String databaseURL = "jdbc:ucanaccess://C:/Users/emman/OneDrive/Desktop/sample_2/database.accdb;memory=false";
        public static int customer_number;
        
        public static void delete_current_order(int customer_number){
            try{
                Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
                Connection connection = DriverManager.getConnection(databaseURL);
                //System.out.println("Connected to MS Access database");
                Statement query = connection.createStatement();
                ResultSet resultset = query.executeQuery("SELECT Product.Stock, Product.ConsumedStocks, PurchaseOrder.Quantity, PurchaseOrder.CustomerID, PurchaseOrder.ProductID FROM Product, PurchaseOrder WHERE CustomerID = "+customer_number+";");
                
                while(resultset.next()){
                    int remaining_stock = resultset.getInt("Stock");
                    System.out.print("Remaining stock: " + remaining_stock);
                    int id = resultset.getInt("ProductID");
                    int qty = resultset.getInt("Quantity");
                    System.out.print("QTY: " + qty);
                    int stock = remaining_stock + qty;
                    int consumed_stock = resultset.getInt("ConsumedStocks");
                    System.out.print("Cons Stock: " + consumed_stock);
                    int new_val_con_stock = consumed_stock - qty;
                    System.out.print("new stock valu: " + new_val_con_stock);
                    PreparedStatement sql = connection.prepareStatement("UPDATE Product SET Stock = "+stock+", ConsumedStocks = "+new_val_con_stock+" WHERE ProductID = "+id+";");
                    sql.executeUpdate();
                    sql.close();
                }
                PreparedStatement stmt = connection.prepareStatement("DELETE FROM PurchaseOrder WHERE CustomerID ="+customer_number+";");
                stmt.execute();
                stmt.close();
                resultset.close();
                query.close();
                connection.close();

            } catch(SQLException error){
                error.printStackTrace();
            } catch(ClassNotFoundException error){
                error.printStackTrace();
            }
        }
        
        public static void get_rows(){
            String sql = "SELECT COUNT(*) AS CustomerID FROM Customer";
            int rowCount;
            try {
                Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
                Connection connection = DriverManager.getConnection(databaseURL);
                System.out.println("Connected to MS Access database");

                Statement pst = connection.createStatement();
                ResultSet rs = pst.executeQuery(sql);
                if (rs.next()) {
                    rowCount = rs.getInt("CustomerID");
                    customer_number = rowCount + 1;
                }
                // Close the resources
                rs.close();
                pst.close();
                connection.close();
            } catch(SQLException err){
                err.printStackTrace();
            } catch(ClassNotFoundException err){
                err.printStackTrace();
            }
        }                
	public CustomerOrder(){
            JFrame frame = new JFrame("ROUTE 66");
            value = "";
            price = 0;
            get_rows();
            Order menu = new Order();
            menu.clean();
            menu.setCurrent_customer_no(customer_number);
            
            try{
                Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
                Connection connection = DriverManager.getConnection(databaseURL);
                //System.out.println("Connected to MS Access database");
                Statement query = connection.createStatement();
                ResultSet resultset = query.executeQuery("SELECT Stock FROM Product;");
                
                while(resultset.next()){
                    int remaining_stock = resultset.getInt("Stock");
                    menu.getStock_list().add(remaining_stock);
                }
                
                resultset.close();
                query.close();
                connection.close();

            } catch(SQLException error){
                error.printStackTrace();
            } catch(ClassNotFoundException error){
                error.printStackTrace();
            }
            
            JLabel logo = new JLabel(); //JLabel Creation
            ImageIcon imageIcon = new ImageIcon("C:/Users/emman/OneDrive/Desktop/sample_2/design_img/logo_final.png"); // load the image to a imageIcon
            Image image = imageIcon.getImage(); // transform it 
            Image newimg = image.getScaledInstance(60, 60,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
            imageIcon = new ImageIcon(newimg);  // transform it back
            logo.setIcon(imageIcon);
            logo.setBounds(30, 10, 65, 65); //Sets the location of the image
            
            JLabel restaurantName = new JLabel();// create restaurant name text
            restaurantName.setText("ROUTE 66");
            restaurantName.setBounds(110,29 , 500, 20);
            restaurantName.setFont(new Font("Verdana", Font.BOLD, 25));
            
            JLabel itemName = new JLabel();            
            itemName.setText("ITEM: " + value);
            itemName.setBounds(15, 710, 500, 29);
            itemName.setFont(new Font("Lucida Sans Unicode", Font.BOLD, 24));
            
            JLabel itemPrice = new JLabel();
            itemPrice.setText("PRICE: " + value);
            itemPrice.setBounds(15, 745, 500, 27);
            itemPrice.setFont(new Font("Lucida Sans Unicode", Font.BOLD, 22));
            
            JLabel CustomerLabel = new JLabel();// create restaurant name text
            CustomerLabel.setText("CUSTOMER NO. " + customer_number);
            CustomerLabel.setBounds(500, 0 , 500,100);
            CustomerLabel.setFont(new Font("Verdana", Font.BOLD, 45));

            JLabel QuantityLabel = new JLabel();// create restaurant name text
            QuantityLabel.setText("QUANTITY");
            QuantityLabel.setBounds(990, 700 , 500,100);
            QuantityLabel.setFont(new Font("Lucida Sans Unicode", Font.BOLD, 18));

            // coloring shit
            Color food_padding = new Color(251, 246, 240);
            Color back = new Color(174, 230, 230);
            Color center_background = new Color(255, 255, 255);
            Color back_button_color = new Color(230, 230, 230);
            
            JPanel FoodPanel1 = new JPanel();
            FoodPanel1.setLayout(new GridLayout(2,2,20,20));
            FoodPanel1.setBorder(BorderFactory.createEmptyBorder(50,50,50,50));
            FoodPanel1.setBackground(food_padding);
            FoodPanel1.setPreferredSize(new Dimension(1000,500));

            JPanel FoodPanel2 = new JPanel();
            FoodPanel2.setLayout(new GridLayout(2,2,20,20));
            FoodPanel2.setBorder(BorderFactory.createEmptyBorder(50,50,50,50));
            FoodPanel2.setBackground(food_padding);
            FoodPanel2.setPreferredSize(new Dimension(1000,500));
            FoodPanel2.setVisible(false);

            JPanel FoodPanel3 = new JPanel();
            FoodPanel3.setLayout(new GridLayout(2,2,20,20));
            FoodPanel3.setBorder(BorderFactory.createEmptyBorder(50,50,50,50));
            FoodPanel3.setBackground(food_padding);
            FoodPanel3.setPreferredSize(new Dimension(1000,500));
            FoodPanel3.setVisible(false);

            JPanel FoodPanel4 = new JPanel();
            FoodPanel4.setLayout(new GridLayout(2,2,20,20));
            FoodPanel4.setBorder(BorderFactory.createEmptyBorder(50,50,50,50));
            FoodPanel4.setBackground(food_padding);
            FoodPanel4.setPreferredSize(new Dimension(1000,500));
            FoodPanel4.setVisible(false);

            JPanel FoodPanel5 = new JPanel();
            FoodPanel5.setLayout(new GridLayout(2,2,20,20));
            FoodPanel5.setBorder(BorderFactory.createEmptyBorder(50,50,50,50));
            FoodPanel5.setBackground(food_padding);
            FoodPanel5.setPreferredSize(new Dimension(1000,500));
            FoodPanel5.setVisible(false);
    	
            JButton AppetizerButton = new JButton("APPETIZER");
            AppetizerButton.setBounds(50, 115, 240,60);
            AppetizerButton.setFont(new Font("Lucida Sans Unicode", Font.PLAIN, 18));
            AppetizerButton.setBackground(center_background);
            AppetizerButton.setBorder(BorderFactory.createEtchedBorder());
            AppetizerButton.setBorder(BorderFactory.createEmptyBorder());
            AppetizerButton.setFocusPainted(false);
            
            JButton MealButton = new JButton("MEAL");
            MealButton.setBounds(340, 115, 240,60);
            MealButton.setFont(new Font("Lucida Sans Unicode", Font.PLAIN, 18));
            MealButton.setBackground(back_button_color);
            MealButton.setBorder(BorderFactory.createEtchedBorder());
            MealButton.setBorder(BorderFactory.createEmptyBorder());
            MealButton.setFocusPainted(false);
            
            JButton SideDishButton = new JButton("SIDE DISH");
            SideDishButton.setBounds(630, 115, 240,60);
            SideDishButton.setFont(new Font("Lucida Sans Unicode", Font.PLAIN, 18));
            SideDishButton.setBackground(back_button_color);
            SideDishButton.setBorder(BorderFactory.createEtchedBorder());
            SideDishButton.setBorder(BorderFactory.createEmptyBorder());
            SideDishButton.setFocusPainted(false);
            
            JButton DessertButton = new JButton("DESSERT");
            DessertButton.setBounds(920, 115, 240,60);
            DessertButton.setFont(new Font("Lucida Sans Unicode", Font.PLAIN, 18));
            DessertButton.setBackground(back_button_color);
            DessertButton.setBorder(BorderFactory.createEtchedBorder());
            DessertButton.setBorder(BorderFactory.createEmptyBorder());
            DessertButton.setFocusPainted(false);
            
            JButton DrinksButton = new JButton("DRINKS");
            DrinksButton.setBounds(1210, 115, 240,60);
            DrinksButton.setFont(new Font("Lucida Sans Unicode", Font.PLAIN, 18));
            DrinksButton.setBackground(back_button_color);
            DrinksButton.setBorder(BorderFactory.createEtchedBorder());
            DrinksButton.setBorder(BorderFactory.createEmptyBorder());
            DrinksButton.setFocusPainted(false);
            
            AppetizerButton.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e) {
                        AppetizerButton.setBackground(center_background);
                        MealButton.setBackground(back_button_color);
                        SideDishButton.setBackground(back_button_color);
                        DessertButton.setBackground(back_button_color);
                        DrinksButton.setBackground(back_button_color);
                                
                        FoodPanel2.setVisible(false);
                        FoodPanel3.setVisible(false);
                        FoodPanel4.setVisible(false);
                        FoodPanel5.setVisible(false);
                        FoodPanel1.setVisible(true);
                }
            });
           
            MealButton.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e) {
                        AppetizerButton.setBackground(back_button_color);
                        MealButton.setBackground(center_background);
                        SideDishButton.setBackground(back_button_color);
                        DessertButton.setBackground(back_button_color);
                        DrinksButton.setBackground(back_button_color);
                        FoodPanel1.setVisible(false);
                        FoodPanel3.setVisible(false);
                        FoodPanel4.setVisible(false);
                        FoodPanel5.setVisible(false);
                        FoodPanel2.setVisible(true);
                }
            });

            SideDishButton.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e) {
                        AppetizerButton.setBackground(back_button_color);
                        MealButton.setBackground(back_button_color);
                        SideDishButton.setBackground(center_background);
                        DessertButton.setBackground(back_button_color);
                        DrinksButton.setBackground(back_button_color);
                        FoodPanel1.setVisible(false);
                        FoodPanel2.setVisible(false);
                        FoodPanel4.setVisible(false);
                        FoodPanel5.setVisible(false);
                        FoodPanel3.setVisible(true);
                }
            });

            DessertButton.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e) {
                        AppetizerButton.setBackground(back_button_color);
                        MealButton.setBackground(back_button_color);
                        SideDishButton.setBackground(back_button_color);
                        DessertButton.setBackground(center_background);
                        DrinksButton.setBackground(back_button_color);
                        FoodPanel1.setVisible(false);
                        FoodPanel2.setVisible(false);
                        FoodPanel3.setVisible(false);
                        FoodPanel5.setVisible(false);
                        FoodPanel4.setVisible(true);
                }
            });

            DrinksButton.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e) {
                        AppetizerButton.setBackground(back_button_color);
                        MealButton.setBackground(back_button_color);
                        SideDishButton.setBackground(back_button_color);
                        DessertButton.setBackground(back_button_color);
                        DrinksButton.setBackground(center_background);
                        FoodPanel1.setVisible(false);
                        FoodPanel2.setVisible(false);
                        FoodPanel3.setVisible(false);
                        FoodPanel4.setVisible(false);
                        FoodPanel5.setVisible(true);
                }
            });
            SpinnerNumberModel spinnerModel = new SpinnerNumberModel(0,0,item_stock,1);
            JSpinner AmountSpinner = new JSpinner(spinnerModel);
            AmountSpinner.setBounds(1100,720,100,55);
            Font font = AmountSpinner.getFont();
            int new_size = 20;
            AmountSpinner.setFont(font.deriveFont(Font.PLAIN, new_size));
                    
            ImageIcon iconLogo = new ImageIcon("C:/Users/emman/OneDrive/Desktop/sample_2/design_img/logo_final.png");
            //iconLogo.setBounds(10, 10, 40, 40);
            frame.setIconImage(iconLogo.getImage());
            ImageIcon iconA = new ImageIcon("C:/Users/emman/OneDrive/Desktop/sample_2/food_menu/egg salad.jpg");
            ImageIcon iconB = new ImageIcon("C:/Users/emman/OneDrive/Desktop/sample_2/food_menu/salmon balls (1).jpg");
            ImageIcon iconC = new ImageIcon("C:/Users/emman/OneDrive/Desktop/sample_2/food_menu/Dynamite-Cheese-Sticks-2-1200x900 (1).jpg");
            ImageIcon iconD = new ImageIcon("C:/Users/emman/OneDrive/Desktop/sample_2/food_menu/rellenong-hipon1 (1).jpg");
            
            
            JButton Appetizer1 = new JButton(" ", iconA);
            Appetizer1.setFocusPainted(false);
            if(menu.getStock_list().get(0) == 0){
                Appetizer1.setEnabled(false);
            }
            Appetizer1.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e) {
                    item_stock = menu.getStock_list().get(0);
                    SpinnerNumberModel snm = (SpinnerNumberModel) AmountSpinner.getModel();
                    snm.setMaximum(item_stock);
                    value = food_array[0];
                    price = price_array[0];
                    id = product_id[0];
                    itemName.setText("Item: " + value);
                    itemPrice.setText("Price: " + price + " php");}
                });
            JButton Appetizer2 = new JButton(" ", iconB);
            Appetizer2.setFocusPainted(false);
            if(menu.getStock_list().get(1) == 0){
                Appetizer2.setEnabled(false);
            }
            Appetizer2.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e) {
                    item_stock = menu.getStock_list().get(1);
                    SpinnerNumberModel snm = (SpinnerNumberModel) AmountSpinner.getModel();
                    snm.setMaximum(item_stock);
                    System.out.println(item_stock);
                    value = food_array[1];
                    price = price_array[1];
                    id = product_id[1];
                    itemName.setText("Item: " + value);
                    itemPrice.setText("Price: " + price + " php");}
                });
            JButton Appetizer3 = new JButton(" ", iconC);
            Appetizer3.setFocusPainted(false);
            if(menu.getStock_list().get(2) == 0){
                Appetizer3.setEnabled(false);
            }
            Appetizer3.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e) {
                    item_stock = menu.getStock_list().get(2);
                    SpinnerNumberModel snm = (SpinnerNumberModel) AmountSpinner.getModel();
                    snm.setMaximum(item_stock);
                    value = food_array[2];
                    price = price_array[2];
                    id = product_id[2];
                    itemName.setText("Item: " + value);
                    itemPrice.setText("Price: " + price + " php");}
                });
            JButton Appetizer4 = new JButton(" ", iconD);
            Appetizer4.setFocusPainted(false);
            if(menu.getStock_list().get(3) == 0){
                Appetizer4.setEnabled(false);
            }
            Appetizer4.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e) {
                    item_stock = menu.getStock_list().get(3);
                    SpinnerNumberModel snm = (SpinnerNumberModel) AmountSpinner.getModel();
                    snm.setMaximum(item_stock);
                    value = food_array[3];
                    price = price_array[3];
                    id = product_id[3];
                    itemName.setText("Item: " + value);
                    itemPrice.setText("Price: " + price + " php");}
                });
            
            ImageIcon iconE = new ImageIcon("C:/Users/emman/OneDrive/Desktop/sample_2/food_menu/beefsteak.jpg");
            ImageIcon iconF = new ImageIcon("C:/Users/emman/OneDrive/Desktop/sample_2/food_menu/whole-chicken_cooked (1).jpg");
            ImageIcon iconG = new ImageIcon("C:/Users/emman/OneDrive/Desktop/sample_2/food_menu/lutong-pinoy-bulalo-1200x900 (1).jpg");
            ImageIcon iconH = new ImageIcon("C:/Users/emman/OneDrive/Desktop/sample_2/food_menu/cb987cf56e9f1240460a65ef8dd125f1 (1).jpg");

            JButton Meal1 = new JButton(" ", iconE);
            Meal1.setFocusPainted(false);
            if(menu.getStock_list().get(4) == 0){
                Meal1.setEnabled(false);
            }
            Meal1.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e) {
                    item_stock = menu.getStock_list().get(4);
                    SpinnerNumberModel snm = (SpinnerNumberModel) AmountSpinner.getModel();
                    snm.setMaximum(item_stock);
                    value = food_array[4];
                    price = price_array[4];
                    id = product_id[4];
                    itemName.setText("Item: " + value);
                    itemPrice.setText("Price: " + price + " php");}
                });
            JButton Meal2 = new JButton(" ", iconF);
            Meal2.setFocusPainted(false);
            if(menu.getStock_list().get(5) == 0){
                Meal2.setEnabled(false);
            }
            Meal2.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e) {
                    item_stock = menu.getStock_list().get(5);
                    SpinnerNumberModel snm = (SpinnerNumberModel) AmountSpinner.getModel();
                    snm.setMaximum(item_stock);
                    value = food_array[5];
                    price = price_array[5];
                    id = product_id[5];
                    itemName.setText("Item: " + value);
                    itemPrice.setText("Price: " + price + " php");}
                });
            
            JButton Meal3 = new JButton(" ", iconG);
            Meal3.setFocusPainted(false);
            if(menu.getStock_list().get(6) == 0){
                Meal3.setEnabled(false);
            }
            Meal3.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e) {
                    item_stock = menu.getStock_list().get(6);
                    SpinnerNumberModel snm = (SpinnerNumberModel) AmountSpinner.getModel();
                    snm.setMaximum(item_stock);
                    value = food_array[6];
                    price = price_array[6];
                    id = product_id[6];
                    itemName.setText("Item: " + value);
                    itemPrice.setText("Price: " + price+ " php");}
                });
            
            JButton Meal4 = new JButton(" ", iconH);
            Meal4.setFocusPainted(false);
            if(menu.getStock_list().get(7) == 0){
                Meal4.setEnabled(false);
            }
            Meal4.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e) {
                    item_stock = menu.getStock_list().get(7);
                    SpinnerNumberModel snm = (SpinnerNumberModel) AmountSpinner.getModel();
                    snm.setMaximum(item_stock);
                    value = food_array[7];
                    price = price_array[7];
                    id = product_id[7];
                    itemName.setText("Item: " + value);
                    itemPrice.setText("Price: " + price+ " php");}
                });

            ImageIcon iconI = new ImageIcon("C:/Users/emman/OneDrive/Desktop/sample_2/food_menu/Spaghetti-with-Meat-Sauce-Recipe-Video (1).jpg");
            ImageIcon iconJ = new ImageIcon("C:/Users/emman/OneDrive/Desktop/sample_2/food_menu/Goat-cheese-pesto-linguine-pasta (1).jpg");
            ImageIcon iconK = new ImageIcon("C:/Users/emman/OneDrive/Desktop/sample_2/food_menu/French_Fries (1).jpg");
            ImageIcon iconL = new ImageIcon("C:/Users/emman/OneDrive/Desktop/sample_2/food_menu/gp4k2jro_burgers_625x300_20_June_22 (1).jpg");

            JButton SideDish1 = new JButton(" ", iconI);
            SideDish1.setFocusPainted(false);
            if(menu.getStock_list().get(8) == 0){
                SideDish1.setEnabled(false);
            }
            SideDish1.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e) {
                    item_stock = menu.getStock_list().get(8);
                    SpinnerNumberModel snm = (SpinnerNumberModel) AmountSpinner.getModel();
                    snm.setMaximum(item_stock);
                    value = food_array[8];
                    price = price_array[8];
                    id = product_id[8];
                    itemName.setText("Item: " + value);
                    itemPrice.setText("Price: " + price + " php");}
                });
            
            JButton SideDish2 = new JButton(" ", iconJ);
            SideDish2.setFocusPainted(false);
            if(menu.getStock_list().get(9) == 0){
                SideDish2.setEnabled(false);
            }
            SideDish2.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e) {
                    item_stock = menu.getStock_list().get(9);
                    SpinnerNumberModel snm = (SpinnerNumberModel) AmountSpinner.getModel();
                    snm.setMaximum(item_stock);
                    value = food_array[9];
                    price = price_array[9];
                    id = product_id[9];
                    itemName.setText("Item: " + value);
                    itemPrice.setText("Price: " + price+ " php");}
                });
            
            JButton SideDish3 = new JButton(" ", iconK);
            SideDish3.setFocusPainted(false);
            if(menu.getStock_list().get(10) == 0){
                SideDish3.setEnabled(false);
            }
            SideDish3.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e) {
                    item_stock = menu.getStock_list().get(10);
                    SpinnerNumberModel snm = (SpinnerNumberModel) AmountSpinner.getModel();
                    snm.setMaximum(item_stock);
                    value = food_array[10];
                    price = price_array[10];
                    id = product_id[10];
                    itemName.setText("Item: " + value);
                    itemPrice.setText("Price: " + price + " php");}
                });
            
            JButton SideDish4 = new JButton(" ", iconL);
            SideDish4.setFocusPainted(false);
            if(menu.getStock_list().get(11) == 0){
                SideDish4.setEnabled(false);
            }
            SideDish4.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e) {
                    item_stock = menu.getStock_list().get(11);
                    SpinnerNumberModel snm = (SpinnerNumberModel) AmountSpinner.getModel();
                    snm.setMaximum(item_stock);
                    value = food_array[11];
                    price = price_array[11];
                    id = product_id[11];
                    itemName.setText("Item: " + value);
                    itemPrice.setText("Price: " + price+ " php");}
                });
            

            ImageIcon iconM = new ImageIcon("C:/Users/emman/OneDrive/Desktop/sample_2/food_menu/cakeb (1).jpg");
            ImageIcon iconN = new ImageIcon("C:/Users/emman/OneDrive/Desktop/sample_2/food_menu/chocolate-cupcakes- (1).jpg");
            ImageIcon iconO = new ImageIcon("C:/Users/emman/OneDrive/Desktop/sample_2/food_menu/Chocolate-Ice-Cream_0 (1).jpg");
            ImageIcon iconP = new ImageIcon("C:/Users/emman/OneDrive/Desktop/sample_2/food_menu/cookies6 (1).jpg");

            JButton Dessert1 = new JButton(" ", iconM);
            Dessert1.setFocusPainted(false);
            if(menu.getStock_list().get(12) == 0){
                Dessert1.setEnabled(false);
            }
            Dessert1.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e) {
                    item_stock = menu.getStock_list().get(12);
                    SpinnerNumberModel snm = (SpinnerNumberModel) AmountSpinner.getModel();
                    snm.setMaximum(item_stock);
                    value = food_array[12];
                    price = price_array[12];
                    id = product_id[12];
                    itemName.setText("Item: " + value);
                    itemPrice.setText("Price: " + price + " php");}
                });
            
            JButton Dessert2 = new JButton(" ", iconN);
            Dessert2.setFocusPainted(false);
            if(menu.getStock_list().get(13) == 0){
                Dessert2.setEnabled(false);
            }
            Dessert2.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e) {
                    item_stock = menu.getStock_list().get(13);
                    SpinnerNumberModel snm = (SpinnerNumberModel) AmountSpinner.getModel();
                    snm.setMaximum(item_stock);
                    value = food_array[13];
                    price = price_array[13];
                    id = product_id[13];
                    itemName.setText("Item: " + value);
                    itemPrice.setText("Price: " + price + " php");}
                });
            
            JButton Dessert3 = new JButton(" ", iconO);
            Dessert3.setFocusPainted(false);
            if(menu.getStock_list().get(14) == 0){
                Dessert3.setEnabled(false);
            }
            Dessert3.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e) {
                    item_stock = menu.getStock_list().get(14);
                    SpinnerNumberModel snm = (SpinnerNumberModel) AmountSpinner.getModel();
                    snm.setMaximum(item_stock);
                    value = food_array[14];
                    price = price_array[14];
                    id = product_id[14];
                    itemName.setText("Item: " + value);
                    itemPrice.setText("Price: " + price + " php");}
                });
            
            JButton Dessert4 = new JButton(" ", iconP);
            Dessert4.setFocusPainted(false);
            if(menu.getStock_list().get(15) == 0){
                Dessert4.setEnabled(false);
            }
            Dessert4.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e) {
                    item_stock = menu.getStock_list().get(15);
                    SpinnerNumberModel snm = (SpinnerNumberModel) AmountSpinner.getModel();
                    snm.setMaximum(item_stock);
                    value = food_array[15];
                    price = price_array[15];
                    id = product_id[15];
                    itemName.setText("Item: " + value);
                    itemPrice.setText("Price: " + price + " php");}
                });


            ImageIcon iconQ = new ImageIcon("C:/Users/emman/OneDrive/Desktop/sample_2/food_menu/beer (1).jpg");
            ImageIcon iconR = new ImageIcon("C:/Users/emman/OneDrive/Desktop/sample_2/food_menu/coffee (1).jpg");
            ImageIcon iconS = new ImageIcon("C:/Users/emman/OneDrive/Desktop/sample_2/food_menu/cokes (1).jpg");
            ImageIcon iconT = new ImageIcon("C:/Users/emman/OneDrive/Desktop/sample_2/food_menu/frappe (1).jpg");

            JButton Drinks1 = new JButton(" ", iconQ);
            Drinks1.setFocusPainted(false);
            if(menu.getStock_list().get(16) == 0){
                Drinks1.setEnabled(false);
            }
            Drinks1.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e) {
                    item_stock = menu.getStock_list().get(16);
                    SpinnerNumberModel snm = (SpinnerNumberModel) AmountSpinner.getModel();
                    snm.setMaximum(item_stock);
                    value = food_array[16];
                    price = price_array[16];
                    id = product_id[16]; 
                    itemName.setText("Item: " + value);
                    itemPrice.setText("Price: " + price+ " php");}
                });
            
            JButton Drinks2 = new JButton(" ", iconR);
            Drinks2.setFocusPainted(false);
            if(menu.getStock_list().get(17) == 0){
                Drinks2.setEnabled(false);
            }
            Drinks2.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e) {
                    item_stock = menu.getStock_list().get(17);
                    SpinnerNumberModel snm = (SpinnerNumberModel) AmountSpinner.getModel();
                    snm.setMaximum(item_stock);
                    System.out.println(item_stock);
                    value = food_array[17];
                    price = price_array[17];
                    id = product_id[17];
                    itemName.setText("Item: " + value);
                    itemPrice.setText("Price: " + price+ " php");}
                });
            
            JButton Drinks3 = new JButton(" ", iconS);
            Drinks3.setFocusPainted(false);
            if(menu.getStock_list().get(18) == 0){
                Drinks3.setEnabled(false);
            }
            Drinks3.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e) {
                    item_stock = menu.getStock_list().get(18);
                    SpinnerNumberModel snm = (SpinnerNumberModel) AmountSpinner.getModel();
                    snm.setMaximum(item_stock);
                    value = food_array[18];
                    price = price_array[18];
                    id = product_id[18];
                    itemName.setText("Item: " + value);
                    itemPrice.setText("Price: " + price + " php");}
                });
            
            JButton Drinks4 = new JButton(" ", iconT);
            Drinks4.setFocusPainted(false);
            if(menu.getStock_list().get(19) == 0){
                Drinks4.setEnabled(false);
            }
            Drinks4.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e) {
                    item_stock = menu.getStock_list().get(19);
                    SpinnerNumberModel snm = (SpinnerNumberModel) AmountSpinner.getModel();
                    snm.setMaximum(item_stock);
                    value = food_array[19];
                    price = price_array[19];
                    id = product_id[19];
                    itemName.setText("Item: " + value);
                    itemPrice.setText("Price: " + price+ " php");}
                });
            
            
            
            Color orange_button = new Color(255, 220, 175);
            
            JButton AddOrderButton = new JButton("ADD ORDER");
            AddOrderButton.setBounds(1210, 720, 130,55);
            AddOrderButton.setEnabled(false);
            AddOrderButton.setBackground(orange_button);
            AddOrderButton.setBorder(BorderFactory.createEtchedBorder());
            AddOrderButton.setFocusPainted(false);
            
            JButton PayOrder = new JButton("PAY ORDER");
            PayOrder.setBounds(1350, 720, 130,55);
            PayOrder.setEnabled(false);
            PayOrder.setBackground(orange_button);
            PayOrder.setBorder(BorderFactory.createEtchedBorder());
            PayOrder.setFocusPainted(false);
            
            AmountSpinner.addChangeListener(new ChangeListener() {      
                @Override
                public void stateChanged(ChangeEvent e) {
                  
                  AddOrderButton.setEnabled(true);
                }
            });
            
            AddOrderButton.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e) {
                    int selectedValue = (int) AmountSpinner.getValue();
                    int selectedValue1;
                    AmountSpinner.setValue(((SpinnerNumberModel) AmountSpinner.getModel()).getMinimum());
                    if (selectedValue > 0) {
                        
                        PayOrder.setEnabled(true);
                        //for each loop then use id for base
                        food_check = search(menu.get_food_order(),value);
                        if (food_check == -1)
                        {
                        menu.get_food_order().add(value);
                        menu.get_food_id().add(id);
                        menu.get_quantity_order().add(selectedValue);
                        menu.get_total_price_order().add(selectedValue *price);
                        }
                        else
                        {
                            selectedValue1 = menu.get_quantity_order().get(food_check) + selectedValue;
                            menu.get_quantity_order().set(food_check, selectedValue1);
                            menu.get_total_price_order().set(food_check, selectedValue1 * price);
                        }
                        }
                        
                    }
                });
    	
            PayOrder.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e) {
                    try {
                        Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
                        Connection connection = DriverManager.getConnection(databaseURL);
                        System.out.println("Connected to MS Access database");

                        PreparedStatement sql = connection.prepareStatement("INSERT INTO Customer(CustomerID) VALUES("+customer_number+")");
                        sql.executeUpdate();
                        int current = customer_number;
                        int length = menu.get_food_order().size();
                        
                        for(int i = 0; i < length; i++){
                            PreparedStatement sql2 = connection.prepareStatement("INSERT INTO PurchaseOrder(CustomerID, ProductID, Quantity, Total) VALUES "
                                +"("+current+", "+menu.get_food_id().get(i)+", "+menu.get_quantity_order().get(i)+", "+menu.get_total_price_order().get(i)+")");
                            sql2.executeUpdate();
                        }
                        
                        
                        Statement pst = connection.createStatement();
                        ResultSet rs = pst.executeQuery("SELECT Product.Stock, PurchaseOrder.Quantity, PurchaseOrder.ProductID FROM Product, PurchaseOrder WHERE CustomerID = "+current+";");
                        
                        while(rs.next()){
                            int stock = rs.getInt("Stock");
                            //System.out.println(stock);
                            
                            int id = rs.getInt("ProductID");
                            
                            int consumed_stock = rs.getInt("Quantity");
                            //System.out.println(consumed_stock);
                            
                            int remaining_stock = stock - consumed_stock;
                            //System.out.println(remaining_stock);
                            
                            PreparedStatement sql3 = connection.prepareStatement("UPDATE Product SET Stock = "+remaining_stock+", ConsumedStocks = "+consumed_stock+" WHERE ProductID = "+id+";");
                            sql3.executeUpdate();
                        }
                        sql.close();
                        rs.close();
                        connection.close();
                    } catch(SQLException err){
                        err.printStackTrace();
                    } catch(ClassNotFoundException err){
                        err.printStackTrace();
                    }
                    frame.dispose(); 
                    ReceiptWindow receipt = new ReceiptWindow();
                }
            });
            
            JPanel UpperPanel = new JPanel();
            UpperPanel.setBackground(back);
            UpperPanel.setPreferredSize(new Dimension(100,175));


            JPanel LowerPanel = new JPanel();
            LowerPanel.setBackground(back);
            LowerPanel.setPreferredSize(new Dimension(100,100));

            JPanel CenterPanel = new JPanel();
            CenterPanel.setBackground(Color.WHITE);
            CenterPanel.setPreferredSize(new Dimension(100,100));

            
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
            frame.setSize(1550, 1000);
            frame.setLayout(new BorderLayout());
            frame.setVisible(true);
            frame.add(logo);
            frame.add(AppetizerButton);
            frame.add(MealButton);
            frame.add(SideDishButton);
            frame.add(DessertButton);
            frame.add(DrinksButton);
            frame.add(AddOrderButton);
            frame.add(PayOrder);
            frame.add(AmountSpinner);
            frame.add(QuantityLabel);
            frame.add(CustomerLabel);
            frame.add(restaurantName);
            frame.add(itemName);
            frame.add(itemPrice);
            
        
            frame.add(UpperPanel, BorderLayout.NORTH);
            frame.add(LowerPanel, BorderLayout.SOUTH);
            frame.add(CenterPanel, BorderLayout.CENTER);

            CenterPanel.add(FoodPanel1);
            CenterPanel.add(FoodPanel2);
            CenterPanel.add(FoodPanel3);
            CenterPanel.add(FoodPanel4);
            CenterPanel.add(FoodPanel5);

            FoodPanel1.add(Appetizer1);
            FoodPanel1.add(Appetizer2);
            FoodPanel1.add(Appetizer3);
            FoodPanel1.add(Appetizer4);

            FoodPanel2.add(Meal1);
            FoodPanel2.add(Meal2);
            FoodPanel2.add(Meal3);
            FoodPanel2.add(Meal4);

            FoodPanel3.add(SideDish1);
            FoodPanel3.add(SideDish2);
            FoodPanel3.add(SideDish3);
            FoodPanel3.add(SideDish4);

            FoodPanel4.add(Dessert1);
            FoodPanel4.add(Dessert2);
            FoodPanel4.add(Dessert3);
            FoodPanel4.add(Dessert4);

            FoodPanel5.add(Drinks1);
            FoodPanel5.add(Drinks2);
            FoodPanel5.add(Drinks3);
            FoodPanel5.add(Drinks4);
	}
}
