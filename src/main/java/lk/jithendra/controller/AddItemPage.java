package lk.jithendra.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lk.jithendra.dto.ItemDto;
import lk.jithendra.model.SystemModel;

import java.io.IOException;

public class AddItemPage {
    public TextField txtItemCode;
    public TextField txtItemName;
    public TextField txtPrice;
    public TextField txtQty;
    public TextField txtCate;
    public Button backBtn;
    public Button addItemBtn;
    public Button dashboardBtn;
    public Button billBtn;
    public Button logOutBtn;
    public TableView viewtbl;
    public Label lblMassage;

    public void addItem(ActionEvent actionEvent) {
        String itemCode = txtItemCode.getText();
        String itemName = txtItemName.getText();
        String category = txtCate.getText();
        double price = Double.parseDouble(txtPrice.getText());
        int qty = Integer.parseInt(txtQty.getText());

        boolean status = SystemModel.addItem(new ItemDto(itemCode,itemName,category,price,qty));

        if (status){
            System.out.println("Item added successfully");
            lblMassage.setText("Item added successfully");
        }
        else {
            System.out.println("Item not added");
            lblMassage.setText("Item not added");
        }
    }

    public void back(ActionEvent actionEvent) throws IOException {
        txtItemCode.clear();
        txtItemName.clear();
        txtPrice.clear();
        txtQty.clear();
        txtCate.clear();
        lblMassage.setText("");
    }

    public void updatePage(ActionEvent actionEvent) throws IOException {
        Parent mainPageRoot = FXMLLoader.load(getClass().getResource("/View/updatePage.fxml"));
        Stage stage = (Stage) backBtn.getScene().getWindow();
        stage.setScene(new Scene(mainPageRoot));
        stage.setTitle("Main Page");
        stage.show();
    }

    public void dashboardPage(ActionEvent actionEvent) throws IOException {
        Parent mainPageRoot = FXMLLoader.load(getClass().getResource("/View/controllerPage.fxml"));
        Stage stage = (Stage) dashboardBtn.getScene().getWindow();
        stage.setScene(new Scene(mainPageRoot));
        stage.setTitle("Main Page");
        stage.show();
    }

    public void billPage(ActionEvent actionEvent) throws IOException {
        Parent mainPageRoot = FXMLLoader.load(getClass().getResource("/View/mainPage.fxml"));
        Stage stage = (Stage) billBtn.getScene().getWindow();
        stage.setScene(new Scene(mainPageRoot));
        stage.setTitle("Main Page");
        stage.show();
    }

    public void logOut(ActionEvent actionEvent) throws IOException {
        Parent mainPageRoot = FXMLLoader.load(getClass().getResource("/View/controllerPage.fxml"));
        Stage stage = (Stage) logOutBtn.getScene().getWindow();
        stage.setScene(new Scene(mainPageRoot));
        stage.setTitle("Main Page");
        stage.show();
    }
}
