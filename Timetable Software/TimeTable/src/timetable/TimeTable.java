/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timetable;

import com.itextpdf.text.DocumentException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 *
 * @author Vika$h
 */
public class TimeTable extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("selection.fxml"));

            ////System.out.println("dssff");
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.resizableProperty().setValue(Boolean.FALSE);
            stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                public void handle(WindowEvent we) {
                    try {
                        try {
                            pdfAll x=new pdfAll("allBtechBatches.pdf");
                        } catch (FileNotFoundException ex) {
                            Logger.getLogger(TimeTable.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (DocumentException ex) {
                            Logger.getLogger(TimeTable.class.getName()).log(Level.SEVERE, null, ex);
                        }
delete_txt a = new delete_txt();
                    } catch (IOException ex) {
                        Logger.getLogger(Student_teacherController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });

            //System.out.println(root1);
            stage.setScene(new Scene(root1));
            ////System.out.println("dsf");
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(TimeTable.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        launch(args);
    }

}
