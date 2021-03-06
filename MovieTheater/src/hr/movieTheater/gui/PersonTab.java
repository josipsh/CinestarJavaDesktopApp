/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.movieTheater.gui;

import hr.algebra.utils.MessageUtils;
import hr.movieTheater.customEvents.RefreshMovieTableInvoker;
import hr.movieTheater.customEvents.RefreshMovieTableInvokerSingleton;
import hr.movieTheater.customEvents.RefreshMovieTableListener;
import hr.movieTheater.log.LogHandler;
import hr.movieTheater.dal.Repository;
import hr.movieTheater.dal.RepositorySingleton;
import hr.movieTheater.gui.popUpDialog.ShowAllMoviesDialog;
import hr.movieTheater.gui.popUpDialog.ShowMovieDialog;
import hr.movieTheater.log.LogHandlerSingleton;
import hr.movieTheater.model.Movie;
import hr.movieTheater.model.MovieTableModelForPersonsTab;
import hr.movieTheater.model.Person;
import hr.movieTheater.model.PersonTableModel;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.text.JTextComponent;

/**
 *
 * @author Josip
 */
public class PersonTab extends javax.swing.JPanel implements RefreshMovieTableListener {

    private MovieTableModelForPersonsTab movieModel;
    private PersonTableModel personsModel;
    
    private Repository repo;
    private LogHandler logHandler;
    
    List<JTextComponent> textValidationFields;
    List<JLabel> errorFields;
    
    private Person selectedPerson;
    private int selectedRowPersonTable = -1;
   
    private Movie selectedMovie;
    private int selectedRowMovieTable = -1;
    
    private RefreshMovieTableInvoker eventToRespont;
    /**
     * Creates new form PersonTab
     */
    public PersonTab() {
        initComponents();
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

        jScrollPane1 = new javax.swing.JScrollPane();
        tPersons = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        tfFirstName = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        tfMiddleName = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        tfLastName = new javax.swing.JTextField();
        btnAdd = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tMovies = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        btnAddNewMovie = new javax.swing.JButton();
        btnShowMovieInformation = new javax.swing.JButton();
        lblLastNameError = new javax.swing.JLabel();
        lblFirstNameError = new javax.swing.JLabel();
        lblRoleError = new javax.swing.JLabel();
        tfRole = new javax.swing.JTextField();

        tPersons.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tPersons.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tPersonsMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tPersons);

        jLabel1.setText("Ime:");

        jLabel2.setText("Srednje ime:");

        jLabel3.setText("Prezime:");

        btnAdd.setText("Dodaj ");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnUpdate.setText("A??uriraj");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnDelete.setText("Obri??i");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        jLabel4.setText("Zanimanje:");

