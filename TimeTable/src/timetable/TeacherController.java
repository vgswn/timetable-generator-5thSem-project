/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timetable;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Vika$h
 */
public class TeacherController implements Initializable {

    @FXML
    private TableView<Data> TimeTable;
    @FXML
    private TableColumn<Data,String> Day;
    @FXML
    private TableColumn<Data,String> slot1;
    @FXML
    private TableColumn<Data, String> slot2;
    @FXML
    private TableColumn<Data, String> slot3;
    @FXML
    private TableColumn<Data, String> slot4;
    @FXML
    private TableColumn<Data, String> lab;

    /**
     * Initializes the controller class.
     */
        public static ObservableList<Data> lst = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
       lst.clear();
        Day.setCellValueFactory(new PropertyValueFactory<Data, String>("a"));
        slot1.setCellValueFactory(new PropertyValueFactory<Data, String>("b"));

        slot2.setCellValueFactory(new PropertyValueFactory<Data, String>("c"));
        slot3.setCellValueFactory(new PropertyValueFactory<Data, String>("d"));
        slot4.setCellValueFactory(new PropertyValueFactory<Data, String>("e"));
        lab.setCellValueFactory(new PropertyValueFactory<Data, String>("f"));
        
        Data qq = new Data("Monday", "sub1","sub2", "sub3", "sub4","lab");
        lst.add(qq);
        TimeTable.setItems(lst);
        }
    }    
    

