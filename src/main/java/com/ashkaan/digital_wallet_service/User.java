package com.ashkaan.digital_wallet_service; //file belongs to this project's folder group

//import org.hibernate.Hibernate;

//importing necessary libraries for the class to function properly
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

//This class represents a User entity in the digital wallet service

@Entity //this tag tells springboot that this class is an entity and should be mapped to a database table or it should be treated as a database table blueprint
@Table(name = "app_users") //this tag tells springboot that the name of the database table for this entity should be "app_users" instead of the default name which would be "user"
public class User {
    
    @Id //tells hibernate that the field right below this line is the primary key for this table
    @GeneratedValue(strategy = GenerationType.IDENTITY) //tells hibernate to automatically generate the value for this field when a new record is created in the database
    private Long id; // this field represents the unique identifier for each user in the database and is of type Long which can hold large integer values and it will become a column in the database table for this entity

    //each of the following fields represents a column in the database table for this entity and will hold the respective data for each user
    //(Hibernate will automatically map these fields to columns in the database table)
    private String name;
    private String email;
    private String password;

    //getter and setter methods for each field to allow access and modification of the field values
    public Long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}
