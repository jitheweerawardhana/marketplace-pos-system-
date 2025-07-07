package lk.jithendra.model;

import lk.jithendra.db.DBConnection;
import lk.jithendra.dto.ItemDto;
import lk.jithendra.dto.ItemDto2;
import lk.jithendra.dto.OrderDetailDto;
import lk.jithendra.dto.OrderDto;
import lk.jithendra.tm.ItemTm;

import java.sql.*;
import java.util.ArrayList;

public class SystemModel {

    public static ArrayList<ItemTm> searchItem(String itemName) {

        ArrayList<ItemTm> itemTmList = new ArrayList<>();

        try{

            Connection connection = DBConnection.getDBConnection().getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM SupermarketItems WHERE item_name like ? or item_code like ? or category LIKE ? ");
            preparedStatement.setObject(1, "%"+itemName+"%");
            preparedStatement.setObject(2, "%"+itemName+"%");
            preparedStatement.setObject(3, "%"+itemName+"%");

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                itemTmList.add(new ItemTm(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getDouble(5),
                        resultSet.getInt(6)
                ));
            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return itemTmList;
    }

    public static boolean addItem(ItemDto itemDto){
        boolean status = false;

        try{
            Connection connection = DBConnection.getDBConnection().getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO supermarketitems( item_code,item_name,category,price,quantity) VALUES (?,?,?,?,?)");
            preparedStatement.setObject(1,itemDto.getItemCode());
            preparedStatement.setObject(2,itemDto.getItemName());
            preparedStatement.setObject(3,itemDto.getCategory());
            preparedStatement.setObject(4,itemDto.getPrice());
            preparedStatement.setObject(5,itemDto.getQty());

            int i = preparedStatement.executeUpdate();

            if(i>0){
                status = true;
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return status;
    }
    public static ItemDto2 searchItem2(String itemCode) {
        ItemDto2 itemDto2 = null;

        try {
            Connection connection = DBConnection.getDBConnection().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM SupermarketItems WHERE item_code LIKE ?");
            preparedStatement.setObject(1, itemCode);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                try {
                    itemDto2 = new ItemDto2(
                            resultSet.getString(2),
                            resultSet.getString(3), // item_name
                            resultSet.getString(4), // category
                            resultSet.getDouble(5), // price
                            resultSet.getInt(6)     // quantity
                    );
                } catch (NumberFormatException e) {
                    System.err.println("Invalid price value for item: " + resultSet.getString(1));
                    throw new RuntimeException("Invalid data format in database", e);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return itemDto2;
    }



    public static boolean addItemDatabases(OrderDto orderDto) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getDBConnection().getConnection();

        try {
            connection.setAutoCommit(false);


            PreparedStatement stm1 = connection.prepareStatement(
                    "INSERT INTO orders(date, totalprice) VALUES (?, ?)",
                    Statement.RETURN_GENERATED_KEYS
            );
            stm1.setObject(1, orderDto.getDate());
            stm1.setObject(2, orderDto.getsubTotal());

            int orderSave = stm1.executeUpdate();

            if (orderSave > 0) {
                ResultSet generatedKeys = stm1.getGeneratedKeys();

                if (generatedKeys.next()) {
                    int orderId = generatedKeys.getInt(1);


                    for (OrderDetailDto dto : orderDto.getOrderDetailDtos()) {
                        PreparedStatement stm2 = connection.prepareStatement(
                                "INSERT INTO orderdetails(oID, itemId, qty, price) VALUES (?, ?, ?, ?)"
                        );
                        stm2.setObject(1, orderId);
                        stm2.setObject(2, dto.getItemID());
                        stm2.setObject(3, dto.getOrderQty());
                        stm2.setObject(4, dto.getPrice());

                        int orderDetailSave = stm2.executeUpdate();

                        if (orderDetailSave > 0) {
                            PreparedStatement stm3 = connection.prepareStatement(
                                    "UPDATE supermarketitems SET quantity = quantity - ? WHERE id = ?"
                            );
                            stm3.setObject(1, dto.getOrderQty());
                            stm3.setObject(2, dto.getItemID());

                            int orderDetailUpdate = stm3.executeUpdate();

                            if (orderDetailUpdate <= 0) {

                                connection.rollback();
                                connection.setAutoCommit(true);
                                return false;
                            }
                        } else {

                            connection.rollback();
                            connection.setAutoCommit(true);
                            return false;
                        }

                    }


                    connection.commit();
                    connection.setAutoCommit(true);
                    return true;
                }
            }
            connection.rollback();
            connection.setAutoCommit(true);
            return false;
        } catch (Exception e) {
            connection.rollback();
            throw e;
        } finally {
            connection.setAutoCommit(true);
        }
    }

    public  static boolean updateItemDatabases(ItemDto itemDto) {
        boolean status = false;

        try{
            Connection connection = DBConnection.getDBConnection().getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement("update supermarketitems set  item_code=?,item_name=?, category=?,  price=?, quantity=? where item_code=?");
            preparedStatement.setObject(1, itemDto.getItemCode());
            preparedStatement.setObject(2, itemDto.getItemName());
            preparedStatement.setObject(3, itemDto.getCategory());
            preparedStatement.setObject(4, itemDto.getPrice());
            preparedStatement.setObject(5, itemDto.getQty());

            int i = preparedStatement.executeUpdate();

            if(i>0){
                status = true;
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return status;
    }

}
