package com.wms.entity;

import lombok.*;

import jakarta.persistence.*;
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

    @Column(name = "expiration_date")
    private Date expirationDate;

    @Column(name = "email")
    private String email;
}
