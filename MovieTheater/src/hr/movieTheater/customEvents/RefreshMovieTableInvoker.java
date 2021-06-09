/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.movieTheater.customEvents;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Josip
 */
public class RefreshMovieTableInvoker {
    private List<RefreshMovieTableListener> listeners = new ArrayList<>();
    
    public void addListener(RefreshMovieTableListener listener){
        listeners.add(listener);
    }
    
    public void invokeEvent(){
        if (!listeners.isEmpty()) {
            
            for (RefreshMovieTableListener l : listeners) {
                l.refreshTableMovieTable();
            }
        }
    }
}
