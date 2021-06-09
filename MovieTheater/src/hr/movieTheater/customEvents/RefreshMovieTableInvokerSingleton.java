/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.movieTheater.customEvents;

/**
 *
 * @author Josip
 */
public class RefreshMovieTableInvokerSingleton {
    private static RefreshMovieTableInvoker instance;

    private RefreshMovieTableInvokerSingleton() {
    }
    
    public static RefreshMovieTableInvoker getInstance(){
        if (instance == null) {
            createInstance();
        }
        return instance;
    }
    
    private static void createInstance() {
        instance = new RefreshMovieTableInvoker();
    }
    
}
