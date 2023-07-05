package ru.javabegin.micro.demo.eurekaclient.repository;


import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

@Data
public class UserDto {
    private Long age;
    private String name;
    private Address address;

    @Autowired
    public UserDto(Address address) {
        this.address = address;
    }
}

