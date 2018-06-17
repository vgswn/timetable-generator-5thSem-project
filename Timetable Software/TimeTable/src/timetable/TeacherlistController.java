/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timetable;

import com.itextpdf.text.DocumentException;
import com.jfoenix.controls.JFXButton;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;
import java.util.StringTokenizer;
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
import static timetable.Student_teacherController.batch;

/**
 * FXML Controller class
 *
 * @author vips
 */
public class TeacherlistController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private ComboBox<String> teacherlist;

    @FXML
    private JFXButton teacherlistbtn;
    @FXML
    private JFXButton backbtn;
    public static String teacher;

    
   
    
    ObservableList<String> list = FXCollections.observableArrayList();

    @Override
    
    public void initialize(URL url, ResourceBundle rb) {
        FileReader f=null;
        try {
            f = new FileReader(SelectionController.teacherfile);
            BufferedReader b=new BufferedReader(f);
            String Line;
            while((Line=b.readLine())!=null)
            {
                StringTokenizer st=new StringTokenizer(Line,"-");
                String temp=st.nextToken();
                temp=st.nextToken();
               
                list.add(temp);
            }
           
            
            Collections.sort(list);
            teacherlist.setItems(list);
            //Collections.sort(list, cmprtr);
            backbtn.setOnAction(new EventHandler<ActionEvent>() {
                
                @Override
                public void handle(ActionEvent event) {
                    
                    try {
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("student_teacher.fxml"));
                        
                        //System.out.println("dssff");
                        
                        Parent root1 = (Parent) fxmlLoader.load();
                        Stage stage = new Stage();
                        stage.resizableProperty().setValue(Boolean.FALSE);
                        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                            public void handle(WindowEvent we) {
                                try {
                                    try {
                                        pdfAll x=new pdfAll("allBtechBatches.pdf");
                                    } catch (FileNotFoundException ex) {
                                        Logger.getLogger(TeacherlistController.class.getName()).log(Level.SEVERE, null, ex);
                                    } catch (DocumentException ex) {
                                        Logger.getLogger(TeacherlistController.class.getName()).log(Level.SEVERE, null, ex);
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
                        stage5 = (Stage) backbtn.getScene().getWindow();
                        stage5.close();
                    } catch (IOException ex) {
                        Logger.getLogger(Sec1Controller.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }); teacherlistbtn.setOnAction(new EventHandler<ActionEvent>() {
                
                @Override
                public void handle(ActionEvent event) {
                    
                    try {
                        teacher = teacherlist.getValue();
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("sec1.fxml"));
                        
                        //    //System.out.println("dssff");
                        Parent root1 = (Parent) fxmlLoader.load();
                        Stage stage = new Stage();
                        stage.resizableProperty().setValue(Boolean.FALSE);
                        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                            public void handle(WindowEvent we) {
                                try {
                                    try {
                                        pdfAll x=new pdfAll("allBtechBatches.pdf");
                                    } catch (FileNotFoundException ex) {
                                        Logger.getLogger(TeacherlistController.class.getName()).log(Level.SEVERE, null, ex);
                                    } catch (DocumentException ex) {
                                        Logger.getLogger(TeacherlistController.class.getName()).log(Level.SEVERE, null, ex);
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
                        stage5 = (Stage) teacherlistbtn.getScene().getWindow();
                        stage5.close();
                    } catch (IOException ex) {
                        Logger.getLogger(FirstController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                
            });
        } catch (FileNotFoundException ex) {
            Logger.getLogger(TeacherlistController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(TeacherlistController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                f.close();
            } catch (IOException ex) {
                Logger.getLogger(TeacherlistController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

}
