/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.movieTheater.gui;

import hr.algebra.utils.FileUtils;
import hr.algebra.utils.JAXBUtils;
import hr.algebra.utils.MessageUtils;
import hr.movieTheater.DirectoryInformationHandler;
import hr.movieTheater.customEvents.RefreshMovieTableInvoker;
import hr.movieTheater.customEvents.RefreshMovieTableInvokerSingleton;
import hr.movieTheater.customsExceptions.InvalidUserTypeException;
import hr.movieTheater.log.LogHandler;
import hr.movieTheater.dal.Repository;
import hr.movieTheater.dal.RepositorySingleton;
import hr.movieTheater.gui.popUpDialog.CreateUser;
import hr.movieTheater.gui.popUpDialog.ShowAllMoviesDialog;
import hr.movieTheater.gui.popUpDialog.ShowAllUsers;
import hr.movieTheater.log.LogHandlerSingleton;
import hr.movieTheater.model.User;
import hr.movieTheater.model.UserType;
import hr.movieTheater.xml.Theater;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.SwingUtilities;
import javax.xml.bind.JAXBException;

/**
 *
 * @author Josip
 */
public class MainForm extends javax.swing.JFrame {
    
    private LogInPanel logInPanel;
    private UserPanel userPanel;
    
    private LogHandler logHandler;
    private Repository repo;
    
    private RefreshMovieTableInvoker invoker;
    
    private Optional<User> user = Optional.empty();
    
