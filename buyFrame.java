import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class buyFrame extends JPanel {
    private JPanel panellable = new JPanel();
    private JPanel buylablepanel = new JPanel();
    private Storage storage;
    private ArrayList<JLabel> productbuylLabels = new ArrayList<JLabel>();
    private ArrayList<Integer> productbuy = new  ArrayList<Integer>();
    private  ArrayList<String> productname = new   ArrayList<String>();
    private int i;
    private double totalprice;
    public buyFrame(Storage storage)
    {
        this.storage=storage;
        panellable.setBackground(Color.PINK);
        panellable.setLayout(new BoxLayout(panellable,BoxLayout.Y_AXIS));
        panellable.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        buylablepanel.setLayout(new BoxLayout(buylablepanel,BoxLayout.Y_AXIS));
        buylablepanel.setBackground(Color.GREEN);
        JTextField textnumproduct = new JTextField(5);
        JTextField textquantity = new JTextField(3);
        JButton addbuyproduct = new JButton("add");
        JTextField textyourprice = new JTextField("Your price",5);
        JButton buyButton = new JButton("BUY");
        JTextField textremove =  new JTextField(5);
        JButton removeButton = new JButton("remove");

        JLabel labelcart = new JLabel("In The Cart");
        JLabel labeltotalprice = new JLabel();

        JPanel panelcenter= new JPanel();
        JPanel bPanel = new JPanel();
        JLabel label = new JLabel("Shop");
        bPanel.add(textyourprice);
        bPanel.add(buyButton);
        panelcenter.setLayout(new BorderLayout());
        panelcenter.add(label,BorderLayout.NORTH);
        panelcenter.add(panellable,BorderLayout.CENTER);
        panelcenter.add(bPanel,BorderLayout.SOUTH);
        

        JPanel panelEast =  new JPanel();
        panelEast.setLayout(new BorderLayout());
        JPanel panelnouthofeast = new JPanel();
        panelnouthofeast.add(textnumproduct);
        panelnouthofeast.add(textquantity);
        panelnouthofeast.add(addbuyproduct);
        JPanel panelsouthofeast = new JPanel();
        panelsouthofeast.add(textremove);
        panelsouthofeast.add(removeButton);
        panelEast.add(panelnouthofeast,BorderLayout.NORTH);
        panelEast.add(panelsouthofeast,BorderLayout.SOUTH);
        
        JPanel panelWest = new JPanel();
        panelWest.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panelWest.setLayout(new BorderLayout());
        panelWest.add(labelcart,BorderLayout.NORTH);
        panelWest.add(buylablepanel,BorderLayout.CENTER);
        panelWest.add(labeltotalprice,BorderLayout.SOUTH);
        setLayout(new BorderLayout());
        add(panelEast,BorderLayout.EAST);
        add(panelcenter,BorderLayout.CENTER);
        add(panelWest,BorderLayout.WEST);
        addbuyproduct.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                int num = search(textnumproduct.getText());
                int quantity = Integer.parseInt(textquantity.getText());
                if(quantity<=storage.getquantity(num)){
                productbuy.add(quantity);
                productname.add(storage.getProduct().get(num).getname());
                productbuylLabels.add(new JLabel(String.format("%s %.2f THB %d",storage.getProduct().get(num).getname(),storage.getProduct().get(num).getprice(),productbuy.get(i))));
                totalprice = totalprice+(storage.getProduct().get(num).getprice()*productbuy.get(i));
                labeltotalprice.setText("Totalprice "+Double.toString(totalprice)+" THB");
                i++;
                showproductbuylLabels();
                }
                else
                {
                    JOptionPane.showMessageDialog(null,"Not enough quantity!","BUY Page",JOptionPane.PLAIN_MESSAGE);
                }
            }
            
        });
        buyButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if(Double.parseDouble(textyourprice.getText())>=totalprice)
                {
                    for(int c=0;c<productbuy.size();c++)
                    {
                        storage.setquantity(search(productname.get(c)),storage.getquantity(search(productname.get(c)))-productbuy.get(c));
                        storage.setLabels(new JLabel(String.format("%s %.2f THB %d",storage.getProduct().get(search(productname.get(c))).getname(),storage.getProduct().get(search(productname.get(c))).getprice(),storage.getProduct().get(search(productname.get(c))).getquantity())), search(productname.get(c)));
                    }
                    buylablepanel.removeAll();
                    productbuy.removeAll(productbuy);
                    productname.removeAll(productname);
                    productbuylLabels.removeAll(productbuylLabels);
                    totalprice=0;
                    i=0;
                    textyourprice.setText("Your price");
                    labeltotalprice.setText("Totalprice 0 THB");
                    JOptionPane.showMessageDialog(null,"Succeed!","BUY Page",JOptionPane.PLAIN_MESSAGE);
                    showlable();
                }
                else
                {
                    JOptionPane.showMessageDialog(null,"Not enough money!","BUY Page",JOptionPane.PLAIN_MESSAGE);
                }
            }
            
        });
        removeButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                totalprice = totalprice-(productbuy.get(searchproductname(textremove.getText()))*storage.getProduct().get(search(textremove.getText())).getprice());
                productbuy.remove(searchproductname(textremove.getText()));
                productbuylLabels.remove(searchproductname(textremove.getText()));
                productname.remove(searchproductname(textremove.getText()));
                labeltotalprice.setText("Totalprice "+Double.toString(totalprice)+" THB");
                showproductbuylLabels();
                repaint();
                i--;
            }
            
        });
    }
    public void showproductbuylLabels()
    {
        buylablepanel.removeAll();
        for(int j=0;j<productbuylLabels.size();j++)
        {
            buylablepanel.add(productbuylLabels.get(j));
        }
        repaint();
    }
    public void showlable()
    {
        panellable.removeAll();
        for(int j=0;j<storage.getProduct().size();j++)
        {
            panellable.add(storage.getLabels(j));
        }
        repaint();
    }
    public int search(String name)
    {
        int k=0;
        for(k=0;k<storage.getProduct().size();k++)
        {
            if(storage.getProduct().get(k).getname().equals(name))
            {
                return k;
            }
        }
        return k;
    }
    public int searchproductname(String name)
    {
        int k=0;
        for(k=0;k<productname.size();k++)
        {
            if(productname.get(k).equals(name))
            {
                return k;
            }
        }
        return k;
    }
}
