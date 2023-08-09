package com.wms.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;

import jakarta.persistence.*;

@Entity
@Getter
@Setter
@ToString
@SuperBuilder
@NoArgsConstructor
@Table(name = "software")
public class Software extends Item {

    @Column(name = "license_id")
    private Long licenseId;
}
