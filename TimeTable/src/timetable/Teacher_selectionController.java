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
import javafx.stage.WindowEvent;
import static timetable.FirstController.batch;

/**
 * FXML Controller class
 *
 * @author vips
 */
public class Teacher_selectionController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private ComboBox<String> teacher_combo;

    @FXML
    private JFXButton teachr_btn;
    @FXML
    private JFXButton back;
    public static String teacher;

    ObservableList<String> list = FXCollections.observableArrayList("sku", "ps", "aks", "at", "vkc", "sanjay", "abab", "sm", "sb", "tp", "pk", "akt", "sr", "sanjai", "js", "sbh", "rv", "mk", "mg", "bibhas", "sku", "rekha", "rajat", "maity", "np", "ashutosh", "vs", "raka",
            "vips", "ust", "sv", "pc", "sirv", "rrs", "sks", "ag", "venkat", "pawank", "sa", "sd", "kps", "ca", "gf2", "sk");

    @Override

    public void initialize(URL url, ResourceBundle rb) {

        teacher_combo.setItems(list);

        back.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

                try {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("first.fxml"));

                    System.out.println("dssff");

                    Parent root1 = (Parent) fxmlLoader.load();
                    Stage stage = new Stage();
                    stage.resizableProperty().setValue(Boolean.FALSE);
                    stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                        public void handle(WindowEvent we) {
                            try {
                                delete_txt a = new delete_txt();
                            } catch (IOException ex) {
                                Logger.getLogger(FirstController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    });
                    System.out.println(root1);
                    stage.setScene(new Scene(root1));
                    System.out.println("dsf");
                    stage.show();
                    Stage stage5;
                    stage5 = (Stage) back.getScene().getWindow();
                    stage5.close();
                } catch (IOException ex) {
                    Logger.getLogger(Sec1Controller.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        teachr_btn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

                try {
                    teacher = teacher_combo.getValue();
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("sec1.fxml"));

                    //    System.out.println("dssff");
                    Parent root1 = (Parent) fxmlLoader.load();
                    Stage stage = new Stage();
                    stage.resizableProperty().setValue(Boolean.FALSE);
                    stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                        public void handle(WindowEvent we) {
                            try {
                                delete_txt a = new delete_txt();
                            } catch (IOException ex) {
                                Logger.getLogger(FirstController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    });
                    System.out.println(root1);
                    stage.setScene(new Scene(root1));
                    System.out.println("dsf");
                    stage.show();

                    Stage stage5;
                    stage5 = (Stage) teachr_btn.getScene().getWindow();
                    stage5.close();
                } catch (IOException ex) {
                    Logger.getLogger(FirstController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        });

    }

}
