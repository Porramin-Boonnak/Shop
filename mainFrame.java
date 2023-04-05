import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class mainFrame{
    static CardLayout c1 = new CardLayout();
    public static void main(String[] args)
    {
        JFrame jFrame = new JFrame();
        JPanel mainPanel =  new JPanel();
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        Storage  storage = new Storage();
        JButton storageButton = new JButton("BUY");
        JButton buyButton =  new JButton("Storage");
        buyFrame buyFrame  = new buyFrame(storage);
        StorageFrame storageFrame = new StorageFrame(storage);
        mainPanel.setLayout(c1);
        mainPanel.add(buyFrame,"1");
        mainPanel.add(storageFrame,"2");
        jFrame.add(storageButton,BorderLayout.NORTH);
        jFrame.add(mainPanel,BorderLayout.CENTER);
        storageButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                c1.show(mainPanel,"2");
                jFrame.remove(storageButton);
                jFrame.add(buyButton,BorderLayout.NORTH);
                storageFrame.showlable();
            }
            
        });
        buyButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                c1.show(mainPanel,"1");
                jFrame.remove(buyButton);
                jFrame.add(storageButton,BorderLayout.NORTH);
                buyFrame.showlable();
            }
            
        });
        c1.show(mainPanel,"1");
        jFrame.setSize(700,600);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setVisible(true);
    }
}