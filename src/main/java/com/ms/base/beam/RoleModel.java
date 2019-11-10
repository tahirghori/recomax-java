package com.ms.base.beam;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.util.UUID;


@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "role_model")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class RoleModel extends BaseModel {


    private String name;

}