    /**
     * Creates new form MainForm
     */
    public MainForm() {
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

        tbContent = new javax.swing.JPanel();
        mbMenuRoot = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Cinestar");
        setIconImages(null);
        setPreferredSize(new java.awt.Dimension(1175, 890));
        setResizable(false);

        tbContent.setPreferredSize(new java.awt.Dimension(1195, 900));
        tbContent.setLayout(new java.awt.GridBagLayout());

        jMenu1.setText("File");
        mbMenuRoot.add(jMenu1);

        jMenu2.setText("Edit");
        mbMenuRoot.add(jMenu2);

        setJMenuBar(mbMenuRoot);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tbContent, javax.swing.GroupLayout.DEFAULT_SIZE, 1154, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tbContent, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 879, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar mbMenuRoot;
    private javax.swing.JPanel tbContent;
    // End of variables declaration//GEN-END:variables

    private void init() {
        initLogHadnler();
        initRepository();
        initInvoker();
        initDefaultUsers();
        clearMenu();
        initForm();
        initLogInForm();
        //initClientUserMenu();
        //initAdminUsermenu();
        //initUserForm();
    }
    
    private void initLogHadnler() {
        logHandler = LogHandlerSingleton.getInstance();
    }

    private void initRepository() {
        repo = RepositorySingleton.getInstance();
    }

    private void initInvoker() {
        invoker = RefreshMovieTableInvokerSingleton.getInstance();
    }
    
    private void initDefaultUsers() {
        try {
            repo.createUser(new User(1, "admin", "admin", "admin", 1, Optional.of(UserType.ADMIN), "admin@gmail.com", String.valueOf("admin".hashCode()), LocalDate.now()));
            repo.createUser(new User(1, "korisnik", "korisnik", "korisnik", 1, Optional.of(UserType.CLIENT), "korisnik@gmail.com", String.valueOf("korisnik".hashCode()), LocalDate.now()));
        } catch (SQLException ex) {
            logHandler.log(ex.getMessage(), ex);
            MessageUtils.showErrorMessage("Error br. 10", "Došlo je do pogreške tijekom kreiranja defaultnih podataka. Za pomoć obratite se adminu");
            Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
            System.exit(1);
        } catch (InvalidUserTypeException ex) {
            logHandler.log(ex.getMessage(), ex);
            MessageUtils.showErrorMessage("Error br. 60", "Došlo je do pogreške tijekom kreiranja defaultnih podataka. Za pomoć obratite se razvojnom timu");
            Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void initForm() {
        userPanel = new UserPanel();
        logInPanel = new LogInPanel(this);
    }
    
    private void initUserForm() {
        tbContent.add(userPanel);  
    }
    
    private void initLogInForm() {
        setTitle("Cinestar");
        tbContent.add(logInPanel);
    }
    
    private void removeUserForm() {
        tbContent.remove(userPanel);  
    }
    
    private void removeLogInForm() {
        tbContent.remove(logInPanel);
    }

    private void initClientUserMenu() {
        JMenu mHome = createMenuTab("Početna");
        JMenuItem miLogOut = createMenuItem("Odjavi se");
        mbMenuRoot.add(mHome);
        mHome.add(miLogOut);
        
        miLogOut.addActionListener((ActionEvent m) -> {
            removeUserForm();
            clearMenu();
            initLogInForm();
            this.setVisible(true);
        });
    }
    
    private void initAdminUsermenu() {
        JMenu mHome = createMenuTab("Početna");
        mbMenuRoot.add(mHome);
        JMenu mDataBase = createMenuTab("Upravljanje bazom podataka");
        mbMenuRoot.add(mDataBase);
        JMenu mExsportXml = createMenuTab("Eksportiraj XML");
        mbMenuRoot.add(mExsportXml);

        JMenuItem miLogOut = createMenuItem("Odjavi se");
        JMenuItem miAddNewAdmin = createMenuItem("Dodaj novog admina");
        JMenuItem miManageUsersAccaunts = createMenuItem("Upravljaj računima");
        mHome.add(miManageUsersAccaunts);
        mHome.add(miAddNewAdmin);
        mHome.add(miLogOut);
        
        miLogOut.addActionListener(m -> {
            removeUserForm();
            clearMenu();
            initLogInForm();
            this.setVisible(true);
        });
        miAddNewAdmin.addActionListener(m -> new CreateUser((Frame) SwingUtilities.getWindowAncestor(this), true, true).setVisible(true));
        miManageUsersAccaunts.addActionListener(m -> new ShowAllUsers((Frame) SwingUtilities.getWindowAncestor(this), true).setVisible(true));

        JMenuItem miExportXml = createMenuItem("Eksportiraj XML");
        mExsportXml.add(miExportXml);
        
        miExportXml.addActionListener(m -> {
            Optional<String> filePath = FileUtils.chooseDestinadion();
            if (filePath.isPresent()) {
                try {
                    String fileName = filePath.get() + File.separator + "MovieTheater.xml";
                    JAXBUtils.save(new Theater(repo.getMovies(true)), fileName);
                    MessageUtils.showInformationMessage("Sve je uredu", "XML dokument je uspješno eksportiran.");
                } catch (JAXBException ex) {
                    logHandler.log(ex.getMessage(), ex);
                    MessageUtils.showErrorMessage("Error br. 70", "Došlo je do poglreške tijekom formatiranja xml dokumenta. Za pomoć pogledatjete dokumentaciju.");
                    Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    logHandler.log(ex.getMessage(), ex);
                    MessageUtils.showErrorMessage("Error br. 10", "Došlo je to problema tijekom brisanja podataka. Pogledajte dokumentaciju kako biste otklonili problem.");
                    Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        JMenuItem miUpdateDataBase = createMenuItem("Ažuriraj bazu podataka");
        JMenuItem miDeleteDataBase = createMenuItem("Obriši bazu podataka");
        mDataBase.add(miUpdateDataBase);
        mDataBase.add(miDeleteDataBase);
        
        miUpdateDataBase.addActionListener(m -> {
            new ShowAllMoviesDialog((Frame) SwingUtilities.getWindowAncestor(this), true).setVisible(true);
        });
        miDeleteDataBase.addActionListener(m -> {
            boolean isOkToDeleteMovies = MessageUtils.showConfirmDialog("Jeste li sigurni", "Jeste li sigurni da želite obrisati sve filmove?") == 0;
            if (isOkToDeleteMovies) {
                try {
                    deletePictures();
                    repo.deleteMovieData();
                    MessageUtils.showInformationMessage("Sve je ok", "Baza podataka je uspješno obrisana");
                    invoker.invokeEvent();
                }catch (IOException ex) {
                    logHandler.log(ex.getMessage(), ex);
                    MessageUtils.showErrorMessage("Error br. 50", "Došlo je to problema tijekom brisanja slika. Pogledajte dokumentaciju kako biste otklonili problem.");
                } catch (SQLException ex) {
                    logHandler.log(ex.getMessage(), ex);
                    MessageUtils.showErrorMessage("Error br. 10", "Došlo je to problema tijekom brisanja podataka. Pogledajte dokumentaciju kako biste otklonili problem.");
                }
            }
        });
    }

    private void clearMenu() {
        mbMenuRoot.removeAll();
    }

    private JMenu createMenuTab(String title) {
        return new JMenu(title);
    }

    private JMenuItem createMenuItem(String title) {
        return new JMenuItem(title);
    }

    public void loadFormForUser(Optional<User> user){
        this.user = user;
        if (user.get().getUserType().get() == UserType.ADMIN) {
            //Admin
            clearMenu();
            removeLogInForm();
            initUserForm();
            initAdminUsermenu();
            this.setVisible(true);
            setTitle("Cinestar - korisnik: " + user.get().getFirstName());
        }
        else{
            //Client
            clearMenu();
            removeLogInForm();
            initUserForm();
            initClientUserMenu();
            this.setVisible(true);
            setTitle("Cinestar - korisnik: " + user.get().getFirstName());
        }
    }
    
    private void deletePictures() throws SQLException, IOException {
        File f = new File(DirectoryInformationHandler.getAssetDirectory());
        for (File fd : f.listFiles()) {
            fd.delete();
        }
    }
}
