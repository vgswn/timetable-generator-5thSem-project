/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timetable;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Rakalicious
 */
public class delete_txt {

    delete_txt() throws IOException {
        for (int i = 0; i < 38; i++) {
            File f = new File("test" + i + ".txt");
            f.delete();

        }
        File f = new File("teacher.txt");
        f.delete();
    }

}
