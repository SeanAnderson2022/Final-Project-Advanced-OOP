import javax.swing.*;
import java.sql.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class Myprogram {
        // temp list for menu
        public static String value = "";
        public static int price = 0;
        public static int id = 0;
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
        static int search(String[] food_array, String value){	
                int i;
                for (i = 0; i < 8; i++)
                {
                        if (food_array[i].equals(value))
                        {
                                return i;
                        }
                }
                return -1;	
        }
        
        // for qty
        public static ArrayList<Integer> quantity_order = new ArrayList<Integer>();
        // for order eme
        public static ArrayList<String> food_order = new ArrayList<String>();
        public static ArrayList<Integer> total_price_order = new ArrayList<Integer>();
        public static ArrayList<Integer> food_id = new ArrayList<Integer>();
        public static String databaseURL = "jdbc:ucanaccess://C:/Users/emman/OneDrive/Desktop/sample_2/database.accdb;memory=false";
        public static int customer_number;
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
	public Myprogram(){
            
            
            food_order.clear();
            quantity_order.clear();
            total_price_order.clear();
            get_rows();
            JLabel logo = new JLabel(); //JLabel Creation
            logo.setIcon(new ImageIcon("C:/Users/wyina/Downloads/logo.jpg")); //Sets the image to be displayed as an icon
            logo.setSize(50, 50);
            logo.setBounds(10, 25, 74, 50); //Sets the location of the image
            
            JLabel restaurantName = new JLabel();// create restaurant name text
            restaurantName.setText("ROUTE 66");
            restaurantName.setBounds(5, 0 , 500, 25);
            restaurantName.setFont(new Font("Verdana", Font.BOLD, 25));
            
            JLabel itemName = new JLabel();
            itemName.setText("Item: " + value);
            itemName.setBounds(5, 705, 500, 27);
            itemName.setFont(new Font("Verdana", Font.BOLD, 24));
            
            JLabel itemPrice = new JLabel();
            itemPrice.setText("Price: " + value);
            itemPrice.setBounds(5, 735, 500, 27);
            itemPrice.setFont(new Font("Verdana", Font.BOLD, 24));
            
            JLabel CustomerLabel = new JLabel();// create restaurant name text
            CustomerLabel.setText("CUSTOMER NO. " + customer_number);
            CustomerLabel.setBounds(500, 0 , 500,100);
            CustomerLabel.setFont(new Font("Verdana", Font.BOLD, 45));

            JLabel QuantityLabel = new JLabel();// create restaurant name text
            QuantityLabel.setText("Quantity");
            QuantityLabel.setBounds(950, 700 , 500,100);
            QuantityLabel.setFont(new Font("Verdana", Font.BOLD, 25));

            Color color = new Color(245,245,245);


            JPanel FoodPanel1 = new JPanel();
            FoodPanel1.setLayout(new GridLayout(2,2,20,20));
            FoodPanel1.setBorder(BorderFactory.createEmptyBorder(50,50,50,50));
            FoodPanel1.setBackground(color);
            FoodPanel1.setPreferredSize(new Dimension(1000,500));

            JPanel FoodPanel2 = new JPanel();
            FoodPanel2.setLayout(new GridLayout(2,2,20,20));
            FoodPanel2.setBorder(BorderFactory.createEmptyBorder(50,50,50,50));
            FoodPanel2.setBackground(color);
            FoodPanel2.setPreferredSize(new Dimension(1000,500));
            FoodPanel2.setVisible(false);

            JPanel FoodPanel3 = new JPanel();
            FoodPanel3.setLayout(new GridLayout(2,2,20,20));
            FoodPanel3.setBorder(BorderFactory.createEmptyBorder(50,50,50,50));
            FoodPanel3.setBackground(color);
            FoodPanel3.setPreferredSize(new Dimension(1000,500));
            FoodPanel3.setVisible(false);

            JPanel FoodPanel4 = new JPanel();
            FoodPanel4.setLayout(new GridLayout(2,2,20,20));
            FoodPanel4.setBorder(BorderFactory.createEmptyBorder(50,50,50,50));
            FoodPanel4.setBackground(color);
            FoodPanel4.setPreferredSize(new Dimension(1000,500));
            FoodPanel4.setVisible(false);

            JPanel FoodPanel5 = new JPanel();
            FoodPanel5.setLayout(new GridLayout(2,2,20,20));
            FoodPanel5.setBorder(BorderFactory.createEmptyBorder(50,50,50,50));
            FoodPanel5.setBackground(color);
            FoodPanel5.setPreferredSize(new Dimension(1000,500));
            FoodPanel5.setVisible(false);
    	
            JButton AppetizerButton = new JButton("Appetizer");
            AppetizerButton.setBounds(50, 100, 240,75);
            AppetizerButton.setFont(new Font("Verdana", Font.BOLD, 20));

            AppetizerButton.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e) {
                        FoodPanel2.setVisible(false);
                        FoodPanel3.setVisible(false);
                        FoodPanel4.setVisible(false);
                        FoodPanel5.setVisible(false);
                        FoodPanel1.setVisible(true);
                }
            });
    	
            JButton MealButton = new JButton("Meal");
            MealButton.setBounds(340, 100, 240,75);
            MealButton.setFont(new Font("Verdana", Font.BOLD, 20));
    	
            MealButton.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e) {
                        FoodPanel1.setVisible(false);
                        FoodPanel3.setVisible(false);
                        FoodPanel4.setVisible(false);
                        FoodPanel5.setVisible(false);
                        FoodPanel2.setVisible(true);
                }
            });
    	
            JButton SideDishButton = new JButton("Side Dish");
            SideDishButton.setBounds(630, 100, 240,75);
            SideDishButton.setFont(new Font("Verdana", Font.BOLD, 20));
    	
            SideDishButton.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e) {
                        FoodPanel1.setVisible(false);
                        FoodPanel2.setVisible(false);
                        FoodPanel4.setVisible(false);
                        FoodPanel5.setVisible(false);
                        FoodPanel3.setVisible(true);
                }
            });
    	
            JButton DessertButton = new JButton("Dessert");
            DessertButton.setBounds(920, 100, 240,75);
            DessertButton.setFont(new Font("Verdana", Font.BOLD, 20));
    	
            DessertButton.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e) {
                        FoodPanel1.setVisible(false);
                        FoodPanel2.setVisible(false);
                        FoodPanel3.setVisible(false);
                        FoodPanel5.setVisible(false);
                        FoodPanel4.setVisible(true);
                }
            });
    	
            JButton DrinksButton = new JButton("Drinks");
            DrinksButton.setBounds(1210, 100, 240,75);
            DrinksButton.setFont(new Font("Verdana", Font.BOLD, 20));

            DrinksButton.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e) {
                        FoodPanel1.setVisible(false);
                        FoodPanel2.setVisible(false);
                        FoodPanel3.setVisible(false);
                        FoodPanel4.setVisible(false);
                        FoodPanel5.setVisible(true);

                }
            });
            SpinnerNumberModel spinnerModel = new SpinnerNumberModel(0,0,100,1);
            JSpinner AmountSpinner = new JSpinner(spinnerModel);
            AmountSpinner.setBounds(1100,720,100,55);
            
            JButton AddOrderButton = new JButton("Add Order");
            AddOrderButton.setBounds(1210, 720, 130,55);

            AddOrderButton.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e) {
                    int selectedValue = (int) AmountSpinner.getValue();
                    if (selectedValue > 0) {
                        food_order.add(value);
                        food_id.add(id);
                        quantity_order.add(selectedValue);
                        total_price_order.add(selectedValue *price);}
                        
                }
            });
    	
            JButton PayOrder = new JButton("Pay Order");
            PayOrder.setBounds(1350, 720, 130,55);

            PayOrder.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e) {
                    // sa confirm button eme
                    try {
                        Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
                        Connection connection = DriverManager.getConnection(databaseURL);
                        System.out.println("Connected to MS Access database");
        
                        //samples variable
                        PreparedStatement sql = connection.prepareStatement("INSERT INTO Customer(CustomerID) VALUES("+customer_number+")");
                        sql.executeUpdate();
                        int current = customer_number;
                        int length = food_order.size();
                        /* debugging
                        System.out.println(length);
                        System.out.println(food_order);
                        System.out.println(food_id);
                        System.out.println(quantity_order);
                        System.out.println(total_price_order);*/
                        for(int i = 0; i < length; i++){
                        //product_ID = food_order.get(i);
                            PreparedStatement sql2 = connection.prepareStatement("INSERT INTO PurchaseOrder(CustomerID, ProductID, Quantity, Total) VALUES "
                                +"("+current+", "+food_id.get(i)+", "+quantity_order.get(i)+", "+total_price_order.get(i)+")");
                            sql2.executeUpdate();
                        }
                        connection.close();
                    } catch(SQLException err){
                        err.printStackTrace();
                    } catch(ClassNotFoundException err){
                        err.printStackTrace();
                    }

                    /*
                    void display_p(item){
                        Statement s = connection.executeQuery("SELECT Picture FROM Product WHERE ProductName ="+item);
                    }*/
                    new ReceiptWindow();
                }
            });

    	
            ImageIcon iconA = new ImageIcon("C:/Users/wyina/Downloads/egg salad.jpg");
            ImageIcon iconB = new ImageIcon("C:/Users/wyina/Downloads/salmon balls (1).jpg");
            ImageIcon iconC = new ImageIcon("C:/Users/wyina/Downloads/Dynamite-Cheese-Sticks-2-1200x900 (1).jpg");
            ImageIcon iconD = new ImageIcon("C:/Users/wyina/Downloads/rellenong-hipon1 (1).jpg");
            
            JButton Appetizer1 = new JButton(" ", iconA);
            Appetizer1.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e) {
                    value = food_array[0];
                    price = price_array[0];
                    id = product_id[0];
                    itemName.setText("Item: " + value);
                    itemPrice.setText("Price: " + price + " php");}
                });
            JButton Appetizer2 = new JButton(" ", iconB);
            Appetizer2.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e) {
                    value = food_array[1];
                    price = price_array[1];
                    id = product_id[1];
                    itemName.setText("Item: " + value);
                    itemPrice.setText("Price: " + price + " php");}
                });
            JButton Appetizer3 = new JButton(" ", iconC);
            Appetizer3.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e) {
                    value = food_array[2];
                    price = price_array[2];
                    id = product_id[2];
                    itemName.setText("Item: " + value);
                    itemPrice.setText("Price: " + price + " php");}
                });
            JButton Appetizer4 = new JButton(" ", iconD);
            Appetizer4.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e) {
                    value = food_array[3];
                    price = price_array[3];
                    id = product_id[3];
                    itemName.setText("Item: " + value);
                    itemPrice.setText("Price: " + price + " php");}
                });
            
            ImageIcon iconE = new ImageIcon("C:/Users/wyina/Downloads/beefsteak.jpg");
            ImageIcon iconF = new ImageIcon("C:/Users/wyina/Downloads/whole-chicken_cooked (1).jpg");
            ImageIcon iconG = new ImageIcon("C:/Users/wyina/Downloads/lutong-pinoy-bulalo-1200x900 (1).jpg");
            ImageIcon iconH = new ImageIcon("C:/Users/wyina/Downloads/cb987cf56e9f1240460a65ef8dd125f1 (1).jpg");

            JButton Meal1 = new JButton(" ", iconE);
            Meal1.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e) {
                    value = food_array[4];
                    price = price_array[4];
                    id = product_id[4];
                    itemName.setText("Item: " + value);
                    itemPrice.setText("Price: " + price + " php");}
                });
            JButton Meal2 = new JButton(" ", iconF);
            Meal2.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e) {
                    value = food_array[5];
                    price = price_array[5];
                    id = product_id[5];
                    itemName.setText("Item: " + value);
                    itemPrice.setText("Price: " + price + " php");}
                });
            
            JButton Meal3 = new JButton(" ", iconG);
            Meal3.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e) {
                    value = food_array[6];
                    price = price_array[6];
                    id = product_id[6];
                    itemName.setText("Item: " + value);
                    itemPrice.setText("Price: " + price+ " php");}
                });
            
            JButton Meal4 = new JButton(" ", iconH);
            Meal4.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e) {
                    value = food_array[7];
                    price = price_array[7];
                    id = product_id[7];
                    itemName.setText("Item: " + value);
                    itemPrice.setText("Price: " + price+ " php");}
                });

            ImageIcon iconI = new ImageIcon("C:/Users/wyina/Downloads/Spaghetti-with-Meat-Sauce-Recipe-Video (1).jpg");
            ImageIcon iconJ = new ImageIcon("C:/Users/wyina/Downloads/Goat-cheese-pesto-linguine-pasta (1).jpg");
            ImageIcon iconK = new ImageIcon("C:/Users/wyina/Downloads/French_Fries (1).jpg");
            ImageIcon iconL = new ImageIcon("C:/Users/wyina/Downloads/gp4k2jro_burgers_625x300_20_June_22 (1).jpg");

            JButton SideDish1 = new JButton(" ", iconI);
            SideDish1.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e) {
                    value = food_array[8];
                    price = price_array[8];
                    id = product_id[8];
                    itemName.setText("Item: " + value);
                    itemPrice.setText("Price: " + price + " php");}
                });
            
            JButton SideDish2 = new JButton(" ", iconJ);
            SideDish2.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e) {
                    value = food_array[9];
                    price = price_array[9];
                    id = product_id[9];
                    itemName.setText("Item: " + value);
                    itemPrice.setText("Price: " + price+ " php");}
                });
            
            JButton SideDish3 = new JButton(" ", iconK);
            SideDish3.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e) {
                    value = food_array[10];
                    price = price_array[10];
                    id = product_id[10];
                    itemName.setText("Item: " + value);
                    itemPrice.setText("Price: " + price + " php");}
                });
            
            JButton SideDish4 = new JButton(" ", iconL);
            SideDish4.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e) {
                    value = food_array[11];
                    price = price_array[11];
                    id = product_id[11];
                    itemName.setText("Item: " + value);
                    itemPrice.setText("Price: " + price+ " php");}
                });
            

            ImageIcon iconM = new ImageIcon("C:/Users/wyina/Downloads/cakeb (1).jpg");
            ImageIcon iconN = new ImageIcon("C:/Users/wyina/Downloads/chocolate-cupcakes- (1).jpg");
            ImageIcon iconO = new ImageIcon("C:/Users/wyina/Downloads/Chocolate-Ice-Cream_0 (1).jpg");
            ImageIcon iconP = new ImageIcon("C:/Users/wyina/Downloads/cookies6 (1).jpg");

            JButton Dessert1 = new JButton(" ", iconM);
            Dessert1.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e) {
                    value = food_array[12];
                    price = price_array[12];
                    id = product_id[12];
                    itemName.setText("Item: " + value);
                    itemPrice.setText("Price: " + price + " php");}
                });
            
            JButton Dessert2 = new JButton(" ", iconN);
            Dessert2.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e) {
                    value = food_array[13];
                    price = price_array[13];
                    id = product_id[13];
                    itemName.setText("Item: " + value);
                    itemPrice.setText("Price: " + price + " php");}
                });
            
            JButton Dessert3 = new JButton(" ", iconO);
            Dessert3.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e) {
                    value = food_array[14];
                    price = price_array[14];
                    id = product_id[14];
                    itemName.setText("Item: " + value);
                    itemPrice.setText("Price: " + price + " php");}
                });
            
            JButton Dessert4 = new JButton(" ", iconP);
            Dessert4.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e) {
                    value = food_array[15];
                    price = price_array[15];
                    id = product_id[15];
                    itemName.setText("Item: " + value);
                    itemPrice.setText("Price: " + price + " php");}
                });


            ImageIcon iconQ = new ImageIcon("C:/Users/wyina/Downloads/beer (1).jpg");
            ImageIcon iconR = new ImageIcon("C:/Users/wyina/Downloads/coffee (1).jpg");
            ImageIcon iconS = new ImageIcon("C:/Users/wyina/Downloads/cokes (1).jpg");
            ImageIcon iconT = new ImageIcon("C:/Users/wyina/Downloads/frappe (1).jpg");

            JButton Drinks1 = new JButton(" ", iconQ);
            Drinks1.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e) {
                    value = food_array[16];
                    price = price_array[16];
                    id = product_id[16];
                    itemName.setText("Item: " + value);
                    itemPrice.setText("Price: " + price+ " php");}
                });
            
            JButton Drinks2 = new JButton(" ", iconR);
            Drinks2.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e) {
                    value = food_array[17];
                    price = price_array[17];
                    id = product_id[17];
                    itemName.setText("Item: " + value);
                    itemPrice.setText("Price: " + price+ " php");}
                });
            
            JButton Drinks3 = new JButton(" ", iconS);
            Drinks3.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e) {
                    value = food_array[18];
                    price = price_array[18];
                    id = product_id[18];
                    itemName.setText("Item: " + value);
                    itemPrice.setText("Price: " + price + " php");}
                });
            
            JButton Drinks4 = new JButton(" ", iconT);
            Drinks4.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e) {
                    value = food_array[19];
                    price = price_array[19];
                    id = product_id[19];
                    itemName.setText("Item: " + value);
                    itemPrice.setText("Price: " + price+ " php");}
                });
            

            

            JPanel UpperPanel = new JPanel();
            UpperPanel.setBackground(Color.CYAN);
            UpperPanel.setPreferredSize(new Dimension(100,175));


            JPanel LowerPanel = new JPanel();
            LowerPanel.setBackground(Color.CYAN);
            LowerPanel.setPreferredSize(new Dimension(100,100));

            JPanel CenterPanel = new JPanel();
            CenterPanel.setBackground(Color.WHITE);
            CenterPanel.setPreferredSize(new Dimension(100,100));

            JFrame frame = new JFrame("Myprogram");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
            frame.setSize(1550, 1000);
            frame.setLayout(new BorderLayout());
            frame.setVisible(true);
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
            frame.add(logo);
        
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
   
    public static void main(String[] args) {
        new Myprogram();
    }
}
