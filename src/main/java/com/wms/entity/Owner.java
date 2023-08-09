package com.wms.entity;

import lombok.*;

import jakarta.persistence.*;

@Entity
@Getter
@Setter
@Builder
@ToString
@Table(name = "owner")
@NoArgsConstructor
@AllArgsConstructor
public class Owner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "owner_group_id")
    private Long ownerGroupId;
}
