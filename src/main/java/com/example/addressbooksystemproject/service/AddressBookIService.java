package com.example.addressbooksystemproject.service;

import com.example.addressbooksystemproject.dto.AddressDto;
import com.example.addressbooksystemproject.model.AddressBook;

import java.util.List;
import java.util.Optional;

public interface AddressBookIService {
    /**
     * All unimplementred methods for service class
     */

    AddressBook saveData(AddressBook addressDetail);

    AddressBook saveDatadto(AddressDto addressDetail);

    Optional<AddressBook> FindById(Long Id);

    List<AddressBook> findAll();

    AddressBook editById(AddressDto dtomodel, Long Id);

    void deleteById(Long Id);

    List<AddressBook> getAddressBookByemail(String email);

    List<AddressBook> getAddressBookBycity(String city);

}

