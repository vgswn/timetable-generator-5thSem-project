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
public class Student_teacherController implements Initializable {

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @FXML

    private ComboBox student_teacher;
    ObservableList<String> list = FXCollections.observableArrayList("Student", "Teacher", "Room");

    @FXML
    private JFXButton student_teacher_btn;
    @FXML
    private JFXButton backbtn;

    public static int batch;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            Thread.sleep(800);
        } catch (InterruptedException ex) {
            Logger.getLogger(Student_teacherController.class.getName()).log(Level.SEVERE, null, ex);
        }
        toDOC a = new toDOC();
        a.create();
        toDocRoom a2 = new toDocRoom();
        a2.create();
        toDocSec a3 = new toDocSec();
        a3.create();
        
        backbtn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

                try {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("selection.fxml"));

                    ////System.out.println("dssff");

                    Parent root1 = (Parent) fxmlLoader.load();
                    Stage stage = new Stage();
                    ////System.out.println(root1);
                    stage.setScene(new Scene(root1));
                    ////System.out.println("dsf");
                    stage.show();
                    Stage stage5;
                    stage5 = (Stage) backbtn.getScene().getWindow();
                    stage5.close();
                } catch (IOException ex) {
                    Logger.getLogger(Sec1Controller.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        student_teacher.setItems(list);
        student_teacher_btn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

                if (student_teacher.getValue() == "Teacher") {
                    try {
                        batch = -1;
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("teacherlist.fxml"));

                        //    ////System.out.println("dssff");
                        Parent root1 = (Parent) fxmlLoader.load();
                        Stage stage = new Stage();
                        ////System.out.println(root1);
                        stage.setScene(new Scene(root1));
                        ////System.out.println("dsf");
                        stage.show();
                        stage.setTitle((String) student_teacher.getValue());
                        Stage stage5;
                        stage5 = (Stage) student_teacher_btn.getScene().getWindow();
                        stage5.close();
                    } catch (IOException ex) {
                        Logger.getLogger(FirstController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else if (student_teacher.getValue() == "Student") {
                    try {
                        batch = 100;
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("bm.fxml"));

                        Parent root1 = (Parent) fxmlLoader.load();
                        Stage stage = new Stage();
                        ////System.out.println(root1);
                        stage.setScene(new Scene(root1));
                        ////System.out.println("dsf");
                        stage.show();
                        stage.setTitle((String) student_teacher.getValue());
                        Stage stage5;
                        stage5 = (Stage) student_teacher_btn.getScene().getWindow();
                        stage5.close();
                    } catch (IOException ex) {
                        Logger.getLogger(FirstController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    batch = 200;
                    try {

                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("room_class.fxml"));

                        Parent root1 = (Parent) fxmlLoader.load();
                        Stage stage = new Stage();
                        ////System.out.println(root1);
                        stage.setScene(new Scene(root1));
                        ////System.out.println("dsf");
                        stage.show();
                        stage.setTitle((String) student_teacher.getValue());
                        Stage stage5;
                        stage5 = (Stage) student_teacher_btn.getScene().getWindow();
                        stage5.close();
                    } catch (IOException ex) {
                        Logger.getLogger(FirstController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
            }

        });

    }
}
