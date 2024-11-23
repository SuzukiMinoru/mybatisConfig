package com.yz.plus.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.yz.typehandler.MyString;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

import java.util.List;

@Data
@Alias("Country")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("country")
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
