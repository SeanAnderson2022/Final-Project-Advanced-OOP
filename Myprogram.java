import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Myprogram {
   
    public static void main(String[] args) {
    	String databaseURL = "jdbc:ucanaccess://C:/Users/emman/OneDrive/Desktop/sample_2/database.accdb";

    	JLabel logo = new JLabel(); //JLabel Creation
        logo.setIcon(new ImageIcon("C:/Users/wyina/Downloads/logo.jpg")); //Sets the image to be displayed as an icon
        logo.setSize(50, 50);
        logo.setBounds(10, 25, 74, 50); //Sets the location of the image
  
    	
    	JLabel restaurantName = new JLabel();// create restaurant name text
    	restaurantName.setText("ROUTE 66");
    	restaurantName.setBounds(0, 0 , 500, 25);
    	restaurantName.setFont(new Font("Verdana", Font.BOLD, 25));
    	
    	JLabel CustomerLabel = new JLabel();// create restaurant name text
    	CustomerLabel.setText("CUSTOMER NO.");
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
    	
    	
    	JButton AddOrderButton = new JButton("Add Order");
    	AddOrderButton.setBounds(1210, 720, 260,55);
    	// sa confirm button eme
        try {
            Connection connection = DriverManager.getConnection(databaseURL);
            System.out.println("Connected to MS Access database");
            
            //samples variable
            int var_customer = 10001;
            
            PreparedStatement sql = connection.prepareStatement("INSERT INTO Customer(CustomerID) VALUES("+var_customer+")");
           
            PreparedStatement sql2 = connection.prepareStatement("INSERT INTO PurchaseOrder(CustomerID, ProductID, Quantity, Total) VALUES "
                    + "("+var_customer+", 1002, 2, 100)");
            
            sql.executeUpdate();
            sql2.executeUpdate();
            
            connection.close();
        } catch(SQLException e){
            e.printStackTrace();
        }
    	
        
    	ImageIcon iconA = new ImageIcon("C:/Users/wyina/Downloads/egg salad.jpg");
    	ImageIcon iconB = new ImageIcon("C:/Users/wyina/Downloads/salmon balls (1).jpg");
    	ImageIcon iconC = new ImageIcon("C:/Users/wyina/Downloads/Dynamite-Cheese-Sticks-2-1200x900 (1).jpg");
    	ImageIcon iconD = new ImageIcon("C:/Users/wyina/Downloads/rellenong-hipon1 (1).jpg");
        
    	JButton Appetizer1 = new JButton(" ", iconA);
    	JButton Appetizer2 = new JButton(" ", iconB);
    	JButton Appetizer3 = new JButton(" ", iconC);
    	JButton Appetizer4 = new JButton(" ", iconD);
    	
    	ImageIcon iconE = new ImageIcon("C:/Users/wyina/Downloads/beefsteak.jpg");
    	ImageIcon iconF = new ImageIcon("C:/Users/wyina/Downloads/whole-chicken_cooked (1).jpg");
    	ImageIcon iconG = new ImageIcon("C:/Users/wyina/Downloads/lutong-pinoy-bulalo-1200x900 (1).jpg");
    	ImageIcon iconH = new ImageIcon("C:/Users/wyina/Downloads/cb987cf56e9f1240460a65ef8dd125f1 (1).jpg");
        
    	JButton Meal1 = new JButton(" ", iconE);
    	JButton Meal2 = new JButton(" ", iconF);
    	JButton Meal3 = new JButton(" ", iconG);
    	JButton Meal4 = new JButton(" ", iconH);
    	
    	ImageIcon iconI = new ImageIcon("C:/Users/wyina/Downloads/Spaghetti-with-Meat-Sauce-Recipe-Video (1).jpg");
    	ImageIcon iconJ = new ImageIcon("C:/Users/wyina/Downloads/Goat-cheese-pesto-linguine-pasta (1).jpg");
    	ImageIcon iconK = new ImageIcon("C:/Users/wyina/Downloads/French_Fries (1).jpg");
    	ImageIcon iconL = new ImageIcon("C:/Users/wyina/Downloads/gp4k2jro_burgers_625x300_20_June_22 (1).jpg");
        
    	JButton SideDish1 = new JButton(" ", iconI);
    	JButton SideDish2 = new JButton(" ", iconJ);
    	JButton SideDish3 = new JButton(" ", iconK);
    	JButton SideDish4 = new JButton(" ", iconL);
    	
    	ImageIcon iconM = new ImageIcon("C:/Users/wyina/Downloads/cakeb (1).jpg");
    	ImageIcon iconN = new ImageIcon("C:/Users/wyina/Downloads/chocolate-cupcakes- (1).jpg");
    	ImageIcon iconO = new ImageIcon("C:/Users/wyina/Downloads/Chocolate-Ice-Cream_0 (1).jpg");
    	ImageIcon iconP = new ImageIcon("C:/Users/wyina/Downloads/cookies6 (1).jpg");
        
    	JButton Dessert1 = new JButton(" ", iconM);
    	JButton Dessert2 = new JButton(" ", iconN);
    	JButton Dessert3 = new JButton(" ", iconO);
    	JButton Dessert4 = new JButton(" ", iconP);
    	
    	
    	ImageIcon iconQ = new ImageIcon("C:/Users/wyina/Downloads/beer (1).jpg");
    	ImageIcon iconR = new ImageIcon("C:/Users/wyina/Downloads/coffee (1).jpg");
    	ImageIcon iconS = new ImageIcon("C:/Users/wyina/Downloads/cokes (1).jpg");
    	ImageIcon iconT = new ImageIcon("C:/Users/wyina/Downloads/frappe (1).jpg");
        
    	JButton Drinks1 = new JButton(" ", iconQ);
    	JButton Drinks2 = new JButton(" ", iconR);
    	JButton Drinks3 = new JButton(" ", iconS);
    	JButton Drinks4 = new JButton(" ", iconT);
    	
    	JSpinner AmountSpinner = new JSpinner();
    	AmountSpinner.setBounds(1100,720,100,55);

    	
    	
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
        frame.add(AmountSpinner);
        frame.add(QuantityLabel);
        frame.add(CustomerLabel);
        frame.add(restaurantName);
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
}

