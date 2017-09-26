/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timetable;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author vips
 */
public class pdfAll {

    ObservableList<Data> list = FXCollections.observableArrayList();

    pdfAll(String dest) throws FileNotFoundException, DocumentException {
        Document document = new Document(PageSize.A4.rotate());
       PdfWriter writer= PdfWriter.getInstance(document, new FileOutputStream(dest));
                                    document.open();
        for (int i = 0; i <10; i++) {

            try {
               
                String fileName = "test" + i + ".txt";
                String line = "null";

            String fs = "B.Tech ";
            int year;
            int sec;
            year=i/3;
            sec=i%3+1;
            
            if (sec < 3) {
                fs = fs + " IT ";
                fs = fs + " Sem : " + Integer.toString(2 * year + 1);
                fs = fs + " Sec : " + Integer.toString(sec);
                if(year==3)
                {
                    fs=fs+" + Sec 2";
                }

            } else {
                fs = fs + " ECE ";
                fs = fs + " Sem : " + Integer.toString(2 * year+ 1);

            }
            
           document.add(new Paragraph(fs+"\n \n \n"));

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

                    list.add(new Data(a[0], a[1], a[2], "TEA BREAK"+"\n \n \n \n", a[3], a[4]));
                }

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

}
}
