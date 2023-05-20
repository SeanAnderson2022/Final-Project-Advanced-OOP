package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WelcomePage {

    public static void main(String[] args){
        // Create a JFrame
        JFrame frame = new JFrame("ROUTE 66");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        frame.pack();
        ImageIcon iconLogo = new ImageIcon("C:/Users/emman/OneDrive/Desktop/sample_2/design_img/logo_final.png");
        frame.setIconImage(iconLogo.getImage());
        
        JLabel logo = new JLabel(); //JLabel Creation
        ImageIcon imageIcon = new ImageIcon("C:/Users/emman/OneDrive/Desktop/sample_2/design_img/logo_final.png"); // load the image to a imageIcon
        Image image = imageIcon.getImage(); // transform it 
        Image newimg = image.getScaledInstance(290, 290,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
        imageIcon = new ImageIcon(newimg);  // transform it back
        logo.setIcon(imageIcon);
        logo.setBounds(620, 40, 300, 300); //Sets the location of the image

        JLabel label = new JLabel();// create restaurant name text
    	label.setText("WELCOME TO ROUTE 66");
    	label.setBounds(500, 320 , 800,100);
    	label.setFont(new Font("Verdana", Font.BOLD, 45));
	label.setForeground(Color.white);
        
        Color color = new Color(228,222,216);
        
        JButton OrderButton = new JButton("ORDER NOW!");
        
        OrderButton.setBackground(color);
        OrderButton.setBounds(650, 500, 240,75);
        OrderButton.setBorder(BorderFactory.createEtchedBorder());
        OrderButton.setFocusPainted(false);
        OrderButton.setFont(new Font("Verdana", Font.PLAIN, 20));
        OrderButton.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new CustomerOrder();
            }
        });

        // Create a custom JPanel for drawing the background image
        JPanel panel = new JPanel() {
            protected void paintComponent(Graphics g) {
                // Load the background image
                Image backgroundImage = new ImageIcon("C:/Users/emman/OneDrive/Desktop/sample_2/design_img/welcom_page.png").getImage();
                // Draw the background image
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), null);
            }
        };
        // for absolute positioning
        panel.setLayout(null);
        
        // Set the size and position of the custom panel
        panel.setPreferredSize(new Dimension(1550, 800));
        panel.setBounds(0, 0, 1550, 800);
        
        // Add other components to the custom panel or perform any desired customizations
        // Set the custom panel as the content pane of the JFrame
        frame.setContentPane(panel);
        
        // Pack the JFrame to adjust its size to the custom panel
        frame.pack();
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        // Set the JFrame size to match the custom panel size
        frame.setSize(panel.getSize());
        
        // Make the JFrame visible
        frame.add(logo);
        frame.setVisible(true);
        frame.add(OrderButton);
        frame.add(label);
        
        
        
        
        

       
        
        
    }
}
