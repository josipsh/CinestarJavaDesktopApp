/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.movieTheater.log;

/**
 *
 * @author Josip
 */
public class LogHandlerSingleton {

    private LogHandlerSingleton() {
    }
    
    private static LogHandler instance;
    
    
    public static LogHandler getInstance(){
        if (instance == null) {
            createInstance();
        }
        return instance;
    }
    
    private static void createInstance() {
        instance = new LogHandler();
    }
    
}
