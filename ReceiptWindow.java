package ReceiptWindow;
import Order.Order;
import src.Myprogram;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.*;

public class ReceiptWindow{
    public ReceiptWindow(){
        Order menu = new Order();
        JFrame frame = new JFrame("Myprogram");
        
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
        table.setRowHeight(25);
        table.setBackground(Color.BLACK);
        table.setForeground(Color.WHITE);
        table.setFont(new Font("Lucidas Sans Unicode", Font.PLAIN, 17));
        table.getTableHeader().setBackground(Color.BLACK);
        table.getTableHeader().setFont(new Font("Lucida Sans Unicode", Font.BOLD, 18));
        table.getTableHeader().setForeground(Color.WHITE);
        table.getTableHeader().setBorder(BorderFactory.createEtchedBorder());
        
        
        
        JScrollPane receipt = new JScrollPane(table);
        receipt.setBorder(BorderFactory.createEmptyBorder());
        receipt.getViewport().setBackground(Color.BLACK);
        
        
        int total = menu.get_total_of_total_price();
        JLabel total_amount = new JLabel("TOTAL: " + total + " php");
        total_amount.setFont(new Font("Lucida Sans Unicode", Font.BOLD, 22));
        total_amount.setForeground(Color.WHITE);
        
        JPanel second_receipt = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        second_receipt.setPreferredSize(new Dimension(450, 50));
        second_receipt.setBackground(Color.BLACK);
        
        
                
        JLabel CustomerLabel = new JLabel();
        CustomerLabel.setText("CUSTOMER RECEIPT");
        CustomerLabel.setBounds(550, 20 , 500,100);
        CustomerLabel.setFont(new Font("Verdana", Font.BOLD, 40));
        
        Color orange_button = new Color(255, 220, 175);
        
        JButton NextOrderButton = new JButton("NEXT CUSTOMER");
        NextOrderButton.setFont(new Font("Lucida Sans Unicode", Font.BOLD, 15));
        NextOrderButton.setBounds(720, 710, 200,55);
        //NextOrderButton.setEnabled(false);
        NextOrderButton.setBackground(orange_button);
        NextOrderButton.setBorder(BorderFactory.createEtchedBorder());
        NextOrderButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new Myprogram();
            }
        });
        JButton void_order = new JButton("CANCEL ORDER");
        void_order.setBounds(950, 710, 170, 55);
        void_order.setBackground(orange_button);
        void_order.setFont(new Font("Lucida Sans Unicode", Font.BOLD, 15));
        void_order.setBorder(BorderFactory.createEtchedBorder());
        void_order.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                int cno = menu.getCurrent_customer_no();
                System.out.println("Current: " + cno);
                Myprogram.delete_current_order(cno);
                frame.dispose();
                new Myprogram();
            }
        });
        
        JButton ExitButton = new JButton("EXIT");
        ExitButton.setBounds(570, 710, 120, 55);
        ExitButton.setBackground(orange_button);
        ExitButton.setFont(new Font("Lucida Sans Unicode", Font.BOLD, 15));
        ExitButton.setBorder(BorderFactory.createEtchedBorder());
        ExitButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });
        JPanel CenterPanel = new JPanel();
        CenterPanel.setPreferredSize(new Dimension(510,100));
        CenterPanel.setBackground(Color.black);
        
        JPanel RightPanel = new JPanel();
        RightPanel.setPreferredSize(new Dimension(520,100));
        RightPanel.setBackground(Color.CYAN);

        JPanel LeftPanel = new JPanel();
        LeftPanel.setPreferredSize(new Dimension(520,100));
        LeftPanel.setBackground(Color.CYAN);

        Color color = new Color(95,196,203);

        JPanel UpperPanel = new JPanel();
        UpperPanel.setPreferredSize(new Dimension(100,150 ));
        UpperPanel.setBackground(color);

        JPanel LowerPanel = new JPanel();
        LowerPanel.setPreferredSize(new Dimension(100,100));
        LowerPanel.setBackground(color);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setSize(1550, 1000);
        frame.setLayout(new BorderLayout());
        frame.setVisible(true);
        frame.add(CustomerLabel);
        frame.add(NextOrderButton);
        frame.add(ExitButton);
        frame.add(void_order);
        frame.add(RightPanel, BorderLayout.EAST);
        frame.add(LeftPanel, BorderLayout.WEST);
        frame.add(UpperPanel, BorderLayout.NORTH);
        frame.add(CenterPanel, BorderLayout.CENTER);
        frame.add(LowerPanel, BorderLayout.SOUTH);
        CenterPanel.add(receipt, BorderLayout.NORTH);
        CenterPanel.add(second_receipt, BorderLayout.SOUTH);
        second_receipt.add(total_amount);
    }
}
