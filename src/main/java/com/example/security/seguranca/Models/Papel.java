package com.example.security.seguranca.Models;

import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Getter
@Setter

@Entity
public class Papel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    @Column(name = "nome", length = 100)
    private String Nome;
}