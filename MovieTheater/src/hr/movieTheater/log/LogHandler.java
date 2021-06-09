/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.movieTheater.log;

import hr.algebra.utils.FileUtils;
import hr.movieTheater.DirectoryInformationHandler;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import javafx.util.converter.LocalDateTimeStringConverter;
import javax.swing.text.DateFormatter;

/**
 *
 * @author Josip
 */
public class LogHandler {
    private Logger logger;
    private String logsDirectoryFolder = DirectoryInformationHandler.getLogDirectory() + File.separator;
    
    private static DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ISO_DATE;
    
    public LogHandler()
    {
        logger = Logger.getAnonymousLogger();

        configure();
    }

    private void configure()
    {
        try
        {
            FileUtils.createDirHierarchy(logsDirectoryFolder);
            FileHandler fileHandler = new FileHandler(logsDirectoryFolder + LocalDateTime.now().format(DATE_FORMAT) + ".log", true);
            
            SimpleFormatter formatter = new SimpleFormatter();
            fileHandler.setFormatter(formatter);
            
            logger.addHandler(fileHandler);
            
        } catch (IOException ex)
        {
            ex.printStackTrace();
            logger.log(Level.OFF, ex.getMessage(), ex);
        }
    }

    private String getCurrentDateString()
    {
        LocalDateTime date = LocalDateTime.now();
        return date.format(DATE_FORMAT);
    }

    public void log(String message, Throwable ex)
    {
        logger.log(Level.SEVERE, message, ex);
    }
}
