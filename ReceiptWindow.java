package ReceiptWindow;
import Order.Order;
import src.CustomerOrder;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.*;

public class ReceiptWindow{
    public ReceiptWindow(){
        Order menu = new Order();
        JFrame frame = new JFrame("Receipt");
        ImageIcon iconLogo = new ImageIcon("C:/Users/emman/OneDrive/Desktop/sample_2/design_img/logo_final.png");
        //iconLogo.setBounds(10, 10, 40, 40);
        frame.setIconImage(iconLogo.getImage());
        JPanel panel = new JPanel() {
            protected void paintComponent(Graphics g) {
                // Load the background image
                Image backgroundImage = new ImageIcon("C:/Users/emman/OneDrive/Desktop/sample_2/design_img/background_receipt.png").getImage();
                // Draw the background image
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), null);
            }
        };
        // Set the layout manager to null for absolute positioning
        panel.setLayout(null);
        
        // Set the size and position of the custom panel
        panel.setPreferredSize(new Dimension(1550, 800));
        panel.setBounds(0, 0, 1550, 800);
        
        JPanel CenterPanel = new JPanel();
        CenterPanel.setBounds(450, 170, 650,480);
        CenterPanel.setBackground(Color.WHITE);
        CenterPanel.setLayout(null);
        
        String[] col = {"QTY", "ITEM", "PRICE"};
        DefaultTableModel tableModel = new DefaultTableModel(col, 0);
        int size = menu.get_quantity_order().size();
        for(int i = 0; i < size; i++){
            Object[] row = new Object[col.length];
            row[0] = menu.get_quantity_order(i);
            row[1] = menu.get_food_order(i);
            row[2] = menu.get_total_price_order(i);
            tableModel.addRow(row);
        }
        
        JTable table = new JTable(tableModel);
        table.setShowGrid(false);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        table.getColumnModel().getColumn(0).setPreferredWidth(10);
        table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(1).setPreferredWidth(200);
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(JLabel.RIGHT);
        table.getColumnModel().getColumn(2).setPreferredWidth(20);
        table.getColumnModel().getColumn(2).setCellRenderer(rightRenderer);
        table.setRowHeight(35);
        table.setBackground(Color.WHITE);
        table.setForeground(Color.BLACK);
        table.setFont(new Font("Lucidas Sans Unicode", Font.PLAIN, 17));
        table.getTableHeader().setBackground(Color.WHITE);
        table.getTableHeader().setFont(new Font("Lucida Sans Unicode", Font.BOLD, 18));
        table.getTableHeader().setForeground(Color.BLACK);
        table.getTableHeader().setBorder(BorderFactory.createEmptyBorder());
        JScrollPane receipt = new JScrollPane(table);
        receipt.getViewport().setBackground(Color.WHITE);
        receipt.setBorder(BorderFactory.createEmptyBorder());
        receipt.setBounds(50, 20, 550, 300);
        CenterPanel.add(receipt);
        
        int total = menu.get_total_of_total_price();
        JLabel total_amount = new JLabel("TOTAL: " + total + " php");
        total_amount.setFont(new Font("Lucida Sans Unicode", Font.BOLD, 22));
        total_amount.setBounds(400, 400, 200, 47);
        total_amount.setForeground(Color.BLACK);
        CenterPanel.add(total_amount);

        JLabel CustomerLabel = new JLabel();
        CustomerLabel.setText("CUSTOMER RECEIPT");
        CustomerLabel.setBounds(550, 20 , 500,100);
        CustomerLabel.setFont(new Font("Verdana", Font.PLAIN, 40));
        
        Color orange_button = new Color(255, 220, 175);
        
        JButton NextOrderButton = new JButton("NEXT CUSTOMER");
        NextOrderButton.setFont(new Font("Verdana", Font.PLAIN, 15));
        NextOrderButton.setBounds(770, 700, 200,55);
        //NextOrderButton.setEnabled(false);
        NextOrderButton.setBackground(orange_button);
        NextOrderButton.setBorder(BorderFactory.createEtchedBorder());
        NextOrderButton.setFocusPainted(false);
        NextOrderButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new CustomerOrder();
            }
        });
        
        JButton ExitButton = new JButton("EXIT");
        ExitButton.setBounds(600, 700, 120, 55);
        ExitButton.setBackground(orange_button);
        ExitButton.setFont(new Font("Verdana", Font.PLAIN, 15));
        
        ExitButton.setBorder(BorderFactory.createEtchedBorder());
        ExitButton.setFocusPainted(false);
        ExitButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });

        Color color = new Color(95,196,203);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setSize(1550, 1000);
        frame.setLayout(new BorderLayout());
        frame.setVisible(true);
        frame.setContentPane(panel);
        frame.add(CustomerLabel);
        frame.add(NextOrderButton);
        frame.add(ExitButton);

        frame.add(CenterPanel);

    }
}

