/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timetable;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * FXML Controller class
 *
 * @author Vika$h
 */
public class SectionController implements Initializable {

    @FXML
    private TableView<?> TimeTable;
    @FXML
    private TableColumn<?, ?> slot1;
    @FXML
    private TableColumn<?, ?> slot2;
    @FXML
    private TableColumn<?, ?> slot3;
    @FXML
    private TableColumn<?, ?> slot4;
    @FXML
    private TableColumn<?, ?> lab;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
