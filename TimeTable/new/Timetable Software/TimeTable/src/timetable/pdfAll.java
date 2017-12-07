/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timetable;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.GrayColor;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfGState;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPRow;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PdfWriter;
import static com.sun.org.apache.xalan.internal.lib.ExsltDynamic.map;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
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
    ObservableList<Data> list_ = FXCollections.observableArrayList();

    pdfAll(String dest) throws FileNotFoundException, DocumentException, BadElementException, IOException {/*
        HashMap<Integer, String> map = new HashMap<Integer, String>();

        map.put(9, "BTech(IT) –VII Semester  ");
        map.put(10, "BTech(ECE) –VII Semester  ");
        map.put(11, "Dual Degree B.Tech.(ECE) MTech(MI)  ");
        map.put(12, "M.Tech.(MI) – I Semester  ");
        map.put(13, "M.Tech.(MI) – III Semester  ");
        map.put(14, "BME –VII Sem (Dual degree) I Sem (MTech)  ");
        map.put(15, "BME –IX Sem (Integrated)  ");
        map.put(16, "B.Tech. DUAL(IT) –VII WCE-  ");
        map.put(17, "M.Tech. (WCE) –I Semester  ");
        map.put(18, "B.Tech. DUAL(IT) – VII CLIS-  ");
        map.put(19, "M.Tech.(CLIS) –I Semester  ");
        map.put(20, "B. Tech.DUAL(IT) –VII SE-  ");
        map.put(21, "M.Tech.(SE) –I Semester  ");
        map.put(22, "B.Tech.DUAL (IT) –VII HCI-  ");
        map.put(23, "M.Tech.(HCI) –I Semester  ");
        map.put(24, "B.Tech.DUAL(IT) – VII IS-  ");
        map.put(25, "M.Tech.(IS) –I Semester  ");
        map.put(26, "B.Tech. DUAL (IT) –VII RO-  ");
        map.put(27, "M.Tech.(RO) – I Semester  ");
        map.put(28, "M.Tech.(IS) – III Semester  ");
        map.put(29, "M.Tech.(HCI)– III Semester  ");
        map.put(30, "M.Tech.(RO)– III Semester  ");
        map.put(31, "M.Tech.(SE)– III Semester  ");
        map.put(32, "M.Tech.(WCE)– III Semester  ");
        map.put(33, "M.Tech.(CLIS)– III Semester  ");
        map.put(34, "BI–VII Semester (Dual degree)I Semester (MTech)  ");
        map.put(35, "BI–III Semester (MTech)  ");
        map.put(36, "B. Tech.DUAL(IT) –VII MBA  ");
        map.put(37, "B. Tech.DUAL(ECE) –VII MBA  ");
        Rectangle pageSize = new Rectangle(PageSize.A4.rotate());
//pageSize.setBackgroundColor(new BaseColor(84, 141, 212));
        Document document = new Document(pageSize);

        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("AllBatchesPdfs.pdf"));
        document.open();
        HashMap<String, Integer> m = new HashMap<String, Integer>();

        for (int i = 0; i < 38; i++) {
            //document.add(img);

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
                if (i > 9) {
                    fs = map.get(i);
                }
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
                int x;
                x = 0;
                while ((line = bufferedReader.readLine()) != null) {

                    if (line.startsWith("SEC")) {
                        continue;
                    }
                    String[] a = new String[6];
                    int count = 0;
                    st = new StringTokenizer(line, ";");
                    int flagg = 0;
                    while (st.hasMoreTokens()) {
                        flagg++;

                        String s = st.nextToken();

                        if (s.equals("$")) {
                            s = " ";
                        }
                        if (s.startsWith("null")) {
                            s = " ";
                        }
                        String news = s;
                        /*if(!s.equals(" "))
                             news=s.substring(0,7);*/
                        //System.out.println(news);

                        /*if (!m.containsKey(news) && flagg != 1 && !s.equals(" ")) {
                            m.put(news, x);
                            x++;
                        }
                        System.out.println(news + " " + m.get(news));
                        a[count++] = s;
                        //System.out.print("newnewnewnew "+x);

                        //  System.out.println(s);
                    }

                    //list.add(new Data(a[0], a[1], a[2], "TEA BREAK" + "\n \n \n \n", a[3], a[4], "LUNCH BREAK", a[5]));
                    //list_.add(new Data(a[0], a[1], a[2], "TEA BREAK" + "\n \n \n \n", a[3], a[4], "LUNCH BREAK", a[5]));

                }
                System.out.println();

                PdfPTable table = new PdfPTable(8);
                table.setWidthPercentage(100);
                String[] tableTitleList = {" " + "\n \n \n", "9-10 AM", "10-11 AM", "11-11:15 AM", "11:15-12:15 PM", "12:15-1:15 PM", "LUNCH BREAK", "6:00-7:00 PM"};

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
                ArrayList<String> lol = new ArrayList();
                list.stream().forEach((item) -> {
                    lol.add(item.getA());
                    lol.add(item.getB());
                    lol.add(item.getC());
                    lol.add(item.getD());
                    lol.add(item.getE());
                    lol.add(item.getF());
                    lol.add(item.getG());
                    lol.add(item.getH());
                }
                );
                
                /*
                0-239-239-88
1-255,74,61
2-94,213,237
3-154,237,94
4-white
5-232,113,222
6-98,88,239
7-239,156,88

                        *//*
int[][] multi = new int[10][];
multi[0] = new int[10];
multi[1] = new int[10];
multi[2] = new int[10];
multi[3] = new int[10];
multi[4] = new int[10];
int k;
    for (k=0;k<5;k++)
    {
        int j;
        for(j=0;j<8;j++)
        {
            int in=i*8+j;
            String ccccc=lol.get(in);
            
            multi[i][j]=m.get(ccccc);
            
        }
    }*/
                 /* int xx=2*8+1;
                        String news=lol.get(xx);
                        int index=m.get(news);
                        System.out.println("asdfghjgfdsfghjhgfdsfghjgfd "+index);
                int[][] clr = new int[][]{
                    {239,239,88},
                    {255, 74, 61},
                    {94,213,237},
                    {154,237,94},
                    {232,113,222},
                    {98,88,239},
                    {239,156,88}
                    
                };
                
                
                boolean b = true;
                int ii = 0;
                
                for (PdfPRow r : table.getRows()) {
                   
                    if (ii == 0) {

                        PdfPCell[] c = r.getCells();

                        for (int jj = 0; jj < 8; jj++) {
                            c[jj].setBackgroundColor(new BaseColor(50, 255, 50));

                        }

                    }
                    
                    else {
                        PdfPCell[] c = r.getCells();
                        c[0].setBackgroundColor(new BaseColor(50, 255, 50));
                       
                      
                       // System.out.println("newwwwwwwwwwwwwwwwwwwwwwwwwwwwwww"+cl+" "+m.get(cl));
                       //System.out.println("newwwwwwwwwwwwwwwwwwwwwwwwwwwwwww"+cl+" "+m.get(cl)+" "+clr[cll][0]+" "+ clr[cll][1]+" "+clr[cll][2]);

                        //System.out.println("new debug "+cll);
                        //c[1].setBackgroundColor(new BaseColor(clr[index][0], clr[index][1], clr[index][2]));

                    }
                    /*ii++;*/
                  /*  ii++;
                }
                document.add(table);
                list.clear();
                list_.clear();
                document.newPage();

            } catch (Exception e) {

            }
        }
        int ll;

        /*for(String q:m.keySet())
       {
           System.out.println(q+" "+m.get(q));
       }*/
      /*  try {

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
      /*  PdfReader reader = new PdfReader("AllBatchesPdfs.pdf");
        PdfStamper stamper = new PdfStamper(reader, new FileOutputStream("watermarked.pdf"));

        // text watermark
        Font FONT = new Font(Font.FontFamily.HELVETICA, 34, Font.BOLD, new GrayColor(0.5f));
        Phrase p = new Phrase("Memorynotfound (watermark)", FONT);

        // image watermark
        Image img = Image.getInstance("IIIT_logo.jpg");
        float w = img.getScaledWidth();
        float h = img.getScaledHeight();

        // properties
        PdfContentByte over;
        Rectangle pagesize;
        float x, y;

        // loop over every page
        int n = reader.getNumberOfPages();
        for (int i = 1; i <= n; i++) {

            // get page size and position
            pagesize = reader.getPageSizeWithRotation(i);
            x = pagesize.getLeft() / 2;
            y = pagesize.getTop() / 2;
            over = stamper.getOverContent(i);
            //over.setFillOpacity(0.5f);

            over.saveState();

            System.out.print(x + " " + y);

            // set transparency
            PdfGState state = new PdfGState();
            state.setFillOpacity(1.0f);
            over.setGState(state);

            // add watermark text and image
            over.addImage(img, w, 0, 0, h, x + 50, y + 150);

            over.restoreState();
        }
        stamper.close();
        reader.close();

    }*/

    }
}
