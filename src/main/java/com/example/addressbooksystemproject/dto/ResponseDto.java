package com.example.addressbooksystemproject.dto;

import com.example.addressbooksystemproject.model.AddressBook;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Optional;

@Data
@NoArgsConstructor
public class ResponseDto {
    private String message;
    private Object object;

    public ResponseDto(String string, String response) {
        this.message = string;
        this.object = response;
    }

    public ResponseDto(String s, Optional<AddressBook> response) {
        this.message = s;
        this.object = response;
    }
    public ResponseDto(String s, List<AddressBook> allEmp) {
        this.message = s;
        this.object = allEmp;
    }

    public ResponseDto(String s, AddressBook token) {
        this.message=s;
        this.object=token;
    }
}