/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.movieTheater.gui.popUpDialog;

import hr.algebra.utils.MessageUtils;
import hr.movieTheater.customsExceptions.InvalidUserTypeException;
import hr.movieTheater.dal.Repository;
import hr.movieTheater.dal.RepositorySingleton;
import hr.movieTheater.log.LogHandler;
import hr.movieTheater.log.LogHandlerSingleton;
import hr.movieTheater.model.User;
import hr.movieTheater.model.UserType;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.text.JTextComponent;

/**
 *
 * @author Josip
 */
public class CreateUser extends javax.swing.JDialog {

    private Optional<User> user;
    
    private LogHandler logHandler;
    private Repository repo;
    
    private List<JTextComponent> textFields;
    private List<JLabel> errorFields;
    
    private DefaultComboBoxModel<UserType> cbModel = new DefaultComboBoxModel<>();
    
    private boolean createAdminEnabled = true;
    /**
     * Creates new form CreateUser
     */
    public CreateUser(java.awt.Frame parent, boolean modal, boolean createAdminEnabled) {
        super(parent, modal);
        initComponents();
        user = Optional.empty();
        this.createAdminEnabled = createAdminEnabled;
        init();
    }
    
    public CreateUser(java.awt.Frame parent, boolean modal, Optional<User> user) {
        super(parent, modal);
        initComponents();
        this.user = user;
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

        jLabel1 = new javax.swing.JLabel();
        tfFirstName = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        tfMiddleName = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        tfLastName = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        tfAddDate = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        tfEmail = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        tfPasword = new javax.swing.JPasswordField();
        jLabel7 = new javax.swing.JLabel();
        cbUserType = new javax.swing.JComboBox();
        jLabel8 = new javax.swing.JLabel();
        tfPaswordRepeated = new javax.swing.JPasswordField();
        lblLastNameError = new javax.swing.JLabel();
        lblFirstNameError = new javax.swing.JLabel();
        lblEmailError = new javax.swing.JLabel();
        lblPaswordRepetedError = new javax.swing.JLabel();
        lblPaswordError = new javax.swing.JLabel();
        btnCancel = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Ime:");

        jLabel2.setText("Srednje ime:");

        jLabel3.setText("Prezime:");

        jLabel4.setText("Datum otvaranja ra??una:");

        tfAddDate.setEditable(false);

        jLabel5.setText("Email:");

        jLabel6.setText("Lozinka");

        jLabel7.setText("Tip korisnika:");

        cbUserType.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel8.setText("Ponovi lozinku:");

        lblLastNameError.setForeground(new java.awt.Color(204, 0, 0));

        lblFirstNameError.setForeground(new java.awt.Color(204, 0, 0));

        lblEmailError.setForeground(new java.awt.Color(204, 0, 0));

        lblPaswordRepetedError.setForeground(new java.awt.Color(204, 0, 0));

        lblPaswordError.setForeground(new java.awt.Color(204, 0, 0));

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(90, 90, 90)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGap(50, 50, 50)
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(cbUserType, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGap(78, 78, 78)
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18)
                                .addComponent(tfLastName))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel8))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tfAddDate)
                                    .addComponent(tfEmail)
                                    .addComponent(tfPasword)
                                    .addComponent(tfPaswordRepeated)))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel2))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(tfMiddleName)
                                    .addComponent(tfFirstName, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblLastNameError, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblEmailError, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblPaswordRepetedError, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblPaswordError, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblFirstNameError, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 57, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(117, 117, 117))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(tfFirstName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblFirstNameError, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfMiddleName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(tfLastName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblLastNameError, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(tfAddDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(tfEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblEmailError, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(tfPasword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPaswordError, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(tfPaswordRepeated, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPaswordRepetedError, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(cbUserType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(42, 42, 42)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(75, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        dispose();
    }//GEN-LAST:event_btnCancelActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        if (isFormOk()) {
            try {
                if (user.isPresent()) {
                    repo.updateUser(getDataFromForm().get());
                }
                else{
                    repo.createUser(getDataFromForm().get());
                }
                dispose();
            } catch (SQLException ex) {
                logHandler.log(ex.getMessage(), ex);
                MessageUtils.showErrorMessage("Error br 10", "Do??lo je do pogre??ke tijekom spremanja podataka od korisnika. Za pomo?? obratite se razvojnom timu");
                Logger.getLogger(CreateUser.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InvalidUserTypeException ex) {
                logHandler.log(ex.getMessage(), ex);
                MessageUtils.showErrorMessage("Error br. 60", "Do??lo je do pogre??ke tijekom spremanja podataka od korisnika. Za pomo?? obratite se razvojnom timu");
                Logger.getLogger(CreateUser.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                logHandler.log(ex.getMessage(), ex);
                MessageUtils.showErrorMessage("Error br. 100", "Do??lo je do nepoznate pogre??ke. Javite se adminu.");
            }
        }
    }//GEN-LAST:event_btnSaveActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnSave;
    private javax.swing.JComboBox cbUserType;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel lblEmailError;
    private javax.swing.JLabel lblFirstNameError;
    private javax.swing.JLabel lblLastNameError;
    private javax.swing.JLabel lblPaswordError;
    private javax.swing.JLabel lblPaswordRepetedError;
    private javax.swing.JTextField tfAddDate;
    private javax.swing.JTextField tfEmail;
    private javax.swing.JTextField tfFirstName;
    private javax.swing.JTextField tfLastName;
    private javax.swing.JTextField tfMiddleName;
    private javax.swing.JTextField tfPasword;
    private javax.swing.JTextField tfPaswordRepeated;
    // End of variables declaration//GEN-END:variables

    private void init() {
        initLogHandler();
        initRepository();
        initValidationFields();
        loadCbModel();
        if (user.isPresent()) {
            fillForm();
            setTitle("A??uriraj korisnika");
        }
        else{
            clearForm();
            setTitle("Kreiraj novog korisnika");
        }
        
    }

    private void initLogHandler() {
        logHandler = LogHandlerSingleton.getInstance();
    }

    private void initRepository() {
        repo = RepositorySingleton.getInstance();
    }

    private void initValidationFields() {
        textFields = Arrays.asList(tfFirstName, tfLastName, tfEmail, tfPasword, tfPaswordRepeated);
        errorFields = Arrays.asList(lblFirstNameError, lblLastNameError, lblEmailError, lblPaswordError, lblPaswordRepetedError);
    }

    private void loadCbModel() {
        for (UserType u : UserType.values()) {
            cbModel.addElement(u);
        }
        cbUserType.setModel(cbModel);
    }
    
    private boolean isFormOk(){
        boolean ok = true;
        
        for (int i = 0; i < textFields.size(); i++) {
            if (textFields.get(i).getText().trim().isEmpty()) {
                errorFields.get(i).setText("X");
                ok = false;
            }
            else{
                errorFields.get(i).setText("");
            }
        }
        
        if (!tfPasword.getText().equals(tfPaswordRepeated.getText())) {
            ok = false;
            MessageUtils.showInformationMessage("Korisni??ka gre??ka", "Lozinke se ne podudaraju!");
        }
        
        if (!isEmailGood()) {
            ok = false;
            MessageUtils.showInformationMessage("Korisni??ka gre??ka", "Email nije ispravno unesen!");
        }
        
        if (!createAdminEnabled && cbUserType.getSelectedItem().equals(UserType.ADMIN)) {
            cbUserType.setSelectedIndex(1);
            ok = false;
            MessageUtils.showInformationMessage("Korisni??ka gre??ka", "Niste u mogu??nosti kreirati admina. Samo admin mo??e kreirati drugog admina.");
        }
        
        return ok;
    }

    private boolean isEmailGood() {
        String email = tfEmail.getText().trim();
        if (email.isEmpty()) {
            return false;
        }
        
        String[] splitedEmail = email.split("@");
        
        if (splitedEmail.length == 1) {
            return false;
        }
        
        String[] splitedDimain = splitedEmail[1].split("\\.");
        
        if (splitedDimain.length == 1 || splitedDimain.length == 0) {
            return false;
        }
        
        return true;
    }

    private Optional<User> getDataFromForm() {
        if (user.isPresent()) {
            return Optional.of(new User(
                user.get().getId(), 
                tfFirstName.getText().trim(), 
                tfMiddleName.getText().trim(), 
                tfLastName.getText().trim(), 
                user.get().getIdClient(), 
                Optional.of( (UserType) cbUserType.getSelectedItem()), 
                tfEmail.getText().trim(), 
                getPasword(), 
                LocalDate.now()
        )); 
            
        }
        
        return Optional.of(new User(
                0, 
                tfFirstName.getText().trim(), 
                tfMiddleName.getText().trim(), 
                tfLastName.getText().trim(), 
                0, 
                Optional.of( (UserType) cbUserType.getSelectedItem()), 
                tfEmail.getText().trim(), 
                getPasword(), 
                LocalDate.now()
        ));
    }

    private void fillForm() {
        tfFirstName.setText(user.get().getFirstName());
        tfMiddleName.setText(user.get().getMiddleName());
        tfLastName.setText(user.get().getLastName());
        tfEmail.setText(user.get().getEmail());
        tfAddDate.setText(user.get().getAddDate().format(User.DATE_FORMAT));
        tfPasword.setText(user.get().getPasword());
        tfPaswordRepeated.setText(user.get().getPasword());
    }

    private void clearForm() {
        tfFirstName.setText("");
        tfMiddleName.setText("");
        tfLastName.setText("");
        tfEmail.setText("");
        tfAddDate.setText("");
        tfPasword.setText("");
        tfPaswordRepeated.setText("");
    }
    
    private String getPasword() {
        if (user.isPresent()) {
            if (tfPasword.getText().trim().equals(user.get().getPasword())) {
                return tfPasword.getText().trim();
            }
            return String.valueOf(tfPasword.getText().trim().hashCode());
        }
        else{
            return String.valueOf(tfPasword.getText().trim().hashCode());
        }
    }
}
