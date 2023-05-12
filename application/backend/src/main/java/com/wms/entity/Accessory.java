package com.wms.entity;

import lombok.*;
import javax.persistence.*;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@ToString
@Table(name = "accessory")
public class Accessory extends Item{
}
