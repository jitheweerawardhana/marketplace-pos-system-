package lk.jithendra.dto;

public class OrderDetailDto {
    private int itemID;
    private int orderQty;
    private double price;

    public OrderDetailDto(int itemID, int orderQty, double price) {
        this.itemID = itemID;
        this.orderQty = orderQty;
        this.price = price;
    }

    public int getItemID() {
        return itemID;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    public int getOrderQty() {
        return orderQty;
    }

    public void setOrderQty(int orderQty) {
        this.orderQty = orderQty;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
