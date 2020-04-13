package application;

/**
 * Main.java created by akshaybodla on MacBook Pro in CS400Project01
 * 
 * Author: Akshay Bodla (bodla@wisc.edu)
 * Date: @date
 * 
 * Course: CS400
 * Semester: Spring 2020
 * Lecture: 001
 * 
 * IDE: Eclipse IDE for Java Developers
 * 
 * Version: 2019-06 (4.12.0)
 * Build id: 20190614-1200
 * 
 * Device:  Akshay's Macbook Pro
 * OS    :  macOS High Sierra
 * Version: Version 10.15.4
 * 
 * List Collaborators: N/A
 * 
 * Other Credits: N/A
 */

import java.util.List;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * @author akshaybodla
 *
 */
@SuppressWarnings("unused")
public class Main extends Application {
    // store any command-line arguments that were entered.
    // NOTE: this.getParameters().getRaw() will get these also
    private List<String> args;

    private static final int WINDOW_WIDTH = 300;
    private static final int WINDOW_HEIGHT = 200;
    private static final String APP_TITLE = "JavaFX Program";
    
    @Override
    public void start(Stage primaryStage) throws Exception {

        //label to be added at the top of a border pane
        Label firstProg = new Label("CS400 My First JavaFX Program");
        
        //add the label to a box
        HBox h1 = new HBox(); 
        h1.getChildren().add(firstProg);
        
        ObservableList<String> options = FXCollections.observableArrayList 
                ("Cheesecake", "Brownie Sundae", "Smoothie");
        
        ComboBox<String> cb = new ComboBox<String>(options);
        
        
        //Add hbox & label to a scene
        BorderPane bp = new BorderPane();
        bp.setTop(h1);
        bp.setLeft(cb);
        Scene mainScene = new Scene(bp, WINDOW_WIDTH, WINDOW_HEIGHT);
        
        primaryStage.setTitle(APP_TITLE);
        primaryStage.setScene(mainScene);
        primaryStage.show();
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
           launch(args);
    }
}