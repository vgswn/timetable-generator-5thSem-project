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
public class MtechlistController implements Initializable {

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @FXML

    private ComboBox mtechlist;
    ObservableList<String> list = FXCollections.observableArrayList("Dual Degree B.Tech.(ECE) MTech(MI)  ","M.Tech.(MI) – I Semester  ","M.Tech.(MI) – III Semester  ","BME –VII Sem (Dual degree) I Sem (MTech)  ","B.Tech. DUAL(IT) –VII WCE-  ","M.Tech. (WCE) –I Semester  ",
"B.Tech. DUAL(IT) – VII CLIS-  ",
"M.Tech.(CLIS) –I Semester  ",
"B. Tech.DUAL(IT) –VII SE-  ",
"M.Tech.(SE) –I Semester  ",
"B.Tech.DUAL (IT) –VII HCI-  ",
"M.Tech.(HCI) –I Semester  ",
"B.Tech.DUAL(IT) – VII IS-  ",
"M.Tech.(IS) –I Semester  ",
"B.Tech. DUAL (IT) –VII RO-  ",
"M.Tech.(RO) – I Semester  ",
"M.Tech.(IS) – III Semester  ",
"M.Tech.(HCI)– III Semester  ",
"M.Tech.(RO)– III Semester  ",
"M.Tech.(SE)– III Semester  ",
"M.Tech.(WCE)– III Semester  ",
"M.Tech.(CLIS)– III Semester  ",
"BI–VII Semester (Dual degree)I Semester (MTech)  ",
"BI–III Semester (MTech)  ",
"B. Tech.DUAL(IT) –VII MBA  ",
"B. Tech.DUAL(ECE) –VII MBA  ");
    @FXML
    private JFXButton mtechlistbtn;
        @FXML

        private JFXButton backbtn;


    public static int batch;
    public static String course;

    
    public void initialize(URL url, ResourceBundle rb) {
         backbtn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

                try {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("bm.fxml"));

                    System.out.println("dssff");

                    Parent root1 = (Parent) fxmlLoader.load();
                    Stage stage = new Stage();
                    System.out.println(root1);
                    stage.setScene(new Scene(root1));
                    System.out.println("dsf");
                    stage.show();
                    Stage stage5;
                    stage5 = (Stage) backbtn.getScene().getWindow();
                    stage5.close();
                } catch (IOException ex) {
                    Logger.getLogger(Sec1Controller.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        mtechlist.setItems(list);
        mtechlistbtn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                                    course = (String) mtechlist.getValue();

                 FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("sec1.fxml"));

                    //    System.out.println("dssff");
                    Parent root1 = null;
                try {
                    root1 = (Parent) fxmlLoader.load();
                } catch (IOException ex) {
                    Logger.getLogger(MtechlistController.class.getName()).log(Level.SEVERE, null, ex);
                }
                    Stage stage = new Stage();
                    stage.resizableProperty().setValue(Boolean.FALSE);
                    stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                        public void handle(WindowEvent we) {
                            try {
                                try {
                                    pdfAll x=new pdfAll("allBtechBatches.pdf");
                                } catch (FileNotFoundException ex) {
                                    Logger.getLogger(MtechlistController.class.getName()).log(Level.SEVERE, null, ex);
                                } catch (DocumentException ex) {
                                    Logger.getLogger(MtechlistController.class.getName()).log(Level.SEVERE, null, ex);
                                }
delete_txt a = new delete_txt();
                            } catch (IOException ex) {
                                Logger.getLogger(Student_teacherController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    });
                    System.out.println(root1);
                    stage.setScene(new Scene(root1));
                    System.out.println("dsf");
                    stage.show();

                    Stage stage5;
                    stage5 = (Stage) mtechlistbtn.getScene().getWindow();
                    stage5.close();
                }
           
            });

        }
}


    

