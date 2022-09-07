package com.example.addressbooksystemproject.service;

import com.example.addressbooksystemproject.dto.AddressDto;
import com.example.addressbooksystemproject.model.AddressBook;

import java.util.List;
import java.util.Optional;

public interface AddressBookIService {
    /**
     * All unimplementred methods for service class
     */

    // AddressBook saveData(AddressBook addressDetail);

    AddressBook saveDatadto(AddressDto addressDetail);

    Optional<AddressBook> FindById(Long Id);

    List<AddressBook> findAll();

    AddressBook editById(AddressDto dtomodel, Long Id);

    Optional<AddressBook> deleteById(Long Id);

    List<AddressBook> getAddressBookByemail(String email);

    List<AddressBook> getAddressBookBycity(String city);

    List<AddressBook> getAddressBookByzipcode(Long zipcode);

    String insertRecord(AddressDto addressDto);

    List<AddressBook> getDataByToken(String token);

    Optional<AddressBook> getAllUserByToken(String token);
}


