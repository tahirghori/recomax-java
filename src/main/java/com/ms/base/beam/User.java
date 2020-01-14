package com.ms.base.beam;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "user_model")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class User extends Base {

    @NotEmpty(message = "Please enter your name")
    @Size(min = 3, max = 25, message = "Minimum name length: 3 - 25 characters")
    @Pattern(regexp = "[a-zA-Z]+([\\s][a-zA-Z]+)*", message = "Only characters are allowed!")
    protected String name;


    @NotEmpty(message = "Please enter your Last Name")
    @Size(min = 3, max = 25, message = "Minimum name length: 3 - 25 characters")
    @Pattern(regexp = "[a-zA-Z]+([\\s][a-zA-Z]+)*", message = "Only characters are allowed!")
    protected String lastName;

    @NotEmpty(message = "Please enter your First Name")
    @Size(min = 3, max = 25, message = "Minimum name length: 3 - 25 characters")
    @Pattern(regexp = "[a-zA-Z]+([\\s][a-zA-Z]+)*", message = "Only characters are allowed!")
    protected String firstName;

    private String authId;
    private String clientId;

    @NotNull(message = "Please enter your DOB")
    private String dateOfBirth;

    @NotEmpty(message = "Please enter your phone number")
    @Size(min = 8, max = 18, message = "Phone number must be a valid number!")
    private String phone;

    @NotEmpty(message = "Please enter ISO code")
    @Size(min = 2, max = 3, message = "Phone Country code required with 2-3 length!")
    private String isoCode;

    private String type;
    private boolean isactive;

    private String rewardCode;


    private String address;

    @NotEmpty(message = "Upload Image")
    private String image;


    @ElementCollection
    private List<String> phoneNumbers = new ArrayList<String>();

    @NotEmpty(message = "Please enter your email address")
    @Email
    @Pattern(regexp = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$", message = "Invalid Email Format!")
    private String email;

    @NotEmpty(message = "Please enter your password")
    @Size(min = 8, max = 32, message = "Minimum password length: 8 - 64 characters")
    private String password;


    @Transient
    private String referralCode;


    //    @Enumerated
    @Min(value = 0, message = "Gender can not be other than 0 or 1")
    @Max(value = 1, message = "Gender can not be other than 0 or 1")
    public int gender;

//    public static enum Gender {
//        MALE,
//        FEMALE
//    }


    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    private Collection<Role> userRoles;


}
