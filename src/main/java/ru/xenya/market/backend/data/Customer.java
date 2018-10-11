package ru.xenya.market.backend.data;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Table(name = "customers")
public class Customer extends AbstractEntity {

    @NotBlank
    @Size(min=2, max = 255)
    private String fullName;

    private String address;

    private String phoneNumbers;

    @Size(max = 20, message = "{market.phone.number.invalid")
    @Pattern(regexp = "^((8|\\+7)[\\- ]?)?(\\(?\\d{3}\\)?[\\- ]?)?[\\d\\- ]{7,10}$", message = "{market.phone.number.invalid")
    private String phoneNumberForSMS;

    @OneToMany (cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @OrderColumn
    @JoinColumn
  //  @NotEmpty
    private Set<Order> orders;

    public Customer() {
    }

    public Customer(@NotBlank @Size(max = 255) String fullName) {
        this.fullName = fullName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(String phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    public String getPhoneNumberForSMS() {
        return phoneNumberForSMS;
    }

    public void setPhoneNumberForSMS(String phoneNumberForSMS) {
        this.phoneNumberForSMS = phoneNumberForSMS;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }
}