/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timetable;

import com.itextpdf.text.BaseColor;
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
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPRow;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import javafx.stage.WindowEvent;
import javafx.util.Duration;
import static jdk.nashorn.internal.objects.NativeString.trim;
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
    private TableColumn<Data, String> slot5;
    @FXML
    private TableColumn<Data, String> slot6;
    @FXML
    private TableColumn<Data, String> slot7;
    @FXML
    private TableColumn<Data, String> slot8;
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
    ObservableList<Data> list2 = FXCollections.observableArrayList();
    public static HashMap<String, Integer> map = new HashMap<String, Integer>();
    public static HashMap<Integer, String> smap = new HashMap<Integer, String>();
    public static HashMap<String, Integer> tmap = new HashMap<String, Integer>();
    public static HashMap<String, String> roommap = new HashMap<String, String>();
    public static HashMap<String, String> rmap = new HashMap<String, String>();
    public static HashMap<Integer, String> ttmap = new HashMap();
    int no = 0;
    String line;
    String fileName;

    public void makeHashMap() throws FileNotFoundException, IOException {
        BufferedReader br = null;

        br = new BufferedReader(new FileReader(SelectionController.teacherfile));
        String line = null;
        while ((line = br.readLine()) != null) {
            /*StringTokenizer st = new StringTokenizer(line, "-");
            String aa = st.nextToken().trim();
            int a = Integer.parseInt(aa);
            String b = st.nextToken().trim();*/
            int index=line.indexOf('-');
            String aa=line.substring(0, index);
            int a=Integer.parseInt(aa);
            String b=line.substring(index+1, line.length());
            tmap.put(b, a);
            ttmap.put(a, b);

        }
        br = new BufferedReader(new FileReader(SelectionController.sectionfile));
        while ((line = br.readLine()) != null) {
            int index=line.indexOf('-');
            String aa=line.substring(0, index);
            int a=Integer.parseInt(aa);
            String b=line.substring(index+1, line.length());
            smap.put(a, b);
            map.put(b, a);

        }
        br = new BufferedReader(new FileReader(SelectionController.roomfile));
        while ((line = br.readLine()) != null) {
            int index=line.indexOf('-');
            String aa=line.substring(0, index);
            int a=Integer.parseInt(aa);
            String b=line.substring(index+1, line.length());
            rmap.put(b, aa);
            roommap.put(aa, b);

        }

    }

    //   public static ObservableList<Data> lst = FXCollections.observableArrayList();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            makeHashMap();

            /*rmap.put("5006", "10");
            rmap.put("5007", "11");
            rmap.put("5054", "12");
            rmap.put("5106", "20");
            rmap.put("5107", "21");
            rmap.put("5154", "22");
            rmap.put("5206", "30");
            rmap.put("5207", "31");
            rmap.put("5254", "32");
            rmap.put("5055", "13");
            rmap.put("5155", "23");
            rmap.put("5255", "33");
            rmap.put("2121", "42");
            rmap.put("2205", "43");
            rmap.put("2223", "41");
            rmap.put("4223", "80");
            rmap.put("4105", "81");
            rmap.put("4122", "82");
            rmap.put("3013", "90");
            rmap.put("3113", "91");
            rmap.put("3112", "92");
            rmap.put("4133", "83");
            rmap.put("4205", "84");
            rmap.put("3222", "97");
            rmap.put("4123", "85");
            rmap.put("3202", "93");
            rmap.put("2311", "43");
            
            roommap.put("10", "5006");
            roommap.put("11", "5007");
            roommap.put("12", "5054");
            roommap.put("20", "5106");
            roommap.put("21", "5107");
            roommap.put("22", "5154");
            roommap.put("30", "5206");
            roommap.put("31", "5207");
            roommap.put("32", "5254");
            roommap.put("13", "5055");
            roommap.put("23", "5155");
            roommap.put("33", "5255");
            roommap.put("42", "2121");
            roommap.put("40", "2205");
            roommap.put("41", "2223");
            roommap.put("80", "4223");
            roommap.put("81", "4105");
            roommap.put("82", "4122");
            roommap.put("90", "3013");
            roommap.put("91", "3113");
            roommap.put("92", "3112");
            roommap.put("83", "4133");
            roommap.put("84", "4205");
            roommap.put("92", "3222");
            roommap.put("85", "4123");
            roommap.put("93", "3202");
            roommap.put("43", "2311");
            
            tmap.put("sku", 0);
            tmap.put("ps", 1);
            tmap.put("aks", 2);
            tmap.put("at", 3);
            tmap.put("vkc", 4);
            tmap.put("sanjay", 5);
            tmap.put("pk", 6);
            tmap.put("abab", 7);
            tmap.put("sb", 8);
            tmap.put("tp", 9);
            tmap.put("akt", 10);
            tmap.put("sanjai", 11);
            tmap.put("js", 12);
            tmap.put("sbh", 13);
            tmap.put("rv", 14);
            tmap.put("mk", 15);
            tmap.put("mg", 16);
            tmap.put("bibhas", 17);
            tmap.put("rekha", 18);
            tmap.put("rajat", 19);
            tmap.put("maity", 20);
            tmap.put("np", 21);
            tmap.put("ashutosh", 22);
            tmap.put("sm", 23);
            tmap.put("sr", 24);
            tmap.put("vs", 25);
            tmap.put("raka", 26);
            tmap.put("ust", 27);
            tmap.put("sv", 28);
            tmap.put("pc", 29);
            tmap.put("sks", 30);
            tmap.put("srv", 31);
            tmap.put("ag", 32);
            tmap.put("venkat", 33);
            tmap.put("rrs", 34);
            tmap.put("vips", 35);
            tmap.put("pawank", 36);
            tmap.put("sa", 37);
            tmap.put("sd", 38);
            tmap.put("kps", 39);
            tmap.put("ca", 40);
            tmap.put("gf2", 41);
            tmap.put("sk", 42);
            tmap.put("gcn", 43);
            tmap.put("vt", 44);
            tmap.put("rs", 45);
            tmap.put("ap", 46);
            tmap.put("am", 47);
            tmap.put("sangita", 48);
            tmap.put("km", 49);
            tmap.put("nm", 50);
            tmap.put("suneel", 51);
            tmap.put("bss", 52);
            tmap.put("ss", 53);
            tmap.put("prasanna", 54);
            tmap.put("brs", 55);
            tmap.put("aa", 56);
            tmap.put("rk", 57);
            tmap.put("mm", 58);
            tmap.put("sintu", 59);
            tmap.put("pv", 60);
            tmap.put("tl", 61);
            tmap.put("av", 62);
            tmap.put("vnt", 63);
            tmap.put("ug", 64);
            tmap.put("seema", 65);
            tmap.put("pooja", 66);
            tmap.put("ad", 67);
            tmap.put("sunny", 68);
            tmap.put("neha", 69);
            tmap.put("ac", 70);
            tmap.put("rks", 71);
            smap.put(0, "BTech(IT) ľI Semester Section 1 ");
            smap.put(1, "BTech(IT) ľI Semester Section 2");
            smap.put(2, "BTech(ECE) ľI Semester Section 3 ");
            smap.put(3, "BTech(IT) ľIII Semester Section 1 ");
            smap.put(4, "BTech(IT) ľIII Semester Section 2 ");
            smap.put(5, "BTech(ECE) ľIII Semester Section 3 ");
            smap.put(6, "BTech(IT) ľV Semester Section 1 ");
            smap.put(7, "BTech(IT) ľV Semester Section 2 ");
            smap.put(8, "BTech(ECE) ľV Semester Section 3 ");
            
            smap.put(9, "BTech(IT) ľVII Semester  ");
            smap.put(10, "BTech(ECE) ľVII Semester  ");
            smap.put(11, "Dual Degree B.Tech.(ECE) MTech(MI)  ");
            smap.put(12, "M.Tech.(MI) ľ I Semester  ");
            smap.put(13, "M.Tech.(MI) ľ III Semester  ");
            smap.put(14, "BME ľVII Sem (Dual degree) I Sem (MTech)  ");
            smap.put(15, "BME ľIX Sem (Integrated)  ");
            smap.put(16, "B.Tech. DUAL(IT) ľVII WCE-  ");
            smap.put(17, "M.Tech. (WCE) ľI Semester  ");
            smap.put(18, "B.Tech. DUAL(IT) ľ VII CLIS-  ");
            smap.put(19, "M.Tech.(CLIS) ľI Semester  ");
            smap.put(20, "B. Tech.DUAL(IT) ľVII SE-  ");
            smap.put(21, "M.Tech.(SE) ľI Semester  ");
            smap.put(22, "B.Tech.DUAL (IT) ľVII HCI-  ");
            smap.put(23, "M.Tech.(HCI) ľI Semester  ");
            smap.put(24, "B.Tech.DUAL(IT) ľ VII IS-  ");
            smap.put(25, "M.Tech.(IS) ľI Semester  ");
            smap.put(26, "B.Tech. DUAL (IT) ľVII RO-  ");
            smap.put(27, "M.Tech.(RO) ľ I Semester  ");
            smap.put(28, "M.Tech.(IS) ľ III Semester  ");
            smap.put(29, "M.Tech.(HCI)ľ III Semester  ");
            smap.put(30, "M.Tech.(RO)ľ III Semester  ");
            smap.put(31, "M.Tech.(SE)ľ III Semester  ");
            smap.put(32, "M.Tech.(WCE)ľ III Semester  ");
            smap.put(33, "M.Tech.(CLIS)ľ III Semester  ");
            smap.put(34, "BIľVII Semester (Dual degree)I Semester (MTech)  ");
            smap.put(35, "BIľIII Semester (MTech)  ");
            smap.put(36, "B. Tech.DUAL(IT) ľVII MBA  ");
            smap.put(37, "B. Tech.DUAL(ECE) ľVII MBA  ");
            
            map.put("BTech(IT) –VII Semester  ", 9);
            map.put("BTech(ECE) –VII Semester  ", 10);
            map.put("Dual Degree B.Tech.(ECE) MTech(MI)  ", 11);
            map.put("M.Tech.(MI) – I Semester  ", 12);
            map.put("M.Tech.(MI) – III Semester  ", 13);
            map.put("BME –VII Sem (Dual degree) I Sem (MTech)  ", 14);
            map.put("BME –IX Sem (Integrated)  ", 15);
            map.put("B.Tech. DUAL(IT) –VII WCE-  ", 16);
            map.put("M.Tech. (WCE) –I Semester  ", 17);
            map.put("B.Tech. DUAL(IT) – VII CLIS-  ", 18);
            map.put("M.Tech.(CLIS) –I Semester  ", 19);
            map.put("B. Tech.DUAL(IT) –VII SE-  ", 20);
            map.put("M.Tech.(SE) –I Semester  ", 21);
            map.put("B.Tech.DUAL (IT) –VII HCI-  ", 22);
            map.put("M.Tech.(HCI) –I Semester  ", 23);
            map.put("B.Tech.DUAL(IT) – VII IS-  ", 24);
            map.put("M.Tech.(IS) –I Semester  ", 25);
            map.put("B.Tech. DUAL (IT) –VII RO-  ", 26);
            map.put("M.Tech.(RO) – I Semester  ", 27);
            map.put("M.Tech.(IS) – III Semester  ", 28);
            map.put("M.Tech.(HCI)– III Semester  ", 29);
            map.put("M.Tech.(RO)– III Semester  ", 30);
            map.put("M.Tech.(SE)– III Semester  ", 31);
            map.put("M.Tech.(WCE)– III Semester  ", 32);
            map.put("M.Tech.(CLIS)– III Semester  ", 33);
            map.put("BI–VII Semester (Dual degree)I Semester (MTech)  ", 34);
            map.put("BI–III Semester (MTech)  ", 35);
            map.put("B. Tech.DUAL(IT) –VII MBA  ", 36);
            map.put("B. Tech.DUAL(ECE) –VII MBA  ", 37);*/
            list.clear();
            Day.setCellValueFactory(new PropertyValueFactory<Data, String>("a"));
            slot1.setCellValueFactory(new PropertyValueFactory<Data, String>("b"));

            slot2.setCellValueFactory(new PropertyValueFactory<Data, String>("c"));
            slot3.setCellValueFactory(new PropertyValueFactory<Data, String>("d"));
            slot4.setCellValueFactory(new PropertyValueFactory<Data, String>("e"));
            slot5.setCellValueFactory(new PropertyValueFactory<Data, String>("f"));
            slot6.setCellValueFactory(new PropertyValueFactory<Data, String>("g"));
            slot7.setCellValueFactory(new PropertyValueFactory<Data, String>("h"));
            slot8.setCellValueFactory(new PropertyValueFactory<Data, String>("i"));

            try {
                //Data qq = new Data("Monday", "sub1", "sub2", "sub3", "sub4", "slot5");
                this.go();
            } catch (IOException ex) {
                Logger.getLogger(Sec1Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
            TimeTable.setItems(list2);

            home.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {
                    try {
                        //   runbat q = new runbat();
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("student_teacher.fxml"));

                        ////System.out.println("dssff");

                        Parent root1 = (Parent) fxmlLoader.load();
                        Stage stage = new Stage();
                        stage.resizableProperty().setValue(Boolean.FALSE);
                        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                            public void handle(WindowEvent we) {
                                try {
                                    //pdfAll x = new pdfAll("allBtechBatches.pdf");
                                    delete_txt a = new delete_txt();
                                } catch (IOException ex) {
                                    Logger.getLogger(FirstController.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                        });
                        ////System.out.println(root1);
                        stage.setScene(new Scene(root1));
                        ////System.out.println("dsf");
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
                    if (Student_teacherController.batch == -1) {
                        try {
                            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("student_teacher.fxml"));

                            ////System.out.println("dssff");

                            Parent root1 = (Parent) fxmlLoader.load();
                            Stage stage = new Stage();
                            stage.resizableProperty().setValue(Boolean.FALSE);
                            stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                                public void handle(WindowEvent we) {
                                    try {
                                        try {
                                            pdfAll x = new pdfAll("allBtechBatches.pdf");
                                        } catch (FileNotFoundException ex) {
                                            Logger.getLogger(Sec1Controller.class.getName()).log(Level.SEVERE, null, ex);
                                        } catch (DocumentException ex) {
                                            Logger.getLogger(Sec1Controller.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                                        delete_txt a = new delete_txt();
                                    } catch (IOException ex) {
                                        Logger.getLogger(FirstController.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                }
                            });
                            ////System.out.println(root1);
                            stage.setScene(new Scene(root1));
                            ////System.out.println("dsf");
                            stage.show();
                            Stage stage5;
                            stage5 = (Stage) back.getScene().getWindow();
                            stage5.close();
                        } catch (IOException ex) {
                            Logger.getLogger(Sec1Controller.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    } else if (Student_teacherController.batch == 100) {
                        try {
                            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("student_teacher.fxml"));

                            ////System.out.println("dssff");

                            Parent root1 = (Parent) fxmlLoader.load();
                            Stage stage = new Stage();
                            stage.resizableProperty().setValue(Boolean.FALSE);
                            stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                                public void handle(WindowEvent we) {
                                    try {
                                        try {
                                            pdfAll x = new pdfAll("allBtechBatches.pdf");
                                        } catch (FileNotFoundException ex) {
                                            Logger.getLogger(Sec1Controller.class.getName()).log(Level.SEVERE, null, ex);
                                        } catch (DocumentException ex) {
                                            Logger.getLogger(Sec1Controller.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                                        delete_txt a = new delete_txt();
                                    } catch (IOException ex) {
                                        Logger.getLogger(FirstController.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                }
                            });
                            ////System.out.println(root1);
                            stage.setScene(new Scene(root1));
                            ////System.out.println("dsf");
                            stage.show();
                            Stage stage5;
                            stage5 = (Stage) back.getScene().getWindow();
                            stage5.close();
                        } catch (IOException ex) {
                            Logger.getLogger(Sec1Controller.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    } else {
                        try {

                            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("student_teacher.fxml"));

                            ////System.out.println("dssff");

                            Parent root1 = (Parent) fxmlLoader.load();
                            Stage stage = new Stage();
                            stage.resizableProperty().setValue(Boolean.FALSE);
                            stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                                public void handle(WindowEvent we) {
                                    try {
                                        try {
                                            pdfAll x = new pdfAll("allBtechBatches.pdf");
                                        } catch (FileNotFoundException ex) {
                                            Logger.getLogger(Sec1Controller.class.getName()).log(Level.SEVERE, null, ex);
                                        } catch (DocumentException ex) {
                                            Logger.getLogger(Sec1Controller.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                                        delete_txt a = new delete_txt();
                                    } catch (IOException ex) {
                                        Logger.getLogger(FirstController.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                }
                            });
                            ////System.out.println(root1);
                            stage.setScene(new Scene(root1));
                            ////System.out.println("dsf");
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

        } catch (IOException ex) {
            Logger.getLogger(Sec1Controller.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void go() throws FileNotFoundException, IOException {

        /*if (Student_teacherController.batch == -1) {

            String fileName = "teacher.txt";
            sec.setText("Teacher : " + TeacherlistController.teacher);
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
                        if (s.equals("Teacher: " + TeacherlistController.teacher)) {
                            //System.out.println("sbfgvdc");
                            flag = 1;
                            break;
                        }
                        StringTokenizer stt = new StringTokenizer(s, "!");
                        if (s.equals("$")) {

                            s = " ";
                            a[count] = s;
                        }
                        else if (s.startsWith("null")) {

                            s = " ";
                            a[count] = s;
                        }
                        else {
                            while (stt.hasMoreTokens()) {
                                String ss = stt.nextToken();
                                a[count] += ss;
                                a[count] += "\n";

                            }

                        }
                        count++;

                        //  //System.out.println(s);
                    }
                    if (flag > 0 && flag < 7) {

                        if (a[0] != "" && !a[0].startsWith("Teacher")) {
                            if(a[5].startsWith("null"))
                                a[5]=" ";
                               Data d=new Data(a[0], a[1], a[2], "TB \n "+"\n", a[3], a[4],"LUNCH BREAK"," ",a[5]);
                            list2.add(d);
                        }
                        flag++;
                    }

                }

            } catch (Exception e) {

            }
        } */
        if (Student_teacherController.batch == -1) {
            ////System.out.println("dumtaradumtara");
            String fileName = "teacher.txt";
            sec.setText("Teacher : " + TeacherlistController.teacher);
            String line = "null";

            try {
                int f = 0;
                FileReader fileReader = new FileReader(fileName);
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                StringTokenizer st = new StringTokenizer(line);
                int flag = 0;
                int i;
                String line2;
                while ((line = bufferedReader.readLine()) != null) {
                    if (line.startsWith("Teacher")) {
                        StringTokenizer colon = new StringTokenizer(line, ":");
                        String t = colon.nextToken();

                        String index = colon.nextToken();
                        ////System.out.println(index);
                        StringTokenizer semicolon = new StringTokenizer(index, ";");
                        String index2 = semicolon.nextToken();
                        ////System.out.println("index2"+index2);

                        if (index2.equals(String.valueOf(tmap.get(TeacherlistController.teacher)))) {
                            ////System.out.println("Inside");
                            for (i = 0; i < 5; i++) {
                                line2 = bufferedReader.readLine();

                                String[] a = new String[6];
                                int count = 0;
                                st = new StringTokenizer(line2, ";");
                                while (st.hasMoreTokens()) {
                                    String s = st.nextToken();
                                    s = s.trim();
                                    int daysign = s.indexOf("(");
                                    if (daysign == -1) {
                                        a[count] = s;
                                        count++;

                                        continue;
                                    }
                                    if (s.equals('F')) {
                                        s = " ";
                                        a[count++] = s;
                                        //System.out.println("fnsdjuhnfuksdhfusfkskfsbksdbf" + s);
                                    } else if (s.startsWith("null")) {

                                        s = " ";
                                        a[count++] = s;

                                    } else {
                                        ////System.out.println("Inside2");
                                        StringTokenizer openparen = new StringTokenizer(s, "(");
                                        ////System.out.println(s);
                                        String x = openparen.nextToken();
                                        int secc = Integer.valueOf(x);
                                        ////System.out.println(secc);
                                        String secname = smap.get(secc);
                                        ////System.out.println(secname);
                                        s = secname + "\n(" + openparen.nextToken();
                                        a[count++] = s;
                                    }

                                    //  //System.out.println(s);
                                }
                                Data d = new Data(a[0], a[1], a[2], "TB \n " + "\n", a[3], a[4], "LUNCH BREAK", a[5], " ");
                                list2.add(d);
                                ////System.out.println(a[0] + a[1] + a[2] + a[3] + a[4] + a[5]);
                                //list.add(new Data(a[0], a[1], a[2], "TB \n " + "\n", a[3], a[4], "LUNCH BREAK", a[5]));

                            }

                        }

                    }

                    /*  String[] a = new String[6];
                    int i;
                    for (i = 0; i < 5; i++) {
                        a[i] = "";
                    }
                    int count = 0;

                    st = new StringTokenizer(line, ";");
                    while (st.hasMoreTokens()) {

                        String s = st.nextToken();
                        if (s.equals("Teacher: " + TeacherlistController.teacher)) {
                            //System.out.println("sbfgvdc");
                            flag = 1;
                            break;
                        }
                        StringTokenizer stt = new StringTokenizer(s, "!");
                        if (s.equals("$")) {

                            s = " ";
                            a[count] = s;
                        } else if (s.startsWith("null")) {

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

                        //  //System.out.println(s);
                    }
                    if (flag > 0 && flag < 7) {

                        if (a[0] != "" && !a[0].startsWith("Teacher")) {
                            if (a[5].startsWith("null")) {
                                a[5] = " ";
                            }

                            list.add(new Data(a[0], a[1], a[2], "TB \n " + "\n", a[3], a[4], "LUNCH BREAK", a[5]));

                        }
                        flag++;
                    }
                    
                     */
                }

            } catch (Exception e) {

            }
        } else if (Student_teacherController.batch == 200) {
            String roomName = room_selectionController.room;
            //System.out.println(roomName);
            FileReader roomFreader = new FileReader("room" + rmap.get(roomName) + ".txt");
            sec.setText(" Room No : " + roomName);

            BufferedReader roombuffer = new BufferedReader(roomFreader);
            //StringTokenizer roomst = new StringTokenizer(line);
            String roomline;
            roomline = roombuffer.readLine();
            while ((roomline = roombuffer.readLine()) != null) {
                StringTokenizer roomtoken = new StringTokenizer(roomline, ";");
                int count = 0;
                String[] a = new String[6];
                //while (roomtoken.hasMoreTokens()) {
                String roomfound = roomtoken.nextToken();
                //System.out.println(roomfound);

                a[0] = roomfound;
                count++;
                for (int i = 0; i < 5; i++) {
                    roomfound = roomtoken.nextToken();
                    if (roomfound.equals("-1")) {
                        a[count] = " ";
                        count++;
                    } else {
                        a[count] = smap.get(Integer.parseInt(roomfound));
                        //System.out.println("room   " + a[count]);

                        count++;
                    }

                }

                //}
                Data d = new Data(a[0], a[1], a[2], "TB \n " + "\n", a[3], a[4], "LUNCH BREAK", a[5], " ");
                list2.add(d);
            }

        } else {

            if (BmController.batch == 10) {

                //sec.setText(Integer.toString(SecondController.sec + 1));
                //year.setText(Integer.toString(FirstController.batch + 1));
                String fs = "B.Tech ";
                if (SecondController.sec + 1 < 3 && !"4th_Year".equals(FirstController.year)) {
                    fs = fs + " IT ";
                    fs = fs + " Sem : " + Integer.toString(2 * FirstController.batch + 1);
                    fs = fs + " Sec : " + Integer.toString(SecondController.sec + 1);
                    no = (SecondController.sec) + FirstController.batch * 3;

                } else if (SecondController.sec + 1 == 3 && !"4th_Year".equals(FirstController.year)) {
                    fs = fs + " ECE ";
                    fs = fs + " Sem : " + Integer.toString(2 * FirstController.batch + 1);
                    no = (SecondController.sec) + FirstController.batch * 3;
                } else if (FoursecController.sec == 0 && "4th_Year".equals(FirstController.year)) {
                    fs = fs + " IT Sem :7 ";
                    no = 9;
                } else if (FoursecController.sec == 1 && "4th_Year".equals(FirstController.year)) {
                    fs = fs + " ECE Sem :7 ";
                    no = 10;

                }
                sec.setText(fs);

                fileName = "test" + no + ".txt";
                line = "null";
                //System.out.println("In Sec1");
            } else if (BmController.batch == 20) {
                String fs = MtechlistController.course;
                                //System.out.println(fs);

                no = map.get(fs);
                                //System.out.println(no);

                fileName = "test" + no + ".txt";
                line = "null";
                sec.setText(trim(fs));

            } else if (BmController.batch == 30) {
                String fs = OtherlistController.course;
                no = map.get(fs);
                fileName = "test" + no + ".txt";
                line = "null";
                sec.setText(trim(fs));

            }

        }

        try {
            ////System.out.println("new:::"+no);
            FileReader f;
            BufferedReader b = null;
            if (no < 11) {
                f = new FileReader("lab" + no + ".txt");
                b = new BufferedReader(f);
            }
            String lab = " ";
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            StringTokenizer st = new StringTokenizer(line);
            int c = 0;
            while ((line = bufferedReader.readLine()) != null) {
                lab = " ";

                if (line.startsWith("SEC")) {
                    continue;
                }
                if (no < 11) {
                    lab = b.readLine();
                    if (lab.startsWith("nul")) {
                        lab = " ";
                    }
                }
                c++;
                String[] a = new String[6];
                int count = 0;
                st = new StringTokenizer(line, ";");
                while (st.hasMoreTokens()) {
                    String s = st.nextToken();
                    if (s.equals("")) {
                        s = " ";

                    } else if (s.startsWith("null")) {

                        s = " ";
                        a[count++] = s;
                    } else {
                        //s=s.replaceAll("Room no", line);

                        if (s.contains(":")) {
                            StringTokenizer newcolon = new StringTokenizer(s, ":");
                            String vips = newcolon.nextToken();
                            String vipsno = newcolon.nextToken();
                            ////System.out.println(vips+"hgvytvythu "+vipsno);

                            vipsno = vipsno.trim();
                            ////System.out.println(vipsno);

                            s = vips + ":" + roommap.get(vipsno);
                        }
                        s = s.replaceAll("!", "\n");
                        //System.out.println(s);
                        a[count++] = s;
                    }

                }

                list2.add(new Data(a[0], a[1], a[2], "TB \n " + "\n", a[3], a[4], "LUNCH BREAK", a[5], lab));
            }
        } catch (Exception e) {

        }
    }

    public void createPdf(String dest) {
        try {
            Document document = new Document(PageSize.A4.rotate());

            PdfWriter.getInstance(document, new FileOutputStream(dest));
            document.open();
            String sss = "Indian Institute of Information Technology, Allahabad" + "\n" + "Time Table" + "\n" + "Academic Semester: July-December 2017";
            Paragraph ssss = new Paragraph(sss + "\n \n \n");
            ssss.setAlignment(Element.ALIGN_CENTER);

            document.add(ssss);
            Paragraph sssss = new Paragraph(dest.substring(0, dest.length() - 3) + "\n \n");
            sssss.setAlignment(Element.ALIGN_CENTER);

            document.add(sssss);

            PdfPTable table = new PdfPTable(8);
            table.setWidthPercentage(100);
            String[] tableTitleList = {" " + "\n \n \n", "9-10 AM", "10-11 AM", "11-11:15 AM", "11:15-12:15 PM", "12:15-1:15 PM", "LUNCH BREAK", "6:00 PM-7:00 PM"};

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
                table.addCell(item.getG());
                table.addCell(item.getH());

            });
            boolean b = true;
            int ii = 0;
            for (PdfPRow r : table.getRows()) {
                if (ii == 0) {
                    PdfPCell[] c = r.getCells();
                    for (int jj = 0; jj < 8; jj++) {
                        c[jj].setBackgroundColor(new BaseColor(147, 220, 105));
                    }

                } else {
                    PdfPCell[] c = r.getCells();
                    c[0].setBackgroundColor(new BaseColor(147, 220, 105));

                }
                ii++;

            }
            document.add(table);
            document.close();

        } catch (DocumentException | FileNotFoundException ex) {
            Logger.getLogger(Sec1Controller.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

}
