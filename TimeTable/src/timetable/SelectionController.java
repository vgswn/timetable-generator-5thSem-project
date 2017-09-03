/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timetable;

import com.jfoenix.controls.JFXButton;
import java.io.File;
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

/**
 * FXML Controller class
 *
 * @author Vika$h
 */
public class SelectionController implements Initializable {

    @FXML
    private JFXButton select_btn;
        @FXML
    private Text file_name;

    @FXML
    private JFXButton generate;

    public static   File selected_file;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        select_btn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                FileChooser fc=new FileChooser();
              selected_file=fc.showOpenDialog(new Stage());
                if(selected_file!=null)
                {

                    file_name.setText(selected_file.getName());
                    

                   
                    
                }
                

                
            }

        });
        
        
        
                generate.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                
                    try {
                        runbat q=new runbat();
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("first.fxml"));
                        
                        System.out.println("dssff");
                        
                        Parent root1 = (Parent) fxmlLoader.load();
                        Stage stage = new Stage();
                        System.out.println(root1);
                        stage.setScene(new Scene(root1));
                        System.out.println("dsf");
                        stage.show();
                        Stage stage5;
                        stage5 = (Stage)generate.getScene().getWindow();
                        stage5.close();
                    } catch (IOException ex) {
                        Logger.getLogger(SelectionController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                }
                

                
            

        });

        // TODO
      
    
}
}
