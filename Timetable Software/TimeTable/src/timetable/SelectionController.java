/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timetable;

import com.itextpdf.text.DocumentException;
import com.jfoenix.controls.JFXButton;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * FXML Controller class
 *
 * @author Vika$h
 */
public class SelectionController implements Initializable {

    @FXML
    private JFXButton generate;

    public static File inputfile;
    public static File teacherfile;
    public static File roomfile;
    public static File sectionfile;

    @FXML
    private Text inputtxt;

    @FXML
    private JFXButton inputbtn;

    @FXML
    private JFXButton teacherbtn;

    @FXML
    private JFXButton roombtn;

    @FXML
    private JFXButton selectionbtn;

    @FXML
    private Text teachertxt;

    @FXML
    private Text roomtxt;

    @FXML
    private Text sectiontxt;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        inputbtn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                FileChooser fc = new FileChooser();
                inputfile = fc.showOpenDialog(new Stage());
                if (inputfile != null) {
                    inputtxt.setText(inputfile.getName());
                }
            }
        });
        teacherbtn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                FileChooser fc = new FileChooser();
                teacherfile = fc.showOpenDialog(new Stage());
                if (teacherfile != null) {
                    teachertxt.setText(teacherfile.getName());
                }
            }
        });
        roombtn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                FileChooser fc = new FileChooser();
                roomfile = fc.showOpenDialog(new Stage());
                if (roomfile != null) {
                    roomtxt.setText(roomfile.getName());
                }
            }
        });
        selectionbtn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                FileChooser fc = new FileChooser();
                sectionfile = fc.showOpenDialog(new Stage());
                if (sectionfile != null) {
                    sectiontxt.setText(sectionfile.getName());
                }
            }
        });

        generate.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

                try {
                    Parse p = new Parse(inputfile.getAbsolutePath());
                    p.parse("new.txt");
                    inputfile = new File("new.txt");

                    runbat a = new runbat();
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("student_teacher.fxml"));

                    //System.out.println("dssff");
                    Parent root1 = (Parent) fxmlLoader.load();
                    Stage stage = new Stage();
                    stage.resizableProperty().setValue(Boolean.FALSE);
                    stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                        public void handle(WindowEvent we) {
                            try {
                                try {
                                    pdfAll x = new pdfAll("allBtechBatches.pdf");
                                } catch (FileNotFoundException ex) {
                                    Logger.getLogger(SelectionController.class.getName()).log(Level.SEVERE, null, ex);
                                } catch (DocumentException ex) {
                                    Logger.getLogger(SelectionController.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                delete_txt a = new delete_txt();
                            } catch (IOException ex) {
                                Logger.getLogger(Student_teacherController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    });
                    //System.out.println(root1);
                    stage.setScene(new Scene(root1));
                    //System.out.println("dsf");
                    stage.show();
                    Stage stage5;
                    stage5 = (Stage) generate.getScene().getWindow();
                    stage5.close();
                } catch (IOException ex) {
                    Logger.getLogger(SelectionController.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

        });

        // TODO
    }
}
