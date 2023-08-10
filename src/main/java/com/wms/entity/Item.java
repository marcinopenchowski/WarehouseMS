package com.wms.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@ToString
@Getter
@Setter
@SuperBuilder
@MappedSuperclass
@NoArgsConstructor
public abstract class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    @ToString.Exclude
    private Category category;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private Double price;

    @Column(name = "description")
    private String description;

    @Column(name = "purchase_date")
    private LocalDate purchaseDate;

    @ManyToOne
    @JoinColumn(name = "owner_id", referencedColumnName = "id")
    @ToString.Exclude
    private Owner owner;
}
