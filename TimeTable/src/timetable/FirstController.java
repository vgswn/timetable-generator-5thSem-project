/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timetable;

import com.itextpdf.text.DocumentException;
import com.jfoenix.controls.JFXButton;
import java.io.FileNotFoundException;
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

/**
 * FXML Controller class
 *
 * @author Vika$h
 */
public class FirstController implements Initializable {

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @FXML

    private ComboBox what;
    ObservableList<String> list = FXCollections.observableArrayList("1st_Year", "2nd_Year", "3rd_Year", "4th_Year", "Teacher");

    @FXML
    private JFXButton okbtn;

    public static int batch;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        what.setItems(list);
        okbtn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

                if (what.getValue() == "Teacher") {
                    try {
                        batch = -1;
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("teacher_selection.fxml"));

                        //    System.out.println("dssff");
                        Parent root1 = (Parent) fxmlLoader.load();
                        Stage stage = new Stage();
                        stage.resizableProperty().setValue(Boolean.FALSE);
                        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                            public void handle(WindowEvent we) {
                                try {
                                    try {
                                        pdfAll x=new pdfAll("allBtechBatches.pdf");
                                    } catch (FileNotFoundException ex) {
                                        Logger.getLogger(FirstController.class.getName()).log(Level.SEVERE, null, ex);
                                    } catch (DocumentException ex) {
                                        Logger.getLogger(FirstController.class.getName()).log(Level.SEVERE, null, ex);
                                    }
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
                        stage.setTitle((String) what.getValue());
                        Stage stage5;
                        stage5 = (Stage) okbtn.getScene().getWindow();
                        stage5.close();
                    } catch (IOException ex) {
                        Logger.getLogger(FirstController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else if (what.getValue() == "4th_Year") {
                    try {
                        batch = 100;
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("sec1.fxml"));

                        //    System.out.println("dssff");
                        Parent root1 = (Parent) fxmlLoader.load();
                        Stage stage = new Stage();
                        stage.resizableProperty().setValue(Boolean.FALSE);
                        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                            public void handle(WindowEvent we) {
                                try {
                                    try {
                                        pdfAll x=new pdfAll("allBtechBatches.pdf");
                                    } catch (FileNotFoundException ex) {
                                        Logger.getLogger(FirstController.class.getName()).log(Level.SEVERE, null, ex);
                                    } catch (DocumentException ex) {
                                        Logger.getLogger(FirstController.class.getName()).log(Level.SEVERE, null, ex);
                                    }
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
                        stage.setTitle((String) what.getValue());
                        Stage stage5;
                        stage5 = (Stage) okbtn.getScene().getWindow();
                        stage5.close();
                    } catch (IOException ex) {
                        Logger.getLogger(FirstController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else if ((what.getValue() == "1st_Year") || (what.getValue() == "2nd_Year") || (what.getValue() == "3rd_Year")) {
                    String s = (String) what.getValue();
                    batch = Integer.parseInt(s.substring(0, 1)) - 1;
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("second.fxml"));
                    Parent root1 = null;
                    try {
                        root1 = (Parent) fxmlLoader.load();
                    } catch (IOException ex) {
                        Logger.getLogger(TimeTable.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    Stage stage = new Stage();
                    stage.resizableProperty().setValue(Boolean.FALSE);
                    stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                        public void handle(WindowEvent we) {
                            try {
                                try {
                                    pdfAll x=new pdfAll("allBtechBatches.pdf");
                                } catch (FileNotFoundException ex) {
                                    Logger.getLogger(FirstController.class.getName()).log(Level.SEVERE, null, ex);
                                } catch (DocumentException ex) {
                                    Logger.getLogger(FirstController.class.getName()).log(Level.SEVERE, null, ex);
                                }
delete_txt a = new delete_txt();
                            } catch (IOException ex) {
                                Logger.getLogger(FirstController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    });
                    stage.setScene(new Scene(root1));
                    stage.show();
                    stage.setTitle((String) what.getValue());

                    Stage stage5;
                    stage5 = (Stage) okbtn.getScene().getWindow();
                    stage5.close();
                } else {
                }
            }

        });

    }
}
