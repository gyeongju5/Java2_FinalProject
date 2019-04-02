package finalproject;

import content.Order;
import content.ReadFile;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class FinalProject extends Application {

    private LinkedList<Order> orderList = new LinkedList();
    private int sub = 0;
    private Label lblOrderID = new Label("Order ID: ");
    private TextField txtOrderID = new TextField();
    private Label lblName = new Label("Name: ");
    private TextField txtName = new TextField();
    private Label lblAddress = new Label("Address: ");
    private TextField txtAddress = new TextField();
    private Label lblCity = new Label("City: ");
    private TextField txtCity = new TextField();
    private Label lblProduct = new Label("Product: ");
    private TextField txtProduct = new TextField();
    private Label lblPrice = new Label("Price: ");
    private TextField txtPrice = new TextField();
    private Label lblQuantity = new Label("Quantity: ");
    private TextField txtQuantity = new TextField();
    private Button btnFirst = new Button("First");
    private Button btnNext = new Button("Next");
    private Button btnLast = new Button("Last");
    private Button btnPrevious = new Button("Previous");
    private Button btnNew = new Button("New");
    private Button btnUpdate = new Button("Update");
    private Button btnDelete = new Button("Delete");
    private Button btnSearch = new Button("Search");

    @Override
    public void start(Stage primaryStage) throws IOException {

        btnNext.setOnAction(new showNext());
        btnPrevious.setOnAction(new showPrevious());
        btnFirst.setOnAction(new showFirst());
        btnLast.setOnAction(new showLast());
        btnNew.setOnAction(new addNew());
        btnUpdate.setOnAction(new update());
        btnDelete.setOnAction(new delete());

        primaryStage.setOnShowing(new startProgram());
//        Scene scene = new Scene(root, 300, 250);
        primaryStage.setTitle("Final Project");
        primaryStage.setScene(getScene());
        primaryStage.show();
    }

    public static void main(String[] args) {

        launch(args);
    }

    private Scene getScene() {

        GridPane pane = new GridPane();
        pane.add(lblOrderID, 0, 0);
        pane.add(txtOrderID, 1, 0);
        pane.add(lblName, 2, 0);
        pane.add(txtName, 3, 0);
        pane.add(lblAddress, 4, 0);
        pane.add(txtAddress, 5, 0);
        pane.add(lblCity, 6, 0);
        pane.add(txtCity, 7, 0);
        pane.add(lblProduct, 0, 1);
        pane.add(txtProduct, 1, 1);
        pane.add(lblPrice, 2, 1);
        pane.add(txtPrice, 3, 1);
        pane.add(lblQuantity, 4, 1);
        pane.add(txtQuantity, 5, 1);
        pane.add(btnFirst, 0, 2);
        pane.add(btnPrevious, 1, 2);
        pane.add(btnNext, 2, 2);
        pane.add(btnLast, 3, 2);
        pane.add(btnNew, 0, 3);
        pane.add(btnUpdate, 1, 3);
        pane.add(btnDelete, 2, 3);
        pane.add(btnSearch, 0, 4);
        pane.setHgap(10);
        pane.setVgap(10);

        Scene scene = new Scene(pane, 1000, 300);

        return scene;
    }

    public class addNew implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent e) {
            txtOrderID.setText(null);
            txtName.setText(null);
            txtAddress.setText(null);
            txtCity.setText(null);
            txtProduct.setText(null);
            txtPrice.setText(null);
            txtQuantity.setText(null);
            txtOrderID.requestFocus();
        }
    }

    public class startProgram implements EventHandler<WindowEvent> {

        @Override
        public void handle(WindowEvent e) {
            try {
                ReadFile.loadFile("orders.dat", orderList);
                showData(sub);
            } catch (IOException ex) {

            }
        }
    }

    public void showData(int sub) {
        txtOrderID.setText(orderList.get(sub).getID());
        txtName.setText(orderList.get(sub).getName());
        txtAddress.setText(orderList.get(sub).getAddress());
        txtCity.setText(orderList.get(sub).getCity());
        txtProduct.setText(orderList.get(sub).getProduct());
        txtPrice.setText(Double.toString(orderList.get(sub).getPrice()));
        txtQuantity.setText(Integer.toString(orderList.get(sub).getQuantity()));

    }

    public class showNext implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent e) {
            sub = ((sub + 1) <= orderList.size() - 1) ? (sub + 1) : (orderList.size() - 1);
            showData(sub);
        }
    }

    public class showPrevious implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent e) {
            sub = (sub - 1) > 0 ? (sub - 1) : 0;
            showData(sub);
        }
    }

    public class showFirst implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent e) {
            showData(0);
        }
    }

    public class showLast implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent e) {
            showData(orderList.size() - 1);
        }
    }

    public class update implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent e) {
            Alert dlgConfirmation = new Alert(AlertType.CONFIRMATION);
            dlgConfirmation.setContentText("Do you want to UPDATE?");
            Optional<ButtonType> result = dlgConfirmation.showAndWait();
            String message = new String();
            Alert dlgMessage = new Alert(AlertType.INFORMATION);

            if (result.get() == ButtonType.OK) {
                message = "Confirm Clicked";
                orderList.get(sub).setID(txtOrderID.getText());
                orderList.get(sub).setName(txtName.getText());
                orderList.get(sub).setAddress(txtAddress.getText());
                orderList.get(sub).setCity(txtCity.getText());
                orderList.get(sub).setProduct(txtProduct.getText());
                orderList.get(sub).setPrice(Double.parseDouble(txtPrice.getText()));
                orderList.get(sub).setQuantity(Integer.parseInt(txtQuantity.getText()));

                dlgMessage.setContentText(message);
                dlgMessage.show();
            } else {
                message = "Cancel Clicked";
                dlgMessage.setContentText(message);
                dlgMessage.show();
            }
        }
    }

    public class delete implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent e) {
            Alert dlgConfirmation = new Alert(AlertType.CONFIRMATION);
            dlgConfirmation.setContentText("Do you want to delete this element?");
            Optional<ButtonType> result = dlgConfirmation.showAndWait();
            String message = new String();
            Alert dlgMessage = new Alert(AlertType.INFORMATION);

            if (result.get() == ButtonType.OK) {
                message = "Confirm Clicked";
                dlgMessage.setContentText(message);
                dlgMessage.show();
                orderList.remove(sub);
                showData(sub);
            } else {
                message = "Cancel Clicked";
                dlgMessage.setContentText(message);
                dlgMessage.show();
            }
        }
    }

}
