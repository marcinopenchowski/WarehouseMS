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
@Table(name = "accessory")
public class Accessory extends Item {
}
