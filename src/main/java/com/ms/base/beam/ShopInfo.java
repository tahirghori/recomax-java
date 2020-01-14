package com.ms.base.beam;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.Date;
import java.util.List;


@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class ShopInfo extends Base {




    @NotEmpty(message = "Please enter your name")
    @Size(min = 3, max = 25, message = "Minimum name length: 3 - 25 characters")
    @Pattern(regexp="[a-zA-Z]+([\\s][a-zA-Z]+)*", message =  "Only characters are allowed!")
    protected String name;



    protected String shopId;
    protected Date lastIndexed;
    protected String lastIndexBy;
    protected Date lastPushed;
    protected Date lastModified;
    protected String lastPushedBy;
    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    private Collection<ConsoleEvent> consoleEvents;

    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    private Collection<PopulerItem> populerItems;
    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    private Collection<QueryEvent> queryEvents;
    protected Date itemLastUpdated;



}
