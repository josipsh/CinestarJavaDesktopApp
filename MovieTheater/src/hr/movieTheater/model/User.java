/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.movieTheater.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

/**
 *
 * @author Josip
 */
public class User extends Person{
    
    public static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ISO_DATE;
    
    private int idClient;
    private Optional<UserType> userType;
    private String email;
    private String pasword;
    private LocalDate addDate;

    public User(int idPerson, String firstName, String MiddleName, String lastName, int idClient, Optional<UserType> userType, String email, String pasword, LocalDate addDate) {
        super(idPerson, firstName, MiddleName, lastName);
        this.idClient = idClient;
        this.userType = userType;
        this.email = email;
        this.pasword = pasword;
        this.addDate = addDate;
    }
    public User(Optional<Person> person,int idClient, Optional<UserType> userType, String email, String pasword, LocalDate addDate) {
        super(person.get().getId(), person.get().getFirstName(), person.get().getMiddleName(), person.get().getLastName());
        this.idClient = idClient;
        this.userType = userType;
        this.email = email;
        this.pasword = pasword;
        this.addDate = addDate;
    }
    public User(int idClient, Optional<UserType> userType, String email, String pasword, LocalDate addDate) {
        super(0, "", "", "");
        this.idClient = idClient;
        this.userType = userType;
        this.email = email;
        this.pasword = pasword;
        this.addDate = addDate;
    }

    public int getIdClient() {
        return idClient;
    }

    public Optional<UserType> getUserType() {
        return userType;
    }

    public String getEmail() {
        return email;
    }

    public String getPasword() {
        return pasword;
    }

    public LocalDate getAddDate() {
        return addDate;
    }

    public void setPasword(String pasword) {
        this.pasword = pasword;
    }

    @Override
    public String toString() {
        return "User{" + "idClient=" + idClient + ", iduserRole=" + userType.get() + ", email=" + email + ", pasword=" + pasword + ", addDate=" + addDate + '}';
    }
    
}
