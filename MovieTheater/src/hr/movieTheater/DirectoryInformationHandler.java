/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.movieTheater;

import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileSystemView;

/**
 *
 * @author Josip
 */
public class DirectoryInformationHandler {
    
    private static String MY_DOCUMENT_DIRECTORY = getMyDocumentDirectory();
    private static String PROJECT_DIRECTORY = MY_DOCUMENT_DIRECTORY + File.separator + "CinestarAppStorage";
    
    public static String getAssetDirectory(){
        return PROJECT_DIRECTORY + File.separator +"assets";
    }
    
    public static String getLogDirectory(){
        return PROJECT_DIRECTORY + File.separator +"chache";
    }

    private static String getMyDocumentDirectory() {
        JFileChooser fr = new JFileChooser();
        FileSystemView fs = fr.getFileSystemView();
        
        return fs.getDefaultDirectory().toString();
    }
}
