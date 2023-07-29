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
@Table(name = "asset")
public class Asset extends Item {
}
