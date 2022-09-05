package com.example.addressbooksystemproject.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import java.util.List;

@Data
@NoArgsConstructor
@Valid
public class AddressDto {

    @Pattern(regexp = "^[A-Z]{1}[a-zA-Z\\s]{2,}$", message=" Name is invalid ")
    String fullName;
    @Pattern(regexp = "^[1-9]{2}[0-9]{10}$", message="Invalid Contact Number(Should have Country Code and must be 10 digit number) example: 919234567890")
    String phoneNumber;
    String address;
    String city;
    String state;
    @Pattern(regexp = "^[1-9]{1}[0-9]{5}$", message="Invalid Zip Code(First digit is non-zero, Should be 6 digit), example: 234098")
    String zip;
    Long zipcode;
    private List<String> email;
}