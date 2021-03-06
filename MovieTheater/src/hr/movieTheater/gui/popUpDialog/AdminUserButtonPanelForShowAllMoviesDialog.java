/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.movieTheater.gui.popUpDialog;

import hr.algebra.utils.FileUtils;
import hr.algebra.utils.MessageUtils;
import hr.movieTheater.DirectoryInformationHandler;
import hr.movieTheater.customEvents.RefreshMovieTableInvoker;
import hr.movieTheater.customEvents.RefreshMovieTableInvokerSingleton;
import hr.movieTheater.dal.Repository;
import hr.movieTheater.dal.RepositorySingleton;
import hr.movieTheater.log.LogHandler;
import hr.movieTheater.log.LogHandlerSingleton;
import hr.movieTheater.model.Movie;
import hr.movieTheater.parsers.rss.MovieParser;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.stream.XMLStreamException;


/**
 *
 * @author Josip
 */
public class AdminUserButtonPanelForShowAllMoviesDialog extends javax.swing.JPanel {

    private LogHandler logHandler;
    private Repository repo;
    
    private ShowAllMoviesDialog parent;
    
    private Optional<Integer> idMovie;
    
    private RefreshMovieTableInvoker invoker;
    
    /**
     * Creates new form AdminUserButtonPanelForShowAllMovieDialog
     */
    public AdminUserButtonPanelForShowAllMoviesDialog(ShowAllMoviesDialog parent) {
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

        btnUpdateDataBase = new javax.swing.JButton();
        btnDeleteDataBase = new javax.swing.JButton();
        btnShowMovie = new javax.swing.JButton();

        btnUpdateDataBase.setText("A??uriraj bazu podataka");
        btnUpdateDataBase.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateDataBaseActionPerformed(evt);
            }
        });

        btnDeleteDataBase.setText("Obri??i sve podatke u bazi podataka");
        btnDeleteDataBase.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteDataBaseActionPerformed(evt);
            }
        });

        btnShowMovie.setText("Prikazi film");
        btnShowMovie.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnShowMovieActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(23, Short.MAX_VALUE)
                .addComponent(btnShowMovie, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addComponent(btnUpdateDataBase, javax.swing.GroupLayout.PREFERRED_SIZE, 358, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(btnDeleteDataBase, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(16, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnUpdateDataBase)
                    .addComponent(btnDeleteDataBase)
                    .addComponent(btnShowMovie))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnShowMovieActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnShowMovieActionPerformed
        idMovie = parent.getMovieId();
        
        if (idMovie.isPresent()) {
            new ShowMovieDialog(parent.getParentFrame(), true, idMovie.get()).setVisible(true);
            parent.updateTable();
            invoker.invokeEvent();
        }
        else{
            MessageUtils.showInformationMessage("Korisni??ka kre??ka", "Potrebno je ozna??iti film");
        }
        
    }//GEN-LAST:event_btnShowMovieActionPerformed

    private void btnUpdateDataBaseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateDataBaseActionPerformed
        try {
            MessageUtils.showInformationMessage("Sve je ok", "A??uriranje je pokrenuto! Kliknite OK kako bi se nastavilo");
            repo.createMovies(MovieParser.parse());
            MessageUtils.showInformationMessage("Sve je ok", "Baza podataka je uspje??no a??urirana");
            parent.updateTable();
            invoker.invokeEvent();
        }catch (SQLException ex) {
            logHandler.log(ex.getMessage(), ex);
            MessageUtils.showErrorMessage("Error br. 10", "Do??lo je to problema tijekom dohva??anja podataka za tablicu. Pogledajte dokumentaciju kako biste otklonili problem.");
            Logger.getLogger(ShowAllMoviesDialog.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            logHandler.log(ex.getMessage(), ex);
            MessageUtils.showErrorMessage("Error br. 50", "Do??lo je do problema tijekom konekcije. Pogledajte dokumentaciju kako biste otklonili problem.");
            Logger.getLogger(AdminUserButtonPanelForShowAllMoviesDialog.class.getName()).log(Level.SEVERE, null, ex);
        } catch (XMLStreamException ex) {
            logHandler.log(ex.getMessage(), ex);
            MessageUtils.showErrorMessage("Error br. 40", "Do??lo je do problema tijekom parsiranja. Pogledajte dokumentaciju kako biste otklonili problem.");
            Logger.getLogger(AdminUserButtonPanelForShowAllMoviesDialog.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            logHandler.log(ex.getMessage(), ex);
            MessageUtils.showErrorMessage("Error br. 100", "Do??lo je do nepoznate pogre??ke. Javite se adminu.");
            Logger.getLogger(AdminUserButtonPanelForShowAllMoviesDialog.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_btnUpdateDataBaseActionPerformed

    private void btnDeleteDataBaseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteDataBaseActionPerformed
        boolean isOkToDeleteMovies = MessageUtils.showConfirmDialog("Jeste li sigurni", "Jeste li sigurni da ??elite obrisati sve filmove?") == 0;
        if (isOkToDeleteMovies) {
            try {
                deletePictures();
                repo.deleteMovieData();
                MessageUtils.showInformationMessage("Sve je ok", "Baza podataka je uspje??no obrisana");
                parent.updateTable();
                invoker.invokeEvent();
            } catch (SQLException ex) {
                logHandler.log(ex.getMessage(), ex);
                MessageUtils.showErrorMessage("Error br. 10", "Do??lo je to problema tijekom brisanja podataka. Pogledajte dokumentaciju kako biste otklonili problem.");
                Logger.getLogger(AdminUserButtonPanelForShowAllMoviesDialog.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                logHandler.log(ex.getMessage(), ex);
                MessageUtils.showErrorMessage("Error br. 50", "Do??lo je to problema tijekom brisanja slika. Pogledajte dokumentaciju kako biste otklonili problem.");
                Logger.getLogger(AdminUserButtonPanelForShowAllMoviesDialog.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
               logHandler.log(ex.getMessage(), ex);
                MessageUtils.showErrorMessage("Error br. 100", "Do??lo je do nepoznate pogre??ke. Javite se adminu.");
            }
        }   
    }//GEN-LAST:event_btnDeleteDataBaseActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDeleteDataBase;
    private javax.swing.JButton btnShowMovie;
    private javax.swing.JButton btnUpdateDataBase;
    // End of variables declaration//GEN-END:variables

    private void init() {
        initLogHandler();
        initRepository();
        initInvoker();
    }

    private void initLogHandler() {
        logHandler = LogHandlerSingleton.getInstance();
    }

    private void initRepository() {
        repo = RepositorySingleton.getInstance();
    }

    private void initInvoker() {
        invoker = RefreshMovieTableInvokerSingleton.getInstance();
    }

    private void deletePictures() throws SQLException, IOException {
        File f = new File(DirectoryInformationHandler.getAssetDirectory());
        for (File fd : f.listFiles()) {
            fd.delete();
        }
    }
    
}
