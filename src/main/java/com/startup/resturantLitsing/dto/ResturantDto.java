package com.startup.resturantLitsing.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResturantDto {
    private int id;
    private String name;
    private String address;
    private String city;
    private String resturantDescription;
}
