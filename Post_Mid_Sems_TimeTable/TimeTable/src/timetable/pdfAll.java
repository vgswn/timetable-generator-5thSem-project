/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timetable;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPRow;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;
import static com.sun.org.apache.xalan.internal.lib.ExsltDynamic.map;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Cell;
import static javafx.scene.text.Font.font;
import static jdk.nashorn.internal.objects.NativeArray.map;
import static jdk.nashorn.internal.objects.NativeDebug.map;

/**
 *
 * @author vips
 */
public class pdfAll {
    

    ObservableList<Data> list = FXCollections.observableArrayList();

    pdfAll(String dest) throws FileNotFoundException, DocumentException {
             HashMap<Integer,String> map = new HashMap<Integer,String>();     
                

map.put(9,"BTech(IT) –VII Semester  ");
map.put(10,"BTech(ECE) –VII Semester  ");
map.put(11,"Dual Degree B.Tech.(ECE) MTech(MI)  ");
map.put(12,"M.Tech.(MI) – I Semester  ");
map.put(13,"M.Tech.(MI) – III Semester  ");
map.put(14,"BME –VII Sem (Dual degree) I Sem (MTech)  ");
map.put(15,"BME –IX Sem (Integrated)  ");
map.put(16,"B.Tech. DUAL(IT) –VII WCE-  ");
map.put(17,"M.Tech. (WCE) –I Semester  ");
map.put(18,"B.Tech. DUAL(IT) – VII CLIS-  ");
map.put(19,"M.Tech.(CLIS) –I Semester  ");
map.put(20,"B. Tech.DUAL(IT) –VII SE-  ");
map.put(21,"M.Tech.(SE) –I Semester  ");
map.put(22,"B.Tech.DUAL (IT) –VII HCI-  ");
map.put(23,"M.Tech.(HCI) –I Semester  ");
map.put(24,"B.Tech.DUAL(IT) – VII IS-  ");
map.put(25,"M.Tech.(IS) –I Semester  ");
map.put(26,"B.Tech. DUAL (IT) –VII RO-  ");
map.put(27,"M.Tech.(RO) – I Semester  ");
map.put(28,"M.Tech.(IS) – III Semester  ");
map.put(29,"M.Tech.(HCI)– III Semester  ");
map.put(30,"M.Tech.(RO)– III Semester  ");
map.put(31,"M.Tech.(SE)– III Semester  ");
map.put(32,"M.Tech.(WCE)– III Semester  ");
map.put(33,"M.Tech.(CLIS)– III Semester  ");
map.put(34,"BI–VII Semester (Dual degree)I Semester (MTech)  ");
map.put(35,"BI–III Semester (MTech)  ");
map.put(36,"B. Tech.DUAL(IT) –VII MBA  ");
map.put(37,"B. Tech.DUAL(ECE) –VII MBA  ");
        Rectangle pageSize = new Rectangle(PageSize.A4.rotate());
//pageSize.setBackgroundColor(new BaseColor(84, 141, 212));
        Document document = new Document(pageSize);

        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("AllBatchesPdfs.pdf"));
        document.open();
        for (int i = 0; i < 38; i++) {

            try {

                String fileName = "test" + i + ".txt";
                String line = "null";

                String fs = "B.Tech ";
                int year;
                int sec;
                year = i / 3;
                sec = i % 3 + 1;

                if (sec < 3) {
                    fs = fs + " IT ";
                    fs = fs + " Sem : " + Integer.toString(2 * year + 1);
                    fs = fs + " Sec : " + Integer.toString(sec);
                    if (year == 3) {
                        fs = fs + " + Sec 2";
                    }

                } else {
                    fs = fs + " ECE ";
                    fs = fs + " Sem : " + Integer.toString(2 * year + 1);

                }
                if(i>9)
                    fs=map.get(i);
                String sss = "Indian Institute of Information Technology, Allahabad" + "\n" + "Time Table" + "\n" + "Academic Semester: July-December 2017";
                Paragraph ssss = new Paragraph(sss + "\n \n \n");
                ssss.setAlignment(Element.ALIGN_CENTER);

                document.add(ssss);
                Paragraph sssss = new Paragraph(fs + "\n \n");
                sssss.setAlignment(Element.ALIGN_CENTER);

                document.add(sssss);

                FileReader fileReader = new FileReader(fileName);
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                StringTokenizer st = new StringTokenizer(line);
                while ((line = bufferedReader.readLine()) != null) {
                    if(line.startsWith("SEC"))
                        continue;
                    String[] a = new String[6];
                    int count = 0;
                    st = new StringTokenizer(line, ";");
                    while (st.hasMoreTokens()) {
                        String s = st.nextToken();
                        if (s.equals("$")) {
                            s = " ";
                        }
                        if(s.startsWith("null"))
                            s = " ";
                            

                        a[count++] = s;
                        //  System.out.println(s);
                    }

                    list.add(new Data(a[0], a[1], a[2], "TEA BREAK" + "\n \n \n \n", a[3], a[4],"LUNCH BREAK",a[5]));
                }

                PdfPTable table = new PdfPTable(8);
                table.setWidthPercentage(100);
                String[] tableTitleList = {" " + "\n \n \n", "9-10 AM", "10-11 AM", "11-11:15 AM", "11:15-12:15 PM", "12:15-1:15 PM","LUNCH BREAK","6:00-7:00 PM"};

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
                        for (int jj = 0; jj <8 ; jj++) {
                            c[jj].setBackgroundColor(new BaseColor(50, 255, 50));
                        }

                    } else {
                        PdfPCell[] c = r.getCells();
                        c[0].setBackgroundColor(new BaseColor(50, 255, 50));

                    }
                    ii++;

                }
                document.add(table);
                list.clear();
                document.newPage();

            } catch (Exception e) {

            }
        }
        try {

        } catch (Exception ex) {
            Logger.getLogger(Sec1Controller.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

        document.close();

        /*Rectangle pageSized = new Rectangle(PageSize.A4.rotate());
//pageSize.setBackgroundColor(new BaseColor(84, 141, 212));
        Document documents = new Document(pageSize);

        PdfWriter writers = PdfWriter.getInstance(documents, new FileOutputStream("AllTeachers.pdf"));
        documents.open();
        String fileName = "teacher.txt";
        FileReader fileReader = new FileReader(fileName);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        for (int k = 0; k < 42; k++) {

            try {

                
                String fs = "Teacher:";
                String line = "null";

                try {
                    int f = 0;
                    
                    
                    StringTokenizer st = new StringTokenizer(line);
                    int flag = 0;
                    line = bufferedReader.readLine();
                    fs = fs + " " + line;
                    for (int j = 0; j < 5; j++) {
                        String[] a = new String[6];
                        line = bufferedReader.readLine();

                        int i;
                        for (i = 0; i < 6; i++) {
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
                        

                            

                    list.add(new Data(a[0], a[1], a[2], "TEA BREAK" + "\n \n \n \n", a[3], a[4],"LUNCH BREAK",a[5]));

                            
                           
                        }

                    }

                 catch (Exception e) {

                }
                String sss = "Indian Institute of Information Technology, Allahabad" + "\n" + "Time Table" + "\n" + "Academic Semester: July-December 2017";
                Paragraph ssss = new Paragraph(sss + "\n \n \n");
                ssss.setAlignment(Element.ALIGN_CENTER);

                documents.add(ssss);
                Paragraph sssss = new Paragraph(fs + "\n \n");
                sssss.setAlignment(Element.ALIGN_CENTER);

                documents.add(sssss);

                

                PdfPTable table = new PdfPTable(8);
                table.setWidthPercentage(100);
                String[] tableTitleList = {" " + "\n \n \n", "9-10 AM", "10-11 AM", "11-11:15 AM", "11:15-12:15 PM", "12:15-1:15 PM","LUNCH BREAK","LAB 3:00 PM-6:00 PM"};

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
                            c[jj].setBackgroundColor(new BaseColor(50, 255, 50));
                        }

                    } else {
                        PdfPCell[] c = r.getCells();
                        c[0].setBackgroundColor(new BaseColor(50, 255, 50));

                    }
                    ii++;

                }
                documents.add(table);
                list.clear();
                documents.newPage();

            } catch (Exception e) {

            }
        }
        try {

        } catch (Exception ex) {
            Logger.getLogger(Sec1Controller.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

        documents.close();
*/
    }
}
