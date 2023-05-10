package com.wms.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import javax.persistence.*;
import java.sql.Date;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@ToString
@Table(name = "license")
public class License {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Basic
    @Column(name = "expiration_date")
    private Date expirationDate;

    @Basic
    @Column(name = "email")
    private String email;
}
