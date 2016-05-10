
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author dttung
 */
public class AddUser extends Application{
    private TextField txtUsername = new TextField();
    private PasswordField txtPassword = new PasswordField();
    private Button btnAdd = new Button("Add");
    private Button btnClear = new Button("Clear");
    private Label lblMessage = new Label("");
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception 
    {
        // Create UI
        GridPane gridPane = new GridPane();
        gridPane.setHgap(6);
        gridPane.setVgap(6);
        gridPane.add(new Label("Username: "), 0, 0);
        gridPane.add(txtUsername, 1, 0);
        gridPane.add(new Label("Password:"), 0, 1);
        gridPane.add(txtPassword, 1, 1);
        gridPane.add(btnAdd,1,2);
        gridPane.add(btnClear, 1, 2);
        gridPane.add(lblMessage, 1, 3);
        // Set properties for UI
        gridPane.setAlignment(Pos.CENTER);
        txtUsername.setAlignment(Pos.BOTTOM_LEFT);
        txtPassword.setAlignment(Pos.BOTTOM_LEFT);
        btnAdd.setAlignment(Pos.CENTER_LEFT);
        btnClear.setAlignment(Pos.CENTER_LEFT);
        GridPane.setHalignment(btnAdd, HPos.LEFT);
        GridPane.setHalignment(btnClear, HPos.RIGHT);
        
        // Create a scene and place it in the stage
        Scene scene = new Scene(gridPane, 400, 250);
        primaryStage.setTitle("Add User"); // Set title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage
        
        btnAdd.setOnAction(e->addUser());
        btnClear.setOnAction(e->clear());
    }
    private void addUser()
    {
        String usersFile = "users.txt";        
        try {
            FileWriter fileWriter = new FileWriter(usersFile, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(txtUsername.getText());
            bufferedWriter.write("|");
            bufferedWriter.write(txtPassword.getText());
            bufferedWriter.newLine();
            bufferedWriter.close();
        } catch (IOException ex) {
            Logger.getLogger(AddUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        lblMessage.setText("User " + txtUsername.getText() + " has been created.");
        txtUsername.setText("");
        txtPassword.setText("");
    }
    private void clear()
    {
        lblMessage.setText("");
        txtUsername.setText("");
        txtPassword.setText("");
    }
}
