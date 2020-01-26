package com.ms.base.beam;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.hibernate.annotations.*;
import org.hibernate.annotations.Generated;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.validation.BindingResult;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"},
        allowGetters = true)
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Base implements Serializable {


    //    @JsonInclude
//    @Id
//    @GeneratedValue(generator = "uuid2")
//    @GenericGenerator(name = "uuid2", strategy = "uuid2")
//    @Column(name = "id", columnDefinition = "BINARY(16)")
//    protected UUID id;

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Type(type = "uuid-binary")
    private UUID id;


    @CreationTimestamp
    protected Date createdAt;

    @UpdateTimestamp
    protected Date updatedAt;

    @JsonIgnore
    @Column(nullable = false)
    protected boolean deleted;


    @PrePersist
    protected void onCreate() {
        createdAt = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = new Date();
    }

    @PostPersist
    protected void onCreateUUID() {
        id = UUID.randomUUID();
    }



    @JsonIgnore
    @Transient
    protected BindingResult bindingResult;


}
