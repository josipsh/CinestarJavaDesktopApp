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
public class PersonDataFromDataBase {
    private int idPersonData;
    private String firstName;
    private String middleName;
    private String lastName;


    public PersonDataFromDataBase (int idPersonData, String firstName, String middleName, String lastName) {
        this.idPersonData = idPersonData;
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
    }

    public int getIdPersonData() {
        return idPersonData;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
    
    public String getMiddleName() {
        return middleName;
    }

    @Override
    public String toString() {
        return "PersonData{" + "idPersonData=" + idPersonData + ", firstName=" + firstName + ", lastName=" + lastName + '}';
    }
    
    
}
