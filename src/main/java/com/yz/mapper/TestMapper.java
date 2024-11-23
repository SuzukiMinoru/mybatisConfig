package com.yz.mapper;

import com.yz.entity.City;
import com.yz.entity.Country;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TestMapper {

    List<String> selectEuro();

    City selectCapitalByCountry(@Param("country") String  country);

    Country selectCountryByName(@Param("country") String country);

    Country selectCountryByName0(@Param("country") String country);

    City selectCityByID(@Param("id") int id);

    List<City> selectCitiesByCountryCode(@Param("countryCode") String countryCode);

    void insertCountry(Country country);
}
