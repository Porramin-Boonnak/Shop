import java.util.ArrayList;
import javax.swing.JLabel;

public  class Storage  {
    private ArrayList<Product> product = new ArrayList<Product>(); 
    private ArrayList<JLabel> labels = new ArrayList<JLabel>();
    public Storage(){
        
    }
    public Storage(Product product,JLabel labels){
        this.product.add(product);
        this.labels.add(labels);
    }
    public ArrayList<Product> getProduct(){
        return product;
    }
    public JLabel getLabels(int index){
        return labels.get(index);
    }
    public void setProduct(Product product,int i){
        this.product.set(i, product);
    }
    public void setLabels(JLabel label,int i){
        this.labels.set(i, label);
    }
    public void addProduct(Product product){
        this.product.add(product);
    }
    public void addLables(JLabel label){
        this.labels.add(label);
    }
    public void removeProduct(int i){
        this.product.remove(i);
    }
    public void removeLable(int i){
        this.labels.remove(i);
    }
    public int getquantity(int i)
    {
        return this.product.get(i).getquantity();
    }
    public void setquantity(int i,int quantity){
        this.product.get(i).setquantity(quantity);
    }
}
