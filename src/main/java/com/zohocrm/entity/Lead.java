package com.zohocrm.entity;




import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Table(name = "leads")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Lead {
    @Id
    private String lid;
    @Column(name = "first_name", nullable = false)
    private String firstName;
    @Column(name = "last_name",nullable = false)
    private String lastName;
    @Column(name = "email",nullable = false,unique = true)
    private String email;
    @Column(name = "mobile",nullable = false,unique = true)
    private long mobile;
    @Column(name = "lead_type",nullable = false)
    private String leadType;
    @Column(name = "address")
    private String address;
    @Column(name = "designation")
    private String designation;
    @Column(name = "company")
    private String company;
    @Column(name = "note")
    private String note;
}
