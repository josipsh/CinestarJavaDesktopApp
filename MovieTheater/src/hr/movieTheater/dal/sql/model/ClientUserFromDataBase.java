/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.movieTheater.dal.sql.model;

/**
 *
 * @author Josip Sadek
 */
public class ClientUserFromDataBase {
    
    private final int idClientUser;
    private final int persondataID;
    private final int userRoleID;
    private final String email;
    private final int pasword;
    private final int addDate;

    /**
     *
     * @param idClientUser
     * @param persondataID
     * @param userRoleID
     * @param email
     * @param pasword
     * @param addDate
     */
    public ClientUserFromDataBase(int idClientUser, int persondataID, int userRoleID, String email, int pasword, int addDate) {
        this.idClientUser = idClientUser;
        this.persondataID = persondataID;
        this.userRoleID = userRoleID;
        this.email = email;
        this.pasword = pasword;
        this.addDate = addDate;
    }

    public int getIdClientUser() {
        return idClientUser;
    }

    public int getPersondataID() {
        return persondataID;
    }

    public int getUserRoleID() {
        return userRoleID;
    }

    public String getEmail() {
        return email;
    }

    public int getPasword() {
        return pasword;
    }

    public int getAddDate() {
        return addDate;
    }

    @Override
    public String toString() {
        return "ClientUser{" + "idClientUser=" + idClientUser + ", persondataID=" + persondataID + ", userRoleID=" + userRoleID + ", email=" + email + '}';
    }
    
    
    
    
}
