// Data d = new Data(a[0], a[1], a[2], "TB \n " + "\n", a[3], a[4], "LUNCH BREAK", a[5], " ");
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timetable;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.util.StringTokenizer;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBody;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDocument1;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageSz;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STPageOrientation;

/**
 *
 * @author Rakalicious
 */
public class toDOC {

    public void create() {
        try {
            XWPFDocument document = new XWPFDocument();

            CTDocument1 doc = document.getDocument();
            CTBody body = doc.getBody();

            if (!body.isSetSectPr()) {
                body.addNewSectPr();
            }
            CTSectPr section = body.getSectPr();

            if (!section.isSetPgSz()) {
                section.addNewPgSz();
            }
            CTPageSz pageSize = section.getPgSz();

            pageSize.setOrient(STPageOrientation.LANDSCAPE);
            pageSize.setW(BigInteger.valueOf(15840));
            pageSize.setH(BigInteger.valueOf(12240));

            //Write the Document in file system
            FileOutputStream out = new FileOutputStream(new File("../Outputs/time_table_teacher.docx"));
            BufferedReader br = new BufferedReader(new FileReader("teacher.txt"));
            String line = null;
            while ((line = br.readLine()) != null) {

                if (line.startsWith("Teacher")) {
                    String tmp = line.substring(8);
                    tmp = tmp.substring(0, tmp.length() - 1);
                    int tcode = Integer.parseInt(tmp);
                    if (Sec1Controller.ttmap.containsKey(tcode)) {

                        XWPFParagraph para = document.createParagraph();
                        XWPFRun rh = para.createRun();
                        rh.setText("INDIAN INSTITUTE OF INFORMATION TECHNOLOGY, ALLAHABAD");
                        rh.setFontSize(15);
                        rh.setColor("ff0000");
                        para.setAlignment(ParagraphAlignment.CENTER);
                        para.setSpacingBefore(0);
                        para.setSpacingAfter(0);
                        rh.addBreak();
                        XWPFParagraph parag = document.createParagraph();
                        XWPFRun rh3 = parag.createRun();
                        rh3.setText(Sec1Controller.ttmap.get(tcode));
                        rh3.setFontSize(12);
                        rh3.setColor("0000ff");
                        parag.setAlignment(ParagraphAlignment.CENTER);
                        parag.setSpacingBefore(0);
                        parag.setSpacingAfter(0);
                        rh3.addBreak();

                        XWPFTable table = document.createTable();
                        XWPFTableRow tableRowOne = table.getRow(0);
                        tableRowOne.getCell(0).setText("Day");
                        tableRowOne.addNewTableCell().setText("9-10AM");
                        tableRowOne.addNewTableCell().setText("10-11AM");
                        tableRowOne.addNewTableCell().setText("11-11:15PM");
                        tableRowOne.addNewTableCell().setText("11:15-12:15PM");
                        tableRowOne.addNewTableCell().setText("12:15-1:15AM");
                        tableRowOne.addNewTableCell().setText("Lunch Break");

                        tableRowOne.addNewTableCell().setText("3-6PM");
                        tableRowOne.addNewTableCell().setText("6-7PM");
                        for (int i = 0; i < 5; i++) {
                            line = br.readLine();
                            // //System.out.println("spiderman spiderman");

                            String[] a = new String[6];
                            int count = 0;
                            StringTokenizer st = new StringTokenizer(line, ";");
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
                                    ////System.out.println("fnsdjuhnfuksdhfusfkskfsbksdbf" + s);
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
                                    String secname = Sec1Controller.smap.get(secc);
                                    ////System.out.println(secname);
                                    s = secname + "\n(" + openparen.nextToken();
                                    a[count++] = s;
                                }

                            }
                            XWPFTableRow tableRowTwo = table.createRow();
                            //  Data d = new Data(a[0], a[1], a[2], "TB \n " + "\n", a[3], a[4], "LUNCH BREAK", a[5], " ");      //
                            tableRowTwo.getCell(0).setText(a[0]);
                            tableRowTwo.getCell(1).setText(a[1]);
                            tableRowTwo.getCell(2).setText(a[2]);
                            tableRowTwo.getCell(3).setText("TB");
                            tableRowTwo.getCell(4).setText(a[3]);
                            tableRowTwo.getCell(5).setText(a[4]);
                            tableRowTwo.getCell(6).setText("Lunch Break");
                            tableRowTwo.getCell(7).setText(" ");
                            tableRowTwo.getCell(8).setText(a[5]);

                        }
                        //

                        document.createParagraph().createRun().addBreak();
                        XWPFParagraph paragraph = document.createParagraph();
                        paragraph.setPageBreak(true);

                    }
                }
            }
            document.write(out);

            //create table
            //create first row
            /*XWPFTableRow tableRowOne = table.getRow(0);
            tableRowOne.getCell(0).setText("col one, row one");
            tableRowOne.addNewTableCell().setText("col two, row one");
            tableRowOne.addNewTableCell().setText("col three, row one");

            //create second row
            XWPFTableRow tableRowTwo = table.createRow();
            tableRowTwo.getCell(0).setText("col one, row two");
            tableRowTwo.getCell(1).setText("col two, row two");
            tableRowTwo.getCell(2).setText("col three, row two");

            //create third row
            XWPFTableRow tableRowThree = table.createRow();
            tableRowThree.getCell(0).setText("col one, row three");
            tableRowThree.getCell(1).setText("col two, row three");
            tableRowThree.getCell(2).setText("col three, row three");*/
            document.close();
            out.close();
            //System.out.println("create_table.docx written successully");
        } catch (Exception ex) {
            //System.out.println("Error aa gaya");
            ex.printStackTrace();
        }
    }

}
