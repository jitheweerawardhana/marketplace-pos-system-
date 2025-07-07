package lk.jithendra.dto;

import java.util.ArrayList;

public class OrderDto {
    private String orderDate;
    private double subTotal;

    private ArrayList<OrderDetailDto> orderDetailDtos;

    public OrderDto(String orderDate, double subTotal, ArrayList<OrderDetailDto> orderDetailDtos) {
        this.orderDate = orderDate;
        this.subTotal = subTotal;
        this.orderDetailDtos = orderDetailDtos;
    }

    public String getDate() {
        return orderDate;
    }

    public void setDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public double getsubTotal() {
        return subTotal;
    }

    public void setsubTotal(double subTotal) {
        this.subTotal = subTotal;
    }

    public ArrayList<OrderDetailDto> getOrderDetailDtos() {
        return orderDetailDtos;
    }

    public void setOrderDetailDtos(ArrayList<OrderDetailDto> orderDetailDtos) {
        this.orderDetailDtos = orderDetailDtos;
    }
}
