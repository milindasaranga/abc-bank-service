package com.abcbank.account.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String  username; private String password;
    private String email;
    private String  address;
    private String account;
    private String status;
    private Double balance;
    private Long numOfChecks;

}
