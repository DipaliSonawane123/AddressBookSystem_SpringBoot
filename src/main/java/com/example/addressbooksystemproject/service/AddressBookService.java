package com.example.addressbooksystemproject.service;

import com.example.addressbooksystemproject.dto.AddressDto;
import com.example.addressbooksystemproject.exception.AddressBookException;
import com.example.addressbooksystemproject.model.AddressBook;
import com.example.addressbooksystemproject.repo.Repo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class AddressBookService implements AddressBookIService {
    @Autowired
    Repo repository;

    @Override
    public AddressBook saveData(AddressBook addressDetail) {
        repository.save(addressDetail);
        return addressDetail;
    }

    @Override
    public AddressBook saveDatadto(AddressDto addressDetail) {
        AddressBook newdata = new AddressBook(addressDetail);
        repository.save(newdata);
        return newdata;
    }

    @Override
    public Optional<AddressBook> FindById(Long Id) {
        return repository.findById(Id);
    }

    @Override
    public List<AddressBook> findAll() {
        return repository.findAll();
    }

    @Override
    public AddressBook editById(AddressDto dtomodel, Long Id) {
        AddressBook editdata = repository.findById(Id).orElse(null);
        if (editdata != null) {
            editdata.setFullName(dtomodel.getFullName());
            editdata.setPhoneNumber(dtomodel.getPhoneNumber());
            editdata.setEmail(dtomodel.getEmail());
            editdata.setAddress(dtomodel.getAddress());
            editdata.setCity(dtomodel.getCity());
            editdata.setState(dtomodel.getState());
            editdata.setZipcode(dtomodel.getZipcode());
            return repository.save(editdata);
        } else
            throw new AddressBookException("Id:"+Id+" is not present ");

    }

    @Override
    public void deleteById(Long Id) {
        Optional<AddressBook> findById = repository.findById(Id);
        if (findById.isPresent()){
            repository.deleteById(Id);
        } else throw new AddressBookException("Id:"+Id+" not present");

    }

    @Override
    public List<AddressBook> getAddressBookByID(String email) {

        return repository.findAddressBookById(email);
    }

    @Override
    public List<AddressBook> getAddressBookBycity(String city) {
        return repository.findAddressBookBycity(city);
    }
}
