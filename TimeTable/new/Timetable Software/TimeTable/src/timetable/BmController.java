/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timetable;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Vika$h
 */
public class BmController implements Initializable {

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @FXML

    private ComboBox course;
    ObservableList<String> list = FXCollections.observableArrayList("Btech","Mtech","Other");

    @FXML
    private JFXButton coursebtn;
        @FXML

        private JFXButton backbtn;


    public static int batch;
    
    public void initialize(URL url, ResourceBundle rb) {
        
         backbtn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

                try {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("student_teacher.fxml"));

                    //System.out.println("dssff");

                    Parent root1 = (Parent) fxmlLoader.load();
                    Stage stage = new Stage();
                    //System.out.println(root1);
                    stage.setScene(new Scene(root1));
                    //System.out.println("dsf");
                    stage.show();
                    Stage stage5;
                    stage5 = (Stage) backbtn.getScene().getWindow();
                    stage5.close();
                } catch (IOException ex) {
                    Logger.getLogger(Sec1Controller.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

    
        course.setItems(list);
        coursebtn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

                if (course.getValue() == "Btech") {
                    try {
                        batch=10;
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("first.fxml"));
                        
                    //    //System.out.println("dssff");
                        
                        Parent root1 = (Parent) fxmlLoader.load();
                        Stage stage = new Stage();
                        //System.out.println(root1);
                        stage.setScene(new Scene(root1));
                        //System.out.println("dsf");
                        stage.show();
                        stage.setTitle((String) course.getValue());
                        Stage stage5;
                        stage5 = (Stage)coursebtn.getScene().getWindow();
                        stage5.close();
                    } catch (IOException ex) {
                        Logger.getLogger(FirstController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } 
                else if (course.getValue() == "Mtech") {
                    try {
                        batch=20;
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("mtechlist.fxml"));
                        
                    //    //System.out.println("dssff");
                        
                        Parent root1 = (Parent) fxmlLoader.load();
                        Stage stage = new Stage();
                        //System.out.println(root1);
                        stage.setScene(new Scene(root1));
                        //System.out.println("dsf");
                        stage.show();
                        stage.setTitle((String) course.getValue());
                        Stage stage5;
                        stage5 = (Stage)coursebtn.getScene().getWindow();
                        stage5.close();
                    } catch (IOException ex) {
                        Logger.getLogger(FirstController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } 
                else if (course.getValue() == "Other") {
                    try {
                        batch=30;
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("otherlist.fxml"));
                        
                    //    //System.out.println("dssff");
                        
                        Parent root1 = (Parent) fxmlLoader.load();
                        Stage stage = new Stage();
                        //System.out.println(root1);
                        stage.setScene(new Scene(root1));
                        //System.out.println("dsf");
                        stage.show();
                        stage.setTitle((String) course.getValue());
                        Stage stage5;
                        stage5 = (Stage)coursebtn.getScene().getWindow();
                        stage5.close();
                    } catch (IOException ex) {
                        Logger.getLogger(FirstController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } 
                
                
                
               else {
                }
            }

        });

    }
}
