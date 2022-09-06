package com.example.addressbooksystemproject.service;

import com.example.addressbooksystemproject.dto.AddressDto;
import com.example.addressbooksystemproject.exception.AddressBookException;
import com.example.addressbooksystemproject.model.AddressBook;
import com.example.addressbooksystemproject.repo.Repo;
import com.example.addressbooksystemproject.util.TokenUtil;
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
    @Autowired
    TokenUtil tokenUtil;
/**
 *Method for Save data without Dto
 */
//    @Override
//    public AddressBook saveData(AddressBook addressDetail) {
//        repository.save(addressDetail);
//        return addressDetail;
//    }
    /**
     *Method for save data in AddressBook using ResponseDTO
     */
    @Override
    public AddressBook saveDatadto(AddressDto addressDetail) {
        AddressBook newdata = new AddressBook(addressDetail);
        repository.save(newdata);
        return newdata;
    }
    /**
     *Method for data find by using id
     */
    @Override
    public Optional<AddressBook> FindById(Long Id) {
        return repository.findById(Id);
    }
    /**
     *Method for find All AddressBooklist
     */
    @Override
    public List<AddressBook> findAll() {
        return repository.findAll();
    }
    /**
     *Method for edit Details in AddressBook
     */
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
    /**
     *Method for delete Data by id
     */
    @Override
    public void deleteById(Long Id) {
        Optional<AddressBook> findById = repository.findById(Id);
        if (findById.isPresent()){
            repository.deleteById(Id);
        } else throw new AddressBookException("Id:"+Id+" not present");

    }
    /**
     *Method for get details by email
     */
    @Override
    public List<AddressBook> getAddressBookByemail(String email) {

        return repository.findAddressBookByemail(email);
    }
    /**
     *Method for get details by city
     */
    @Override
    public List<AddressBook> getAddressBookBycity(String city) {
        return repository.findAddressBookBycity(city);
    }

    @Override
    public List<AddressBook> getAddressBookByzipcode(Long zipcode) {
        return repository.findAddressBookByzipcode(zipcode);
    }

    @Override
    public String insertRecord(AddressDto addressDto)  throws AddressBookException {
        AddressBook addressBook =new AddressBook(addressDto);
        repository.save(addressBook);
        String token = tokenUtil.createToken(addressBook.getId());
     return token;
    }

    @Override
    public List<AddressBook> getDataByToken(String token) {
        long Id=tokenUtil.decodeToken(token);
        Optional<AddressBook> isDataPresent=repository.findById(Id);
        if (isDataPresent.isPresent()){
            List<AddressBook> addressBookList=repository.findAll();
            return addressBookList;
        }else{
            throw new AddressBookException("Token is not found...!");
        }
    }

    @Override
    public Optional<AddressBook> getAllUserByToken(String token) {
        Long Userid = tokenUtil.decodeToken(token);
        Optional<AddressBook> existingData = repository.findById(Userid);
        if(existingData.isPresent()){
            return existingData;
        }else
            throw new AddressBookException("Invalid Token");
    }

    }


