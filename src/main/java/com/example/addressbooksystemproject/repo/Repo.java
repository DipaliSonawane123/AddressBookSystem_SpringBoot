package com.example.addressbooksystemproject.repo;

import com.example.addressbooksystemproject.model.AddressBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface Repo extends JpaRepository<AddressBook, Long> {
    @Query(value="SELECT * FROM address_book WHERE userid = uerid AND email = :email", nativeQuery=true)
    List<AddressBook> findAddressBookByemail(String email);
    @Query(value="SELECT * FROM address_book,address_book_city WHERE userid = ID AND city = :city", nativeQuery=true)
    List<AddressBook> findAddressBookBycity(String city);
    @Query(value="SELECT * FROM address_book WHERE userid = userid AND zipcode = :zipcode", nativeQuery=true)
    List<AddressBook> findAddressBookByzipcode(Long zipcode);
}






