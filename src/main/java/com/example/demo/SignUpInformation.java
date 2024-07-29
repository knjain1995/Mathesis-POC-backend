package com.example.demo;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data   // lombok
@Document()   // MongoDB, indicates that below class is a document for MongoDB
public class SignUpInformation {

    @Id // indicates this is id / PRIMARY KEY
    private String id;  // PRIMARY KEY
    private String firstname;
    private String lastname;
    @Indexed(unique = true) // make field unique
    private String email;
    @Indexed(unique = true) // make field unique
    private String phone;   // try BigInteger
    private Date dateofbirth;
    private String password;
    private boolean newsletterintent;

}
