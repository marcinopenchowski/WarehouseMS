package com.wms.entity;

import lombok.*;

import jakarta.persistence.*;

@Entity
@Getter
@Setter
@Builder
@ToString
@Table(name = "owner_group")
@NoArgsConstructor
@AllArgsConstructor
public class OwnerGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;
}
