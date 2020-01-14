package com.ms.base.beam;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;


@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "role_model")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Role extends Base {


    private String name;

}
