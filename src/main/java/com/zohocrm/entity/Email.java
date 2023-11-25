package com.zohocrm.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "emails")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Email {
    @Id
    private String eid;
    @Column(name = "to_email")
    private String to;
    @Column(name = "subject")
    private String subject;
    @Column(name = "message")
    private String message;
}
