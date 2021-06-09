/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.movieTheater.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author Josip Sadek
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"firstName", "middleName", "lastName"})
public class Person implements Comparable<Person>{
//public class Person {
    @XmlTransient
    private int id;
    private String firstName;
    private String lastName;
    private String middleName;

    public Person() {
    }

    public Person(int id, String firstName, String middleName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
    }

    public int getId() {
        return id;
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

    public void setId(int id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }
    
    @Override
    public String toString() {
        return firstName + " " + middleName + " " + lastName;
    }
//    
//    @Override
//    public String toString() {
//        return "id=" + id + " {firstName=" + firstName 
//                + ", middleName=" + middleName +", lastName=" 
//                + lastName + '}';
//    }

    @Override
    public int compareTo(Person p) {
        return Integer.valueOf(p.getId()).compareTo(Integer.valueOf(id));
    }

//    @Override
//    public int compareTo(Person p) {
//        if (p.getLastName().isEmpty() || lastName.isEmpty()) {
//                //return p.getFirstName().compareTo(firstName);
//                return -1;
//        }
//        if (lastName.compareTo(p.getLastName()) == 0) {
//            return this.firstName.compareTo(p.getFirstName());
//        }
//        
//        return lastName.compareTo(p.getLastName());
//    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Person) {
            Person o = (Person) obj;
            return Integer.valueOf(o.getId()).equals(Integer.valueOf(id));
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Integer.valueOf(id).hashCode();
    }
    
    
}
