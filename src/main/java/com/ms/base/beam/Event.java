package com.ms.base.beam;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Collection;


@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "[event]")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Event extends Base {

    private String name;
    private String timestamp;
    private String endpoint;
    private String method;
    private String clientIp;
    private String userId;
    private String sessionId;

//
//    @OneToOne(fetch = FetchType.EAGER,cascade=CascadeType.ALL)
//    @JoinColumn(name = "users_id", nullable = false)
//    private User users;

}
