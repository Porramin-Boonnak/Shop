public class Product {
    private String name;
    private Double price;
    private int quantity;
    public Product(String name,Double price,int quantity){
        this.name=name;
        this.price=price;
        this.quantity=quantity;
    }
    public String getname()
    {
        return name;
    }
    public Double getprice()
    {
        return price;
    }
    public int getquantity(){
        return quantity;
    }
    public void setquantity(int quantity){
        this.quantity = quantity;
    }
}
