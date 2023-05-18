package com.example.cms.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Contact extends BaseEntity {

    private String firstName;
    private String lastName;
    @Column(unique = true)
    private String email;
    private String phoneNumber;

}
