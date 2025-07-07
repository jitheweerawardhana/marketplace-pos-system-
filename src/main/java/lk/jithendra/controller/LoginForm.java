package lk.jithendra.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginForm {
    public TextField txtUserName;
    public TextField txtPassword;
    public Button logingBtn;
    public Button clearBtn;

    public void login(ActionEvent actionEvent) throws IOException {
        String userName = txtUserName.getText();
        String password = txtPassword.getText();
        String username = "user";
        String pass = "1234";

        if(userName.equals(username) && password.equals(pass)) {
            Parent mainPageRoot = FXMLLoader.load(getClass().getResource("/View/controllerPage.fxml"));
            Stage stage = (Stage) logingBtn.getScene().getWindow();
            stage.setScene(new Scene(mainPageRoot));
            stage.setTitle("Main Page");
            stage.show();
        }
        else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Failed");
            alert.setHeaderText(null);
            alert.setContentText("Login Failed, Try Again!");
            alert.showAndWait();
        }
    }

    public void clear(ActionEvent actionEvent) {
        txtUserName.clear();
        txtPassword.clear();
    }
}
