/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.movieTheater.gui.popUpDialog;

import hr.algebra.utils.MessageUtils;
import hr.movieTheater.dal.Repository;
import hr.movieTheater.dal.RepositorySingleton;
import hr.movieTheater.log.LogHandler;
import hr.movieTheater.log.LogHandlerSingleton;
import hr.movieTheater.model.Movie;
import hr.movieTheater.model.Person;
import java.sql.SQLException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 *
 * @author Josip
 */
public class ClientUserButtonPanelForShowAllMoviesDialog extends javax.swing.JPanel {

    private LogHandler logHandler;
    private Repository repo;
    
    private ShowAllMoviesDialog parent;
    private Optional<Person> person;
    private Optional<Movie> movie;
    
    /**
     * Creates new form ClientUserButtonPanelForShowAllMoviesDialog
     */
    public ClientUserButtonPanelForShowAllMoviesDialog(ShowAllMoviesDialog parent) {
        initComponents();
        this.parent = parent;
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

        btnUpdate = new javax.swing.JButton();
        tfInformation = new javax.swing.JTextField();

        setPreferredSize(new java.awt.Dimension(990, 50));

        btnUpdate.setText("Ažuriraj");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tfInformation, javax.swing.GroupLayout.DEFAULT_SIZE, 870, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnUpdate)
                    .addComponent(tfInformation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(16, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        initMovie();
        if (movie.isPresent()) {
            //moze dalje
            new MovieHandlerDialog((JFrame)SwingUtilities.getWindowAncestor(parent), true, movie).setVisible(true);
            parent.updateTable();
        }
        else{
            MessageUtils.showInformationMessage("Korisniška greška", "Potrebno je označiti film");
        }
    }//GEN-LAST:event_btnUpdateActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnUpdate;
    private javax.swing.JTextField tfInformation;
    // End of variables declaration//GEN-END:variables

    private void init() {
        initLogHandler();
        initRepository();
        initPerson();
        initTextField();
        
    }
    
    private void initLogHandler() {
        logHandler = LogHandlerSingleton.getInstance();
    }

    private void initRepository() {
        repo = RepositorySingleton.getInstance();
    }
    private void initPerson() {
        try {
            person = repo.getPerson(parent.getPersonId().get());
        } catch (SQLException ex) {
            logHandler.log(ex.getMessage(), ex);
            MessageUtils.showErrorMessage("Error br. 10", "Došlo je do pogreške tijeko mdohvačanja podataka. Za pomoć obratite se adminu");
            Logger.getLogger(ClientUserButtonPanelForShowAllMoviesDialog.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            logHandler.log(ex.getMessage(), ex);
            MessageUtils.showErrorMessage("Error br. 100", "Došlo je do nepoznate pogreške. Javite se adminu.");
        }
    }

    private void initTextField() {
        if (person.isPresent()) {
            tfInformation.setText("Film dodajete osobi " + person.get().toString() + " na nacin da ažurirate film. Odnosno, pridružite osobu filmu");
        }
        else{
            tfInformation.setText("Film dodajete osobi na nacin da ažurirate film. Odnosno, pridružite osobu filmu");
        }
    }

    private void initMovie() {
        if (parent.getMovieId().isPresent()) {
            try 
            {
                movie = repo.getMovie(parent.getMovieId().get());
            } catch (SQLException ex) {
                logHandler.log(ex.getMessage(), ex);
                MessageUtils.showErrorMessage("Error br. 10", "Došlo je do pogreške tijeko mdohvačanja podataka. Za pomoć obratite se adminu");
                Logger.getLogger(ClientUserButtonPanelForShowAllMoviesDialog.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                logHandler.log(ex.getMessage(), ex);
                MessageUtils.showErrorMessage("Error br. 100", "Došlo je do nepoznate pogreške. Javite se adminu.");
            }
        }
        else{
            movie = Optional.empty();
        }
    }
}