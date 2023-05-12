package com.wms.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import javax.persistence.*;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@ToString
@Table(name = "software")
public class Software extends Item{

    @Basic
    @Column(name = "license_id")
    private Long licenseId;
}
