/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.movieTheater.gui.popUpDialog;

import hr.movieTheater.dal.Repository;
import hr.movieTheater.dal.RepositorySingleton;
import hr.movieTheater.log.LogHandler;
import hr.movieTheater.log.LogHandlerSingleton;
import hr.movieTheater.model.Movie;
import java.sql.SQLException;
import java.util.Optional;

/**
 *
 * @author Josip
 */
public class MovieHandlerDialog extends javax.swing.JDialog {
    
    private final Optional<Movie> movie;

    private Repository repo;
    private LogHandler logHandler;
    private MovieHandlerPanel child;
    
    /**
     * Creates new form MovieHandlerDialog
     * @param parent
     * @param modal
     * @param movieId
     */
    public MovieHandlerDialog(java.awt.Frame parent, boolean modal, Optional<Movie> movie) {
        super(parent, modal);
        initComponents();
        this.movie = movie;
        init();
    }
    
    public MovieHandlerDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.movie = Optional.empty();
        init();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tbContent = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        tbContent.setLayout(new java.awt.GridLayout());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tbContent, javax.swing.GroupLayout.DEFAULT_SIZE, 950, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tbContent, javax.swing.GroupLayout.DEFAULT_SIZE, 750, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel tbContent;
    // End of variables declaration//GEN-END:variables
    
    private void init() {
       initRepository();
       initLogHandler();
       child = new MovieHandlerPanel(this, movie);
       tbContent.add(child);
       initTitle();
       
    }
        
    private void initRepository() {
        repo = RepositorySingleton.getInstance();
    }

    private void initLogHandler() {
        logHandler = LogHandlerSingleton.getInstance();
    }
    
    private void initTitle() {
        if (movie.isPresent()) {
            this.setTitle("A??uriraj film");
        }
        else{
            this.setTitle("Kreiraj film");
        }
    }

    public void saveData(Optional<Movie> m) throws SQLException{
        if (movie.isPresent()) {
            repo.updateMovie(m.get());
        }
        else{
            repo.createMovie(m.get());
        }
    }
    
    public MovieHandlerPanel getChildPanel(){
        return child;
    }

}