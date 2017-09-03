/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timetable;

import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author Vika$h
 */
public class runbat {

    runbat(){
         try{
             //for windows
    PrintWriter writer = new PrintWriter("new.bat", "UTF-8");
    writer.println("a.exe");
    writer.println("exit");
    //for Linux
   /* PrintWriter writer = new PrintWriter("new.sh", "UTF-8");
    writer.println("g++ new.cpp");
    writer.println("./a.out");
*/
    writer.close();
            //for windows
            String[] command = {"cmd.exe", "/C", "Start", "new.bat"};
            Process p =  Runtime.getRuntime().exec(command);  
           //for linuux
           /*String target = new String("/home/vips/NetBeansProjects/TimeTable/new.sh");
                        Runtime rt = Runtime.getRuntime();
                        Process proc = rt.exec(target);*/
                   }
        catch (IOException e) {
}
    }
    
}

