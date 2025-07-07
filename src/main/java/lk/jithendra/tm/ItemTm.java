package lk.jithendra.tm;

public class ItemTm {
    private int id;
    private String itemCode;
    private String itemName;
    private String category;
    private double price;
    private int qty;

    public ItemTm(int id, String itemCode, String itemName, String category, double price, int qty) {
        this.id = id;
        this.itemCode = itemCode;
        this.itemName = itemName;
        this.category = category;
        this.price = price;
        this.qty = qty;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }
}