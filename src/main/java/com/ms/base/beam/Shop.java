package com.ms.base.beam;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;


@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Shop extends Base {




    @NotEmpty(message = "Please enter your name")
    @Size(min = 3, max = 25, message = "Minimum name length: 3 - 25 characters")
    @Pattern(regexp="[a-zA-Z]+([\\s][a-zA-Z]+)*", message =  "Only characters are allowed!")
    protected String name;


    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    private List<Szenario>  szenariosList;

}
