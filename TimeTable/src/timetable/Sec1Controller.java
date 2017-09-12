/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timetable;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
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
import javafx.print.PageLayout;
import javafx.print.PageOrientation;
import javafx.print.Paper;
import javafx.print.Printer;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.scene.transform.Scale;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import static timetable.SelectionController.selected_file;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javafx.util.Duration;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author Vika$h
 */
public class Sec1Controller implements Initializable {

    @FXML
    private TableView<Data> TimeTable;
    @FXML
    private TableColumn<Data, String> Day;
    @FXML
    private TableColumn<Data, String> slot1;
    @FXML
    private TableColumn<Data, String> slot2;
    @FXML
    private TableColumn<Data, String> slot3;
    @FXML
    private TableColumn<Data, String> slot4;
    @FXML
    private TableColumn<Data, String> lab;
    @FXML
    private Button home;

    @FXML
    private Button back;

    @FXML
    private Button print;

    @FXML
    private Text sec;

    /**
     * Initializes the controller class.
     */
    ObservableList<Data> list = FXCollections.observableArrayList();

    //   public static ObservableList<Data> lst = FXCollections.observableArrayList();
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        list.clear();
        Day.setCellValueFactory(new PropertyValueFactory<Data, String>("a"));
        slot1.setCellValueFactory(new PropertyValueFactory<Data, String>("b"));

        slot2.setCellValueFactory(new PropertyValueFactory<Data, String>("c"));
        slot3.setCellValueFactory(new PropertyValueFactory<Data, String>("d"));
        slot4.setCellValueFactory(new PropertyValueFactory<Data, String>("e"));
        lab.setCellValueFactory(new PropertyValueFactory<Data, String>("f"));

        Data qq = new Data("Monday", "sub1", "sub2", "sub3", "sub4", "lab");
        this.go();
        TimeTable.setItems(list);

        home.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                try {
                    runbat q = new runbat();
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("first.fxml"));

                    System.out.println("dssff");

