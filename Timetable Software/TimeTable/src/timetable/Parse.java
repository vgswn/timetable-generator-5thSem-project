package timetable;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Rakalicious
 */
public class Parse {
    String infile;
    String opfile;
    HashMap<Integer, String> hm ;
    public Parse(String in){
        this.makeHashMap();
        infile = in;
    }
    public void parse()
    {
        parse("toC.txt");
    }
    public void makeHashMap(){
        BufferedReader br = null;
        try {
            hm = new HashMap();
            br = new BufferedReader(new FileReader(SelectionController.teacherfile.getAbsoluteFile()));
            String line = null;
            while ( (line = br.readLine())!=null ){
                StringTokenizer st = new StringTokenizer(line,"-");
                hm.put(Integer.parseInt(st.nextToken().trim()),st.nextToken().trim());
            }
        } catch (Exception ex) {
            Logger.getLogger(Parse.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                br.close();
            } catch (IOException ex) {
                Logger.getLogger(Parse.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    public void parse(String op){
        this.opfile = op;
        String line = null;
        try{
            FileReader fileReader = new FileReader(infile);
            BufferedReader br = new BufferedReader(fileReader);
            FileWriter fileWriter = new FileWriter(opfile);
            BufferedWriter bw = new BufferedWriter(fileWriter);
            line=br.readLine();
            bw.write(String.valueOf(line));

            while ( (line=br.readLine())!= null) {
                if (line.startsWith("Section No.")){
                    bw.newLine();
                    String section = line.substring(12).trim();
                    line = br.readLine();
                    int noOfSub = Integer.parseInt( line.substring(16).trim() );
                    line = br.readLine();
                    int noOfRoom = Integer.parseInt( line.substring(13).trim());
                    bw.write(section+" "+noOfSub);
                    bw.newLine();
                    ////System.out.println(noOfRoom);
                    bw.write(String.valueOf(noOfRoom));
                    bw.newLine();
                    bw.flush();
                    for ( int i = 0; i < noOfRoom ; i++){
                        line = br.readLine();
                        line = line.substring(5).trim();
                        bw.write(line);
                        bw.newLine();
                        bw.flush();
                    }
                    for ( int i = 0; i < noOfSub; i++){
                        //skip empty lines                     
                        line=br.readLine();
                        //work from here
                        line = br.readLine();
                        String subno = line.substring(12).trim();
                        line = br.readLine();
                        int elective =Integer.parseInt( line.substring(26).trim() );
                        if (elective < 2) elective = -1;
                        bw.write(subno+" "+elective);
                        bw.newLine();
                        bw.flush();
                        if (elective ==-1 ) elective = 1;
                        for (int j = 0 ; j < elective; j++){
                            String subCode = br.readLine().substring(13).trim();
                            String teacherCode = br.readLine().substring(13).trim();
                          //  to add teacherName change Here
                            bw.write(subCode);
                            bw.newLine();
                            bw.write(teacherCode);
                            bw.newLine();
                            bw.write(hm.get(Integer.parseInt(teacherCode)));
                            bw.newLine();
                            bw.flush();
                        }
                        line = br.readLine();
                        String linked = line.substring(14).trim();
                        if (linked.startsWith("NO")){
                            bw.write("0");
                            bw.newLine();
                            bw.flush();
                        } else {
                            StringTokenizer st= new StringTokenizer(linked,",");
                            String parent = st.nextToken();
                            parent = st.nextToken().trim();
                            bw.write("1");
                            bw.newLine();
                            bw.write(parent);
                            bw.newLine();
                            bw.flush();
                        }
                        
                    }
                    
                }
                
            }
            bw.close();
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
