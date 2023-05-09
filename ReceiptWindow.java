import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReceiptWindow {
	
	
	ReceiptWindow(){
		
		JLabel CustomerLabel = new JLabel();
    	CustomerLabel.setText("CUSTOMER RECEIPT");
    	CustomerLabel.setBounds(0, 0 , 500,100);
    	CustomerLabel.setFont(new Font("Verdana", Font.BOLD, 45));
    	
    	JButton NextOrderButton = new JButton("NEXT CUSTOMER");
    	NextOrderButton.setBounds(1210, 720, 260,55);
    	
    	NextOrderButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				Myprogram window = new Myprogram();
			}
    	});
		
		
		JPanel CenterPanel = new JPanel();
		CenterPanel.setPreferredSize(new Dimension(100,100));
		CenterPanel.setBackground(Color.white);
		
		JPanel RightPanel = new JPanel();
		RightPanel.setPreferredSize(new Dimension(175,100));
		RightPanel.setBackground(Color.CYAN);
		
		JPanel LeftPanel = new JPanel();
		LeftPanel.setPreferredSize(new Dimension(175,100));
		LeftPanel.setBackground(Color.CYAN);
		
		Color color = new Color(95,196,203);
		
		JPanel UpperPanel = new JPanel();
		UpperPanel.setPreferredSize(new Dimension(100,150));
		UpperPanel.setBackground(color);
		
		JPanel LowerPanel = new JPanel();
		LowerPanel.setPreferredSize(new Dimension(100,50));
		LowerPanel.setBackground(color);
		
		JFrame frame = new JFrame("Myprogram");
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
	    frame.setSize(1550, 1000);
	    frame.setLayout(new BorderLayout());
	    frame.setVisible(true);
	    frame.add(RightPanel, BorderLayout.EAST);
        frame.add(LeftPanel, BorderLayout.WEST);
	    frame.add(UpperPanel, BorderLayout.NORTH);
        frame.add(CenterPanel, BorderLayout.CENTER);
        frame.add(LowerPanel, BorderLayout.SOUTH);
        LowerPanel.add(NextOrderButton);
        UpperPanel.add(CustomerLabel);
        
	}
}
