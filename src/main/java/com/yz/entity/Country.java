package com.yz.entity;

import com.yz.typehandler.MyString;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Country {

    private MyString countryName;

    private String countryCode;

    private String continent;

    private String region;

    private String localName;

    private String governmentForm;

    private String headOfState;

    private City capital;

    private List<City> cities;

}
