package com.wms.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@SuperBuilder
@NoArgsConstructor
@Table(name = "software")
public class Software extends Item {

    @Basic
    @Column(name = "license_id")
    private Long licenseId;
}
