/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timetable;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author Vika$h
 */
public class runbat {

    private static String OS = System.getProperty("os.name").toLowerCase();

    public static boolean isWindows() {

        return (OS.indexOf("win") >= 0);

    }

    public static boolean isUnix() {

        return (OS.indexOf("nix") >= 0 || OS.indexOf("nux") >= 0 || OS.indexOf("aix") > 0);

    }

    runbat() {

        try {
            if (isUnix()) {
                
                String s = SelectionController.inputfile.getAbsolutePath();

                //Runtime.getRuntime().exec("/bin/bash -c g++ new.cpp");
                //Runtime.getRuntime().exec("/bin/bash -c ./a.out "+s);
                PrintWriter writer = new PrintWriter("new.sh", "UTF-8");
                System.out.println("tdsgyhfugi");
                // writer.println("g++ new.cpp");
                writer.println("./a.out " + s);
                writer.close();
                File f = new File("new.sh");

                String[] argss = new String[]{"/bin/bash", "-c", "bash -f new.sh"};
                Process procs = new ProcessBuilder(argss).start();

            } else if (isWindows()) {
                String s = SelectionController.inputfile.getAbsolutePath();

                //System.out.println("fsdfsdfghfusiohguiofsh\nguifahg");
                //for windows
                PrintWriter writer = new PrintWriter("new.bat", "UTF-8");

                writer.println("new.exe " + s);
                writer.println("exit");
                writer.close();
                String[] command = {"cmd.exe", "/C", "Start", "new.bat"};
                Process p = Runtime.getRuntime().exec(command);
                

            }
        } catch (IOException e) {
        }
    }

}
