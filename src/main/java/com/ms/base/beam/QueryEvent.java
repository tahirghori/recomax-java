package com.ms.base.beam;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;


@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class QueryEvent extends Base {




    @NotEmpty(message = "Please enter your name")
    @Size(min = 3, max = 25, message = "Minimum name length: 3 - 25 characters")
    @Pattern(regexp="[a-zA-Z]+([\\s][a-zA-Z]+)*", message =  "Only characters are allowed!")
    protected String name;
    protected Date date;
    protected int count;


}
