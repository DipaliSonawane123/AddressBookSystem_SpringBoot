package com.example.addressbooksystemproject.controller;


import com.example.addressbooksystemproject.dto.AddressDto;
import com.example.addressbooksystemproject.dto.ResponseDto;
import com.example.addressbooksystemproject.model.AddressBook;
import com.example.addressbooksystemproject.service.AddressBookIService;
import org.apache.tomcat.jni.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/AddressBook")
public class AddressBookController {
    @Autowired
    AddressBookIService service;

//    /**
//     * Api for Show mesaage using service layer
//     */
//    @GetMapping("/home")
//    public String serviceCall() {
//        return ("Welcome to AddressBookSystem....");
//    }
//
//    @PostMapping("/post")
//    public AddressBook addAddressDetails(@RequestBody AddressBook addressDetail) {
//        return service.saveData(addressDetail);
//    }

    /**Post Api for using reponse DTO*/
    @PostMapping("/postdto")
    public ResponseEntity<ResponseDto> addUserData(@Valid @RequestBody AddressDto addressBookData) {
        AddressBook response = service.saveDatadto(addressBookData);
        ResponseDto responseDTO = new ResponseDto("Data Added Successfully", (Optional.ofNullable(response)));
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    /**
     * Api to get data using ID delete data
     */
    @GetMapping("/get/{Id}")
    public ResponseEntity<ResponseDto> FindById(@PathVariable Long Id) {
        Optional<AddressBook> response = service.FindById(Id);
        ResponseDto responseDto = new ResponseDto("***All Details of Person using Id***", response);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    /**
     * Api for fetch all data data
     */
    @GetMapping("/findAll")
    public ResponseEntity<ResponseDto> findAllDetail() {
        List<AddressBook> allUser = service.findAll();
        ResponseDto responseDTO = new ResponseDto("** All AddressBook List ** ", allUser);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);

    }
    /**
     * API for edit information
     */
    @PutMapping("/edit/{Id}")
    public ResponseEntity<ResponseDto> updateById(@PathVariable Long Id, @Valid @RequestBody AddressDto addressBookDTO) {
        Optional<AddressBook> Details = Optional.ofNullable(service.editById(addressBookDTO, Id));
        ResponseDto respDTO = new ResponseDto(" **** Person dtails is updated *****", Details);
        return new ResponseEntity<>(respDTO, HttpStatus.OK);
    }

    /**
     * Api fot delete data
     */
    @DeleteMapping("/delete/{Id}")
    public ResponseEntity<ResponseDto> deleteById(@PathVariable Long Id) {
         service.deleteById(Id);
        ResponseDto reponseDTO = new ResponseDto("** AddressBook Details of person deleted successfully ** ", "Id:" + Id + " is deleted");
        return new ResponseEntity(reponseDTO, HttpStatus.ACCEPTED);
    }
    /**
     *Method for AddressBook Details search by email*/
    @GetMapping("/email/{email}")
    public ResponseEntity<ResponseDto> getDataByemail(@PathVariable String email) {
        List<AddressBook> personDetailsList = service.getAddressBookByemail(email);
        ResponseDto respDTO = new ResponseDto("*** Data by using email ***", personDetailsList);
        return new ResponseEntity<>(respDTO, HttpStatus.OK);
    }
    /**
     *Method for AddressBook Details search by city*/
    @GetMapping("/city/{city}")
    public ResponseEntity<ResponseDto> getDataByCity(@PathVariable String city) {
        List<AddressBook> PersonDetailsList = service.getAddressBookBycity(city);
        ResponseDto respDTO = new ResponseDto("*** Data by using city ***", PersonDetailsList);
        return new ResponseEntity<>(respDTO, HttpStatus.OK);
    }
    /**
     *Method for AddressBook Details search by city*/
    @GetMapping("/zipcode/{zipcode}")
    public ResponseEntity<ResponseDto> getDatabyzipcode(@PathVariable Long zipcode) {
        List<AddressBook> PersonDetailsList = service.getAddressBookByzipcode(zipcode);
        ResponseDto respDTO = new ResponseDto("*** Data by using city ***", PersonDetailsList);
        return new ResponseEntity<>(respDTO, HttpStatus.OK);
    }
    @CrossOrigin
    @PostMapping("/insert")
    public ResponseEntity<String>AddAddressDetails(@Valid @RequestBody AddressDto addressDto) {
        String token = service.insertRecord(addressDto);
        ResponseDto respDTO = new ResponseDto("*** Data Added successfully   ***", token);
        return new ResponseEntity(respDTO, HttpStatus.CREATED);
    }
    @CrossOrigin
    @GetMapping("/retrieve/{token}")
    public ResponseEntity<String>getUserDetails(@Valid @PathVariable String token){
        List<AddressBook> userData = service.getDataByToken(token);
        ResponseDto respDTO = new ResponseDto("Data retrieved successfully", userData);
        return new ResponseEntity(respDTO, HttpStatus.CREATED);
    }
    @CrossOrigin
    @GetMapping("/getData/{token}")
    public ResponseEntity<String>getAllUser(@Valid @PathVariable String token){
        Optional<AddressBook> userData = service.getAllUserByToken(token);
        ResponseDto respDTO = new ResponseDto("Data retrieved successfully", userData);
        return new ResponseEntity(respDTO, HttpStatus.CREATED);
    }


}

