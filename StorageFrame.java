import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class StorageFrame extends JPanel {
    private int i;
    private Storage storage;
    private JPanel panellable = new JPanel();
    public StorageFrame(Storage storage)
    {   
        this.storage= storage;
        panellable.setBackground(Color.PINK);
        JTextField textname = new JTextField("Name",10);
        JTextField textprice = new JTextField("Price",10);
        JTextField textquantity = new JTextField("quantity",10);
        JButton addButton = new JButton("ADD");
        JButton removeButton = new JButton("remove");
        JTextField textremove = new JTextField(5);
        JTextField addquantity = new JTextField(3);
        JTextField indexquantity = new JTextField(5);
        JButton addquantitynum = new JButton("add");
        panellable.setLayout(new BoxLayout(panellable,BoxLayout.Y_AXIS));
        JPanel panelnorth = new JPanel();
        panelnorth.setLayout(new BoxLayout(panelnorth,BoxLayout.X_AXIS));
        panelnorth.add(textname);
        panelnorth.add(textprice);
        panelnorth.add(textquantity);
        panelnorth.add(addButton);
        JPanel panelwest = new JPanel();
        panelwest.add(textremove);
        panelwest.add(removeButton);
        JPanel panelEast = new JPanel();
        panelEast.add(indexquantity);
        panelEast.add(addquantity);
        panelEast.add(addquantitynum);
        setLayout(new BorderLayout());
        add(panelEast,BorderLayout.EAST);
        add(panelwest,BorderLayout.WEST);
        add(panelnorth,BorderLayout.NORTH);
        add(panellable,BorderLayout.CENTER);
        addButton.addActionListener(new  ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                storage.addProduct(new Product(textname.getText(),Double.parseDouble(textprice.getText()), Integer.parseInt(textquantity.getText())));
                storage.addLables(new JLabel(String.format("%s %.2f THB %d",storage.getProduct().get(i).getname(),storage.getProduct().get(i).getprice(),storage.getProduct().get(i).getquantity())));
                showlable();
                i++;
            }
            
        });
        removeButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                storage.removeLable(search(textremove.getText()));
                storage.removeProduct(search(textremove.getText()));
                showlable();
                repaint();
                i--;
            }
            
        });
        addquantitynum.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                int num = search(indexquantity.getText());
                storage.setquantity(num,storage.getProduct().get(num).getquantity()+Integer.parseInt(addquantity.getText()));
                storage.setLabels(new JLabel(String.format("%s %.2f THB %d",storage.getProduct().get(num).getname(),storage.getProduct().get(num).getprice(),storage.getProduct().get(num).getquantity())),num);
                showlable();
            }
            
        });
    }
    public void showlable()
    {
        panellable.removeAll();
        for(int j=0;j<storage.getProduct().size();j++)
        {
            panellable.add(storage.getLabels(j));
            validate();
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
}
