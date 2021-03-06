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
import hr.movieTheater.model.Person;
import hr.movieTheater.model.PersonTransferable;
import java.awt.Dialog;
import java.awt.datatransfer.Transferable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.DropMode;
import javax.swing.JComponent;
import javax.swing.ListSelectionModel;
import javax.swing.TransferHandler;

/**
 *
 * @author Josip
 */
public class DragAndDropPersonPanel extends javax.swing.JPanel {

    private LogHandler logHandler;
    private Repository repo;
    
    
    private DragAndDropDialog parent;
    private Set<Person> persons;
    private Set<Person> allPersons;
    
    private Set<Person> searchResult;
    private String typedString = "";
        
    private DefaultListModel<Person> personsModel;
    private DefaultListModel<Person> allPersonsModel;

    /**
     * Creates new form DragAndDropPersonPanel
     */
//    public DragAndDropPersonPanel() {
//        initComponents();
//    }

    DragAndDropPersonPanel(DragAndDropDialog parent, List<Person> persons) {
        this.parent = parent;
        this.persons = new TreeSet<>(persons);
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
        lsPersons = new javax.swing.JList<Person>();
        jScrollPane2 = new javax.swing.JScrollPane();
        lsAllPersons = new javax.swing.JList<Person>();
        btnCancel = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        btnAdd = new javax.swing.JButton();
        tfSearch = new javax.swing.JTextField();
        btnRemove = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(530, 480));

        jScrollPane1.setViewportView(lsPersons);

        jScrollPane2.setViewportView(lsAllPersons);

        btnCancel.setText("Odustani");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        btnSave.setText("Spremi");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        btnAdd.setText("Dodaj novu osobu");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        tfSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfSearchKeyReleased(evt);
            }
        });

        btnRemove.setText("Obri??i");
        btnRemove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(72, 72, 72)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
                    .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRemove, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15))
                    .addComponent(tfSearch)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(100, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(tfSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAdd)
                    .addComponent(btnRemove))
                .addGap(44, 44, 44)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        new AddNewPerson((Dialog) this.getParent().getParent().getParent().getParent().getParent(), true).setVisible(true);
        initData();
        loadAllPeopleModel(allPersons);
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        parent.dispose();
    }//GEN-LAST:event_btnCancelActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        parent.setPersons(new ArrayList<>(persons));
        parent.saveData();
        parent.dispose();
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveActionPerformed
        persons.remove(lsPersons.getSelectedValue());
        loadPeopleModel(persons);
        
    }//GEN-LAST:event_btnRemoveActionPerformed

    private void tfSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfSearchKeyReleased
        
        typedString = tfSearch.getText().trim();
        searchResult.clear();
        if (0 != typedString.length()) {
            for (Person person : allPersons) {
                if (person.getFirstName().toLowerCase().startsWith(typedString.toLowerCase()) || person.getLastName().toLowerCase().startsWith(typedString.toLowerCase())) {
                    searchResult.add(person);
                }
            }
            loadAllPeopleModel(searchResult);
        }
        else{
            loadAllPeopleModel(allPersons);
        }
    }//GEN-LAST:event_tfSearchKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnRemove;
    private javax.swing.JButton btnSave;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JList<Person> lsAllPersons;
    private javax.swing.JList<Person> lsPersons;
    private javax.swing.JTextField tfSearch;
    // End of variables declaration//GEN-END:variables

    private void init() {
        initLogHandler();
        initRepository();
        initData();
        initModels();
        loadPeopleModel(persons);
        loadAllPeopleModel(allPersons);
        initDragAndDrop();
    }

    private void initLogHandler() {
        logHandler = LogHandlerSingleton.getInstance();
    }

    private void initRepository() {
        repo = RepositorySingleton.getInstance();
    }

    private void initData() {
        try {
            allPersons = new TreeSet<>(repo.getPersons());
        } catch (SQLException ex) {
            logHandler.log(ex.getMessage(), ex);
            MessageUtils.showErrorMessage("Error br. 10", "Do??lo je do pogre??ke za vrijeme dohva??anja podataka. Za pomo?? obratite se adminu.");
            Logger.getLogger(DragAndDropPersonPanel.class.getName()).log(Level.SEVERE, null, ex);
        }catch (Exception ex) {
            logHandler.log(ex.getMessage(), ex);
            MessageUtils.showErrorMessage("Error br. 100", "Do??lo je do nepoznate pogre??ke. Javite se adminu.");
        }
        
        searchResult = new TreeSet<>();
   }
    
    private void initModels() {
        personsModel = new DefaultListModel<>();
        allPersonsModel = new DefaultListModel<>();
    }

    private void loadPeopleModel(Set<Person> p) {
        personsModel.clear();      
        p.forEach(o -> personsModel.addElement(o));
        lsPersons.setModel(personsModel);
    }

    private void loadAllPeopleModel(Set<Person> p) {
        allPersonsModel.clear();  
        p.forEach(o -> allPersonsModel.addElement(o));
        lsAllPersons.setModel(allPersonsModel);
    }

    private void initDragAndDrop() {
        lsAllPersons.setDragEnabled(true);
        lsAllPersons.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        lsAllPersons.setTransferHandler(new ExsportTransferHandler());
        
        lsPersons.setDropMode(DropMode.ON);
        lsPersons.setTransferHandler(new ImportTransferHandler());
    }

    private class ExsportTransferHandler extends TransferHandler {
        
        @Override
        public int getSourceActions(JComponent c) {
            return COPY;
        }
        
        @Override
        protected Transferable createTransferable(JComponent c) {
            return new PersonTransferable(lsAllPersons.getSelectedValue());
        }
    }
    
    private class ImportTransferHandler extends TransferHandler {

        @Override
        public boolean canImport(TransferSupport support) {
            return support.isDataFlavorSupported(PersonTransferable.PERSON_FLAVOR);
        }

        @Override
        public boolean importData(TransferSupport support) {
            Transferable transfer = support.getTransferable();
            
            try {
                Person person = (Person) transfer.getTransferData(PersonTransferable.PERSON_FLAVOR);
                if (persons.add(person)) {
                    loadPeopleModel(persons);
                    return true;                }
            } catch (Exception e) {
                logHandler.log(e.getMessage(), e);
                MessageUtils.showErrorMessage("Gre??ka br. 30", "Nije mogu??e izvr??iti ovu radnju. Za pomoc javite se adminu.");
                return false;
            }
            return super.importData(support); //To change body of generated methods, choose Tools | Templates.
        }
    }

}
