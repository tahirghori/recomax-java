package com.ms.base.beam;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;


@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Szenario extends Base {




    @NotEmpty(message = "Please enter your name")
    @Size(min = 3, max = 25, message = "Minimum name length: 3 - 25 characters")
    @Pattern(regexp="[a-zA-Z]+([\\s][a-zA-Z]+)*", message =  "Only characters are allowed!")
    protected String name;

    @ElementCollection
    private List<String> filterElementList = new ArrayList<String>();


    @ElementCollection
    private List<String> boostElementList = new ArrayList<String>();


}
