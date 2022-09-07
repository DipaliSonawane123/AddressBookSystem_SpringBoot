package com.example.addressbooksystemproject.model;

import com.example.addressbooksystemproject.dto.AddressDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collections;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name="AddressBook")
public class AddressBook {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "userid")
    private Long Id;
    String fullName;
    String phoneNumber;
    String email;
    String address;
    @ElementCollection
    @CollectionTable(name="AddressBook_city",joinColumns=@JoinColumn(name="ID"))
    @Column(name="city")
    private List<String>  city;
    String state;
    Long zipcode;

    public AddressBook(AddressDto dto) {
        this.fullName=dto.getFullName();
        this.phoneNumber=dto.getPhoneNumber();
        this.email= dto.getEmail();
        this.address=dto.getAddress();
        this. city= dto.getCity();
        this.state=dto.getState();
        this.zipcode=dto.getZipcode();
    }
}

