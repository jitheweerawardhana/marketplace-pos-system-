package lk.jithendra.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lk.jithendra.dto.ItemDto;
import lk.jithendra.dto.ItemDto2;
import lk.jithendra.model.SystemModel;
import lk.jithendra.tm.ItemTm;

import java.io.IOException;
import java.util.ArrayList;

public class UpdatePage {
    public Button searchBtn;
    public TextField txtSearch;
    public Button backBtn;
    public TextField txtItemCode;
    public TextField txtItemName;
    public TextField txtCat;
    public TextField txtPrice;
    public TextField txtQty;
    public Button editBtn;
    public Button dashboardBtn;
    public Button adminBtn;
    public Button billPageBtn;
    public Button itemaddPageBtn;
    public Button orderBtn;
    public Button logOutBtn;
    public Button updatePageBtn;

    public void back(ActionEvent actionEvent) throws IOException {
        Parent mainPageRoot = FXMLLoader.load(getClass().getResource("/view/controllerPage.fxml"));
        Stage stage = (Stage) backBtn.getScene().getWindow();
        stage.setScene(new Scene(mainPageRoot));
        stage.setTitle("Controller Page");
        stage.show();
    }

    public void search(ActionEvent actionEvent) {
        String itemCode = txtSearch.getText();
        ItemDto2 itemDto2 = SystemModel.searchItem2(itemCode);

        if (itemDto2 != null) {
            txtItemCode.setText(itemDto2.getItemCode());
            txtItemName.setText(itemDto2.getItemName());
            txtCat.setText(itemDto2.getCategory());
            txtPrice.setText(String.valueOf(itemDto2.getPrice()));
            txtQty.setText(String.valueOf(itemDto2.getQty()));
        } else {
            System.out.println("Item not found.");
        }
    }


    public void searchTxt(ActionEvent actionEvent) {
    }

    public void update(ActionEvent actionEvent) {
    }

    public void dasgboard(ActionEvent actionEvent) {
    }

    public void billPage(ActionEvent actionEvent) throws IOException {
        Parent mainPageRoot = FXMLLoader.load(getClass().getResource("/View/mainPage.fxml"));
        Stage stage = (Stage) billPageBtn.getScene().getWindow();
        stage.setScene(new Scene(mainPageRoot));
        stage.setTitle("Main Page");
        stage.show();
    }

    public void updatePage(ActionEvent actionEvent) throws IOException {

        String itemCode = txtItemCode.getText();
        String itemName = txtItemName.getText();
        String category = txtCat.getText();
        double price = Integer.parseInt(txtPrice.getText());
        int qty = Integer.parseInt(txtQty.getText());

        boolean status = SystemModel.updateItemDatabases(new ItemDto(itemCode,itemName,category,price,qty));

        if (status) {
            System.out.println("update success");
        }
        else {
            System.out.println("update failed");
        }
    }

    public void logOut(ActionEvent actionEvent) {
    }

    public void addItemPage(ActionEvent actionEvent) throws IOException {
        Parent mainPageRoot = FXMLLoader.load(getClass().getResource("/View/addItemPage.fxml"));
        Stage stage = (Stage) itemaddPageBtn.getScene().getWindow();
        stage.setScene(new Scene(mainPageRoot));
        stage.setTitle("Main Page");
        stage.show();
    }
}
