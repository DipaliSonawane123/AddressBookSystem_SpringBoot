package com.example.addressbooksystemproject.controller;


import com.example.addressbooksystemproject.dto.AddressDto;
import com.example.addressbooksystemproject.dto.ResponseDto;
import com.example.addressbooksystemproject.model.AddressBook;
import com.example.addressbooksystemproject.service.AddressBookIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/AddressBook")
public class AddressBookController {
    @Autowired
    AddressBookIService service;

    /**
     * Api for Show mesaage using service layer
     */
    @GetMapping("/home")
    public String serviceCall() {
        return ("Welcome to AddressBookSystem....");
    }

    @PostMapping("/post")
    public AddressBook addAddressDetails(@RequestBody AddressBook addressDetail) {
        return service.saveData(addressDetail);
    }


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
        ResponseDto responseDto = new ResponseDto("***All Details of employee using Id***", response);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    /**
     * Api fetch all data data
     */
    @GetMapping("/findAll")
    public ResponseEntity<ResponseDto> findAllDetail() {
        List<AddressBook> allEmp = service.findAll();
        ResponseDto responseDTO = new ResponseDto("** All Employee List ** ", allEmp);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);

    }

    @PutMapping("/edit/{Id}")
    public ResponseEntity<ResponseDto> updateById(@PathVariable Long Id, @Valid @RequestBody AddressDto addressBookDTO) {
        Optional<AddressBook> Details = Optional.ofNullable(service.editById(addressBookDTO, Id));
        ResponseDto respDTO = new ResponseDto("Data Update info", Details);
        return new ResponseEntity<>(respDTO, HttpStatus.OK);
    }

    /**
     * Api fot delete data
     */
    @DeleteMapping("/delete/{Id}")
    public ResponseEntity<ResponseDto> deleteById(@PathVariable Long Id) {
        service.deleteById(Id);
        ResponseDto reponseDTO = new ResponseDto("** Employee Data deleted successfully ** ", "Id:" + Id + " is deleted");
        return new ResponseEntity(reponseDTO, HttpStatus.ACCEPTED);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<ResponseDto> getEmployeeDataByDepartment(@PathVariable String email) {
        List<AddressBook> employeeDataList = service.getAddressBookByID(email);
        ResponseDto respDTO = new ResponseDto("*** Data by using email ***", employeeDataList);
        return new ResponseEntity<>(respDTO, HttpStatus.OK);
    }

    @GetMapping("/city/{city}")
    public ResponseEntity<ResponseDto> getEmployeeDataByCity(@PathVariable String city) {
        List<AddressBook> employeeDataList = service.getAddressBookBycity(city);
        ResponseDto respDTO = new ResponseDto("*** Data by using email ***", employeeDataList);
        return new ResponseEntity<>(respDTO, HttpStatus.OK);
    }
}
