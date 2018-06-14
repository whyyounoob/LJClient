package View;

import Arguments.Base;
import Arguments.LoginArgument;
import Controller.XMLLJClient;
import Returns.UserData;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class LogWindow extends Application {

    private Pane pane;
    private Label usernameLabel;
    private Label passwordLabel;
    private TextField usernameField;
    private PasswordField passwordField;
    private Label homeCatalog;
    private Button loginbtn;

    @Override
    public void start(final Stage enterStage) {
        enterStage.setResizable(false);

        pane = new Pane();
        usernameLabel = new Label("Username: ");
        passwordLabel = new Label("Password: ");
        usernameField = new TextField();
        passwordField = new PasswordField();
        homeCatalog = new Label("LiveJournal");
        loginbtn = new Button("Log In");
        pane.getChildren().addAll(homeCatalog, usernameLabel, passwordLabel, usernameField, passwordField,
                loginbtn);
        pane.setStyle("-fx-background-color: aliceblue");

        homeCatalog.setStyle("-fx-font-family: sans-serif;\n" +
                "     -fx-font-size: 28;\n" +
                "     -fx-fill: linear-gradient(to top, cyan, dodgerblue);\n" +
                "     -fx-effect: dropshadow(gaussian, dodgerblue, 15, 0.25, 5, 5);");
        homeCatalog.setLayoutX(100);
        homeCatalog.setLayoutY(20);

        usernameLabel.setFont(new Font("Times New Roman", 19));
        usernameLabel.setLayoutX(40);
        usernameLabel.setLayoutY(70);

        passwordLabel.setFont(new Font("Times New Roman", 19));
        passwordLabel.setLayoutX(45);
        passwordLabel.setLayoutY(110);

        usernameField.setPrefSize(200, 30);
        usernameField.setLayoutX(130);
        usernameField.setLayoutY(65);
        usernameField.setPromptText("Enter your username...");


        passwordField.setPrefSize(200, 30);
        passwordField.setLayoutX(130);
        passwordField.setLayoutY(105);
        passwordField.setPromptText("Enter your password...");

        loginbtn.setPrefSize(180, 30);
        loginbtn.setLayoutX(100);
        loginbtn.setLayoutY(150);
        loginbtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent event) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Ooops");
                alert.setHeaderText(null);
                if (usernameField.getText().equals("") || passwordField.getText().equals("")) {

                    alert.setContentText("Fill the all fields, please");
                    alert.showAndWait();
                } else {
                    Base.getInstance().setData(usernameField.getText(), passwordField.getText());
                    LoginArgument loginArgument = new LoginArgument();
                    UserData userData = XMLLJClient.getInstance().login(loginArgument);
                    if (userData == null) {
                        alert.setContentText("Something went wrong, check info and your connection");
                        alert.showAndWait();
                    } else {

                        enterStage.setScene(new MainWindow(new Pane(), userData));
                    }

                }
            }
        });


        Scene enterScene = new Scene(pane, 380, 250);
        enterStage.setScene(enterScene);
        enterStage.show();
    }

    public void showingEW() {
        launch();
    }
}