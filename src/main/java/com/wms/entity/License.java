package com.wms.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Getter
@Setter
@Builder
@ToString
@Table(name = "license")
@NoArgsConstructor
@AllArgsConstructor
public class License {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Basic
    @Column(name = "expiration_date")
    private Date expirationDate;

    @Basic
    @Column(name = "email")
    private String email;
}
