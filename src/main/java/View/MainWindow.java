package View;

import Arguments.*;
import Controller.XMLLJClient;
import Returns.BlogEntry;
import Returns.Friend;
import Returns.PostResult;
import Returns.UserData;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;


public class MainWindow extends Scene {

    private Friend[] friendsData;
    private UserData userData;
    private BlogEntry[] eventsData;

    private ScrollPane eventsScroll = new ScrollPane();
    private Pane singleEvent = new Pane();
    private ScrollPane friendsScroll = new ScrollPane();
    private VBox friendPane = new VBox();
    private VBox eventsPane = new VBox();
    private Pane userPane = new Pane();
    private Pane postEventPane = new Pane();


    private Label userNameLabel = new Label("");
    private Button refreshButton = new Button("Refresh");
    private Button addFriendButton = new Button("Add friend");
    private Button newEventButton = new Button("New event");
    private ImageView defaultFoto = new ImageView();
    private Label userIdLabel = new Label();
    private Label titleEventLabel = new Label("Title: ");
    private TextField titleField = new TextField();
    private Label eventLabel = new Label("Your event: ");
    private TextArea eventArea = new TextArea();
    private Button postButton = new Button("Post event");

    public MainWindow(Pane mainPane, UserData userData) {
        super(mainPane, 700, 600);
        this.userData = userData;
        setPostEventPane();
        mainPane.getChildren().addAll(friendsScroll, eventsScroll, userPane, postEventPane, singleEvent);
        setEventsPane();

        eventsScroll.setLayoutX(250);
        eventsScroll.setLayoutY(userData.getDefaultImage().getHeight());
        eventsScroll.setStyle("-fx-border-style: solid;" +
                "-fx-border-color: black;");
        eventsScroll.setPrefSize(450, 600 - userData.getDefaultImage().getHeight());
        eventsScroll.setVisible(true);
        eventsScroll.setContent(eventsPane);
        eventsScroll.setPannable(true);

        singleEvent.setLayoutX(250);
        singleEvent.setLayoutY(userData.getDefaultImage().getHeight());
        singleEvent.setStyle("-fx-border-style: solid;" +
                "-fx-border-color: black;");
        singleEvent.setPrefSize(450, 600 - userData.getDefaultImage().getHeight());


        singleEvent.setVisible(false);

        postEventPane.setPrefSize(450, 600 - userData.getDefaultImage().getHeight());
        postEventPane.setLayoutX(250);
        postEventPane.setLayoutY(userData.getDefaultImage().getHeight());
        postEventPane.setStyle("-fx-border-style: solid;" +
                "-fx-border-color: black;");
        postEventPane.setVisible(false);
        friendsScroll.setContent(friendPane);
        friendsScroll.setStyle("-fx-border-color: black;" +
                "-fx-border-style: solid;");
        friendsScroll.setLayoutX(0);
        friendsScroll.setLayoutY(userData.getDefaultImage().getHeight());
        friendsScroll.setPrefSize(250, 600 - userData.getDefaultImage().getHeight());
        friendsScroll.setPannable(true);
        newEventButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                eventsScroll.setVisible(false);
                singleEvent.setVisible(false);
                postEventPane.setVisible(true);
            }
        });
        setUserPane();
        setFriendsPane();

    }

    private void setUserPane() {
        defaultFoto.setImage(userData.getDefaultImage());
        double height = userData.getDefaultImage().getHeight();
        double width = userData.getDefaultImage().getWidth();
        userPane.getChildren().addAll(refreshButton, userNameLabel, newEventButton, addFriendButton, defaultFoto, userIdLabel);
        userPane.setLayoutX(0);
        userPane.setLayoutY(0);
        userPane.setPrefSize(700, height);
        userPane.setStyle("-fx-border-color: black;\n"
                + "-fx-border-style: solid;\n");

        defaultFoto.setLayoutX(0);
        defaultFoto.setLayoutY(0);
        userNameLabel.setText("Welcome, " + userData.getFullname());
        userIdLabel.setText("#" + userData.getUserId());
        userNameLabel.setLayoutX(width + 10);
        userNameLabel.setLayoutY(5);
        userNameLabel.setStyle("-fx-font-size: 21;" +
                "-fx-font-family: 'Comic Sans MS';");
        userIdLabel.setLayoutX(width + 10);
        userIdLabel.setLayoutY(25);
        userIdLabel.setStyle("-fx-font-size: 15;" +
                "-fx-font-family: 'Comic Sans MS';");

        refreshButton.setLayoutX(610);
        refreshButton.setLayoutY(10);
        refreshButton.setStyle("-fx-pref-height: 25;" +
                "-fx-pref-width: 80");
        refreshButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                eventsPane.getChildren().clear();
                setEventsPane();
                eventsScroll.setVisible(true);
                singleEvent.setVisible(false);
                postEventPane.setVisible(false);
            }
        });

        addFriendButton.setLayoutX(510);
        addFriendButton.setLayoutY(10);
        addFriendButton.setStyle("-fx-pref-height: 25;" +
                "-fx-pref-width: 80");
        addFriendButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                TextInputDialog dialog = new TextInputDialog("");
                dialog.setTitle("Add friend");
                dialog.setHeaderText("Username of new friend:");
                dialog.setContentText("Username: ");

                Optional<String> result = dialog.showAndWait();
                result.ifPresent(usName -> {
                    int check = XMLLJClient.getInstance().editFriend(new EditFriendsArgument("add", usName));

                    if (check == 1) {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Success!");
                        alert.setContentText("Friend successfully added");
                        alert.showAndWait();
                    } else if (check == 0) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Ooops...");
                        alert.setContentText("Something went wrong.");
                        alert.showAndWait();
                    }
                    friendPane.getChildren().clear();
                    setFriendsPane();
                });

            }
        });

        newEventButton.setLayoutX(510);
        newEventButton.setLayoutY(45);
        newEventButton.setStyle("-fx-pref-height: 25;" +
                "-fx-pref-width: 180;");
    }

    private void setFriendsPane() {

        friendsData = XMLLJClient.getInstance().getFriends(new GetFriendsArgument()).getFriends();
        for (int i = 0; i < friendsData.length; i++) {
            Pane pane = new Pane();
            Label fullNameLabel = new Label();
            Label usernameLabel = new Label();
            Label typeLabel = new Label();
            Label bdayLabel = new Label();
            Button deleteButton = new Button("Delete");

            pane.setStyle("-fx-border-color: black;" +
                    "-fx-border-style: solid;" +
                    "-fx-pref-width: 233;" +
                    "-fx-pref-height: 130;");

            fullNameLabel.setText(friendsData[i].getFullname());
            fullNameLabel.setStyle("-fx-font-size: 17;");
            fullNameLabel.setLayoutX(5);
            fullNameLabel.setLayoutY(5);

            usernameLabel.setText("Username: " + friendsData[i].getUsername());
            usernameLabel.setStyle("-fx-font-size: 13");
            usernameLabel.setLayoutX(8);
            usernameLabel.setLayoutY(30);

            if (friendsData[i].getBday() != null) {
                bdayLabel.setText("B-Day: " + friendsData[i].getBday());
            } else {
                bdayLabel.setText("B-Day: ---");
            }
            bdayLabel.setStyle("-fx-font-size: 13;");
            bdayLabel.setLayoutX(8);
            bdayLabel.setLayoutY(50);

            if (friendsData[i].getType() != null) {
                typeLabel.setText("Type: " + friendsData[i].getType());
            } else {
                typeLabel.setText("Type: ---");
            }
            typeLabel.setStyle("-fx-font-size: 13;");
            typeLabel.setLayoutX(8);
            typeLabel.setLayoutY(70);
            deleteButton.setLayoutX(8);
            deleteButton.setLayoutY(90);
            deleteButton.setPrefSize(100, 30);
            deleteButton.setOnAction(new EditFriend("delete", friendsData[i].getUsername()));

            pane.getChildren().addAll(deleteButton, fullNameLabel, usernameLabel, bdayLabel, typeLabel);
            friendPane.getChildren().add(pane);

        }
    }

    private void setPostEventPane() {
        postEventPane.getChildren().addAll(titleEventLabel, titleField, eventLabel, eventArea, postButton);

        titleEventLabel.setStyle("-fx-font-size: 20");
        titleEventLabel.setLayoutX(10);
        titleEventLabel.setLayoutY(5);
        titleField.setLayoutX(10);
        titleField.setLayoutY(38);
        titleField.setStyle("-fx-pref-width: 350;" +
                "-fx-pref-height: 30;" +
                "-fx-font-size: 15;");

        eventLabel.setStyle("-fx-font-size: 20;");
        eventLabel.setLayoutX(10);
        eventLabel.setLayoutY(68);

        eventArea.setLayoutX(10);
        eventArea.setLayoutY(95);
        eventArea.setPrefSize(380, 300);

        postButton.setLayoutX(10);
        postButton.setLayoutY(405);
        postButton.setStyle("-fx-font-size: 25;" +
                "-fx-pref-height: 50;" +
                "-fx-pref-width: 150;");
        postButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (titleField.getText().equals("") || eventArea.getText().equals("")) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Ooops");
                    alert.setHeaderText(null);
                    alert.setContentText("Fill the all fields, please");
                    alert.showAndWait();
                } else {
                    PostResult postResult = XMLLJClient.getInstance().postevent(new PostArgument(titleField.getText(), eventArea.getText()));
                    eventArea.clear();
                    titleField.clear();
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Success!");
                    alert.setHeaderText("This is URL to your event");
                    alert.setContentText(postResult.getUrl());
                    alert.showAndWait();
                    eventsPane.getChildren().clear();
                    setEventsPane();
                    eventsScroll.setVisible(true);
                    postEventPane.setVisible(false);
                }
            }
        });

    }

    private void setEventsPane() {
        eventsData = null;
        eventsData = XMLLJClient.getInstance().getEvents(new GetEventsArgument());
        eventsPane.getChildren().clear();
        for (int i = 0; i < eventsData.length; i++) {
            final int ID = eventsData[i].getDItemId();
            Pane pane = new Pane();
            Label subjectLabel = new Label(eventsData[i].getSubject());
            Label bodyLabel = new Label(eventsData[i].getBody());
            Label eventDate = new Label(eventsData[i].getDate().toString());
            Label comLabel = new Label("Comments: " + eventsData[i].getReply_count());
            Label viewMore = new Label("View more & edit");

            pane.setStyle("-fx-pref-width: 433;" +
                    "-fx-pref-height: 250;" +
                    "-fx-border-style: solid;" +
                    "-fx-border-color: black;");

            subjectLabel.setLayoutX(5);
            subjectLabel.setLayoutY(5);
            subjectLabel.setStyle("-fx-font-size: 25;");

            bodyLabel.setLayoutX(5);
            bodyLabel.setLayoutY(35);
            bodyLabel.setPrefSize(410, 150);
            bodyLabel.setWrapText(true);

            comLabel.setLayoutX(5);
            comLabel.setLayoutY(228);
            comLabel.setStyle("-fx-font-size: 15;");
            comLabel.setOnMouseClicked(new OpenComments(eventsData[i].getURL()));

            eventDate.setLayoutX(5);
            eventDate.setLayoutY(210);
            eventDate.setStyle("-fx-font-size: 15;");

            viewMore.setLayoutY(228);
            viewMore.setStyle("-fx-font-size: 15;");
            viewMore.setLayoutX(310);
            viewMore.setOnMouseClicked(new ViewMore(eventsData[i]));

            pane.getChildren().addAll(viewMore, comLabel, subjectLabel, bodyLabel, eventDate);
            eventsPane.getChildren().add(pane);
        }
    }

    private void setSingleEvent(BlogEntry blogEntry) {

        singleEvent.getChildren().clear();


        Label titleLabel = new Label("Title: ");
        TextField title = new TextField(blogEntry.getSubject());
        Label bodyLabel = new Label("Event: ");
        TextArea bodyArea = new TextArea(blogEntry.getBody());
        Button editButton = new Button("Edit");
        editButton.setDisable(true);

        singleEvent.getChildren().addAll(titleLabel, title, bodyLabel, bodyArea, editButton);

        titleLabel.setStyle("-fx-font-size: 20");
        titleLabel.setLayoutX(10);
        titleLabel.setLayoutY(5);
        title.setLayoutX(10);
        title.setLayoutY(38);
        title.setStyle("-fx-pref-width: 350;" +
                "-fx-pref-height: 30;" +
                "-fx-font-size: 15;");
        title.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!(newValue.equals(oldValue))) {
                editButton.setDisable(false);
            }
        });

        bodyLabel.setStyle("-fx-font-size: 20;");
        bodyLabel.setLayoutX(10);
        bodyLabel.setLayoutY(68);

        bodyArea.setLayoutX(10);
        bodyArea.setLayoutY(95);
        bodyArea.setPrefSize(380, 300);
        bodyArea.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!(newValue.equals(oldValue))) {
                editButton.setDisable(false);
            }
        });

        editButton.setLayoutX(10);
        editButton.setLayoutY(405);
        editButton.setStyle("-fx-font-size: 25;" +
                "-fx-pref-height: 50;" +
                "-fx-pref-width: 150;");
        editButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                XMLLJClient.getInstance().editEvents(new EditEventArgument(blogEntry.getItemid(), title.getText(), bodyArea.getText()));
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success!");
                alert.setContentText("Post successfully updated(deleted)");
                alert.showAndWait();
                singleEvent.setVisible(false);
                setEventsPane();
                eventsScroll.setVisible(true);

            }
        });
    }

    private class ViewMore implements EventHandler<MouseEvent> {

        BlogEntry entry;

        public ViewMore(BlogEntry entry) {
            this.entry = entry;
        }

        @Override
        public void handle(MouseEvent event) {
            setSingleEvent(entry);
            singleEvent.setVisible(true);
            eventsScroll.setVisible(false);
        }
    }

    private class EditFriend implements EventHandler<ActionEvent> {

        private String what;
        private String username;

        public EditFriend(String what, String username) {
            this.what = what;
            this.username = username;
        }

        @Override
        public void handle(ActionEvent event) {
            XMLLJClient.getInstance().editFriend(new EditFriendsArgument(what, username));
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success!");
            switch (what) {
                case "delete":
                    alert.setContentText("Friend successfully deleted");
                    break;
                case "add":
                    alert.setContentText("Post successfully added");
                    break;
            }
            alert.showAndWait();
            friendPane.getChildren().clear();
            setFriendsPane();
        }
    }

    private class OpenComments implements EventHandler<MouseEvent> {

        private String URL;

        public OpenComments(String URL) {
            this.URL = URL;
        }

        @Override
        public void handle(MouseEvent event) {
            try {
                Desktop.getDesktop().browse(new URI(URL));
            } catch (IOException e) {
                e.printStackTrace();
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
        }
    }
}