        tMovies.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tMovies.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tMoviesMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tMovies);

        jLabel5.setText("Filmovi u kojima sudjeluje:");

        btnAddNewMovie.setText("Dodaj film");
        btnAddNewMovie.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddNewMovieActionPerformed(evt);
            }
        });

        btnShowMovieInformation.setText("Prikazi iv??e informacija o filmu");
        btnShowMovieInformation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnShowMovieInformationActionPerformed(evt);
            }
        });

        lblLastNameError.setForeground(new java.awt.Color(204, 51, 0));

        lblFirstNameError.setForeground(new java.awt.Color(204, 51, 0));

        lblRoleError.setForeground(new java.awt.Color(204, 51, 0));

        tfRole.setEditable(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(37, 37, 37)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tfMiddleName)
                                    .addComponent(tfFirstName)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(37, 37, 37)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(tfLastName)
                                    .addComponent(tfRole, javax.swing.GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(33, 33, 33)
                                .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btnDelete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblLastNameError, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblRoleError, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblFirstNameError, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(34, 34, 34)
                                .addComponent(btnAddNewMovie, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnShowMovieInformation, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1115, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(25, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfFirstName, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(btnAddNewMovie)
                    .addComponent(btnShowMovieInformation)
                    .addComponent(lblFirstNameError, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfMiddleName, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfLastName, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblLastNameError, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(tfRole, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lblRoleError, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(44, 44, 44)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(93, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        if (isFormOk() && selectedRowPersonTable != -1) {
            try {
                repo.updatePerson(new Person(
                        selectedPerson.getId(),
                        tfFirstName.getText().trim(),
                        tfMiddleName.getText().trim(),
                        tfLastName.getText().trim()
                ));
                
                MessageUtils.showInformationMessage("Sve je uredu", "Osoba je uspje??no a??urirana");
            } catch (SQLException ex) {
                logHandler.log(ex.getMessage(), ex);
                MessageUtils.showErrorMessage("Error br. 10", "Do??lo je do pogre??ke tijekom a??uriranja podataka, Za pomo?? obratite se adminu.");
                Logger.getLogger(PersonTab.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                logHandler.log(ex.getMessage(), ex);
                MessageUtils.showErrorMessage("Error br. 100", "Do??lo je do nepoznate pogre??ke. Javite se adminu.");
            }
        }
        else{
            MessageUtils.showInformationMessage("Korisni??ka gre??ka", "Potrebno je oznaciti osobu!");
        }
        
        updatePersonTable();
        selectRowInPersonTable(selectedRowPersonTable);
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void tPersonsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tPersonsMouseClicked
        try {
            selectedPerson = repo.getPerson(getPersonIdFromTable()).get();
        } catch (SQLException ex) {
            logHandler.log(ex.getMessage(), ex);
            MessageUtils.showErrorMessage("Error br. 10", "Do??lo je do pogre??ke za vrijeme dohva??anja podataka. Za pomo?? obratite se adminu.");
            Logger.getLogger(PersonTab.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
                logHandler.log(ex.getMessage(), ex);
                MessageUtils.showErrorMessage("Error br. 100", "Do??lo je do nepoznate pogre??ke. Javite se adminu.");
        }
        fillForm();
    }//GEN-LAST:event_tPersonsMouseClicked

    private void tMoviesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tMoviesMouseClicked
        getMovieIdFromTable();
    }//GEN-LAST:event_tMoviesMouseClicked

    private void btnShowMovieInformationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnShowMovieInformationActionPerformed
        if (selectedRowMovieTable != -1) {
            new ShowMovieDialog((JFrame)SwingUtilities.getWindowAncestor(this), true, getMovieIdFromTable()).setVisible(true);
            fillMovietable();
        }
        else{
            MessageUtils.showInformationMessage("Korisni??ka kre??ka", "Protrebno je ozna??iti film!");
        }
    }//GEN-LAST:event_btnShowMovieInformationActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        if (isFormOk()) {
            try {
                repo.createPerson(new Person(
                        -1,
                        tfFirstName.getText().trim(),
                        tfMiddleName.getText().trim(),
                        tfLastName.getText().trim()
                ));
                MessageUtils.showInformationMessage("Sve je uredu", "Osoba je uspje??no spremljena");
            } catch (SQLException ex) {
                logHandler.log(ex.getMessage(), ex);
                MessageUtils.showErrorMessage("Error br. 10", "Do??lo je do pogre??ke tijekom spremanj podataka, Za pomo?? obratite se adminu.");
                Logger.getLogger(PersonTab.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                logHandler.log(ex.getMessage(), ex);
                MessageUtils.showErrorMessage("Error br. 100", "Do??lo je do nepoznate pogre??ke. Javite se adminu.");
            }
        }
        
        updatePersonTable();
        
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnAddNewMovieActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddNewMovieActionPerformed
        if (selectedRowPersonTable != -1) {
            new ShowAllMoviesDialog((JFrame)SwingUtilities.getWindowAncestor(this), true, Optional.of(selectedPerson.getId())).setVisible(true);    
        }
        else{
            MessageUtils.showInformationMessage("Korisni??ka gre??ka", "Potrebno je ozna??iti osobu!");
        }
        
    }//GEN-LAST:event_btnAddNewMovieActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        
        if (selectedRowPersonTable != -1) {
            boolean isOkToDeleteMovies = MessageUtils.showConfirmDialog("Jeste li sigurni", "Jeste li sigurni da ??elite obrisati osobu?") == 0;
            if (isOkToDeleteMovies) {
                try {
                    repo.deletePerson(selectedPerson.getId());
                    MessageUtils.showInformationMessage("Sve je ok", "Osoba je uspje??no obrisana");
                    updatePersonTable();
                    clearForm();
                } catch (SQLException ex) {
                    logHandler.log(ex.getMessage(), ex);
                    MessageUtils.showErrorMessage("Error br. 10", "Do??lo je to problema tijekom brisanja podataka. za pomo?? obratite se adminu.");
                } catch (Exception ex) {
                logHandler.log(ex.getMessage(), ex);
                MessageUtils.showErrorMessage("Error br. 100", "Do??lo je do nepoznate pogre??ke. Javite se adminu.");
                }
            }
        }
        else{
            MessageUtils.showInformationMessage("Korisni??ka gre??ka", "Potrebno je oznaciti osobu!");
        }
    }//GEN-LAST:event_btnDeleteActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnAddNewMovie;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnShowMovieInformation;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblFirstNameError;
    private javax.swing.JLabel lblLastNameError;
    private javax.swing.JLabel lblRoleError;
    private javax.swing.JTable tMovies;
    private javax.swing.JTable tPersons;
    private javax.swing.JTextField tfFirstName;
    private javax.swing.JTextField tfLastName;
    private javax.swing.JTextField tfMiddleName;
    private javax.swing.JTextField tfRole;
    // End of variables declaration//GEN-END:variables

    private void init() {
        initLogHandler();
        initRepository();
        initTables();
        initValidationFields();
        initEventListener();
    }

    private void initLogHandler() {
        logHandler = LogHandlerSingleton.getInstance();
    }

    private void initRepository() {
        repo = RepositorySingleton.getInstance();
    }

    private void initTables() {
        try {
            movieModel = new MovieTableModelForPersonsTab(new ArrayList<>());
            personsModel = new PersonTableModel(repo.getPersons());
        } catch (SQLException ex) {
            logHandler.log(ex.getMessage(), ex);
            MessageUtils.showErrorMessage("Error br. 10", "Do??lo je do pogre??ke tijekom dohva??anja podataka o osobi. Za pomoc obratite se adminu");
            Logger.getLogger(PersonTab.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
                logHandler.log(ex.getMessage(), ex);
                MessageUtils.showErrorMessage("Error br. 100", "Do??lo je do nepoznate pogre??ke. Javite se adminu.");
        }
        
        tMovies.setModel(movieModel);
        tMovies.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tMovies.setAutoCreateRowSorter(true);
        
        tPersons.setModel(personsModel);
        tPersons.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tPersons.setAutoCreateRowSorter(true);
        
    }

    private void initValidationFields() {
        textValidationFields = Arrays.asList(tfFirstName, tfLastName);
        errorFields = Arrays.asList(lblFirstNameError, lblLastNameError);
    }
    
    private void initEventListener() {
        eventToRespont = RefreshMovieTableInvokerSingleton.getInstance();
        eventToRespont.addListener(this);
    }
    
    private boolean isFormOk(){
        boolean ok = true;
        
        for (int i = 0; i < textValidationFields.size(); i++) {
            
            boolean isTextFealdEmpty = textValidationFields.get(i).getText().trim().isEmpty();
            if (isTextFealdEmpty) {
                errorFields.get(i).setText("X");
                ok = false;
            }
            else{
                errorFields.get(i).setText("");
            }
        }
        return ok;
    }
    
    private void fillForm(){
        crearErrorLabels();
        tfFirstName.setText(selectedPerson.getFirstName());
        tfMiddleName.setText(selectedPerson.getMiddleName());
        tfLastName.setText(selectedPerson.getLastName());
        fillMovietable();
        fillTextFieldRola();
    }

    private void clearForm(){
        crearErrorLabels();
        tfFirstName.setText("");
        tfMiddleName.setText("");
        tfLastName.setText("");
        tfRole.setText("");
        clearMovietable();
    }
    
    private void crearErrorLabels() {
        for (JLabel  l : errorFields) {
            l.setText("");
        }
    }

    private void fillMovietable() {
        try {
            movieModel.setData(repo.getMoviesFilteredByPerson(selectedPerson));
        } catch (SQLException ex) {
            logHandler.log(ex.getMessage(), ex);
            MessageUtils.showErrorMessage("Error br. 10", "Do??lo je do pogre??ke tijekom dohva??anja podataka ta tablicu Film. Za pomoc obratite se adminu");
            Logger.getLogger(PersonTab.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    private void fillTextFieldRola() {
        Optional<Integer> value = Optional.empty();
        
        try {
            value = repo.whatRolePersonHas(selectedPerson.getId());
        } catch (SQLException ex) {
            logHandler.log(ex.getMessage(), ex);
            MessageUtils.showErrorMessage("Error br. 10", "Do??lo je do pogre??ke tijekom dohva??anja podataka ta tablicu Film. Za pomoc obratite se adminu");
            Logger.getLogger(PersonTab.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
                logHandler.log(ex.getMessage(), ex);
                MessageUtils.showErrorMessage("Error br. 100", "Do??lo je do nepoznate pogre??ke. Javite se adminu.");
        }
        
        boolean isDirector = value.isPresent() && value.get() == Integer.valueOf(-1);
        if (isDirector) {
            tfRole.setText("Re??iser/ica");
        }
                
        boolean isActor = value.isPresent() && value.get() == Integer.valueOf(1);
        if (isActor) {
            tfRole.setText("Glumac/ica");
        }
        
        boolean isActorAndDirector = value.isPresent() && value.get() == Integer.valueOf(0);
        if (isActorAndDirector) {
            tfRole.setText("Glumac/ica, Re??iser/ica");
        }
        
        
        if (!value.isPresent()) {
            tfRole.setText("Nema podatka!");
        }
    }

    private int getPersonIdFromTable() {
        selectedRowPersonTable = tPersons.getSelectedRow();
        return (int) tPersons.getValueAt(selectedRowPersonTable, 0);
    }

    private int getMovieIdFromTable() {
        selectedRowMovieTable = tMovies.getSelectedRow();
        return (int) tMovies.getValueAt(selectedRowMovieTable, 0);
    }

    private void updatePersonTable() {
        try {
            personsModel.setData(repo.getPersons());
        } catch (SQLException ex) {
            logHandler.log(ex.getMessage(), ex);
            MessageUtils.showErrorMessage("Error br. 10", "Do??lo je do problema prilikom a??uriranj tablice Osoba. Za pomo?? obratite se adminu.");
            Logger.getLogger(PersonTab.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void selectRowInPersonTable(int sr) {
        tPersons.setRowSelectionInterval(0, sr);
    }

    @Override
    public void refreshTableMovieTable() {
        updatePersonTable();
        selectedRowPersonTable = -1;
    }

    private void clearMovietable() {
        movieModel.setData(new ArrayList<>());
        clearForm();
    }
    
}
