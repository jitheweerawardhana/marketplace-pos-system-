package lk.jithendra.controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import lk.jithendra.dto.OrderDetailDto;
import lk.jithendra.dto.OrderDto;
import lk.jithendra.model.SystemModel;
import lk.jithendra.tm.ItemTm;
import javafx.scene.input.MouseEvent;
import lk.jithendra.tm.OrderTm;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

public class MainPage implements Initializable {
    public TableView<ItemTm> tblItem;
    public TableView<OrderTm> tblOrder;
    public TextField txtSearch;
    public Button searchBtn;
    public Label balence;
    public TextField txtAmount;
    public Button donebtn;
    public Button clearBtn;
    public Button orderBtn;
    public TextField txtQty;
    public Button backBtn;
    public Label totalLb;

    ItemTm itemTm;
    double total;
    double price;
    double subtotal;

    ArrayList<OrderTm> orderDetails = new ArrayList<>();
    private ArrayList<OrderDetailDto> orderDetailDtos = new ArrayList<>();
    ArrayList<OrderDetailDto> orderDetailsArray = new ArrayList<>();


    public void searchTxt(ActionEvent actionEvent) {
    }

    public void search(ActionEvent actionEvent) {
        String itemName = txtSearch.getText();

        ArrayList<ItemTm> itemTms = SystemModel.searchItem(itemName);

        tblItem.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("id"));
        tblItem.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        tblItem.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("itemName"));
        tblItem.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("category"));
        tblItem.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("price"));
        tblItem.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("qty"));

        tblItem.setItems(FXCollections.observableArrayList(itemTms));

    }

    public void done(ActionEvent actionEvent) {
        try {
            double amount = Double.parseDouble(txtAmount.getText());
            double bale = amount - subtotal;
            balence.setText(String.valueOf(bale));

            String format = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(new Date());
            boolean status = SystemModel.addItemDatabases(new OrderDto(format, subtotal, orderDetailsArray));

            if (status) {
                orderDetailsArray.clear();
                orderDetails.clear();
                tblOrder.getItems().clear();
                subtotal = 0;
                totalLb.setText("0.00");
                txtAmount.clear();
                System.out.println("Order Placed Successfully");
            }else {
                System.out.println("Order Placed failed");
            }

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Database Error");
        }
    }

    public void clear(ActionEvent actionEvent) {
        orderDetails.clear();
        tblOrder.getItems().clear();
        subtotal = 0;
        totalLb.setText("0.00");
        balence.setText("0.00");
    }

    public void order(ActionEvent actionEvent) {

        int qty = Integer.parseInt(txtQty.getText());
                  total = price * qty;
                  subtotal += total;

        totalLb.setText(String.valueOf(subtotal));
        OrderTm order = new OrderTm(itemTm.getItemName(),total);
        orderDetails.add(order);
        tblOrder.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("itemName"));
        tblOrder.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("price"));
        tblOrder.setItems(FXCollections.observableArrayList(orderDetails));



        int orderQty = Integer.parseInt(txtQty.getText());
        orderDetailsArray.add(new OrderDetailDto(itemTm.getId(),orderQty,total));
    }

    public void back(ActionEvent actionEvent) throws IOException {
        Parent mainPageRoot = FXMLLoader.load(getClass().getResource("/view/controllerPage.fxml"));
        Stage stage = (Stage) backBtn.getScene().getWindow();
        stage.setScene(new Scene(mainPageRoot));
        stage.setTitle("Controller Page");
        stage.show();
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        String itemName = txtSearch.getText();

        ArrayList<ItemTm> itemTms = SystemModel.searchItem(itemName);

        tblItem.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("id"));
        tblItem.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        tblItem.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("itemName"));
        tblItem.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("category"));
        tblItem.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("price"));
        tblItem.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("qty"));

        tblItem.setItems(FXCollections.observableArrayList(itemTms));

        tblItem.setOnMouseClicked((MouseEvent event) -> {
            if (event.getClickCount() == 1 && !tblItem.getSelectionModel().isEmpty()) {
                itemTm = tblItem.getSelectionModel().getSelectedItem();

                if(itemTm != null){
                    price = itemTm.getPrice();
                }
            }
        });



    }

}