                    Parent root1 = (Parent) fxmlLoader.load();
                    Stage stage = new Stage();
                    System.out.println(root1);
                    stage.setScene(new Scene(root1));
                    System.out.println("dsf");
                    stage.show();
                    Stage stage5;
                    stage5 = (Stage) home.getScene().getWindow();
                    stage5.close();
                } catch (IOException ex) {
                }

            }

        });

        back.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                if (FirstController.batch == -1) {
                    try {
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("first.fxml"));

                        System.out.println("dssff");

                        Parent root1 = (Parent) fxmlLoader.load();
                        Stage stage = new Stage();
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

                } else if (FirstController.batch == 100) {
                    try {
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("first.fxml"));

                        System.out.println("dssff");

                        Parent root1 = (Parent) fxmlLoader.load();
                        Stage stage = new Stage();
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

                } else {
                    try {

                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("second.fxml"));

                        System.out.println("dssff");

                        Parent root1 = (Parent) fxmlLoader.load();
                        Stage stage = new Stage();
                        System.out.println(root1);
                        stage.setScene(new Scene(root1));
                        System.out.println("dsf");
                        stage.show();
                        Stage stage5;
                        stage5 = (Stage) back.getScene().getWindow();
                        stage5.close();
                    } catch (IOException ex) {
                    }
                }

            }

        });

        print.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                File file = new File("routine.pdf");

                createPdf(sec.getText() + ".pdf");

                TrayNotification tray = new TrayNotification();
                tray.setTitle("Success");
                tray.setMessage("saved as " + sec.getText() + ".pdf");
                tray.setNotificationType(NotificationType.SUCCESS);
                tray.showAndDismiss(Duration.millis(2000));
            }
        });

    }

    public void go() {

        if (FirstController.batch == -1) {

            String fileName = "teacher.txt";
            sec.setText("Teacher : " + Teacher_selectionController.teacher);
            String line = "null";

            try {
                int f = 0;
                FileReader fileReader = new FileReader(fileName);
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                StringTokenizer st = new StringTokenizer(line);
                int flag = 0;
                while ((line = bufferedReader.readLine()) != null) {
                    String[] a = new String[6];
                    int i;
                    for (i = 0; i < 5; i++) {
                        a[i] = "";
                    }
                    int count = 0;

                    st = new StringTokenizer(line, ";");
                    while (st.hasMoreTokens()) {

                        String s = st.nextToken();
                        if (s.equals("Teacher: " + Teacher_selectionController.teacher)) {
                            System.out.println("sbfgvdc");
                            flag = 1;
                            break;
                        }
                        StringTokenizer stt = new StringTokenizer(s, "!");
                        if (s.equals("$")) {

                            s = " ";
                            a[count] = s;
                        } else {
                            while (stt.hasMoreTokens()) {
                                String ss = stt.nextToken();
                                a[count] += ss;
                                a[count] += "\n";

                            }

                        }
                        count++;

                        //  System.out.println(s);
                    }
                    if (flag > 0 && flag < 7) {

                        if(a[0]!=""){
                            
                        list.add(new Data(a[0], a[1], a[2], "TB", a[3], a[4]));
                        flag++;
                        }
                    }

                }

            } catch (Exception e) {

            }
        } else if (FirstController.batch
                == 100) {

            String fileName = "test6.txt";
            String line = "null";
            sec.setText("B.Tech IT 7th Sem Sec A + Sec B");
            try {

                FileReader fileReader = new FileReader(fileName);
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                StringTokenizer st = new StringTokenizer(line);

                while ((line = bufferedReader.readLine()) != null) {
                    String[] a = new String[6];
                    int i;
                    for (i = 0; i < 5; i++) {
                        a[i] = "";
                    }
                    int count = 0;
                    st = new StringTokenizer(line, ";");
                    while (st.hasMoreTokens()) {
                        String s = st.nextToken();
                        StringTokenizer stt = new StringTokenizer(s, "!");
                        if (s.equals("$")) {

                            s = " ";
                            a[count] = s;
                        } else {
                            while (stt.hasMoreTokens()) {
                                String ss = stt.nextToken();
                                a[count] += ss;
                                a[count] += "\n";

                            }

                        }
                        count++;

                        //  System.out.println(s);
                    }

                    list.add(new Data(a[0], a[1], a[2], "TEA BREAK", a[3], a[4]));
                }
            } catch (Exception e) {

            }

        } else {
            //sec.setText(Integer.toString(SecondController.sec + 1));
            //year.setText(Integer.toString(FirstController.batch + 1));
            String fs = "B.Tech ";
            if (SecondController.sec + 1 < 3) {
                fs = fs + " IT ";
                fs = fs + " Sem : " + Integer.toString(2 * FirstController.batch + 1);
                fs = fs + " Sec : " + Integer.toString(SecondController.sec + 1);

            } else {
                fs = fs + " ECE ";
                fs = fs + " Sem : " + Integer.toString(2 * FirstController.batch + 1);

            }
            sec.setText(fs);

            int no = (SecondController.sec) + FirstController.batch * 3;
            String fileName = "test" + no + ".txt";
            String line = "null";
            System.out.println("In Sec1");

            try {

                FileReader fileReader = new FileReader(fileName);
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                StringTokenizer st = new StringTokenizer(line);
                while ((line = bufferedReader.readLine()) != null) {
                    String[] a = new String[6];
                    int count = 0;
                    st = new StringTokenizer(line, ";");
                    while (st.hasMoreTokens()) {
                        String s = st.nextToken();
                        if (s.equals("$")) {
                            s = " ";
                        }

                        a[count++] = s;
                        //  System.out.println(s);
                    }

                    list.add(new Data(a[0], a[1], a[2], "TEA BREAK", a[3], a[4]));
                }
            } catch (Exception e) {

            }
        }
    }

    public void createPdf(String dest) {
        try {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(dest));
            document.open();
            PdfPTable table = new PdfPTable(6);
            table.setWidthPercentage(100);
            String[] tableTitleList = {" DAYS", "9-10 AM", "10-11 AM", "11-11:15 AM", "11:15-12:15 PM", "12:15-1:15 PM",};

            for (String field : tableTitleList) {
                table.addCell(field);
            }
            list.stream().forEach((item) -> {
                table.addCell(item.getA());
                table.addCell(item.getB());
                table.addCell(item.getC());
                table.addCell(item.getD());
                table.addCell(item.getE());
                table.addCell(item.getF());
            });
            document.add(table);
            document.close();

        } catch (Exception ex) {
            Logger.getLogger(Sec1Controller.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<List<String>> getData() {
        List<List<String>> data = new ArrayList<List<String>>();
        String[] tableTitleList = {" DAYS", "9-10 AM", "10-11 AM", "11-11:15 AM", "11:15-12:15 PM", "12:15-1:15 PM",};
        data.add(Arrays.asList(tableTitleList));
        for (int i = 0; i < 5;) {
            List<String> dataLine = new ArrayList<String>();
            i++;
            list.stream().forEach((_item) -> {
                System.out.println(_item);
            });
            data.add(dataLine);
        }

        return data;
    }

}
