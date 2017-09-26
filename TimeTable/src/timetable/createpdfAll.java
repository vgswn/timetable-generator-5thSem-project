/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timetable;

import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author vips
 */
public class createpdfAll {
                ObservableList<Data> list = FXCollections.observableArrayList();

    createpdfAll(){
        
        
    }
    public void createPdf(String dest) {

        try {
            Document document = new Document(PageSize.A4.rotate());
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
