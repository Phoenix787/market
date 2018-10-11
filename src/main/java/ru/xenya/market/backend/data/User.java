package ru.xenya.market.backend.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@EqualsAndHashCode(callSuper = true)
//@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class User extends AbstractEntity {

    private String email;
    private String passwordHash;
    private String firstName;
    private String lastName;

}
