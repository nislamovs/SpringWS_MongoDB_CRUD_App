package com.soap.soapserver.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Address {

    private String country;
    private String city;
    private String province;
    private String street;
    private String postcode;
}
