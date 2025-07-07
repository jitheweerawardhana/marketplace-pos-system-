package lk.jithendra.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class ControllerPage {

    public Button dashboardBtn;
    public Button adminBtn;
    public Button billPageBtn;
    public Button itemaddPageBtn;
    public Button orderBtn;
    public Button logOutBtn;
    public Button updatePageBtn;

    public void adminPage(ActionEvent actionEvent) {
    }

    public void billPage(ActionEvent actionEvent) throws IOException {
        Parent mainPageRoot = FXMLLoader.load(getClass().getResource("/View/mainPage.fxml"));
        Stage stage = (Stage) billPageBtn.getScene().getWindow();
        stage.setScene(new Scene(mainPageRoot));
        stage.setTitle("Main Page");
        stage.show();
    }

    public void addItemPage(ActionEvent actionEvent) throws IOException {
        Parent mainPageRoot = FXMLLoader.load(getClass().getResource("/View/addItemPage.fxml"));
        Stage stage = (Stage) itemaddPageBtn.getScene().getWindow();
        stage.setScene(new Scene(mainPageRoot));
        stage.setTitle("Main Page");
        stage.show();
    }

    public void updatePage(ActionEvent actionEvent) throws IOException {
        Parent mainPageRoot = FXMLLoader.load(getClass().getResource("/View/updatePage.fxml"));
        Stage stage = (Stage) updatePageBtn.getScene().getWindow();
        stage.setScene(new Scene(mainPageRoot));
        stage.setTitle("Main Page");
        stage.show();
    }
}
