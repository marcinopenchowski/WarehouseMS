package com.wms.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@ToString
@Table(name = "item")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
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
    private LocalDateTime purchaseDate;

    @Column(name = "purchase_cost")
    private Double purchaseCost;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id", referencedColumnName = "id")
    @ToString.Exclude
    private Owner owner;
}
